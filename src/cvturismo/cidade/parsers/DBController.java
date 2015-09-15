package cvturismo.cidade.parsers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.android.gms.maps.model.LatLng;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBController  extends SQLiteOpenHelper {
	
	Context mContext;
	private static String DB_PATH = ""; 
	private static String DB_NAME ="cvturismo.db";// Database name
	
	

	public DBController(Context applicationcontext) {
        super(applicationcontext, "cvturismo.db", null, 1);
        
        
        Context context=applicationcontext;
		
		if(android.os.Build.VERSION.SDK_INT >= 17){
		       DB_PATH = context.getApplicationInfo().dataDir + "/databases/";         
		    }
		    else
		    {
		       DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
		    }
		    this.mContext = context;
        
    }
	
	
	/*##########################COPIAR BASE DE DADOS#############################*/
	   //Copy the database from assets
 
	 public void copyDataBase() throws IOException
	 {
	     InputStream mInput = mContext.getAssets().open(DB_NAME);
	     String outFileName = DB_PATH + DB_NAME;
	     OutputStream mOutput = new FileOutputStream(outFileName);
	     byte[] mBuffer = new byte[1024];
	     int mLength;
	     while ((mLength = mInput.read(mBuffer))>0)
	     {
	         mOutput.write(mBuffer, 0, mLength);
	     }
	     mOutput.flush();
	     mOutput.close();
	     mInput.close();
	 }
	 
	//Check that the database exists here: /data/data/your package/databases/Da Name
	 private boolean checkDataBase()
	 {
	     File dbFile = new File(DB_PATH + DB_NAME);
	     //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
	     return dbFile.exists();
	 }
	 
	 
	 ////////CRIAR CASO NÃO EXISTIR/////////
	 public void createDataBase() throws IOException
	 {
	     //If database not exists copy it from the assets

	     boolean mDataBaseExist = checkDataBase();
	     if(!mDataBaseExist)
	     {
	         this.getReadableDatabase();
	         this.close();
	         try 
	         {
	             //Copy the database from assests
	             copyDataBase();
	             Log.e("CREATEDB", "createDatabase database created");
	         } 
	         catch (IOException mIOException) 
	         {
	             throw new Error("ErrorCopyingDataBase");
	         }
	     }
	 }

	
	
	
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
        
         String  ContactoLugarquery =" CREATE TABLE contactos_lugares(Id INTEGER, IdLugar INTEGER, Telefone INTEGER, Email TEXT, Endereco TEXT, Linkweb  TEXT, Criado_em TEXT, Actualisado_em TEXT)";
         database.execSQL(ContactoLugarquery );

         
         String  categoriaquery =" CREATE TABLE categorias (id INTEGER, descriacao TEXT,link_icon TEXT,Criado_em TEXT,Actualisado_em TEXT)";
		 database.execSQL(categoriaquery );
		
		
		 String  categorialugarquery =" CREATE TABLE categorias_lugares (id INTEGER, lugar_id INTEGER, Categoria_id INTEGER,SubCategoria_id INTEGER,Criado_em TEXT,Actualisado_em TEXT)";
	     database.execSQL(categorialugarquery );
	     
		 String  subCategoriaquery =" CREATE TABLE subcategorias (id INTEGER, categoria_id INTEGER, descriacao TEXT, link_icon TEXT, Criado_em TEXT, Actualisado_em TEXT)";
	     database.execSQL(subCategoriaquery);
	     
	     String  fotoCapaCidadequery ="CREATE TABLE fotoscapacidade (id INTEGER, nome TEXT, CidadeId INTEGER, Criado_em TEXT, Actualisado_em TEXT)";
	     database.execSQL(fotoCapaCidadequery);
		
		
	     
      
	}
	@Override
	public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
		
		 String Cidadequery;
		 Cidadequery= "DROP TABLE IF EXISTS lugares";
	     database.execSQL(Cidadequery);
			
  
		 String ContactoLugarquery;
		 ContactoLugarquery= "DROP TABLE IF EXISTS contactos_lugares";
		 database.execSQL(ContactoLugarquery);
		 
		 
		 String Lugarquery;
		 Lugarquery= "DROP TABLE IF EXISTS cidades";
		 database.execSQL(Lugarquery);
  
		 String Categoriaquery;
		 Categoriaquery= "DROP TABLE IF EXISTS categorias";
		 database.execSQL(Categoriaquery);
		 
		 String Fotoscapacidadequery;
		 Fotoscapacidadequery= "DROP TABLE IF EXISTS fotoscapacidade";
		 database.execSQL(Fotoscapacidadequery);
       
		 String Subcategoriaquery;
		 Subcategoriaquery= "DROP TABLE IF EXISTS subcategorias";
		 database.execSQL(Subcategoriaquery);
		 
		 
		 String Categorialugarquery;
		 Categorialugarquery= "DROP TABLE IF EXISTS categorias_lugares";
		 database.execSQL(Categorialugarquery);
		 
		 
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
	
	
	/**
	 * Obter dados de uma cidade fornecendo o seu ID
	 * 
	 */
	public HashMap<String, String> getCidadeById(int Id){
		HashMap<String, String> map = new HashMap<String, String>();
		
		String selectQuery = "SELECT  * FROM cidades where id="+Id;
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	    
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
	        
	    }
	    database.close();
		return map;
	}
	
	
	
	//Retorna coordenadas central de uma cidade através de um ID 
	
	public LatLng getLocationCity(int idCidade){
		
		
		double latitude = 0;
		double longitude = 0;
		
		String selectQuery = "SELECT  * FROM cidades where id="+idCidade;
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
		
	    if (cursor.moveToFirst()) {
	    							
	    							latitude = cursor.getDouble(cursor.getColumnIndex("Latitude_centro"));
	    							longitude = cursor.getDouble(cursor.getColumnIndex("Longitude_centro"));
	    						  }
								  database.close();
	    
		
		LatLng ll = new LatLng(latitude, longitude);
		return ll;
		
		
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
 * Get list of lugares from SQLite DB as Array List
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
        	map.put("Latitude", cursor.getString(2));
            map.put("Longitude", cursor.getString(3));
        	map.put("Descricao", cursor.getString(4));
        	map.put("Criado_em",cursor.getString(5));
        	map.put("Actualisado_em", cursor.getString(6));
        	map.put("UserId", cursor.getString(7));
        	map.put("User", cursor.getString(8));
        	map.put("CidadeId", cursor.getString(9));
        	map.put("Cidade", cursor.getString(10));
     	
            List.add(map);
        } while (cursor.moveToNext());
    }
    database.close();
    return List;


}


