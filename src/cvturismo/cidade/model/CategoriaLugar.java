package cvturismo.cidade.model;

public class CategoriaLugar {

	
	int _id;
	int _idlugar;
	int _idsubcategoria;
	int _idcategoria;
	
	
	
	public CategoriaLugar(int _id, int _idlugar, int _idsubcategoria,
			int _idcategoria) {
		super();
		this._id = _id;
		this._idlugar = _idlugar;
		this._idsubcategoria = _idsubcategoria;
		this._idcategoria = _idcategoria;
	}
	

	public CategoriaLugar( int _idlugar, int _idsubcategoria,
			int _idcategoria) {
		super();
		
		this._idlugar = _idlugar;
		this._idsubcategoria = _idsubcategoria;
		this._idcategoria = _idcategoria;
	}


	public int get_id() {
		return _id;
	}


	public void set_id(int _id) {
		this._id = _id;
	}


	public int get_idlugar() {
		return _idlugar;
	}


	public void set_idlugar(int _idlugar) {
		this._idlugar = _idlugar;
	}


	public int get_idsubcategoria() {
		return _idsubcategoria;
	}


	public void set_idsubcategoria(int _idsubcategoria) {
		this._idsubcategoria = _idsubcategoria;
	}


	public int get_idcategoria() {
		return _idcategoria;
	}


	public void set_idcategoria(int _idcategoria) {
		this._idcategoria = _idcategoria;
	}
	
	
	
	
}
