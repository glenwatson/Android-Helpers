package com.glen_watson.sqlite;

import java.util.Date;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class StatsTable extends Table {
	private static final int MILLISECONDS = 1000;
	private static final String TABLE_NAME = "stats";
	private static final String STAT_NAME_COLUMN = "name";
	private static final String STAT_VALUE_COLUMN = "value";
	
	@Override
	protected int getVersion() {
		return 1;
	}
	
	@Override
	protected String getName() {
		return TABLE_NAME;
	}
	
	@Override
	protected void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE "+TABLE_NAME+" ("+
				STAT_NAME_COLUMN + " TEXT NOT NULL, " +
				STAT_VALUE_COLUMN + " INTEGER NOT NULL" +
				")"
		);
		populate(db);
	}
	
	protected static void populate(SQLiteDatabase db) {
		/*
			Initialize your stats, e.g.-
			ContentValues value = new ContentValues();
			value.put(STAT_VALUE_COLUMN, 0); //default
			value.put(STAT_NAME_COLUMN, GAMES_STAT_NAME);
			db.insert(TABLE_NAME, null, value);
		*/
	}
	
	protected static int getInt(String statName, SQLiteDatabase db) {
		Cursor c = db.query(TABLE_NAME, new String[]{STAT_VALUE_COLUMN}, STAT_NAME_COLUMN+"=?", new String[]{statName}, null, null, "1");
		int result = -1;
		if(c.moveToNext())
			result = c.getInt(0);
		c.close();
		return result;
	}
	
	protected static boolean getBoolean(String statName, SQLiteDatabase db) {
		return toBoolean(getInt(statName, db));
	}
	
	protected static Date getDate(String statName, SQLiteDatabase db) {
		return new Date(getInt(statName, db) * MILLISECONDS);
	}
	
	
	protected static int setInt(String statName, int statValue, SQLiteDatabase writableDb) {
		ContentValues row = new ContentValues();
		row.put(STAT_VALUE_COLUMN, statValue);
		writableDb.update(TABLE_NAME, row, STAT_NAME_COLUMN+"=?", new String[]{statName});
		return statValue;
	}
	
	protected static int incrementInt(String statName, SQLiteDatabase db) {
		int newStatValue = getInt(statName, db) + 1;
		return setInt(statName, newStatValue, db);
	}
	
	protected static void setBoolean(String statName, boolean statValue, SQLiteDatabase db) {
		setInt(statName, fromBoolean(statValue), db);
	}
	
	protected static boolean flipBoolean(String statName, SQLiteDatabase db) {
		boolean newStatValue = !toBoolean(getInt(statName, db));
		setInt(statName, fromBoolean(newStatValue), db);
		return newStatValue;
	}
	
	protected static void setDate(String statName, Date statValue, SQLiteDatabase db) {
		setInt(statName, (int) (statValue.getTime() / MILLISECONDS), db);
	}
	
	protected static void setToNow(String statName, SQLiteDatabase db) {
		setDate(statName, new Date(), db);
	}
	
	@Override
	protected String dump(SQLiteDatabase db) {
		return null;
	}
	
}
