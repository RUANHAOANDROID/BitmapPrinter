package com.example.myapplication

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bitmapPrinter = BitmapPrinter()
        val haoPrinter = HaoPrinter(this, bitmapPrinter);
        val test = PrinterMock(haoPrinter);
        test.printer()
        val imageView = findViewById<ImageView>(R.id.imgPrinter)
        imageView.setImageBitmap(bitmapPrinter.drawEnd())
    }
}