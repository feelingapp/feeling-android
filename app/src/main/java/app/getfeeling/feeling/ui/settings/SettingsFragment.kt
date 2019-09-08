package app.getfeeling.feeling.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import app.getfeeling.feeling.R
import app.getfeeling.feeling.databinding.SettingsFragmentBinding

class SettingsFragment : Fragment() {
    private val settingsNavController: NavController? by lazy { activity?.findNavController(R.id.settings_nav_host_fragment) }

    private lateinit var binding: SettingsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SettingsFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        setupWithNavController(
            toolbar,
            settingsNavController!!,
            AppBarConfiguration(settingsNavController!!.graph)
        )
    }
}
