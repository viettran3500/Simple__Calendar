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
        when (position) {
            0 -> {
                return list[0]
            }
            1 -> {
                return list[1]
            }
            2 -> {
                return list[2]
            }
            3 -> {
                return list[3]
            }
            4 -> {
                return list[4]
            }
            5 -> {
                return list[5]
            }
            6 -> {
                return list[6]
            }
            7 -> {
                return list[7]
            }
            8 -> {
                return list[8]
            }
            9 -> {
                return list[9]
            }
            10 -> {
                return list[10]
            }
            11 -> {
                return list[11]
            }
        }
        return list[0]
    }

}