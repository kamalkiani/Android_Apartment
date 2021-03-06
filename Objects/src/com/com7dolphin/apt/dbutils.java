package com.com7dolphin.apt;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class dbutils {
private static dbutils mostCurrent = new dbutils();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static String _db_real = "";
public static String _db_integer = "";
public static String _db_blob = "";
public static String _db_text = "";
public static String _v7 = "";
public com.com7dolphin.apt.main _vvvvv4 = null;
public com.com7dolphin.apt.act2 _vvvvv5 = null;
public static String  _v0(anywheresoftware.b4a.BA _ba,String _filename) throws Exception{
String _targetdir = "";
 //BA.debugLineNum = 30;BA.debugLine="Sub CopyDBFromAssets (FileName As String) As Strin";
 //BA.debugLineNum = 31;BA.debugLine="Dim TargetDir As String";
_targetdir = "";
 //BA.debugLineNum = 32;BA.debugLine="If File.ExternalWritable Then TargetDir = File.Di";
if (anywheresoftware.b4a.keywords.Common.File.getExternalWritable()) { 
_targetdir = anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal();}
else {
_targetdir = anywheresoftware.b4a.keywords.Common.File.getDirInternal();};
 //BA.debugLineNum = 33;BA.debugLine="If File.Exists(TargetDir, FileName) = False Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(_targetdir,_filename)==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 34;BA.debugLine="File.Copy(File.DirAssets, FileName, TargetDir, F";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),_filename,_targetdir,_filename);
 };
 //BA.debugLineNum = 36;BA.debugLine="Return TargetDir";
if (true) return _targetdir;
 //BA.debugLineNum = 37;BA.debugLine="End Sub";
return "";
}
public static String  _vv1(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.sql.SQL _sql,String _tablename,anywheresoftware.b4a.objects.collections.Map _fieldsandtypes,String _primarykey) throws Exception{
anywheresoftware.b4a.keywords.StringBuilderWrapper _sb = null;
int _i = 0;
String _field = "";
String _ftype = "";
String _query = "";
 //BA.debugLineNum = 43;BA.debugLine="Sub CreateTable(SQL As SQL, TableName As String, F";
 //BA.debugLineNum = 44;BA.debugLine="Dim sb As StringBuilder";
_sb = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 45;BA.debugLine="sb.Initialize";
_sb.Initialize();
 //BA.debugLineNum = 46;BA.debugLine="sb.Append(\"(\")";
_sb.Append("(");
 //BA.debugLineNum = 47;BA.debugLine="For i = 0 To FieldsAndTypes.Size - 1";
{
final int step21 = 1;
final int limit21 = (int) (_fieldsandtypes.getSize()-1);
for (_i = (int) (0); (step21 > 0 && _i <= limit21) || (step21 < 0 && _i >= limit21); _i = ((int)(0 + _i + step21))) {
 //BA.debugLineNum = 48;BA.debugLine="Dim field, ftype As String";
_field = "";
_ftype = "";
 //BA.debugLineNum = 49;BA.debugLine="field = FieldsAndTypes.GetKeyAt(i)";
_field = BA.ObjectToString(_fieldsandtypes.GetKeyAt(_i));
 //BA.debugLineNum = 50;BA.debugLine="ftype = FieldsAndTypes.GetValueAt(i)";
_ftype = BA.ObjectToString(_fieldsandtypes.GetValueAt(_i));
 //BA.debugLineNum = 51;BA.debugLine="If i > 0 Then sb.Append(\", \")";
if (_i>0) { 
_sb.Append(", ");};
 //BA.debugLineNum = 52;BA.debugLine="sb.Append(\"[\").Append(field).Append(\"] \").Append";
_sb.Append("[").Append(_field).Append("] ").Append(_ftype);
 //BA.debugLineNum = 53;BA.debugLine="If field = PrimaryKey Then sb.Append(\" PRIMARY K";
if ((_field).equals(_primarykey)) { 
_sb.Append(" PRIMARY KEY");};
 }
};
 //BA.debugLineNum = 55;BA.debugLine="sb.Append(\")\")";
_sb.Append(")");
 //BA.debugLineNum = 56;BA.debugLine="Dim query As String";
_query = "";
 //BA.debugLineNum = 57;BA.debugLine="query = \"CREATE TABLE IF NOT EXISTS [\" & TableNam";
_query = "CREATE TABLE IF NOT EXISTS ["+_tablename+"] "+_sb.ToString();
 //BA.debugLineNum = 58;BA.debugLine="Log(\"CreateTable: \" & query)";
anywheresoftware.b4a.keywords.Common.Log("CreateTable: "+_query);
 //BA.debugLineNum = 59;BA.debugLine="SQL.ExecNonQuery(query)";
_sql.ExecNonQuery(_query);
 //BA.debugLineNum = 60;BA.debugLine="End Sub";
return "";
}
public static String  _vv2(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.sql.SQL _sql,String _tablename,anywheresoftware.b4a.objects.collections.Map _wherefieldequals) throws Exception{
anywheresoftware.b4a.keywords.StringBuilderWrapper _sb = null;
anywheresoftware.b4a.objects.collections.List _args = null;
int _i = 0;
 //BA.debugLineNum = 392;BA.debugLine="Sub DeleteRecord(SQL As SQL, TableName As String,";
 //BA.debugLineNum = 393;BA.debugLine="Dim sb As StringBuilder";
_sb = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 394;BA.debugLine="sb.Initialize";
_sb.Initialize();
 //BA.debugLineNum = 395;BA.debugLine="sb.Append(\"DELETE FROM [\").Append(TableName).A";
_sb.Append("DELETE FROM [").Append(_tablename).Append("] WHERE ");
 //BA.debugLineNum = 396;BA.debugLine="If WhereFieldEquals.Size = 0 Then";
if (_wherefieldequals.getSize()==0) { 
 //BA.debugLineNum = 397;BA.debugLine="Log(\"WhereFieldEquals map empty!\")";
anywheresoftware.b4a.keywords.Common.Log("WhereFieldEquals map empty!");
 //BA.debugLineNum = 398;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 400;BA.debugLine="Dim args As List";
_args = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 401;BA.debugLine="args.Initialize";
_args.Initialize();
 //BA.debugLineNum = 402;BA.debugLine="For i = 0 To WhereFieldEquals.Size - 1";
{
final int step309 = 1;
final int limit309 = (int) (_wherefieldequals.getSize()-1);
for (_i = (int) (0); (step309 > 0 && _i <= limit309) || (step309 < 0 && _i >= limit309); _i = ((int)(0 + _i + step309))) {
 //BA.debugLineNum = 403;BA.debugLine="If i > 0 Then sb.Append(\" AND \")";
if (_i>0) { 
_sb.Append(" AND ");};
 //BA.debugLineNum = 404;BA.debugLine="sb.Append(\"[\").Append(WhereFieldEquals.Get";
_sb.Append("[").Append(BA.ObjectToString(_wherefieldequals.GetKeyAt(_i))).Append("] = ?");
 //BA.debugLineNum = 405;BA.debugLine="args.Add(WhereFieldEquals.GetValueAt(i))";
_args.Add(_wherefieldequals.GetValueAt(_i));
 }
};
 //BA.debugLineNum = 407;BA.debugLine="Log(\"DeleteRecord: \" & sb.ToString)";
anywheresoftware.b4a.keywords.Common.Log("DeleteRecord: "+_sb.ToString());
 //BA.debugLineNum = 408;BA.debugLine="SQL.ExecNonQuery2(sb.ToString, args)";
_sql.ExecNonQuery2(_sb.ToString(),_args);
 //BA.debugLineNum = 409;BA.debugLine="End Sub";
return "";
}
public static String  _vv3(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.sql.SQL _sql,String _tablename) throws Exception{
String _query = "";
 //BA.debugLineNum = 63;BA.debugLine="Sub DropTable(SQL As SQL, TableName As String)";
 //BA.debugLineNum = 64;BA.debugLine="Dim query As String";
_query = "";
 //BA.debugLineNum = 65;BA.debugLine="query = \"DROP TABLE IF EXISTS [\" & TableName & \"]";
_query = "DROP TABLE IF EXISTS ["+_tablename+"]";
 //BA.debugLineNum = 66;BA.debugLine="Log(\"DropTable: \" & query)";
anywheresoftware.b4a.keywords.Common.Log("DropTable: "+_query);
 //BA.debugLineNum = 67;BA.debugLine="SQL.ExecNonQuery(query)";
_sql.ExecNonQuery(_query);
 //BA.debugLineNum = 68;BA.debugLine="End Sub";
return "";
}
public static String  _vv4(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.sql.SQL _sql,String _query,String[] _stringargs,int _limit,boolean _clickable) throws Exception{
anywheresoftware.b4a.objects.collections.List _table = null;
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
anywheresoftware.b4a.keywords.StringBuilderWrapper _sb = null;
int _i = 0;
int _row = 0;
 //BA.debugLineNum = 311;BA.debugLine="Sub ExecuteHtml(SQL As SQL, Query As String, Strin";
 //BA.debugLineNum = 312;BA.debugLine="Dim Table As List";
_table = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 313;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 314;BA.debugLine="If StringArgs <> Null Then";
if (_stringargs!= null) { 
 //BA.debugLineNum = 315;BA.debugLine="cur = SQL.ExecQuery2(Query, StringArgs)";
_cur.setObject((android.database.Cursor)(_sql.ExecQuery2(_query,_stringargs)));
 }else {
 //BA.debugLineNum = 317;BA.debugLine="cur = SQL.ExecQuery(Query)";
_cur.setObject((android.database.Cursor)(_sql.ExecQuery(_query)));
 };
 //BA.debugLineNum = 319;BA.debugLine="Log(\"ExecuteHtml: \" & Query)";
anywheresoftware.b4a.keywords.Common.Log("ExecuteHtml: "+_query);
 //BA.debugLineNum = 320;BA.debugLine="If Limit > 0 Then Limit = Min(Limit, cur.RowCount";
if (_limit>0) { 
_limit = (int) (anywheresoftware.b4a.keywords.Common.Min(_limit,_cur.getRowCount()));}
else {
_limit = _cur.getRowCount();};
 //BA.debugLineNum = 321;BA.debugLine="Dim sb As StringBuilder";
_sb = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 322;BA.debugLine="sb.Initialize";
_sb.Initialize();
 //BA.debugLineNum = 323;BA.debugLine="sb.Append(\"<html><body>\").Append(CRLF)";
_sb.Append("<html><body>").Append(anywheresoftware.b4a.keywords.Common.CRLF);
 //BA.debugLineNum = 324;BA.debugLine="sb.Append(\"<style type='text/css'>\").Append(HtmlC";
_sb.Append("<style type='text/css'>").Append(_v7).Append("</style>").Append(anywheresoftware.b4a.keywords.Common.CRLF);
 //BA.debugLineNum = 325;BA.debugLine="sb.Append(\"<table><tr>\").Append(CRLF)";
_sb.Append("<table><tr>").Append(anywheresoftware.b4a.keywords.Common.CRLF);
 //BA.debugLineNum = 326;BA.debugLine="For i = 0 To cur.ColumnCount - 1";
{
final int step253 = 1;
final int limit253 = (int) (_cur.getColumnCount()-1);
for (_i = (int) (0); (step253 > 0 && _i <= limit253) || (step253 < 0 && _i >= limit253); _i = ((int)(0 + _i + step253))) {
 //BA.debugLineNum = 327;BA.debugLine="sb.Append(\"<th>\").Append(cur.GetColumnName(i)).A";
_sb.Append("<th>").Append(_cur.GetColumnName(_i)).Append("</th>");
 }
};
 //BA.debugLineNum = 338;BA.debugLine="sb.Append(\"</tr>\").Append(CRLF)";
_sb.Append("</tr>").Append(anywheresoftware.b4a.keywords.Common.CRLF);
 //BA.debugLineNum = 339;BA.debugLine="For row = 0 To Limit - 1";
{
final int step257 = 1;
final int limit257 = (int) (_limit-1);
for (_row = (int) (0); (step257 > 0 && _row <= limit257) || (step257 < 0 && _row >= limit257); _row = ((int)(0 + _row + step257))) {
 //BA.debugLineNum = 340;BA.debugLine="cur.Position = row";
_cur.setPosition(_row);
 //BA.debugLineNum = 341;BA.debugLine="If row Mod 2 = 0 Then";
if (_row%2==0) { 
 //BA.debugLineNum = 342;BA.debugLine="sb.Append(\"<tr>\")";
_sb.Append("<tr>");
 }else {
 //BA.debugLineNum = 344;BA.debugLine="sb.Append(\"<tr class='odd'>\")";
_sb.Append("<tr class='odd'>");
 };
 //BA.debugLineNum = 346;BA.debugLine="For i = 0 To cur.ColumnCount - 1";
{
final int step264 = 1;
final int limit264 = (int) (_cur.getColumnCount()-1);
for (_i = (int) (0); (step264 > 0 && _i <= limit264) || (step264 < 0 && _i >= limit264); _i = ((int)(0 + _i + step264))) {
 //BA.debugLineNum = 347;BA.debugLine="sb.Append(\"<td>\")";
_sb.Append("<td>");
 //BA.debugLineNum = 348;BA.debugLine="If Clickable Then";
if (_clickable) { 
 //BA.debugLineNum = 349;BA.debugLine="sb.Append(\"<a href='http://\").Append(i).Append";
_sb.Append("<a href='http://").Append(BA.NumberToString(_i)).Append(".");
 //BA.debugLineNum = 350;BA.debugLine="sb.Append(row)";
_sb.Append(BA.NumberToString(_row));
 //BA.debugLineNum = 351;BA.debugLine="sb.Append(\".com'>\").Append(cur.GetString2(i)).";
_sb.Append(".com'>").Append(_cur.GetString2(_i)).Append("</a>");
 }else {
 //BA.debugLineNum = 353;BA.debugLine="sb.Append(cur.GetString2(i))";
_sb.Append(_cur.GetString2(_i));
 };
 //BA.debugLineNum = 355;BA.debugLine="sb.Append(\"</td>\")";
_sb.Append("</td>");
 }
};
 //BA.debugLineNum = 357;BA.debugLine="sb.Append(\"</tr>\").Append(CRLF)";
_sb.Append("</tr>").Append(anywheresoftware.b4a.keywords.Common.CRLF);
 }
};
 //BA.debugLineNum = 359;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 360;BA.debugLine="sb.Append(\"</table></body></html>\")";
_sb.Append("</table></body></html>");
 //BA.debugLineNum = 361;BA.debugLine="Return sb.ToString";
if (true) return _sb.ToString();
 //BA.debugLineNum = 362;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.collections.Map  _vv5(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.sql.SQL _sql,String _query,String[] _stringargs,int _limit,anywheresoftware.b4a.objects.collections.List _dbtypes) throws Exception{
anywheresoftware.b4a.objects.collections.List _table = null;
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
int _row = 0;
anywheresoftware.b4a.objects.collections.Map _m = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _root = null;
 //BA.debugLineNum = 272;BA.debugLine="Sub ExecuteJSON (SQL As SQL, Query As String, Stri";
 //BA.debugLineNum = 273;BA.debugLine="Dim table As List";
_table = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 274;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 275;BA.debugLine="If StringArgs <> Null Then";
if (_stringargs!= null) { 
 //BA.debugLineNum = 276;BA.debugLine="cur = SQL.ExecQuery2(Query, StringArgs)";
_cur.setObject((android.database.Cursor)(_sql.ExecQuery2(_query,_stringargs)));
 }else {
 //BA.debugLineNum = 278;BA.debugLine="cur = SQL.ExecQuery(Query)";
_cur.setObject((android.database.Cursor)(_sql.ExecQuery(_query)));
 };
 //BA.debugLineNum = 280;BA.debugLine="Log(\"ExecuteJSON: \" & Query)";
anywheresoftware.b4a.keywords.Common.Log("ExecuteJSON: "+_query);
 //BA.debugLineNum = 281;BA.debugLine="Dim table As List";
_table = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 282;BA.debugLine="table.Initialize";
_table.Initialize();
 //BA.debugLineNum = 283;BA.debugLine="If Limit > 0 Then Limit = Min(Limit, cur.RowCount";
if (_limit>0) { 
_limit = (int) (anywheresoftware.b4a.keywords.Common.Min(_limit,_cur.getRowCount()));}
else {
_limit = _cur.getRowCount();};
 //BA.debugLineNum = 284;BA.debugLine="For row = 0 To Limit - 1";
{
final int step214 = 1;
final int limit214 = (int) (_limit-1);
for (_row = (int) (0); (step214 > 0 && _row <= limit214) || (step214 < 0 && _row >= limit214); _row = ((int)(0 + _row + step214))) {
 //BA.debugLineNum = 285;BA.debugLine="cur.Position = row";
_cur.setPosition(_row);
 //BA.debugLineNum = 286;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 287;BA.debugLine="m.Initialize";
_m.Initialize();
 //BA.debugLineNum = 288;BA.debugLine="For i = 0 To cur.ColumnCount - 1";
{
final int step218 = 1;
final int limit218 = (int) (_cur.getColumnCount()-1);
for (_i = (int) (0); (step218 > 0 && _i <= limit218) || (step218 < 0 && _i >= limit218); _i = ((int)(0 + _i + step218))) {
 //BA.debugLineNum = 289;BA.debugLine="Select DBTypes.Get(i)";
switch (BA.switchObjectToInt(_dbtypes.Get(_i),(Object)(_db_text),(Object)(_db_integer),(Object)(_db_real))) {
case 0:
 //BA.debugLineNum = 291;BA.debugLine="m.Put(cur.GetColumnName(i), cur.GetString2(i)";
_m.Put((Object)(_cur.GetColumnName(_i)),(Object)(_cur.GetString2(_i)));
 break;
case 1:
 //BA.debugLineNum = 293;BA.debugLine="m.Put(cur.GetColumnName(i), cur.GetLong2(i))";
_m.Put((Object)(_cur.GetColumnName(_i)),(Object)(_cur.GetLong2(_i)));
 break;
case 2:
 //BA.debugLineNum = 295;BA.debugLine="m.Put(cur.GetColumnName(i), cur.GetDouble2(i)";
_m.Put((Object)(_cur.GetColumnName(_i)),(Object)(_cur.GetDouble2(_i)));
 break;
default:
 //BA.debugLineNum = 297;BA.debugLine="Log(\"Invalid type: \" & DBTypes.Get(i))";
anywheresoftware.b4a.keywords.Common.Log("Invalid type: "+BA.ObjectToString(_dbtypes.Get(_i)));
 break;
}
;
 }
};
 //BA.debugLineNum = 300;BA.debugLine="table.Add(m)";
_table.Add((Object)(_m.getObject()));
 }
};
 //BA.debugLineNum = 302;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 303;BA.debugLine="Dim root As Map";
_root = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 304;BA.debugLine="root.Initialize";
_root.Initialize();
 //BA.debugLineNum = 305;BA.debugLine="root.Put(\"root\", table)";
_root.Put((Object)("root"),(Object)(_table.getObject()));
 //BA.debugLineNum = 306;BA.debugLine="Return root";
if (true) return _root;
 //BA.debugLineNum = 307;BA.debugLine="End Sub";
return null;
}
public static String  _vv6(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.sql.SQL _sql,String _query,String[] _stringargs,int _limit,anywheresoftware.b4a.objects.ListViewWrapper _listview1,boolean _twolines) throws Exception{
anywheresoftware.b4a.objects.collections.List _table = null;
String[] _cols = null;
int _i = 0;
 //BA.debugLineNum = 247;BA.debugLine="Sub ExecuteListView(SQL As SQL, Query As String, S";
 //BA.debugLineNum = 249;BA.debugLine="ListView1.Clear";
_listview1.Clear();
 //BA.debugLineNum = 250;BA.debugLine="Dim Table As List";
_table = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 251;BA.debugLine="Table = ExecuteMemoryTable(SQL, Query, StringArgs";
_table = _vv0(_ba,_sql,_query,_stringargs,_limit);
 //BA.debugLineNum = 252;BA.debugLine="Dim Cols() As String";
_cols = new String[(int) (0)];
java.util.Arrays.fill(_cols,"");
 //BA.debugLineNum = 253;BA.debugLine="For i = 0 To Table.Size - 1";
{
final int step193 = 1;
final int limit193 = (int) (_table.getSize()-1);
for (_i = (int) (0); (step193 > 0 && _i <= limit193) || (step193 < 0 && _i >= limit193); _i = ((int)(0 + _i + step193))) {
 //BA.debugLineNum = 254;BA.debugLine="Cols = Table.Get(i)";
_cols = (String[])(_table.Get(_i));
 //BA.debugLineNum = 255;BA.debugLine="If TwoLines Then";
if (_twolines) { 
 //BA.debugLineNum = 256;BA.debugLine="ListView1.AddTwoLines2(Cols(0), Cols(1), Cols)";
_listview1.AddTwoLines2(_cols[(int) (0)],_cols[(int) (1)],(Object)(_cols));
 }else {
 //BA.debugLineNum = 258;BA.debugLine="ListView1.AddSingleLine2(Cols(0), Cols)";
_listview1.AddSingleLine2(_cols[(int) (0)],(Object)(_cols));
 };
 }
};
 //BA.debugLineNum = 261;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.collections.Map  _vv7(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.sql.SQL _sql,String _query,String[] _stringargs) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
anywheresoftware.b4a.objects.collections.Map _res = null;
int _i = 0;
 //BA.debugLineNum = 209;BA.debugLine="Sub ExecuteMap(SQL As SQL, Query As String, String";
 //BA.debugLineNum = 210;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 211;BA.debugLine="If StringArgs <> Null Then";
if (_stringargs!= null) { 
 //BA.debugLineNum = 212;BA.debugLine="cur = SQL.ExecQuery2(Query, StringArgs)";
_cur.setObject((android.database.Cursor)(_sql.ExecQuery2(_query,_stringargs)));
 }else {
 //BA.debugLineNum = 214;BA.debugLine="cur = SQL.ExecQuery(Query)";
_cur.setObject((android.database.Cursor)(_sql.ExecQuery(_query)));
 };
 //BA.debugLineNum = 216;BA.debugLine="Log(\"ExecuteMap: \" & Query)";
anywheresoftware.b4a.keywords.Common.Log("ExecuteMap: "+_query);
 //BA.debugLineNum = 217;BA.debugLine="If cur.RowCount = 0 Then";
if (_cur.getRowCount()==0) { 
 //BA.debugLineNum = 218;BA.debugLine="Log(\"No records found.\")";
anywheresoftware.b4a.keywords.Common.Log("No records found.");
 //BA.debugLineNum = 219;BA.debugLine="Return Null";
if (true) return (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (anywheresoftware.b4a.objects.collections.Map.MyMap)(anywheresoftware.b4a.keywords.Common.Null));
 };
 //BA.debugLineNum = 221;BA.debugLine="Dim res As Map";
_res = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 222;BA.debugLine="res.Initialize";
_res.Initialize();
 //BA.debugLineNum = 223;BA.debugLine="cur.Position = 0";
_cur.setPosition((int) (0));
 //BA.debugLineNum = 224;BA.debugLine="For i = 0 To cur.ColumnCount - 1";
{
final int step172 = 1;
final int limit172 = (int) (_cur.getColumnCount()-1);
for (_i = (int) (0); (step172 > 0 && _i <= limit172) || (step172 < 0 && _i >= limit172); _i = ((int)(0 + _i + step172))) {
 //BA.debugLineNum = 225;BA.debugLine="res.Put(cur.GetColumnName(i).ToLowerCase, cur.Ge";
_res.Put((Object)(_cur.GetColumnName(_i).toLowerCase()),(Object)(_cur.GetString2(_i)));
 }
};
 //BA.debugLineNum = 227;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 228;BA.debugLine="Return res";
if (true) return _res;
 //BA.debugLineNum = 229;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.collections.List  _vv0(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.sql.SQL _sql,String _query,String[] _stringargs,int _limit) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
anywheresoftware.b4a.objects.collections.List _table = null;
int _row = 0;
String[] _values = null;
int _col = 0;
 //BA.debugLineNum = 182;BA.debugLine="Sub ExecuteMemoryTable(SQL As SQL, Query As String";
 //BA.debugLineNum = 183;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 184;BA.debugLine="If StringArgs <> Null Then";
if (_stringargs!= null) { 
 //BA.debugLineNum = 185;BA.debugLine="cur = SQL.ExecQuery2(Query, StringArgs)";
_cur.setObject((android.database.Cursor)(_sql.ExecQuery2(_query,_stringargs)));
 }else {
 //BA.debugLineNum = 187;BA.debugLine="cur = SQL.ExecQuery(Query)";
_cur.setObject((android.database.Cursor)(_sql.ExecQuery(_query)));
 };
 //BA.debugLineNum = 189;BA.debugLine="Log(\"ExecuteMemoryTable: \" & Query)";
anywheresoftware.b4a.keywords.Common.Log("ExecuteMemoryTable: "+_query);
 //BA.debugLineNum = 190;BA.debugLine="Dim table As List";
_table = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 191;BA.debugLine="table.Initialize";
_table.Initialize();
 //BA.debugLineNum = 192;BA.debugLine="If Limit > 0 Then Limit = Min(Limit, cur.RowCount";
if (_limit>0) { 
_limit = (int) (anywheresoftware.b4a.keywords.Common.Min(_limit,_cur.getRowCount()));}
else {
_limit = _cur.getRowCount();};
 //BA.debugLineNum = 193;BA.debugLine="For row = 0 To Limit - 1";
{
final int step146 = 1;
final int limit146 = (int) (_limit-1);
for (_row = (int) (0); (step146 > 0 && _row <= limit146) || (step146 < 0 && _row >= limit146); _row = ((int)(0 + _row + step146))) {
 //BA.debugLineNum = 194;BA.debugLine="cur.Position = row";
_cur.setPosition(_row);
 //BA.debugLineNum = 195;BA.debugLine="Dim values(cur.ColumnCount) As String";
_values = new String[_cur.getColumnCount()];
java.util.Arrays.fill(_values,"");
 //BA.debugLineNum = 196;BA.debugLine="For col = 0 To cur.ColumnCount - 1";
{
final int step149 = 1;
final int limit149 = (int) (_cur.getColumnCount()-1);
for (_col = (int) (0); (step149 > 0 && _col <= limit149) || (step149 < 0 && _col >= limit149); _col = ((int)(0 + _col + step149))) {
 //BA.debugLineNum = 197;BA.debugLine="values(col) = cur.GetString2(col)";
_values[_col] = _cur.GetString2(_col);
 }
};
 //BA.debugLineNum = 199;BA.debugLine="table.Add(values)";
_table.Add((Object)(_values));
 }
};
 //BA.debugLineNum = 201;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 202;BA.debugLine="Return table";
if (true) return _table;
 //BA.debugLineNum = 203;BA.debugLine="End Sub";
return null;
}
public static String  _vvv1(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.sql.SQL _sql,String _query,String[] _stringargs,int _limit,anywheresoftware.b4a.objects.SpinnerWrapper _spinner1) throws Exception{
anywheresoftware.b4a.objects.collections.List _table = null;
String[] _cols = null;
int _i = 0;
 //BA.debugLineNum = 232;BA.debugLine="Sub ExecuteSpinner(SQL As SQL, Query As String, St";
 //BA.debugLineNum = 233;BA.debugLine="Spinner1.Clear";
_spinner1.Clear();
 //BA.debugLineNum = 234;BA.debugLine="Dim Table As List";
_table = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 235;BA.debugLine="Table = ExecuteMemoryTable(SQL, Query, StringArgs";
_table = _vv0(_ba,_sql,_query,_stringargs,_limit);
 //BA.debugLineNum = 236;BA.debugLine="Dim Cols() As String";
_cols = new String[(int) (0)];
java.util.Arrays.fill(_cols,"");
 //BA.debugLineNum = 237;BA.debugLine="For i = 0 To Table.Size - 1";
{
final int step183 = 1;
final int limit183 = (int) (_table.getSize()-1);
for (_i = (int) (0); (step183 > 0 && _i <= limit183) || (step183 < 0 && _i >= limit183); _i = ((int)(0 + _i + step183))) {
 //BA.debugLineNum = 238;BA.debugLine="Cols = Table.Get(i)";
_cols = (String[])(_table.Get(_i));
 //BA.debugLineNum = 239;BA.debugLine="Spinner1.Add(Cols(0))";
_spinner1.Add(_cols[(int) (0)]);
 }
};
 //BA.debugLineNum = 241;BA.debugLine="End Sub";
return "";
}
public static int  _vvv2(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.sql.SQL _sql) throws Exception{
int _count = 0;
int _version = 0;
anywheresoftware.b4a.objects.collections.Map _m = null;
 //BA.debugLineNum = 366;BA.debugLine="Sub GetDBVersion (SQL As SQL) As Int";
 //BA.debugLineNum = 367;BA.debugLine="Dim count, version As Int";
_count = 0;
_version = 0;
 //BA.debugLineNum = 368;BA.debugLine="count = SQL.ExecQuerySingleResult(\"SELECT count(*";
_count = (int)(Double.parseDouble(_sql.ExecQuerySingleResult("SELECT count(*) FROM sqlite_master WHERE Type='table' AND name='DBVersion'")));
 //BA.debugLineNum = 369;BA.debugLine="If count > 0 Then";
if (_count>0) { 
 //BA.debugLineNum = 370;BA.debugLine="version = SQL.ExecQuerySingleResult(\"SELECT vers";
_version = (int)(Double.parseDouble(_sql.ExecQuerySingleResult("SELECT version FROM DBVersion")));
 }else {
 //BA.debugLineNum = 373;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 374;BA.debugLine="m.Initialize";
_m.Initialize();
 //BA.debugLineNum = 375;BA.debugLine="m.Put(\"version\", DB_INTEGER)";
_m.Put((Object)("version"),(Object)(_db_integer));
 //BA.debugLineNum = 376;BA.debugLine="CreateTable(SQL, \"DBVersion\", m, \"version\")";
_vv1(_ba,_sql,"DBVersion",_m,"version");
 //BA.debugLineNum = 378;BA.debugLine="SQL.ExecNonQuery(\"INSERT INTO DBVersion VALUES (";
_sql.ExecNonQuery("INSERT INTO DBVersion VALUES (1)");
 //BA.debugLineNum = 380;BA.debugLine="version = 1";
_version = (int) (1);
 };
 //BA.debugLineNum = 383;BA.debugLine="Return version";
if (true) return _version;
 //BA.debugLineNum = 384;BA.debugLine="End Sub";
return 0;
}
public static String  _vvv3(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.sql.SQL _sql,String _tablename,anywheresoftware.b4a.objects.collections.List _listofmaps) throws Exception{
anywheresoftware.b4a.keywords.StringBuilderWrapper _sb = null;
anywheresoftware.b4a.keywords.StringBuilderWrapper _columns = null;
anywheresoftware.b4a.keywords.StringBuilderWrapper _values = null;
int _i1 = 0;
anywheresoftware.b4a.objects.collections.List _listofvalues = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
int _i2 = 0;
String _col = "";
Object _value = null;
 //BA.debugLineNum = 74;BA.debugLine="Sub InsertMaps(SQL As SQL, TableName As String, Li";
 //BA.debugLineNum = 75;BA.debugLine="Dim sb, columns, values As StringBuilder";
_sb = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
_columns = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
_values = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 77;BA.debugLine="If ListOfMaps.Size > 1 AND ListOfMaps.Get(0) = Li";
if (_listofmaps.getSize()>1 && (_listofmaps.Get((int) (0))).equals(_listofmaps.Get((int) (1)))) { 
 //BA.debugLineNum = 78;BA.debugLine="Log(\"Same Map found twice in list. Each item in";
anywheresoftware.b4a.keywords.Common.Log("Same Map found twice in list. Each item in the list should include a different map object.");
 //BA.debugLineNum = 79;BA.debugLine="ToastMessageShow(\"Same Map found twice in list.";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("Same Map found twice in list. Each item in the list should include a different map object.",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 80;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 82;BA.debugLine="SQL.BeginTransaction";
_sql.BeginTransaction();
 //BA.debugLineNum = 83;BA.debugLine="Try";
try { //BA.debugLineNum = 84;BA.debugLine="For i1 = 0 To ListOfMaps.Size - 1";
{
final int step50 = 1;
final int limit50 = (int) (_listofmaps.getSize()-1);
for (_i1 = (int) (0); (step50 > 0 && _i1 <= limit50) || (step50 < 0 && _i1 >= limit50); _i1 = ((int)(0 + _i1 + step50))) {
 //BA.debugLineNum = 85;BA.debugLine="sb.Initialize";
_sb.Initialize();
 //BA.debugLineNum = 86;BA.debugLine="columns.Initialize";
_columns.Initialize();
 //BA.debugLineNum = 87;BA.debugLine="values.Initialize";
_values.Initialize();
 //BA.debugLineNum = 88;BA.debugLine="Dim listOfValues As List";
_listofvalues = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 89;BA.debugLine="listOfValues.Initialize";
_listofvalues.Initialize();
 //BA.debugLineNum = 90;BA.debugLine="sb.Append(\"INSERT INTO [\" & TableName & \"] (\")";
_sb.Append("INSERT INTO ["+_tablename+"] (");
 //BA.debugLineNum = 91;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 92;BA.debugLine="m = ListOfMaps.Get(i1)";
_m.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_listofmaps.Get(_i1)));
 //BA.debugLineNum = 93;BA.debugLine="For i2 = 0 To m.Size - 1";
{
final int step59 = 1;
final int limit59 = (int) (_m.getSize()-1);
for (_i2 = (int) (0); (step59 > 0 && _i2 <= limit59) || (step59 < 0 && _i2 >= limit59); _i2 = ((int)(0 + _i2 + step59))) {
 //BA.debugLineNum = 94;BA.debugLine="Dim col As String";
_col = "";
 //BA.debugLineNum = 95;BA.debugLine="Dim value As Object";
_value = new Object();
 //BA.debugLineNum = 96;BA.debugLine="col = m.GetKeyAt(i2)";
_col = BA.ObjectToString(_m.GetKeyAt(_i2));
 //BA.debugLineNum = 97;BA.debugLine="value = m.GetValueAt(i2)";
_value = _m.GetValueAt(_i2);
 //BA.debugLineNum = 98;BA.debugLine="If i2 > 0 Then";
if (_i2>0) { 
 //BA.debugLineNum = 99;BA.debugLine="columns.Append(\", \")";
_columns.Append(", ");
 //BA.debugLineNum = 100;BA.debugLine="values.Append(\", \")";
_values.Append(", ");
 };
 //BA.debugLineNum = 102;BA.debugLine="columns.Append(\"[\").Append(col).Append(\"]\")";
_columns.Append("[").Append(_col).Append("]");
 //BA.debugLineNum = 103;BA.debugLine="values.Append(\"?\")";
_values.Append("?");
 //BA.debugLineNum = 104;BA.debugLine="listOfValues.Add(value)";
_listofvalues.Add(_value);
 }
};
 //BA.debugLineNum = 106;BA.debugLine="sb.Append(columns.ToString).Append(\") VALUES (\"";
_sb.Append(_columns.ToString()).Append(") VALUES (").Append(_values.ToString()).Append(")");
 //BA.debugLineNum = 107;BA.debugLine="If i1 = 0 Then Log(\"InsertMaps (first query out";
if (_i1==0) { 
anywheresoftware.b4a.keywords.Common.Log("InsertMaps (first query out of "+BA.NumberToString(_listofmaps.getSize())+"): "+_sb.ToString());};
 //BA.debugLineNum = 108;BA.debugLine="SQL.ExecNonQuery2(sb.ToString, listOfValues)";
_sql.ExecNonQuery2(_sb.ToString(),_listofvalues);
 }
};
 //BA.debugLineNum = 110;BA.debugLine="SQL.TransactionSuccessful";
_sql.TransactionSuccessful();
 } 
       catch (Exception e78) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e78); //BA.debugLineNum = 112;BA.debugLine="ToastMessageShow(LastException.Message, True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 113;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)));
 };
 //BA.debugLineNum = 115;BA.debugLine="SQL.EndTransaction";
