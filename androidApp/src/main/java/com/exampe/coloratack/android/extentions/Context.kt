package com.exampe.coloratack.android.extentions

import android.content.Context
import android.graphics.Insets
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets
import android.view.WindowMetrics
import com.exampe.coloratack.android.MainActivity
import com.exampe.coloratack.enums.Color

fun Context.dpToPx(dp: Int): Int {
    return (dp * resources.displayMetrics.density).toInt()
}

fun Context.screenDimensions(callBack: (width: Int, height: Int) -> Unit) {
    var width = -1
    var height = -1

    (this as? MainActivity)?.let { activity ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics: WindowMetrics = activity.windowManager.currentWindowMetrics
            val insets: Insets =
                windowMetrics.windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            width = windowMetrics.bounds.width() - insets.left - insets.right
            height = windowMetrics.bounds.height() - insets.top - insets.bottom
        } else {
            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            width = displayMetrics.widthPixels
            height = displayMetrics.heightPixels
        }
    }
    callBack(width, height)
}

fun Context.getMColor(color: Color) = when (color) {
    Color.WHITE -> android.graphics.Color.WHITE
    Color.BLUE -> android.graphics.Color.BLUE
    Color.RED -> android.graphics.Color.RED
    Color.GREEN -> android.graphics.Color.GREEN
    Color.YELLOW -> android.graphics.Color.YELLOW
}