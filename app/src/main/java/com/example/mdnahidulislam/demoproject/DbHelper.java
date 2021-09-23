package com.example.mdnahidulislam.demoproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.jar.Attributes;

import static android.provider.Contacts.SettingsColumns.KEY;
import static android.provider.ContactsContract.Intents.Insert.EMAIL;

public class DbHelper extends SQLiteOpenHelper {
    private  static  final  String dbname="users.db";
  //user table
    private  static  final  String table_name="userinfo";
    private  static  final  String id="ID";
    private  static  final  String name="NAME";
    private  static  final  String email="EMAIL";
    private  static  final  String user_name="USER_NAME";
    private  static  final  String pass="PASSWORD";
    private  static  final int version=62;
//requst friends table

    private  static final String table_name2="requestfriends";
    //private  static final String id="ID";
    private  static final String friends_1="FRIENDS_1";
    private  static final String friends_2="FRIENDS_2";
//loed user table
    private  static final String table_name3="loggeduser";
    private  static final String loguser1="LOGGEDUSER";
//frndstable
        private  static final String table_name4="friend_reqest_accept";
   private  static final String user="USER";
    private  static final String friends="FRIENDS";
//private String create_table="create table"+table_name2+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,FRIENDS_1 text,FRIENDS_2 text)";
  //ratingtable
private  static final String table_name5="RATING_TABLE";
  //  private  static final String user="USER";
    private  static final String smile="SMILE";
    private  static final String beauty="BEAUTY";
    private  static final String anger="ANGER";
    private  static final String nature="NATURE";
    private Context context;

   //private  static  final String create_table="create table"+table_name+"(ID INTEGER(10) PRIMARY KEY AUTOINCREMENT,NAME VARCHAR(30) NOT NULL,EMAIL VARCHAR(30) NOT NULL,USER_NAME VARCHAR(30) NOT NULL,PASSWORD VARCHAR(30) NOT NULL)";
    private  static final String drop_table1="DROP TABLE IF EXISTS "+table_name;
    private  static final String drop_table2="DROP TABLE IF EXISTS "+table_name2;
    private  static final String drop_table3="DROP TABLE IF EXISTS "+table_name3;

    private  static final String drop_table4="DROP TABLE IF EXISTS "+table_name4;
    private  static final String drop_table5="DROP TABLE IF EXISTS "+table_name5;
    public DbHelper(Context context) {
        super(context,dbname,null, version);
        //SQLiteDatabase db1=this.getWritableDatabase();
       this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
try{

    db.execSQL("create table "+table_name4+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,USER text ,FRIENDS text )");
    db.execSQL("create table "+table_name5+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,USER text ,SMILE text ,ANGER text ,BEAUTY text ,NATURE text )");

    db.execSQL("create table "+table_name3+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,LOGGEDUSER text )");
     db.execSQL("create table "+table_name+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME text ,EMAIL text NOT NULL,USER_NAME text UNIQUE ,PASSWORD text )");
   db.execSQL("create table "+table_name2+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,FRIENDS_1 text ,FRIENDS_2 text )");

  //  db.execSQL("create table "+table_name5+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,FRIENDS_1 text ,FRIENDS_2 text )");
    //db.execSQL("create table "+table_name6+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,FRIENDS_1 text ,FRIENDS_2 text )");
    Toast.makeText(context,"onCreate method is called",Toast.LENGTH_SHORT).show();
}
catch(Exception e)
{
    Toast.makeText(context,"EXCEPTION "+e,Toast.LENGTH_LONG).show();

}

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    try{
        Toast.makeText(context,"onupgrade method is called",Toast.LENGTH_SHORT).show();
      /* db.execSQL(drop_table1);
        db.execSQL(drop_table2);*/
        //db.execSQL(drop_table3);
      db.execSQL(drop_table4);
       onCreate(db);

    }catch (Exception e)
    { Toast.makeText(context,"EXCEPTION "+e,Toast.LENGTH_SHORT).show();
    }

    }
    public long insertData(UserDetails userDetails)

    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
      contentValues.put(name,userDetails.getName());
        contentValues.put(email,userDetails.getEmail());
        contentValues.put(user_name,userDetails.getUsername());
        contentValues.put(pass,userDetails.getPassword());

long rowid=db.insert(table_name,null,contentValues);
        return rowid;}

       public long insertData2(LogUser loguser) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues2=new ContentValues();
           contentValues2.put(loguser1,loguser.getLoguser());
           long rowid2=db.insert(table_name3, null, contentValues2);
           return rowid2;

       }
    public long insertData3(LogUser loguser,String value) {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues2=new ContentValues();
        contentValues2.put(friends_1,loguser.getLoguser());
        contentValues2.put(friends_2,value);
        long rowid3=db.insert(table_name2, null, contentValues2);
        return rowid3;

    }
    public long insertData4(LogUser loguser,String value) {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues2=new ContentValues();
        contentValues2.put(user,loguser.getLoguser());
        contentValues2.put(friends,value);
        long rowid4=db.insert(table_name4, null, contentValues2);
        contentValues2.put(friends,loguser.getLoguser());
        contentValues2.put(user,value);
         rowid4=db.insert(table_name4, null, contentValues2);

        return rowid4;

    }
    public long insertData5(RatingDetails ratingDetails,String value)

    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(user,value);
        contentValues.put(smile,ratingDetails.getSmile());
        contentValues.put(anger,ratingDetails.getAnger());
        contentValues.put(beauty,ratingDetails.getBeauty());
        contentValues.put(nature,ratingDetails.getNature());

        long rowid=db.insert(table_name5,null,contentValues);
        return rowid;}
    public boolean findmatch(String uname,String pass)
    {
        SQLiteDatabase db1=this.getReadableDatabase();
        Cursor cursor=db1.rawQuery("select * from "+table_name,null);

        Boolean result=false;
        if(cursor.getCount()==0)
        {
            Toast.makeText(context,"NO DATA FOUND",Toast.LENGTH_LONG).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                String username=cursor.getString(3);
                String password=cursor.getString(4);
                if(username.equals(uname) && password.equals(pass)){
                    result=true;
                    break;
                }



            }

        }
        return result;


    }
    public Cursor getpost()
    {
        SQLiteDatabase db2=this.getReadableDatabase();
        String ara="";
        Cursor cursor=getloggeduser();
        if(cursor.getCount()==0)
        {
            Toast.makeText(context,"NO DATA FOUND",Toast.LENGTH_LONG).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                ara=cursor.getString(1);
            }
        }
        String[] param=new String[]{ara};
        Cursor cursor1 =db2.rawQuery("Select * from "+table_name5+" where " +user+" = ? ",param);
        return cursor1;
    }
    public Cursor showfrnd()
    {
        SQLiteDatabase db2=this.getReadableDatabase();
        //  ArrayList<String> frnds = new ArrayList<>();
        String ara="";

        String name="";
        Cursor cursor=getloggeduser();

        if(cursor.getCount()==0)
        {
            Toast.makeText(context,"NO DATA FOUND",Toast.LENGTH_LONG).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                ara=cursor.getString(1);
            }
        }
        String[] param=new String[]{ara};
        Cursor cursor1 =db2.rawQuery("Select * from "+table_name+" where " +user_name+"!= ? ",param);
        return cursor1;
    }
    public Cursor showfrnd1()
    {
        SQLiteDatabase db2=this.getReadableDatabase();
      //  ArrayList<String> frnds = new ArrayList<>();
        String ara="";

        String name="";
        Cursor cursor=getloggeduser();

        if(cursor.getCount()==0)
        {
            Toast.makeText(context,"NO DATA FOUND",Toast.LENGTH_LONG).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                ara=cursor.getString(1);
            }
        }
