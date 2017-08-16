package com.stewhouse.kotlinexample

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

fun AppCompatActivity.extensionFunOutsideClass() { // For this function is not a part of the class which is overrided, so it won't use hidden caching.
    Log.e("MainActivity", "AppCompatActivity.b()")
}

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    fun MainActivity.extensionFunInsideClass() {  // The hidden caching of this function will be generated inside MainActivity's code.
        Log.e("MainActivity", "MainActivity.a()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


        // Kotlin Android Extensions.
        extensionFunInsideClass() // Call MainActivity.a()
        extensionFunOutsideClass() // Call AppCompatActivity.b()

        // Null safety check and using let.
        var str: String? = "abc"
//        var str : String = "abc"                          // It occurs an warning because str is always not null, so it doesn't need to pass through if phrase.
//        var str : String                                  // It occurs an error forces to initialize the string.
        var strLen = if (str != null) str.length else -1    // Kotlin smart casts str : String? to str : String.

        Log.e("MainActivity", "str: $str, strLen: $strLen")

        var kotlinClass: FirstKotlinClass = FirstKotlinClass()
        kotlinClass.safeCalls()
        kotlinClass.elvisOperator(str)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
