package app.getfeeling.feeling.ui.me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import app.getfeeling.feeling.databinding.MeFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MeFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MeViewModel

    private lateinit var binding: MeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MeFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MeViewModel::class.java)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CalendarViewAdapter(viewModel.feelings.value!!)
        }
    }

}
