package com.example.hymn.userinterface.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hymn.R
import com.example.hymn.firestore.FirestoreClass
import com.example.hymn.models.Product
import com.example.hymn.userinterface.activities.CartListActivity
import com.example.hymn.userinterface.activities.ProductDetailsActivity
import com.example.hymn.userinterface.activities.SettingsActivity
import com.example.hymn.userinterface.adapters.DashboardItemsListAdapter
import com.example.hymn.utils.Constants
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : BaseFragment() {

    //private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //if we want to use the options menu in fragment we need to add it
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        getDashboardItemsList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //dashboardViewModel =
            //ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)


        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.dashboard_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when (id) {

            R.id.action_settings -> {


                startActivity(Intent(activity, SettingsActivity::class.java))
                return true
            }
            R.id.action_cart->{
                startActivity(Intent(activity, CartListActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * A function to get the success result of the dashboard items from cloud firestore.
     *
     * @param dashboardItemsList
     */
    fun successDashboardItemsList(dashboardItemsList: ArrayList<Product>) {

        // Hide the progress dialog.
        hideProgressDialog()

        if (dashboardItemsList.size > 0) {

            rv_dashboard_items.visibility = View.VISIBLE
            tv_no_dashboard_items_found.visibility = View.GONE

            rv_dashboard_items.layoutManager = GridLayoutManager(activity, 2)
            rv_dashboard_items.setHasFixedSize(true)

            val adapter = DashboardItemsListAdapter(requireActivity(), dashboardItemsList)
            rv_dashboard_items.adapter = adapter

            adapter.setOnClickListener(object: DashboardItemsListAdapter.OnClickListener{
                override fun onClick(position: Int, product: Product) {
                    val intent = Intent(context, ProductDetailsActivity::class.java)
                    intent.putExtra(Constants.EXTRA_PRODUCT_ID, product.product_id)
                    intent.putExtra(Constants.EXTRA_PRODUCT_OWNER_ID, product.user_id)
                    startActivity(intent)
                }

            })
        } else {
            rv_dashboard_items.visibility = View.GONE
            tv_no_dashboard_items_found.visibility = View.VISIBLE
        }
    }
    /**
     * A function to get the dashboard items list from cloud firestore.
     */
    private fun getDashboardItemsList() {
        // Show the progress dialog.
        showProgressDialog(resources.getString(R.string.please_wait))

        FirestoreClass().getDashboardItemsList(this@DashboardFragment)
    }

}