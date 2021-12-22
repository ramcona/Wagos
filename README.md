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