String[] param=new String[]{ara};
        Cursor cursor1 =db2.rawQuery("Select * from "+table_name+" where " +user_name+"= ? ",param);
        if(cursor1.getCount()==0)
        {
            Toast.makeText(context,"NO DATA FOUND",Toast.LENGTH_LONG).show();
        }
        else {
            while (cursor1.moveToNext()) {
                name= cursor1.getString(1);

            }
        }

String[] param1=new String[]{name};
      Cursor cursor2 =db2.rawQuery("Select * from "+table_name2+" where " +friends_2+" = ? " ,param1);


        return cursor2;
    }
    public Cursor showfrnd2()
    {
        SQLiteDatabase db2=this.getReadableDatabase();

        String ara="";
        Cursor cursor=getloggeduser();

        if(cursor.getCount()==0)
        {
            Toast.makeText(context,"NO DATA FOUND",Toast.LENGTH_LONG).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                ara=cursor.getString(1);
            }
        }
        String[] param=new String[]{ara};
        Cursor cursor1 =db2.rawQuery("Select * from "+table_name4+" where " +user+" = ? ",param);
        return cursor1;
    }
    //loguser
    public boolean deletedata()
    {
        SQLiteDatabase db=this.getWritableDatabase();
       return db.delete(table_name3,"1",null)>0;

    }
    //frndrequest
    public boolean deletedatafrnd(String frnd)
    {
       SQLiteDatabase db=this.getWritableDatabase();
        String ara="";

        String name="";
        Cursor cursor=getloggeduser();

        if(cursor.getCount()==0)
        {
            Toast.makeText(context,"NO DATA FOUND",Toast.LENGTH_LONG).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                ara=cursor.getString(1);
            }
        }
        String[] param=new String[]{ara};
        Cursor cursor1 =db.rawQuery("Select * from "+table_name+" where " +user_name+"= ? ",param);
        if(cursor1.getCount()==0)
        {
            Toast.makeText(context,"NO DATA FOUND",Toast.LENGTH_LONG).show();
        }
        else {
            while (cursor1.moveToNext()) {
                name= cursor1.getString(1);

            }
        }
        return db.delete(table_name2,friends_1 + " = ? and "+friends_2+"=? ",new String[]{frnd,name} )>0;

    }//unfrnd
    public boolean deletedatafrnd1(String frnd)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String ara="";

        String name="";
        Cursor cursor=getloggeduser();

        if(cursor.getCount()==0)
        {
            Toast.makeText(context,"NO DATA FOUND",Toast.LENGTH_LONG).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                ara=cursor.getString(1);
            }
        }
        return db.delete(table_name4,user+ " = ? and "+friends +" =? ",new String[]{ara,frnd} )>0;
  //db.execSQL("delete from "+table_name4+" where "+user+" = "+ara+" and "+friends+"= "+frnd+";");
    }
    public boolean deletedatafrnd2(String frnd) {
        SQLiteDatabase db = this.getWritableDatabase();
        String ara = "";

        String name = "";
        Cursor cursor = getloggeduser();

        if (cursor.getCount() == 0) {
            Toast.makeText(context, "NO DATA FOUND", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                ara = cursor.getString(1);
            }
        }
        return db.delete(table_name4, user + " = ? and " + friends + " =? ", new String[]{frnd, ara}) > 0;
    }

    public Cursor getloggeduser()
    {
        SQLiteDatabase db2=this.getReadableDatabase();
        Cursor cursor =db2.rawQuery("Select * from "+table_name3,null);
        return cursor;
    }

}
