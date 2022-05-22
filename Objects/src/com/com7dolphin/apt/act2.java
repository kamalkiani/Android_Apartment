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

public class act2 extends Activity implements B4AActivity{
	public static act2 mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "com.com7dolphin.apt", "com.com7dolphin.apt.act2");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (act2).");
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
		activityBA = new BA(this, layout, processBA, "com.com7dolphin.apt", "com.com7dolphin.apt.act2");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "com.com7dolphin.apt.act2", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (act2) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (act2) Resume **");
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
		return act2.class;
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
        BA.LogInfo("** Activity (act2) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (act2) Resume **");
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
public static anywheresoftware.b4a.sql.SQL _v5 = null;
public static String _v6 = "";
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _vvvvvv7 = null;
public anywheresoftware.b4a.sql.SQL.CursorWrapper _vvvvvvvv4 = null;
public Object _vvvvvvvvvv5 = null;
public com.com7dolphin.apt.searchview _vvvvvvvvvv4 = null;
public anywheresoftware.b4a.objects.AnimationWrapper _vvvvvvvvv0 = null;
public anywheresoftware.b4a.objects.AnimationWrapper _vvvvvvvv7 = null;
public anywheresoftware.b4a.objects.AnimationWrapper _vvvvvvvvvv1 = null;
public anywheresoftware.b4a.objects.AnimationWrapper _vvvvvvvv6 = null;
public anywheresoftware.b4a.objects.AnimationWrapper _vvvvvvvvvv2 = null;
public anywheresoftware.b4a.objects.AnimationWrapper _vvvvvvvv0 = null;
public anywheresoftware.b4a.objects.PanelWrapper _rightmenu = null;
public anywheresoftware.b4a.objects.PanelWrapper _mainpanel = null;
public anywheresoftware.b4a.objects.PanelWrapper _blurpanel = null;
public static boolean _vvvvvvv3 = false;
public static boolean _vvvvvvv5 = false;
public anywheresoftware.b4a.objects.ListViewWrapper _toolslist = null;
public uk.co.martinpearman.b4a.webviewextras.WebViewExtras _vvvvvvv0 = null;
public static String _vvvvvvv7 = "";
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _vvvvvvvv5 = null;
public anywheresoftware.b4a.objects.WebViewWrapper _webview1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _titlelabel = null;
public anywheresoftware.b4a.objects.PanelWrapper _toppanel = null;
public anywheresoftware.b4a.objects.ListViewWrapper _vvvvvvvv3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _vvvvvvvvv1 = null;
public anywheresoftware.b4a.agraham.dialogs.InputDialog.CustomDialog2 _vvvvvvvvv7 = null;
public anywheresoftware.b4a.objects.PanelWrapper _vvvvvvvvv2 = null;
public static String _vvvvvvvvvv6 = "";
public anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper _vvvvvvvvv3 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper _vvvvvvvvv4 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper _vvvvvvvvv5 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper _vvvvvvvvv6 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _btmimg = null;
public com.com7dolphin.apt.main _vvvvv4 = null;
public com.com7dolphin.apt.dbutils _vvvvv6 = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 73;BA.debugLine="Sub Activity_Create (FirstTime As Boolean)";
 //BA.debugLineNum = 75;BA.debugLine="myFont= Typeface.LoadFromAssets(\"irsans.ttf\")";
mostCurrent._vvvvvv7.setObject((android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("irsans.ttf")));
 //BA.debugLineNum = 77;BA.debugLine="dir=DBUtils.CopyDBFromAssets(\"apt.db\")";
_v6 = mostCurrent._vvvvv6._v0(mostCurrent.activityBA,"apt.db");
 //BA.debugLineNum = 78;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 79;BA.debugLine="SQL1.Initialize(dir, \"apt.db\", False)";
