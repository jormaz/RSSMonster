package com.example.rssmonster.data;

import java.sql.Date;

import java.util.HashMap;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.Time;
import android.util.Log;

import java.util.ArrayList;

import com.example.rssmonster.data.FeedContract.Articles;

public class DummyFeeds {
	//private ArrayList<HashMap<String, Object>> db ;
	//public DummyFeeds(SQLiteDatabase db) {
	public static void insertDummyFeeds (SQLiteDatabase db) {
		/*HasMap
		db = new ArrayList<HashMap<String, Object>>();
		addFeed("Art’culo 1", "2012-10-13T16:00:00.000Z");
		addFeed("Otro art’culo", "2012-10-13T16:00:00.000Z");
		addFeed("M‡s de lo mismo", "2012-10-17T15:00:00.000Z");
		addFeed("Otro", "2012-10-13T10:30:00.000Z");
		*/
		/*BDD*/		 
		//Parámetro SQLiteDatabase db,
		/*
		addFeed(db, "Artículo 1", "2012-10-13T16:00:00.000Z");
		addFeed(db, "Artículo 2", "2012-10-13T16:00:00.000Z");
		addFeed(db, "Artículo 3", "2012-10-17T15:00:00.000Z");
		*/
		
		for(int i=1;i<=10;i++){
			/*
			Date curDate = new Date();
			String sCurdate = String.valueOf(curDate);
			long curMillis = curDate.Date();
			*/
             		
			/*long lDateTime = new Date().getTime();			
			String sCurDate = String.valueOf(lDateTime);
			*/
			
			Long millis = parseDate("2012-10-17T15:00:00.000Z");
			String sCurDate = String.valueOf(millis);
			
			
			String[] bindArgs = new String[]{"Title-"+i,"Link"+i,sCurDate,"Description"+i,"content"+i,};
			 ContentValues values = new ContentValues();
		        values.put(Articles.TITLE, bindArgs[0]);
		        values.put(Articles.LINK, bindArgs[1]);
		        values.put(Articles.PUB_DATE, bindArgs[2]);		        
		        values.put(Articles.DESCRIPTION, bindArgs[3]);
		        values.put(Articles.CONTENT, bindArgs[4]);
		    long id = db.insert(Articles.TABLE_NAME, null, values );			
			Log.i("FeedDbHelper.onCreate","He insertado el registro: "+i);
		
		
		}
	}
	/*
	public ArrayList<HashMap<String, Object>> getDummyFeeds(){
		return this.db;
	}
	*/
	public static void addFeed(SQLiteDatabase db,String title, String date) {
		/*HashMap
		 HashMap <String,Object> feedMap = new HashMap<String,Object>();
		 feedMap.put(FeedContract.Articles.TITLE,title);
		
		 long millis = parseDate(date);
		 feedMap.put(FeedContract.Articles.PUB_DATE, millis);
		 this.db.add(feedMap);
		*/
		/*BDD*/
		//Parámetro SQLiteDatabase db,
		ContentValues values = new ContentValues();
		
		values.put(FeedContract.Articles.TITLE, title);
		
		Long millis = parseDate(date);
		values.put(FeedContract.Articles.PUB_DATE, millis);

		db.insert(FeedContract.Articles.TABLE_NAME, null, values);
		
	}

	private static Long parseDate(String date) {
		Time time = new  Time();
		time.parse3339(date);
		Long millis = time.normalize(false);
		return millis;
	}
}


