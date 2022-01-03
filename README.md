# wagos

[![](https://jitpack.io/v/ramcona/wagos.svg)](https://jitpack.io/#ramcona/wagos)

Library dibuat untuk autentifikasi whatsapp di buat  tim CAN Creative.
hanya mendukung untuk android 21+.

## Features
- Request otp code
- Resend otp code
- Verifikasi otp code
- verifikasi nomor terdaftar di aplikasi

## Perangkat
- OS     : Android
- Min OS : 21 (Lolipop)

## Installation
 tambahkan maven jitpack pada build.gadle
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

setelah itu tambahakn dependency wago nya.
```
dependencies {
	        implementation 'com.github.ramcona:wagos:1.3.4'
	}
```

Rebuild

untuk menggunkan librarynya bisa di lihat di 
MainActivity.kt | [MainActivity.kt][MainActivity] 

[MainActivity]: <https://github.com/ramcona/wagos/blob/840ac097b286753b00df25ff4702e9515d38b498/app/src/main/java/com/can/wagos/MainActivity.kt>




## A. Penggunaan untuk Verifikasi

1. Tambahkan ``WagoCallback`` sebagai callback implement

contoh : 
````
class MainActivity : AppCompatActivity(), WagoCallback {
}
````

Kemudian tambahkan `class` `WagoVerification` untuk melakukan verifikasi sebagai varibel global

contoh : 
````
    /*member untuk verifikasi*/
    private lateinit var verification: WagoVerification
````

2. Perkenalkan variabel ``WagoVerification`` di dalam method/void ``onCreate``

contoh:
````
        verification = WagoVerification(this, this)
````

3. Kemudian tambahkan token aplikasi yang sudah anda dapatkan dari tim kami kedalam library
catatan : pada langkah ini tidak perlu di undang berkali-kali hanya cukup satu kali
   
contoh : 
````
verification.setTokenApp("xxxxxxxxxxxx")
````

4. Melakukan permintaan kode otp, pada langkah ini hanya membutuhkan satu baris.
catatan : nomor harus di awali dengan kode negara tanpa simbol (+)
   contoh : 6281239343373

````
verification.requestOTP(phone = 6281239343373)
````

Menerima response setelah melakukan permintaan kode otp, pada langkah kali ini pastikan anda sudah 
implement semua member ``WagoCallback``.

Selanjutkan pada member/void ``onSuccessRequest(token:String)`` dan simpan token itu karena akan digunakan pada langkah2 selanjutnya.

Bila gagal melakukan permintaan member/void ``onError(msg:String)`` akan terpanggil.

5. Melakukan permintaan kode otp ulang, pada langkah ini tidak membutuhkan nomor telepon lagi.
yang digunakan kali ini adalah token yang didapat dari langkah ke 4 / pada saat permintaan kode otp.
````
verification.resendCode(token = {token yang di dapat ketika request})
````

Selanjutkan member/void ``onSuccessResendCode()`` akan terpanggil yang menandakan bahwa permintaan berhasil dilakukan dan kode otp terkirim. 

Bila gagal melakukan permintaan member/void ``onError(msg:String)`` akan terpanggil.

6. Yang terakhir melakukan autentifikasi kode otp, di langkah ini yang kita perlukan hanya otp code yang di kirim ke Whatsapp pengguna dan token yang sudah kita simpan
````
/**
* verificaiton otp code
* @param token cant empty (you have token after reqest otp code )
* @param otpCode have send to whatsapp number*/

verification.verification(token = requestToken, otpCode = otpCode)
````



## A. Penggunaan untuk mengecek nomor tersedia di Whatsapp atau tidak

1. implement `class` `WagoCheckValidPhoneCallback` dan daftarkan member-member nya.

````
class MainActivity : AppCompatActivity(), WagoCheckValidPhoneCallback {
}
````

2. Kemudian tambahkan `class` `WagoCheckPhone` untuk melakukan verifikasi sebagai varibel global

3. Perkenalkan variabel ``WagoCheckPhone`` di dalam method/void ``onCreate``

contoh : 
````
checkValidPhone = WagoCheckPhone(this, this)
````

4. Kemudian langsung saja panggil method / void `check()` pada class `WagoCheckPhone`
catatan : varabel `phone` harus di isi dengan diawali kode negara dan tanpa simbol (+)

contoh : 62892362328323

````
checkValidPhone.check(phone = 62892362328323)
````

Setelah melakukan permintaan check nomor telepon apakah tersedia di whatsapp method / void `resultValidNumberCheck()` terpangil.
``response.data.code`` : 1  = nomor tersedia / terdaftar di whatsapp

``response.data.code`` : 0  = nomor tidak terdaftar di whatsapp

Bila terjadi kendala ketika melakukan permintaan check nomor whatsapp method / void `onError` akan terpanggil


untuk penggunaan lengkapnya bisa di lihat di
MainActivity.kt | [MainActivity.kt][MainActivity]

[MainActivity]: <https://github.com/ramcona/wagos/blob/840ac097b286753b00df25ff4702e9515d38b498/app/src/main/java/com/can/wagos/MainActivity.kt>
