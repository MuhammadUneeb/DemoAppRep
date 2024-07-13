package com.example.demoapp.utils

import android.content.Context
import android.widget.Toast
import java.util.Calendar

fun showToast(message:String?,context: Context){
    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
}
fun greetUser(): String {
    val calendar = Calendar.getInstance()
    val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
    return when (hourOfDay) {
        in 0..11 -> "Good Morning"
        in 12..16 -> "Good Afternoon"
        in 17..18 -> "Good Evening"
        else -> "Good Night"
    }
}