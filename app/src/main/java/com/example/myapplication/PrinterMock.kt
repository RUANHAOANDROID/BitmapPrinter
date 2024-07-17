package com.example.myapplication

import android.os.Bundle
import android.util.Log

class PrinterMock(private val printer: HaoPrinter) {
    fun printer() {
        val printerJSON = PrinterContentProvider.getPrinterContent()

        val pageBundle = Bundle()
        pageBundle.putInt("pageW", printerJSON.pageW.toInt())
        pageBundle.putInt("pageH", printerJSON.pageH.toInt())
        pageBundle.putInt("OffsetX", printerJSON.offsetX.toInt())
        pageBundle.putInt("OffsetY", printerJSON.offsetY.toInt())
        pageBundle.putInt("direction", 0)
        printer.setPageSize(pageBundle)
        for (e in printerJSON.elements) {
            if (e.isText()) {
                printerText(printer, e.text!!, e)
                Log.d("PrinterMock",e.toString())
            }
            if (e.isQrCode()) {
                Log.d("PrinterMock",e.toString())
                val qrBundle = Bundle()
                qrBundle.putInt("iLeft", e.iLeft.toInt())
                qrBundle.putInt("iTop", e.iTop.toInt())
                qrBundle.putInt("expectedHeight", e.expectedHeight!!.toInt())
                printer.addQrCode(
                    qrBundle,
                    "09006910492260<MjAwMDAwMzQxOTIwMjQtMDUtMjcyMDI0LTA1LTI3>"
                )
            }
        }
        printer.endPrintDoc()
        printer.CloseDevice(1)
    }

    private fun printerText(printer: HaoPrinter, text: String, textBuilder: Element) {
        val textBuild = Bundle()
        textBuild.putInt("fontSize", textBuilder.fontSize!!.toInt())
        textBuild.putInt("rotation", textBuilder.rotation!!.toInt())
        textBuild.putInt("iLeft", textBuilder.iLeft.toInt())
        textBuild.putInt("iTop", textBuilder.iTop.toInt())
        textBuild.putInt("pageWidth", textBuilder.pageWidth!!.toInt())
        textBuild.putInt("align", textBuilder.align!!.toInt())
        printer.addText(textBuild, text)
    }
}
