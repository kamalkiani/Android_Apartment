package com.com7dolphin.apt;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "com.com7dolphin.apt", "com.com7dolphin.apt.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
		BA.handler.postDelayed(new WaitForLayout(), 5);

	}
	private static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "com.com7dolphin.apt", "com.com7dolphin.apt.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "com.com7dolphin.apt.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEvent(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}

public anywheresoftware.b4a.keywords.Common __c = null;
public static String _vvv7 = "";
public static String _vvv0 = "";
public static anywheresoftware.b4a.objects.Timer _vvvv1 = null;
public anywheresoftware.b4a.objects.AnimationWrapper _vvvvv7 = null;
public anywheresoftware.b4a.objects.AnimationWrapper _vvvvv0 = null;
public anywheresoftware.b4a.objects.AnimationWrapper _vvvvvv1 = null;
public anywheresoftware.b4a.objects.AnimationWrapper _vvvvvv2 = null;
public anywheresoftware.b4a.objects.AnimationWrapper _vvvvvv3 = null;
public anywheresoftware.b4a.objects.PanelWrapper _welcomepanel = null;
public anywheresoftware.b4a.objects.PanelWrapper _homepanel = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _button1 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _button2 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _button3 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _button4 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _button5 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label5 = null;
public com.com7dolphin.apt.act2 _vvvvv5 = null;
public com.com7dolphin.apt.dbutils _vvvvv6 = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
vis = vis | (act2.mostCurrent != null);
return vis;}
public static String  _a_animationend() throws Exception{
 //BA.debugLineNum = 153;BA.debugLine="Sub a_AnimationEnd";
 //BA.debugLineNum = 154;BA.debugLine="If Sender = a1 Then";
if ((anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)).equals((Object)(mostCurrent._vvvvv7.getObject()))) { 
 //BA.debugLineNum = 155;BA.debugLine="PageURL = \"/data/ch1.html#menu\"";
_vvv7 = "/data/ch1.html#menu";
 //BA.debugLineNum = 156;BA.debugLine="pageTitle = \"فصل اول\"";
_vvv0 = "فصل اول";
 //BA.debugLineNum = 157;BA.debugLine="StartActivity(Act2)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._vvvvv5.getObject()));
 }else if((anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)).equals((Object)(mostCurrent._vvvvv0.getObject()))) { 
 //BA.debugLineNum = 159;BA.debugLine="PageURL = \"/data/ch2.html#menu\"";
_vvv7 = "/data/ch2.html#menu";
 //BA.debugLineNum = 160;BA.debugLine="pageTitle = \"فصل دوم\"";
_vvv0 = "فصل دوم";
 //BA.debugLineNum = 161;BA.debugLine="StartActivity(Act2)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._vvvvv5.getObject()));
 }else if((anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)).equals((Object)(mostCurrent._vvvvvv1.getObject()))) { 
 //BA.debugLineNum = 163;BA.debugLine="PageURL = \"/data/ch3.html#menu\"";
_vvv7 = "/data/ch3.html#menu";
 //BA.debugLineNum = 164;BA.debugLine="pageTitle = \"فصل سوم\"";
_vvv0 = "فصل سوم";
 //BA.debugLineNum = 165;BA.debugLine="StartActivity(Act2)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._vvvvv5.getObject()));
 }else if((anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)).equals((Object)(mostCurrent._vvvvvv2.getObject()))) { 
 //BA.debugLineNum = 167;BA.debugLine="PageURL = \"/data/ch4.html#menu\"";
_vvv7 = "/data/ch4.html#menu";
 //BA.debugLineNum = 168;BA.debugLine="pageTitle = \"فصل چهارم\"";
_vvv0 = "فصل چهارم";
 //BA.debugLineNum = 169;BA.debugLine="StartActivity(Act2)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._vvvvv5.getObject()));
 }else if((anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)).equals((Object)(mostCurrent._vvvvvv3.getObject()))) { 
 //BA.debugLineNum = 171;BA.debugLine="PageURL = \"/data/ch5.html#menu\"";
_vvv7 = "/data/ch5.html#menu";
 //BA.debugLineNum = 172;BA.debugLine="pageTitle = \"فصل پنجم\"";
_vvv0 = "فصل پنجم";
 //BA.debugLineNum = 173;BA.debugLine="StartActivity(Act2)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._vvvvv5.getObject()));
 };
 //BA.debugLineNum = 175;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
