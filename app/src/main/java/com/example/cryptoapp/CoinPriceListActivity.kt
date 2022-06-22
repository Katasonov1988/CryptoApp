package com.example.cryptoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.api.ApiFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
// https://min-api.cryptocompare.com/documentation?key=Price&cat=multipleSymbolsFullPriceEndpoint
class MainActivity : AppCompatActivity() {

//    private val compositeDisposable = CompositeDisposable()

    private lateinit var viewModel : CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]


        viewModel.priceList.observe(this, Observer {
            Log.d("TEST_OF", "Success in activity: $it")
        })

        viewModel.getDetailInfo("BTC").observe(this, Observer {
            Log.d("TEST_OF", "Success in activity: $it")
        })

//    val disposable =
//        ApiFactory.apiService.getTopCoinsInfo()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                Log.d("DATA", it.toString())
//            },{
//                Log.d("DATA", it.message.toString())
//            })
//        compositeDisposable.add(disposable)
        }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        compositeDisposable.dispose()
//    }
    }
