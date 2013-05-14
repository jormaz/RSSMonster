package com.example.rssmonster.widget;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.text.format.DateUtils;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import com.example.rssmonster.data.FeedContract;
import com.example.rssmonster.data.FeedContract.Articles;
import com.example.rssmonster.data.FeedDbHelper;
import com.example.rssmonster.R;

import android.net.Uri;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

public class FeedsAdapter extends SimpleCursorAdapter {
	private static final String[] FROM = new String[] { FeedContract.Articles.TITLE, FeedContract.Articles.PUB_DATE };
	private static final int[] TO = new int[] { R.id.feed_listitem_title,
			R.id.feed_listitem_date };
	private Context context;
	
	/*HashTable: FeedAdapter hereda de SimpleAdapter
	 
	public FeedsAdapter(Context context, ArrayList<HashMap<String, Object>> data)
			//Context context, List<? extends Map<String, ?>> data) 
	{
		//super(context, R.layout.feed_listitem , null, FROM, TO, FLAG_REGISTER_CONTENT_OBSERVER);
		super(context, data, R.layout.feed_listitem , FROM, TO);
		
		this.context = context;
		initArticlesCursor(context);
	}
	*/
	
	/*BDD*/
	public FeedsAdapter(Context context) {
		super(context, R.layout.feed_listitem , null, FROM, TO, FLAG_REGISTER_CONTENT_OBSERVER);
		
		this.context = context;
		initArticlesCursor(context);
	}
 
	private void initArticlesCursor(Context context) {
		/* Solución inicial
		Uri uri = Articles.getUri();
		String[] projection = new String[]{FeedContract.Articles._ID, FeedContract.Articles.TITLE, FeedContract.Articles.PUB_DATE};
		String selection = null;
		String[] selectionArgs = null;
		String sortOrder = FeedContract.Articles.PUB_DATE + " ASC";
		
		final Cursor cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
		
		*/
		
		
		FeedDbHelper helper = new FeedDbHelper(this.context );
		SQLiteDatabase db = helper.getReadableDatabase();
		String table = Articles.TABLE_NAME;
		String[] columns = new String[] { Articles._ID, Articles.TITLE, Articles.PUB_DATE };
		String selection = null; //WHERE edad < 1; -- ?
		String[] selectionArgs = null; //Cuando utilizamos el interrogante en el Where ponemos un String[] con las condiciones de filtrado
		String orderBy = Articles.PUB_DATE + " DESC";
		String groupBy = null;
		String having = null;
		final Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
		this.changeCursor(cursor);
		
		
	}
	
	@Override
	public void setViewText(TextView v, String text) {
		if(isDateView(v)) {
			text = getFormattedDate(text);
		}
		super.setViewText(v, text);
	}

	private boolean isDateView(TextView v) {
		/*int myId ; 
		myId = v.getId();*/
		return v.getId() == R.id.feed_listitem_date;
	}
	
	private String getFormattedDate(String text) {
		Long millis = Long.parseLong(text);	
		Date date = new Date();
		date.setTime(millis);
		
		//Long millis = Long.valueOf(text);
		return date.toString();
		
		//return (String)DateUtils.getRelativeTimeSpanString(context, millis);
		
	}

}
