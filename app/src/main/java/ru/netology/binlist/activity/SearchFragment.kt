package ru.netology.binlist.activity

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import ru.netology.binlist.R
import ru.netology.binlist.activity.MainActivity.Companion.longArg
import ru.netology.binlist.databinding.FragmentSearchBinding
import ru.netology.binlist.error.UnknownErrors


class SearchFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSearchBinding.inflate(layoutInflater)

        fun showToast(txt: String) {
            Toast.makeText(
                context,
                txt, Toast.LENGTH_LONG
            )
                .show()
        }

        binding.apply {
            btnSearch.setOnClickListener {
                if (
                    search.editText?.text?.isEmpty() == true
                ) {
                    showToast("Поле ввода должно быть заполнено!")
                } else {
                    try {
                        val numBin = search.editText?.text.toString().toLong()
                        if (numBin != 0L) {
                            findNavController().navigate(
                                R.id.mainFragment,
                                Bundle().apply {
                                    longArg = numBin
                                }
                            )
                            curFrag?.startSearch(numBin)
                        } else showToast("Некорректный ввод!")
                    } catch (e: Exception) {
                        showToast("Некорректный ввод, вводите только цифры!")
                    }

                }
            }
            btnHistory.setOnClickListener {
                findNavController().navigate(R.id.historyFragment)
            }
        }
        return binding.root
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

//    override fun onDetach() {
//        super.onDetach()
//        curFrag?.fragDetach("Search")
//        curFrag = null
//    }
//
//    override fun onStart() {
//        super.onStart()
//        curFrag?.fragAttach("Search")
//
//    }

}