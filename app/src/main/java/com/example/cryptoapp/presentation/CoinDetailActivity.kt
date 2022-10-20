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

class CoinDetailActivity : AppCompatActivity(), ShareCoinDialogFragment.ShareDialogListener {

    private lateinit var viewModel : CoinViewModel
    private val binding by lazy {
        ActivityCoinDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)){
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)?: EMPTY_SYMBOL
       if (savedInstanceState == null) {
           supportFragmentManager
               .beginTransaction()
               .replace(R.id.fragment_container, CoinDetailFragment.newInstance(fromSymbol))
               .commit()
       }
    }


    companion object {
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
        TODO("Not yet implemented")
    }

}