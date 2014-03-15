package com.glen_watson.sqlite;

import com.glen_watson.App;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {
	
	private static final String NAME = "smess";
	private static final int VERSION = 1;
	private Table[] tables = new Table[]{
		/* your Table classes here */
	};
	
	public DB(Context context) {
		super(context, NAME, null, VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		for(Table table : tables) {
			table.onCreate(db);
		}
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		for(Table table : tables) {
			table.onUpgrade(db, oldVersion, newVersion);
		}
	}
	
	public void dump() {
		SQLiteDatabase db = this.getReadableDatabase();
		App.d("\r\n-------------------------------------------------------------\r\n");
		for(Table table : tables) {
			App.d(table.dump(db));
		}
		App.d("\r\n-------------------------------------------------------------\r\n");
	}
}
