package com.nwebber.instamet

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.nwebber.instamet.ui.main.AboutFragment

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var navHostFragment: NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupActionBarWithNavController(this, navHostFragment.navController)

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.let {
                it.title = when (destination.id) {
                    R.id.settingsFragment -> getString(R.string.settings)
                    R.id.aboutFragment -> getString(R.string.about)
                    R.id.resultFragment -> getString(R.string.search_results)
                    else -> getString(R.string.app_name)
                }
            }
        }

    }

    override fun onSupportNavigateUp() = Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val currentFragment = Navigation.findNavController(this, R.id.nav_host_fragment).currentDestination
        if (currentFragment != null) {
            Log.d(TAG, "Current Fragment: ${currentFragment.label}")
            return when (item.itemId) {
                R.id.settings_item -> {
                     when(currentFragment.label){
                        "main_fragment" -> {
                            navHostFragment.navController.navigate(R.id.action_mainFragment_to_settingsFragment)
                            true
                        }
                        "fragment_about" -> {
                            navHostFragment.navController.navigate(R.id.action_aboutFragment_to_settingsFragment)
                            true
                        }
                        "fragment_settings" -> {
                            //Already here!
                            true
                        }
                        "fragment_result" -> {
                            navHostFragment.navController.navigate(R.id.action_resultFragment_to_settingsFragment)
                            true
                        }
                        else ->{
                            false
                        }
                    }

                }
                R.id.about_item -> {
                     when(currentFragment.label){
                        "main_fragment" -> {
                            navHostFragment.navController.navigate(R.id.action_mainFragment_to_aboutFragment)
                            true
                        }
                        "fragment_about" -> {
                            //Already here!
                            true
                        }
                        "fragment_settings" -> {
                            navHostFragment.navController.navigate(R.id.action_settingsFragment_to_aboutFragment)
                            true
                        }
                        "fragment_result" -> {
                            navHostFragment.navController.navigate(R.id.action_resultFragment_to_aboutFragment)
                            true
                        }
                        else ->{
                            false
                        }
                    }
                }
                else -> super.onOptionsItemSelected(item)
            }
        }
        return true
    }
}