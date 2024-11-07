package ru.netology.binlist.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.netology.binlist.R
import ru.netology.binlist.databinding.ActivityMainBinding
import ru.netology.binlist.dto.BinRequest
import ru.netology.binlist.util.BinArg
import ru.netology.binlist.util.LongEditArg

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CurrentFragment {
    companion object {
        var Bundle.binArg: BinRequest? by BinArg
        var Bundle.longArg: Long by LongEditArg
        var numberBin = 0L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.setDisplayHomeAsUpEnabled(destination.id != R.id.searchFragment)
        }
        invalidateOptionsMenu()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun fragAttach(nameFrag: String) {
        println("attach $nameFrag")
        if (numberBin != 0L) {
            println("numberBin $numberBin")
            val currentFragment =
                supportFragmentManager.currentNavigationFragment
            if (currentFragment is MainFragment) currentFragment.loadBin(numberBin)
        }
        numberBin = 0
    }


    private val FragmentManager.currentNavigationFragment: Fragment?
        get() = primaryNavigationFragment?.childFragmentManager?.fragments?.first()

    override fun startSearch(num: Long) {
        numberBin = num
    }

}