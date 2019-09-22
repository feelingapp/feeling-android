package app.getfeeling.feeling.ui.day

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import app.getfeeling.feeling.R
import app.getfeeling.feeling.databinding.DayFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DayFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val dayViewModel: DayViewModel by activityViewModels { viewModelFactory }

    private val mainNavController: NavController? by lazy { activity?.findNavController(R.id.nav_host_fragment) }

    private lateinit var binding: DayFragmentBinding

    private val args: DayFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DayFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dayViewModel.feelingId.value = args.feelingId
        binding.viewModel = dayViewModel
        binding.toolbar.setNavigationOnClickListener { mainNavController?.navigateUp() }

        dayViewModel.feeling.observe(this) { feeling ->
            val colour = ContextCompat.getColor(context!!, feeling.emotion.getColour())
            binding.layout.setBackgroundColor(colour)
            binding.appBar.setBackgroundColor(colour)
            binding.toolbarLayout.setContentScrimColor(colour)
            binding.toolbar.setBackgroundColor(colour)
            binding.imageEmoji.setImageResource(feeling.emotion.getEmoji())
        }
    }
}
