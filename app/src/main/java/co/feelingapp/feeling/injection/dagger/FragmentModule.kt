package co.feelingapp.feeling.injection.dagger

import co.feelingapp.feeling.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeMainFragmentInjector(): MainFragment
}
