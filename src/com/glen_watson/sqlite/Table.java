package com.glen_watson.sqlite;

import android.database.sqlite.SQLiteDatabase;

public abstract class Table {
	
	protected abstract int getVersion();
	
	protected abstract String getName();
	
	protected abstract String dump(SQLiteDatabase db);
	
	/**
	 * Default creates a table with no columns
	 * @param db - the DB to act on
	 */
	protected void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE "+getName());
	}
	
	/**
	 * Default drops the table
	 * @param db - the DB to act on
	 * @param oldVersion
	 * @param newVersion
	 */
	protected void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS "+getName());
		onCreate(db);
	}
	
	protected static boolean toBoolean(int i) {
		return i == 0;
	}
	
	protected static int fromBoolean(boolean b) {
		return b ? 0 : 1;
	}
}