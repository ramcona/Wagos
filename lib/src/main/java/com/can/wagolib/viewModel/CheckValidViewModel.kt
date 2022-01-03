package com.can.wagolib.viewModel

import android.content.Context
import com.can.wagolib.R
import com.can.wagolib.callback.WagoCheckValidPhoneCallback
import com.can.wagolib.network.response.WagooResponse
import com.can.wagolib.repositories.AuthRepo
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class CheckValidViewModel(var context: Context, var callback: WagoCheckValidPhoneCallback) {

    private lateinit var subscription: Disposable
    private var repo = AuthRepo()

    fun hit(phone:String, apptoken:String) {
        subscription = repo.checkValidNumber(phone, apptoken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { callback.onLoading(true) }
            .doOnTerminate { callback.onLoading(false) }
            .subscribe(
                { result ->
                    callback.resultValidNumberCheck(result)
                },
                { error ->
                    if (error is HttpException && (error.code() == 400)){

                        val responseBody = error.response()?.errorBody()?.string()
                        val response =  Gson().fromJson(responseBody, WagooResponse::class.java)

                        callback.onError(response.meta.message)

                    }
                    else if (error is HttpException && (error.code() == 500 || error.code() == 502)){
                        callback.onError(context.getString(R.string.teks_terjadi_kesalahan_code_500))
                    }
                    else{
                        try{
                            when {
                                error.message!!.contains("Failed to connect", true) -> {
                                    callback.onError(context.getString(R.string.teks_tidak_dapat_tehubung_ke_server))
                                }
                                error.message.toString().contains("4") -> {
                                    callback.onError(context.getString(R.string.teks_terjadi_kesalahan_code_400))
                                }
                                error.message.toString().contains("5") -> {
                                    callback.onError(context.getString(R.string.teks_terjadi_kesalahan_code_500))
                                }
                                else -> {
                                    callback.onError(error.message ?: "")
                                }
                            }
                        }catch (e:KotlinNullPointerException){
                            callback.onError(e.message ?: "")
                        }
                    }
                }

            )
    }

}