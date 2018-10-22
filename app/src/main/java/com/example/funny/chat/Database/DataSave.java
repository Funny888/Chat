package com.example.funny.chat.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataSave extends SQLiteOpenHelper {
    public static final String NAME_DB = "ChatDB", TABLE_Udata = "Users", TABLE_Gdata = "ChatGrp", TABLE_Mdata = "Messages";
    public static final  int VERSION = 1;

    public static final String _ID = "_id";
    public static final String PROFIMAGE = "ProfImage";
    public static final String NAME = "Name";
    public static final String FAMILY = "Family";
    public static final String PATRONYMIC = "Patronymic";
    public static final String LOGIN = "Login";
    public static final String PASSWORD = "Password";
    public static final String E_MAIL = "e_mail";


    public static final String _ID_USER = "_idUser";
    public static final String _ID_GRP = "_idGrp";
    public static final String _RELAT_WITH_USER = "_relatWithUser";



    public static final String _ID_MSG = "_idMsg";
    public static final String CHAT_NAME = "ChatName";
    public static final String FROM_USER = "FromUser";
    public static final String TO_USER = "ToUser";
    public static final String DATE_MSG = "DateMsg";
    public static final String TEXT_MSG = "TextMsg";
    public static final String ANSWER = "ANSWER";





    public DataSave(Context context) {
        super(context, NAME_DB,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_Udata + " ( " + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + PROFIMAGE + " TEXT,"
                +  NAME + " CHAR NOT NULL, " + FAMILY + " CHAR, " + PATRONYMIC + "CHAR,"
                + E_MAIL + " TEXT NOT NULL, " + ANSWER + "  TEXT NOT NULL);");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_Gdata + " (" + _ID_GRP + " INTEGER PRIMARY KEY AUTOINCREMENT," + PROFIMAGE +  " TEXT," + TEXT_MSG + " TEXT," +
                CHAT_NAME + " TEXT, " + DATE_MSG + " DATETIME, " + FROM_USER + " int(5), " + TO_USER + " int(5) );");

        sqLiteDatabase.execSQL("CREATE  TABLE " + TABLE_Mdata + " (" + _ID_MSG + " INTEGER PRIMARY KEY AUTOINCREMENT, " + _ID_GRP + " int(5), " + PROFIMAGE + " TEXT," +
                DATE_MSG + " DATETIME," + FROM_USER + " int(5), " + TO_USER + " int(5)  );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
