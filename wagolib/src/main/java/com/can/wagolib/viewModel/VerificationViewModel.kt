package com.can.wagolib.viewModel

import android.content.Context
import com.can.wagolib.R
import com.can.wagolib.callback.WagoCallback
import com.can.wagolib.repositories.AuthRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class VerificationViewModel(var context: Context, var callback: WagoCallback) {

    private lateinit var subscription: Disposable
    private var repo = AuthRepo()

    fun requestOTP(phone:String, apptoken:String) {
        subscription = repo.requestOTP(phone, apptoken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { callback.onLoading(true) }
            .doOnTerminate { callback.onLoading(false) }
            .subscribe(
                { result ->
                    if (!result.error){
                        callback.onSuccessRequest(result.token)
                    }else{
                        callback.onError(result.message)
                    }
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

    fun resendCode(token:String, apptoken:String) {
        subscription = repo.resend(token, apptoken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { callback.onLoading(true) }
            .doOnTerminate { callback.onLoading(false) }
            .subscribe(
                { result ->
                    if (!result.error){
                        callback.onSuccessResendCode()
                    }else{
                        callback.onError(result.message)
                    }
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

    fun verification(otpcode:String, token:String, apptoken:String) {
        subscription = repo.verification(token, otpcode, apptoken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { callback.onLoading(true) }
            .doOnTerminate { callback.onLoading(false) }
            .subscribe(
                { result ->
                    if (!result.error){
                        callback.onSuccessVerification(result)
                    }else{
                        callback.onError(result.message)
                    }
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