/**
 * Listar todos os lugares da cidade escolhida
 * @return
 */

	public ArrayList<HashMap<String, String>> getAllLugarByIdCidade(int idCidade){
		
		ArrayList<HashMap<String, String>> List;
		
		List = new ArrayList<HashMap<String, String>>();
		
		String selectQuery = "SELECT  * FROM lugares where CidadeId="+idCidade;
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	        do {
	        
	    		
	        	HashMap<String, String> map = new HashMap<String, String>();
	        	
	        	map.put("Id", cursor.getString(0));
	        	map.put("Nome", cursor.getString(1));
	        	map.put("Latitude", cursor.getString(2));
	            map.put("Longitude", cursor.getString(3));
	        	map.put("Descricao", cursor.getString(4));
	        	map.put("Criado_em",cursor.getString(5));
	        	map.put("Actualisado_em", cursor.getString(6));
	        	map.put("UserId", cursor.getString(7));
	        	map.put("User", cursor.getString(8));
	        	map.put("CidadeId", cursor.getString(9));
	        	map.put("Cidade", cursor.getString(10));
	        		     	
	            List.add(map);
	        } while (cursor.moveToNext());
	    }
	    database.close();
	    return List;

	}

	/**
	 * Listar todos os lugares da Cidade por Categoria
	 * @return
	 */
	
	public ArrayList<HashMap<String, String>> getAllLugarCidadeByCategoria(int idCidade,int idCategoria){
		
		ArrayList<HashMap<String, String>> List;
		
		List = new ArrayList<HashMap<String, String>>();
		
		String selectQuery = "SELECT  * FROM lugares as L, categorias_lugares CL " +
							 "where L.CidadeId="+idCidade+" and CL.Categoria_id="+idCategoria
							  +" and CL.lugar_id=L.Id";
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	        do {

	        	HashMap<String, String> map = new HashMap<String, String>();
	        	
	        	map.put("Id", cursor.getString(0));
	        	map.put("Nome", cursor.getString(1));
	        	map.put("Latitude", cursor.getString(2));
	            map.put("Longitude", cursor.getString(3));
	        	map.put("Descricao", cursor.getString(4));
	        	map.put("Criado_em",cursor.getString(5));
	        	map.put("Actualisado_em", cursor.getString(6));
	        	map.put("UserId", cursor.getString(7));
	        	map.put("User", cursor.getString(8));
	        	map.put("CidadeId", cursor.getString(9));
	        	map.put("Cidade", cursor.getString(10));
	        		     	
	            List.add(map);
	        } while (cursor.moveToNext());
	    }
	    database.close();
	    return List;

	}
	
	/**
	 * Listar todos os lugares da Cidade por Categoria
	 * @return
	 */
	
	public ArrayList<HashMap<String, String>> getAllLugarCidadeCategoriaBySubCategoria(int idCidade,int idCategoria, int idSubCategoria){
		
		ArrayList<HashMap<String, String>> List;
		
		List = new ArrayList<HashMap<String, String>>();
		
		String selectQuery = "SELECT  * FROM lugares as L, categorias_lugares CL " +
							 "where L.CidadeId="+idCidade+
							 " and CL.Categoria_id="+idCategoria+
							 " and CL.SubCategoria_id="+idSubCategoria+
							 " and CL.lugar_id=L.Id";
		
		Log.d("LugaresSub", selectQuery);
		
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	        do {

	        	HashMap<String, String> map = new HashMap<String, String>();
	        	
	        	map.put("Id", cursor.getString(0));
	        	map.put("Nome", cursor.getString(1));
	        	map.put("Latitude", cursor.getString(2));
	            map.put("Longitude", cursor.getString(3));
	        	map.put("Descricao", cursor.getString(4));
	        	map.put("Criado_em",cursor.getString(5));
	        	map.put("Actualisado_em", cursor.getString(6));
	        	map.put("UserId", cursor.getString(7));
	        	map.put("User", cursor.getString(8));
	        	map.put("CidadeId", cursor.getString(9));
	        	map.put("Cidade", cursor.getString(10));
	        		     	
	            List.add(map);
	        } while (cursor.moveToNext());
	    }
	    database.close();
	    return List;

	}
	
	
	
	/**
	 * Retorna Hashmap de Lugar por id
	 * @return
	 */

		public HashMap<String, String> getLugarById(int idLugar){
			
			HashMap<String, String> map;
			
			map = new HashMap<String, String>();
			
			String selectQuery = "SELECT  * FROM lugares where Id="+idLugar;
		    SQLiteDatabase database = this.getWritableDatabase();
		    Cursor cursor = database.rawQuery(selectQuery, null);
		    if (cursor.moveToFirst()) 
		    {
		       
		        	map.put("Id", cursor.getString(0));
		        	map.put("Nome", cursor.getString(1));     
		        	map.put("Latitude", cursor.getString(2));
		            map.put("Longitude", cursor.getString(3));
		           	map.put("Descricao", cursor.getString(4));
		        	map.put("CidadeId", cursor.getString(10));
		        	
		    }
		    database.close();
		    return map;

		}
	
	
	
	
	
	
	/**
	 * Inserts Categorias into SQLite DB
	 * @param queryValues
	 */
	
	//-------------------------------------inf categorias Metudos-------------------------------------------	
	
	public void insertCategorias(HashMap<String, String> queryValues) {
		
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("Id", queryValues.get("Id"));
	    values.put("Descricao", queryValues.get("Descricao"));
	    values.put("Link_icon", queryValues.get("Link_icon"));
		values.put("Criado_em", queryValues.get("Criado_em"));
		values.put("Actualisado_em", queryValues.get("Actualisado_em"));
		
		database.insert("categorias", null, values);
		
		database.close();
	}
	
	

		public void deleteAllCategorias() {
			SQLiteDatabase database = this.getWritableDatabase();
			database.delete("categorias", null, null);
			
		}
	/**
	 * Get list of categorias from SQLite DB as Array List
	 * @return
	 */
	public ArrayList<HashMap<String, String>> getAllCategorias() {
		
		ArrayList<HashMap<String, String>> List;
		
		List = new ArrayList<HashMap<String, String>>();
		
		String selectQuery = "SELECT  * FROM categorias";
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	        do {
	        	
	        	
	    		
	        	HashMap<String, String> map = new HashMap<String, String>();
	        	
	        	map.put("Id", cursor.getString(0));
	        	map.put("Descricao", cursor.getString(1));
	            map.put("Link_icon", cursor.getString(2));
	        	/*
	            map.put("Criado_em", cursor.getString(4));
	        	map.put("Actualisado_em", cursor.getString(5));
	        	*/
                List.add(map);
	        } while (cursor.moveToNext());
	    }
	    database.close();
	    return List;
	}
	

	/**
	 * Inserts Categorias_Lugares into SQLite DB
	 * @param queryValues
	 */
	
	//-------------------------------------inf categorias_luagares Metudos-------------------------------------------	
	
	public void insertCategoriasLugares(HashMap<String, String> queryValues) {
		
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("Id", queryValues.get("Id"));
	    values.put("Lugar_id", queryValues.get("Lugar_id"));
	    values.put("Categoria_id", queryValues.get("Categoria_id"));
	    values.put("SubCategoria_id", queryValues.get("SubCategoria_id"));
	    values.put("Criado_em", queryValues.get("Criado_em"));
		values.put("Actualisado_em", queryValues.get("Actualisado_em"));
		
		database.insert("categorias_lugares", null, values);
		
		database.close();
	}
	
	

		public void deleteAllCategoriaLugar() {
			SQLiteDatabase database = this.getWritableDatabase();
			database.delete("categorias_lugares", null, null);
			
		}
	/**
	 * Get list of categorias_lugares from SQLite DB as Array List
	 * @return
	 */
	public ArrayList<HashMap<String, String>> getAllCategoriasLugares() {
		
		ArrayList<HashMap<String, String>> List;
		
		List = new ArrayList<HashMap<String, String>>();
		
		String selectQuery = "SELECT  * FROM categorias_lugares";
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	        do {
	        	
	        	
	    		
	        	HashMap<String, String> map = new HashMap<String, String>();
	        	
	        	map.put("Id", cursor.getString(0));
	        	map.put("Lugar_id", cursor.getString(2));
	            map.put("Categoria_id", cursor.getString(3));
	            map.put("SubCategoria_id", cursor.getString(4));
	            map.put("Criado_em", cursor.getString(5));
	        	map.put("Actualisado_em", cursor.getString(6));
	        	
                List.add(map);
	        } while (cursor.moveToNext());
	    }
	    database.close();
	    return List;
	}
	
	/**
	 * Get list of SubCategorias by ID categorias from SQLite DB as Array List
	 * @return
	 */
	public ArrayList<HashMap<String, String>> getAllSubCategoriasByIdCategoria(int idCategoria) {
		
		ArrayList<HashMap<String, String>> List;
		
		List = new ArrayList<HashMap<String, String>>();
		
		String selectQuery = "SELECT  * FROM subcategorias where categoria_id="+idCategoria;
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	        do {
	        	
	        	
	    		
	        	HashMap<String, String> map = new HashMap<String, String>();
	        	
	        	map.put("Id", cursor.getString(0));
	        	map.put("IdCategoria", cursor.getString(1));
	        	map.put("Descricao", cursor.getString(2));
	            map.put("Link_icon", cursor.getString(3));
	        	/*
	            map.put("Criado_em", cursor.getString(4));
	        	map.put("Actualisado_em", cursor.getString(5));
	        	*/
                List.add(map);
	        } while (cursor.moveToNext());
	    }
	    database.close();
	    return List;
	}

	
	
	/**
	 * Verifica se uma categoria tem subcategorias
	 * @return
	 */
	public boolean temSubCategorias(int idCategoria){
		
		String selectQuery = "SELECT  * FROM subcategorias where categoria_id="+idCategoria;
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
		
	    int c = cursor.getCount();
	    if (c==0) return false;
	    else return true;
	}
	

	/**
	 * Retorna uma lista de nome de fotos Capa da Cidade pelo ID 
	 * @return
	 */
	public ArrayList<String> getAllFotoCapaCidade(int idCidade) {
		
		ArrayList<String> List;
		
		List = new ArrayList<String>();
		
		String selectQuery = "SELECT  * FROM fotoscapacidade where CidadeId="+idCidade;
	    
		SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	        do {
	        	String s = new  String("");
	            s = cursor.getString(1);
                List.add(s);
	        } while (cursor.moveToNext());
	    }
	    database.close();
	    return List;
	}
	
	
	public String NomeDrawableCategoriaById(int idCategoria){
		String NomeDrawable = new String();
		
		String selectQuery = "SELECT  * FROM categorias where id="+idCategoria;
	    
		SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	        
	        	NomeDrawable = cursor.getString(2);
                
	        
	    }
	    database.close();
	    return NomeDrawable;
		
		
	}
	
	
	public HashMap<String, String>  getCategoriaById(int idCategoria){
		
		HashMap<String, String> map =new HashMap<String, String>();
		
		
		String selectQuery = "SELECT  * FROM categorias where id="+idCategoria;
	    
		SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	        
	    	map.put("Id", cursor.getString(0));
        	map.put("Descricao", cursor.getString(1));
            map.put("Link_icon", cursor.getString(2));
	      
	    }
	    database.close();
	  
	    return map;
		
		
	}
	
