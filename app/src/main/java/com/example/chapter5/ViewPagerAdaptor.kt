package com.example.chapter5

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdaptor(
    fa: FragmentActivity,
    listener: (CharSequence) -> Unit
) : FragmentStateAdapter(fa) {
    private val dataFragments = mutableListOf(
        SecondFragment.newInstance("Bermain suit melawan sesama pemain.","1", listener),
        SecondFragment.newInstance("Bermain suit melawan komputer.", "2", listener),
        SecondFragment.newInstance("Tuliskan namamu di bawah","3", listener)
    )

    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment = dataFragments[position]
}