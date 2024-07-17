package com.example.myapplication;

import android.os.Bundle;

interface IPrinter {

    int OpenDevice(int DeviceID, String deviceFile, String szPort, String szParam);

    int CloseDevice(int DeviceID);

    int getStatus();

    int setPageSize(Bundle format); //打印设置

    int startPrintDoc(); //新建打印任务

    int addText(Bundle format, String text); //打印文本

    int addQrCode(Bundle format, String qrCode); //打印二维码

    int addImage(Bundle format, String imageData); //打印图像

    int endPrintDoc(); //结束打印任务

}