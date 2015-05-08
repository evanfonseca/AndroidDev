package cvturismo.definicoes;



import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthAdapter.Provider;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthListener;
import org.json.JSONException;
import org.json.JSONObject;

import br.exemplosocialoauth.R;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import cvturismo.socialoauth.domain.Constant;
import cvturismo.socialoauth.domain.User;





public class LoginActivity extends Definition implements OnClickListener{
	
	
	
	
	
	private SocialAuthAdapter socialAuth;
	private TextView tvInfo;
	private Button btLogin, btLoginf,btLoging;
	LinearLayout ll;
	private ProgressBar pbLoad;
	private int cont = 0;
	
	
	
	///////LOGIN NORMAL /////
	private TextView mRegister, mForget;
	private EditText user, pass;
	private Button mSubmit;

	// Progress Dialog
	private ProgressDialog pDialog;

	// JSON parser class
	JSONParser jsonParser = new JSONParser();

	// php login script location:

	// localhost :
	// testing on your device
	// put your local ip instead, on windows, run CMD > ipconfig
	// or in mac's terminal type ifconfig and look for the ip under en0 or en1
	// private static final String LOGIN_URL =
	// "http://xxx.xxx.x.x:1234/webservice/login.php";

	// testing on Emulator:
	private static final String LOGIN_URL = "http://172.16.84.76/task_manager/v1/login";

	// testing from a real server:
	// private static final String LOGIN_URL =
	// "http://www.mybringback.com/webservice/login.php";

	// JSON element ids from repsonse of php script:
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "error";
	
	public boolean onCreateOptionsMenu(Menu menu) {  
        // Inflate the menu; this adds items to the action bar if it is present.  
        getMenuInflater().inflate(R.menu.main, menu);//Menu Resource, Menu  
        return true;  
    }
	///////LOGIN NORMAL /////
	
	
	
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		socialAuth = new SocialAuthAdapter(new ResponseListener());
		
		// VIEWS
			tvInfo = (TextView) findViewById(R.id.tvInfo);
			btLogin = (Button) findViewById(R.id.btLogin);
			btLoginf = (Button) findViewById(R.id.btLoginf);
			btLoging = (Button) findViewById(R.id.btLoging);
			ll = (LinearLayout) findViewById(R.id.LinearLayout1);
			pbLoad = (ProgressBar) findViewById(R.id.pbLoad);
			
		// SHARED PREFERENCES
			SharedPreferences sp = getSharedPreferences(Constant.PREF_STATUS, MODE_PRIVATE);
			boolean status = sp.getBoolean(Constant.PREF_IS_LOGGED, false);
			
			if(status){
				enableViews(false);
				linkedInLogin(null);
				facebookLogin(null);
				googleplusLogin(null);
			}
			
			
		///////LOGIN NORMAL /////
			// setup input fields
			user = (EditText) findViewById(R.id.email);
			pass = (EditText) findViewById(R.id.password);

			// setup buttons
			mSubmit = (Button) findViewById(R.id.login);
			mRegister = (TextView) findViewById(R.id.register);
			mForget = (TextView) findViewById(R.id.forget);

