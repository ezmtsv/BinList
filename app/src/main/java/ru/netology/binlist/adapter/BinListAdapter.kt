package ru.netology.binlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.binlist.databinding.CardBinBinding
import ru.netology.binlist.dto.BinRequest
import ru.netology.binlist.util.AndroidUtils.getTime

interface ListenerSelectBin {
    fun viewBin(bin: BinRequest)
}

class BinListAdapter(
    private val listener: ListenerSelectBin
) : ListAdapter<BinRequest, BinViewHolder>(BinDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinViewHolder {
        val binding = CardBinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BinViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: BinViewHolder, position: Int) {
        val bin = getItem(position)
        holder.bind(bin)
    }
}

class BinViewHolder(
    private val binding: CardBinBinding,
    private val listener: ListenerSelectBin
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(bin: BinRequest) {
        binding.apply {
            val num = "${bin.bin?.number}"
            "${num.substring(0, 4)} ${num.substring(4, 6)}** **** ****".also { number.text = it }
            scheme.text = bin.bin?.scheme
            dateReq.text = getTime(bin.ip?.currentTime)
            bankName.text = bin.bin?.issuer?.name
            flag.text = bin.bin?.country?.flag

            root.setOnClickListener {
                listener.viewBin(bin)
            }
        }
    }
}

class BinDiffCallBack : DiffUtil.ItemCallback<BinRequest>() {
    override fun areItemsTheSame(oldItem: BinRequest, newItem: BinRequest): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: BinRequest, newItem: BinRequest): Boolean =
        oldItem == newItem

}


