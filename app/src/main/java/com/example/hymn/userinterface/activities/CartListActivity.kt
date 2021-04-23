package com.example.hymn.userinterface.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hymn.R
import com.example.hymn.firestore.FirestoreClass
import com.example.hymn.models.CartItem
import com.example.hymn.userinterface.adapters.CartItemsListAdapter
import kotlinx.android.synthetic.main.activity_cart_list.*
import kotlinx.android.synthetic.main.activity_settings.*

class CartListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_list)
        setupActionBar()
    }

    /**
     * A function to notify the success result of the cart items list from cloud firestore.
     *
     * @param cartList
     */
    fun successCartItemsList(cartList: ArrayList<CartItem>) {

        // Hide progress dialog.
        hideProgressDialog()


        /*for (i in cartList) {

            Log.i("Cart Item Title", i.title)

        }*/

        if (cartList.size > 0) {

            rv_cart_items_list.visibility = View.VISIBLE
            ll_checkout.visibility = View.VISIBLE
            tv_no_cart_item_found.visibility = View.GONE

            rv_cart_items_list.layoutManager = LinearLayoutManager(this@CartListActivity)
            rv_cart_items_list.setHasFixedSize(true)

            val cartListAdapter = CartItemsListAdapter(this@CartListActivity, cartList)
            rv_cart_items_list.adapter = cartListAdapter

            var subTotal: Double = 0.0

            for (item in cartList) {

                val price = item.price.toDouble()
                val quantity = item.cart_quantity.toInt()

                subTotal += (price * quantity)
            }

            tv_sub_total.text = "$$subTotal"
            // Here we have kept Shipping Charge fixed = $10.
            tv_shipping_charge.text = "$10.0"

            if (subTotal > 0) {
                ll_checkout.visibility = View.VISIBLE

                val total = subTotal + 10
                tv_total_amount.text = "$$total"
            } else {
                ll_checkout.visibility = View.GONE
            }

        } else {
            rv_cart_items_list.visibility = View.GONE
            ll_checkout.visibility = View.GONE
            tv_no_cart_item_found.visibility = View.VISIBLE
        }
    }

    /**
     * A function to get the list of cart items in the activity.
     */
    private fun getCartItemsList() {

        // Show the progress dialog.
        showProgressDialog(resources.getString(R.string.please_wait))

        FirestoreClass().getCartList(this@CartListActivity)
    }

    override fun onResume() {
        super.onResume()
        getCartItemsList()
    }
    /**
     * A function for actionBar Setup.
     */
    private fun setupActionBar() {

        setSupportActionBar(toolbar_settings_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
        }

        toolbar_settings_activity.setNavigationOnClickListener { onBackPressed() }
    }

}