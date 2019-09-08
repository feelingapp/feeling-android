package app.getfeeling.feeling

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import app.getfeeling.feeling.ui.signin.SignInViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val signInViewModel by viewModels<SignInViewModel> { viewModelFactory }

    private lateinit var mainNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        mainNavController = findNavController(R.id.nav_host_fragment)

        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val bottomNavigationViewFab = findViewById<FloatingActionButton>(R.id.fab)
        bottomNavigationView.setupWithNavController(mainNavController)

        mainNavController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.sign_in_fragment) {
                bottomNavigationView.visibility = View.GONE
                bottomNavigationViewFab.visibility = View.GONE
            } else {
                bottomNavigationView.visibility = View.VISIBLE
                bottomNavigationViewFab.visibility = View.VISIBLE
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val uri = intent.data

        if (uri != null && uri.toString().startsWith(BuildConfig.FEELING_API_REDIRECT_URI)) {
            val authorizationCode = uri.getQueryParameter("authorization_code")
            val state = uri.getQueryParameter("state")

            if (authorizationCode != null && state != null)
                signInViewModel.handleAuthorizationCallback(authorizationCode, state)
        }
    }
}
