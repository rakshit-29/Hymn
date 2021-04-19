package com.example.hymn.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.hymn.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.dialog_progress.*

//To make it inheritable by all activities we need to make the class open
open class BaseActivity : AppCompatActivity() {

        private lateinit var mProgressDialog: Dialog

        /**
         * A function to show the success and error messages in snack bar component.
         */
        fun showErrorSnackBar(message: String, errorMessage: Boolean) {
            val snackBar =
                Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
            val snackBarView = snackBar.view

            //Color of snackBar set to red for error
            if (errorMessage) {
                snackBarView.setBackgroundColor(
                    ContextCompat.getColor(
                        this@BaseActivity,
                        R.color.colorSnackBarError
                    )
                )
            }
            //Color of snackBar set to red for error
            else{
                snackBarView.setBackgroundColor(
                    ContextCompat.getColor(
                        this@BaseActivity,
                        R.color.colorSnackBarSuccess
                    )
                )
            }
            snackBar.show()
        }

    /**
     * Function for showing progress dialog with text "Please wait..."
     */
    fun showProgressDialog(text: String) {
        mProgressDialog = Dialog(this)

        /*Set the screen content from a layout resource.
        The resource will be inflated, adding all top-level views to the screen.*/
        mProgressDialog.setContentView(R.layout.dialog_progress)

        mProgressDialog.tv_progress_text.text = text

        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(false)

        //Start the dialog and display it on screen.
        mProgressDialog.show()
    }
    /**
     * Function for dismissing dialog box
     */
    fun hideProgressDialog(){
        mProgressDialog.dismiss()
    }
}
