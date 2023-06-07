package com.example.myapplication;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;


import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * EP-381C
 * 8 dots/mm(203dpi)
 * 72mm
 */
public class HaoPrinter {

    Context context;
    public int printerStatus = 0;

    BitmapPrinter bitmapPrinter;

    public HaoPrinter(Context applicationContext, BitmapPrinter bitmapPrinter) {
        this.context = applicationContext;
        openDevice();
        this.bitmapPrinter = bitmapPrinter;
    }


    public int OpenDevice(int DeviceID, String deviceFile, String szPort, String szParam) {
        return 0;
    }

    private void openDevice() {

    }


    public int CloseDevice(int DeviceID) {

        return 0;
    }

    /**
     * 首先获取状态
     *
     * @return
     * @
     */

    public int getStatus() {
        byte[] status = new byte[1];

        return 0;
    }

    /**
     * 设置页面大小
     *
     * @param format - 打印设置
     *               <ul>
     * @return
     * @
     */
    public int setPageSize(Bundle format) {
        /**
         * format – 指定打印设置格式
         * pageW(int)：纸张宽度（毫米），不能大于门票纸，否则可能导致定位
         * 错误
         * pageH(int)：纸张高度（毫米），不能大于门票纸，否则可能导致定位错q
         * 误
         * direction(int)：打印起始坐标方向，0－出纸方向右下角为坐标原点，1
         * －出纸方向左上角为坐标原点
         * OffsetX(int)：左偏移量（毫米）,设置后，打印内容全部往左偏移指定位
         * 置
         * OffsetY(int)：上偏移量（毫米）,设置后，打印内容全部往下偏移指定位
         * 置
         */
        int pageW = format.getInt("pageW");
        int pageH = format.getInt("pageH");
        int direction = format.getInt("direction");
        int offsetX = format.getInt("OffsetX");
        int offsetY = format.getInt("OffsetY");
        Log.d(TAG, "setPageSize: pageW=" + pageW);
        Log.d(TAG, "setPageSize: pageH=" + pageH);
        Log.d(TAG, "setPageSize: direction=" + direction);
        Log.d(TAG, "setPageSize: OffsetX=" + offsetX);
        Log.d(TAG, "setPageSize: OffsetY=" + offsetY);

        bitmapPrinter.setPageSize(pageW * 8, pageH * 8, direction, offsetX, offsetY);
        return 0;
    }

    private static final String TAG = "HaoPrinter";

    /**
     * 首先是获取状态，其次是startPrintDoc打印
     *
     * @return
     * @
     */

    public int startPrintDoc() {

        return 0;
    }

    public int addText(Bundle format, String text) {
        /**
         * fontName(Sting)：字体名称，安卓下使用的字体文件必须放在
         * asset\font 目录下，例如填： FZLTXHJW.TTF ， 则该字体文件存
         * 放在项目的 asset\font\FZLTXHJW.TTF
         * fontSize(int)：字体大小，一般设置为 10~16 大小
         * rotation(int)：旋转角度，0，90，180，270 四个角度
         * iLeft(int): 距离左边距离,单位 mm
         * iTop(int): 距离顶部距离,单位 mm
         * align(int)：对齐方式，默认左对齐，0:left, 1:center, 2:right
         * pageWidth(int)：文本打印宽度，单位:MM，如果内容超过宽度会自动
         * 换行
         */
        String fontName = format.getString("fontName");
        int fontSize = format.getInt("fontSize");
        int rotation = format.getInt("rotation");
        int iLeft = format.getInt("iLeft");
        int iTop = format.getInt("iTop");
        int align = format.getInt("align");
        int pageWidth = format.getInt("pageWidth");
        Log.d(TAG, text+" fontSize=" + fontSize + " rotation=" + rotation + " iLeft=" + iLeft + " iTop=" + iTop + " align=" + align + " pageWidth=" + pageWidth);
        bitmapPrinter.addText(text, fontSize, rotation, iLeft * 8, iTop * 8, align, pageWidth * 8);
        return 0;
    }

    public int addQrCode(Bundle format, String qrCode) {
        /**
         * iLeft(int): 距离左边距离,单位 mm
         * iTop(int): 距离顶部距离,单位 mm
         * expectedHeight(int) - 期望高度，单位 mm
         * qrCode – 二维码内容
         */
        int iLeft = format.getInt("iLeft");
        int iTop = format.getInt("iTop");//7
        int expectedHeight = format.getInt("expectedHeight");//16
        //宽 1~16
        Log.d(TAG, "addQrCode: iLeft=" + iLeft + " iTop=" + iTop + " expectedHeight=" + expectedHeight);
        bitmapPrinter.addQrCode(iLeft * 8, iTop * 8, expectedHeight, qrCode);
        return 0;
    }


    public int addImage(Bundle format, String imageData) {
        byte[] bytes = Base64.decode(imageData, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        //format –打印格式，可设置打印的位置、宽度、高度
        //rotation(int)：旋转角度，0，90，180，270 四个角度
        //iLeft(int): 距离左边距离,单位 mm
        //iTop(int): 距离顶部距离,单位 mm
        //iWidth(int): 图像宽度,单位 mm
        //iHeight(int): 图像高度,单位 mm
        int rotation = format.getInt("rotation");
        int iLeft = format.getInt("iLeft");
        int iTop = format.getInt("iTop");
        int iWidth = format.getInt("iWidth");
        int iHeight = format.getInt("iHeight");

        return 0;
    }

    /**
     * 结束打印任务
     *
     * @return
     * @
     */

    public int endPrintDoc() {
        Bitmap bitmap = bitmapPrinter.drawEnd();

        //mPos.POS_FullCutPaper();
        return 0;
    }


    public void OnOpen() {
        printerStatus = 0;
        Log.d(TAG, "OnOpen: " + printerStatus);
    }


    public void OnOpenFailed() {
        printerStatus = 1;
        Log.d(TAG, "OnOpenFailed: " + printerStatus);
    }


    public void OnClose() {
        printerStatus = 2;
        Log.d(TAG, "OnClose: " + printerStatus);
    }

}
