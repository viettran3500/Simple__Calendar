package com.viet.simplecalendar

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.viet.simplecalendar.fragment.*
import java.util.*

class ViewPagerAdapter(fm: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fm, behavior) {

    override fun getCount(): Int {
        return 12
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0->{
                var oneFragment : OneFragment = OneFragment()
                return oneFragment
            }
            1->{
                var twoFragment = TwoFragment()
                return TwoFragment()
            }
            2->{
                return ThreeFragment()
            }
            3->{
                return FourFragment()
            }
            4->{
                return FiveFragment()
            }
            5->{
                return SixFragment()
            }
            6->{
                return SevenFragment()
            }
            7->{
                return EightFragment()
            }
            8->{
                return NineFragment()
            }
            9->{
                return TenFragment()
            }
            10->{
                return ElevenFragment()
            }
            11->{
                var twelveFragment = TwelveFragment()
                return twelveFragment
            }
        }
        return OneFragment()
    }

}