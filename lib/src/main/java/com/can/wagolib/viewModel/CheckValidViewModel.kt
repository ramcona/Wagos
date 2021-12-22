package com.can.wagolib.viewModel

import android.content.Context
import com.can.wagolib.R
import com.can.wagolib.callback.WagoCheckValidPhoneCallback
import com.can.wagolib.repositories.AuthRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

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

            )
    }

}