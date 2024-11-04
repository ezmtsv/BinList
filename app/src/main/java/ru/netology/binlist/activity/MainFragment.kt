package ru.netology.binlist.activity

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.netology.binlist.R
import ru.netology.binlist.activity.MainActivity.Companion.binArg
import ru.netology.binlist.adapter.BinCardAdapter
import ru.netology.binlist.databinding.FragmentMainBinding
import ru.netology.binlist.dto.BinRequest
import ru.netology.binlist.error.UnknownErrors
import ru.netology.binlist.viewmodel.BinReqViewModel

@AndroidEntryPoint
@OptIn(ExperimentalCoroutinesApi::class)

class MainFragment : Fragment() {
    private var binding: FragmentMainBinding? = null
    private val viewModel: BinReqViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        binding?.bottomNavigationBin?.selectedItemId = R.id.mainFragment

        fun showToast(txt: String) {
            Toast.makeText(
                context,
                txt, Toast.LENGTH_LONG
            )
                .show()
        }

        val adapter = BinCardAdapter(binding!!)

        val binArg = arguments?.binArg ?: BinRequest(0)
        viewModel.setBin(binArg)

        viewModel.dataState.observe(viewLifecycleOwner) { state ->
            if (state.error) {
                showToast("Что-то пошло не так!")
            }

            if (state.error403) {
                showToast("Отказано в доступе!")
            }

            if (state.error404) {
                showToast("По вашему BIN ничего не найдено!")
            }
        }

        viewModel.binReq.observe(viewLifecycleOwner) {
            adapter.bind(it)
        }

        viewModel.listBins.observe(viewLifecycleOwner) {
//            println(it)
        }

        binding?.bottomNavigationBin?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_main -> {
                    findNavController().navigate(R.id.searchFragment)
                    true
                }

                R.id.menu_history -> {
                    findNavController().navigate(R.id.historyFragment)
                    true
                }

                else -> false

            }

        }
        return binding!!.root
    }

    private var curFrag: CurrentFragment? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            curFrag = context as CurrentFragment
        } catch (e: ClassCastException) {
            throw UnknownErrors
        }
    }

    override fun onResume() {
        curFrag?.fragAttach("MAINFRAG RESUME")
        super.onResume()
    }

    fun loadBin(bin: Long) {
        viewModel.loadReq(bin)
    }

}