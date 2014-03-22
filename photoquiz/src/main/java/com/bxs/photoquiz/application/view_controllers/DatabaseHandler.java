package com.bxs.photoquiz.application.view_controllers;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "contactsManager";

	// Table name
	private static final String TABLE_CONTACTS = "contacts";



	// Table Columns for level table
    private static final String COLUMN_LEVEL_ID = "levelId";

    // Table columns for question table
	private static final String COLUMN_QUES_ID = "quesId";
	private static final String COLUMN_QUES_TEXT = "quesText";
    private static final String COLUMN_QUES_ANS_OPTION_ID = "quesAnsOptionId";
    private static final String COLUMN_QUES_IMAGE_URL = "quesImageURL";

    // Table columns for options table
    private static final String COLUMN_OPTION_ID = "optionId";
    private static final String COLUMN_OPTION_TEXT = "optionText";





	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

		// Create tables again
		onCreate(db);
	}


}
