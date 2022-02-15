package com.example.ssookssook

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ssookssook.SwipeFragment.TestFragment
import com.example.ssookssook.SwipeFragment.TestFragment2
import com.example.ssookssook.SwipeFragment.TestFragment3

class ViewPagerAdapter(private val manager: FragmentActivity, private val day:Int) : FragmentStateAdapter(manager) {
    private val fragmentList = ArrayList<Fragment>();
    private val fragmentManager = manager.supportFragmentManager;

    override fun createFragment(position: Int): Fragment {

        //day는 존재하는 날의 수
        //
        val fragment = TestFragment();
        val bundle= Bundle();
        bundle.putInt("day", day+position)
        //Log.i("position",position.toString())
        fragment.arguments = bundle;



        return fragment;
    }
    
    override fun getItemCount(): Int {
        return day;
    }
}