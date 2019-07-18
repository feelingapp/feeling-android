package app.getfeeling.feeling.injection.dagger

import app.getfeeling.feeling.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeMainFragmentInjector(): MainFragment
}
