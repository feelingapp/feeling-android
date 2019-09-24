package app.getfeeling.feeling.injection.module

import app.getfeeling.feeling.ui.day.DayFragment
import app.getfeeling.feeling.ui.me.MeFragment
import app.getfeeling.feeling.ui.settings.MainSettingsFragment
import app.getfeeling.feeling.ui.settings.SettingsFragment
import app.getfeeling.feeling.ui.signin.SignInFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeMeFragment(): MeFragment

    @ContributesAndroidInjector
    abstract fun contributeSettingsFragment(): SettingsFragment

    @ContributesAndroidInjector
    abstract fun contributeMainSettingsFragment(): MainSettingsFragment

    @ContributesAndroidInjector
    abstract fun contributeSignInFragment(): SignInFragment

    @ContributesAndroidInjector
    abstract fun contributeDayFragment(): DayFragment
}
