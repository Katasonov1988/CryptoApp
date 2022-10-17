package com.example.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ItemCoinIfoBinding
import com.example.cryptoapp.domain.CoinInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coin_ifo.view.*

class CoinInfoAdapter(private val context: Context) :
    RecyclerView.Adapter<CoinInfoViewHolder>() {

    var coinPriceInfoList: List<CoinInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = ItemCoinIfoBinding.inflate(
            LayoutInflater.from(parent.context)
            ,parent,
            false
        )
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinPriceInfoList[position]
        with(holder.binding) {
            with(coin) {
                val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
                tvSymbols.text = fromsymbol
                tvPrice.text = price?.let {
                    String.format(symbolsTemplate, it.take(10), tosymbol) }
                root.setOnClickListener {
                    onCoinClickListener?.onCoinClick(this)
                }

                tvLastUpdate.text = String.format(
                    lastUpdateTemplate, lastupdate)
                Picasso.get().load(imageurl).into(root.ivLogoCoin)
            }

        }





    }

    override fun getItemCount() = coinPriceInfoList.size



    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo: CoinInfo)
    }

}