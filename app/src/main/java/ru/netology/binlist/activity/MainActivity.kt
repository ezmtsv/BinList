package ru.netology.binlist.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.netology.binlist.R
import ru.netology.binlist.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        findNavController(R.id.nav_host_fragment).navigate(
            R.id.mainFragment
        )
    }
}