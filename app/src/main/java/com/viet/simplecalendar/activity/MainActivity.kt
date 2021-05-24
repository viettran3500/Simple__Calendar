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
import com.viet.simplecalendar.utils.lastDayClick
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
                index = p2
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
                        dataSetChangedFragment()
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

    fun dataSetChangedFragment() {
        if (dayClick != null && lastDayClick != null) {
            val date: Calendar = Calendar.getInstance()
            date.time = dayClick
            val monthClick = date.get(Calendar.MONTH)
            val lastDate: Calendar = Calendar.getInstance()
            lastDate.time = lastDayClick
            val lastMonthClick = lastDate.get(Calendar.MONTH)
            if (date.get(Calendar.YEAR) == lastDate.get(Calendar.YEAR))
                if (lastMonthClick - monthClick == 1)
                    when (monthClick) {
                        0 -> {
                            twoFragment.calendarAdapter?.notifyItemChanged(
                                twoFragment.dates.indexOf(
                                    lastDayClick
                                )
                            )
                            listFragment[1] = twoFragment
                        }
                        1 -> {
                            threeFragment.calendarAdapter?.notifyItemChanged(
                                threeFragment.dates.indexOf(
                                    lastDayClick
                                )
                            )
                            listFragment[2] = threeFragment
                        }
                        2 -> {
                            fourFragment.calendarAdapter?.notifyItemChanged(
                                fourFragment.dates.indexOf(
                                    lastDayClick
                                )
                            )
                            listFragment[3] = fourFragment
                        }
                        3 -> {
                            fiveFragment.calendarAdapter?.notifyItemChanged(
                                fiveFragment.dates.indexOf(
                                    lastDayClick
                                )
                            )
                            listFragment[4] = fiveFragment
                        }
                        4 -> {
                            sixFragment.calendarAdapter?.notifyItemChanged(
                                sixFragment.dates.indexOf(
                                    lastDayClick
                                )
                            )
                            listFragment[5] = sixFragment
                        }
                        5 -> {
                            sevenFragment.calendarAdapter?.notifyItemChanged(
                                sevenFragment.dates.indexOf(
                                    lastDayClick
                                )
                            )
                            listFragment[6] = sevenFragment
                        }
                        6 -> {
                            eightFragment.calendarAdapter?.notifyItemChanged(
                                eightFragment.dates.indexOf(
                                    lastDayClick
                                )
                            )
                            listFragment[7] = eightFragment
                        }
                        7 -> {
                            nineFragment.calendarAdapter?.notifyItemChanged(
                                nineFragment.dates.indexOf(
                                    lastDayClick
                                )
                            )
                            listFragment[8] = nineFragment
                        }
                        8 -> {
                            tenFragment.calendarAdapter?.notifyItemChanged(
                                tenFragment.dates.indexOf(
                                    lastDayClick
                                )
                            )
                            listFragment[9] = tenFragment
                        }
                        9 -> {
                            elevenFragment.calendarAdapter?.notifyItemChanged(
                                elevenFragment.dates.indexOf(
                                    lastDayClick
                                )
                            )
                            listFragment[10] = elevenFragment
                        }
                        10 -> {
                            twelveFragment.calendarAdapter?.notifyItemChanged(
                                twelveFragment.dates.indexOf(
                                    lastDayClick
                                )
                            )
                            listFragment[11] = twelveFragment
                        }
                    }
                else if (lastMonthClick - monthClick == -1)
                    when (monthClick) {
                        1 -> {
                            oneFragment.calendarAdapter?.notifyItemChanged(
                                oneFragment.dates.indexOf(
                                    lastDayClick
                                )
                            )
                            listFragment[0] = oneFragment
                        }
                        2 -> {
                            twoFragment.calendarAdapter?.notifyItemChanged(
                                twoFragment.dates.indexOf(
                                    lastDayClick
                                )
                            )
                            listFragment[1] = twoFragment
                        }
                        3 -> {
                            threeFragment.calendarAdapter?.notifyItemChanged(
                                threeFragment.dates.indexOf(
                                    lastDayClick
                                )
                            )
                            listFragment[2] = threeFragment
                        }
                        4 -> {
                            fourFragment.calendarAdapter?.notifyItemChanged(
                                fourFragment.dates.indexOf(
                                    lastDayClick
                                )
                            )
                            listFragment[3] = fourFragment
                        }
                        5 -> {
                            fiveFragment.calendarAdapter?.notifyItemChanged(
                                fiveFragment.dates.indexOf(
                                    lastDayClick
                                )
                            )
                            listFragment[4] = fiveFragment
                        }
                        6 -> {
                            sixFragment.calendarAdapter?.notifyItemChanged(
                                sixFragment.dates.indexOf(
                                    lastDayClick
                                )
                            )
                            listFragment[5] = sixFragment
                        }
                        7 -> {
                            sevenFragment.calendarAdapter?.notifyItemChanged(
                                sevenFragment.dates.indexOf(
                                    lastDayClick
                                )
                            )
                            listFragment[6] = sevenFragment
                        }
                        8 -> {
                            eightFragment.calendarAdapter?.notifyItemChanged(
                                eightFragment.dates.indexOf(
                                    lastDayClick
                                )
                            )
                            listFragment[7] = eightFragment
                        }
                        9 -> {
                            nineFragment.calendarAdapter?.notifyItemChanged(
                                nineFragment.dates.indexOf(
                                    lastDayClick
                                )
                            )
                            listFragment[8] = nineFragment
                        }
                        10 -> {
                            tenFragment.calendarAdapter?.notifyItemChanged(
                                tenFragment.dates.indexOf(
                                    lastDayClick
                                )
                            )
                            listFragment[9] = tenFragment
                        }
                        11 -> {
                            elevenFragment.calendarAdapter?.notifyItemChanged(
                                elevenFragment.dates.indexOf(
                                    lastDayClick
                                )
                            )
                            listFragment[10] = elevenFragment
                        }
                    }
        }
    }
}
