package com.shahriar.task8.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shahriar.task8.R

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnApplyWindowInsetsListener(null)
        bottomNav.setPadding(0, 0, 0, 0)

        val homeFragment: Fragment = HomeFragment()
        val scheduleFragment: Fragment = ScheduleFragment()
        val favouriteFragment: Fragment = FavouriteFragment()
        val profileFragment: Fragment = ProfileFragment()

        var activeFragment: Fragment = homeFragment

        supportFragmentManager.beginTransaction().apply {
            add(R.id.content, homeFragment)
            add(R.id.content, scheduleFragment)
            add(R.id.content, favouriteFragment)
            add(R.id.content, profileFragment)
            hide(scheduleFragment)
            hide(favouriteFragment)
            hide(profileFragment)
            commit()
        }


        bottomNav.setOnItemSelectedListener { item ->
            val newFragment =
                when (item.itemId) {
                    R.id.home -> homeFragment
                    R.id.schedule -> scheduleFragment
                    R.id.favourite -> favouriteFragment
                    R.id.profile -> profileFragment
                    else -> return@setOnItemSelectedListener false
                }
            if (activeFragment != newFragment) {
                supportFragmentManager.beginTransaction().apply {
                    hide(activeFragment)
                    show(newFragment)
                    commit()
                }
                activeFragment = newFragment
            }
            else{
                Toast.makeText(this,"clicked the same button!", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }
}