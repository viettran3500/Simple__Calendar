package com.viet.simplecalendar.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.viet.simplecalendar.fragment.*

class ViewPagerAdapter(fm: FragmentManager, behavior: Int, listFragment: MutableList<Fragment>) :
    FragmentStatePagerAdapter(fm, behavior) {
    var list = listFragment
    override fun getCount(): Int {
        return 12
    }

    override fun getItem(position: Int): Fragment {
        return list[position]
    }

}