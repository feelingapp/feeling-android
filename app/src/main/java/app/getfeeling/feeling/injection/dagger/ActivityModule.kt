package app.getfeeling.feeling.injection.dagger

import app.getfeeling.feeling.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivityInjector(): MainActivity
}