//-------------------------------------inf Contactos Lugar Metudos-------------------------------------------	


	public void insertContactoLugar(HashMap<String, String> queryValues) {
		
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("Id", queryValues.get("Id"));
		values.put("IdLugar", queryValues.get("IdLugar"));
	    values.put("Telefone", queryValues.get("Telefone"));
	    values.put("Email", queryValues.get("Email"));
		values.put("Endereco", queryValues.get("Endereco"));
		values.put("Linkweb", queryValues.get("Linkweb"));
		values.put("Criado_em", queryValues.get("Criado_em"));
		values.put("Actualisado_em", queryValues.get("Actualisado_em"));
		
		
		database.insert("contactos_lugares", null, values);
		
		database.close();
	}
	
	

		public void deleteAllCaontactoLugar() {
			SQLiteDatabase database = this.getWritableDatabase();
			database.delete("contactos_lugares", null, null);
			
		}
	/**
	 * Get list of contacto lugar from SQLite DB as Array List
	 * @return
	 */
	public ArrayList<HashMap<String, String>> getAllContactoLugar() {
		
		ArrayList<HashMap<String, String>> List;
		
		List = new ArrayList<HashMap<String, String>>();
		
		String selectQuery = "SELECT  * FROM  contactos_lugares";
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	        do {
	        	
	        	HashMap<String, String> map = new HashMap<String, String>();
	        	
	        	map.put("Id", cursor.getString(0));
	        	map.put("IdLugar", cursor.getString(1));
	        	map.put("Telefone", cursor.getString(2));
	            map.put("Email", cursor.getString(3));
	        	map.put("Endereco", cursor.getString(4));
	        	map.put("Linkweb", cursor.getString(5));
	        	map.put("Criado_em", cursor.getString(6));
	        	map.put("Actualisado_em", cursor.getString(7));
	        
	        	
                List.add(map);
	        } while (cursor.moveToNext());
	    }
	    database.close();
	    return List;
	}
	
	

	public HashMap<String, String>  getContactoLugarByIdLugar(int IdLugar){
		
		HashMap<String, String> map =new HashMap<String, String>();
		
		
		String selectQuery = "SELECT  * FROM contactos_lugares where IdLugar="+IdLugar;
	    
		SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    
	    
	    if (cursor.moveToFirst()) {
	        
	    	
        	map.put("Id", cursor.getString(0));
        	map.put("IdLugar", cursor.getString(1));
        	map.put("Telefone", cursor.getString(2));
            map.put("Email", cursor.getString(3));
        	map.put("Endereco", cursor.getString(4));
        	map.put("Linkweb", cursor.getString(5));
	       
	        
	    }
	    
	    database.close();
	  
	    return map;
		
		
	}
	
	
	public boolean temContacto(int IdLugar){
		
		String selectQuery = "SELECT  * FROM contactos_lugares where IdLugar="+IdLugar;
		SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
		
	    int c = cursor.getCount();
	    if (c==0) return false;
	    else return true;
	}
	
	
	
	
	
	
}
