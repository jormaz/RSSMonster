package com.example.rssmonster;

import com.example.rssmonster.app.AppIntent;
import com.example.rssmonster.app.RssIntent;
import com.example.rssmonster.data.DummyFeeds; 
import com.example.rssmonster.widget.FeedsAdapter;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
//import android.content.Intent;
import android.graphics.Point;
//import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;



public class ArticleListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.article_list);
		
		/*Hash
		DummyFeeds dummy = new DummyFeeds();
		final FeedsAdapter adapter = new FeedsAdapter(this, dummy.getDummyFeeds());
		*/
		final FeedsAdapter adapter = new FeedsAdapter(this);		
		setListAdapter(adapter);
		
		
		/* Prácticas
		ListView listView =(ListView) findViewById(R.id.listView1);
		String[] datos = getResources().getStringArray(R.array.datos);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R .layout.simple_list_item_1,datos); 
		//adapter.addAll(datos);//Se puede quitar
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {			 
			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3) {
				Toast.makeText(ArticleListActivity .this, 
						(String)adapter.getItemAtPosition(position) , 
						Toast.LENGTH_LONG).show();
			}
		});
		*/

	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = AppIntent.getArticleIntent(id);
		
		startActivity(intent);
	}
	

	//...
	
	private static final int MNU_OPC1 = 1;
	private static final int MNU_OPC2 = 2;
	
	 
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    //Alternativa 2
		
	   
	    if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
	        // only for android older than HoneyComb
	    	 menu.add(Menu.NONE, MNU_OPC1, Menu.NONE,R.string .title_activity_setting)
	            .setIcon(android.R.drawable.ic_menu_preferences);
	    
	    	 menu.add(Menu.NONE, MNU_OPC2, Menu.NONE, R.string.title_activity_about)
	            .setIcon(R.drawable.abouticon);
	   }else{
		   menu.add(Menu.NONE, MNU_OPC1, Menu.NONE,R.string .title_activity_setting)
           .setIcon(android.R.drawable.ic_menu_preferences).setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);	
   
		   menu.add(Menu.NONE, MNU_OPC2, Menu.NONE, R.string.title_activity_about)
           .setIcon(R.drawable.abouticon).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
	   }
	    
	    getMenuInflater().inflate(R.menu.article_list, menu);
	  
	    return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case MNU_OPC1:	            
	            return true;
	        case MNU_OPC2:{
	        	Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);  
				vibrator.vibrate(100);  
				//Alternativa a la redirección del MainActivity
				//Context context = this;
				final Activity context = this;	
				
				//Intent intent = new Intent( context,AboutActivity.class);
				//startActivity(intent);
				
				//ShowPopUp Window
				int popupWidth =context.getResources().getDimensionPixelSize( R.dimen.logo)*2;				
				int popupHeight = popupWidth;
				// Inflate the popup_layout.xml
				   LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popupAbout);
				   LayoutInflater layoutInflater = (LayoutInflater) context
				     .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				   View layout = layoutInflater.inflate(R.layout.about  , viewGroup);
				 
				// Creating the PopupWindow
				   final PopupWindow popup = new PopupWindow(context);
				   popup.setContentView(layout);
				   popup.setWidth(popupWidth);
				   popup.setHeight(popupHeight);
				   popup.setFocusable(true);
				   //Comentario
				   //Comentario2
				
				// Displaying the popup at the specified location, + offsets.
				   Point p = new Point(30,30);
				   int OFFSET_X = 0;
				   int OFFSET_Y = 0;
				// Clear the default translucent background
				  // popup.setBackgroundDrawable(new BitmapDrawable());
				 
				// Displaying the popup at the specified location, + offsets.   
				   popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);
				 
				   
	            return true;
	        }
	        default:
	            return super.onOptionsItemSelected(item);
	    }	    
	}

}
	
