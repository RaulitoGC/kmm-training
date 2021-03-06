package com.rguzmanc.friends.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rguzmanc.friends.android.list.FriendListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(android.R.id.content, FriendListFragment.newInstance(), FriendListFragment.TAG)
                .addToBackStack(FriendListFragment.TAG)
                .commit()
        }
    }
}
