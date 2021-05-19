package com.viet.simplecalendar.adapter

import android.graphics.Color
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.viet.simplecalendar.R
import java.util.*

class CalendarAdapter(private var dayList: MutableList<Date>) :
    RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {

    var i = 0
    var p = 0
    val nowDate = Calendar.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_calendar, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position > 6) {
            val dateCalendar: Calendar = Calendar.getInstance()
            val monthDate: Date = dayList[position]
            dateCalendar.time = monthDate
            val displayMonth = dateCalendar.get(Calendar.MONTH) + 1
            val displayYear = dateCalendar.get(Calendar.YEAR)
            holder.tvDay.text = dateCalendar.get(Calendar.DAY_OF_MONTH).toString()

            val date: Calendar = Calendar.getInstance()
            date.time = dayList[dayList.size - 1]
            val currentMonth = date.get(Calendar.MONTH) + 1
            val currentYear = date.get(Calendar.YEAR)

            if (displayMonth == currentMonth && displayYear == currentYear) {
                holder.tvDay.setTextColor(Color.argb(255, 0, 0, 0))
            } else {
                holder.tvDay.setTextColor(Color.argb(255, 194, 194, 194))
            }
            if (dateCalendar.get(Calendar.DATE) == nowDate.get(Calendar.DATE) &&
                dateCalendar.get(Calendar.MONTH) == nowDate.get(Calendar.MONTH) &&
                dateCalendar.get(Calendar.YEAR) == nowDate.get(Calendar.YEAR)
            )
                holder.tvDay.setTextColor(Color.argb(255, 255, 0, 0))
        } else {
            val dateCalendar: Calendar = Calendar.getInstance()
            val monthDate: Date = dayList[position]
            dateCalendar.time = monthDate
            when (dateCalendar.get(Calendar.DAY_OF_WEEK)) {
                Calendar.MONDAY -> holder.tvDay.text = "Mon"
                Calendar.TUESDAY -> holder.tvDay.text = "Tue"
                Calendar.WEDNESDAY -> holder.tvDay.text = "Wed"
                Calendar.THURSDAY -> holder.tvDay.text = "Thu"
                Calendar.FRIDAY -> holder.tvDay.text = "Fri"
                Calendar.SATURDAY -> holder.tvDay.text = "Sat"
                Calendar.SUNDAY -> holder.tvDay.text = "Sun"
            }
            holder.tvDay.setTextColor(Color.argb(255, 0, 0, 0))
        }

        holder.tvDay.setOnClickListener {
            if (position > 6) {
                val handler = Handler()
                val runnable = Runnable {
                    kotlin.run {
                        if (i == 1)
                            holder.tvDay.setBackgroundResource(R.drawable.bg_item_calendar_click)
                        i = 0
                    }
                }
                i++
                if (i == 1) {
                    if (p != position)
                        notifyItemChanged(p)
                    p = position
                    handler.postDelayed(runnable, 200)
                } else if (i == 2) {
                    if (p == position) {
                        i = 0
                        handler.removeCallbacks(runnable)
                        holder.tvDay.setBackgroundResource(R.drawable.bg_item_calendar_doubleclick)
                    }
                }

            }
        }
        holder.tvDay.setBackgroundResource(R.drawable.bg_item_calendar)
    }


    override fun getItemCount(): Int {
        return dayList.size - 1
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvDay: TextView = view.findViewById(R.id.tvDay)
    }

}