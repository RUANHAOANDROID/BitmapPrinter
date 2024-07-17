// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.example.myapplication

import com.google.gson.Gson

const val TestJSON = "{\"pageW\":76,\"pageH\":196,\"direction\":0,\"OffsetX\":0,\"OffsetY\":0,\"elements\":[{\"elementType\":1,\"iLeft\":46,\"iTop\":152,\"expectedHeight\":24,\"qrCode\":\"70529905174645<MjAwMDAwMTkyNDIwMjQtMDctMTcyMDI1LTA3LTE3>\"},{\"elementType\":0,\"fontName\":\"\",\"fontSize\":26,\"rotation\":90,\"iLeft\":44,\"iTop\":148,\"align\":0,\"pageWidth\":6,\"text\":\"票价\"},{\"elementType\":0,\"fontName\":\"\",\"fontSize\":26,\"rotation\":90,\"iLeft\":40,\"iTop\":148,\"align\":0,\"pageWidth\":12,\"text\":\"销售日期\"},{\"elementType\":0,\"fontName\":\"\",\"fontSize\":26,\"rotation\":90,\"iLeft\":36,\"iTop\":148,\"align\":0,\"pageWidth\":9,\"text\":\"订单号\"},{\"elementType\":0,\"fontName\":\"\",\"fontSize\":26,\"rotation\":90,\"iLeft\":32,\"iTop\":148,\"align\":0,\"pageWidth\":12,\"text\":\"使用人数\"},{\"elementType\":0,\"fontName\":\"\",\"fontSize\":26,\"rotation\":90,\"iLeft\":28,\"iTop\":148,\"align\":0,\"pageWidth\":12,\"text\":\"票券名称\"},{\"elementType\":0,\"fontName\":\"\",\"fontSize\":28,\"rotation\":270,\"iLeft\":16,\"iTop\":144,\"align\":1,\"pageWidth\":36,\"text\":\"固定文字 这是没有旋转过的文字28\"},{\"elementType\":0,\"fontName\":\"\",\"fontSize\":26,\"rotation\":90,\"iLeft\":44,\"iTop\":154,\"align\":0,\"pageWidth\":3,\"text\":\"0.00\"},{\"elementType\":0,\"fontName\":\"\",\"fontSize\":26,\"rotation\":90,\"iLeft\":40,\"iTop\":160,\"align\":0,\"pageWidth\":1,\"text\":\"2024-07-17\"},{\"elementType\":0,\"fontName\":\"\",\"fontSize\":26,\"rotation\":90,\"iLeft\":36,\"iTop\":157,\"align\":0,\"pageWidth\":2,\"text\":\"MO202407170000152877\"},{\"elementType\":0,\"fontName\":\"\",\"fontSize\":26,\"rotation\":90,\"iLeft\":32,\"iTop\":160,\"align\":0,\"pageWidth\":1,\"text\":\"1\"},{\"elementType\":0,\"fontName\":\"\",\"fontSize\":26,\"rotation\":90,\"iLeft\":28,\"iTop\":160,\"align\":0,\"pageWidth\":2,\"text\":\"测试溪水自助票\"}]}"
data class PrinterJSON(
    val elements: List<Element>,
    val offsetX: Long,
    val offsetY: Long,
    val pageH: Long,
    val pageW: Long,
    val direction: Long
)

data class Element(
    val fontName: String? = null,
    val rotation: Long? = null,
    val fontSize: Long? = null,
    val iLeft: Long,
    val text: String? = null,
    val align: Long? = null,
    val elementType: Long,
    val iTop: Long,
    val pageWidth: Long? = null,
    val qrCode: String? = null,
    val expectedHeight: Long? = null
){
    fun isText(): Boolean {
        return text!=null
    }
    fun isQrCode(): Boolean {
        return qrCode!=null
    }
}

object PrinterJsonProvider {
    fun getPrinterContent(): PrinterJSON {
        return Gson().fromJson(TestJSON, PrinterJSON::class.java)
    }
}