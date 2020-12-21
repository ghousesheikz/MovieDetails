package com.sample.hiltdisampleapp.utils

import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

object Constants {

    private val ROTATION = 6000L

    const val APPID="9b8cb8c7f11c077f8c4e217974d9ee40"
    const val CITY = "Bengaluru"

    fun startAnimation(imageView : ImageView?) {
      val rotate = RotateAnimation(
            0.0f, 360.0f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        rotate.duration = ROTATION
        rotate.repeatCount = Animation.INFINITE
        imageView?.startAnimation(rotate)
    }

    fun retriveWeekDays(seconds : Long):String?{
        val sdf = SimpleDateFormat("EEEE")
        val dateFormat = Date(seconds.times(1000))
        return sdf.format(dateFormat)
    }

    fun calculateTemperature(temperature : Double):String{
        var temp = temperature
        temp -= 273.15
        return """${temp.roundToInt()}℃"""

    }
}