anywheresoftware.b4a.keywords.constants.TypefaceWrapper _myfont = null;
 //BA.debugLineNum = 61;BA.debugLine="Sub Activity_Create (FirstTime As Boolean)";
 //BA.debugLineNum = 62;BA.debugLine="Activity.LoadLayout(\"layHome\")";
mostCurrent._activity.LoadLayout("layHome",mostCurrent.activityBA);
 //BA.debugLineNum = 63;BA.debugLine="showWelcome(5000)";
_vvvvvv4((int) (5000));
 //BA.debugLineNum = 65;BA.debugLine="Dim myFont As Typeface";
_myfont = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
 //BA.debugLineNum = 66;BA.debugLine="myFont= Typeface.LoadFromAssets(\"irsans.ttf\")";
_myfont.setObject((android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("irsans.ttf")));
 //BA.debugLineNum = 67;BA.debugLine="SetTypeface(homePanel,myFont)";
_vvvvvv5(mostCurrent._homepanel,_myfont);
 //BA.debugLineNum = 70;BA.debugLine="a1.InitializeTranslate(\"a\", 0, 0, 0, 4)";
mostCurrent._vvvvv7.InitializeTranslate(mostCurrent.activityBA,"a",(float) (0),(float) (0),(float) (0),(float) (4));
 //BA.debugLineNum = 71;BA.debugLine="a1.RepeatMode = a1.REPEAT_REVERSE";
mostCurrent._vvvvv7.setRepeatMode(mostCurrent._vvvvv7.REPEAT_REVERSE);
 //BA.debugLineNum = 72;BA.debugLine="a1.Duration = 200";
mostCurrent._vvvvv7.setDuration((long) (200));
 //BA.debugLineNum = 73;BA.debugLine="a2.InitializeTranslate(\"a\", 0, 0, 0, 4)";
mostCurrent._vvvvv0.InitializeTranslate(mostCurrent.activityBA,"a",(float) (0),(float) (0),(float) (0),(float) (4));
 //BA.debugLineNum = 74;BA.debugLine="a2.RepeatMode = a2.REPEAT_REVERSE";
mostCurrent._vvvvv0.setRepeatMode(mostCurrent._vvvvv0.REPEAT_REVERSE);
 //BA.debugLineNum = 75;BA.debugLine="a2.Duration = 200";
mostCurrent._vvvvv0.setDuration((long) (200));
 //BA.debugLineNum = 76;BA.debugLine="a3.InitializeTranslate(\"a\", 0, 0, 0, 4)";
mostCurrent._vvvvvv1.InitializeTranslate(mostCurrent.activityBA,"a",(float) (0),(float) (0),(float) (0),(float) (4));
 //BA.debugLineNum = 77;BA.debugLine="a3.RepeatMode = a3.REPEAT_REVERSE";
mostCurrent._vvvvvv1.setRepeatMode(mostCurrent._vvvvvv1.REPEAT_REVERSE);
 //BA.debugLineNum = 78;BA.debugLine="a3.Duration = 200";
mostCurrent._vvvvvv1.setDuration((long) (200));
 //BA.debugLineNum = 79;BA.debugLine="a4.InitializeTranslate(\"a\", 0, 0, 0, 4)";
mostCurrent._vvvvvv2.InitializeTranslate(mostCurrent.activityBA,"a",(float) (0),(float) (0),(float) (0),(float) (4));
 //BA.debugLineNum = 80;BA.debugLine="a4.RepeatMode = a4.REPEAT_REVERSE";
mostCurrent._vvvvvv2.setRepeatMode(mostCurrent._vvvvvv2.REPEAT_REVERSE);
 //BA.debugLineNum = 81;BA.debugLine="a4.Duration = 200";
mostCurrent._vvvvvv2.setDuration((long) (200));
 //BA.debugLineNum = 82;BA.debugLine="a5.InitializeTranslate(\"a\", 0, 0, 0, 4)";
mostCurrent._vvvvvv3.InitializeTranslate(mostCurrent.activityBA,"a",(float) (0),(float) (0),(float) (0),(float) (4));
 //BA.debugLineNum = 83;BA.debugLine="a5.RepeatMode = a5.REPEAT_REVERSE";
mostCurrent._vvvvvv3.setRepeatMode(mostCurrent._vvvvvv3.REPEAT_REVERSE);
 //BA.debugLineNum = 84;BA.debugLine="a5.Duration = 200";
