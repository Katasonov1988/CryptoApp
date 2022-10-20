package com.example.cryptoapp.presentation

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.icu.lang.UProperty.DASH
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ActivityCoinDetailBinding
import com.example.cryptoapp.databinding.FragmentCoinDetailBinding
import com.squareup.picasso.Picasso
import java.lang.RuntimeException
import java.math.BigDecimal

class CoinDetailFragment : Fragment() {

    private lateinit var viewModel : CoinViewModel

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
                    tvMinPrice.text = it.lowday.toString().take(10)
                    tvMaxPrice.text = it.highday.toString().take(10)
                    tvLastMarket.text = it.lastmarket
                    tvUpdate.text = it.lastupdate
                    tvFromSymbol.text = it.fromsymbol
                    tvToSymbol.text = it.tosymbol
                    Picasso.get().load(it.imageurl).into(ivLogoCoin)

                    tvMaxDifference.text =
                        (if (it.highday != null && it.lowday != null) {
                            BigDecimal.valueOf(it.highday).subtract(BigDecimal.valueOf(it.lowday))
                                .toString().take(10)
                        } else {
                            DASH
                        })

                    val priceDouble = it.price?.toDouble()
                    tvDayDifferenceInPercents.text =
                        if (priceDouble != null && it.openday!= null) {
                            ((BigDecimal.valueOf(priceDouble)
                                .subtract(BigDecimal.valueOf(it.openday)) * BigDecimal.valueOf(
                                HUNDRED_PERCENT)) / BigDecimal.valueOf(
                                it.openday
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

        fun newInstance (fromSymbol: String) : Fragment {
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