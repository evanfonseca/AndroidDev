package cvturismo.definicoes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import br.exemplosocialoauth.R;
import cvturismo.socialoauth.domain.Constant;
import cvturismo.socialoauth.domain.Image;
import cvturismo.socialoauth.domain.User;

public class ProfileActivity extends  Definition implements OnClickListener{
	
	
	private ProgressBar pbLoad;
	private ImageView ivProfile;
	private TextView tvInfo;
	
	// JSON element ids from repsonse of php script:
		private static final String TAG_SUCCESS = "success";
		private static final String TAG_MESSAGE = "error";
	
	// Session manager declaration variiable
	private SessionManager session;
	// URL to user data request
	private static final String PROFILE_URL = "http://172.16.84.76/task_manager/v1/profile";
	// JSON parser class
	JSONParser jP = new JSONParser();// JSON parser class	
	// Progress Dialog
	private ProgressDialog pDialog;
	// API KEY
	private String apiKey;
	// JSON parser class
	JSONParser jsonParser = new JSONParser();
	// User variable
	private User user;
	
	Button bts;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
			//Instancing session manager
			session = new SessionManager(getApplicationContext());
			//Check if user is loged
			session.checkLogin();
		
			// get user data from session
			HashMap<String, String> us = session.getUserDetails();
			
			// name
			apiKey = us.get(SessionManager.KEY_KEY);
		
				
		// SHARED PREFERENCES
			SharedPreferences sp = getSharedPreferences(Constant.PREF_STATUS, MODE_PRIVATE);
			SharedPreferences.Editor editor = sp.edit();
			editor.putBoolean(Constant.PREF_IS_LOGGED, true);
			editor.commit();
	
		// VIEWS
			pbLoad = (ProgressBar) findViewById(R.id.pbLoad);
			ivProfile = (ImageView) findViewById(R.id.ivProfile);
			tvInfo = (TextView) findViewById(R.id.tvInfo);
			
			bts = (Button) findViewById(R.id.sair);
			
			bts.setText(apiKey);
			
			
			//System.out.println(apiKey);
			
			/*new AttemptProfile();
			
			String contentInfo = user.getProviderId() != null ? "<b>Plataforma:</b> "+user.getProviderId()+"<br />" : "";
			contentInfo += user.getValidatedId() != null ? "<b>ID:</b> "+user.getValidatedId()+"<br />" : "";
			contentInfo += user.getFirstName() != null ? "<b>Primeiro nome:</b> "+user.getFirstName()+"<br />" : "";
			contentInfo += user.getLastName() != null ? "<b>�ltimo nome:</b> "+user.getLastName()+"<br />" : "";
			contentInfo += user.getEmail() != null ? "<b>Email:</b> "+user.getEmail()+"<br />" : "";
			contentInfo += user.getCountry() != null ? "<b>Pa�s:</b> "+user.getCountry()+"<br />" : "";
			contentInfo += user.getLanguage() != null ? "<b>L�ngua:</b> "+user.getLanguage()+"<br />" : "";
			contentInfo += user.getLocation() != null ? "<b>Localiza��o:</b> "+user.getLocation()+"<br />" : "";
			contentInfo += user.getGender() != null ? "<b>G�nero:</b> "+user.getGender()+"<br />" : "";
			contentInfo += user.getDisplayName() != null ? "<b>Nome em amostra:</b> "+user.getDisplayName()+"<br />" : "";
			contentInfo += user.getFullName() != null ? "<b>Nome completo:</b> "+user.getFullName()+"<br />" : "";
			contentInfo += user.getBirthDate() != null ? "<b>Nascimento:</b> "+user.getBirthDate().getDay()+"/"+user.getBirthDate().getMonth()+"/"+user.getBirthDate().getYear()+"<br />" : "";
			
			if(user.getContactInfo() != null){
				contentInfo += "<b>Informa��es de contato:</b> <br />";
					Set keys = user.getContactInfo().keySet();
				Iterator<String> i = keys.iterator();
					while(i.hasNext()){
					String key = (String) i.next();
					String value = (String) user.getContactInfo().get(key);
					contentInfo += "&nbsp;&nbsp; <b>"+key+":</b> "+value+"<br />";
					
				}
			}
			
				tvInfo.setText( Html.fromHtml(contentInfo) );
				Image.loadImg(ProfileActivity.this, user.getProfileImageURL(), ivProfile, pbLoad);
				
				*/
				
