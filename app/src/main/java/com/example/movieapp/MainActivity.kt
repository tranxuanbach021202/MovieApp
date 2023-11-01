package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.movieapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        navController = findNavController(R.id.container_fragment)

        binding.navBottom.setupWithNavController(navController)


        navController.addOnDestinationChangedListener{_, destination, _ ->
            if(destination.id == R.id.movie_detail_fragment) {
                binding.navBottom.visibility = View.GONE
            } else {
                binding.navBottom.visibility = View.VISIBLE
            }
        }
    }

}