package ru.netology.binlist.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.netology.binlist.R
import ru.netology.binlist.databinding.FragmentHistoryBinding
import ru.netology.binlist.databinding.FragmentMainBinding

class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHistoryBinding.inflate(layoutInflater)
        return binding.root
    }
}