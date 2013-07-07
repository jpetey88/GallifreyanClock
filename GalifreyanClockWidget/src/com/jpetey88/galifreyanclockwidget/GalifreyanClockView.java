package com.jpetey88.galifreyanclockwidget;

import java.util.Calendar;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.SparseIntArray;
import android.widget.ImageView;
import android.widget.RemoteViews;

public class GalifreyanClockView {
		private SparseIntArray value = null;
		public GalifreyanClockView(){
			value = new SparseIntArray(6);
			
		}

		public void update(RemoteViews views) {
			 Calendar calendar = Calendar.getInstance();
		        calendar.setTimeInMillis(System.currentTimeMillis());
		        
		        
		        int hour = calendar.get(Calendar.HOUR);
		        int minute = calendar.get(Calendar.MINUTE);
		        int second = calendar.get(Calendar.SECOND);
		        
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
			switch(thing){
			case 1:
				resource = R.drawable.gale1bt;
				break;
			case 2:
				resource = R.drawable.gale2bt;
				break;
			case 3:
				resource = R.drawable.gale3bt;
				break;
			case 4:
				resource = R.drawable.gale4bt;
				break;
			case 5:
				resource = R.drawable.gale5bt;
				break;
			case 6:
				resource = R.drawable.gale6bt;
				break;
			case 7:
				resource = R.drawable.gale7bt;
				break;
			case 8:
				resource = R.drawable.gale8bt;
				break;
			case 9:
				resource = R.drawable.gale9bt;
				break;
			default:
				resource  = R.drawable.gale0bt;
			
			}
			
			return resource;
			
			
		}
		}
	

