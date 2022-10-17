package com.example.cryptoapp.presentation

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ActivityCoinDetailBinding
import com.squareup.picasso.Picasso
import java.math.BigDecimal

class CoinDetailActivity : AppCompatActivity(), ShareCoinDialogFragment.ShareDialogListener {

    private lateinit var viewModel : CoinViewModel
    private val binding by lazy {
        ActivityCoinDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        buttonAppBarSettings()

        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)){
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)?: EMPTY_SYMBOL
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        fromSymbol.let {
            viewModel.getDetailInfo(fromSymbol).observe(this, Observer {
                with(binding) {
                    tvPriceCoin.text = it.price
                    tvMinPrice.text = it.lowday.toString().take(10)
                    tvMaxPrice.text = it.highday.toString().take(10)
                    tvLastMarket.text = it.lastmarket
                    tvUpdate.text = it.lastupdate
                    tvFromSymbol.text = it.fromsymbol
                    tvToSymbol.text = it.tosymbol
                    Picasso.get().load(it.imageurl).into(ivLogoCoinDetailActivity)

                    tvMaxDifference.text =
                        (if (it.highday!= null && it.lowday != null) {
                            BigDecimal.valueOf(it.highday).subtract(BigDecimal.valueOf(it.lowday))
                                .toString().take(10)
                        } else {
                            DASH
                        })

                    val priceDouble = it.price?.toDouble()
                    val hundredPer = 100.0
                    tvDayDifferenceInPercents.text =
                        if (priceDouble != null && it.openday!= null) {
                            ((BigDecimal.valueOf(priceDouble)
                                .subtract(BigDecimal.valueOf(it.openday)) * BigDecimal.valueOf(hundredPer)) / BigDecimal.valueOf(
                                it.openday
                            )).toString().take(7)
                        } else {
                            DASH
                        }
                }

            })
        }
    }


    companion object {
        private const val DASH = "-"
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val MODE_DETAIL_ITEM = "mode_detail_item"
        private const val EMPTY_SYMBOL = ""

       private const val EXTRA_FROM_SYMBOL = "fSym"
        fun newIntent (context: Context, fromSymbol: String) : Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_DETAIL_ITEM)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                val putExtra = putExtra(
                    Intent.EXTRA_SUBJECT,
                    "Криптовалюта: "+ binding.tvFromSymbol.text
                )
                putExtra(
                    Intent.EXTRA_TEXT,
                    "Цена: " + binding.tvPriceCoin.text + "$, "
                            + "Минимум за день: " + binding.tvMinPrice.text + "$, "
                            + "Максимум за день: " + binding.tvMaxPrice.text + "$, "
                            + "Макс скачек за день: " + binding.tvMaxDifference.text + "$."
                )
                type = "text/plain"
            }
            try {
                startActivity(Intent.createChooser(sendIntent, null))
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this,"Невозможно поделиться данными. Нет соответствующих приложений",Toast.LENGTH_SHORT).show()
            }
        }

    fun buttonAppBarSettings() {
        with(binding.bottomAppBar) {
            replaceMenu(R.menu.bottom_app_bar_detail_coin_activity)
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationContentDescription(R.string.save)
            setNavigationOnClickListener {
                finish()
            }
            setOnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.buttonShareCoinDetailActivity -> {
                        val shareCoinDialogFragment = ShareCoinDialogFragment()
                        val manager = supportFragmentManager
                        shareCoinDialogFragment.show(manager,"shareDialog")
                        true
                    }
                    else -> false
                }
            }
        }

    }

}