				if(getIntent() != null){
					user = (User) getIntent().getSerializableExtra("user");
					
					String contentInfo = user.getProviderId() != null ? "<b>Plataforma:</b> "+user.getProviderId()+"<br />" : "";
					contentInfo += user.getValidatedId() != null ? "<b>ID:</b> "+user.getValidatedId()+"<br />" : "";
					contentInfo += user.getFirstName() != null ? "<b>Primeiro nome:</b> "+user.getFirstName()+"<br />" : "";
					contentInfo += user.getLastName() != null ? "<b>�ltimo nome:</b> "+user.getLastName()+"<br />" : "";
					contentInfo += user.getEmail() != null ? "<b>Email:</b> "+user.getEmail()+"<br />" : "";
					contentInfo += user.getCountry() != null ? "<b>Pais:</b> "+user.getCountry()+"<br />" : "";
					contentInfo += user.getLanguage() != null ? "<b>Lingua:</b> "+user.getLanguage()+"<br />" : "";
					contentInfo += user.getLocation() != null ? "<b>Localizacao:</b> "+user.getLocation()+"<br />" : "";
					contentInfo += user.getGender() != null ? "<b>Genero:</b> "+user.getGender()+"<br />" : "";
					contentInfo += user.getDisplayName() != null ? "<b>Nome em amostra:</b> "+user.getDisplayName()+"<br />" : "";
					contentInfo += user.getFullName() != null ? "<b>Nome completo:</b> "+user.getFullName()+"<br />" : "";
					contentInfo += user.getBirthDate() != null ? "<b>Nascimento:</b> "+user.getBirthDate().getDay()+"/"+user.getBirthDate().getMonth()+"/"+user.getBirthDate().getYear()+"<br />" : "";
					
					if(user.getContactInfo() != null){
						contentInfo += "<b>Informacoes de contato:</b> <br />";

						Set keys = user.getContactInfo().keySet();
						Iterator<String> i = keys.iterator();

						while(i.hasNext()){
							String key = (String) i.next();
							String value = (String) user.getContactInfo().get(key);
							contentInfo += "&nbsp;&nbsp; <b>"+key+":</b> "+value+"<br />";
						}
					}
					
					tvInfo.setText( Html.fromHtml(contentInfo) );
					Image.loadImg(ProfileActivity.this, user.getProfileImageURL(), ivProfile, pbLoad);
		  }
	}
	
	class AttemptProfile extends AsyncTask<String, String, String> {
			
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				pDialog = new ProgressDialog(ProfileActivity.this);
				pDialog.setMessage("Attempting Profile...");
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(true);
				pDialog.show();
				
			}

			@Override
			protected String doInBackground(String... arg0) {
				// TODO Auto-generated method stub
				int success;
				
					try {
						// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("apiKey", apiKey));
	
				// getting product details by making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(PROFILE_URL, "POST", params);
	
						// check your log for json response
						Log.d("Profile attempt", json.toString());
	
						// json success tag
						success = json.getInt(TAG_SUCCESS);
						if (success == 1) {
							Log.d("Profile Successful charged!", json.toString());
							// save user data

							user.setFirstName(json.getString("firstName"));
							user.setLastName(json.getString("lastName"));
							user.setEmail(json.getString("email"));
							user.setCountry(json.getString("country"));
							user.setLanguage(json.getString("language"));
							user.setLocation(json.getString("location"));
							user.setProfileImageURL(json.getString("profileImage"));
							
						}
						
						
							
							return json.getString(TAG_MESSAGE);
						
					} catch (JSONException e) {
						e.printStackTrace();
					}
				return null;
			}
			
			
			protected void onPostExecute(String file_url) {
				// dismiss the dialog once product deleted
				pDialog.dismiss();
				if (file_url != null) {
					Toast.makeText(ProfileActivity.this, file_url, Toast.LENGTH_LONG).show();
				}

			}
			
			
		}
	public void signOut(View view){
		// SHARED PREFERENCES
			SharedPreferences sp = getSharedPreferences(Constant.PREF_STATUS, MODE_PRIVATE);
			SharedPreferences.Editor editor = sp.edit();
			editor.putBoolean(Constant.PREF_IS_LOGGED, false);
			editor.commit();
		
		startActivity(new Intent(ProfileActivity.this, Login.class));
		finish();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
