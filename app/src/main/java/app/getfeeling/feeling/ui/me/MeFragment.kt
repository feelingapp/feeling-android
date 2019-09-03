package app.getfeeling.feeling.ui.me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import app.getfeeling.feeling.R
import app.getfeeling.feeling.databinding.MeFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MeFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MeViewModel

    private lateinit var binding: MeFragmentBinding

    private lateinit var calendarMonthAdapter: CalendarMonthAdapter

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
        viewModel = ViewModelProvider(this, viewModelFactory).get(MeViewModel::class.java)
        calendarMonthAdapter = CalendarMonthAdapter()
        calendarMonthAdapter.allFeelings = viewModel.allFeelings
        calendarMonthAdapter.months = resources.getStringArray(R.array.months)

//        viewModel.getAllFeelingsGroupedByMonth().observe(this) {
//            calendarViewAdapter.allFeelings = it
//        }

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.stackFromEnd = true

        binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = calendarMonthAdapter
        }
    }
}
