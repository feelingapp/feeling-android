package app.getfeeling.feeling.injection.module

import app.getfeeling.feeling.BuildConfig
import app.getfeeling.feeling.api.FeelingService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class FeelingServiceModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(OkHttpClient().newBuilder().build())
            .baseUrl(BuildConfig.FEELING_API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideFeelingService(retrofit: Retrofit): FeelingService {
        return retrofit.create(FeelingService::class.java)
    }
}
