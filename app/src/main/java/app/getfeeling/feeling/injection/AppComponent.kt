package app.getfeeling.feeling.injection

import app.getfeeling.feeling.FeelingApp
import app.getfeeling.feeling.injection.module.MainActivityModule
import app.getfeeling.feeling.injection.module.FragmentModule
import app.getfeeling.feeling.injection.module.AppModule

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@SuppressWarnings("unchecked")
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class,
        FragmentModule::class
    ]
)
interface AppComponent : AndroidInjector<FeelingApp>
