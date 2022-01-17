package com.rguzmanc.friends.android.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rguzmanc.friends.android.R
import com.rguzmanc.friends.android.util.consumeOnce

class FriendListFragment : Fragment() {

    companion object {
        const val TAG = "FriendListFragment"

        fun newInstance(): FriendListFragment = FriendListFragment()
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private lateinit var friendListViewModel: FriendListViewModel
    override fun onAttach(context: Context) {
        super.onAttach(context)
        friendListViewModel =
            ViewModelProvider(this, FriendListViewModelFactory())[FriendListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_friend_list, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        progressBar = view.findViewById(R.id.progress)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = FriendAdapter()

        friendListViewModel.friends.observe(viewLifecycleOwner, { friends ->
            friends.consumeOnce {
                (recyclerView.adapter as FriendAdapter).submitList(it)
            }
        })

        friendListViewModel.viewState.observe(viewLifecycleOwner, { viewState ->
            viewState.consumeOnce {
                when (it) {
                    is FriendListViewModel.ViewState.Loading -> progressBar.isVisible =
                        it.showLoading
                    is FriendListViewModel.ViewState.Error -> Toast.makeText(
                        requireContext(),
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
}