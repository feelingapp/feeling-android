package app.getfeeling.feeling.ui.me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import app.getfeeling.feeling.databinding.MeFragmentBinding
import app.getfeeling.feeling.ui.me.calendarMonth.AbstractCalendarMonthAdapter
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MeFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var calendarLayoutManager: RecyclerView.LayoutManager

    @Inject
    lateinit var calendarMonthAdapter: AbstractCalendarMonthAdapter

    private val meViewModel: MeViewModel by activityViewModels { viewModelFactory }

    private lateinit var binding: MeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MeFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(binding) {
            viewModel = meViewModel
            with(recyclerView) {
                layoutManager = calendarLayoutManager
                adapter = calendarMonthAdapter.apply {
                    setFeelingCalendar(meViewModel.feelingCalendar)
                }
            }
        }
    }
}
