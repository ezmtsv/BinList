package ru.netology.binlist.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.netology.binlist.R
import ru.netology.binlist.activity.MainActivity.Companion.binArg
import ru.netology.binlist.adapter.BinListAdapter
import ru.netology.binlist.adapter.ListenerSelectBin
import ru.netology.binlist.databinding.FragmentHistoryBinding
import ru.netology.binlist.dto.BinRequest
import ru.netology.binlist.viewmodel.BinReqViewModel

@AndroidEntryPoint
@OptIn(ExperimentalCoroutinesApi::class)

class HistoryFragment : Fragment() {
    private var binding: FragmentHistoryBinding? = null
    private val viewModel: BinReqViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(layoutInflater)
        binding?.bottomNavigationBin?.selectedItemId = R.id.historyFragment

        val adapter = BinListAdapter(object : ListenerSelectBin {
            override fun viewBin(bin: BinRequest) {
                findNavController().navigate(
                    R.id.mainFragment,
                    Bundle().apply {
                        binArg = bin
                    },

                    )
            }
        })
        binding?.list?.adapter = adapter
        viewModel.listBins.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }

        binding?.bottomNavigationBin?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_main -> {
                    findNavController().navigate(R.id.searchFragment)
                    true
                }

                R.id.menu_history -> {
                    true
                }

                else -> false

            }

        }
        return binding?.root!!
    }

    override fun onResume() {
        binding?.bottomNavigationBin?.selectedItemId = R.id.menu_history
        super.onResume()
    }

//    private var curFrag: CurrentFragment? = null
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        try {
//            curFrag = context as CurrentFragment
//        } catch (e: ClassCastException) {
//            throw UnknownErrors
//        }
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        curFrag?.fragDetach("history")
//        curFrag = null
//    }
//
//    override fun onStart() {
//        super.onStart()
//        curFrag?.fragAttach("history")
//
//    }
}