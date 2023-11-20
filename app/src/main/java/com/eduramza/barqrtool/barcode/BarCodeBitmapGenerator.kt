package com.eduramza.barqrtool.ui

import android.app.Activity
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.annotation.ColorInt
import com.eduramza.barqrtool.R
import com.google.zxing.BarcodeFormat
import com.google.zxing.oned.Code128Writer

private fun createBarcodeBitmap(
    barcodeValue: String,
    @ColorInt barcodeColor: Int,
    @ColorInt backgroundColor: Int,
    widthPixels: Int,
    heightPixels: Int
): Bitmap {
    val bitMatrix = Code128Writer().encode(
        barcodeValue,
        BarcodeFormat.CODE_128,
        widthPixels,
        heightPixels
    )

    val pixels = IntArray(bitMatrix.width * bitMatrix.height)
    for (y in 0 until bitMatrix.height) {
        val offset = y * bitMatrix.width
        for (x in 0 until bitMatrix.width) {
            pixels[offset + x] =
                if (bitMatrix.get(x, y)) barcodeColor else backgroundColor
        }
    }

    val bitmap = Bitmap.createBitmap(
        bitMatrix.width,
        bitMatrix.height,
        Bitmap.Config.ARGB_8888
    )
    bitmap.setPixels(
        pixels,
        0,
        bitMatrix.width,
        0,
        0,
        bitMatrix.width,
        bitMatrix.height
    )
    return bitmap
}

internal fun displayBarCode(imageView: ImageView, activity: Activity, value: String){
    val widthPixels = activity.resources.getDimensionPixelSize(R.dimen.width_barcode)
    val heightPixels = activity.resources.getDimensionPixelSize(R.dimen.height_barcode)

    imageView.setImageBitmap(
        createBarcodeBitmap(
            barcodeValue = value,
            barcodeColor = activity.getColor(R.color.black),
            backgroundColor = activity.getColor(R.color.white),
            widthPixels = widthPixels,
            heightPixels = heightPixels
        )
    )
}