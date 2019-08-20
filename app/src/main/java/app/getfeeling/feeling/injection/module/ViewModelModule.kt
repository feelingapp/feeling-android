package app.getfeeling.feeling.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.getfeeling.feeling.injection.ViewModelFactory
import app.getfeeling.feeling.injection.ViewModelKey
import app.getfeeling.feeling.ui.main.MainViewModel
import app.getfeeling.feeling.ui.signin.SignInViewModel
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

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    abstract fun bindSignInViewModel(signInViewModel: SignInViewModel): ViewModel
}
