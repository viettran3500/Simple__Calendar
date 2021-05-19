package com.viet.simplecalendar.utils

import java.util.*

fun checkMonth(calendar: Calendar, month: Int){
    while(calendar.get(Calendar.MONTH) != month){
        if(calendar.get(Calendar.MONTH) > month)
            calendar.add(Calendar.MONTH, -1)
        else
            calendar.add(Calendar.MONTH, 1)
    }
}
