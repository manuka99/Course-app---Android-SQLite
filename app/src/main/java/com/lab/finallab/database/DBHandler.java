package com.lab.finallab.database;/**
 * Created by Manuka yasas,
 * University Sliit
 * Email manukayasas99@gmail.com
 **/

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.lab.finallab.model.Message;
import com.lab.finallab.model.Users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Manuka yasas,
 * University Sliit
 * Email manukayasas99@gmail.com
 **/
public class DBHandler extends SQLiteOpenHelper {

    private static final String TAG = "DBHandler";
    private static final String COURSE_WEB = "Course_web";

    public DBHandler(@Nullable Context context) {
        super(context, COURSE_WEB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + MessageMaster.Message.TABLE_NAME + " ( "
                + MessageMaster.Message._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MessageMaster.Message.USER_ID + " INTEGER, "
                + MessageMaster.Message.SUBJECT + " TEXT, "
                + MessageMaster.Message.MESSAGE + " TEXT "
                + " ) ");

        sqLiteDatabase.execSQL("CREATE TABLE " + UserMaster.User.TABLE_NAME + " ( "
                + UserMaster.User._ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + UserMaster.User.NAME + " TEXT, "
                + UserMaster.User.PASSWORD + " TEXT, "
                + UserMaster.User.TYPE + " TEXT "
                + " ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_TABLE_USER_QUERY = "DROP TABLE IF EXISTS " + UserMaster.User.TABLE_NAME;
        String DROP_TABLE_MESSAGE_QUERY = "DROP TABLE IF EXISTS " + MessageMaster.Message.TABLE_NAME;
        sqLiteDatabase.execSQL(DROP_TABLE_MESSAGE_QUERY);
        sqLiteDatabase.execSQL(DROP_TABLE_USER_QUERY);
        onCreate(sqLiteDatabase);
    }

    public boolean register(String name, String password, String type) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(UserMaster.User.NAME, name);
        contentValues.put(UserMaster.User.PASSWORD, password);
        contentValues.put(UserMaster.User.TYPE, type);

        long val = sqLiteDatabase.insert(UserMaster.User.TABLE_NAME, null, contentValues);
        if (val > 0)
            return true;
        else
            return false;
    }

    public Users login(String name, String password) {
        Users user = new Users();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(UserMaster.User.TABLE_NAME, new String[]{UserMaster.User._ID, UserMaster.User.NAME, UserMaster.User.PASSWORD, UserMaster.User.TYPE},
                UserMaster.User.NAME + " =? and " + UserMaster.User.PASSWORD + " =? ", new String[]{name, password}, null, null, null);
        if (cursor.moveToFirst()) {
            user.set_id(cursor.getString(0));
            user.setName(cursor.getString(1));
//            user.setPassword(cursor.getString(0));
            user.setType(cursor.getString(3));
        }
        return user;
    }


    public boolean addMessage(Message message) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MessageMaster.Message.SUBJECT, message.getSubject());
        contentValues.put(MessageMaster.Message.MESSAGE, message.getMessage());
        contentValues.put(MessageMaster.Message.USER_ID, message.getUser_ID());

        long val = sqLiteDatabase.insert(MessageMaster.Message.TABLE_NAME, null, contentValues);
        if (val > 0)
            return true;
        else
            return false;
    }


    public List<Message> getMessages() {
        List<Message> messages = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(MessageMaster.Message.TABLE_NAME, new String[]{MessageMaster.Message._ID, MessageMaster.Message.USER_ID, MessageMaster.Message.SUBJECT, MessageMaster.Message.MESSAGE},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Message message = new Message();
                message.set_id(cursor.getString(0));
                message.setUser_ID(cursor.getString(1));
                message.setSubject(cursor.getString(2));
                message.setSubject(cursor.getString(3));
                messages.add(message);
            } while (cursor.moveToNext());
        }
        return messages;
    }

    public Message getMessage(String id) {
        Message message = new Message();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(MessageMaster.Message.TABLE_NAME, new String[]{MessageMaster.Message._ID, MessageMaster.Message.USER_ID, MessageMaster.Message.SUBJECT, MessageMaster.Message.MESSAGE},
                MessageMaster.Message._ID + " = ? ", new String[]{id}, null, null, null);
        if (cursor.moveToFirst()) {
            message.set_id(cursor.getString(0));
            message.setUser_ID(cursor.getString(1));
            message.setSubject(cursor.getString(2));
            message.setMessage(cursor.getString(3));
        }
        return message;
    }

}
