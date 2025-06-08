package com.terabyte.realmdatabaseexample.activity

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.terabyte.realmdatabaseexample.R
import com.terabyte.realmdatabaseexample.databinding.ActivityMainBinding
import com.terabyte.realmdatabaseexample.fragment.AdoptedPetsFragment
import com.terabyte.realmdatabaseexample.fragment.OwnersFragment
import com.terabyte.realmdatabaseexample.fragment.PetsToAdoptFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureInsets()
        configureBottomNavView()
    }

    private fun configureInsets() {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun configureBottomNavView() {
        setCurrentFragment(PetsToAdoptFragment())

        binding.bottomNavMain.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.menuItemPetsToAdopt -> {
                    setCurrentFragment(PetsToAdoptFragment())
                }
                R.id.menuItemAdoptedPets -> {
                    setCurrentFragment(AdoptedPetsFragment())
                }
                R.id.menuItemOwners -> {
                    setCurrentFragment(OwnersFragment())
                }
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameMainFragments, fragment)
            commit()
        }
    }
}