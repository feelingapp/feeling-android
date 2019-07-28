package app.getfeeling.feeling.ui.main

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.getfeeling.feeling.R
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.isApiOnline.observe(this, Observer(::updateApiOnline))
        button.setOnClickListener { viewModel.setInput() }
    }

    private fun updateApiOnline(isApiOnline: String) {
        textView.text = isApiOnline

        if (isApiOnline == "Online") {
            Toast.makeText(context, "Successful response from Feeling API", Toast.LENGTH_LONG).show()
            textView.setTextColor(Color.GREEN)
        } else {
            Toast.makeText(context, "No response from Feeling API", Toast.LENGTH_LONG).show()
            textView.setTextColor(Color.RED)
        }
    }
}
