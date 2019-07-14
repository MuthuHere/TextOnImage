package com.muthu.textonimage

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.text.Layout
import android.text.StaticLayout
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.text.TextPaint
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        ivWithText.setImageBitmap(drawTextOnBitmap(this, R.drawable.ic_image, "Muthu"))
    }

    private fun drawTextOnBitmap(
        gContext: Context,
        gResId: Int,
        gText: String
    ): Bitmap {
        val resources = gContext.resources
        val scale = resources.displayMetrics.density
        var bitmap = BitmapFactory.decodeResource(resources, gResId)

        var bitmapConfig: android.graphics.Bitmap.Config? = bitmap.config
        // set default bitmap config if none
        if (bitmapConfig == null) {
            bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888
        }
        // resource bitmaps are imutable,
        // so we need to convert it to mutable one
        bitmap = bitmap.copy(bitmapConfig, true)

        val canvas = Canvas(bitmap)
        // new antialised Paint
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        // text color - #3D3D3D
        paint.color = Color.rgb(200, 61, 61)
        // text size in pixels
        paint.textSize = (25 * scale)
        // text shadow
        paint.setShadowLayer(1f, 0f, 1f, Color.WHITE)

        // draw text to the Canvas center
        val bounds = Rect()
        paint.getTextBounds(gText, 0, gText.length, bounds)
        val x = ((bitmap.width - bounds.width()) / 2).toFloat()
        val y = ((bitmap.height + bounds.height()) / 2).toFloat()

        canvas.drawText(gText, x, y, paint)

        return bitmap
    }
}
