package com.example.rssmonster;

import com.example.rssmonster.app.AppIntent;
import com.example.rssmonster.data.FeedContract;
import com.example.rssmonster.data.FeedDbHelper;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.TextView;

public class ArticleActivity extends Activity {
	private TextView titleView;
	private TextView dateView;
	private TextView descriptionView;
	private TextView contentView;
	private TextView linkView;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_article);

		titleView = (TextView) findViewById(R.id.article_title);
		dateView = (TextView) findViewById(R.id.article_date);
		descriptionView = (TextView) findViewById(R.id.article_description);
		contentView = (TextView) findViewById(R.id.article_content);
		linkView = (TextView) findViewById(R.id.article_link);
	
	}

	@Override
	protected void onStart() {
		super.onStart();

		final long id = getIntent().getLongExtra(AppIntent.EXTRA_ARTICLE_ID, -1);
		
		loadArticle(id);
	}

	private void loadArticle(long id) {
		final FeedDbHelper dbHelper = new FeedDbHelper(this);
		final SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		final String table = FeedContract.Articles.TABLE_NAME;
		final String[] columns = null;
		final String selection = FeedContract.Articles._ID + "=?";
		final String[] selectionArgs = new String[]{ ""+id };
		final String groupBy = null;
		final String having = null;
		final String orderBy = null;
		
		final Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
		cursor.moveToFirst();
		
		final String title = cursor.getString(cursor.getColumnIndex(FeedContract.Articles.TITLE));
		titleView.setText(title);
		
		final long pubdate = cursor.getLong(cursor.getColumnIndex(FeedContract.Articles.PUB_DATE));
		dateView.setText(DateUtils.getRelativeTimeSpanString(this, pubdate));
		
		final String description = cursor.getString(cursor.getColumnIndex(FeedContract.Articles.DESCRIPTION));
		descriptionView.setText(description);
		
		final String content = cursor.getString(cursor.getColumnIndex(FeedContract.Articles.CONTENT));
		contentView.setText(content);
		
		final String link = cursor.getString(cursor.getColumnIndex(FeedContract.Articles.LINK));
		descriptionView.setText(description);
		
		
		cursor.close();
	}

	private CharSequence parseDate(long pubdate) {
		// TODO Auto-generated method stub
		return null;
	}

}
