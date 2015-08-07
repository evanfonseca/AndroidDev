package cvturismo.cidade.model;

public class ContactoLugar {

	
	int _id;
	int _idlugar;
	String _telefone;
	String _email;
	String _emdereco;
	String _lnikweb;
	
	
	public ContactoLugar(int _id, int _idlugar, String _telefone,
			String _email, String _emdereco, String _likweb) {
		super();
		this._id = _id;
		this._idlugar = _idlugar;
		this._telefone = _telefone;
		this._email = _email;
		this._emdereco = _emdereco;
		this._lnikweb = _likweb;
	}
	
	
	public ContactoLugar( int _idlugar, String _telefone,
			String _email, String _emdereco, String _likweb) {
		super();
		
		this._idlugar = _idlugar;
		this._telefone = _telefone;
		this._email = _email;
		this._emdereco = _emdereco;
		this._lnikweb = _likweb;
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


	public String get_telefone() {
		return _telefone;
	}


	public void set_telefone(String _telefone) {
		this._telefone = _telefone;
	}


	public String get_email() {
		return _email;
	}


	public void set_email(String _email) {
		this._email = _email;
	}


	public String get_emdereco() {
		return _emdereco;
	}


	public void set_emdereco(String _emdereco) {
		this._emdereco = _emdereco;
	}


	public String get_likweb() {
		return _lnikweb;
	}


	public void set_likweb(String _likweb) {
		this._lnikweb = _likweb;
	}
	
	
	
	
}
