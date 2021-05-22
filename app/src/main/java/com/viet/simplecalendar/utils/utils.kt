package com.viet.simplecalendar.utils

import java.util.*

var dayClick: Date? = null
var numClick = 0

fun checkMonth(calendar: Calendar, month: Int) {
    while (calendar.get(Calendar.MONTH) != month) {
        if (calendar.get(Calendar.MONTH) > month)
            calendar.add(Calendar.MONTH, -1)
        else
            calendar.add(Calendar.MONTH, 1)
    }
}
