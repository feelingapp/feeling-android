package app.getfeeling.feeling.injection

import android.app.Application
import android.content.Context
import app.getfeeling.feeling.FeelingApp
import app.getfeeling.feeling.injection.module.AppModule
import app.getfeeling.feeling.injection.module.FragmentModule
import app.getfeeling.feeling.injection.module.MainActivityModule
import app.getfeeling.feeling.injection.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@SuppressWarnings("unchecked", "unsafe")
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<FeelingApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun appContext(context: Context): Builder

        fun build(): AppComponent
    }
}
