package com.example.myapplication;

import android.os.Bundle;

public class PrinterUchiTest {

    private HaoPrinter printer;

    public PrinterUchiTest(HaoPrinter printer) {
        this.printer = printer;
    }

    public void printer() {
        Bundle pageBundle = new Bundle();
        pageBundle.putInt("pageW", 72);
        pageBundle.putInt("pageH", 72);
        pageBundle.putInt("OffsetX", 0);
        pageBundle.putInt("OffsetY", 0);
        pageBundle.putInt("direction", 0);
        printer.setPageSize(pageBundle);

        // addText: text=票券名称 fontSize=14 rotation=0 iLeft=0 iTop=0 align=1 pageWidth=10
        // addText: text=票券名称 fontSize=14 rotation=0 iLeft=0 iTop=0 align=1 pageWidth=10
        TextBuilder pjBuilder = new TextBuilder();
        String text = "票券名称";
        pjBuilder.fontSize = 14;
        pjBuilder.rotation = 0;
        pjBuilder.iLeft = 0;
        pjBuilder.iTop = 0;
        pjBuilder.align = 1;
        pjBuilder.pageWidth = 10;
        printerText(printer, text, pjBuilder);

        // addQrCode: iLeft=0 iTop=6 expectedHeight=26 qrCode=77032975400592<MjAwMDAwMDEzNjIwMjMtMDYtMDMyMDI0LTA2LTAz>
        // addQrCode: iLeft=0 iTop=6 expectedHeight=26 qrCode=77032975400592<MjAwMDAwMDEzNjIwMjMtMDYtMDMyMDI0LTA2LTAz>
        Bundle qrBundle = new Bundle();
        qrBundle.putInt("iLeft", 1);
        qrBundle.putInt("iTop", 6);
        qrBundle.putInt("expectedHeight", 26);
        printer.addQrCode(qrBundle, "34027290657570<MjAwMDAwMTkyNDIwMjMtMDYtMDMyMDIzLTA2LTAz>");


        //addText: text=票券编号 fontSize=14 rotation=0 iLeft=27 iTop=7 align=1 pageWidth=10
        //addText: text=票券编号 fontSize=14 rotation=0 iLeft=27 iTop=7 align=1 pageWidth=10
        TextBuilder numberBuilder = new TextBuilder();
        String noTitle = "票券编号";
        numberBuilder.fontSize = 14;
        numberBuilder.rotation = 0;
        numberBuilder.iLeft = 27;
        numberBuilder.iTop = 7;
        numberBuilder.align = 1;
        numberBuilder.pageWidth = 10;
        printerText(printer, noTitle, numberBuilder);

        //addText: text=订单号 fontSize=14 rotation=0 iLeft=27 iTop=15 align=1 pageWidth=8
        //addText: text=订单号 fontSize=14 rotation=0 iLeft=27 iTop=15 align=1 pageWidth=8
        TextBuilder dingdanBuilder = new TextBuilder();
        String dingdanTitle = "订单号";
        dingdanBuilder.fontSize = 14;
        dingdanBuilder.rotation = 0;
        dingdanBuilder.iLeft = 27;
        dingdanBuilder.iTop = 15;
        dingdanBuilder.align = 1;
        dingdanBuilder.pageWidth = 8;
        printerText(printer, dingdanTitle, dingdanBuilder);

        //addText: text=有效期 fontSize=14 rotation=0 iLeft=27 iTop=24 align=1 pageWidth=8
        //addText: text=有效期 fontSize=14 rotation=0 iLeft=27 iTop=24 align=1 pageWidth=8
        TextBuilder qdBuilder = new TextBuilder();
        String qdTitle = "有效期";
        qdBuilder.fontSize = 14;
        qdBuilder.rotation = 0;
        qdBuilder.iLeft = 27;
        qdBuilder.iTop = 24;
        qdBuilder.align = 1;
        qdBuilder.pageWidth = 8;
        printerText(printer, qdTitle, qdBuilder);

        //addText: text=hell word  fontSize=14 rotation=0 iLeft=2 iTop=33 align=1 pageWidth=27
        //addText: text=hell word  fontSize=14 rotation=0 iLeft=2 iTop=33 align=1 pageWidth=27
        TextBuilder helloBuilder = new TextBuilder();
        String helloTitle = "hell word";
        helloBuilder.fontSize = 14;
        helloBuilder.rotation = 0;
        helloBuilder.iLeft = 2;
        helloBuilder.iTop = 33;
        helloBuilder.align = 1;
        helloBuilder.pageWidth = 27;
        printerText(printer, helloTitle, helloBuilder);

        //addText: text=0元门票 fontSize=14 rotation=0 iLeft=11 iTop=0 align=1 pageWidth=63
        //addText: text=0元门票 fontSize=14 rotation=0 iLeft=11 iTop=0 align=1 pageWidth=63
        TextBuilder moneyBuilder = new TextBuilder();
        String money = "0元门票";
        moneyBuilder.fontSize = 14;
        moneyBuilder.rotation = 0;
        moneyBuilder.iLeft = 11;
        moneyBuilder.iTop = 0;
        moneyBuilder.align = 1;
        moneyBuilder.pageWidth = 63;
        printerText(printer, money, moneyBuilder);

        //addText: text=T2306030018523800001 fontSize=14 rotation=0 iLeft=38 iTop=7 align=1 pageWidth=35
        //addText: text=T2306030018523800001 fontSize=14 rotation=0 iLeft=38 iTop=7 align=1 pageWidth=35
        TextBuilder tBuilder = new TextBuilder();
        String t = "T2306030018523800001";
        tBuilder.fontSize = 14;
        tBuilder.rotation = 0;
        tBuilder.iLeft = 38;
        tBuilder.iTop = 7;
        tBuilder.align = 1;
        tBuilder.pageWidth = 35;
        printerText(printer, t, tBuilder);

        //addText: text=MO202710010000033105 fontSize=14 rotation=0 iLeft=35 iTop=15 align=1 pageWidth=37
        //addText: text=MO202710010000033105 fontSize=14 rotation=0 iLeft=35 iTop=15 align=1 pageWidth=37
        TextBuilder mmBuilder = new TextBuilder();
        String mm = "MO202710010000033105";
        mmBuilder.fontSize = 14;
        mmBuilder.rotation = 0;
        mmBuilder.iLeft = 35;
        mmBuilder.iTop = 15;
        mmBuilder.align = 1;
        mmBuilder.pageWidth = 37;
        printerText(printer, mm, mmBuilder);
        //addText: text=2024-06-03 fontSize=14 rotation=0 iLeft=35 iTop=24 align=1 pageWidth=37
        //addText: text=2024-06-03 fontSize=14 rotation=0 iLeft=35 iTop=24 align=1 pageWidth=37
        TextBuilder mBuilder = new TextBuilder();
        String m = "2024-06-03";
        mBuilder.fontSize = 14;
        mBuilder.rotation = 0;
        mBuilder.iLeft = 35;
        mBuilder.iTop = 24;
        mBuilder.align = 1;
        mBuilder.pageWidth = 37;
        printerText(printer, m, mBuilder);
        //addText: text= fontSize=14 rotation=0 iLeft=29 iTop=33 align=1 pageWidth=41
        //addText: text= fontSize=14 rotation=0 iLeft=29 iTop=33 align=1 pageWidth=41
        TextBuilder kBuilder = new TextBuilder();
        String k = "";
        mBuilder.fontSize = 14;
        mBuilder.rotation = 0;
        mBuilder.iLeft = 29;
        mBuilder.iTop = 33;
        mBuilder.align = 1;
        mBuilder.pageWidth = 41;
        printerText(printer, k, kBuilder);

        printer.endPrintDoc();
        printer.CloseDevice(1);
    }

    private void printerText(HaoPrinter printer, String text, TextBuilder textBuilder) {
        Bundle textBuild = new Bundle();
        textBuild.putInt("fontSize", textBuilder.fontSize);
        textBuild.putInt("rotation", textBuilder.rotation);
        textBuild.putInt("iLeft", textBuilder.iLeft);
        textBuild.putInt("iTop", textBuilder.iTop);
        textBuild.putInt("pageWidth", textBuilder.pageWidth);
        textBuild.putInt("align", textBuilder.align);
        printer.addText(textBuild, text);
    }
}
