package com.feature.listing

import java.time.Duration
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object Utils {


    fun convertReadableDate(date: String): String? {
        // Define the UTC time string and parse it
        val utcTimeStr = date
        val formatter = DateTimeFormatter.ISO_DATE_TIME
        val utcDateTime = ZonedDateTime.parse(utcTimeStr, formatter)

        // Convert to IST (Indian Standard Time, UTC +5:30)
        val istZoneId = ZoneId.of("Asia/Kolkata")
        val istDateTime = utcDateTime.withZoneSameInstant(istZoneId)

        // Get current time in IST for comparison
        val currentDateTime = ZonedDateTime.now(istZoneId)

        // Calculate time difference in minutes, hours, and days
        val minutesAgo = Duration.between(istDateTime, currentDateTime).toMinutes()
        val hoursAgo = Duration.between(istDateTime, currentDateTime).toHours()
        val daysAgo = Duration.between(istDateTime, currentDateTime).toDays()

        // Print converted IST time
        println("Converted IST Time: ${istDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss"))}")

        // Time difference output
        when {
            minutesAgo < 60 -> println("$minutesAgo minutes ago")
            hoursAgo < 24 -> println("$hoursAgo hours ago")
            else -> println("$daysAgo day(s) ago")
        }

        // Output date in DD MMM format (e.g., 02 Nov)
        return istDateTime.format(DateTimeFormatter.ofPattern("dd MMM YYYY"))
    }

}