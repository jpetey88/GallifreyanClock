package com.jpetey88.galifreyanclockwidget;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
public class GalefreyClock extends AppWidgetProvider {
	public static String CLOCK_WIDGET_UPDATE = "com.jpetey88.GalifreyanClockUpdate";
	public static String LOG_TAG = "Clocky";
  DateFormat df = new SimpleDateFormat("hh:mm:ss");
  public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
      ComponentName thisAppWidget = new ComponentName(context.getPackageName(), getClass().getName());
 	 
	  int ids[] = appWidgetManager.getAppWidgetIds(thisAppWidget);
      for (int appWidgetID: ids) {
          updateAppWidget(context, appWidgetManager, appWidgetID);
      }
  }
  private PendingIntent createClockTickIntent(Context context) {
	    Intent intent = new Intent(CLOCK_WIDGET_UPDATE);
	    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	    return pendingIntent;
	}
	@Override
	public void onEnabled(Context context) {
	        super.onEnabled(context);
	        Log.d(LOG_TAG, "Widget Provider enabled.  Starting timer to update widget every second");
	        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTimeInMillis(System.currentTimeMillis());
	        calendar.add(Calendar.SECOND, 1);
	        alarmManager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), 1000
	, createClockTickIntent(context));
	}
	@Override
	public void onDisabled(Context context) {
	        super.onDisabled(context);
	        Log.d(LOG_TAG, "Widget Provider disabled. Turning off timer");
	        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
	        alarmManager.cancel(createClockTickIntent(context));
	}
	/**
	 * Custom Intent name that is used by the 'AlarmManager' to tell us to update the
	clock once per second.
	 */
	@Override    
	public void onReceive(Context context, Intent intent) {
	    super.onReceive(context, intent);
	    Log.d(LOG_TAG, "Received intent " + intent);
	    if (CLOCK_WIDGET_UPDATE.equals(intent.getAction())) {
	        Log.d(LOG_TAG, "Clock update");
	        // Get the widget manager and ids for this widget provider, then call the shared
	        // clock update method.
	        ComponentName thisAppWidget = new ComponentName(context.getPackageName(), getClass().getName());
	        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
	        int ids[] = appWidgetManager.getAppWidgetIds(thisAppWidget);
	        for (int appWidgetID: ids) {
	            updateAppWidget(context, appWidgetManager, appWidgetID);
	        }
	    }
	}
	public void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId){

	      // Create an Intent to launch ExampleActivity
	      Intent intent = new Intent(context, GalefreyClock.class);
	      PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
	      // Get the layout for the App Widget and attach an on-click listener
	      // to the button
	      RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget1);
	     // views.setOnClickPendingIntent(R.id.button, pendingIntent);
	      // To update a label
	      GalifreyanClockView clockView = new GalifreyanClockView();
	      clockView.update(views);
	     // views.setTextViewText(R.id.widget1label, df.format(new Date()));
	      // Tell the AppWidgetManager to perform an update on the current app
	      // widget
	      
	      
	      
	      appWidgetManager.updateAppWidget(appWidgetId, views);
	    
	}
	


}
