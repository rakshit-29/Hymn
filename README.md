# Hymn- An app for audiophiles

## _An E-Commerce music shopping application developed for Android platform_

### Hymn is a cloud-enabled, mobile-ready android application which is developed in Kotlin language

The application uses Google Firebase Firestore to store a NoSQL database.

- Clean User Interface
- Smooth transitions between activities
- Realtime Database on Google cloud, which can be accessed in real-time!

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

## Contact Information
> rakshit-29 on GitHub: https://github.com/rakshit-29
> Email: rakshit290900@gmail.com
> LinkedIn: https://www.linkedin.com/in/rakshit-saxena-5122a11a6/

## Manifest
- <img src="https://user-images.githubusercontent.com/61807065/115963481-7873ee00-a4ed-11eb-8143-e9d929364658.JPG" width="15%"></img> 
- <img src="https://user-images.githubusercontent.com/61807065/115963531-b8d36c00-a4ed-11eb-95aa-292781a25c1a.JPG" width="15%"></img> 

My models are data classes for all the Product Details, User Details, Cart, Sold product details, Addresses.

The User Interface has 3 main package components:
- Activities
- Adapters
- Fragments

An Adapter is a bridge between UI component and data source that helps us to fill data in UI component. It holds the data and send the data to an Adapter view then view can takes the data from the adapter view and shows the data on different views like as ListView, GridView, **RecyclerView**.
Using adapters for different activities what I do is get user, order, product, address details hashmaps list and create view holders for fragments. Binds items to ArrayList.

onViewHolder for MyOrdersListAdapter
```sh
if (holder is MyViewHolder) {

            GlideLoader(context).loadProductPicture(
                model.image,
                holder.itemView.iv_item_image
            )

            holder.itemView.tv_item_name.text = model.title
            holder.itemView.tv_item_price.text = "$${model.total_amount}"

            holder.itemView.ib_delete_product.visibility = View.GONE

            holder.itemView.setOnClickListener {
                //Intent going to MyOrderDetailsActivity
                val intent= Intent(context, MyOrderDetailsActivity::class.java)
                intent.putExtra(Constants.EXTRA_MY_ORDER_DETAILS, model)
                context.startActivity(intent)
            }
        }
```

Sold product fragment function successSoldProductList with (@param ArrayList<SoldProduct>)
            
```sh
fun successSoldProductsList(soldProductsList: ArrayList<SoldProduct>) {

        // Hide Progress dialog.
        hideProgressDialog()


        if (soldProductsList.size > 0) {
            rv_sold_product_items.visibility = View.VISIBLE
            tv_no_sold_products_found.visibility = View.GONE

            rv_sold_product_items.layoutManager = LinearLayoutManager(activity)
            rv_sold_product_items.setHasFixedSize(true)

            val soldProductsListAdapter =
                SoldProductListAdapter(requireActivity(), soldProductsList)
            rv_sold_product_items.adapter = soldProductsListAdapter
        } else {
            rv_sold_product_items.visibility = View.GONE
            tv_no_sold_products_found.visibility = View.VISIBLE
        }

    }
```

Login Activity:
This is the activity where user can login with their information.
Has intents to go to forgot password activity, register user activity, dashboard activity.
And if it is a new user, it directly goes to Complete Profile first then to the dashboard.

Login registered user

```sh
private fun logInRegisteredUser(){
        if(validateLoginDetails()){
            //Show Progress dialog
            showProgressDialog(resources.getString(R.string.please_wait))

            //Get the text from editText and trim the spaces
            val email: String = et_email.text.toString().trim{it <= ' ' }
            val password: String= et_password.text.toString().trim{it <= ' ' }

            //Log-In using FirebaseAuth
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task->

                    if(task.isSuccessful){
                        FirestoreClass().getUserDetails(this@LoginActivity)

                    }else {
                        hideProgressDialog()
                        showErrorSnackBar(task.exception!!.message.toString(), true)
                    }
                }
        }

    }
```


Register Activity:
Registers new users and updates the information on our cloud firestore database.

```sh
private fun registerUser() {

        // Check with validate function if the entries are valid or not.
        if (validateRegisterDetails()) {
            //We can pass our own String parameter to the function below to display whatever we want
            //But for now we are displaying "Please Wait..."
            showProgressDialog(resources.getString(R.string.please_wait))

            val email: String = et_email.text.toString().trim { it <= ' ' }
            val password: String = et_password.text.toString().trim { it <= ' ' }

            // Create an instance and create a register a user with email and password.
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    OnCompleteListener<AuthResult> { task ->


                        // If the registration is successfully done
                        if (task.isSuccessful) {

                            // Firebase registered user
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            val user = User(
                                    firebaseUser.uid,
                                    et_first_name.text.toString().trim { it <= ' ' },
                                    et_last_name.text.toString().trim { it <= ' ' },
                                    et_email.text.toString().trim { it <= ' ' }
                            )

                            FirestoreClass().registerUser(this@RegisterActivity, user)

                            //Sign out from the register then go back to the login activity
                            //FirebaseAuth.getInstance().signOut()
                            //finish()
                        } else {
                            hideProgressDialog()
                            // If the registering is not successful then show error message.
                            showErrorSnackBar(task.exception!!.message.toString(), true)
                        }
                    })
        }
    }
```



