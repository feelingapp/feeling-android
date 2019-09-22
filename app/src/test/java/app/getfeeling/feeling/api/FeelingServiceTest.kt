package app.getfeeling.feeling.api

import app.getfeeling.feeling.TestHelper
import app.getfeeling.feeling.api.models.AccountModelJsonAdapter
import app.getfeeling.feeling.api.models.FeelingModelJsonAdapter
import app.getfeeling.feeling.api.models.FeelingsModelJsonAdapter
import app.getfeeling.feeling.api.models.QuoteModelJsonAdapter
import app.getfeeling.feeling.valueobjects.Feeling
import app.getfeeling.feeling.valueobjects.FeelingJsonAdapter
import app.getfeeling.feeling.valueobjects.FeelingsJsonAdapter
import app.getfeeling.feeling.valueobjects.Settings
import app.getfeeling.feeling.util.Emotion
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

        feelingService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/").toString())
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient().newBuilder().build())
            .build()
            .create(FeelingService::class.java)

        moshi = Moshi.Builder().build()
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
    fun checkIfAccountExists_success() {
        // arrange
        val expectedResponseBody = TestHelper.getJson("get_accountExists_success.json")
        val expectedAccountModel = AccountModelJsonAdapter(moshi).fromJson(expectedResponseBody)!!
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(expectedResponseBody)
        )

        // act
        val response = runBlocking {
            feelingService.checkIfAccountExists("someone@gmail.com")
        }

        // assert
        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()!!).isEqualTo(expectedAccountModel)
    }

    @Test
    fun checkIfAccountExists_failure() {
        // arrange
        val expectedResponse = TestHelper.getJson("get_accountExists_failure.json")
        val expectedAccountModel = AccountModelJsonAdapter(moshi).fromJson(expectedResponse)!!
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(expectedResponse)
        )

        // act
        val response = runBlocking {
            feelingService.checkIfAccountExists("someone@gmail.com")
        }

        // assert
        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()!!).isEqualTo(expectedAccountModel)
    }

    @Test
    fun getFeelings_success() {
        // arrange
        val expectedResponseBody = TestHelper.getJson("get_feelings_success.json")
        val expectedFeelingsModel = FeelingsJsonAdapter(moshi).fromJson(expectedResponseBody)!!
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(expectedResponseBody)
        )

        // act
        val response = runBlocking { feelingService.getFeelings() }

        // assert
        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()!!).isEqualTo(expectedFeelingsModel)
    }

    @Test
    fun getFeeling_success() {
        // arrange
        val expectedResponseBody = TestHelper.getJson("get_feeling_success.json")
        val expectedFeelingModel = FeelingJsonAdapter(moshi).fromJson(expectedResponseBody)!!
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(expectedResponseBody)
        )

        // act
        val response = runBlocking { feelingService.getFeeling(1) }

        // assert
        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()!!).isEqualTo(expectedFeelingModel)
    }

    @Test
    fun getFeeling_failure() {
        // arrange
        mockWebServer.enqueue(MockResponse().setResponseCode(404))

        // act
        val response = runBlocking { feelingService.getFeeling(1) }

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
            feelingService.updateFeeling(1, Feeling("", Emotion.AMAZING, "", listOf()))
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
            feelingService.updateFeeling(1, Feeling("", Emotion.AMAZING, "", listOf()))
        }

        // assert
        assertThat(response.isSuccessful).isFalse()
    }

    @Test
    fun deleteFeeling_success() {
        // arrange
        mockWebServer.enqueue(MockResponse().setResponseCode(200))

        // act
        val response = runBlocking { feelingService.deleteFeeling(1) }

        // assert
        assertThat(response.isSuccessful).isTrue()
    }

    @Test
    fun getQuote_success() {
        // arrange
        val expectedResponseBody = TestHelper.getJson("get_quote_success.json")
        val expectedQuoteModel = QuoteModelJsonAdapter(moshi).fromJson(expectedResponseBody)!!
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(expectedResponseBody)
        )

        // act
        val response = runBlocking { feelingService.getQuote("amazing") }

        // assert
        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()!!).isEqualTo(expectedQuoteModel)
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
