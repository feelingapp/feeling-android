package app.getfeeling.feeling.injection.module

import app.getfeeling.feeling.BuildConfig
import app.getfeeling.feeling.web.FeelingAPI
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class FeelingAPIModule {
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
    fun provideFeelingAPI(retrofit: Retrofit): FeelingAPI {
        return retrofit.create(FeelingAPI::class.java)
    }
}
