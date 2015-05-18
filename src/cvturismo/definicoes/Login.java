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

public class Login extends Definition implements OnClickListener{
	
	//Session Manager
	SessionManager session;
	
	
	
	private SocialAuthAdapter socialAuth;
	private TextView tvInfo;
	private Button btLogin, btLoginf,btLoging;
	LinearLayout ll;
	private ProgressBar pbLoad;
	private int cont = 0;
	
	
	
	///////LOGIN NORMAL /////
	private TextView mRegister, mForget;
	private EditText us, pass;
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
	private static final String REGISTER_URL = "http://172.16.84.76/task_manager/v1/register";

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
		
		// Session Manager
        session = new SessionManager(getApplicationContext());
        
    //////Social login tools 
		// VIEWS
			tvInfo = (TextView) findViewById(R.id.tvInfo);
			btLogin = (Button) findViewById(R.id.btLogin);
			btLoginf = (Button) findViewById(R.id.btLoginf);
			btLoging = (Button) findViewById(R.id.btLoging);
			ll = (LinearLayout) findViewById(R.id.LinearLayout1);
			pbLoad = (ProgressBar) findViewById(R.id.pbLoad);
			
		/*/ SHARED PREFERENCES
			SharedPreferences sp = getSharedPreferences(Constant.PREF_STATUS, MODE_PRIVATE);
			boolean status = sp.getBoolean(Constant.PREF_IS_LOGGED, false);
			
			if(status){
				enableViews(true);
				linkedInLogin(null);
				facebookLogin(null);
				googleplusLogin(null);
			}
			*/
		//////Social login tools	
			
			
		//////LOGIN NORMAL /////
			// setup input fields
			us = (EditText) findViewById(R.id.email);
			pass = (EditText) findViewById(R.id.password);

			// setup buttons
			mSubmit = (Button) findViewById(R.id.login);
			mRegister = (TextView) findViewById(R.id.register);
			mForget = (TextView) findViewById(R.id.forget);

