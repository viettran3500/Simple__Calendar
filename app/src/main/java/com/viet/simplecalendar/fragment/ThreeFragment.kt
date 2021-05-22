package com.viet.simplecalendar.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.viet.simplecalendar.adapter.CalendarAdapter
import com.viet.simplecalendar.activity.MainActivity
import com.viet.simplecalendar.R
import com.viet.simplecalendar.utils.checkMonth
import kotlinx.android.synthetic.main.fragment_three.view.*
import java.text.SimpleDateFormat
import java.util.*

class ThreeFragment : Fragment() {
    lateinit var mainActivity: MainActivity
    val MAX_DAY = 50
    var calendar: Calendar = Calendar.getInstance()
    var dateFormat: SimpleDateFormat = SimpleDateFormat("MMMM yyyy")

    var dates: MutableList<Date> = mutableListOf()
    var calendarAdapter: CalendarAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_three, container, false)

        mainActivity = activity as MainActivity
        calendar = mainActivity.calendar
        checkMonth(calendar, 2)

        val monthYear: String = dateFormat.format(calendar.time)
        view.tvMonthYear.text = monthYear
        dates.clear()
        val monthCalendar: Calendar = calendar.clone() as Calendar
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1)
        val firstDayOfMonth: Int =
            monthCalendar.get(Calendar.DAY_OF_WEEK) - mainActivity.index

        monthCalendar.add(Calendar.DAY_OF_MONTH, -firstDayOfMonth)
        if (monthCalendar.get(Calendar.DAY_OF_MONTH) > 7)
            monthCalendar.add(Calendar.DAY_OF_MONTH, -7)
        else
            monthCalendar.add(Calendar.DAY_OF_MONTH, -14)

        while (dates.size < MAX_DAY - 1) {
            dates.add(monthCalendar.time)
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        dates.add(calendar.time)

        view.rcvCalendar.layoutManager = GridLayoutManager(this.context, 7)
        calendarAdapter = CalendarAdapter(dates)
        view.rcvCalendar.adapter = calendarAdapter

        calendarAdapter!!.notifyDataSetChanged()

        return view
    }

}