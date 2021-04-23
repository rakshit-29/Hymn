package com.example.hymn.userinterface.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import com.example.hymn.R
import com.example.hymn.firestore.FirestoreClass
import com.example.hymn.models.User
import com.example.hymn.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.et_email
import kotlinx.android.synthetic.main.activity_login.et_password
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // This is used to hide the status bar and make the login screen as a full screen activity.

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        // Click event assigned to Forgot Password text.
        tv_forgot_password.setOnClickListener(this)
        // Click event assigned to Login button.
        btn_login.setOnClickListener(this)
        // Click event assigned to Register text.
        tv_register.setOnClickListener(this)

    }
    /**
     * A function to notify user that logged in success and get the user details from the FireStore database after authentication.
     */
    fun userLoggedInSuccess(user: User) {

        // Hide the progress dialog.
        hideProgressDialog()


        if (user.profileCompleted == 0) {
            // If the user profile is incomplete then launch the UserProfileActivity.
            val intent = Intent(this@LoginActivity, UserProfileActivity::class.java)
            intent.putExtra(Constants.EXTRA_USER_DETAILS, user)
            startActivity(intent)
        } else {
            // Redirect the user to Main Screen after log in.
            startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
        }

        finish()
    }
    // In Login screen the clickable components are Login Button, ForgotPassword text and Register Text.
    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                //Lambda expression for forgot password text
                R.id.tv_forgot_password -> {
                    val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
                    startActivity(intent)
                }
                //Lambda expression for login button
                R.id.btn_login -> {

                    //START
                    logInRegisteredUser()
                    // END
                }
                //Lambda expression for Register activity text
                R.id.tv_register -> {
                    // Launch the register screen when the user clicks on the text.
                    val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
    /**
     * A function to validate the login entries of a user.
     */
    private fun validateLoginDetails(): Boolean {
        return when {
            TextUtils.isEmpty(et_email.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }
            TextUtils.isEmpty(et_password.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }
            else -> {
                true
            }
        }
    }
    /**
     * A function to log in registered user.
     */
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

}