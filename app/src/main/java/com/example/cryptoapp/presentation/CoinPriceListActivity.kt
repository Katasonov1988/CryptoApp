package com.example.cryptoapp.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.example.cryptoapp.domain.CoinInfo
import com.example.cryptoapp.presentation.adapters.CoinInfoAdapter

// https://min-api.cryptocompare.com/documentation?key=Price&cat=multipleSymbolsFullPriceEndpoint
class CoinPriceListActivity : AppCompatActivity() {

private val binding by lazy {
    ActivityCoinPriceListBinding.inflate(layoutInflater)
}
    private lateinit var viewModel: CoinViewModel
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val adapter = CoinInfoAdapter(this)
//        rvCoinPriceList.layoutManager = GridLayoutManager(this, 3)
//
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinInfo) {
                val intent =
                    CoinDetailActivity.newIntent(
                        this@CoinPriceListActivity,
                        coinPriceInfo.fromsymbol
                    )
                startActivity(intent)
            }

        }
       binding.rvCoinPriceList.adapter = adapter
        binding.rvCoinPriceList.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                searchView?.clearFocus()
                return false
            }
        })


        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this) {
            adapter.coinPriceInfoList = it
        }
//
//            viewModel.getDateFromQuery("btc").observe(this, Observer {
//                adapter.coinPriceInfoList = it
//
//            })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu.findItem(R.id.search_icon)
        searchItem.expandActionView()
        val searchView = searchItem.actionView as SearchView
        this.searchView = searchView
        with(searchView) {
            maxWidth = Int.MAX_VALUE
            queryHint = "Поиск по названию монеты"
            requestFocusFromTouch()
            setIconifiedByDefault(false)
            isSubmitButtonEnabled()
            onActionViewExpanded()
            clearFocus()

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }

            })

        }
        return super.onCreateOptionsMenu(menu)

    }

    override fun onResume() {
        super.onResume()
        searchView?.clearFocus()
        searchView?.setQuery("", false)
    }
}


