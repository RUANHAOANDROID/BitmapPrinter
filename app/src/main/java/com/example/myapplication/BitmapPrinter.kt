package com.example.myapplication

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.Log
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import java.util.*

/**
openDevice: device =  VID:0FE6 PID:811EVendorId=4070ProductId=33054
OnOpen: 0
setPageSize: pageW=72
setPageSize: pageH=120
setPageSize: direction=0
setPageSize: OffsetX=0
setPageSize: OffsetY=

addText: text=票券名称 fontSize=20 rotation=0 iLeft=0 iTop=0 align=1 pageWidth=71
addQrCode: iLeft=6 iTop=6 expectedHeight=63 qrCode=27636652205751<MjAwMDAwMTkyNDIwMjMtMDYtMDIyMDIzLTA2LTAy>
addText: text=证件类型 fontSize=14 rotation=0 iLeft=20 iTop=73 align=1 pageWidth=30
addText: text=有效期 fontSize=14 rotation=0 iLeft=20 iTop=81 align=1 pageWidth=30
addText: text=固定文字 fontSize=14 rotation=0 iLeft=20 iTop=90 align=1 pageWidth=30
addText: text=hao88打印测试票 fontSize=20 rotation=0 iLeft=0 iTop=6 align=0 pageWidth=71
addText: text= fontSize=14 rotation=0 iLeft=20 iTop=79 align=0 pageWidth=71
addText: text=2023-06-02 fontSize=14 rotation=0 iLeft=20 iTop=87 align=0 pageWidth=71
endPrintDoc: 结束打印任务
 */
class BitmapPrinter {
    private lateinit var bitmap: Bitmap
    private lateinit var canvas: Canvas

    private var mPaint: Paint = Paint()

    private var textPaint: TextPaint = TextPaint().apply {
        style = Paint.Style.STROKE
    }

    fun setPageSize(
        pageW: Int = 567, pageH: Int = 900, direction: Int = 0, OffsetX: Int = 0, OffsetY: Int = 0
    ) {
        val (bitmap, canvas) = basicBitmap(pageW, pageH)
        this.bitmap = bitmap
        this.canvas = canvas
    }

    /**
    fontName(Sting)：字体名称，安卓下使用的字体文件必须放在
    asset\font 目录下，例如填： FZLTXHJW.TTF ， 则该字体文件存放在项目的 asset\font\FZLTXHJW.TTF
    fontSize(int)：字体大小，一般设置为 10~16 大小
    rotation(int)：旋转角度，0，90，180，270 四个角度
    iLeft(int): 距离左边距离,单位 mm
    iTop(int): 距离顶部距离,单位 mm
    align(int)：对齐方式，默认左对齐，0:left, 1:center, 2:right
    pageWidth(int)：文本打印宽度，单位:MM，如果内容超过宽度会自动换行
     */
    fun addText(
        text: String,
        fontSize: Int,
        rotation: Int,
        iLeft: Int,
        iTop: Int,
        align: Int,
        pageWidth: Int
    ) {
        textPaint.textSize = fontSize.toFloat() // 设置字体大小
        var alignment = when (align) {
            0 -> {
                Layout.Alignment.ALIGN_NORMAL
            }
            1 -> {
                Layout.Alignment.ALIGN_CENTER
            }
            2 -> {
                Layout.Alignment.ALIGN_OPPOSITE
            }
            else -> {
                Layout.Alignment.ALIGN_NORMAL
            }
        }
        var x = iLeft
//        if (alignment == Layout.Alignment.ALIGN_CENTER) {
//            x += (bitmap.width - pageWidth) / 2
//        }
        var y = iTop
        drawText(text, x.toFloat(), y.toFloat(), pageWidth, alignment)
    }

    private fun drawText(
        text: String,
        x: Float,
        y: Float,
        textWidth: Int,
        align: Layout.Alignment
    ) {
        textPaint.color = Color.BLACK
        textPaint.style = Paint.Style.FILL
        val staticLayout = StaticLayout.Builder.obtain(
            text, 0, text.length, textPaint, textWidth
        )
            .setAlignment(align)
            .build()
        canvas.save() // 保存当前 Canvas 状态
        canvas.translate(x, y) // 平移 Canvas，将区域放置在指定位置
        staticLayout.draw(canvas) // 绘制文本布局
        canvas.restore() // 恢复 Canvas 状态
        //canvas.drawText(text, x, y, mPaint)
        Log.d(TAG, "drawText: text=$text x=$x y=$y textWidth=$textWidth")
    }

    /**
    iLeft(int): 距离左边距离,单位 mm
    iTop(int): 距离顶部距离,单位 mm
    expectedHeight(int) - 期望高度，单位 mm
    qrCode – 二维码内容
     */
    fun addQrCode(
        iLeft: Int,
        iTop: Int,
        expectedHeight: Int,
        qrCode: String = "27636652205751<MjAwMDAwMTkyNDIwMjMtMDYtMDIyMDIzLTA2LTAy>"
    ) {
        drawQrCode(qrCode, expectedHeight * 8, iLeft.toFloat(), iTop.toFloat())
    }

    private fun drawQrCode(context: String, size: Int, x: Float, y: Float) {
        val bitmap = generateQrImage(text = context, size)
        if (null != bitmap) {
            Log.d(TAG, "drawQrCode: x=$x y=$y")
            canvas.drawBitmap(bitmap, x, y, null)
        }
    }

    companion object {
        const val TAG = "Printer"
    }

    fun drawEnd(): Bitmap {
        //canvas.restore()
        mPaint.style = Paint.Style.STROKE
        canvas.drawRect(1f, 1f, bitmap.width - 1f, bitmap.height - 1f, mPaint)
        return bitmap
    }

    fun recycle() = run {
        bitmap.recycle()
    }

    private fun generateQrImage(text: String, size: Int): Bitmap? {
        Log.d(TAG, "generateQrImage: text=$text size=$size")
        var qrImage: Bitmap? = null
        if (text.trim { it <= ' ' }.isEmpty()) {
            return null
        }
        val hintMap: MutableMap<EncodeHintType, Any?> = EnumMap(
            EncodeHintType::class.java
        )
        hintMap[EncodeHintType.CHARACTER_SET] = "UTF-8"
        hintMap[EncodeHintType.MARGIN] = 1
        val qrCodeWriter = QRCodeWriter()
        try {
            val byteMatrix = qrCodeWriter.encode(
                text, BarcodeFormat.QR_CODE, size, size, hintMap
            )
            val height = byteMatrix.height
            val width = byteMatrix.width
            qrImage = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    qrImage.setPixel(x, y, if (byteMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        } catch (e: WriterException) {
            e.printStackTrace()
        }
        return qrImage
    }


    private fun basicBitmap(width: Int, height: Int): Pair<Bitmap, Canvas> {
        if (width > 576f) {
            Log.e("打印机服务", "不支持大于576像素的模板")
        }
        Log.d(TAG, "basicBitmap: width=$width height=$height")
        val printerBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        val canvas = Canvas(printerBitmap)
        canvas.drawColor(Color.WHITE)
        return Pair(printerBitmap, canvas)
    }
}
