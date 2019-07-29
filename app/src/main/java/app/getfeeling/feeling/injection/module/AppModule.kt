package app.getfeeling.feeling.injection.module

import android.app.Application
import androidx.room.Room
import app.getfeeling.feeling.BuildConfig
import app.getfeeling.feeling.api.FeelingService
import app.getfeeling.feeling.repository.FeelingRepository
import app.getfeeling.feeling.repository.interfaces.IFeelingRepository
import app.getfeeling.feeling.room.FeelingDatabase
import app.getfeeling.feeling.room.dao.FeelingDao
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideDatabase(app: Application): FeelingDatabase {
        return Room.databaseBuilder(app, FeelingDatabase::class.java, "FeelingDatabase.db")
            .build()
    }

    @Singleton
    @Provides
    fun provideFeelingDao(database: FeelingDatabase): FeelingDao {
        return database.feelingDao()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return MoshiConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideFeelingService(converterFactory: Converter.Factory, okHttpClient: OkHttpClient): FeelingService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.FEELING_API_URL)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
            .create(FeelingService::class.java)
    }

    @Singleton
    @Provides
    fun provideFeelingRepository(feelingDao: FeelingDao, feelingService: FeelingService): IFeelingRepository {
        return FeelingRepository(feelingDao, feelingService)
    }
}
