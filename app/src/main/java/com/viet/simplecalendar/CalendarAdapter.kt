package com.viet.simplecalendar

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class CalendarAdapter(var dayList: MutableList<Date>) :
    RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_calendar, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var dateCalendar: Calendar =Calendar.getInstance()
        val monthDate: Date = dayList[position]
        dateCalendar.time = monthDate
        var displayMonth = dateCalendar.get(Calendar.MONTH) + 1
        var displayYear = dateCalendar.get(Calendar.YEAR)
        holder.tvDay.text = dateCalendar.get(Calendar.DAY_OF_MONTH).toString()

        val date : Calendar = Calendar.getInstance()
        date.time = dayList[42]
        val currentMonth = date.get(Calendar.MONTH) + 1
        val currentYear = date.get(Calendar.YEAR)


        if (displayMonth == currentMonth && displayYear == currentYear) {
            holder.itemView.setBackgroundColor(Color.parseColor("#D81B60"))
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#cccccc"))
        }
    }

    override fun getItemCount(): Int {
        return dayList.size - 1
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvDay: TextView = view.findViewById(R.id.tvDay)
    }

}