_v5.Initialize(_v6,"apt.db",anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 82;BA.debugLine="Activity.LoadLayout(\"layMain\")";
mostCurrent._activity.LoadLayout("layMain",mostCurrent.activityBA);
 //BA.debugLineNum = 83;BA.debugLine="titleLabel.Text = Main.pageTitle";
mostCurrent._titlelabel.setText((Object)(mostCurrent._vvvvv4._vvv0));
 //BA.debugLineNum = 84;BA.debugLine="titleLabel.Typeface=myFont";
mostCurrent._titlelabel.setTypeface((android.graphics.Typeface)(mostCurrent._vvvvvv7.getObject()));
 //BA.debugLineNum = 85;BA.debugLine="WebView1.LoadUrl(\"file:///android_asset\" & Main.p";
mostCurrent._webview1.LoadUrl("file:///android_asset"+mostCurrent._vvvvv4._vvv7);
 //BA.debugLineNum = 86;BA.debugLine="createMenu";
_vvvvvv0();
 //BA.debugLineNum = 87;BA.debugLine="createFavList";
_vvvvvvv1();
 //BA.debugLineNum = 88;BA.debugLine="createFont";
_vvvvvvv2();
 //BA.debugLineNum = 90;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 268;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 269;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 270;BA.debugLine="If rightMenuIsOpen = True Then";
if (_vvvvvvv3==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 271;BA.debugLine="closeRightMenu";
_vvvvvvv4();
 //BA.debugLineNum = 272;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else if(_vvvvvvv5==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 274;BA.debugLine="closeTopPanel";
_vvvvvvv6();
 //BA.debugLineNum = 275;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else if(mostCurrent._webview1.getUrl().indexOf("apt")!=-1) { 
 //BA.debugLineNum = 277;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 }else if(mostCurrent._webview1.getUrl().indexOf("ex1")!=-1) { 
 //BA.debugLineNum = 279;BA.debugLine="WebView1.LoadUrl(\"file:///android_asset/data/ch";
mostCurrent._webview1.LoadUrl("file:///android_asset/data/ch1.html#menu");
 //BA.debugLineNum = 280;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else if(mostCurrent._webview1.getUrl().indexOf("ex2")!=-1) { 
 //BA.debugLineNum = 282;BA.debugLine="WebView1.LoadUrl(\"file:///android_asset/data/ch";
mostCurrent._webview1.LoadUrl("file:///android_asset/data/ch2.html#menu");
 //BA.debugLineNum = 283;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else if(mostCurrent._webview1.getUrl().indexOf("ex3")!=-1) { 
 //BA.debugLineNum = 285;BA.debugLine="WebView1.LoadUrl(\"file:///android_asset/data/ch";
mostCurrent._webview1.LoadUrl("file:///android_asset/data/ch3.html#menu");
 //BA.debugLineNum = 286;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else if(mostCurrent._webview1.getUrl().indexOf("ex4")!=-1) { 
 //BA.debugLineNum = 288;BA.debugLine="WebView1.LoadUrl(\"file:///android_asset/data/ch";
mostCurrent._webview1.LoadUrl("file:///android_asset/data/ch4.html#menu");
 //BA.debugLineNum = 289;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else if(mostCurrent._webview1.getUrl().indexOf("ex5")!=-1) { 
 //BA.debugLineNum = 291;BA.debugLine="WebView1.LoadUrl(\"file:///android_asset/data/ch";
mostCurrent._webview1.LoadUrl("file:///android_asset/data/ch5.html#menu");
 //BA.debugLineNum = 292;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else if((mostCurrent._webview1.getUrl().substring(mostCurrent._webview1.getUrl().indexOf("#"))).equals("#menu") == false) { 
 //BA.debugLineNum = 294;BA.debugLine="Javascript=\"document.location.href = '#menu'\"";
mostCurrent._vvvvvvv7 = "document.location.href = '#menu'";
 //BA.debugLineNum = 295;BA.debugLine="MyWebViewExtras.executeJavascript(WebView1, Jav";
mostCurrent._vvvvvvv0.executeJavascript((android.webkit.WebView)(mostCurrent._webview1.getObject()),mostCurrent._vvvvvvv7);
 //BA.debugLineNum = 296;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 };
 //BA.debugLineNum = 299;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 57;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 59;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 53;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 55;BA.debugLine="End Sub";
return "";
}
public static String  _blurpanel_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 214;BA.debugLine="Sub blurPanel_Touch (Action As Int, X As Float, Y";
 //BA.debugLineNum = 215;BA.debugLine="If Action = Activity.ACTION_DOWN Then";
if (_action==mostCurrent._activity.ACTION_DOWN) { 
 //BA.debugLineNum = 216;BA.debugLine="If rightMenuIsOpen Then";
if (_vvvvvvv3) { 
 //BA.debugLineNum = 217;BA.debugLine="closeRightMenu";
_vvvvvvv4();
 }else if(_vvvvvvv5) { 
 //BA.debugLineNum = 219;BA.debugLine="closeTopPanel";
_vvvvvvv6();
 };
 };
 //BA.debugLineNum = 222;BA.debugLine="End Sub";
return "";
}
public static String  _btmimg_click() throws Exception{
 //BA.debugLineNum = 399;BA.debugLine="Sub btmImg_Click";
 //BA.debugLineNum = 401;BA.debugLine="If Connected = False Then";
if (_vvvvvvvv1()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 402;BA.debugLine="ToastMessageShow(\"اتصال به اینترنت برقرار نیست\",";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("اتصال به اینترنت برقرار نیست",anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 404;BA.debugLine="WebView1.Height=90%y";
mostCurrent._webview1.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (90),mostCurrent.activityBA));
 //BA.debugLineNum = 405;BA.debugLine="btmImg.Visible=False";
mostCurrent._btmimg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 406;BA.debugLine="ToastMessageShow(\"لطفا تا بارگزاری صفحه منتظر بم";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("لطفا تا بارگزاری صفحه منتظر بمانید",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 407;BA.debugLine="WebView1.LoadUrl(\"http://ghanon-apt.ir/exam.aspx";
mostCurrent._webview1.LoadUrl("http://ghanon-apt.ir/exam.aspx");
 };
 //BA.debugLineNum = 409;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvvvv2() throws Exception{
String _str = "";
int _i = 0;
 //BA.debugLineNum = 319;BA.debugLine="Sub buildFav";
 //BA.debugLineNum = 320;BA.debugLine="Dim str As String";
_str = "";
 //BA.debugLineNum = 321;BA.debugLine="favList.Clear";
mostCurrent._vvvvvvvv3.Clear();
 //BA.debugLineNum = 322;BA.debugLine="Cursor1 = SQL1.ExecQuery(\"SELECT * FROM favs\")";
mostCurrent._vvvvvvvv4.setObject((android.database.Cursor)(_v5.ExecQuery("SELECT * FROM favs")));
 //BA.debugLineNum = 323;BA.debugLine="bitmap1.Initialize(File.DirAssets, \"fav.png\")";
mostCurrent._vvvvvvvv5.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"fav.png");
 //BA.debugLineNum = 324;BA.debugLine="For i = 0 To Cursor1.RowCount - 1";
{
final int step262 = 1;
final int limit262 = (int) (mostCurrent._vvvvvvvv4.getRowCount()-1);
for (_i = (int) (0); (step262 > 0 && _i <= limit262) || (step262 < 0 && _i >= limit262); _i = ((int)(0 + _i + step262))) {
 //BA.debugLineNum = 325;BA.debugLine="Cursor1.Position = i";
mostCurrent._vvvvvvvv4.setPosition(_i);
 //BA.debugLineNum = 326;BA.debugLine="str = Cursor1.GetString(\"favinfo\")";
_str = mostCurrent._vvvvvvvv4.GetString("favinfo");
 //BA.debugLineNum = 327;BA.debugLine="favList.AddTwoLinesAndBitmap2(str.SubString2(0,s";
mostCurrent._vvvvvvvv3.AddTwoLinesAndBitmap2(_str.substring((int) (0),_str.indexOf("-"))+": "+_str.substring((int) (_str.indexOf("#")+1)),"",(android.graphics.Bitmap)(mostCurrent._vvvvvvvv5.getObject()),(Object)(_str));
 }
};
 //BA.debugLineNum = 329;BA.debugLine="Cursor1.Close";
mostCurrent._vvvvvvvv4.Close();
 //BA.debugLineNum = 330;BA.debugLine="End Sub";