I have a firestore class for the whole interaction with my firebase console.

Uploading image to cloud storage
```sh
   fun uploadImageToCloudStorage(activity: Activity, imageFileURI: Uri?, imageType:String) {

        //getting the storage reference
        val sRef: StorageReference = FirebaseStorage.getInstance().reference.child(
            imageType + System.currentTimeMillis() + "."
                    + Constants.getFileExtension(
                activity,
                imageFileURI
            )
        )

        //adding the file to reference
        sRef.putFile(imageFileURI!!)
            .addOnSuccessListener { taskSnapshot ->
                // The image upload is success
                Log.e(
                    "Firebase Image URL",
                    taskSnapshot.metadata!!.reference!!.downloadUrl.toString()
                )

                // Get the downloadable url from the task snapshot
                taskSnapshot.metadata!!.reference!!.downloadUrl
                    .addOnSuccessListener { uri ->
                        Log.e("Downloadable Image URL", uri.toString())
                        when(activity){
                            is UserProfileActivity ->{
                                activity.imageUploadSuccess(uri.toString())
                            }
                            is AddProductActivity->{
                                activity.imageUploadSuccess(uri.toString())

                            }
                        }
                    }
            }
            .addOnFailureListener { exception->
                // Here call a function of base activity for transferring the result to it.
                when (activity) {
                    is UserProfileActivity -> {
                        activity.hideProgressDialog()
                    }

                    is AddProductActivity->{
                        activity.hideProgressDialog()
                    }
                }
                Log.e(
                    activity.javaClass.simpleName,
                    exception.message,
                    exception

                )

            }


    }
```

My utils are the utilities used throught my app.

My HymnTextView, HymnRadioButton, HymnEditText, HymnTextViewBold, HymnButton are the attributes with my own defined fonts that I have used, of different AppCompats for laying out the in the whole application.

HymnTextViewBold
```sh
class HymnTextViewBold(context: Context, attrs:AttributeSet): AppCompatTextView(context, attrs){
    init {
        //Call the function to apply the font to the components
        applyFont()
    }
    private fun applyFont(){
        //This is used to get the file from the assets folder and set it to the title textView
        val typeface: Typeface =
            Typeface.createFromAsset(context.assets, "Montserrat-Bold.ttf")
            setTypeface(typeface)

    }
}
```

Constants object file is for defining the collection in firestore.

## Execution GIFS and Project Screenshots

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

**We have used ScrollView for the layout because it is easier to scroll and find the information, some screens are smaller in size, also when someone is filling their information they might have to scroll as their keyboard is displayed on screen**

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

Storage

<img src="https://user-images.githubusercontent.com/61807065/115965693-4caa3580-a4f8-11eb-8eac-0266f50b45ac.JPG" width="15%"></img> 

Addresses collection

<img src="https://user-images.githubusercontent.com/61807065/115965748-972bb200-a4f8-11eb-978e-07fbacdc51aa.JPG" width="15%"></img> 

Users collection

<img src="https://user-images.githubusercontent.com/61807065/115965804-d5c16c80-a4f8-11eb-93d8-8f83ae785a65.JPG" width="15%"></img> 

Sold products collection

<img src="https://user-images.githubusercontent.com/61807065/115965844-ff7a9380-a4f8-11eb-90b8-d1e95ca9f86d.JPG" width="15%"></img> 

Orders collection

<img src="https://user-images.githubusercontent.com/61807065/115966001-9c3d3100-a4f9-11eb-9707-08fe9cad26e0.JPG" width="15%"></img> 

```sh
address
  additionalNote: "No contact delivery"

  address: "Bat Cave"

  id: "uNggedSyuJD2JUxneYrs"

  mobileNumber: "9876543210"

  name: "Bruce Wayne"

  otherDetails: ""

  type: "Home"

  user_id: "c0lvmfwuciZodlNEIyg60EWnbjy2"

  zipCode: "123 456"

id: ""

image: "https://firebasestorage.googleapis.com/v0/b/hymn-b94a6.appspot.com/o/Product_Image1619262726132.jpg?alt=media&token=42fadafa-c9af-467b-aef1-d81ccfa74c71"

items
  0
    cart_quantity: "2"

    id: "a90BM58qctbbt9605D3P"

    image: "https://firebasestorage.googleapis.com/v0/b/hymn-b94a6.appspot.com/o/Product_Image1619262726132.jpg?alt=media&token=42fadafa-c9af-467b-aef1-d81ccfa74c71"

    price: "249"

    product_id:"Xr88VzN8aFbwctieT1Da"

    product_owner_id:"3zHIB6SkmnXMZOPsGb7m2PDTScR2"

    stock_quantity: "10"

    title: "Freedrum virtual drum kit"

  user_id: "c0lvmfwuciZodlNEIyg60EWnbjy2"

  order_datetime: 1619273460457

  shipping_charge:"10.0"

  sub_total_amount: "498.0"

  title: "My order 1619273460457"

  total_amount"508.0"

  user_id: "c0lvmfwuciZodlNEIyg60EWnbjy2"
```

Users authentication details

<img src="https://user-images.githubusercontent.com/61807065/115965951-5718ff00-a4f9-11eb-8bd0-a9510c5e65bf.JPG" width="15%"></img> 











