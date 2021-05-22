package com.viet.simplecalendar.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.viet.simplecalendar.R
import com.viet.simplecalendar.adapter.ViewPagerAdapter
import com.viet.simplecalendar.fragment.*
import com.viet.simplecalendar.utils.dayClick
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var listFragment: MutableList<Fragment> = mutableListOf()
    var previousState: Int = 0
    var currentState: Int = 0
    var currentPosition = 0
    var calendar: Calendar = Calendar.getInstance()
    lateinit var adapter: ViewPagerAdapter
    var list: MutableList<String> = mutableListOf()
    var index = 1

    private val oneFragment = OneFragment()
    private val twoFragment = TwoFragment()
    private val threeFragment = ThreeFragment()
    private val fourFragment = FourFragment()
    private val fiveFragment = FiveFragment()
    private val sixFragment = SixFragment()
    private val sevenFragment = SevenFragment()
    private val eightFragment = EightFragment()
    private val nineFragment = NineFragment()
    private val tenFragment = TenFragment()
    private val elevenFragment = ElevenFragment()
    private val twelveFragment = TwelveFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.add("Sun")
        list.add("Mon")
        list.add("Tue")
        list.add("Wed")
        list.add("Thu")
        list.add("Fri")
        list.add("Sat")
        val sAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list)
        spinner.adapter = sAdapter

        addFragment()

        adapter = ViewPagerAdapter(
            supportFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
            listFragment
        )
        viewPager.adapter = adapter
        currentPosition = calendar.get(Calendar.MONTH)
        viewPager.currentItem = currentPosition
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val i = viewPager.currentItem
                when (p2) {
                    0 -> index = 1
                    1 -> index = 2
                    2 -> index = 3
                    3 -> index = 4
                    4 -> index = 5
                    5 -> index = 6
                    6 -> index = 7
                }
                viewPager.adapter = adapter
                viewPager.currentItem = i
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
            }

            override fun onPageScrollStateChanged(state: Int) {
                if (dayClick != null) {
                    val date: Calendar = Calendar.getInstance()
                    date.time = dayClick
                    val month = date.get(Calendar.MONTH)
                    if (state == 1 && month == viewPager.currentItem) {
                        dataSetChangedFragment(viewPager.currentItem)
                    }
                }

                val currentPage = viewPager.currentItem
                if (currentPage == 11 || currentPage == 0) {
                    previousState = currentState
                    currentState = state
                    if (previousState == 1 && currentState == 0) {
                        if (currentPage == 0) {
                            calendar.add(Calendar.YEAR, -1)
                            viewPager.currentItem = 11
                        } else {
                            calendar.add(Calendar.YEAR, 1)
                            viewPager.currentItem = 0
                        }
                    }
                }
            }
        })
    }

    private fun addFragment() {
        listFragment.add(oneFragment)
        listFragment.add(twoFragment)
        listFragment.add(threeFragment)
        listFragment.add(fourFragment)
        listFragment.add(fiveFragment)
        listFragment.add(sixFragment)
        listFragment.add(sevenFragment)
        listFragment.add(eightFragment)
        listFragment.add(nineFragment)
        listFragment.add(tenFragment)
        listFragment.add(elevenFragment)
        listFragment.add(twelveFragment)
    }

    fun dataSetChangedFragment(i: Int) {
        when (i) {
            0 -> {
                twoFragment.calendarAdapter?.notifyDataSetChanged()
                listFragment[1] = twoFragment
            }
            1 -> {
                oneFragment.calendarAdapter?.notifyDataSetChanged()
                listFragment[0] = oneFragment
                threeFragment.calendarAdapter?.notifyDataSetChanged()
                listFragment[2] = threeFragment
            }
            2 -> {
                twoFragment.calendarAdapter?.notifyDataSetChanged()
                listFragment[1] = twoFragment
                fourFragment.calendarAdapter?.notifyDataSetChanged()
                listFragment[3] = fourFragment
            }
            3 -> {
                threeFragment.calendarAdapter?.notifyDataSetChanged()
                listFragment[2] = threeFragment
                fiveFragment.calendarAdapter?.notifyDataSetChanged()
                listFragment[4] = fiveFragment
            }
            4 -> {
                fourFragment.calendarAdapter?.notifyDataSetChanged()
                listFragment[3] = fourFragment
                sixFragment.calendarAdapter?.notifyDataSetChanged()
                listFragment[5] = sixFragment
            }
            5 -> {
                fiveFragment.calendarAdapter?.notifyDataSetChanged()
                listFragment[4] = fiveFragment
                sevenFragment.calendarAdapter?.notifyDataSetChanged()
                listFragment[6] = sevenFragment
            }
            6 -> {
                sixFragment.calendarAdapter?.notifyDataSetChanged()
                listFragment[5] = sixFragment
                eightFragment.calendarAdapter?.notifyDataSetChanged()
                listFragment[7] = eightFragment
            }
            7 -> {
                sevenFragment.calendarAdapter?.notifyDataSetChanged()
                listFragment[6] = sevenFragment
                nineFragment.calendarAdapter?.notifyDataSetChanged()
                listFragment[8] = nineFragment
            }
            8 -> {
                eightFragment.calendarAdapter?.notifyDataSetChanged()
                listFragment[7] = eightFragment
                tenFragment.calendarAdapter?.notifyDataSetChanged()
                listFragment[9] = tenFragment
            }
            9 -> {
                nineFragment.calendarAdapter?.notifyDataSetChanged()
                listFragment[8] = nineFragment
                elevenFragment.calendarAdapter?.notifyDataSetChanged()
                listFragment[10] = elevenFragment
            }
            10 -> {
                tenFragment.calendarAdapter?.notifyDataSetChanged()
                listFragment[9] = tenFragment
                twelveFragment.calendarAdapter?.notifyDataSetChanged()
                listFragment[11] = twelveFragment
            }
            11 -> {
                elevenFragment.calendarAdapter?.notifyDataSetChanged()
                listFragment[10] = elevenFragment
            }
        }

    }
}
