# wagos

[![](https://jitpack.io/v/ramcona/wagos.svg)](https://jitpack.io/#ramcona/wagos)

Library dibuat untuk autentifikasi whatsapp di buat  tim CAN Creative.
hanya mendukung untuk android 21+.

## Features
- Request otp code
- Resend otp code
- Verifikasi otp code
- verifikasi nomor terdaftar di aplikasi

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

[MainActivity]: <https://github.com/ramcona/wagos/blob/c18b3cf7077fd4f748f186fefd3e5b1044f7578f/app/src/main/java/com/can/wagos/MainActivity.kt>


## A. Penggunaan untuk Verifikasi

1. Tambahkan ``WagoCallback`` sebagai callback implement

contoh : 
``class MainActivity : AppCompatActivity(), WagoCallback {
}``

Kemudian panggil `class` `WagoVerification` untuk melakukan verifikasi

contoh : 
````
    /*member untuk verifikasi*/
    private lateinit var verification: WagoVerification
````

2. perkenalkan variabel ``WagoVerification`` di dalam method/void ``onCreate``

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