_sql.EndTransaction();
 //BA.debugLineNum = 116;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 13;BA.debugLine="Dim DB_REAL, DB_INTEGER, DB_BLOB, DB_TEXT As Stri";
_db_real = "";
_db_integer = "";
_db_blob = "";
_db_text = "";
 //BA.debugLineNum = 14;BA.debugLine="DB_REAL = \"REAL\"";
_db_real = BA.__b (new byte[] {1,5,0,-109}, 44684);
 //BA.debugLineNum = 15;BA.debugLine="DB_INTEGER = \"INTEGER\"";
_db_integer = BA.__b (new byte[] {26,15,21,44,11,26,18}, 502916);
 //BA.debugLineNum = 16;BA.debugLine="DB_BLOB = \"BLOB\"";
_db_blob = BA.__b (new byte[] {17,13,80,-32}, 571498);
 //BA.debugLineNum = 17;BA.debugLine="DB_TEXT = \"TEXT\"";
_db_text = BA.__b (new byte[] {7,4,88,-44}, 616090);
 //BA.debugLineNum = 18;BA.debugLine="Dim HtmlCSS As String";
_v7 = "";
 //BA.debugLineNum = 19;BA.debugLine="HtmlCSS = \"table {width: 100%;border: 1px solid #";
_v7 = BA.__b (new byte[] {39,33,-35,79,41,126,-59,82,60,59,-44,20,98,102,-115,19,126,100,-100,19,49,47,-122,10,45,120,-59,88,48,37,-125,9,55,50,-117,9,96,120,-46,7,36,36,-60,24,36,115,-123,2,48,54,-35,10,123,63,-34,7,35,39,-104,65,99}, 398574)+BA.__b (new byte[] {115,53,61,115,55,127,50,58,59,42,103,123,61,46,49,59,58,122,109,99,49,48,108,36,86,33,110,122,43,59,59,101,45,49,108,48,35,53,55,125,48,36,122,46,61,101,36,40,85,60,63,102,37,123,42,63,39,61,61,101,113,32,109,48,41,36,46,46,50,54,35,123,122,111,107,106,41,57,111,108,47}, 466781)+BA.__b (new byte[] {39,37,39,-126,36,127,113,-7,37,63,112,-51,49,41,111,-52,110,116,99,-36,126,105,38,-62,100,99,44}, 607647)+BA.__b (new byte[] {125,46,-123,104,108,36,-126,107,54,53,-103,33,55,50,-116,104,99,35,-106,50,49,46,-122,96,124,39,-34,32,123,124,-128,117,118,48,-40,38,96,46,-117,109,57,124,-127,32,56,99,-124,97,62,49,-112,63,46,115,-42,109,116,34,-123,110,109,34,-113,38,124,116,-63,50,36,63,-64,109,99}, 788932)+BA.__b (new byte[] {50,96,122,24,56,59,120,74,120,59,123,4,55,52,99,76,39,46,119,80,48,50,50,17,100,98,56,29,44,50,111,91,120,125,108,70,112,96,114}, 158287);
 //BA.debugLineNum = 24;BA.debugLine="End Sub";
