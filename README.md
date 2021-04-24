# Hymn- An app for audiophiles

## _An E-Commerce music shopping application developed for Android platform_

Hymn is a cloud-enabled, mobile-ready android application which is developed in Kotlin language

The application uses Google Firebase Firestore to store a NoSQL database.

- Clean User Interface
- Smooth transitions between activities
- Realtime Database on Google cloud. which can be accessed in real-time!

## Logo
<img src="https://user-images.githubusercontent.com/61807065/115805521-2333c180-a3b3-11eb-9d8f-40b0c39af3a4.png" width="15%"></img> 

## Configuration
Glide Loader for easy image loading and uploading: com.github.bumptech.glide:glide:4.11.0

Firebase for Kotin: 'com.google.firebase:firebase-auth-ktx'

```sh
implementation platform('com.google.firebase:firebase-bom:26.8.0')
implementation 'com.google.firebase:firebase-analytics-ktx'
implementation 'com.google.firebase:firebase-firestore-ktx'
implementation 'com.google.firebase:firebase-storage-ktx'
```
Android Version: 4.4 KitKat and higher

buildToolsVersion "30.0.2"

sourceCompatibility JavaVersion.VERSION_1_8

targetCompatibility JavaVersion.VERSION_1_8

dependency'com.google.gms:google-services:4.3.5'

buildscript ext.kotlin_version = "1.3.72"

## Installation
- Install .apk file
- On Android Studio you can directly run the app in your AVD or android phone which will basically install .apk on your device.
- Sync gradle files

## Copyright
> Created by Rakshit Saxena
> Copyright (c) 2021 . All rights reserved.
> Rakshit Saxena.
> rakshit-29 on GitHub.

## Manifest
- <img src="https://user-images.githubusercontent.com/61807065/115963481-7873ee00-a4ed-11eb-8143-e9d929364658.JPG" width="15%"></img> 
- <img src="https://user-images.githubusercontent.com/61807065/115963531-b8d36c00-a4ed-11eb-95aa-292781a25c1a.JPG" width="15%"></img> 





My app basically revolves around 
- fragments
- activities
- adapters.

Login activity page for Hymn:

<img src="https://user-images.githubusercontent.com/61807065/115805854-c7b60380-a3b3-11eb-9bb3-75c605ed8819.JPG" width="15%"></img> 

Register activity for new users:

<img src="https://user-images.githubusercontent.com/61807065/115942447-5a1fdb00-a478-11eb-816d-e37bc96b1bba.JPG" width="15%"></img> 

Forgot Password activity for people who forgot their password, google firebase will send an email to the entered email id for password reset

<img src="https://user-images.githubusercontent.com/61807065/115942641-3c9f4100-a479-11eb-8b27-7c8a08fa39bc.JPG" width="15%"></img> 

Dashboard activity
- Fragments for different activities
- Has Dashboard, Product, Orders and Sold Products Fragments
- Menu and Navigation within the app

<img src="https://user-images.githubusercontent.com/61807065/115962033-54151300-a4e7-11eb-91c4-025c9b9afe19.JPG" width="15%"></img> 

A new user who wants to
- Register
- Login
- Add products to cart
- Add address
- Checkout
- Place Order

<img src="https://user-images.githubusercontent.com/61807065/115962313-b3275780-a4e8-11eb-9254-f9765b159104.gif" width="15%"></img> 

As a seller, if I want to see my products and who has placed an order.
- Sold products 

<img src="https://user-images.githubusercontent.com/61807065/115962852-4792b980-a4eb-11eb-9312-55b18e38760f.gif" width="15%"></img> 

Now as a seller I want to add products.

<img src="https://user-images.githubusercontent.com/61807065/115963082-3302f100-a4ec-11eb-9b19-6ae1ee39039f.gif" width="15%"></img> 

I have added a swipe to edit and swipe to delete utility to my address list recycler view.
- https://medium.com/@kitek/recyclerview-swipe-to-delete-easier-than-you-thought-cff67ff5e5f6

<img src="https://user-images.githubusercontent.com/61807065/115963286-a7d62b00-a4ec-11eb-865c-2bbd162c9e81.gif" width="15%"></img> 

As we know this is a real-time cloud database which is stored on my Hymn app firebase and firestore console.












