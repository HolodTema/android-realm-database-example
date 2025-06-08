package com.terabyte.realmdatabaseexample.activity

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.activity.enableEdgeToEdge
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.terabyte.realmdatabaseexample.R
import com.terabyte.realmdatabaseexample.databinding.ActivityMainBinding
import com.terabyte.realmdatabaseexample.fragment.AdoptedPetsFragment
import com.terabyte.realmdatabaseexample.fragment.OwnersFragment
import com.terabyte.realmdatabaseexample.fragment.PetsToAdoptFragment
import com.terabyte.realmdatabaseexample.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class]
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
        binding.bottomNavMain.setOnItemSelectedListener { menuItem ->
            viewModel.liveDataCurrentMenuItemId.value = menuItem.itemId
            true
        }

        viewModel.liveDataCurrentMenuItemId.observe(this) {
            setCurrentFragment(it)
        }
    }

    private fun setCurrentFragment(@LayoutRes menuItemId: Int) {
        val fragment = when(menuItemId) {
            R.id.menuItemPetsToAdopt -> {
                PetsToAdoptFragment()
            }
            R.id.menuItemAdoptedPets -> {
                AdoptedPetsFragment()
            }
            R.id.menuItemOwners -> {
                OwnersFragment()
            }
            else -> {
                PetsToAdoptFragment()
            }
        }


        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameMainFragments, fragment)
            commit()
        }
    }
}