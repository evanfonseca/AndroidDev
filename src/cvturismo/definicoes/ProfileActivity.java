package cvturismo.definicoes;

import java.util.Iterator;
import java.util.Set;

import cvturismo.R;
import cvturismo.socialoauth.domain.Constant;
import cvturismo.socialoauth.domain.Image;
import cvturismo.socialoauth.domain.User;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProfileActivity extends Activity {
	private ProgressBar pbLoad;
	private ImageView ivProfile;
	private TextView tvInfo;
	private User user;
	
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		// SHARED PREFERENCES
			SharedPreferences sp = getSharedPreferences(Constant.PREF_STATUS, MODE_PRIVATE);
			SharedPreferences.Editor editor = sp.edit();
			editor.putBoolean(Constant.PREF_IS_LOGGED, true);
			editor.commit();
	
		// VIEWS
			pbLoad = (ProgressBar) findViewById(R.id.pbLoad);
			ivProfile = (ImageView) findViewById(R.id.ivProfile);
			tvInfo = (TextView) findViewById(R.id.tvInfo);
			
		if(getIntent() != null){
			user = (User) getIntent().getSerializableExtra("user");
			
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
		}
	}
	
	
	public void signOut(View view){
		// SHARED PREFERENCES
			SharedPreferences sp = getSharedPreferences(Constant.PREF_STATUS, MODE_PRIVATE);
			SharedPreferences.Editor editor = sp.edit();
			editor.putBoolean(Constant.PREF_IS_LOGGED, false);
			editor.commit();
		
		startActivity(new Intent(ProfileActivity.this, MainActivity.class));
		finish();
	}
}
