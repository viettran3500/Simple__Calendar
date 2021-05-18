package com.viet.simplecalendar

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var previousState : Int = 0
    var currentState : Int = 0
    var currentPosition = 0
    var calendar : Calendar = Calendar.getInstance()
    lateinit var adapter : ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ViewPagerAdapter(supportFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        viewPager.adapter = adapter
        currentPosition = calendar.get(Calendar.MONTH)
        viewPager.currentItem = currentPosition

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                if(currentPosition < position){
                    if(currentPosition == 0 && position == 11){
                        Log.e("aaa","Down")
                    }else{
                        Log.e("aaa","Up")
                    }
                }else{
                    if(currentPosition == 11 && position == 0){
                        Log.e("aaa","Up")
                    }else{
                        Log.e("aaa","Down")
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
                        if(currentPage == 0){
                            calendar.add(Calendar.YEAR,-1)
                            viewPager.currentItem = 11
                        }
                        else{
                            calendar.add(Calendar.YEAR,1)
                            viewPager.currentItem = 0
                        }
                    }
                }
            }
        })
    }

}