return "";
}
public static String  _vvv4(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.sql.SQL _sql,int _version) throws Exception{
 //BA.debugLineNum = 387;BA.debugLine="Sub SetDBVersion (SQL As SQL, Version As Int)";
 //BA.debugLineNum = 388;BA.debugLine="SQL.ExecNonQuery2(\"UPDATE DBVersion set version =";
_sql.ExecNonQuery2("UPDATE DBVersion set version = ?",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(_version)}));
 //BA.debugLineNum = 389;BA.debugLine="End Sub";
return "";
}
public static String  _vvv5(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.sql.SQL _sql,String _tablename,String _field,Object _newvalue,anywheresoftware.b4a.objects.collections.Map _wherefieldequals) throws Exception{
anywheresoftware.b4a.keywords.StringBuilderWrapper _sb = null;
anywheresoftware.b4a.objects.collections.List _args = null;
int _i = 0;
 //BA.debugLineNum = 120;BA.debugLine="Sub UpdateRecord(SQL As SQL, TableName As String,";
 //BA.debugLineNum = 122;BA.debugLine="Dim sb As StringBuilder";
_sb = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 123;BA.debugLine="sb.Initialize";
_sb.Initialize();
 //BA.debugLineNum = 124;BA.debugLine="sb.Append(\"UPDATE [\").Append(TableName).Append(\"]";
_sb.Append("UPDATE [").Append(_tablename).Append("] SET [").Append(_field).Append("] = ? WHERE ");
 //BA.debugLineNum = 125;BA.debugLine="If WhereFieldEquals.Size = 0 Then";
if (_wherefieldequals.getSize()==0) { 
 //BA.debugLineNum = 126;BA.debugLine="Log(\"WhereFieldEquals map empty!\")";
anywheresoftware.b4a.keywords.Common.Log("WhereFieldEquals map empty!");
 //BA.debugLineNum = 127;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 129;BA.debugLine="Dim args As List";
_args = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 130;BA.debugLine="args.Initialize";
_args.Initialize();
 //BA.debugLineNum = 131;BA.debugLine="args.Add(NewValue)";
_args.Add(_newvalue);
 //BA.debugLineNum = 132;BA.debugLine="For i = 0 To WhereFieldEquals.Size - 1";
{
final int step94 = 1;
final int limit94 = (int) (_wherefieldequals.getSize()-1);
for (_i = (int) (0); (step94 > 0 && _i <= limit94) || (step94 < 0 && _i >= limit94); _i = ((int)(0 + _i + step94))) {
 //BA.debugLineNum = 133;BA.debugLine="If i > 0 Then sb.Append(\" AND \")";
if (_i>0) { 
_sb.Append(" AND ");};
 //BA.debugLineNum = 134;BA.debugLine="sb.Append(\"[\").Append(WhereFieldEquals.GetKeyAt(";
_sb.Append("[").Append(BA.ObjectToString(_wherefieldequals.GetKeyAt(_i))).Append("] = ?");
 //BA.debugLineNum = 135;BA.debugLine="args.Add(WhereFieldEquals.GetValueAt(i))";
_args.Add(_wherefieldequals.GetValueAt(_i));
 }
};
 //BA.debugLineNum = 137;BA.debugLine="Log(\"UpdateRecord: \" & sb.ToString)";
anywheresoftware.b4a.keywords.Common.Log("UpdateRecord: "+_sb.ToString());
 //BA.debugLineNum = 138;BA.debugLine="SQL.ExecNonQuery2(sb.ToString, args)";
_sql.ExecNonQuery2(_sb.ToString(),_args);
 //BA.debugLineNum = 139;BA.debugLine="End Sub";
return "";
}
public static String  _vvv6(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.sql.SQL _sql,String _tablename,anywheresoftware.b4a.objects.collections.Map _fields,anywheresoftware.b4a.objects.collections.Map _wherefieldequals) throws Exception{
anywheresoftware.b4a.keywords.StringBuilderWrapper _sb = null;
anywheresoftware.b4a.objects.collections.List _args = null;
int _i = 0;
 //BA.debugLineNum = 143;BA.debugLine="Sub UpdateRecord2(SQL As SQL, TableName As String,";
 //BA.debugLineNum = 144;BA.debugLine="If WhereFieldEquals.Size = 0 Then";
if (_wherefieldequals.getSize()==0) { 
 //BA.debugLineNum = 145;BA.debugLine="Log(\"WhereFieldEquals map empty!\")";
anywheresoftware.b4a.keywords.Common.Log("WhereFieldEquals map empty!");
 //BA.debugLineNum = 146;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 148;BA.debugLine="If Fields.Size = 0 Then";
if (_fields.getSize()==0) { 
 //BA.debugLineNum = 149;BA.debugLine="Log(\"Fields empty\")";
anywheresoftware.b4a.keywords.Common.Log("Fields empty");
 //BA.debugLineNum = 150;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 152;BA.debugLine="Dim sb As StringBuilder";
_sb = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 153;BA.debugLine="sb.Initialize";
_sb.Initialize();
 //BA.debugLineNum = 154;BA.debugLine="sb.Append(\"UPDATE [\").Append(TableName).Append(\"]";
_sb.Append("UPDATE [").Append(_tablename).Append("] SET ");
 //BA.debugLineNum = 155;BA.debugLine="Dim args As List";
_args = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 156;BA.debugLine="args.Initialize";
_args.Initialize();
 //BA.debugLineNum = 157;BA.debugLine="For i=0 To Fields.Size-1";
{
final int step116 = 1;
final int limit116 = (int) (_fields.getSize()-1);
for (_i = (int) (0); (step116 > 0 && _i <= limit116) || (step116 < 0 && _i >= limit116); _i = ((int)(0 + _i + step116))) {
 //BA.debugLineNum = 158;BA.debugLine="If i<>Fields.Size-1 Then";
if (_i!=_fields.getSize()-1) { 
 //BA.debugLineNum = 159;BA.debugLine="sb.Append(\"[\").Append(Fields.GetKeyAt(i)).Appen";
_sb.Append("[").Append(BA.ObjectToString(_fields.GetKeyAt(_i))).Append("]=?,");
 }else {
 //BA.debugLineNum = 161;BA.debugLine="sb.Append(\"[\").Append(Fields.GetKeyAt(i)).Appen";
_sb.Append("[").Append(BA.ObjectToString(_fields.GetKeyAt(_i))).Append("]=?");
 };
 //BA.debugLineNum = 163;BA.debugLine="args.Add(Fields.GetValueAt(i))";
_args.Add(_fields.GetValueAt(_i));
 }
};
 //BA.debugLineNum = 166;BA.debugLine="sb.Append(\" WHERE \")";
_sb.Append(" WHERE ");
 //BA.debugLineNum = 167;BA.debugLine="For i = 0 To WhereFieldEquals.Size - 1";
{
final int step125 = 1;
final int limit125 = (int) (_wherefieldequals.getSize()-1);
for (_i = (int) (0); (step125 > 0 && _i <= limit125) || (step125 < 0 && _i >= limit125); _i = ((int)(0 + _i + step125))) {
 //BA.debugLineNum = 168;BA.debugLine="If i > 0 Then";
if (_i>0) { 
 //BA.debugLineNum = 169;BA.debugLine="sb.Append(\" AND \")";
_sb.Append(" AND ");
 };
 //BA.debugLineNum = 171;BA.debugLine="sb.Append(\"[\").Append(WhereFieldEquals.GetKeyAt(";
_sb.Append("[").Append(BA.ObjectToString(_wherefieldequals.GetKeyAt(_i))).Append("] = ?");
 //BA.debugLineNum = 172;BA.debugLine="args.Add(WhereFieldEquals.GetValueAt(i))";
_args.Add(_wherefieldequals.GetValueAt(_i));
 }
};
 //BA.debugLineNum = 174;BA.debugLine="Log(\"UpdateRecord: \" & sb.ToString)";
anywheresoftware.b4a.keywords.Common.Log("UpdateRecord: "+_sb.ToString());
 //BA.debugLineNum = 175;BA.debugLine="SQL.ExecNonQuery2(sb.ToString, args)";
_sql.ExecNonQuery2(_sb.ToString(),_args);
 //BA.debugLineNum = 176;BA.debugLine="End Sub";
return "";
}
}
