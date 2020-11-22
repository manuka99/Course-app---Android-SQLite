package com.lab.finallab.database;/**
 * Created by Manuka yasas,
 * University Sliit
 * Email manukayasas99@gmail.com
 **/

import android.provider.BaseColumns;

/**
 * Created by Manuka yasas,
 * University Sliit
 * Email manukayasas99@gmail.com
 **/
public class MessageMaster {
    private MessageMaster(){}

    public static class Message implements BaseColumns {
        public static final String TABLE_NAME = "Messages";
        public static final String USER_ID = "User_ID";
        public static final String SUBJECT = "Subject";
        public static final String MESSAGE = "Message";
    }
}
