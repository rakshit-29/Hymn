package com.example.hymn.userinterface.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hymn.R
import com.example.hymn.firestore.FirestoreClass
import com.example.hymn.models.Product
import com.example.hymn.userinterface.activities.AddProductActivity
import com.example.hymn.userinterface.activities.SettingsActivity
import com.example.hymn.userinterface.adapters.MyProductsListAdapter
import kotlinx.android.synthetic.main.fragment_product.*

class ProductFragment : BaseFragment() {

    //private lateinit var homeViewModel: HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    /**
     * A function that will call the delete function of FirestoreClass that will delete the product added by the user.
     *
     * @param productID To specify which product need to be deleted.
     */
    fun deleteProduct(productID: String) {

        // Here we will call the delete function.

       showAlertDialogToDeleteProduct(productID)
    }

    /**
     * A function to show the alert dialog for the confirmation of delete product from cloud firestore.
     */
    private fun showAlertDialogToDeleteProduct(productID: String) {

        val builder = AlertDialog.Builder(requireActivity())
        //set title for alert dialog
        builder.setTitle(resources.getString(R.string.delete_dialog_title))
        //set message for alert dialog
        builder.setMessage(resources.getString(R.string.delete_dialog_message))
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton(resources.getString(R.string.yes)) { dialogInterface, _ ->


            // Show the progress dialog.
            showProgressDialog(resources.getString(R.string.please_wait))

            // Call the function of Firestore class.
            FirestoreClass().deleteProduct(this@ProductFragment, productID)


            dialogInterface.dismiss()
        }

        //performing negative action
        builder.setNegativeButton(resources.getString(R.string.no)) { dialogInterface, _ ->

            dialogInterface.dismiss()
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    /**
     * A function to notify the success result of product deleted from cloud firestore.
     */
    fun productDeleteSuccess() {

        // Hide the progress dialog
        hideProgressDialog()

        Toast.makeText(
            requireActivity(),
            resources.getString(R.string.product_delete_success_message),
            Toast.LENGTH_SHORT
        ).show()

        // Get the latest products list from cloud firestore.
        getProductListFromFireStore()
    }

    /**
     * A function to get the successful product list from cloud firestore.
     *
     * @param productsList will receive the product list from cloud firestore.
     */
    fun successProductsListFromFireStore(productsList: ArrayList<Product>) {

        // Hide Progress dialog.
        hideProgressDialog()

        if(productsList.size>0){
            rv_my_product_items.visibility=View.VISIBLE
            tv_no_products_found.visibility=View.GONE

            rv_my_product_items.layoutManager=LinearLayoutManager(activity)

            rv_my_product_items.setHasFixedSize(true)

            val adapterProducts=MyProductsListAdapter(requireActivity(), productsList, this)
            rv_my_product_items.adapter=adapterProducts
        }else{
            rv_my_product_items.visibility=View.GONE
            tv_no_products_found.visibility=View.VISIBLE
        }

    }
    private fun getProductListFromFireStore() {
        // Show the progress dialog.
        showProgressDialog(resources.getString(R.string.please_wait))

        // Call the function of Firestore class.
        FirestoreClass().getProductsList(this@ProductFragment)
    }

    override fun onResume() {
        super.onResume()
        getProductListFromFireStore()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //homeViewModel =
            //ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_product, container, false)


        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_product_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when(id){
            R.id.action_add_product->{
                startActivity(Intent(activity, AddProductActivity::class.java))

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }



}