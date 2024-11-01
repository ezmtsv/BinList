package ru.netology.binlist.adapter

import ru.netology.binlist.databinding.FragmentMainBinding
import ru.netology.binlist.dto.BinRequest
import javax.inject.Inject

class BinListAdapter @Inject constructor(
    private val binding: FragmentMainBinding
) {
    fun bind(bin: BinRequest) {
        with(binding) {
            country.isEnabled = false
            bankName.isEnabled = false
            code.isEnabled = false
            flag.isEnabled = false
            currency.isEnabled = false
            currencyCode.isEnabled = false
            bankLink.isEnabled = false
            bankPhone.isEnabled = false
            cardScheme.isEnabled = false
            cardType.isEnabled = false
            cvvlen.isEnabled = false
            category.isEnabled = false
            country.editText?.setText(bin.data?.country?.name)
            code.editText?.setText(bin.data?.country?.code)
            flag.editText?.setText(bin.data?.country?.flag)
            currency.editText?.setText(bin.data?.country?.currency)
            currencyCode.editText?.setText(bin.data?.country?.currencyCode)
            bankName.editText?.setText(bin.data?.bank?.name)
            bankLink.editText?.setText(bin.data?.bank?.website)
            bankPhone.editText?.setText(bin.data?.bank?.phone)
            cardScheme.editText?.setText(bin.data?.card?.scheme)
            cardType.editText?.setText(bin.data?.card?.type)
            cvvlen.editText?.setText(bin.data?.card!!.cvvlen)
            category.editText?.setText(bin.data?.card?.category)
        }
    }
}