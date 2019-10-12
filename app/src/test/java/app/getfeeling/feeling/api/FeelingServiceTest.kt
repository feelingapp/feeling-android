package app.getfeeling.feeling.api

import app.getfeeling.feeling.TestHelper
import app.getfeeling.feeling.api.adapters.OffsetDateTimeJsonAdapter
import app.getfeeling.feeling.util.Emotion
import app.getfeeling.feeling.valueobjects.*
import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Moshi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.logging.Level
import java.util.logging.Logger

class FeelingServiceTest {

    private lateinit var mockWebServer: MockWebServer

    private lateinit var feelingService: FeelingService

    private lateinit var moshi: Moshi

    @Before
    fun setUp() {
        // Turn down logging for the MockWebServer as it was being too loud
        Logger.getLogger(MockWebServer::class.java.name).level = Level.WARNING
        mockWebServer = MockWebServer()
        mockWebServer.start()

        moshi = Moshi.Builder().add(OffsetDateTimeJsonAdapter()).build()

        feelingService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/").toString())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(OkHttpClient().newBuilder().build())
            .build()
            .create(FeelingService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getStatus_success() {
        runBlocking {
            // arrange
            mockWebServer.enqueue(MockResponse().setResponseCode(200))

            // act
            val response = feelingService.getStatus()

            // assert
            assertThat(response.isSuccessful).isTrue()
        }
    }

    @Test
    fun getStatus_failure() {
        runBlocking {
            // arrange
            mockWebServer.enqueue(MockResponse().setResponseCode(400))

            // act
            val response = feelingService.getStatus()

            // assert
            assertThat(response.isSuccessful).isFalse()
        }
    }

    @Test
    fun checkIfUserExists_doesExist() {
        // arrange
        val expectedResponseBody = TestHelper.getJson("get_userExists_doesExist.json")
        val expectedUserExists = UserExistsJsonAdapter(moshi).fromJson(expectedResponseBody)!!
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(expectedResponseBody)
        )

        // act
        val response = runBlocking {
            feelingService.checkIfUserExists("someone@gmail.com")
        }

        // assert
        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()!!).isEqualTo(expectedUserExists)
    }

    @Test
    fun checkIfUserExists_doesNotExist() {
        // arrange
        val expectedResponse = TestHelper.getJson("get_userExists_doesNotExist.json")
        val expectedUserExists = UserExistsJsonAdapter(moshi).fromJson(expectedResponse)!!
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(expectedResponse)
        )

        // act
        val response = runBlocking {
            feelingService.checkIfUserExists("someone@gmail.com")
        }

        // assert
        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()!!).isEqualTo(expectedUserExists)
    }

    @Test
    fun getFeeling_success() {
        // arrange
        val expectedResponseBody = TestHelper.getJson("get_feeling_success.json")
        val expectedFeeling = FeelingJsonAdapter(moshi).fromJson(expectedResponseBody)!!
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(expectedResponseBody)
        )

        // act
        val response = runBlocking { feelingService.getFeeling("1") }

        // assert
        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()!!).isEqualTo(expectedFeeling)
    }

    @Test
    fun getFeelings_success() {
        // arrange
        val expectedResponseBody = TestHelper.getJson("get_feelings_success.json")
        val expectedFeelings = FeelingsJsonAdapter(moshi).fromJson(expectedResponseBody)!!
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(expectedResponseBody)
        )

        // act
        val response = runBlocking { feelingService.getFeelings() }

        // assert
        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()!!).isEqualTo(expectedFeelings)
    }

    @Test
    fun getFeeling_failure() {
        // arrange
        mockWebServer.enqueue(MockResponse().setResponseCode(404))

        // act
        val response = runBlocking { feelingService.getFeeling("1") }

        // assert
        assertThat(response.isSuccessful).isFalse()
    }

    @Test
    fun createFeeling_success() {
        // arrange
        mockWebServer.enqueue(MockResponse().setResponseCode(200))

        // act
        val response = runBlocking {
            feelingService.createFeeling(Feeling("", Emotion.AMAZING, "", listOf()))
        }

        // assert
        assertThat(response.isSuccessful).isTrue()
    }

    @Test
    fun updateFeeling_success() {
        // arrange
        mockWebServer.enqueue(MockResponse().setResponseCode(200))

        // act
        val response = runBlocking {
            feelingService.updateFeeling("1", Feeling("", Emotion.AMAZING, "", listOf()))
        }

        // assert
        assertThat(response.isSuccessful).isTrue()
    }

    @Test
    fun updateFeeling_failure() {
        // arrange
        mockWebServer.enqueue(MockResponse().setResponseCode(404))

        // act
        val response = runBlocking {
            feelingService.updateFeeling("1", Feeling("", Emotion.AMAZING, "", listOf()))
        }

        // assert
        assertThat(response.isSuccessful).isFalse()
    }

    @Test
    fun deleteFeeling_success() {
        // arrange
        mockWebServer.enqueue(MockResponse().setResponseCode(200))

        // act
        val response = runBlocking { feelingService.deleteFeeling("1") }

        // assert
        assertThat(response.isSuccessful).isTrue()
    }

    @Test
    fun getQuote_success() {
        // arrange
        val expectedResponseBody = TestHelper.getJson("get_quote_success.json")
        val expectedQuote = QuoteJsonAdapter(moshi).fromJson(expectedResponseBody)!!
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(expectedResponseBody)
        )

        // act
        val response = runBlocking { feelingService.getQuote("amazing") }

        // assert
        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()!!).isEqualTo(expectedQuote)
    }

    @Test
    fun getQuote_failure_EmotionDoesNotExist() {
        // arrange
        mockWebServer.enqueue(MockResponse().setResponseCode(400))

        // act
        val response = runBlocking { feelingService.getQuote("amazing") }

        // assert
        assertThat(response.isSuccessful).isFalse()
    }

    @Test
    fun getQuote_failure_QuoteDoesNotExist() {
        // arrange
        mockWebServer.enqueue(MockResponse().setResponseCode(404))

        // act
        val response = runBlocking { feelingService.getQuote("amazing") }

        // assert
        assertThat(response.isSuccessful).isFalse()
    }

    @Test
    fun getSettings_success() {
        // arrange
        val expectedResponseBody = TestHelper.getJson("get_settings_success.json")
        val expectedSettings = SettingsJsonAdapter(moshi).fromJson(expectedResponseBody)!!
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(expectedResponseBody)
        )

        // act
        val response = runBlocking { feelingService.getSettings() }

        // assert
        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()!!).isEqualTo(expectedSettings)
    }

    @Test
    fun getSettings_failure() {
        // arrange
        mockWebServer.enqueue(MockResponse().setResponseCode(404))

        // act
        val response = runBlocking { feelingService.getSettings() }

        // assert
        assertThat(response.isSuccessful).isFalse()
    }

    @Test
    fun updateSettings_success() {
        // arrange
        mockWebServer.enqueue(MockResponse().setResponseCode(200))

        // act
        val response = runBlocking {
            feelingService.updateSettings(Settings("", Settings.DailyReminder(true, 0, 0)))
        }

        // assert
        assertThat(response.isSuccessful).isTrue()
    }
}
