package com.ilosipov.machines.network

import androidx.lifecycle.MutableLiveData
import com.ilosipov.machines.URL_BASE
import com.ilosipov.machines.model.Machine
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Класс RequestLathe - выполняет запрос на получения данных
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 20.04.2020
 * @version $Id$
 */

class RequestLathe {
    private var retrofit : Retrofit

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private val latheApi : LatheAPI get() = retrofit.create(LatheAPI::class.java)

    fun getLatheMachine(callback: MutableLiveData<List<Machine>>) {
        latheApi.lathe()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<List<Machine>>() {
                override fun onSuccess(listMachine: List<Machine>) {
                    callback.postValue(listMachine)
                }

                override fun onError(error: Throwable) {
                    error.printStackTrace()

                }
            })
    }

}