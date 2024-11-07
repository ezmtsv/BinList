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
        //       this@MainActivity.onBackPressedDispatcher.addCallback(this@MainActivity, callback)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

//        findNavController(R.id.nav_host_fragment).navigate(
//            R.id.mainFragment
//        )
        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.setDisplayHomeAsUpEnabled(destination.id != R.id.searchFragment)
        }
        invalidateOptionsMenu()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    //    override fun fragDetach(nameFrag: String) {
//        println("detach $nameFrag")
//    }
//
    override fun fragAttach(nameFrag: String) {
        println("attach $nameFrag")
        if (numberBin != 0L) {
            println("numberBin $numberBin")
            val currentFragment =
                supportFragmentManager.currentNavigationFragment // переменная currentNavigationFragment дает нам текущий объект франмента, и затем мы можеи вызвать функцию из этого фрагмента из активити
            if (currentFragment is MainFragment) currentFragment.loadBin(numberBin)
        }
        numberBin = 0
    }


    private val FragmentManager.currentNavigationFragment: Fragment?   // получаем переменную currentNavigationFragment
        get() = primaryNavigationFragment?.childFragmentManager?.fragments?.first()

    override fun startSearch(num: Long) {
        numberBin = num
//        println("currentFragment $currentFragment")
    }

//    private val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
//        override fun handleOnBackPressed() {
//            when (val currentFragment = supportFragmentManager.currentNavigationFragment) {
//                is HistoryFragment -> {
//                    println("currentFragment HISTORY")
//                }
//
//                is MainFragment -> {
//                    println("currentFragment MAIN")
//                }
//
//                is SearchFragment -> {
//                    finish()
//                }
////                else->{println("currentFragment $currentFragment")}
//            }
//            findNavController(R.id.nav_host_fragment).navigateUp()
//        }
//    }
}