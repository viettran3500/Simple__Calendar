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
import kotlinx.android.synthetic.main.fragment_five.view.*
import java.text.SimpleDateFormat
import java.util.*

class FiveFragment : Fragment() {
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
        var view: View = inflater.inflate(R.layout.fragment_five, container, false)
        mainActivity = activity as MainActivity
        calendar = mainActivity.calendar
        checkMonth(calendar, 4)

        var monthYear: String = dateFormat.format(calendar.time)
        view.tvMonthYear.text = monthYear
        dates.clear()
        var monthCalendar: Calendar = calendar.clone() as Calendar
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1)
        var firstDayOfMonth: Int =
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

        calendarAdapter = CalendarAdapter(dates)

        view.rcvCalendar.layoutManager = GridLayoutManager(this.context, 7)
        view.rcvCalendar.adapter = calendarAdapter

        calendarAdapter.notifyDataSetChanged()

        return view
    }
}