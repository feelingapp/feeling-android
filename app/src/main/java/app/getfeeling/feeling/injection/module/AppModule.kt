package app.getfeeling.feeling.injection.module

import android.app.Application
import androidx.room.Room
import app.getfeeling.feeling.BuildConfig
import app.getfeeling.feeling.api.FeelingService
import app.getfeeling.feeling.room.FeelingDatabase
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideDatabase(app: Application): FeelingDatabase {
        return Room.databaseBuilder(app, FeelingDatabase::class.java, "FeelingDatabase.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideFeelingService(): FeelingService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.FEELING_API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(OkHttpClient().newBuilder().build())
            .build()
            .create(FeelingService::class.java)
    }
}
