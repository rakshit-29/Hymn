/*
 * *
 *  Created by Rakshit Saxena
 *  Copyright (c) 2021 . All rights reserved.
 * /
 */

package com.example.hymn.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.hymn.R
import java.io.IOException

class GlideLoader(val context: Context) {
    /**
     * A function to load image from URI for the user profile picture.
     */
    fun loadUserPicture(image: Any, imageView: ImageView) {
        try {
            // Load the user image in the ImageView.
            Glide
                .with(context)
                .load(image) //image load
                .centerCrop() // Scale type of the image.
                .placeholder(R.drawable.ic_user_placeholder) // A default place holder if image is failed to load.
                .into(imageView) // the view in which the image will be loaded.
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    /**
     * A function to load image from Uri or URL for the product image.
     */
    fun loadProductPicture(image: Any, imageView: ImageView) {
        try {
            // Load the user image in the ImageView.
            Glide
                .with(context)
                .load(image) // Uri or URL of the image
                .centerCrop() // Scale type of the image.
                .into(imageView) // the view in which the image will be loaded.
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}