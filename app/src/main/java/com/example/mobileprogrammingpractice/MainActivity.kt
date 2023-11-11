package com.example.mobileprogrammingpractice

import CardViewAdapter
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.view.*

data class Item(val name: String, val activityClass: Class<*>, val imageResId: Int)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Action Bar Slider
        val btnMenu: ImageButton = findViewById(R.id.btnMenu)
        btnMenu.setOnClickListener {
            openDrawer()
        }

        // Card(Recycler View)
        val itemList = arrayListOf(
            Item("About Me", AboutMeActivity::class.java, R.drawable.about_me),
            Item("Experience", ExperienceActivity::class.java, R.drawable.experience),
            Item("Project", ProjectActivity::class.java, R.drawable.project),
            Item("Prize", PrizeActivity::class.java, R.drawable.prize)
        )

        val manager1 = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapter1 = CardViewAdapter(itemList)

        recyclerHorizon.apply {
            adapter = adapter1
            layoutManager = manager1
        }

        adapter1.setOnItemClickListener { position ->
            val intent = Intent(this, itemList[position].activityClass)
            startActivity(intent)
        }

        // Navigation Drawer
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.navigationview)

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_phone -> {
                    showToast("U want to call Kangmin?")
                    dialPhoneNumber("01067250209")
                }
                R.id.menu_email -> {
                    showToast("U want to email Kangmin?")
                    composeEmail("rkdals0203@gmail.com")
                }
                R.id.menu_github -> {
                    showToast("U want to check github of Kangmin?")
                    openGitHubPage("https://github.com/rkdals0203")
                }
            }

            drawer.closeDrawer(GravityCompat.START)
            true
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun openDrawer() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.openDrawer(GravityCompat.START)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.drawer_menu, menu)
        Log.d("Click", "Inflated")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("Click", "Phone Item Clicked")
        when (item.itemId) {
            R.id.menu_phone -> {
                Log.d("MenuClick", "Phone Item Clicked")
                dialPhoneNumber("01067250209")
                return true
            }
            R.id.menu_email -> {
                composeEmail("rkdals0203@gmail.com")
                return true
            }
            R.id.menu_github -> {
                openGitHubPage("https://github.com/rkdals0203")
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun dialPhoneNumber(phoneNumber: String) {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:$phoneNumber")
        startActivity(dialIntent)
    }

    private fun composeEmail(email: String) {
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto:$email")
        startActivity(emailIntent)
    }

    private fun openGitHubPage(gitHubUrl: String) {
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(gitHubUrl))
        startActivity(webIntent)
    }
}
