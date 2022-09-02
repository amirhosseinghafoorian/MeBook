package com.example.mebook.ui.util

import android.content.Intent
import android.net.Uri
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

fun buildUrlIntent(url: String) = Intent(Intent.ACTION_VIEW, Uri.parse(url))

fun timestampToDate(time: Long): String {
    val timeStamp = Timestamp(time)
    val date = Date(timeStamp.time)

    return SimpleDateFormat("MM/dd/yyyy").format(date)
}