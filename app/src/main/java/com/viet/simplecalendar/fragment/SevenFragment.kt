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
import kotlinx.android.synthetic.main.fragment_seven.view.*
import java.text.SimpleDateFormat
import java.util.*

class SevenFragment : Fragment() {
    lateinit var mainActivity: MainActivity
    val MAX_DAY = 50
    var calendar: Calendar = Calendar.getInstance()
    var dateFormat: SimpleDateFormat = SimpleDateFormat("MMMM yyyy")

    var dates: MutableList<Date> = mutableListOf()
    lateinit var calendarAdapter: CalendarAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_seven, container, false)
        mainActivity = activity as MainActivity
        calendar = mainActivity.calendar
        checkMonth(calendar, 6)

        var monthYear: String = dateFormat.format(calendar.time)
        view.tvMonthYear.text = monthYear
        dates.clear()
        var monthCalendar: Calendar = calendar.clone() as Calendar
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1)
        var firstDayOfMonth: Int =
            monthCalendar.get(Calendar.DAY_OF_WEEK) - (1 + mainActivity.index)
        monthCalendar.add(Calendar.DAY_OF_MONTH, -(firstDayOfMonth + 7))

        while (dates.size < MAX_DAY - 1) {
            dates.add(monthCalendar.time)
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        dates.add(calendar.time)

        view.rcvCalendar.layoutManager = GridLayoutManager(this.context, 7)
        calendarAdapter = CalendarAdapter(dates)
        view.rcvCalendar.adapter = calendarAdapter

        calendarAdapter.notifyDataSetChanged()

        return view
    }

}