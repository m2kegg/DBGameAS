package ru.samsung.itschool.dbgame;

import java.io.File;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
	/*
	 * TABLES: ------- RESULTS SCORE INTEGER USER VARCHAR
	 */
	private Context context;
	private String DB_NAME = "game.db";

	private SQLiteDatabase db;

	private static DBManager dbManager;

	public static DBManager getInstance(Context context) {
		if (dbManager == null) {
			dbManager = new DBManager(context);
		}
		return dbManager;
	}

	private DBManager(Context context) {
		this.context = context;
		db = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
		createTablesIfNeedBe();
	}

	void addResult(String username, int score) {
		ContentValues contentValues = new ContentValues();
		contentValues.put("username", username);
		contentValues.put("score", score);
		db.insert("RESULTS", null, contentValues);
	}


	ArrayList<Result> getAllResults() {

		ArrayList<Result> data = new ArrayList<Result>();
		Cursor cursor = db.rawQuery("SELECT * FROM RESULTS;", null);
		//Cursor cursor = db.rawQuery("SELECT * FROM RESULTS WHERE USERNAME = ? ORDER BY ? DESC", new String[]{"Player One", "SCORE"});
		//Cursor cursor = db.query("RESULTS", new String[]{"USERNAME", "SCORE"}, null, null, null, null,"MAX(SCORE) DESC");
		boolean hasMoreData = cursor.moveToFirst();

		while (hasMoreData) {
			String name = cursor.getString(cursor.getColumnIndex("USERNAME"));
			int score = Integer.parseInt(cursor.getString(cursor
					.getColumnIndex("SCORE")));
			data.add(new Result(name, score));
			hasMoreData = cursor.moveToNext();
		}

		return data;
	}

	private void createTablesIfNeedBe() {
		db.execSQL("CREATE TABLE IF NOT EXISTS RESULTS (USERNAME TEXT, SCORE INTEGER);");
	}

	private boolean dbExist() {
		File dbFile = context.getDatabasePath(DB_NAME);
		return dbFile.exists();
	}

	ArrayList<Result> getHighResults() {

		ArrayList<Result> data = new ArrayList<Result>();
		Cursor cursor = db.rawQuery("SELECT USERNAME, MAX(SCORE) FROM RESULTS GROUP BY USERNAME;", null);
		boolean hasMoreData = cursor.moveToFirst();

		while (hasMoreData) {
			String name = cursor.getString(cursor.getColumnIndex("USERNAME"));
			int score = Integer.parseInt(cursor.getString(cursor
					.getColumnIndex("MAX(SCORE)")));
			data.add(new Result(name, score));
			hasMoreData = cursor.moveToNext();
		}
		return data;
	}

	ArrayList<Result> getAvgResults() {

		ArrayList<Result> data = new ArrayList<Result>();
		Cursor cursor = db.rawQuery("SELECT USERNAME, AVG(SCORE) FROM RESULTS GROUP BY USERNAME;", null);
		boolean hasMoreData = cursor.moveToFirst();

		while (hasMoreData) {
			String name = cursor.getString(cursor.getColumnIndex("USERNAME"));
			double score = Double.parseDouble(cursor.getString(cursor
					.getColumnIndex("AVG(SCORE)")));
			data.add(new Result(name, (int)score));
			hasMoreData = cursor.moveToNext();
		}
		return data;
	}
}