			// register listeners
			mSubmit.setOnClickListener(this);
			mRegister.setOnClickListener(this);
			mForget.setOnClickListener(this);
		///////LOGIN NORMAL /////	
			
	}
	
	
	public void enableViewsMainThread(final boolean status){
		runOnUiThread(new Runnable(){
			public void run(){
				enableViews(status);
			}
		});
	}
	
	
	public void enableViews(boolean status){
		
		tvInfo.setVisibility(status ? View.VISIBLE : View.GONE);
		btLogin.setVisibility(status ? View.VISIBLE : View.GONE);
		btLoginf.setVisibility(status ? View.VISIBLE : View.GONE);
		btLoging.setVisibility(status ? View.VISIBLE : View.GONE);
		//ll.setVisibility(status ? View.VISIBLE : View.GONE);
		pbLoad.setVisibility(status ? View.GONE : View.VISIBLE);
	}
	
	
	public void linkedInLogin(View view){
		enableViews(false);
		socialAuth.authorize(LoginActivity.this, Provider.LINKEDIN);
	}
	
	public void facebookLogin(View view){
		enableViews(false);
		socialAuth.authorize(LoginActivity.this, Provider.FACEBOOK);
	}
	
	public void googleplusLogin(View view){
		enableViews(false);
		socialAuth.authorize(LoginActivity.this, Provider.GOOGLEPLUS);
	}
	
	
	// SOCIAL AUTH CLASS
		public class ResponseListener implements DialogListener{

			@Override
			public void onComplete(Bundle values) {
				socialAuth.getUserProfileAsync(new ProfileDataListener());
			}

			@Override
			public void onBack() {
				enableViewsMainThread(true);
			}

			@Override
			public void onCancel() {
				enableViewsMainThread(true);
			}

			@Override
			public void onError(SocialAuthError error) {
				enableViewsMainThread(true);
				Log.i("Script", error.getMessage());
			}
		}
		
		
		public class ProfileDataListener implements SocialAuthListener<Profile> {
			@Override
			public void onExecute(String plataform, Profile data) {
				User user = new User();
				user.setProviderId(data.getProviderId());
				user.setValidatedId(data.getValidatedId());
				user.setFirstName(data.getFirstName());
				user.setLastName(data.getLastName());
				user.setEmail(data.getEmail());
				user.setCountry(data.getCountry());
				user.setLanguage(data.getLanguage());
				user.setLocation(data.getLocation());
				user.setProfileImageURL(data.getProfileImageURL());
				user.setGender(data.getGender());
				user.setDisplayName(data.getDisplayName());
				user.setFullName(data.getFullName());
				user.setContactInfo(data.getContactInfo());
				user.setBirthDate(data.getDob());
				
				// START ACTIVITY
					if(cont == 0){
						cont++;
						Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
						intent.putExtra("user", user);
						startActivity(intent);
					}
			}
			
			@Override
			public void onError(SocialAuthError error) {
				enableViews(true);
				Log.i("Script", error.getMessage());
			}
		}
		
		
		
		/**
	     * Event Handling for Individual menu item selected
	     * Identify single menu item by it's id
	     * */
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item)
	    {
	         
	        switch (item.getItemId())
	        {
	        case R.id.logout:
	            // Single menu item is selected do something
	            // Ex: launching new activity/screen or show alert message
	            
	            return true;
	 
	        case R.id.logar:
	            Intent i = new Intent(this, LoginActivity.class);
	            startActivity(i);
	            return true;
	 
	        case R.id.rpassword:
	            Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(""));
	            startActivity(in);
	            return true;
	 
	        case R.id.registar:
	        	Intent inte = new Intent(Intent.ACTION_VIEW, Uri.parse("http://172.16.48.101/cvturismo/frontend/web/index.php?r=site%2Fsignup")); 
	            startActivity(inte);
	        	return true;
	 
	        case R.id.localizacao:
	            
	            return true;
	 
	        case R.id.lingua:
	            
	            return true;
	        case R.id.sair:
	            finish();
	            return true;
	 
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.login:
				new AttemptLogin().execute();
				//Toast.makeText(getApplicationContext(), "Login!!!",Toast.LENGTH_LONG).show();
				break;
			case R.id.register:
				Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://172.16.48.101/cvturismo/frontend/web/site/signup"));
				startActivity(i);
				break;
			case R.id.forget:
				Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("http://172.16.48.101/cvturismo/frontend/web/site/request-password-reset"));
				startActivity(in);
				break;
				
			default:
				break;
			}
			
		}
		
		
		
		class AttemptLogin extends AsyncTask<String, String, String> {
			
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				pDialog = new ProgressDialog(LoginActivity.this);
				pDialog.setMessage("Attempting login...");
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(true);
				pDialog.show();
			}

			@Override
			protected String doInBackground(String... arg0) {
				// TODO Auto-generated method stub
				int success;
				String username = user.getText().toString();
				String password = pass.getText().toString();
				try {
					// Building Parameters
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("email", username));
					params.add(new BasicNameValuePair("password", password));

					Log.d("request!", "starting");
					// getting product details by making HTTP request
					JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL, "POST",
							params);

					// check your log for json response
					Log.d("Login attempt", json.toString());

					// json success tag
					success = json.getInt(TAG_SUCCESS);
					if (success == 1) {
						Log.d("Login Successful!", json.toString());
						// save user data
						SharedPreferences sp = PreferenceManager
								.getDefaultSharedPreferences(LoginActivity.this);
						Editor edit = sp.edit();
						edit.putString("username", username);
						edit.commit();
						
						
						return json.getString(TAG_MESSAGE);
					} else {
						Log.d("Login Failure!", json.getString(TAG_MESSAGE));
						return json.getString(TAG_MESSAGE);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}

				return null;
			}
			
			
			protected void onPostExecute(String file_url) {
				// dismiss the dialog once product deleted
				pDialog.dismiss();
				if (file_url != null) {
					Toast.makeText(LoginActivity.this, file_url, Toast.LENGTH_LONG).show();
				}

			}
			
			
		}
		
		
		
		
}
