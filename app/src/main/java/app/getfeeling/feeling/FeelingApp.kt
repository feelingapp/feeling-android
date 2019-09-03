package app.getfeeling.feeling

import android.content.Context
import app.getfeeling.feeling.injection.DaggerAppComponent
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class FeelingApp : DaggerApplication() {

    private lateinit var injector: AndroidInjector<out DaggerApplication>

    init {
        instance = this
    }

    companion object {
        private var instance: FeelingApp? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? = injector

    override fun onCreate() {
        injector = DaggerAppComponent.builder().application(this).appContext(this).build()
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}
