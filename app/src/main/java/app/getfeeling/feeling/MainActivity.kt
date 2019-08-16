package app.getfeeling.feeling

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.AndroidInjection

class MainActivity : AppCompatActivity(), PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

    private lateinit var navController: NavController

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        navController = findNavController(R.id.nav_host_fragment)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemReselectedListener { }
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_settings -> navigate(R.id.action_main_fragment_to_settings_fragment)
                R.id.action_me -> navigate(R.id.action_settings_fragment_to_main_fragment)
            }
            true
        }
    }

    override fun onPreferenceStartFragment(caller: PreferenceFragmentCompat?, pref: Preference?): Boolean {
        when (pref?.fragment) {
            "app.getfeeling.feeling.ui.settings.DailyReminderFragment" -> navigate(R.id.action_settings_fragment_to_dailyReminderFragment)
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    private fun navigate(resId: Int) {
        navController.navigate(resId)
    }
}