return "";
}
public static String  _closeblurpanelanim_animationend() throws Exception{
 //BA.debugLineNum = 186;BA.debugLine="Sub CloseBlurPanelAnim_AnimationEnd";
 //BA.debugLineNum = 187;BA.debugLine="blurPanel.Visible = False";
mostCurrent._blurpanel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 188;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvvv4() throws Exception{
 //BA.debugLineNum = 224;BA.debugLine="Sub closeRightMenu";
 //BA.debugLineNum = 225;BA.debugLine="rightMenuIsOpen = False";
_vvvvvvv3 = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 226;BA.debugLine="EnableAll(mainPanel, True)";
_vvvvvv6(mostCurrent._mainpanel,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 227;BA.debugLine="anCloseBlurPanel.Start(blurPanel)";
mostCurrent._vvvvvvvv6.Start((android.view.View)(mostCurrent._blurpanel.getObject()));
 //BA.debugLineNum = 228;BA.debugLine="anCloseRightMenu.Start(rightMenu)";
mostCurrent._vvvvvvvv7.Start((android.view.View)(mostCurrent._rightmenu.getObject()));
 //BA.debugLineNum = 229;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvvv6() throws Exception{
 //BA.debugLineNum = 239;BA.debugLine="Sub closeTopPanel";
 //BA.debugLineNum = 240;BA.debugLine="topPanelIsOpen = False";
_vvvvvvv5 = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 241;BA.debugLine="EnableAll(mainPanel, True)";
_vvvvvv6(mostCurrent._mainpanel,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 242;BA.debugLine="anCloseBlurPanel.Start(blurPanel)";
mostCurrent._vvvvvvvv6.Start((android.view.View)(mostCurrent._blurpanel.getObject()));
 //BA.debugLineNum = 243;BA.debugLine="anCloseTopPanel.Start(topPanel)";
mostCurrent._vvvvvvvv0.Start((android.view.View)(mostCurrent._toppanel.getObject()));
 //BA.debugLineNum = 244;BA.debugLine="End Sub";
return "";
}
public static boolean  _vvvvvvvv1() throws Exception{
anywheresoftware.b4a.phone.Phone _p = null;
anywheresoftware.b4a.keywords.StringBuilderWrapper _response = null;
anywheresoftware.b4a.keywords.StringBuilderWrapper _error = null;
 //BA.debugLineNum = 411;BA.debugLine="Sub Connected As Boolean";
 //BA.debugLineNum = 412;BA.debugLine="Dim p As Phone";
_p = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 413;BA.debugLine="Dim Response, Error As StringBuilder";
_response = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
_error = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 414;BA.debugLine="Response.Initialize";
_response.Initialize();
 //BA.debugLineNum = 415;BA.debugLine="Error.Initialize";
_error.Initialize();
 //BA.debugLineNum = 416;BA.debugLine="p.Shell(\"ping -c 1 8.8.8.8\",Null,Response,Erro";
_p.Shell("ping -c 1 8.8.8.8",(String[])(anywheresoftware.b4a.keywords.Common.Null),(java.lang.StringBuilder)(_response.getObject()),(java.lang.StringBuilder)(_error.getObject()));
 //BA.debugLineNum = 417;BA.debugLine="If Error.ToString=\"\" Then";
if ((_error.ToString()).equals("")) { 
 //BA.debugLineNum = 418;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 420;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 422;BA.debugLine="End Sub";
return false;
}
public static String  _vvvvvvv1() throws Exception{
 //BA.debugLineNum = 128;BA.debugLine="Sub createFavList";
 //BA.debugLineNum = 129;BA.debugLine="favTitle.Initialize(\"favTitle\")";
mostCurrent._vvvvvvvvv1.Initialize(mostCurrent.activityBA,"favTitle");
 //BA.debugLineNum = 130;BA.debugLine="favTitle.Text=\"جهت حذف از کلیک مدت دار استفاده کن";
mostCurrent._vvvvvvvvv1.setText((Object)("جهت حذف از کلیک مدت دار استفاده کنید"));
 //BA.debugLineNum = 131;BA.debugLine="favTitle.TextColor = Colors.White";
mostCurrent._vvvvvvvvv1.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 132;BA.debugLine="favTitle.TextSize = 14";
mostCurrent._vvvvvvvvv1.setTextSize((float) (14));
 //BA.debugLineNum = 133;BA.debugLine="favTitle.Typeface=myFont";
mostCurrent._vvvvvvvvv1.setTypeface((android.graphics.Typeface)(mostCurrent._vvvvvv7.getObject()));
 //BA.debugLineNum = 134;BA.debugLine="favTitle.Gravity=Gravity.CENTER";
mostCurrent._vvvvvvvvv1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 135;BA.debugLine="favList.Initialize(\"favList\")";
mostCurrent._vvvvvvvv3.Initialize(mostCurrent.activityBA,"favList");
 //BA.debugLineNum = 136;BA.debugLine="favList.TwoLinesAndBitmap.ImageView.SetLayout(84%";
mostCurrent._vvvvvvvv3.getTwoLinesAndBitmap().ImageView.SetLayout(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (84),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (1),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (15),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (15),mostCurrent.activityBA));
 //BA.debugLineNum = 137;BA.debugLine="favList.TwoLinesAndBitmap.ItemHeight = 17%x";
mostCurrent._vvvvvvvv3.getTwoLinesAndBitmap().setItemHeight(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (17),mostCurrent.activityBA));
 //BA.debugLineNum = 138;BA.debugLine="favList.TwoLinesAndBitmap.Label.TextColor = Color";
mostCurrent._vvvvvvvv3.getTwoLinesAndBitmap().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 139;BA.debugLine="favList.TwoLinesAndBitmap.Label.Gravity = Gravity";
mostCurrent._vvvvvvvv3.getTwoLinesAndBitmap().Label.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.RIGHT);
 //BA.debugLineNum = 140;BA.debugLine="favList.TwoLinesAndBitmap.Label.SetLayout(0, 4%x,";
mostCurrent._vvvvvvvv3.getTwoLinesAndBitmap().Label.SetLayout((int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (4),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (80),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (10),mostCurrent.activityBA));
 //BA.debugLineNum = 141;BA.debugLine="favList.TwoLinesAndBitmap.Label.Typeface=myFont";
mostCurrent._vvvvvvvv3.getTwoLinesAndBitmap().Label.setTypeface((android.graphics.Typeface)(mostCurrent._vvvvvv7.getObject()));
 //BA.debugLineNum = 142;BA.debugLine="favList.TwoLinesAndBitmap.Label.TextSize=18";
mostCurrent._vvvvvvvv3.getTwoLinesAndBitmap().Label.setTextSize((float) (18));
 //BA.debugLineNum = 143;BA.debugLine="favList.Color = Colors.Transparent";
mostCurrent._vvvvvvvv3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 144;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvvv2() throws Exception{
 //BA.debugLineNum = 92;BA.debugLine="Sub createFont";
 //BA.debugLineNum = 93;BA.debugLine="pnl.Initialize(\"pnl\")";
mostCurrent._vvvvvvvvv2.Initialize(mostCurrent.activityBA,"pnl");
 //BA.debugLineNum = 94;BA.debugLine="pnl.Color = Colors.Transparent";
mostCurrent._vvvvvvvvv2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 95;BA.debugLine="s1.Initialize(\"s\")";
mostCurrent._vvvvvvvvv3.Initialize(mostCurrent.activityBA,"s");
 //BA.debugLineNum = 96;BA.debugLine="s2.Initialize(\"s\")";
mostCurrent._vvvvvvvvv4.Initialize(mostCurrent.activityBA,"s");
 //BA.debugLineNum = 97;BA.debugLine="s3.Initialize(\"s\")";
mostCurrent._vvvvvvvvv5.Initialize(mostCurrent.activityBA,"s");
 //BA.debugLineNum = 98;BA.debugLine="s4.Initialize(\"s\")";
mostCurrent._vvvvvvvvv6.Initialize(mostCurrent.activityBA,"s");
 //BA.debugLineNum = 99;BA.debugLine="s1.Text = \"کوچک\"";
mostCurrent._vvvvvvvvv3.setText((Object)("کوچک"));
 //BA.debugLineNum = 100;BA.debugLine="s2.Text = \"متوسط\"";
mostCurrent._vvvvvvvvv4.setText((Object)("متوسط"));
 //BA.debugLineNum = 101;BA.debugLine="s3.Text = \"بزرگ\"";
mostCurrent._vvvvvvvvv5.setText((Object)("بزرگ"));
 //BA.debugLineNum = 102;BA.debugLine="s4.Text = \"خیلی بزرگ\"";
mostCurrent._vvvvvvvvv6.setText((Object)("خیلی بزرگ"));
 //BA.debugLineNum = 103;BA.debugLine="s1.Typeface=myFont";
mostCurrent._vvvvvvvvv3.setTypeface((android.graphics.Typeface)(mostCurrent._vvvvvv7.getObject()));
 //BA.debugLineNum = 104;BA.debugLine="s2.Typeface=myFont";
mostCurrent._vvvvvvvvv4.setTypeface((android.graphics.Typeface)(mostCurrent._vvvvvv7.getObject()));
 //BA.debugLineNum = 105;BA.debugLine="s3.Typeface=myFont";
mostCurrent._vvvvvvvvv5.setTypeface((android.graphics.Typeface)(mostCurrent._vvvvvv7.getObject()));
 //BA.debugLineNum = 106;BA.debugLine="s4.Typeface=myFont";
mostCurrent._vvvvvvvvv6.setTypeface((android.graphics.Typeface)(mostCurrent._vvvvvv7.getObject()));
 //BA.debugLineNum = 107;BA.debugLine="pnl.AddView(s1,0,0,50%x,10%y)";
mostCurrent._vvvvvvvvv2.AddView((android.view.View)(mostCurrent._vvvvvvvvv3.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (50),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (10),mostCurrent.activityBA));
 //BA.debugLineNum = 108;BA.debugLine="pnl.AddView(s2,0,10%y,50%x,10%y)";
mostCurrent._vvvvvvvvv2.AddView((android.view.View)(mostCurrent._vvvvvvvvv4.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (10),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (50),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (10),mostCurrent.activityBA));
 //BA.debugLineNum = 109;BA.debugLine="pnl.AddView(s3,0,20%y,50%x,10%y)";
mostCurrent._vvvvvvvvv2.AddView((android.view.View)(mostCurrent._vvvvvvvvv5.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (20),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (50),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (10),mostCurrent.activityBA));
 //BA.debugLineNum = 110;BA.debugLine="pnl.AddView(s4,0,30%y,50%x,10%y)";
mostCurrent._vvvvvvvvv2.AddView((android.view.View)(mostCurrent._vvvvvvvvv6.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (30),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (50),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (10),mostCurrent.activityBA));
 //BA.debugLineNum = 111;BA.debugLine="cd.AddView(pnl, 55%x, 40%y)";
mostCurrent._vvvvvvvvv7.AddView((android.view.View)(mostCurrent._vvvvvvvvv2.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (55),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (40),mostCurrent.activityBA));
 //BA.debugLineNum = 112;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvv0() throws Exception{
 //BA.debugLineNum = 150;BA.debugLine="Sub createMenu";
 //BA.debugLineNum = 151;BA.debugLine="anOpenRightMenu.InitializeTranslate(\"RightMenuAni";
mostCurrent._vvvvvvvvv0.InitializeTranslate(mostCurrent.activityBA,"RightMenuAnim",(float) (0),(float) (0),(float) (-anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (70),mostCurrent.activityBA)),(float) (0));
 //BA.debugLineNum = 152;BA.debugLine="anOpenRightMenu.Duration = 300";
mostCurrent._vvvvvvvvv0.setDuration((long) (300));
 //BA.debugLineNum = 153;BA.debugLine="anCloseRightMenu.InitializeTranslate(\"RightMenuAn";
mostCurrent._vvvvvvvv7.InitializeTranslate(mostCurrent.activityBA,"RightMenuAnim",(float) (0),(float) (0),(float) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (70),mostCurrent.activityBA)),(float) (0));
 //BA.debugLineNum = 154;BA.debugLine="anCloseRightMenu.Duration = 300";
