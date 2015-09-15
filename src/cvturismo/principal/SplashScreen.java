package cvturismo.principal;

//Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
//Jad home page: http://www.geocities.com/kpdus/jad.html
//Decompiler options: braces fieldsfirst space lnc 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import cvturismo.cidade.parsers.DBController;

import br.exemplosocialoauth.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class SplashScreen extends Activity {
	
	private SQLiteDatabase mDataBase; 

	// Splash screen timer
	private static int SPLASH_TIME_OUT = 3000;
	
	Context mContext;
	private static String DB_PATH = ""; 
	private static String DB_NAME ="cvturismo.db";// Database name
	SharedPreferences app_preferences;
	SharedPreferences.Editor editor;
	// DB Class to perform DB related operations
	DBController controller;
	boolean carregou=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		
		
		
		

		
		app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		carregou=app_preferences.getBoolean("shCarregou",false);
		controller = new DBController(this);

		
		if(!carregou){
					try {
						controller.createDataBase();
						carregou=true;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}

		new Handler().postDelayed(new Runnable() {

			/*
			 * Showing splash screen with a timer. This will be useful when you
			 * want to show case your app logo / company
			 */

			@Override
			public void run() {
				// This method will be executed once the timer is over
				// Start your app main activity

				
				Intent i = new Intent(SplashScreen.this, MMainActivity.class);
				startActivity(i);
				
				
				// close this activity
				finish();
			}
		}, SPLASH_TIME_OUT);
	}
}

