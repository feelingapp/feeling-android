package app.getfeeling.feeling.injection.module

import android.app.Application
import android.content.Context
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import app.getfeeling.feeling.BuildConfig
import app.getfeeling.feeling.api.AuthorizationInterceptor
import app.getfeeling.feeling.api.FeelingService
import app.getfeeling.feeling.api.adapters.OffsetDateTimeJsonAdapter
import app.getfeeling.feeling.repository.FeelingRepository
import app.getfeeling.feeling.repository.TokenRepository
import app.getfeeling.feeling.repository.UserRepository
import app.getfeeling.feeling.repository.interfaces.IFeelingRepository
import app.getfeeling.feeling.repository.interfaces.ITokenRepository
import app.getfeeling.feeling.repository.interfaces.IUserRepository
import app.getfeeling.feeling.room.FeelingDatabase
import app.getfeeling.feeling.room.dao.FeelingDao
import app.getfeeling.feeling.room.dao.UserDao
import app.getfeeling.feeling.ui.me.FeelingMonthDataSourceFactory
import app.getfeeling.feeling.ui.me.calendarDay.AbstractCalendarDayAdapter
import app.getfeeling.feeling.ui.me.calendarDay.CalendarDayAdapter
import app.getfeeling.feeling.ui.me.calendarMonth.AbstractCalendarMonthAdapter
import app.getfeeling.feeling.ui.me.calendarMonth.CalendarMonthAdapter
import app.getfeeling.feeling.valueobjects.Errors
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
import javax.inject.Provider
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideDatabase(app: Application, moshi: Moshi): FeelingDatabase =
        Room.databaseBuilder(app, FeelingDatabase::class.java, "FeelingDatabase.db")
            .build().apply {
                FeelingDatabase.moshi = moshi
            }

    @Singleton
    @Provides
    fun provideFeelingDao(database: FeelingDatabase): FeelingDao = database.feelingDao()

    @Singleton
    @Provides
    fun provideUserDao(database: FeelingDatabase): UserDao = database.userDao()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().add(OffsetDateTimeJsonAdapter()).build()

    @Singleton
    @Provides
    fun provideConverterFactory(moshi: Moshi): Converter.Factory = MoshiConverterFactory.create(moshi)

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Singleton
    @Provides
    fun provideAuthorizationInterceptor(moshi: Moshi, context: Context): AuthorizationInterceptor {
        return AuthorizationInterceptor(moshi, context)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authorizationInterceptor: AuthorizationInterceptor
    ): OkHttpClient =
        OkHttpClient().newBuilder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authorizationInterceptor)
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
    fun provideErrorConverter(retrofit: Retrofit): Converter<ResponseBody, Errors> =
        retrofit.responseBodyConverter(Errors::class.java, arrayOfNulls(0))

    @Singleton
    @Provides
    fun provideFeelingRepository(feelingDao: FeelingDao): IFeelingRepository =
        FeelingRepository(feelingDao)

    @Singleton
    @Provides
    fun provideTokenRepository(
        feelingService: FeelingService,
        errorConverter: Converter<ResponseBody, Errors>,
        moshi: Moshi,
        context: Context
    ): ITokenRepository = TokenRepository(feelingService, errorConverter, moshi, context)

    @Singleton
    @Provides
    fun provideUserRepository(
        feelingService: FeelingService,
        userDao: UserDao
    ): IUserRepository = UserRepository(feelingService, userDao)

    @Provides
    fun provideLinearLayoutManager(context: Context): RecyclerView.LayoutManager =
        LinearLayoutManager(context).apply {
            reverseLayout = true
        }

    @Provides
    fun provideCalendarDayAdapter(): AbstractCalendarDayAdapter = CalendarDayAdapter()

    @Singleton
    @Provides
    fun provideCalendarMonthAdapter(
        context: Context,
        calendarDayAdapterProvider: Provider<AbstractCalendarDayAdapter>
    ): AbstractCalendarMonthAdapter =
        CalendarMonthAdapter(context, calendarDayAdapterProvider)

    @Singleton
    @Provides
    fun provideFeelingMonthDataSourceFactory(feelingRepository: FeelingRepository): FeelingMonthDataSourceFactory =
        FeelingMonthDataSourceFactory(feelingRepository)

    @Singleton
    @Provides
    fun providePagedListConfig(): PagedList.Config = Config(
        pageSize = 1,
        enablePlaceholders = false,
        initialLoadSizeHint = 12
    )
}
