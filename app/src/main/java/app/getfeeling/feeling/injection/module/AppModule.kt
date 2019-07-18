package app.getfeeling.feeling.injection.module

import android.app.Application
import androidx.room.Room
import app.getfeeling.feeling.room.FeelingDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideDatabase(app: Application): FeelingDatabase {
        return Room.databaseBuilder(app, FeelingDatabase::class.java, "FeelingDatabase.db")
            .build()
    }
}