mostCurrent._vvvvvv3.setDuration((long) (200));
 //BA.debugLineNum = 85;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 118;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 119;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 120;BA.debugLine="If Msgbox2(\"آیا میخواهید از برنامه خارج شوید؟\",";
if (anywheresoftware.b4a.keywords.Common.Msgbox2("آیا میخواهید از برنامه خارج شوید؟","توجه","بلی","","خیر",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA)==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 121;BA.debugLine="Activity.finish";
mostCurrent._activity.Finish();
 }else {
 //BA.debugLineNum = 123;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 };
 //BA.debugLineNum = 126;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 104;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 106;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 100;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 102;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
 //BA.debugLineNum = 128;BA.debugLine="Sub button1_Click";
 //BA.debugLineNum = 129;BA.debugLine="a1.Start(button1)";
mostCurrent._vvvvv7.Start((android.view.View)(mostCurrent._button1.getObject()));
 //BA.debugLineNum = 130;BA.debugLine="a1.Start(Label1)";
mostCurrent._vvvvv7.Start((android.view.View)(mostCurrent._label1.getObject()));
 //BA.debugLineNum = 131;BA.debugLine="End Sub";
return "";
}
public static String  _button2_click() throws Exception{
 //BA.debugLineNum = 133;BA.debugLine="Sub button2_Click";
 //BA.debugLineNum = 134;BA.debugLine="a2.Start(button2)";
mostCurrent._vvvvv0.Start((android.view.View)(mostCurrent._button2.getObject()));
 //BA.debugLineNum = 135;BA.debugLine="a2.Start(Label2)";
mostCurrent._vvvvv0.Start((android.view.View)(mostCurrent._label2.getObject()));
 //BA.debugLineNum = 136;BA.debugLine="End Sub";
return "";
}
public static String  _button3_click() throws Exception{
 //BA.debugLineNum = 138;BA.debugLine="Sub button3_Click";
 //BA.debugLineNum = 139;BA.debugLine="a3.Start(button3)";
mostCurrent._vvvvvv1.Start((android.view.View)(mostCurrent._button3.getObject()));
 //BA.debugLineNum = 140;BA.debugLine="a3.Start(Label3)";
mostCurrent._vvvvvv1.Start((android.view.View)(mostCurrent._label3.getObject()));
 //BA.debugLineNum = 141;BA.debugLine="End Sub";
return "";
}
public static String  _button4_click() throws Exception{
 //BA.debugLineNum = 143;BA.debugLine="Sub button4_Click";
 //BA.debugLineNum = 144;BA.debugLine="a4.Start(button4)";
mostCurrent._vvvvvv2.Start((android.view.View)(mostCurrent._button4.getObject()));
 //BA.debugLineNum = 145;BA.debugLine="a4.Start(Label4)";
mostCurrent._vvvvvv2.Start((android.view.View)(mostCurrent._label4.getObject()));
 //BA.debugLineNum = 146;BA.debugLine="End Sub";
return "";
}
public static String  _button5_click() throws Exception{
 //BA.debugLineNum = 148;BA.debugLine="Sub button5_Click";
 //BA.debugLineNum = 149;BA.debugLine="a5.Start(button5)";
mostCurrent._vvvvvv3.Start((android.view.View)(mostCurrent._button5.getObject()));
 //BA.debugLineNum = 150;BA.debugLine="a5.Start(Label5)";
mostCurrent._vvvvvv3.Start((android.view.View)(mostCurrent._label5.getObject()));
 //BA.debugLineNum = 151;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvv6(anywheresoftware.b4a.objects.PanelWrapper _p,boolean _enabled) throws Exception{
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
 //BA.debugLineNum = 108;BA.debugLine="Sub EnableAll(p As Panel, Enabled As Boolean)";
 //BA.debugLineNum = 109;BA.debugLine="For Each v As View In p";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
final anywheresoftware.b4a.BA.IterableList group67 = _p;
final int groupLen67 = group67.getSize();
for (int index67 = 0;index67 < groupLen67 ;index67++){
_v.setObject((android.view.View)(group67.Get(index67)));
 //BA.debugLineNum = 110;BA.debugLine="If v Is Panel Then";
if (_v.getObjectOrNull() instanceof android.view.ViewGroup) { 
 //BA.debugLineNum = 111;BA.debugLine="EnableAll(v, Enabled)";
_vvvvvv6((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_v.getObject())),_enabled);
 }else {
 //BA.debugLineNum = 113;BA.debugLine="v.Enabled = Enabled";
_v.setEnabled(_enabled);
 };
 }
