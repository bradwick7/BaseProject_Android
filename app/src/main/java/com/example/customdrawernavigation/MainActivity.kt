package com.example.customdrawernavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.customdrawernavigation.animation_fragment.AnimationFragment
import com.example.customdrawernavigation.home_fragment.HomeFragment
import com.example.customdrawernavigation.timer_fragment.TimerFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var drawer_layout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(HomeFragment(), R.string.home_title.toString())

        drawer_layout = findViewById(R.id.drawer_layout)
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            R.string.drawer_open,
            R.string.drawer_closed
        )
        drawer_layout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView = findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener {
            handleNavigationItemSelected(it)
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun replaceFragment(fragment: Fragment, title: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content_container, fragment)
        fragmentTransaction.commit()
        setTitle(title)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }

    private fun handleNavigationItemSelected(item: MenuItem) {
        when (item.itemId) {
            R.id.menu_home -> {
                replaceFragment(HomeFragment(), item.title.toString())
            }
            R.id.menu_animation -> {
                replaceFragment(AnimationFragment(), item.title.toString())
            }
            R.id.menu_timer -> {
                replaceFragment(TimerFragment(), item.title.toString())
            }
        }
    }
}