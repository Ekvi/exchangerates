package com.exchangerates.client.views;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;


public class ScreenSizeChecker extends Widget {
    private static ScreenSizeChecker screenSizeChecker;

    private ScreenSizeChecker(){}

    public static ScreenSizeChecker getScreenSizeChecker() {
        if(screenSizeChecker == null) {
            screenSizeChecker = new ScreenSizeChecker();
        }
        return screenSizeChecker;
    }

    public int getChartHeight() {
        int height;

        if(Window.getClientHeight() <= 960 && Window.getClientHeight() >= 920) {
            height = 180;
        } else if(Window.getClientHeight() <= 870 && Window.getClientHeight() >= 795) {
            height = 170;
        } else if(Window.getClientHeight() <= 680 && Window.getClientHeight() >= 660) {
            height = 160;
        } else {
            height = 150;
        }
        return height;
    }

    public int getChartWidth() {
        int width;

        if(Window.getClientWidth() <= 1930 && Window.getClientWidth() >= 1910) {
            width = 280;
        }else if(Window.getClientWidth() <= 1690 && Window.getClientWidth() >= 1590) {
            width = 260;
        } else if(Window.getClientWidth() <= 1375 && Window.getClientWidth() >= 1335) {
            width = 230;
        } else if(Window.getClientWidth() <= 1290 && Window.getClientWidth() >= 1270) {
            width = 220;
        } else if(Window.getClientWidth() <= 1160 && Window.getClientWidth() >= 1145) {
            width = 200;
        } else if(Window.getClientWidth() <= 1015 && Window.getClientWidth() >= 1030) {
            width = 180;
        } else {
            width = 150;
        }
        return width;
    }

    public String getScrollPanelHeight() {
        String height = "";
        if(Window.getClientHeight() > 940 && Window.getClientHeight() < 960 ) {
            height = "503px";
        } else if(Window.getClientHeight() > 920 && Window.getClientHeight() < 930 ) {
            height = "500px";
        } else if(Window.getClientHeight() > 855 && Window.getClientHeight() < 870 ) {
            height = "440px";
        } else if(Window.getClientHeight() > 795 && Window.getClientHeight() < 810 ) {
            height = "380px";
        } else if(Window.getClientHeight() > 760 && Window.getClientHeight() < 770 ) {
            height = "350px";
        } else if(Window.getClientHeight() > 740 && Window.getClientHeight() < 750 ) {
            height = "318px";
        } else if(Window.getClientHeight() > 720 && Window.getClientHeight() < 730 ) {
            height = "315px";
        } else if(Window.getClientHeight() > 695 && Window.getClientHeight() < 710 ) {
            height = "320px";
        } else if(Window.getClientHeight() > 660 && Window.getClientHeight() < 680) {
            height = "290px";
        } else if(Window.getClientHeight() > 615 && Window.getClientHeight() < 630) {
            height = "230px";
        }

        return height;
    }

    public String getStackLayoutPanelSize() {
        String height = "";
        if(Window.getClientHeight() > 940 && Window.getClientHeight() < 960 ) {         //1680x1050
            height = Window.getClientHeight()/1.61 + "px";
        } else if(Window.getClientHeight() > 920 && Window.getClientHeight() < 930 ) {  //1280x1024 1600x1024
            height = Window.getClientHeight()/1.57 + "px";
        } else if(Window.getClientHeight() > 855 && Window.getClientHeight() < 870 ) {  //1280x960
            height = Window.getClientHeight()/1.46 + "px";
        } else if(Window.getClientHeight() > 795 && Window.getClientHeight() < 810 ) {  //1600x900
            height = Window.getClientHeight()/1.36 + "px";
        } else if(Window.getClientHeight() > 760 && Window.getClientHeight() < 770 ) {  //1152x864
            height = Window.getClientHeight()/1.3 + "px";
        } else if(Window.getClientHeight() > 740 && Window.getClientHeight() < 750 ) {  //1680x1050 125%
            height = Window.getClientHeight()/1.27 + "px";
        } else if(Window.getClientHeight() > 720 && Window.getClientHeight() < 730 ) {  //1600x1024 125%
            height = Window.getClientHeight()/1.24 + "px";
        } else if(Window.getClientHeight() > 695 && Window.getClientHeight() < 710 ) {   //1280x800
            height = Window.getClientHeight()/1.19 + "px";
        } else if(Window.getClientHeight() > 660 && Window.getClientHeight() < 680 ) {  //1366x768  1360x768  1280x768
            height = Window.getClientHeight()/1.135 + "px";
        } else if(Window.getClientHeight() > 615 && Window.getClientHeight() < 630 ) {
            height = Window.getClientHeight()/1.05 + "px";
        }
        return height;
    }
}
