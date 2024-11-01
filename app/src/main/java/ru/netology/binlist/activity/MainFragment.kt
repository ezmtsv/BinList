package ru.netology.binlist.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.netology.binlist.adapter.BinListAdapter
import ru.netology.binlist.databinding.FragmentMainBinding
import ru.netology.binlist.viewmodel.BinReqViewModel

@AndroidEntryPoint
@OptIn(ExperimentalCoroutinesApi::class)

class MainFragment : Fragment() {

    private val viewModel: BinReqViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(layoutInflater)

        val adapter = BinListAdapter(binding)

        viewModel.dataState.observe(viewLifecycleOwner){state->
            if (state.error) {
//                Snackbar.make(binding?.root!!, R.string.error_loading, Snackbar.LENGTH_LONG)
//                    .setAction(R.string.retry_loading) { viewModel.loadUsers() }
//                    .show()
                println(state.error)
            }

            if (state.error403) {
//                showBar("Доступ закрыт, выполните вход")
                println("Доступ закрыт, выполните вход")
            }
        }

        viewModel.binReq.observe(viewLifecycleOwner){
            adapter.bind(it)
        }

        viewModel.listBins.observe(viewLifecycleOwner){
            println(it)
        }


        return binding.root
    }

}