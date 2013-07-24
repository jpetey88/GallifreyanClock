package com.jpetey88.galifreyanclockwidget.settings;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Spinner;

import com.jpetey88.galifreyanclockwidget.GalefreyClock;
import com.jpetey88.galifreyanclockwidget.GalifreyanClockView;
import com.jpetey88.galifreyanclockwidget.R;

public class SettingsActivity extends Activity {
    private final String LOG_TAG = "Clocky";
    public static final String PREFS_NAME = "MyPrefsFile";
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences);
        // Display the fragment as the main content.
        Button ok = (Button) findViewById(R.id.okbutton);
        ok.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                	int mAppWidgetId = 0;
            		Intent intent = getIntent();
            		Bundle extras = intent.getExtras();
            		if (extras != null) {
            		    mAppWidgetId = extras.getInt(
            		            AppWidgetManager.EXTRA_APPWIDGET_ID, 
            		            AppWidgetManager.INVALID_APPWIDGET_ID);
            		}
            		if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID){
                        finish();
                    //If the user press BACK, do not add any widget.
                    setResult(RESULT_CANCELED);
                  
            		}else{
            			  final Context context = SettingsActivity.this;
                  		
            		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            	//	RemoteViews views = new RemoteViews(context.getPackageName(),
            	//			R.layout.defaultlayout);
                   		//		appWidgetManager.updateAppWidget(mAppWidgetId, views);
            			      ComponentName thisAppWidget = new ComponentName(context.getPackageName(),
            			                                     GalefreyClock.class.getName());
            				Intent firstUpdate = new Intent(context, GalefreyClock.class);
            				SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
            				SharedPreferences.Editor editor = prefs.edit();
            				
            				Spinner mySpinner = (Spinner)findViewById(R.id.ColorSelect);
            				
            				
            				editor.putString("SymbolColor", mySpinner.getSelectedItem().toString());
            				Log.println(Log.ERROR, "Settings", mySpinner.getSelectedItem().toString());
            				editor.commit();
            			      int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);
            			      firstUpdate.setAction("android.appwidget.action.APPWIDGET_UPDATE");
            			      firstUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            			      context.sendBroadcast(firstUpdate);
            				
            				
            				Intent resultValue = new Intent();
            		resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
            		setResult(RESULT_OK, resultValue);
            		finish();
            		}
                
                }});
        
    }
	
	 /**
	    * Utility method to ensure that when we want an Intent that fire ACTION_APPWIDGET_UPDATE, the extras are correct.<br>
	    * The default implementation of onReceive() will discard it if we don't add the ids of all the instances.
	    * @param context
	    * @return
	    */
	   protected Intent get_ACTION_APPWIDGET_UPDATE_Intent(Context context)
	      {
	      AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
	      ComponentName thisAppWidget = new ComponentName(context.getPackageName(),
	                                     GalefreyClock.class.getName());
	      int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);
	      Intent intent = new Intent(context, GalefreyClock.class);
	      intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
	      intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
	      return intent;
	      }
	 
	
	


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	//  Log.d(LOG_TAG, "Preferences done");
    	
    }
   
    
}