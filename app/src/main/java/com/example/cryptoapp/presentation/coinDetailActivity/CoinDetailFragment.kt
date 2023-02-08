package com.example.cryptoapp.presentation.coinDetailActivity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.databinding.FragmentCoinDetailBinding
import com.example.cryptoapp.presentation.coinPriceListActivity.CoinViewModel
import com.squareup.picasso.Picasso
import java.math.BigDecimal

class CoinDetailFragment : Fragment() {

    private lateinit var viewModel: CoinViewModel

    private var _binding: FragmentCoinDetailBinding? = null
    private val binding: FragmentCoinDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentCoinDetailBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fromSymbol = getSymbol()
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailInfo(fromSymbol).observe(viewLifecycleOwner, Observer {
            with(binding) {
                tvPriceCoin.text = it.price
                tvMinPrice.text = it.lowDay.toString().take(10)
                tvMaxPrice.text = it.highDay.toString().take(10)
                tvLastMarket.text = it.lastMarket
                tvUpdate.text = it.lastUpdate
                tvFromSymbol.text = it.fromSymbol
                tvToSymbol.text = it.toSymbol


                Picasso.get().load(it.imageUrl).into(ivLogoCoin)
                Log.v("Main Activity", it.imageUrl.toString())

                tvMaxDifference.text =
                    (if (it.highDay != null && it.lowDay != null) {
                        BigDecimal.valueOf(it.highDay).subtract(BigDecimal.valueOf(it.lowDay))
                            .toString().take(10)
                    } else {
                        DASH
                    })

                val priceDouble = it.price?.toDouble()
                tvDayDifferenceInPercents.text =
                    if (priceDouble != null && it.openDay != null) {
                        ((BigDecimal.valueOf(priceDouble)
                            .subtract(BigDecimal.valueOf(it.openDay)) * BigDecimal.valueOf(
                            HUNDRED_PERCENT
                        )) / BigDecimal.valueOf(
                            it.openDay
                        )).toString().take(7)
                    } else {
                        DASH
                    }
            }

        })
    }

    private fun getSymbol(): String {
        return requireArguments().getString(EXTRA_FROM_SYMBOL, EMPTY_SYMBOL)
    }

    companion object {
        private const val HUNDRED_PERCENT = 100.0
        private const val DASH = "-"
        private const val EMPTY_SYMBOL = ""
        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newInstance(fromSymbol: String): Fragment {
            return CoinDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_FROM_SYMBOL, fromSymbol)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}