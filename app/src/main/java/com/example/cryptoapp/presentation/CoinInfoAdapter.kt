package com.example.cryptoapp.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.domain.CoinInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coin_ifo.view.*

class CoinInfoAdapter(private val context: Context) :
    RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var coinPriceInfoList: List<CoinInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_coin_ifo, parent, false)
        return CoinInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinPriceInfoList[position]
        val symbolsTemplate = context.resources.getString(R.string.symbols_template)
        val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
        holder.tvSymbols.text = coin.fromsymbol
        holder.tvPrice.text =
            coin.price?.let { String.format(symbolsTemplate, it.take(10), coin.tosymbol) }
        holder.tvLastUpdate.text =
            String.format(lastUpdateTemplate, coin.lastupdate)
        Picasso.get().load(coin.imageurl).into(holder.ivLogoCoin)
        holder.itemView.setOnClickListener {
            onCoinClickListener?.onCoinClick(coin)
        }
    }

    override fun getItemCount() = coinPriceInfoList.size

    inner class CoinInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivLogoCoin = itemView.ivLogoCoinDetailActivity
        val tvSymbols = itemView.tvSymbols
        val tvPrice = itemView.tvPrice
        val tvLastUpdate = itemView.tvLastUpdate
    }

    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo: CoinInfo)
    }

}