mostCurrent._vvvvvvvv7.setDuration((long) (300));
 //BA.debugLineNum = 155;BA.debugLine="anOpenBlurPanel.InitializeAlpha(\"OpenBlurPanelAni";
mostCurrent._vvvvvvvvvv1.InitializeAlpha(mostCurrent.activityBA,"OpenBlurPanelAnim",(float) (0),(float) (1));
 //BA.debugLineNum = 156;BA.debugLine="anOpenBlurPanel.Duration = 300";
mostCurrent._vvvvvvvvvv1.setDuration((long) (300));
 //BA.debugLineNum = 157;BA.debugLine="anCloseBlurPanel.InitializeAlpha(\"CloseBlurPanelA";
mostCurrent._vvvvvvvv6.InitializeAlpha(mostCurrent.activityBA,"CloseBlurPanelAnim",(float) (1),(float) (0));
 //BA.debugLineNum = 158;BA.debugLine="anCloseBlurPanel.Duration = 300";
mostCurrent._vvvvvvvv6.setDuration((long) (300));
 //BA.debugLineNum = 159;BA.debugLine="anOpenTopPanel.InitializeTranslate(\"TopPanelAnim\"";
mostCurrent._vvvvvvvvvv2.InitializeTranslate(mostCurrent.activityBA,"TopPanelAnim",(float) (0),(float) (0),(float) (0),(float) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (50),mostCurrent.activityBA)));
 //BA.debugLineNum = 160;BA.debugLine="anOpenTopPanel.Duration = 300";
mostCurrent._vvvvvvvvvv2.setDuration((long) (300));
 //BA.debugLineNum = 161;BA.debugLine="anCloseTopPanel.InitializeTranslate(\"TopPanelAnim";
mostCurrent._vvvvvvvv0.InitializeTranslate(mostCurrent.activityBA,"TopPanelAnim",(float) (0),(float) (0),(float) (0),(float) (-anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (50),mostCurrent.activityBA)));
 //BA.debugLineNum = 162;BA.debugLine="anCloseTopPanel.Duration = 300";
mostCurrent._vvvvvvvv0.setDuration((long) (300));
 //BA.debugLineNum = 163;BA.debugLine="rightMenuIsOpen = False";
_vvvvvvv3 = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 164;BA.debugLine="topPanelIsOpen = False";
_vvvvvvv5 = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 166;BA.debugLine="toolsList.TwoLinesAndBitmap.ImageView.SetLayout(5";
mostCurrent._toolslist.getTwoLinesAndBitmap().ImageView.SetLayout(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (55),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (1),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (13),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (13),mostCurrent.activityBA));
 //BA.debugLineNum = 167;BA.debugLine="toolsList.TwoLinesAndBitmap.ImageView.Gravity = G";
