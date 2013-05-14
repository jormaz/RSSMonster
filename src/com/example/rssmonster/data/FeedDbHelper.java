package com.example.rssmonster.data;



import com.example.rssmonster.data.FeedContract.Articles;
import com.example.rssmonster.data.DummyFeeds;
import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import android.database.sqlite.SQLiteDatabase;

public class FeedDbHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 2;

	public FeedDbHelper(Context context) {
		super(context, FeedContract.DB_NAME, null, DATABASE_VERSION );
	}

	public FeedDbHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	public FeedDbHelper(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("DROP TABLE IF EXISTS " + Articles.TABLE_NAME);
		db.execSQL("CREATE TABLE " + Articles.TABLE_NAME + "("
					+ Articles._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ Articles.TITLE + " STRING,"
					+ Articles.LINK + " STRING,"
					+ Articles.PUB_DATE + " LONG,"
					+ Articles.DESCRIPTION + " STRING,"
					+ Articles.CONTENT + " STRING"
					+")"
				);
		DummyFeeds.insertDummyFeeds(db);
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
}
