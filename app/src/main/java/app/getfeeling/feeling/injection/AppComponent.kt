package app.getfeeling.feeling.injection

import app.getfeeling.feeling.application.FeelingApplication
import app.getfeeling.feeling.injection.dagger.ActivityModule
import app.getfeeling.feeling.injection.dagger.FragmentModule
import app.getfeeling.feeling.injection.module.AppModule
import app.getfeeling.feeling.injection.module.FeelingServiceModule
import app.getfeeling.feeling.injection.module.MainFragmentModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@SuppressWarnings("unchecked")
@Singleton
@Component(
    modules = [AndroidInjectionModule::class, ActivityModule::class, FragmentModule::class,
        AppModule::class, MainFragmentModule::class, FeelingServiceModule::class]
)
interface AppComponent : AndroidInjector<FeelingApplication>
