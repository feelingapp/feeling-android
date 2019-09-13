package app.getfeeling.feeling

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import app.getfeeling.feeling.databinding.MainActivityBinding
import app.getfeeling.feeling.ui.me.MeViewModel
import app.getfeeling.feeling.ui.signin.SignInViewModel
import app.getfeeling.feeling.util.makeStatusBarTransparent
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val meViewModel: MeViewModel by viewModels { viewModelFactory }

    private val signInViewModel: SignInViewModel by viewModels { viewModelFactory }

    private lateinit var mainNavController: NavController

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        mainNavController = findNavController(R.id.nav_host_fragment)

        setupBottomNavigationView()

        binding.fab.setOnClickListener {
            meViewModel.addFeeling()
        }
        this.makeStatusBarTransparent()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.nav_host_fragment)) { _, insets ->
            insets.consumeSystemWindowInsets()
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

    private fun setupBottomNavigationView() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val bottomNavigationViewFab = findViewById<FloatingActionButton>(R.id.fab)
        bottomNavigationView.setupWithNavController(mainNavController)

        mainNavController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.sign_in_fragment) {
                bottomNavigationView.visibility = View.GONE
                bottomNavigationViewFab.hide()
            } else {
                bottomNavigationView.visibility = View.VISIBLE
                bottomNavigationViewFab.show()
            }
        }
    }
}
