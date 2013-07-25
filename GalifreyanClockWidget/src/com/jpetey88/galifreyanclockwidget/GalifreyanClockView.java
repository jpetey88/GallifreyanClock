package com.jpetey88.galifreyanclockwidget;

import java.util.Calendar;
import java.util.HashMap;

import com.jpetey88.galifreyanclockwidget.settings.SettingsActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.util.SparseIntArray;
import android.widget.ImageView;
import android.widget.RemoteViews;

public class GalifreyanClockView {
		private SparseIntArray value = null;
		private int[] resourceIds = null;
		private String currentTypeColor = "Default"; 
		
		public GalifreyanClockView(){
			
			
			value = new SparseIntArray(6);
			
		//	switchResIds(context, "BlackWithClear");
			
		}
		
		private void switchResIds(Context context, String resource){
			TypedArray arr = null;
			if("BlackWithClear".compareToIgnoreCase(resource) ==0){
				arr= context.getResources().obtainTypedArray(R.array.blackWithClear);
			}else if("BlackWithWhite".compareToIgnoreCase(resource) ==0){
				arr= context.getResources().obtainTypedArray(R.array.blackWithWhite);
			}else if("pink".compareToIgnoreCase(resource) ==0){
				arr= context.getResources().obtainTypedArray(R.array.pink);
			}else if("tardisblue".compareToIgnoreCase(resource) ==0){
				arr= context.getResources().obtainTypedArray(R.array.tardisblue);
			}
			else{
				arr= context.getResources().obtainTypedArray(R.array.blackWithClear);
			}
			
			if(arr!=null){
			fillIntArray(arr);
			arr.recycle();
			}
		}
		
		private void fillIntArray(TypedArray array){
			resourceIds = new int[array.length()];
			for(int i = 0; i< array.length();i++){
				resourceIds[i] = array.getResourceId(i, 0);
			}
		}
		
		
		
		private void checkforConfigChanges(SharedPreferences prefs,Context context){
			
			
			currentTypeColor = prefs.getString("SymbolColor", "Default");
			
				switchResIds(context, currentTypeColor);
			
		}

		public void update(RemoteViews views, SharedPreferences prefs, Context context) {
			checkforConfigChanges(prefs,context);
			
			
			
			
			 Calendar calendar = Calendar.getInstance();
		        calendar.setTimeInMillis(System.currentTimeMillis());
		        
		        
		        int hour = calendar.get(Calendar.HOUR);
		        int minute = calendar.get(Calendar.MINUTE);
		        int second = calendar.get(Calendar.SECOND);
		        
		        if (hour == 0)
		       		hour = 12;
		       		
		        int firstHour = hour>9?1:0;
		        int secondHour = hour %10;
		        
		        
		        int firstMinute = minute/10;
		        int secondMinute = minute %10;
		        
		        int firstSecond = second/10;
		        int secondSecond = second %10;
		        views.setImageViewResource(R.id.pos1, getNumber(firstHour));
		        views.setImageViewResource(R.id.pos2, getNumber(secondHour));
		        views.setImageViewResource(R.id.pos3, getNumber(firstMinute));
		        views.setImageViewResource(R.id.pos4, getNumber(secondMinute));
		        views.setImageViewResource(R.id.pos5, getNumber(firstSecond));
		        views.setImageViewResource(R.id.pos6, getNumber(secondSecond));
		        
		        
		        
		        
		}
		
		
		
		
		
		public int getNumber(int thing){
			int resource  = 0;
		
			resource =resourceIds[thing];
			
			return resource;
			
			
		}
		}
	

