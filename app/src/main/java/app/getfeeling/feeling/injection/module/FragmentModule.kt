package app.getfeeling.feeling.injection.module

import app.getfeeling.feeling.ui.main.MainFragment
import app.getfeeling.feeling.ui.signin.SignInFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributeSignInFragment(): SignInFragment
}
