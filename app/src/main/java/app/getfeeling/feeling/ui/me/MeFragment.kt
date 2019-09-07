package app.getfeeling.feeling.ui.me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import app.getfeeling.feeling.R
import app.getfeeling.feeling.databinding.MeFragmentBinding
import app.getfeeling.feeling.ui.signin.SignInViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MeFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mainNavController: NavController? by lazy { activity?.findNavController(R.id.nav_host_fragment) }

    private val viewModel by activityViewModels<MeViewModel> { viewModelFactory }

    private val signInViewModel by activityViewModels<SignInViewModel> { viewModelFactory }

    private lateinit var binding: MeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (!signInViewModel.isSignedIn()) mainNavController?.navigate(R.id.action_me_fragment_to_sign_in_fragment)

        binding = MeFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
    }
}
