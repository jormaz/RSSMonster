package com.example.rssmonster.data;

import java.util.HashMap;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.Time;

import java.util.ArrayList;

public class DummyFeeds extends ArrayList<HashMap<String, Object>>{
	private ArrayList<HashMap<String, Object>> db ;
	public DummyFeeds() {
		/*HasMap*/
		db = new ArrayList<HashMap<String, Object>>();
		addFeed("Art’culo 1", "2012-10-13T16:00:00.000Z");
		addFeed("Otro art’culo", "2012-10-13T16:00:00.000Z");
		addFeed("M‡s de lo mismo", "2012-10-17T15:00:00.000Z");
		addFeed("Otro", "2012-10-13T10:30:00.000Z");
		
		/*BDD		 
		//Parámetro SQLiteDatabase db,
		addFeed(db, "Art’culo 1", "2012-10-13T16:00:00.000Z");
		addFeed(db, "Otro art’culo", "2012-10-13T16:00:00.000Z");
		addFeed(db, "M‡s de lo mismo", "2012-10-17T15:00:00.000Z");
		addFeed(db, "Otro", "2012-10-13T10:30:00.000Z");
		*/
	}
	public ArrayList<HashMap<String, Object>> getDummyFeeds(){
		return this.db;
	}
	
	public void addFeed(String title, String date) {
		/*HashMap*/
		 HashMap <String,Object> feedMap = new HashMap<String,Object>();
		 feedMap.put(FeedContract.Articles.TITLE,title);
		 
		 long millis = parseDate(date);
		 feedMap.put(FeedContract.Articles.PUB_DATE, millis);
		 this.db.add(feedMap);
		/*BDD
		//Parámetro SQLiteDatabase db,
		ContentValues values = new ContentValues();
		
		values.put(FeedContract.Articles.TITLE, title);
		
		Long millis = parseDate(date);
		values.put(FeedContract.Articles.PUB_DATE, millis);

		db.insert(FeedContract.Articles.TABLE_NAME, null, values);
		*/
	}

	private static Long parseDate(String date) {
		Time time = new  Time();
		time.parse3339(date);
		Long millis = time.normalize(false);
		return millis;
	}
}