;
 //BA.debugLineNum = 116;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
act2._process_globals();
dbutils._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _globals() throws Exception{
 //BA.debugLineNum = 33;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 34;BA.debugLine="Dim a1,a2,a3,a4,a5 As Animation";
mostCurrent._vvvvv7 = new anywheresoftware.b4a.objects.AnimationWrapper();
mostCurrent._vvvvv0 = new anywheresoftware.b4a.objects.AnimationWrapper();
mostCurrent._vvvvvv1 = new anywheresoftware.b4a.objects.AnimationWrapper();
mostCurrent._vvvvvv2 = new anywheresoftware.b4a.objects.AnimationWrapper();
mostCurrent._vvvvvv3 = new anywheresoftware.b4a.objects.AnimationWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Dim welcomePanel As Panel";
mostCurrent._welcomepanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Dim homePanel As Panel";
mostCurrent._homepanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Dim button1 As ImageView";
mostCurrent._button1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Dim button2 As ImageView";
mostCurrent._button2 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Dim button3 As ImageView";
mostCurrent._button3 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Dim button4 As ImageView";
mostCurrent._button4 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Dim button5 As ImageView";
mostCurrent._button5 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Dim Label1 As Label";
mostCurrent._label1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Dim Label2 As Label";
mostCurrent._label2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Dim Label3 As Label";
mostCurrent._label3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Dim Label4 As Label";
mostCurrent._label4 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Dim Label5 As Label";
mostCurrent._label5 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 47;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 28;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 29;BA.debugLine="Dim PageURL, pageTitle As String";
_vvv7 = "";
_vvv0 = "";
 //BA.debugLineNum = 30;BA.debugLine="Dim welcomeTimer As Timer";
_vvvv1 = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 31;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvv5(anywheresoftware.b4a.objects.PanelWrapper _parent,anywheresoftware.b4a.keywords.constants.TypefaceWrapper _t) throws Exception{
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
 //BA.debugLineNum = 49;BA.debugLine="Sub SetTypeface(parent As Panel, t As Typeface)";
 //BA.debugLineNum = 50;BA.debugLine="For Each v As View In parent";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
final anywheresoftware.b4a.BA.IterableList group20 = _parent;
final int groupLen20 = group20.getSize();
for (int index20 = 0;index20 < groupLen20 ;index20++){
_v.setObject((android.view.View)(group20.Get(index20)));
 //BA.debugLineNum = 51;BA.debugLine="If v Is Panel Then";
if (_v.getObjectOrNull() instanceof android.view.ViewGroup) { 
 //BA.debugLineNum = 52;BA.debugLine="SetTypeface(v, t)";
_vvvvvv5((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_v.getObject())),_t);
 }else if(_v.getObjectOrNull() instanceof android.widget.TextView) { 
 //BA.debugLineNum = 54;BA.debugLine="Dim lbl As Label = v";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl.setObject((android.widget.TextView)(_v.getObject()));
 //BA.debugLineNum = 55;BA.debugLine="lbl.Typeface = t";
_lbl.setTypeface((android.graphics.Typeface)(_t.getObject()));
 };
 }
;
 //BA.debugLineNum = 58;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvv4(int _duration) throws Exception{
 //BA.debugLineNum = 87;BA.debugLine="Sub showWelcome(duration As Int)";
 //BA.debugLineNum = 88;BA.debugLine="EnableAll(homePanel,False)";
_vvvvvv6(mostCurrent._homepanel,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 89;BA.debugLine="welcomeTimer.Initialize(\"welTimer\",duration)";
_vvvv1.Initialize(processBA,"welTimer",(long) (_duration));
 //BA.debugLineNum = 90;BA.debugLine="welcomeTimer.Enabled = True";
_vvvv1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 91;BA.debugLine="End Sub";
return "";
}
public static String  _weltimer_tick() throws Exception{
 //BA.debugLineNum = 93;BA.debugLine="Sub welTimer_tick";
 //BA.debugLineNum = 94;BA.debugLine="welcomeTimer.Enabled = False";
_vvvv1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 95;BA.debugLine="welcomePanel.Visible = False";
mostCurrent._welcomepanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 96;BA.debugLine="homePanel.Visible = True";
mostCurrent._homepanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 97;BA.debugLine="EnableAll(homePanel,True)";
_vvvvvv6(mostCurrent._homepanel,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 98;BA.debugLine="End Sub";
return "";
}
}
