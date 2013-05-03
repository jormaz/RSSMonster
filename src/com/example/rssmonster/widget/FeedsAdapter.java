package com.example.rssmonster.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.text.format.DateUtils;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import com.example.rssmonster.data.FeedContract;
import com.example.rssmonster.data.FeedContract.Articles;
import com.example.rssmonster.R;

import android.net.Uri;
import android.database.Cursor;
import android.widget.TextView;

public class FeedsAdapter extends SimpleAdapter {
	private static final String[] FROM = new String[] { FeedContract.Articles.TITLE, FeedContract.Articles.PUB_DATE };
	private static final int[] TO = new int[] { R.id.feed_listitem_title,
			R.id.feed_listitem_date };
	private Context context;
	
	public FeedsAdapter(Context context, ArrayList<HashMap<String, Object>> data)
			//Context context, List<? extends Map<String, ?>> data) 
	{
		//super(context, R.layout.feed_listitem , null, FROM, TO, FLAG_REGISTER_CONTENT_OBSERVER);
		super(context, data, R.layout.feed_listitem , FROM, TO);
		
		this.context = context;
		//initArticlesCursor(context);
	}
	/*BDD
	private void initArticlesCursor(Context context) {
		
		Uri uri = Articles.getUri();
		String[] projection = new String[]{FeedContract.Articles._ID, FeedContract.Articles.TITLE, FeedContract.Articles.PUB_DATE};
		String selection = null;
		String[] selectionArgs = null;
		String sortOrder = FeedContract.Articles.PUB_DATE + " ASC";
		
		final Cursor cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
		this.changeCursor(cursor);
		
	}
	*/
	@Override
	public void setViewText(TextView v, String text) {
		if(isDateView(v)) {
			text = getFormattedDate(text);
		}
		super.setViewText(v, text);
	}

	private boolean isDateView(TextView v) {
		return v.getId() == R.id.feed_listitem_date;
	}
	
	private String getFormattedDate(String text) {
		Long millis = Long.parseLong(text);
		
		return (String)DateUtils.getRelativeTimeSpanString(context, millis);
	}

}
