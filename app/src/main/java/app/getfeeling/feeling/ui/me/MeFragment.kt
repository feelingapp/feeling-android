package app.getfeeling.feeling.ui.me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import app.getfeeling.feeling.R
import app.getfeeling.feeling.databinding.MeFragmentBinding
import app.getfeeling.feeling.ui.me.calendarMonth.AbstractCalendarMonthAdapter
import app.getfeeling.feeling.ui.signin.SignInViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import javax.inject.Provider

class MeFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var calendarLayoutManager: Provider<RecyclerView.LayoutManager>

    @Inject
    lateinit var calendarMonthAdapter: AbstractCalendarMonthAdapter

    private val mainNavController: NavController? by lazy { activity?.findNavController(R.id.nav_host_fragment) }

    private val meViewModel: MeViewModel by activityViewModels { viewModelFactory }

    private val signInViewModel: SignInViewModel by activityViewModels { viewModelFactory }

    private lateinit var binding: MeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (!signInViewModel.isSignedIn()) {
            mainNavController?.navigate(R.id.action_me_fragment_to_sign_in_fragment)
        }

        binding = MeFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        meViewModel.getUser()

        meViewModel.feelingCalendar.observe(this) { feelingCalendar ->
            calendarMonthAdapter.setFeelingCalendar(feelingCalendar)
        }

        with(binding) {
            viewModel = meViewModel
            with(recyclerView) {
                layoutManager = calendarLayoutManager.get()
                adapter = calendarMonthAdapter
            }
        }
    }
}
