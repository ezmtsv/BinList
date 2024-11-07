package ru.netology.binlist.adapter

import android.annotation.SuppressLint
import ru.netology.binlist.databinding.FragmentMainBinding
import ru.netology.binlist.dto.BinRequest
import ru.netology.binlist.util.AndroidUtils.getTime
import javax.inject.Inject

interface ListenerBinCard {
    fun openWebView(link: String)
    fun openDialer(tel: String)
//    fun openMaps(geo: String)
}

class BinCardAdapter @Inject constructor(
    private val binding: FragmentMainBinding,
    private val listenerBinCard: ListenerBinCard
) {
    @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
    fun bind(bin: BinRequest) {
        binding.apply {
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
            level.isEnabled = false
            region.isEnabled = false
            nativeLang.isEnabled = false
            currencyName.isEnabled = false
            capital.isEnabled = false
            time.isEnabled = false
            number.isEnabled = false

            bin.bin?.number?.let { number.editText?.setText("${it}****") }
            time.editText?.setText(getTime(bin.ip?.currentTime))
            country.editText?.setText(bin.bin?.country?.name)
            code.editText?.setText(bin.bin?.country?.languageCode)
            flag.editText?.setText(bin.bin?.country?.flag)
            currency.editText?.setText(bin.bin?.country?.currency)
            currencyCode.editText?.setText(bin.bin?.country?.currencySymbol)
            bankName.editText?.setText(bin.bin?.issuer?.name)
            webSait.text = bin.bin?.issuer?.website
            phone.text = "phone: ${bin.bin?.issuer?.phone}"
//            coords.text = "coordinates: ${bin.ip?.latitude}, ${bin.ip?.longitude}"
            cardScheme.editText?.setText(bin.bin?.scheme)
            cardType.editText?.setText(bin.bin?.type)
            level.editText?.setText(bin.bin?.level)
            region.editText?.setText(bin.bin?.country?.region)
            nativeLang.editText?.setText(bin.bin?.country?.native)
            currencyName.editText?.setText(bin.bin?.country?.currencyName)
            capital.editText?.setText(bin.bin?.country?.capital)

            webSait.setOnClickListener {
                listenerBinCard.openWebView(bin.bin?.issuer?.website.toString())
            }
            phone.setOnClickListener {
                bin.bin?.issuer?.phone?.let {
                    listenerBinCard.openDialer("tel:${bin.bin.issuer.phone}")
                }
            }

//            coords.setOnClickListener {
//                bin.ip?.latitude?.let {
//                    listenerBinCard.openMaps("geo:${bin.ip.latitude}, ${bin.ip.longitude}")
//                }
//            }
        }
    }
}