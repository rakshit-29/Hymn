package com.example.hymn.utils

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore

object Constants {
    /*For getting users collection from the firestore,
    defining constant makes less errors than
    writing a string "users" in the firestore class to get the collection
    */
    const val USERS: String="users"
    const val HYMN_PREFERENCES: String = "HymnPrefs"
    const val LOGGED_IN_USERNAME: String = "logged_in_username"

    const val EXTRA_USER_DETAILS: String="extra_user_details"

    const val READ_STORAGE_PERMISSION_CODE= 2
    const val PICK_IMAGE_REQUEST_CODE=1

    const val MALE: String="Male"
    const val FEMALE:String="Female"

    const val MOBILE: String= "mobile"
    const val GENDER: String="gender"

    fun showImageChooser(activity: Activity){
        //An intent for launching the image selection of phone storage
        val galleryIntent= Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        //Launches the image selection of phone storage using constant code.
        activity.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)

    }

}