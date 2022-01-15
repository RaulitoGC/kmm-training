package com.rguzmanc.friends.android.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rguzmanc.friends.android.R
import com.rguzmanc.friends.android.util.consumeOnce
import com.rguzmanc.friends.domain.GetFriends

class FriendListFragment: Fragment() {

    companion object{
        const val TAG = "FriendListFragment"

        fun newInstance(): FriendListFragment = FriendListFragment()
    }

    private lateinit var recyclerView : RecyclerView

    private lateinit var friendListViewModel: FriendListViewModel
    override fun onAttach(context: Context) {
        super.onAttach(context)
        friendListViewModel = ViewModelProvider(this, FriendListViewModelFactory())[FriendListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_friend_list, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = FriendAdapter()


        friendListViewModel.dataState.observe(viewLifecycleOwner, { dataState ->
            dataState.consumeOnce {
                if(it is GetFriends.DataState.Success){
                    (recyclerView.adapter as FriendAdapter).submitList(it.friends)
                } else{
                    Toast.makeText(requireContext(), "Error!", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }
}