package app.getfeeling.feeling

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import app.getfeeling.feeling.databinding.MainActivityBinding
import app.getfeeling.feeling.ui.signin.SignInViewModel
import app.getfeeling.feeling.util.Emotion
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val signInViewModel: SignInViewModel by viewModels { viewModelFactory }

    private lateinit var mainNavController: NavController

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        mainNavController = findNavController(R.id.nav_host_fragment)

        setupBottomNavigationView()

        if (!signInViewModel.isSignedIn()) {
            mainNavController.navigate(R.id.action_me_fragment_to_sign_in_fragment)
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
        val bottomAppBar = binding.bottomAppBar
        val bottomNavigationView = binding.bottomNavigation
        val bottomNavigationViewFab = binding.fab
        bottomNavigationView.setupWithNavController(mainNavController)

        val randomEmotion = Emotion.values().random()
        with(bottomNavigationViewFab) {
            backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(context, randomEmotion.getColour()))
            setImageResource(randomEmotion.getEmoji())
        }

        mainNavController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.sign_in_fragment) {
                bottomAppBar.visibility = View.GONE
                bottomNavigationView.visibility = View.GONE
                bottomNavigationViewFab.hide()
            } else {
                bottomAppBar.visibility = View.VISIBLE
                bottomNavigationView.visibility = View.VISIBLE
                bottomNavigationViewFab.show()
            }
        }
    }
}
