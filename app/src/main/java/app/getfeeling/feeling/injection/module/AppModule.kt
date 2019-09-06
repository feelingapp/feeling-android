package app.getfeeling.feeling.injection.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import app.getfeeling.feeling.BuildConfig
import app.getfeeling.feeling.api.FeelingService
import app.getfeeling.feeling.api.models.ErrorsModel
import app.getfeeling.feeling.repository.FeelingRepository
import app.getfeeling.feeling.repository.TokenRepository
import app.getfeeling.feeling.repository.interfaces.IFeelingRepository
import app.getfeeling.feeling.repository.interfaces.ITokenRepository
import app.getfeeling.feeling.room.FeelingDatabase
import app.getfeeling.feeling.room.dao.FeelingDao
import app.getfeeling.feeling.room.dao.UserDao
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
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
    fun provideDatabase(app: Application): FeelingDatabase =
        Room.databaseBuilder(app, FeelingDatabase::class.java, "FeelingDatabase.db")
            .build()

    @Singleton
    @Provides
    fun provideFeelingDao(database: FeelingDatabase): FeelingDao = database.feelingDao()

    @Singleton
    @Provides
    fun provideUserDao(database: FeelingDatabase): UserDao = database.userDao()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory = MoshiConverterFactory.create()

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient().newBuilder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS).build()

    @Singleton
    @Provides
    fun provideRetrofit(converterFactory: Converter.Factory, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.FEELING_API_URL)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideFeelingService(retrofit: Retrofit): FeelingService =
        retrofit.create(FeelingService::class.java)

    @Singleton
    @Provides
    fun provideErrorConverter(retrofit: Retrofit): Converter<ResponseBody, ErrorsModel> =
        retrofit.responseBodyConverter(ErrorsModel::class.java, arrayOfNulls(0))

    @Singleton
    @Provides
    fun provideFeelingRepository(
        feelingDao: FeelingDao,
        userDao: UserDao,
        feelingService: FeelingService,
        errorConverter: Converter<ResponseBody, ErrorsModel>
    ): IFeelingRepository = FeelingRepository(feelingDao, userDao, feelingService, errorConverter)

    @Singleton
    @Provides
    fun provideTokenRepository(
        feelingService: FeelingService,
        errorConverter: Converter<ResponseBody, ErrorsModel>,
        moshi: Moshi,
        context: Context
    ): ITokenRepository = TokenRepository(feelingService, errorConverter, moshi, context)
}
