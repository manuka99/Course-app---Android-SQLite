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
public class UserMaster {
    private UserMaster(){}

    public static class User implements BaseColumns {
        public static final String TABLE_NAME = "User";
        public static final String NAME = "Name";
        public static final String PASSWORD = "Password";
        public static final String TYPE = "Type";
        public static final String TYPE_TEACHER = "teacher";
        public static final String TYPE_STUDENT = "student";
    }
}