			//Register and password recovery links
			mRegister.setOnClickListener(this);
			mForget.setOnClickListener(this);
		///////LOGIN NORMAL /////	
			
	}
	
	//Call enableviews to show or hide
	public void enableViewsMainThread(final boolean status){
		runOnUiThread(new Runnable(){
			public void run(){
				enableViews(true);
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
		socialAuth.authorize(Login.this, Provider.LINKEDIN);
	}
	
	public void facebookLogin(View view){
		enableViews(false);
		socialAuth.authorize(Login.this, Provider.FACEBOOK);
	}
	
	public void googleplusLogin(View view){
		enableViews(false);
		socialAuth.authorize(Login.this, Provider.GOOGLEPLUS);
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
		
		User userReg = new User();
		public class ProfileDataListener implements SocialAuthListener<Profile> {
			@Override
			public void onExecute(String plataform, Profile data) {
				
				userReg.setProviderId(data.getProviderId());
				userReg.setValidatedId(data.getValidatedId());
				userReg.setFirstName(data.getFirstName());
				userReg.setLastName(data.getLastName());
				userReg.setEmail(data.getEmail());
				userReg.setCountry(data.getCountry());
				userReg.setLanguage(data.getLanguage());
				userReg.setLocation(data.getLocation());
				userReg.setProfileImageURL(data.getProfileImageURL());
				userReg.setGender(data.getGender());
				userReg.setDisplayName(data.getDisplayName());
				userReg.setFullName(data.getFullName());
				userReg.setContactInfo(data.getContactInfo());
				userReg.setBirthDate(data.getDob());
				
				// Making Social login from server
					new SocialLogin().execute();
			}
			
			@Override
			public void onError(SocialAuthError error) {
				enableViews(true);
				Log.i("Script", error.getMessage());
			}
		}
		
		private String username;
		private   String password;
		
		// Social Login
		class SocialLogin extends AsyncTask<String, String, String> {
			
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				pDialog = new ProgressDialog(Login.this);
				pDialog.setMessage("Attempting login...");
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(true);
				pDialog.show();
			}

			
			@Override
			protected String doInBackground(String... arg0) {
				// TODO Auto-generated method stub
				int success;
				username = userReg.getEmail().toString();
				password = "teste";
				// Check if username, password is filled				
				if(username.trim().length() > 0 && password.trim().length() > 0){
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
							
							// Save user data
							User user = new User();
							user.setFirstName(json.getString("firstName"));
							user.setLastName(json.getString("lastName"));
							user.setEmail(json.getString("email"));
							user.setCountry(json.getString("country"));
							user.setLanguage(json.getString("language"));
							user.setLocation(json.getString("location"));
							user.setProfileImageURL(json.getString("profileImage"));
							// Save user session
							session.createLoginSession(password, username);
							// START ACTIVITY
							Intent intent = new Intent(Login.this, ProfileActivity.class);
							intent.putExtra("user", user);
							startActivity(intent);
							finish();
							
							return json.getString(TAG_MESSAGE);
						} else {
							// Building Parameters
							params.add(new BasicNameValuePair("firstName", userReg.getFirstName()));
							params.add(new BasicNameValuePair("lastName", userReg.getLastName()));
							params.add(new BasicNameValuePair("country", userReg.getCountry()));
							params.add(new BasicNameValuePair("language", userReg.getLanguage()));
							params.add(new BasicNameValuePair("location", userReg.getLocation()));
							params.add(new BasicNameValuePair("profileImage", userReg.getProfileImageURL()));
							// getting product details by making HTTP request
							JSONObject js = jsonParser.makeHttpRequest(REGISTER_URL, "POST",
									params);
							// json success tag
							int sc = js.getInt(TAG_SUCCESS);
							Log.d("rEEGISTERING!", js.toString());
							if(sc == 1){
								// Save user data
								User user = new User();
								user.setFirstName(json.getString("firstName"));
								user.setLastName(json.getString("lastName"));
								user.setEmail(json.getString("email"));
								user.setCountry(json.getString("country"));
								user.setLanguage(json.getString("language"));
								user.setLocation(json.getString("location"));
								user.setProfileImageURL(json.getString("profileImage"));
								// Save user session
								session.createLoginSession(password, username);
								// START ACTIVITY
								Intent i = new Intent(Login.this, ProfileActivity.class);
								i.putExtra("user", user);
								startActivity(i);
								finish();
								
								return json.getString(TAG_MESSAGE);
							}
							
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				return null;
			}
			
			
			protected void onPostExecute(String file_url) {
				// dismiss the dialog once product deleted
				pDialog.dismiss();
				if (file_url != null) {
					Toast.makeText(Login.this, file_url, Toast.LENGTH_LONG).show();
				}

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
				pDialog = new ProgressDialog(Login.this);
				pDialog.setMessage("Attempting login...");
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(true);
				pDialog.show();
			}

			private String username;
			private String password;
			@Override
			protected String doInBackground(String... arg0) {
				// TODO Auto-generated method stub
				int success;
				username = us.getText().toString();
				password = pass.getText().toString();
				// Check if username, password is filled				
				if(username.trim().length() > 0 && password.trim().length() > 0){
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
							User user = new User();
							user.setFirstName(json.getString("firstName"));
							user.setLastName(json.getString("lastName"));
							user.setEmail(json.getString("email"));
							user.setCountry(json.getString("country"));
							user.setLanguage(json.getString("language"));
							user.setLocation(json.getString("location"));
							user.setProfileImageURL(json.getString("profileImage"));
							
							
							// Save user session
							session.createLoginSession(password, username);
							// START ACTIVITY
								if(cont == 0){
									cont++;
									Intent intent = new Intent(Login.this, ProfileActivity.class);
									intent.putExtra("user", user);
									startActivity(intent);
									finish();
								}
							
							return json.getString(TAG_MESSAGE);
						} else {
							Log.d("Login Failure!", json.getString(TAG_MESSAGE));
							return json.getString(TAG_MESSAGE);
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				return null;
			}
			
			
			protected void onPostExecute(String file_url) {
				// dismiss the dialog once product deleted
				pDialog.dismiss();
				if (file_url != null) {
					Toast.makeText(Login.this, file_url, Toast.LENGTH_LONG).show();
				}

			}
			
			
		}
		
		
		
		
}
