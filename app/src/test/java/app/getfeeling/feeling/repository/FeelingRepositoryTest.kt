package app.getfeeling.feeling.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.getfeeling.feeling.api.FeelingService
import app.getfeeling.feeling.api.models.ErrorsModel
import app.getfeeling.feeling.room.dao.FeelingDao
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Converter
import retrofit2.Response

@Suppress("UNCHECKED_CAST")
class FeelingRepositoryTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun getStatus_IsOnline() {
        runBlocking {
            // arrange
            val mockFeelingDao = mock(FeelingDao::class.java)
            val mockFeelingService = mock(FeelingService::class.java)
            val mockErrorConverter = mock(Converter::class.java) as Converter<ResponseBody, ErrorsModel>

            `when`(mockFeelingService.getStatus()).thenReturn(Response.success(200, Unit))

            val feelingRepository = FeelingRepository(mockFeelingDao, mockFeelingService, mockErrorConverter)

            // act
            val response = feelingRepository.getStatus()

            // assert
            response.observeForever {
                assertThat(response.value).matches("Online")
            }
        }
    }

    @Test
    fun getStatus_IsOffline() {
        runBlocking {
            // arrange
            val mockFeelingDao = mock(FeelingDao::class.java)
            val mockFeelingService = mock(FeelingService::class.java)
            val mockErrorConverter = mock(Converter::class.java) as Converter<ResponseBody, ErrorsModel>

            `when`(mockFeelingService.getStatus()).thenReturn(Response.error(400, "".toResponseBody()))

            val feelingRepository = FeelingRepository(mockFeelingDao, mockFeelingService, mockErrorConverter)

            // act
            val response = feelingRepository.getStatus()

            // assert
            response.observeForever {
                assertThat(response.value).matches("Offline")
            }
        }
    }
}
