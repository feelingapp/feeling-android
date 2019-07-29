package app.getfeeling.feeling.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.getfeeling.feeling.injection.ViewModelFactory
import app.getfeeling.feeling.injection.ViewModelKey
import app.getfeeling.feeling.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}
