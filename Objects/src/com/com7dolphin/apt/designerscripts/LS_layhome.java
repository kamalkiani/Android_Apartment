package com.com7dolphin.apt.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_layhome{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 5;BA.debugLine="welcomePanel.Width = 100%x"[layhome/General script]
views.get("welcomepanel").vw.setWidth((int)((100d / 100 * width)));
//BA.debugLineNum = 6;BA.debugLine="welcomePanel.Height = 100%y"[layhome/General script]
views.get("welcomepanel").vw.setHeight((int)((100d / 100 * height)));
//BA.debugLineNum = 7;BA.debugLine="welcomePanel.Left = 0"[layhome/General script]
views.get("welcomepanel").vw.setLeft((int)(0d));
//BA.debugLineNum = 8;BA.debugLine="welcomePanel.Top = 0"[layhome/General script]
views.get("welcomepanel").vw.setTop((int)(0d));
//BA.debugLineNum = 10;BA.debugLine="welcomeImg.Width = 70%x"[layhome/General script]
views.get("welcomeimg").vw.setWidth((int)((70d / 100 * width)));
//BA.debugLineNum = 11;BA.debugLine="welcomeImg.Height = 70%x"[layhome/General script]
views.get("welcomeimg").vw.setHeight((int)((70d / 100 * width)));
//BA.debugLineNum = 12;BA.debugLine="welcomeImg.Left = 15%x"[layhome/General script]
views.get("welcomeimg").vw.setLeft((int)((15d / 100 * width)));
//BA.debugLineNum = 13;BA.debugLine="welcomeImg.Top = 50%y-35%x"[layhome/General script]
views.get("welcomeimg").vw.setTop((int)((50d / 100 * height)-(35d / 100 * width)));
//BA.debugLineNum = 15;BA.debugLine="webLabel.Width = 100%x"[layhome/General script]
views.get("weblabel").vw.setWidth((int)((100d / 100 * width)));
//BA.debugLineNum = 16;BA.debugLine="webLabel.Height = 10%y"[layhome/General script]
views.get("weblabel").vw.setHeight((int)((10d / 100 * height)));
//BA.debugLineNum = 17;BA.debugLine="webLabel.Left = 0"[layhome/General script]
views.get("weblabel").vw.setLeft((int)(0d));
//BA.debugLineNum = 18;BA.debugLine="webLabel.Top = 80%y"[layhome/General script]
views.get("weblabel").vw.setTop((int)((80d / 100 * height)));
//BA.debugLineNum = 20;BA.debugLine="homePanel.Width = 100%x"[layhome/General script]
views.get("homepanel").vw.setWidth((int)((100d / 100 * width)));
//BA.debugLineNum = 21;BA.debugLine="homePanel.Height = 100%y"[layhome/General script]
views.get("homepanel").vw.setHeight((int)((100d / 100 * height)));
//BA.debugLineNum = 22;BA.debugLine="homePanel.Left = 0"[layhome/General script]
views.get("homepanel").vw.setLeft((int)(0d));
//BA.debugLineNum = 23;BA.debugLine="homePanel.Top = 0"[layhome/General script]
views.get("homepanel").vw.setTop((int)(0d));
//BA.debugLineNum = 25;BA.debugLine="headerImg.Width = 100%x"[layhome/General script]
views.get("headerimg").vw.setWidth((int)((100d / 100 * width)));
//BA.debugLineNum = 26;BA.debugLine="headerImg.Height = 60%y"[layhome/General script]
views.get("headerimg").vw.setHeight((int)((60d / 100 * height)));
//BA.debugLineNum = 27;BA.debugLine="headerImg.Left = 0"[layhome/General script]
views.get("headerimg").vw.setLeft((int)(0d));
//BA.debugLineNum = 28;BA.debugLine="headerImg.Top = 0"[layhome/General script]
views.get("headerimg").vw.setTop((int)(0d));
//BA.debugLineNum = 30;BA.debugLine="button1.Width = 100%x"[layhome/General script]
views.get("button1").vw.setWidth((int)((100d / 100 * width)));
//BA.debugLineNum = 31;BA.debugLine="button1.Height = 8%y"[layhome/General script]
views.get("button1").vw.setHeight((int)((8d / 100 * height)));
//BA.debugLineNum = 32;BA.debugLine="button1.Left = 0"[layhome/General script]
views.get("button1").vw.setLeft((int)(0d));
//BA.debugLineNum = 33;BA.debugLine="button1.Top = 60%y"[layhome/General script]
views.get("button1").vw.setTop((int)((60d / 100 * height)));
//BA.debugLineNum = 35;BA.debugLine="button2.Width = 100%x"[layhome/General script]
views.get("button2").vw.setWidth((int)((100d / 100 * width)));
//BA.debugLineNum = 36;BA.debugLine="button2.Height = 8%y"[layhome/General script]
views.get("button2").vw.setHeight((int)((8d / 100 * height)));
//BA.debugLineNum = 37;BA.debugLine="button2.Left = 0"[layhome/General script]
views.get("button2").vw.setLeft((int)(0d));
//BA.debugLineNum = 38;BA.debugLine="button2.Top = 68%y"[layhome/General script]
views.get("button2").vw.setTop((int)((68d / 100 * height)));
//BA.debugLineNum = 40;BA.debugLine="button3.Width = 100%x"[layhome/General script]
views.get("button3").vw.setWidth((int)((100d / 100 * width)));
//BA.debugLineNum = 41;BA.debugLine="button3.Height = 8%y"[layhome/General script]
views.get("button3").vw.setHeight((int)((8d / 100 * height)));
//BA.debugLineNum = 42;BA.debugLine="button3.Left = 0"[layhome/General script]
views.get("button3").vw.setLeft((int)(0d));
//BA.debugLineNum = 43;BA.debugLine="button3.Top = 76%y"[layhome/General script]
views.get("button3").vw.setTop((int)((76d / 100 * height)));
//BA.debugLineNum = 45;BA.debugLine="button4.Width = 100%x"[layhome/General script]
views.get("button4").vw.setWidth((int)((100d / 100 * width)));
//BA.debugLineNum = 46;BA.debugLine="button4.Height = 8%y"[layhome/General script]
views.get("button4").vw.setHeight((int)((8d / 100 * height)));
//BA.debugLineNum = 47;BA.debugLine="button4.Left = 0"[layhome/General script]
views.get("button4").vw.setLeft((int)(0d));
//BA.debugLineNum = 48;BA.debugLine="button4.Top = 84%y"[layhome/General script]
views.get("button4").vw.setTop((int)((84d / 100 * height)));
//BA.debugLineNum = 50;BA.debugLine="button5.Width = 100%x"[layhome/General script]
views.get("button5").vw.setWidth((int)((100d / 100 * width)));
//BA.debugLineNum = 51;BA.debugLine="button5.Height = 8%y"[layhome/General script]
views.get("button5").vw.setHeight((int)((8d / 100 * height)));
//BA.debugLineNum = 52;BA.debugLine="button5.Left = 0"[layhome/General script]
views.get("button5").vw.setLeft((int)(0d));
//BA.debugLineNum = 53;BA.debugLine="button5.Top = 92%y"[layhome/General script]
views.get("button5").vw.setTop((int)((92d / 100 * height)));
//BA.debugLineNum = 55;BA.debugLine="Label1.Width = 95%x"[layhome/General script]
views.get("label1").vw.setWidth((int)((95d / 100 * width)));
//BA.debugLineNum = 56;BA.debugLine="Label1.Height = 8%y"[layhome/General script]
views.get("label1").vw.setHeight((int)((8d / 100 * height)));
//BA.debugLineNum = 57;BA.debugLine="Label1.Left = 0"[layhome/General script]
views.get("label1").vw.setLeft((int)(0d));
//BA.debugLineNum = 58;BA.debugLine="Label1.Top = 60%y"[layhome/General script]
views.get("label1").vw.setTop((int)((60d / 100 * height)));
//BA.debugLineNum = 60;BA.debugLine="Label2.Width = 95%x"[layhome/General script]
views.get("label2").vw.setWidth((int)((95d / 100 * width)));
//BA.debugLineNum = 61;BA.debugLine="Label2.Height = 8%y"[layhome/General script]
views.get("label2").vw.setHeight((int)((8d / 100 * height)));
//BA.debugLineNum = 62;BA.debugLine="Label2.Left = 0"[layhome/General script]
views.get("label2").vw.setLeft((int)(0d));
//BA.debugLineNum = 63;BA.debugLine="Label2.Top = 68%y"[layhome/General script]
views.get("label2").vw.setTop((int)((68d / 100 * height)));
//BA.debugLineNum = 65;BA.debugLine="Label3.Width = 95%x"[layhome/General script]
views.get("label3").vw.setWidth((int)((95d / 100 * width)));
//BA.debugLineNum = 66;BA.debugLine="Label3.Height = 8%y"[layhome/General script]
views.get("label3").vw.setHeight((int)((8d / 100 * height)));
//BA.debugLineNum = 67;BA.debugLine="Label3.Left = 0"[layhome/General script]
views.get("label3").vw.setLeft((int)(0d));
//BA.debugLineNum = 68;BA.debugLine="Label3.Top = 76%y"[layhome/General script]
views.get("label3").vw.setTop((int)((76d / 100 * height)));
//BA.debugLineNum = 70;BA.debugLine="Label4.Width = 95%x"[layhome/General script]
views.get("label4").vw.setWidth((int)((95d / 100 * width)));
//BA.debugLineNum = 71;BA.debugLine="Label4.Height = 8%y"[layhome/General script]
views.get("label4").vw.setHeight((int)((8d / 100 * height)));
//BA.debugLineNum = 72;BA.debugLine="Label4.Left = 0"[layhome/General script]
views.get("label4").vw.setLeft((int)(0d));
//BA.debugLineNum = 73;BA.debugLine="Label4.Top = 84%y"[layhome/General script]
views.get("label4").vw.setTop((int)((84d / 100 * height)));
//BA.debugLineNum = 75;BA.debugLine="Label5.Width = 95%x"[layhome/General script]
views.get("label5").vw.setWidth((int)((95d / 100 * width)));
//BA.debugLineNum = 76;BA.debugLine="Label5.Height = 8%y"[layhome/General script]
views.get("label5").vw.setHeight((int)((8d / 100 * height)));
//BA.debugLineNum = 77;BA.debugLine="Label5.Left = 0"[layhome/General script]
views.get("label5").vw.setLeft((int)(0d));
//BA.debugLineNum = 78;BA.debugLine="Label5.Top = 92%y"[layhome/General script]
views.get("label5").vw.setTop((int)((92d / 100 * height)));

}
}