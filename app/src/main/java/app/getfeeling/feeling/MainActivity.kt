package app.getfeeling.feeling

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import app.getfeeling.feeling.ui.signin.SignInViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        mainNavController = findNavController(R.id.nav_host_fragment)
    }

    fun navigate(resId: Int) {
        mainNavController.navigate(resId)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val uri = intent.data

        if (uri != null && uri.toString().startsWith(BuildConfig.FEELING_API_REDIRECT_URI)) {
            val authorizationCode = uri.getQueryParameter("authorization_code")
            val state = uri.getQueryParameter("state")

            val model = ViewModelProviders.of(this, viewModelFactory).get(SignInViewModel::class.java)

            if (authorizationCode != null && state != null)
                model.callback(authorizationCode, state)
        }
    }
}
