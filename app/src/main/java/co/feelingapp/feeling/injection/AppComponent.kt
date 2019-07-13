package co.feelingapp.feeling.injection

import co.feelingapp.feeling.application.FeelingApplication
import co.feelingapp.feeling.injection.dagger.ActivityModule
import co.feelingapp.feeling.injection.dagger.FragmentModule
import co.feelingapp.feeling.injection.module.AppModule
import co.feelingapp.feeling.injection.module.FeelingAPIModule
import co.feelingapp.feeling.injection.module.MainFragmentModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@SuppressWarnings("unchecked")
@Singleton
@Component(
    modules = [AndroidInjectionModule::class, ActivityModule::class, FragmentModule::class,
        AppModule::class, MainFragmentModule::class, FeelingAPIModule::class]
)
interface AppComponent : AndroidInjector<FeelingApplication>