mostCurrent._toolslist.getTwoLinesAndBitmap().ImageView.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 168;BA.debugLine="toolsList.TwoLinesAndBitmap.ItemHeight = 15%x";
mostCurrent._toolslist.getTwoLinesAndBitmap().setItemHeight(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (15),mostCurrent.activityBA));
 //BA.debugLineNum = 169;BA.debugLine="toolsList.TwoLinesAndBitmap.Label.TextColor = Col";
mostCurrent._toolslist.getTwoLinesAndBitmap().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 170;BA.debugLine="toolsList.TwoLinesAndBitmap.Label.Gravity = Gravi";
mostCurrent._toolslist.getTwoLinesAndBitmap().Label.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 171;BA.debugLine="toolsList.TwoLinesAndBitmap.Label.SetLayout(0, 1%";
mostCurrent._toolslist.getTwoLinesAndBitmap().Label.SetLayout((int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (1),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (54),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (13),mostCurrent.activityBA));
 //BA.debugLineNum = 172;BA.debugLine="toolsList.TwoLinesAndBitmap.Label.Typeface=myFont";
mostCurrent._toolslist.getTwoLinesAndBitmap().Label.setTypeface((android.graphics.Typeface)(mostCurrent._vvvvvv7.getObject()));
 //BA.debugLineNum = 173;BA.debugLine="toolsList.TwoLinesAndBitmap.Label.TextSize = 18";
mostCurrent._toolslist.getTwoLinesAndBitmap().Label.setTextSize((float) (18));
 //BA.debugLineNum = 174;BA.debugLine="toolsList.AddTwoLinesAndBitmap2(\"می پسندم\", \"\", L";
