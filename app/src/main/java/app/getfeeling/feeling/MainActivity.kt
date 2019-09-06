package app.getfeeling.feeling

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import app.getfeeling.feeling.ui.me.MeViewModel
import app.getfeeling.feeling.ui.signin.SignInViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        mainNavController = findNavController(R.id.nav_host_fragment)
        mainNavController.navigate(R.id.sign_in_fragment)

        setupBottomNavigationView()

        val viewModel = ViewModelProvider(this, viewModelFactory).get(MeViewModel::class.java)
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            viewModel.addFeeling()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val uri = intent.data

        if (uri != null && uri.toString().startsWith(BuildConfig.FEELING_API_REDIRECT_URI)) {
            val authorizationCode = uri.getQueryParameter("authorization_code")
            val state = uri.getQueryParameter("state")

            val model = ViewModelProvider(this, viewModelFactory).get(SignInViewModel::class.java)

            if (authorizationCode != null && state != null)
                model.handleAuthorizationCallback(authorizationCode, state)
        }
    }

    private fun setupBottomNavigationView() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val bottomNavigationViewFab = findViewById<FloatingActionButton>(R.id.fab)

        bottomNavigationView.setupWithNavController(mainNavController)
        bottomNavigationView.setOnNavigationItemReselectedListener { }
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_settings -> navigate(R.id.action_me_fragment_to_settings_fragment)
                R.id.action_me -> navigate(R.id.action_settings_fragment_to_me_fragment)
            }
            true
        }

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

    private fun navigate(resId: Int) {
        mainNavController.navigate(resId)
    }
}
