package app.getfeeling.feeling.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import app.getfeeling.feeling.R
import app.getfeeling.feeling.databinding.HomeFragmentBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {
    private val mainNavController: NavController? by lazy { activity?.findNavController(R.id.nav_host_fragment) }

    private val homeNavController: NavController? by lazy { view?.findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = HomeFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemReselectedListener { }
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_settings -> navigate(R.id.action_main_fragment_to_settings_fragment)
                R.id.action_me -> navigate(R.id.action_settings_fragment_to_main_fragment)
            }
            true
        }
    }

    private fun navigate(resId: Int) {
        homeNavController?.navigate(resId)
    }
}