mostCurrent._toolslist.AddTwoLinesAndBitmap2("می پسندم","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"addfav.png").getObject()),(Object)("addfav"));
 //BA.debugLineNum = 175;BA.debugLine="toolsList.AddTwoLinesAndBitmap2(\"علاقه مندی\", \"\",";
mostCurrent._toolslist.AddTwoLinesAndBitmap2("علاقه مندی","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"fav.png").getObject()),(Object)("fav"));
 //BA.debugLineNum = 176;BA.debugLine="toolsList.AddTwoLinesAndBitmap2(\"اندازه قلم\", \"\",";
mostCurrent._toolslist.AddTwoLinesAndBitmap2("اندازه قلم","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"font.png").getObject()),(Object)("font"));
 //BA.debugLineNum = 177;BA.debugLine="toolsList.AddTwoLinesAndBitmap2(\"اشتراک\", \"\", Loa";
mostCurrent._toolslist.AddTwoLinesAndBitmap2("اشتراک","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"share.png").getObject()),(Object)("share"));
 //BA.debugLineNum = 179;BA.debugLine="toolsList.AddTwoLinesAndBitmap2(\"درباره\", \"\", Loa";
mostCurrent._toolslist.AddTwoLinesAndBitmap2("درباره","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"about.png").getObject()),(Object)("about"));
 //BA.debugLineNum = 180;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvvvvvv3() throws Exception{
anywheresoftware.b4a.objects.collections.List _ind = null;
int _i = 0;
 //BA.debugLineNum = 114;BA.debugLine="Sub createSerch";
 //BA.debugLineNum = 115;BA.debugLine="sv.Initialize(Me, \"sv\")";
mostCurrent._vvvvvvvvvv4._initialize(mostCurrent.activityBA,act2.getObject(),"sv");
 //BA.debugLineNum = 116;BA.debugLine="Dim ind As List";
_ind = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 117;BA.debugLine="ind.Initialize";
_ind.Initialize();
 //BA.debugLineNum = 118;BA.debugLine="Cursor1 = SQL1.ExecQuery(\"SELECT ind FROM search\"";
mostCurrent._vvvvvvvv4.setObject((android.database.Cursor)(_v5.ExecQuery("SELECT ind FROM search")));
 //BA.debugLineNum = 119;BA.debugLine="For i = 0 To Cursor1.RowCount - 1";
{
final int step85 = 1;
final int limit85 = (int) (mostCurrent._vvvvvvvv4.getRowCount()-1);
for (_i = (int) (0); (step85 > 0 && _i <= limit85) || (step85 < 0 && _i >= limit85); _i = ((int)(0 + _i + step85))) {
 //BA.debugLineNum = 120;BA.debugLine="Cursor1.Position = i";
mostCurrent._vvvvvvvv4.setPosition(_i);
 //BA.debugLineNum = 121;BA.debugLine="ind.Add(Cursor1.GetString(\"ind\"))";
_ind.Add((Object)(mostCurrent._vvvvvvvv4.GetString("ind")));
 }
};
 //BA.debugLineNum = 123;BA.debugLine="Cursor1.Close";
mostCurrent._vvvvvvvv4.Close();
 //BA.debugLineNum = 124;BA.debugLine="index = sv.SetItems(ind)";
mostCurrent._vvvvvvvvvv5 = mostCurrent._vvvvvvvvvv4._vvvv5(_ind);
 //BA.debugLineNum = 125;BA.debugLine="sv.SetIndex(index)";
mostCurrent._vvvvvvvvvv4._vvvv4(mostCurrent._vvvvvvvvvv5);
 //BA.debugLineNum = 126;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvv6(anywheresoftware.b4a.objects.PanelWrapper _p,boolean _enabled) throws Exception{
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
 //BA.debugLineNum = 258;BA.debugLine="Sub EnableAll(p As Panel, Enabled As Boolean)";
 //BA.debugLineNum = 259;BA.debugLine="For Each v As View In p";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
final anywheresoftware.b4a.BA.IterableList group201 = _p;
final int groupLen201 = group201.getSize();
for (int index201 = 0;index201 < groupLen201 ;index201++){
_v.setObject((android.view.View)(group201.Get(index201)));
 //BA.debugLineNum = 260;BA.debugLine="If v Is Panel Then";
if (_v.getObjectOrNull() instanceof android.view.ViewGroup) { 
 //BA.debugLineNum = 261;BA.debugLine="EnableAll(v, Enabled)";
_vvvvvv6((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_v.getObject())),_enabled);
 }else {
 //BA.debugLineNum = 263;BA.debugLine="v.Enabled = Enabled";
_v.setEnabled(_enabled);
 };
 }
;
 //BA.debugLineNum = 266;BA.debugLine="End Sub";
return "";
}
public static String  _favlist_itemclick(int _position,Object _value) throws Exception{
String _str = "";
String _pageurl = "";
 //BA.debugLineNum = 301;BA.debugLine="Sub favList_ItemClick (Position As Int, Value As O";
 //BA.debugLineNum = 302;BA.debugLine="Dim str, pageURL As String";
_str = "";
_pageurl = "";
 //BA.debugLineNum = 303;BA.debugLine="str = favList.GetItem(Position)";
_str = BA.ObjectToString(mostCurrent._vvvvvvvv3.GetItem(_position));
 //BA.debugLineNum = 304;BA.debugLine="titleLabel.Text = str.SubString2(0,str.IndexOf(\"-";
mostCurrent._titlelabel.setText((Object)(_str.substring((int) (0),_str.indexOf("-"))));
 //BA.debugLineNum = 305;BA.debugLine="pageURL = str.SubString(str.IndexOf(\"-\")+1)";
_pageurl = _str.substring((int) (_str.indexOf("-")+1));
 //BA.debugLineNum = 306;BA.debugLine="WebView1.LoadUrl(pageURL)";
mostCurrent._webview1.LoadUrl(_pageurl);
 //BA.debugLineNum = 307;BA.debugLine="closeTopPanel";
_vvvvvvv6();
 //BA.debugLineNum = 308;BA.debugLine="End Sub";
return "";
}
public static String  _favlist_itemlongclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 310;BA.debugLine="Sub favList_ItemLongClick (Position As Int, Value";
 //BA.debugLineNum = 311;BA.debugLine="If Msgbox2(\"از لیست حذف شود؟\",\"توجه\", \"بلی\", \"\",";
if (anywheresoftware.b4a.keywords.Common.Msgbox2("از لیست حذف شود؟","توجه","بلی","","خیر",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA)==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 312;BA.debugLine="SQL1.ExecNonQuery(\"delete from favs where favinf";
_v5.ExecNonQuery("delete from favs where favinfo = '"+BA.ObjectToString(mostCurrent._vvvvvvvv3.GetItem(_position))+"'");
 //BA.debugLineNum = 313;BA.debugLine="buildFav";
_vvvvvvvv2();
 }else {
 //BA.debugLineNum = 315;BA.debugLine="Return True";
if (true) return BA.ObjectToString(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 317;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 23;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 24;BA.debugLine="Dim myFont As Typeface";
mostCurrent._vvvvvv7 = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Dim Cursor1 As Cursor";
mostCurrent._vvvvvvvv4 = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Dim index As Object";
mostCurrent._vvvvvvvvvv5 = new Object();
 //BA.debugLineNum = 27;BA.debugLine="Dim sv As SearchView";
mostCurrent._vvvvvvvvvv4 = new com.com7dolphin.apt.searchview();
 //BA.debugLineNum = 29;BA.debugLine="Dim anOpenRightMenu, anCloseRightMenu As Animatio";
mostCurrent._vvvvvvvvv0 = new anywheresoftware.b4a.objects.AnimationWrapper();
mostCurrent._vvvvvvvv7 = new anywheresoftware.b4a.objects.AnimationWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Dim anOpenBlurPanel, anCloseBlurPanel As Animatio";
mostCurrent._vvvvvvvvvv1 = new anywheresoftware.b4a.objects.AnimationWrapper();
mostCurrent._vvvvvvvv6 = new anywheresoftware.b4a.objects.AnimationWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Dim anOpenTopPanel, anCloseTopPanel As Animation";
mostCurrent._vvvvvvvvvv2 = new anywheresoftware.b4a.objects.AnimationWrapper();
mostCurrent._vvvvvvvv0 = new anywheresoftware.b4a.objects.AnimationWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Dim rightMenu As Panel";
mostCurrent._rightmenu = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Dim mainPanel As Panel";
mostCurrent._mainpanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Dim blurPanel As Panel";
mostCurrent._blurpanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Dim rightMenuIsOpen, topPanelIsOpen As Boolean";
_vvvvvvv3 = false;
_vvvvvvv5 = false;
 //BA.debugLineNum = 36;BA.debugLine="Dim toolsList As ListView";
mostCurrent._toolslist = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Dim MyWebViewExtras As WebViewExtras";
mostCurrent._vvvvvvv0 = new uk.co.martinpearman.b4a.webviewextras.WebViewExtras();
 //BA.debugLineNum = 39;BA.debugLine="Dim Javascript As String";
mostCurrent._vvvvvvv7 = "";
 //BA.debugLineNum = 40;BA.debugLine="Dim bitmap1 As Bitmap";
mostCurrent._vvvvvvvv5 = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Dim WebView1 As WebView";
mostCurrent._webview1 = new anywheresoftware.b4a.objects.WebViewWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Dim titleLabel As Label";
mostCurrent._titlelabel = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Dim topPanel As Panel";
mostCurrent._toppanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Dim favList As ListView";
mostCurrent._vvvvvvvv3 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Dim favTitle As Label";
mostCurrent._vvvvvvvvv1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Dim cd As CustomDialog2";
mostCurrent._vvvvvvvvv7 = new anywheresoftware.b4a.agraham.dialogs.InputDialog.CustomDialog2();
 //BA.debugLineNum = 47;BA.debugLine="Dim pnl As Panel";
mostCurrent._vvvvvvvvv2 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 48;BA.debugLine="Dim fontSize As String = \"1em\"";
mostCurrent._vvvvvvvvvv6 = "1em";
 //BA.debugLineNum = 49;BA.debugLine="Dim s1,s2,s3,s4 As RadioButton";
mostCurrent._vvvvvvvvv3 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper();
mostCurrent._vvvvvvvvv4 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper();
mostCurrent._vvvvvvvvv5 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper();
mostCurrent._vvvvvvvvv6 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private btmImg As ImageView";
mostCurrent._btmimg = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 51;BA.debugLine="End Sub";
return "";
}
public static String  _homeimg_click() throws Exception{
 //BA.debugLineNum = 210;BA.debugLine="Sub homeImg_Click";
 //BA.debugLineNum = 211;BA.debugLine="Activity.finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 212;BA.debugLine="End Sub";
return "";
}
public static String  _menuimg_click() throws Exception{
 //BA.debugLineNum = 206;BA.debugLine="Sub menuImg_Click";
 //BA.debugLineNum = 207;BA.debugLine="openRightMenu";
_vvvvvvvvvv7();
 //BA.debugLineNum = 208;BA.debugLine="End Sub";
return "";
}
public static String  _openblurpanelanim_animationend() throws Exception{
 //BA.debugLineNum = 182;BA.debugLine="Sub OpenBlurPanelAnim_AnimationEnd";
 //BA.debugLineNum = 183;BA.debugLine="blurPanel.Visible = True";
mostCurrent._blurpanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 184;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvvvvvv7() throws Exception{
 //BA.debugLineNum = 231;BA.debugLine="Sub openRightMenu";
 //BA.debugLineNum = 232;BA.debugLine="rightMenuIsOpen = True";
_vvvvvvv3 = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 233;BA.debugLine="EnableAll(mainPanel, False)";
_vvvvvv6(mostCurrent._mainpanel,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 234;BA.debugLine="blurPanel.Visible = True";
mostCurrent._blurpanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 235;BA.debugLine="anOpenBlurPanel.Start(blurPanel)";
mostCurrent._vvvvvvvvvv1.Start((android.view.View)(mostCurrent._blurpanel.getObject()));
 //BA.debugLineNum = 236;BA.debugLine="anOpenRightMenu.Start(rightMenu)";
mostCurrent._vvvvvvvvv0.Start((android.view.View)(mostCurrent._rightmenu.getObject()));
 //BA.debugLineNum = 237;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvvvvvv0() throws Exception{
 //BA.debugLineNum = 246;BA.debugLine="Sub openTopPanel";
 //BA.debugLineNum = 247;BA.debugLine="topPanelIsOpen = True";
_vvvvvvv5 = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 248;BA.debugLine="EnableAll(mainPanel, False)";
_vvvvvv6(mostCurrent._mainpanel,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 249;BA.debugLine="blurPanel.Visible = True";
mostCurrent._blurpanel.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 250;BA.debugLine="anOpenBlurPanel.Start(blurPanel)";
mostCurrent._vvvvvvvvvv1.Start((android.view.View)(mostCurrent._blurpanel.getObject()));
 //BA.debugLineNum = 251;BA.debugLine="anOpenTopPanel.Start(topPanel)";
mostCurrent._vvvvvvvvvv2.Start((android.view.View)(mostCurrent._toppanel.getObject()));
 //BA.debugLineNum = 252;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 18;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 19;BA.debugLine="Dim SQL1 As SQL";
_v5 = new anywheresoftware.b4a.sql.SQL();
 //BA.debugLineNum = 20;BA.debugLine="Dim dir As String";
_v6 = "";
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return "";
}
public static String  _rightmenuanim_animationend() throws Exception{
 //BA.debugLineNum = 190;BA.debugLine="Sub rightMenuAnim_AnimationEnd";
 //BA.debugLineNum = 191;BA.debugLine="If Sender = anOpenRightMenu Then";
if ((anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)).equals((Object)(mostCurrent._vvvvvvvvv0.getObject()))) { 
 //BA.debugLineNum = 192;BA.debugLine="rightMenu.left = 30%x";
mostCurrent._rightmenu.setLeft(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (30),mostCurrent.activityBA));
 }else if((anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)).equals((Object)(mostCurrent._vvvvvvvv7.getObject()))) { 
 //BA.debugLineNum = 194;BA.debugLine="rightMenu.left = 100%x";
mostCurrent._rightmenu.setLeft(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA));
 };
 //BA.debugLineNum = 196;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvv5(anywheresoftware.b4a.objects.PanelWrapper _parent,anywheresoftware.b4a.keywords.constants.TypefaceWrapper _t) throws Exception{
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
 //BA.debugLineNum = 61;BA.debugLine="Sub SetTypeface(parent As Panel, t As Typeface)";
 //BA.debugLineNum = 62;BA.debugLine="For Each v As View In parent";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
final anywheresoftware.b4a.BA.IterableList group36 = _parent;
final int groupLen36 = group36.getSize();
for (int index36 = 0;index36 < groupLen36 ;index36++){
_v.setObject((android.view.View)(group36.Get(index36)));
 //BA.debugLineNum = 63;BA.debugLine="If v Is Panel Then";
if (_v.getObjectOrNull() instanceof android.view.ViewGroup) { 
 //BA.debugLineNum = 64;BA.debugLine="SetTypeface(v, t)";
_vvvvvv5((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_v.getObject())),_t);
 }else if(_v.getObjectOrNull() instanceof android.widget.TextView) { 
 //BA.debugLineNum = 66;BA.debugLine="Dim lbl As Label = v";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl.setObject((android.widget.TextView)(_v.getObject()));
 //BA.debugLineNum = 67;BA.debugLine="lbl.Typeface = t";
_lbl.setTypeface((android.graphics.Typeface)(_t.getObject()));
 };
 }
;
 //BA.debugLineNum = 70;BA.debugLine="End Sub";
return "";
}
public static String  _sv_itemclick(String _value) throws Exception{
String _str = "";
String _pageurl = "";
 //BA.debugLineNum = 386;BA.debugLine="Sub sv_ItemClick(Value As String)";
 //BA.debugLineNum = 387;BA.debugLine="Dim str, pageURL As String";
_str = "";
_pageurl = "";
 //BA.debugLineNum = 388;BA.debugLine="Cursor1 = SQL1.ExecQuery(\"SELECT page FROM search";
mostCurrent._vvvvvvvv4.setObject((android.database.Cursor)(_v5.ExecQuery("SELECT page FROM search where ind = '"+_value+"'")));
 //BA.debugLineNum = 389;BA.debugLine="Cursor1.Position = 0";
mostCurrent._vvvvvvvv4.setPosition((int) (0));
 //BA.debugLineNum = 390;BA.debugLine="str = Cursor1.GetString(\"page\")";
_str = mostCurrent._vvvvvvvv4.GetString("page");
 //BA.debugLineNum = 391;BA.debugLine="Cursor1.Close";
mostCurrent._vvvvvvvv4.Close();
 //BA.debugLineNum = 393;BA.debugLine="titleLabel.Text = str.SubString2(0,str.IndexOf(\"-";
mostCurrent._titlelabel.setText((Object)(_str.substring((int) (0),_str.indexOf("-"))));
 //BA.debugLineNum = 394;BA.debugLine="pageURL = str.SubString(str.IndexOf(\"-\")+1)";
_pageurl = _str.substring((int) (_str.indexOf("-")+1));
 //BA.debugLineNum = 395;BA.debugLine="WebView1.LoadUrl(pageURL)";
mostCurrent._webview1.LoadUrl(_pageurl);
 //BA.debugLineNum = 396;BA.debugLine="closeTopPanel";
_vvvvvvv6();
 //BA.debugLineNum = 397;BA.debugLine="End Sub";
return "";
}
public static String  _toolslist_itemclick(int _position,Object _value) throws Exception{
String _myurl = "";
anywheresoftware.b4a.objects.IntentWrapper _share1 = null;
int _ret = 0;
 //BA.debugLineNum = 332;BA.debugLine="Sub toolsList_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 333;BA.debugLine="If Value = \"fav\" Then";
if ((_value).equals((Object)("fav"))) { 
 //BA.debugLineNum = 335;BA.debugLine="closeRightMenu";
_vvvvvvv4();
 //BA.debugLineNum = 336;BA.debugLine="topPanel.RemoveAllViews";
mostCurrent._toppanel.RemoveAllViews();
 //BA.debugLineNum = 337;BA.debugLine="topPanel.AddView(favTitle,2%x,1%x,96%x,7%y)";
mostCurrent._toppanel.AddView((android.view.View)(mostCurrent._vvvvvvvvv1.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (2),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (1),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (96),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (7),mostCurrent.activityBA));
 //BA.debugLineNum = 338;BA.debugLine="topPanel.AddView(favList,0,7%y,100%x,43%y)";
mostCurrent._toppanel.AddView((android.view.View)(mostCurrent._vvvvvvvv3.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (7),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (43),mostCurrent.activityBA));
 //BA.debugLineNum = 339;BA.debugLine="buildFav";
_vvvvvvvv2();
 //BA.debugLineNum = 340;BA.debugLine="openTopPanel";
_vvvvvvvvvv0();
 }else if((_value).equals((Object)("addfav"))) { 
 //BA.debugLineNum = 343;BA.debugLine="closeRightMenu";
_vvvvvvv4();
 //BA.debugLineNum = 344;BA.debugLine="Dim myUrl As String = WebView1.Url";
_myurl = mostCurrent._webview1.getUrl();
 //BA.debugLineNum = 345;BA.debugLine="SQL1.ExecNonQuery(\"insert into favs values('\" &";
_v5.ExecNonQuery("insert into favs values('"+mostCurrent._titlelabel.getText()+"-"+_myurl+"')");
 //BA.debugLineNum = 346;BA.debugLine="ToastMessageShow(\"به لیست علاقه مندی اضافه شد\",F";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("به لیست علاقه مندی اضافه شد",anywheresoftware.b4a.keywords.Common.False);
 }else if((_value).equals((Object)("about"))) { 
 //BA.debugLineNum = 349;BA.debugLine="WebView1.LoadUrl(\"file:///android_asset/data/abo";
mostCurrent._webview1.LoadUrl("file:///android_asset/data/about.html#menu");
 //BA.debugLineNum = 350;BA.debugLine="closeRightMenu";
_vvvvvvv4();
 }else if((_value).equals((Object)("search"))) { 
 //BA.debugLineNum = 353;BA.debugLine="closeRightMenu";
_vvvvvvv4();
 //BA.debugLineNum = 354;BA.debugLine="topPanel.RemoveAllViews";
mostCurrent._toppanel.RemoveAllViews();
 //BA.debugLineNum = 355;BA.debugLine="sv.AddToParent(topPanel, 1%x, 1%y, 98%x, 49%y)";
mostCurrent._vvvvvvvvvv4._vvvv3(mostCurrent._toppanel,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (1),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (1),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (98),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (49),mostCurrent.activityBA));
 //BA.debugLineNum = 356;BA.debugLine="openTopPanel";
_vvvvvvvvvv0();
 }else if((_value).equals((Object)("share"))) { 
 //BA.debugLineNum = 359;BA.debugLine="Dim share1 As Intent";
_share1 = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 360;BA.debugLine="share1.Initialize(share1.ACTION_SEND,\"\")";
_share1.Initialize(_share1.ACTION_SEND,"");
 //BA.debugLineNum = 361;BA.debugLine="share1.SetType(\"text/plain\")";
_share1.SetType("text/plain");
 //BA.debugLineNum = 362;BA.debugLine="share1.PutExtra(\"android.intent.extra.TEXT\", \"ht";
_share1.PutExtra("android.intent.extra.TEXT",(Object)("http://farhangi.zanjan.ir"));
 //BA.debugLineNum = 363;BA.debugLine="share1.WrapAsIntentChooser(\"Share via\")";
_share1.WrapAsIntentChooser("Share via");
 //BA.debugLineNum = 364;BA.debugLine="StartActivity(share1)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_share1.getObject()));
 }else if((_value).equals((Object)("font"))) { 
 //BA.debugLineNum = 367;BA.debugLine="closeRightMenu";
_vvvvvvv4();
 //BA.debugLineNum = 368;BA.debugLine="Dim ret As Int";
_ret = 0;
 //BA.debugLineNum = 369;BA.debugLine="ret = cd.Show(\"تغییر اندازه قلم\", \"بلی\", \"خیر\",";
_ret = mostCurrent._vvvvvvvvv7.Show("تغییر اندازه قلم","بلی","خیر","",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 370;BA.debugLine="If ret = -1 Then";
if (_ret==-1) { 
 //BA.debugLineNum = 371;BA.debugLine="If s1.Checked Then";
if (mostCurrent._vvvvvvvvv3.getChecked()) { 
 //BA.debugLineNum = 372;BA.debugLine="fontSize = \"0.7em\"";
mostCurrent._vvvvvvvvvv6 = "0.7em";
 }else if(mostCurrent._vvvvvvvvv4.getChecked()) { 
 //BA.debugLineNum = 374;BA.debugLine="fontSize = \"1em\"";
mostCurrent._vvvvvvvvvv6 = "1em";
 }else if(mostCurrent._vvvvvvvvv5.getChecked()) { 
 //BA.debugLineNum = 376;BA.debugLine="fontSize = \"1.6em\"";
mostCurrent._vvvvvvvvvv6 = "1.6em";
 }else if(mostCurrent._vvvvvvvvv6.getChecked()) { 
 //BA.debugLineNum = 378;BA.debugLine="fontSize = \"2.2em\"";
mostCurrent._vvvvvvvvvv6 = "2.2em";
 };
 //BA.debugLineNum = 380;BA.debugLine="Javascript=\"document.getElementById(\"\"myP\"\").st";
mostCurrent._vvvvvvv7 = "document.getElementById(\"myP\").style.fontSize=\""+mostCurrent._vvvvvvvvvv6+"\"";
 //BA.debugLineNum = 381;BA.debugLine="MyWebViewExtras.executeJavascript(WebView1, Jav";
mostCurrent._vvvvvvv0.executeJavascript((android.webkit.WebView)(mostCurrent._webview1.getObject()),mostCurrent._vvvvvvv7);
 };
 };
 //BA.debugLineNum = 384;BA.debugLine="End Sub";
return "";
}
public static String  _toppanelanim_animationend() throws Exception{
 //BA.debugLineNum = 198;BA.debugLine="Sub TopPanelAnim_AnimationEnd";
 //BA.debugLineNum = 199;BA.debugLine="If Sender = anOpenTopPanel Then";
if ((anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)).equals((Object)(mostCurrent._vvvvvvvvvv2.getObject()))) { 
 //BA.debugLineNum = 200;BA.debugLine="topPanel.top = 0";
mostCurrent._toppanel.setTop((int) (0));
 }else if((anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)).equals((Object)(mostCurrent._vvvvvvvv0.getObject()))) { 
 //BA.debugLineNum = 202;BA.debugLine="topPanel.top = -50%y";
mostCurrent._toppanel.setTop((int) (-anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (50),mostCurrent.activityBA)));
 };
 //BA.debugLineNum = 204;BA.debugLine="End Sub";
return "";
}
}
