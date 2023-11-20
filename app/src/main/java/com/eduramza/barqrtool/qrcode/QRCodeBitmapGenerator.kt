package com.eduramza.barqrtool.qrcode

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.eduramza.barqrtool.R
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import java.util.EnumMap

private fun qrCodeBitmapGenerator(
    activity: Activity,
    data: String,
    widthPixels: Int,
    heightPixels: Int
): Bitmap? {
    val bitMatrix: BitMatrix = try {
        val hints = EnumMap<EncodeHintType, Any>(EncodeHintType::class.java)
        hints[EncodeHintType.CHARACTER_SET] = "UTF-8"
        MultiFormatWriter().encode(
            data,
            BarcodeFormat.QR_CODE,
            widthPixels,
            heightPixels,
            hints
        )
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }

    val qrCodeWidth = bitMatrix.width
    val qrCodeHeight = bitMatrix.height
    val pixels = IntArray(qrCodeWidth * qrCodeHeight)

    for (y in 0 until qrCodeHeight) {
        val offset = y * qrCodeWidth
        for (x in 0 until qrCodeWidth) {
            pixels[offset + x] = if (bitMatrix[x, y]) {
                activity.resources.getColor(R.color.black) // QR code color
            } else {
                activity.resources.getColor(R.color.white) // Background color
            }
        }
    }

    val bitmap = Bitmap.createBitmap(qrCodeWidth, qrCodeHeight, Bitmap.Config.RGB_565)
    bitmap.setPixels(pixels, 0, qrCodeWidth, 0, 0, qrCodeWidth, qrCodeHeight)

    // Customize the QR code bitmap (e.g., add a logo)
//    val logoBitmap = BitmapFactory.decodeResource(activity.resources, R.mipmap.ic_launcher)
//    val scaledLogoBitmap =
//        Bitmap.createScaledBitmap(logoBitmap, qrCodeWidth / 4, qrCodeHeight / 4, false)

    return bitmap
}

internal fun displayQRCode(imageView: ImageView, activity: Activity, value: String){
    val widthPixels = activity.resources.getDimensionPixelSize(R.dimen.qr_code_box_size)
    val heightPixels = activity.resources.getDimensionPixelSize(R.dimen.qr_code_box_size)

    imageView.setImageBitmap(
        qrCodeBitmapGenerator(
            activity = activity,
            data = value,
            widthPixels = widthPixels,
            heightPixels = heightPixels
        )
    )
}