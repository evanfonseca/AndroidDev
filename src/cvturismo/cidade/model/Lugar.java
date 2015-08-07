package cvturismo.cidade.model;

public class Lugar {

	
	int _id;
	String _nome;
    Float _latitude;
    Float _longitude;
    String _descricao;
    int _idutilizador;
    String _estado;
    int _idcidade;
    
    
	public Lugar(int _id, String _nome, Float _latitude, Float _longitude,
			String _descricao, int _idutilizador, String _estado, int _idcidade) {
		super();
		this._id = _id;
		this._nome = _nome;
		this._latitude = _latitude;
		this._longitude = _longitude;
		this._descricao = _descricao;
		this._idutilizador = _idutilizador;
		this._estado = _estado;
		this._idcidade = _idcidade;
	}
	
	public Lugar(String _nome, Float _latitude, Float _longitude,
			String _descricao, int _idutilizador, String _estado, int _idcidade) {
		super();
		
		this._nome = _nome;
		this._latitude = _latitude;
		this._longitude = _longitude;
		this._descricao = _descricao;
		this._idutilizador = _idutilizador;
		this._estado = _estado;
		this._idcidade = _idcidade;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_nome() {
		return _nome;
	}

	public void set_nome(String _nome) {
		this._nome = _nome;
	}

	public Float get_latitude() {
		return _latitude;
	}

	public void set_latitude(Float _latitude) {
		this._latitude = _latitude;
	}

	public Float get_longitude() {
		return _longitude;
	}

	public void set_longitude(Float _longitude) {
		this._longitude = _longitude;
	}

	public String get_descricao() {
		return _descricao;
	}

	public void set_descricao(String _descricao) {
		this._descricao = _descricao;
	}

	public int get_idutilizador() {
		return _idutilizador;
	}

	public void set_idutilizador(int _idutilizador) {
		this._idutilizador = _idutilizador;
	}

	public String get_estado() {
		return _estado;
	}

	public void set_estado(String _estado) {
		this._estado = _estado;
	}

	public int get_idcidade() {
		return _idcidade;
	}

	public void set_idcidade(int _idcidade) {
		this._idcidade = _idcidade;
	}
    
    
    
    
    
}
