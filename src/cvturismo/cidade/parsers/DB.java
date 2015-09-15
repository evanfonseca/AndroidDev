package cvturismo.cidade.parsers;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DB extends SQLiteOpenHelper {

	// Logcat tag
	private static final String LOG = "DatabaseHelper";

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "cvturismo";

	// Table Names
	private static final String TABLE_CIDADES = "cidade";
	private static final String TABLE_OUTROS_SERVICOS = "outros_servicos";
	private static final String TABLE_LUGARES = "lugar";
	private static final String TABLE_FAVORITOS = "favorito";
	private static final String TABLE_COMENTARIOS_LUGARES = "comentariolugar";
	private static final String TABLE_COMENTARIOS_RATINGS = "comentariorating";
	private static final String TABLE_PONTOS_HISTORICOS = "ponto_historico";
	private static final String TABLE_CONTACTS_LUGARES = "contactolugar";
	private static final String TABLE_SUBCATEGORIAS = "subcategoria";
	private static final String TABLE_CATEGORIAS_LUGARES = "categorialugar";
	private static final String TABLE_CATEGORIAS= "categoria";
	private static final String TABLE_ITEMS_GALERIAS = "itemgaleria";
	private static final String TABLE_CONTEUDOS = "conteudo";
	private static final String TABLE_TIPOS_CONTEUDOS = "tipoconteudo";
	private static final String TABLE_FOTOS_CAPAS_CIDADES= "fotoscapascidade";
	
	// Cidades Table Columns names
	
		private static final String KEY_ID_CIDADE = "id";
		private static final String KEY_NAME_CIDADE = "nome";
		private static final String KEY_DESCRICAO_CIDADE= "descricao";
		private static final String KEY_ILHA_CIDADE ="ilha";
		private static final String KEY_LATITUDE_CENTRO_CIDADE="latitude_centro";
		private static final String KEY_LONGITUDE_CENTRO_CIDADE="longitude_centro";
		private static final String KEY_HISTORIA_CIDADE="historia";
		private static final String KEY_CLIMA_CIDADE="clima";
		private static final String KEY_GEOGRAFIA_CIDADE ="geografia";
		private static final String KEY_GERTORID_CIDADE="gestorId";
		private static final String KEY_GERTOR_CIDADE="gestor";
		private static final String KEY_LUGAR_QUANTIDADE_CIDADE="lugarQuantidade";
		private static final String KEY_CRIADO_EM_CIDADE="criado_em";
		private static final String KEY_ACTUALIZADO_EM_CIDADE="actualisado_em";
		
		
			
		// Favoritos Table Columns names
		private static final String KEY_ID_FAVORITOS="id";
		private static final String KEY_USERID_FAVORITOS="user_id";
		private static final String KEY_IDLUGAR_FAVORITOS="lugar_id";
		private static final String KEY_CRIADO_FAVORITOS="criado_em";
		private static final String KEY_ACTUALIZADO_EM_FAVORITOS="actualisado_em";
		
		
		// Cometario Lugar Table Columns names
		private static final String KEY_ID_COMENTARIO_LUGAR="id";
		private static final String KEY_IDCOMENTARIO_COMENTARIO_LUGAR="comentario_id";
		private static final String KEY_IDLUGAR_COMENTARIO_LUGAR="lugar_id";
		private static final String KEY_USERID_COMENTARIO_LUGAR="user_id";
		private static final String KEY_CRIADO_EM_COMENTARIO_LUGAR="criado_em";
		private static final String KEY_ACTUALIZADO_EM_COMENTARIO_LUGAR="actualisado_em";
		private static final String KEY_ID_COMENTARIO_RATING_ID="idCometarioRating_id";
		// Cometario rating Lugar Table Columns names
		
		private static final String KEY_ID_COMENTARIO_RATING="id";
		private static final String KEY_TEXTO_COMENTARIO_RATING="texto";
		private static final String KEY_RATING_COMENTARIO_RATING="rating";
		private static final String KEY_DATE_COMENTARIO_RATING="data";
		private static final String KEY_CRIADO_EM_COMENTARIO_RATING="criado_em";
		private static final String KEY_ACTUALIZADO_EM_COMENTARIO_RATING="actualisado_em";
		
		// Lugar Lugar Table Columns names
		
		
		
	    
		private static final String KEY_ID_LUGAR="id";
		private static final String KEY_NOME_LUGAR="nome";
		private static final String KEY_LATITUDE_LUGAR="latitude";
		private static final String KEY_LONGITUDE_LUGAR="longitude";
		private static final String KEY_DESCRICAO_LUGAR="descricao";
		private static final String KEY_CRIADO_EM_LUGAR="criado_em";
		private static final String KEY_ACTUALIZADO_EM_LUGAR="actualisado_em";
        
		private static final String KEY_USERID_LUGAR="user_id";
		private static final String KEY_USER_LUGAR="user";
		private static final String KEY_CIDADEID_LUGAR="cidade_id";
		private static final String KEY_CIDADE_LUGAR="cidade";
		
		// CAtegoria lugar Table Columns names
		
		private static final String KEY_ID_CATEGORIA_LUGAR="id";
		private static final String KEY_IDLUGAR_CATEGORIA_LUGAR="idlugar";
		private static final String KEY_IDSUBCATEGORIA_CATEGORIA_LUGAR="idsubcategoria";
		private static final String KEY_IDCATEGORIA_CATEGORIA_LUGAR="idcategoria";
		private static final String KEY_CRIADO_EM_CATEGORIA_LUGAR="criado_em";
		private static final String KEY_ACTUALIZADO_EM_CATEGORIA_LUGAR="actualisado_em";
		
		
		// Outros serviços Table Columns names
		
		private static final String KEY_ID_OUTROS_SERVICOS="id";
		private static final String KEY_DESCRICAO_OUTROS_SERVICOS="descricao";
		private static final String KEY_LINKSERVICO_OUTROS_SERVICOS="link_servico";
		private static final String KEY_CRIADO_EM_OUTROS_SERVICOS="criado_em";
		private static final String KEY_ACTUALIZADO_EM_OUTROS_SERVICOS="actualisado_em";
		private static final String KEY_CIDADEID_OUTROS_SERVICOS="cidade_id";
		
		
		// Pontos historicos Table Columns names
		
			private static final String KEY_ID_PONTOS_HISTORICOS="id";
			private static final String KEY_HISTORIA_PONTOS_HISTORICOS="historia";
			private static final String KEY_DATA_CRIACAO_PONTOS_HISTORICOS="data_criacao";
			private static final String KEY_LUARID_PONTOS_HISTORICOS="lugar_id";
			private static final String KEY_CRIADO_EM_PONTOS_HISTORICOS="criado_em";
			private static final String KEY_ACTUALIZADO_EM_PONTOS_HISTORICOS="actualisado_em";
			
			
			
		// SUBCAtegoria lugar Table Columns names
		
			private static final String KEY_ID_SUBCATEGORIA="id";
			private static final String KEY_IDCATEGORIA_SUBCATEGORIA="idcategoria";
			private static final String KEY_DESCRICAO_SUBCATEGORIA="descricao";
			private static final String KEY_LINKICON_SUBCATEGORIA="link_icon";
			private static final String KEY_CRIADO_EM_SUBCATEGORIA="criado_em";
			private static final String KEY_ACTUALIZADO_EM_SUBCATEGORIA="actualisado_em";
			
			
			// CAtegoria  Table Columns names
			
			private static final String KEY_ID_CATEGORIA="id";
			private static final String KEY_DESCRICAO_CATEGORIA="descriacao";
			private static final String KEY_LINKICON_CATEGORIA="link_icon";
			private static final String KEY_CRIADO_EM_CATEGORIA="criado_em";
			private static final String KEY_ACTUALIZADO_EM_CATEGORIA="actualisado_em";
			
			// Contacto lugar Table Columns names
			
			private static final String KEY_ID_CONTACTO_LUGAR="id";
			private static final String KEY_IDLUGAR_CONTACTO_LUGAR="lugar_id";
			private static final String KEY_TELEFONE_CONTACTO_LUGAR="telefone";
			private static final String KEY_EMAIL_CONTACTO_LUGAR="email";
			private static final String KEY_ENDERECO_CONTACTO_LUGAR="endereco";
			private static final String KEY_LINKWEB_CONTACTO_LUGAR="linkWeb";
			private static final String KEY_CRIADO_EM_CONTACTO_LUGAR="criado_em";
			private static final String KEY_ACTUALIZADO_EM_CONTACTO_LUGAR="actualisado_em";
			
			
				
			// Item Galeria Columns names
			
			private static final String KEY_ID_ITEM_GALERIA="id";
			private static final String KEY_LUGARID_ITEM_GALERIA="lugar_id";
			private static final String KEY_IDCONTEUDO_ITEM_GALERIA="idconteudo";
			private static final String KEY_CRIADO_EM_ITEM_GALERIA="criado_em";
			private static final String KEY_ACTUALIZADO_EM_ITEM_GALERIA="actualisado_em";
			
				
			// Conteudo Columns names
			
			private static final String KEY_ID_CONTEUDO="id";
			private static final String KEY_IDTIPOCONTEUDO_CONTEUDO="id_tipo_conteudo";
			private static final String KEY_LINKFILE_CONTEUDO="link_file";
			private static final String KEY_CRIADO_EM_CONTEUDO="criado_em";
			private static final String KEY_ACTUALIZADO_EM_CONTEUDO="actualisado_em";
					
				
			// Tipo Conteudo Columns names
			
			private static final String KEY_ID_TIPO_CONTEUDO="id";
			private static final String KEY_DESCRICAO_TIPO_CONTEUDO="descricao";
			private static final String KEY_CRIADO_EM_TIPO_CONTEUDO="criado_em";
			private static final String KEY_ACTUALIZADO_EM_TIPO_CONTEUDO="actualisado_em";
							
			// Foto de capa cidade Columns names
			
			private static final String KEY_ID_FOTO_CAPA_CIDADE="id";
			private static final String  KEY_IDITEMGALERIA_FOTO_CAPA_CIDADE="id_ItemGaleria";
			private static final String KEY_CRIADO_EM_FOTO_CAPA_CIDADE="criado_em";
			private static final String KEY_ACTUALIZADO_EM_FOTO_CAPA_CIDADE="actualisado_em";

	// Table Create Statements
	// Todo table create statement

	

	private static final  String CREATE_TABLE_CIDADES = "CREATE TABLE " + TABLE_CIDADES + "("
			+ KEY_ID_CIDADE + " INTEGER PRIMARY KEY," 
			+KEY_NAME_CIDADE + " TEXT,"
			+ KEY_DESCRICAO_CIDADE+ " TEXT" 
			+KEY_ILHA_CIDADE+ " TEXT" 
			+KEY_LATITUDE_CENTRO_CIDADE+ " TEXT"
			+KEY_LONGITUDE_CENTRO_CIDADE+ " TEXT" 
			+KEY_HISTORIA_CIDADE+ " TEXT" 
			+KEY_CLIMA_CIDADE+" TEXT"
			+KEY_GEOGRAFIA_CIDADE +"TEXT"
			+KEY_CRIADO_EM_CIDADE+"TEXT"
			+KEY_ACTUALIZADO_EM_CIDADE+"TEXT"	
			+KEY_GERTORID_CIDADE+ "INTEGER"
			+KEY_GERTOR_CIDADE+ " TEXT"
			+KEY_LUGAR_QUANTIDADE_CIDADE+"INTEGER "+ ")";
	

	private static final  String CREATE_TABLE_OUTROS_SERVICOS = "CREATE TABLE " + TABLE_OUTROS_SERVICOS + "("
			+ KEY_ID_OUTROS_SERVICOS + " INTEGER PRIMARY KEY," 
			+ KEY_DESCRICAO_OUTROS_SERVICOS + " TEXT,"
			+ KEY_LINKSERVICO_OUTROS_SERVICOS + " TEXT"
			
			+KEY_CIDADEID_OUTROS_SERVICOS + "INTEGER"
			+KEY_CRIADO_EM_OUTROS_SERVICOS+"TEXT"
			+KEY_ACTUALIZADO_EM_OUTROS_SERVICOS+"TEXT"+ 
			")";
	
	private static final String CREATE_TABLE_LUGARES = "CREATE TABLE " + TABLE_LUGARES+ "("
			+ KEY_ID_LUGAR+ "INTEGER PRIMARY KEY," 
			+ KEY_NOME_LUGAR + " TEXT"
			+ KEY_LATITUDE_LUGAR+ "TEXT"
			+ KEY_LONGITUDE_LUGAR+ "TEXT"
			+ KEY_DESCRICAO_LUGAR+ " TEXT"
			
			+KEY_USERID_LUGAR + "INTEGER"
			+KEY_USER_LUGAR + "TEXT"
			+KEY_CIDADEID_LUGAR + "INTEGER" 
			+KEY_CIDADE_LUGAR + "TEXT" 
			
			+KEY_CRIADO_EM_LUGAR+"TEXT"
			+KEY_ACTUALIZADO_EM_LUGAR+"TEXT"+ 
			")";
	
	private static final String CREATE_TABLE_FAVORITOS = "CREATE TABLE " + TABLE_FAVORITOS + "("
			+ KEY_ID_FAVORITOS+ " INTEGER PRIMARY KEY," 
			+KEY_USERID_FAVORITOS + " TEXT"
			+KEY_IDLUGAR_FAVORITOS+ " INTEGER" 
			+KEY_CRIADO_FAVORITOS+"TEXT"
			+KEY_ACTUALIZADO_EM_FAVORITOS+"TEXT"
			+ ")";
	
	private static final String CREATE_TABLE_COMENTARIOS_LUGARES= "CREATE TABLE " + TABLE_COMENTARIOS_LUGARES+ "("
			+ KEY_ID_COMENTARIO_LUGAR+ "INTEGER PRIMARY KEY," 
			+KEY_IDCOMENTARIO_COMENTARIO_LUGAR + " INTEGER"
			+KEY_IDLUGAR_COMENTARIO_LUGAR+ " INTEGER"
			
			+KEY_USERID_COMENTARIO_LUGAR + " TEXT" 
			+KEY_CRIADO_EM_COMENTARIO_LUGAR +"TEXT"
			+KEY_ACTUALIZADO_EM_COMENTARIO_LUGAR +"TEXT"
			+KEY_ID_COMENTARIO_RATING_ID+"INTEGER"
			+ ")";

	
	private static final String CREATE_TABLE_COMENTARIOS_RATINGS = "CREATE TABLE " + TABLE_COMENTARIOS_RATINGS+ "("
			+ KEY_ID_COMENTARIO_RATING+ " INTEGER PRIMARY KEY," 
			+KEY_TEXTO_COMENTARIO_RATING + "TEXT"
			+KEY_RATING_COMENTARIO_RATING+"TEXT"
			+KEY_DATE_COMENTARIO_RATING + "TEXT" 
			
			+KEY_CRIADO_EM_COMENTARIO_RATING +"TEXT"
			+KEY_ACTUALIZADO_EM_COMENTARIO_RATING +"TEXT"
			+ ")";
	
	private static final  String  CREATE_TABLE_PONTOS_HISTORICOS= "CREATE TABLE " + TABLE_PONTOS_HISTORICOS+ "("
			
				+ KEY_ID_PONTOS_HISTORICOS + " INTEGER PRIMARY KEY," 
				+KEY_HISTORIA_PONTOS_HISTORICOS + " TEXT"
				+KEY_DATA_CRIACAO_PONTOS_HISTORICOS+"TEXT"
				+KEY_LUARID_PONTOS_HISTORICOS+"INTEGER "
				+KEY_CRIADO_EM_PONTOS_HISTORICOS +"TEXT"
				+KEY_ACTUALIZADO_EM_PONTOS_HISTORICOS +"TEXT"
				+ ")";

	private static final String  CREATE_TABLE_CONTACTS_LUGARES= "CREATE TABLE " + TABLE_CONTACTS_LUGARES+ "("
			
				+ KEY_ID_CONTACTO_LUGAR+ " INTEGER PRIMARY KEY," 
				+KEY_IDLUGAR_CONTACTO_LUGAR + " TEXT"
				+KEY_TELEFONE_CONTACTO_LUGAR+"INTEGER"
				+KEY_EMAIL_CONTACTO_LUGAR+ " TEXT"
				+KEY_ENDERECO_CONTACTO_LUGAR+" TEXT"
				+KEY_LINKWEB_CONTACTO_LUGAR+" TEXT"
				
				+KEY_CRIADO_EM_CONTACTO_LUGAR +"TEXT"
				+KEY_ACTUALIZADO_EM_CONTACTO_LUGAR+"TEXT"
				
				+ ")";
	
	private static final String  CREATE_TABLE_SUBCATEGORIAS= "CREATE TABLE " + TABLE_SUBCATEGORIAS+ "("
			
				+ KEY_ID_SUBCATEGORIA + "INTEGER PRIMARY KEY," 
				+KEY_IDCATEGORIA_SUBCATEGORIA+ "INTEGER"
				+KEY_DESCRICAO_SUBCATEGORIA+" TEXT"
				+KEY_LINKICON_SUBCATEGORIA+" TEXT"
				
			    +KEY_CRIADO_EM_SUBCATEGORIA +"TEXT"
				+KEY_ACTUALIZADO_EM_SUBCATEGORIA+"TEXT"
				+	")";
	
	
	private static final  String  CREATE_TABLE_CATEGORIAS_LUGARES= "CREATE TABLE " + TABLE_CATEGORIAS_LUGARES+ "("
			
				+ KEY_ID_CATEGORIA_LUGAR+ " INTEGER PRIMARY KEY," 
				+KEY_IDLUGAR_CATEGORIA_LUGAR+ " INTEGER"
				+KEY_IDSUBCATEGORIA_CATEGORIA_LUGAR+"INTEGER"
				+KEY_IDCATEGORIA_CATEGORIA_LUGAR+"INTEGER"
				
				+KEY_CRIADO_EM_CATEGORIA_LUGAR +"TEXT"
				+KEY_ACTUALIZADO_EM_CATEGORIA_LUGAR+"TEXT"
				+	")";
	
	
	private static final  String  CREATE_TABLE_CATEGORIAS= "CREATE TABLE " + TABLE_CATEGORIAS+ "("
				
				+ KEY_ID_CATEGORIA+ " INTEGER PRIMARY KEY," 
				+KEY_DESCRICAO_CATEGORIA+ " TEXT"
				+KEY_LINKICON_CATEGORIA+"TEXT"
				
				+KEY_CRIADO_EM_CATEGORIA +"TEXT"
				+KEY_ACTUALIZADO_EM_CATEGORIA+"TEXT"
				
				+	")";
	
	
	private static final  String  CREATE_TABLE_ITEMS_GALERIAS = "CREATE TABLE " + TABLE_ITEMS_GALERIAS+ "("
				
				+ KEY_ID_ITEM_GALERIA+ " INTEGER PRIMARY KEY," 
				+KEY_LUGARID_ITEM_GALERIA+ " TEXT"
				+KEY_IDCONTEUDO_ITEM_GALERIA+"INTEGER"
				
				+KEY_CRIADO_EM_ITEM_GALERIA +"TEXT"
				+KEY_ACTUALIZADO_EM_ITEM_GALERIA+"TEXT"
				
				+	")"; 
	
	
	private static final String  CREATE_TABLE_CONTEUDOS = "CREATE TABLE " + TABLE_CONTEUDOS+ "("
			
				+  KEY_ID_CONTEUDO+ " INTEGER PRIMARY KEY," 
				+KEY_IDTIPOCONTEUDO_CONTEUDO+ "INTEGER "
				+KEY_LINKFILE_CONTEUDO+"TEXT"
				
				+KEY_CRIADO_EM_CONTEUDO +"TEXT"
				+KEY_ACTUALIZADO_EM_CONTEUDO+"TEXT"
				+	")";
	
	private static final  String  CREATE_TABLE_TIPOS_CONTEUDOS   = "CREATE TABLE " + TABLE_TIPOS_CONTEUDOS + "("
				
				+ KEY_ID_TIPO_CONTEUDO+ " INTEGER PRIMARY KEY,," 
				+KEY_DESCRICAO_TIPO_CONTEUDO+ " TEXT"
				
				+KEY_CRIADO_EM_TIPO_CONTEUDO+"TEXT"
				+KEY_ACTUALIZADO_EM_TIPO_CONTEUDO+"TEXT"
			
				
				+	")";
	
	
	private static final  String  CREATE_TABLE_FOTOS_CAPAS_CIDADES  = "CREATE TABLE " + TABLE_FOTOS_CAPAS_CIDADES+ "("
				
				+ KEY_ID_FOTO_CAPA_CIDADE+ " INTEGER PRIMARY KEY," 
				+KEY_IDITEMGALERIA_FOTO_CAPA_CIDADE+ "INTEGER"
				
			    +KEY_CRIADO_EM_FOTO_CAPA_CIDADE+"TEXT"
				+KEY_ACTUALIZADO_EM_FOTO_CAPA_CIDADE+"TEXT"
				+	")";
	

	public DB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		// creating required tables
		db.execSQL(CREATE_TABLE_CIDADES);
		db.execSQL(CREATE_TABLE_OUTROS_SERVICOS);
		db.execSQL(CREATE_TABLE_LUGARES);
		db.execSQL(CREATE_TABLE_FAVORITOS);
		
		db.execSQL(CREATE_TABLE_COMENTARIOS_LUGARES);
		db.execSQL(CREATE_TABLE_COMENTARIOS_RATINGS);
		db.execSQL(CREATE_TABLE_PONTOS_HISTORICOS);
		db.execSQL(CREATE_TABLE_CONTACTS_LUGARES);
		
		db.execSQL( CREATE_TABLE_SUBCATEGORIAS);
		
		db.execSQL(CREATE_TABLE_CATEGORIAS_LUGARES);
		db.execSQL(CREATE_TABLE_CATEGORIAS);
		db.execSQL(CREATE_TABLE_ITEMS_GALERIAS);
		db.execSQL(CREATE_TABLE_CONTEUDOS);
		db.execSQL(CREATE_TABLE_TIPOS_CONTEUDOS);
		db.execSQL( CREATE_TABLE_FOTOS_CAPAS_CIDADES );

		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables

		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMENTARIOS_LUGARES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMENTARIOS_RATINGS);
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_LUGARES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CIDADES);
		
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS_LUGARES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITOS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PONTOS_HISTORICOS);
		
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS_GALERIAS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LUGARES);
		
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTEUDOS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIPOS_CONTEUDOS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOTOS_CAPAS_CIDADES);
		
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_OUTROS_SERVICOS);
		
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIAS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBCATEGORIAS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIAS_LUGARES);
		
		// create new tables
		onCreate(db);
	}

	// ------------------------ "Cidade" table methods ----------------//


	
	
}
