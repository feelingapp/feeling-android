package app.getfeeling.feeling

import app.getfeeling.feeling.injection.DaggerAppComponent
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class FeelingApp : DaggerApplication() {

    lateinit var injector: AndroidInjector<out DaggerApplication>

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? = injector

    override fun onCreate() {
        injector = DaggerAppComponent.builder().application(this).build()
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}
