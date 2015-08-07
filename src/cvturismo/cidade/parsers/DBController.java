package cvturismo.cidade.parsers;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBController  extends SQLiteOpenHelper {

	public DBController(Context applicationcontext) {
        super(applicationcontext, "cvturismo.db", null, 1);
    }
	//Creates Table
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		
		
		
		String  Cidadequery ="CREATE TABLE cidades (Id INTEGER,Nome TEXT, Descricao TEXT,Ilha TEXT,Latitude_centro TEXT," +
				"Longitude_centro TEXT,Historia TEXT,Clima TEXT," +
				"Geografia TEXT,Criado_em TEXT,Actualisado_em TEXT," +
				"GestorId INTEGER,Gestor TEXT,LugarQuantidade TEXT,FotoCapa TEXT)";
			
        database.execSQL(Cidadequery);
        
        
    	
		String  Lugarquery ="CREATE TABLE lugares (Id INTEGER,Nome TEXT, Latitude TEXT,Longitude TEXT," +
				"Descricao TEXT,UserId INTEGER,User TEXT," +
				"CidadeId INTEGER,Cidade TEXT ,Criado_em TEXT,Actualisado_em TEXT" +
				")";
			
        database.execSQL(Lugarquery);
        
     
      
	}
	@Override
	public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
		
		String Lugarquery;
		Lugarquery= "DROP TABLE IF EXISTS cidades";
		database.execSQL(Lugarquery);
  
        String Cidadequery;
		Cidadequery= "DROP TABLE IF EXISTS lugares";
		database.execSQL(Cidadequery);
		
        onCreate(database);
	}
	
	
	/**
	 * Inserts cidade into SQLite DB
	 * @param queryValues
	 */
	
	//-------------------------------------inf cidade Metudos-------------------------------------------	
	
	public void insertCidade(HashMap<String, String> queryValues) {
		
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("Id", queryValues.get("Id"));
		values.put("Nome", queryValues.get("Nome"));
	    values.put("Descricao", queryValues.get("Descricao"));
	    values.put("Ilha", queryValues.get("Ilha"));
		values.put("Latitude_centro", queryValues.get("Latitude_centro"));
		values.put("Longitude_centro", queryValues.get("Longitude_centro"));
		values.put("Historia", queryValues.get("Historia"));
		values.put("Clima", queryValues.get("Clima"));
		values.put("Geografia", queryValues.get("Geografia"));
		values.put("Criado_em", queryValues.get("Criado_em"));
		values.put("Actualisado_em", queryValues.get("Actualisado_em"));
		values.put("GestorId", queryValues.get("GestorId"));	
		values.put("Gestor", queryValues.get("Gestor"));
		values.put("LugarQuantidade", queryValues.get("LugarQuantidade"));
		values.put("FotoCapa ", queryValues.get("FotoCapa "));
		
		database.insert("cidades", null, values);
		
		database.close();
	}
	
	

		public void deleteAllCidade() {
			SQLiteDatabase database = this.getWritableDatabase();
			database.delete("cidades", null, null);
			
		}
	/**
	 * Get list of cidades from SQLite DB as Array List
	 * @return
	 */
	public ArrayList<HashMap<String, String>> getAllCidade() {
		
		ArrayList<HashMap<String, String>> List;
		
		List = new ArrayList<HashMap<String, String>>();
		
		String selectQuery = "SELECT  * FROM cidades";
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	        do {
	        	
	        	
	    		
	        	HashMap<String, String> map = new HashMap<String, String>();
	        	
	        	map.put("Id", cursor.getString(0));
	        	map.put("Nome", cursor.getString(1));
	        	map.put("Descricao", cursor.getString(2));
	            map.put("Ilha", cursor.getString(3));
	        	map.put("Latitude_centro", cursor.getString(4));
	            map.put("Longitude_centro", cursor.getString(5));
	        	map.put("Historia", cursor.getString(6));
	        	map.put("Clima", cursor.getString(7));
	        	map.put("Geografia", cursor.getString(8));
	        	map.put("Criado_em", cursor.getString(9));
	        	map.put("Actualisado_em", cursor.getString(10));
	        	map.put("GestorId", cursor.getString(11));
	        	map.put("Gestor", cursor.getString(12));
	        	map.put("LugarQuantidade", cursor.getString(13));
	        	map.put("FotoCapa", cursor.getString(14));
	        	
                List.add(map);
	        } while (cursor.moveToNext());
	    }
	    database.close();
	    return List;
	}
	
//-------------------------------------inf Lugar Metudos-------------------------------------------	
	
	public void insertLugar(HashMap<String, String> queryValues) {
		// TODO Auto-generated method stub
		
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("Id", queryValues.get("Id"));
		values.put("Nome", queryValues.get("Nome"));
		values.put("Latitude", queryValues.get("Latitude"));
		values.put("Longitude", queryValues.get("Longitude"));
		values.put("Descricao", queryValues.get("Descricao"));
		
		values.put("Criado_em", queryValues.get("Criado_em"));
		values.put("Actualisado_em", queryValues.get("Actualisado_em"));
		
		values.put("UserId", queryValues.get("UserId"));
		values.put("User", queryValues.get("User"));
		values.put("CidadeId ", queryValues.get("CidadeId "));
		values.put("Cidade ", queryValues.get("Cidade" ));
	
		
		
		database.insert("lugares", null, values);
		
		database.close();
		
		
	}
	


	public void deleteAllLugar() {
		SQLiteDatabase database = this.getWritableDatabase();
		database.delete("lugares", null, null);
		
	}
/**
 * Get list of cidades from SQLite DB as Array List
 * @return
 */
public ArrayList<HashMap<String, String>> getAllLugar() {
	
	ArrayList<HashMap<String, String>> List;
	
	List = new ArrayList<HashMap<String, String>>();
	
	String selectQuery = "SELECT  * FROM lugares";
    SQLiteDatabase database = this.getWritableDatabase();
    Cursor cursor = database.rawQuery(selectQuery, null);
    if (cursor.moveToFirst()) {
        do {
        	
        	
    		
        	HashMap<String, String> map = new HashMap<String, String>();
        	
        	map.put("Id", cursor.getString(0));
        	map.put("Nome", cursor.getString(1));
        	map.put("Latitude", cursor.getString(3));
            map.put("Longitude", cursor.getString(4));
        	map.put("Descricao", cursor.getString(5));
        	
        	map.put("Criado_em",cursor.getString(6));
        	map.put("Actualisado_em", cursor.getString(7));
    		
        	map.put("UserId", cursor.getString(8));
        	map.put("User", cursor.getString(9));
        	map.put("CidadeId", cursor.getString(10));
        	map.put("CidadeId", cursor.getString(11));
     	
            List.add(map);
        } while (cursor.moveToNext());
    }
    database.close();
    return List;


}


}
