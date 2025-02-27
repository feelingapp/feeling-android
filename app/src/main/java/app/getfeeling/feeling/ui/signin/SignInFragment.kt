package app.getfeeling.feeling.ui.signin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.TrustedWebUtils
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import app.getfeeling.feeling.BuildConfig
import app.getfeeling.feeling.R
import app.getfeeling.feeling.databinding.SignInFragmentBinding
import app.getfeeling.feeling.util.PKCE
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class SignInFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by activityViewModels<SignInViewModel> { viewModelFactory }

    private lateinit var binding: SignInFragmentBinding

    private val mainNavController: NavController? by lazy { activity?.findNavController(R.id.nav_host_fragment) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SignInFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.token.observe(this, Observer {
            if (it != null) {
                mainNavController?.popBackStack()
            }
        })

        binding.fragment = this
    }

    fun continueWithEmail() {
        val customTabsIntent = CustomTabsIntent.Builder()
            .build()
        customTabsIntent.intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        val codeChallenge = viewModel.generateCodeChallenge()
        val state = viewModel.generateState()

        val uri = Uri.parse(BuildConfig.FEELING_WEBSITE_URL).buildUpon()
            .appendPath("authorize")
            .appendQueryParameter("client_id", BuildConfig.FEELING_API_CLIENT_ID)
            .appendQueryParameter("response_type", "code")
            .appendQueryParameter("redirect_uri", BuildConfig.FEELING_API_REDIRECT_URI)
            .appendQueryParameter("code_challenge_method", PKCE.CODE_CHALLENGE_METHOD)
            .appendQueryParameter("code_challenge", codeChallenge)
            .appendQueryParameter("state", state)
            .build()

        TrustedWebUtils.launchAsTrustedWebActivity(
            context,
            customTabsIntent,
            uri
        )
    }
}
