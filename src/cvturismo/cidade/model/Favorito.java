package cvturismo.cidade.model;

public class Favorito {
	
	
	//private variables
	
	int _id;
	int _idutilizador;
	int _idlugar;
	
	
	public Favorito(int _id, int _idutilizador, int _idlugar) {
		super();
		this._id = _id;
		this._idutilizador = _idutilizador;
		this._idlugar = _idlugar;
	}
	
	public Favorito( int _idutilizador, int _idlugar) {
		super();
		
		this._idutilizador = _idutilizador;
		this._idlugar = _idlugar;
	}
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public int get_idutilizador() {
		return _idutilizador;
	}
	public void set_idutilizador(int _idutilizador) {
		this._idutilizador = _idutilizador;
	}
	public int get_idlugar() {
		return _idlugar;
	}
	public void set_idlugar(int _idlugar) {
		this._idlugar = _idlugar;
	}
	

}
