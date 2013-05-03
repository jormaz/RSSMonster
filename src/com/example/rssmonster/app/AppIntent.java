package com.example.rssmonster.app;

import android.content.Intent;

public class AppIntent extends Intent {

	public static final String ACTION_LIST_ARTICLES = "jormaz.intent.action.LIST_ARTICLES";
	public static final String ACTION_LIST_ABOUT = "jormaz.intent.action.ABOUT";
	public static final String ACTION_VIEW_ARTICLE = "jormaz.intent.action.VIEW_ARTICLE";
	public static final String ACTION_SYNC_ARTICLES = "jormaz.intent.action.SYNC_ARTICLES";
	
	public static final String EXTRA_ARTICLE_ID = "jormaz.intent.EXTRA_ID";
	
	public static Intent getArticleListIntent() {
		return new Intent(ACTION_LIST_ARTICLES);
	}

	public static Intent getAboutIntent() {
		return new Intent(ACTION_LIST_ABOUT);
	}

	public static Intent getArticleIntent(long id) {
		Intent intent = new Intent(ACTION_VIEW_ARTICLE);
		intent.putExtra(EXTRA_ARTICLE_ID, id);

		return intent;
	}

	public static Intent getSyncArticlesIntent() {
		return new Intent(ACTION_SYNC_ARTICLES);
	}
}
