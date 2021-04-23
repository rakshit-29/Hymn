package com.example.hymn.userinterface.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.hymn.R
import com.example.hymn.userinterface.activities.AddProductActivity

class OrdersFragment : Fragment() {

    //private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //notificationsViewModel =
            //ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_orders, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        //notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = "Notification fragment"

        return root
    }

}