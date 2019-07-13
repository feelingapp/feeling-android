package co.feelingapp.feeling.injection.module

import dagger.Module
import dagger.Provides

@Module
class MainFragmentModule {

    @Provides
    fun provideString(): String {
        return "Injected!"
    }
}
