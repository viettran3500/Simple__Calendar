package com.viet.simplecalendar.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.viet.simplecalendar.R
import com.viet.simplecalendar.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var previousState: Int = 0
    var currentState: Int = 0
    var currentPosition = 0
    var calendar: Calendar = Calendar.getInstance()
    lateinit var adapter: ViewPagerAdapter
    var list: MutableList<String> = mutableListOf()
    var index = 0

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
        var sAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list)
        spinner.adapter = sAdapter

        adapter = ViewPagerAdapter(
            supportFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        viewPager.adapter = adapter
        currentPosition = calendar.get(Calendar.MONTH)
        viewPager.currentItem = currentPosition

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var i = viewPager.currentItem
                when (p2) {
                    0 -> index = 0
                    1 -> index = 1
                    2 -> index = 2
                    3 -> index = 3
                    4 -> index = 4
                    5 -> index = 5
                    6 -> index = 6
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
                if (currentPosition < position) {
                    if (currentPosition == 0 && position == 11) {
                        Log.e("aaa", "Down")
                    } else {
                        Log.e("aaa", "Up")
                    }
                } else {
                    if (currentPosition == 11 && position == 0) {
                        Log.e("aaa", "Up")
                    } else {
                        Log.e("aaa", "Down")
                    }
                }
                currentPosition = position
            }

            override fun onPageScrollStateChanged(state: Int) {
                var currentPage = viewPager.currentItem
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

}
