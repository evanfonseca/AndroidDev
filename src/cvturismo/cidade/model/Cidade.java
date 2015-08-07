package cvturismo.cidade.model;

public class Cidade {


	//private variables
	String _id;
	String _nome;
	String _descricao;
	String _ilha;
	String _latitude_center;
	String _longitude_center;
	String _historia;
	String _clima;
	String _geografia;
	String  _criado_em;
	String _actualisado_em;
	String _gestorId;
	String _gestor;
	String _lugarQuantidade;
	String _fotoCapa;
	
	
	public Cidade( String _nome, String _descricao, String _ilha,
			String _latitude_center, String _longitude_center,
			String _historia, String _clima, String _geografia,
			String _criado_em, String _actualisado_em, String _gestorId,
			String _gestor, String _lugarQuantidade, String _fotoCapa) {
		super();
		
		this._nome = _nome;
		this._descricao = _descricao;
		this._ilha = _ilha;
		this._latitude_center = _latitude_center;
		this._longitude_center = _longitude_center;
		this._historia = _historia;
		this._clima = _clima;
		this._geografia = _geografia;
		this._criado_em = _criado_em;
		this._actualisado_em = _actualisado_em;
		this._gestorId = _gestorId;
		this._gestor = _gestor;
		this._lugarQuantidade = _lugarQuantidade;
		this._fotoCapa = _fotoCapa;
	}


	public Cidade(String _id, String _nome, String _descricao, String _ilha,
			String _latitude_center, String _longitude_center,
			String _historia, String _clima, String _geografia,
			String _criado_em, String _actualisado_em, String _gestorId,
			String _gestor, String _lugarQuantidade, String _fotoCapa) {
		super();
		this._id = _id;
		this._nome = _nome;
		this._descricao = _descricao;
		this._ilha = _ilha;
		this._latitude_center = _latitude_center;
		this._longitude_center = _longitude_center;
		this._historia = _historia;
		this._clima = _clima;
		this._geografia = _geografia;
		this._criado_em = _criado_em;
		this._actualisado_em = _actualisado_em;
		this._gestorId = _gestorId;
		this._gestor = _gestor;
		this._lugarQuantidade = _lugarQuantidade;
		this._fotoCapa = _fotoCapa;
	}


	





	public String get_id() {
		return _id;
	}


	public void set_id(String _id) {
		this._id = _id;
	}


	public String get_nome() {
		return _nome;
	}


	public void set_nome(String _nome) {
		this._nome = _nome;
	}


	public String get_descricao() {
		return _descricao;
	}


	public void set_descricao(String _descricao) {
		this._descricao = _descricao;
	}


	public String get_ilha() {
		return _ilha;
	}


	public void set_ilha(String _ilha) {
		this._ilha = _ilha;
	}


	public String get_latitude_center() {
		return _latitude_center;
	}


	public void set_latitude_center(String _latitude_center) {
		this._latitude_center = _latitude_center;
	}


	public String get_longitude_center() {
		return _longitude_center;
	}


	public void set_longitude_center(String _longitude_center) {
		this._longitude_center = _longitude_center;
	}


	public String get_historia() {
		return _historia;
	}


	public void set_historia(String _historia) {
		this._historia = _historia;
	}


	public String get_clima() {
		return _clima;
	}


	public void set_clima(String _clima) {
		this._clima = _clima;
	}


	public String get_geografia() {
		return _geografia;
	}


	public void set_geografia(String _geografia) {
		this._geografia = _geografia;
	}


	public String get_criado_em() {
		return _criado_em;
	}


	public void set_criado_em(String _criado_em) {
		this._criado_em = _criado_em;
	}


	public String get_actualisado_em() {
		return _actualisado_em;
	}


	public void set_actualisado_em(String _actualisado_em) {
		this._actualisado_em = _actualisado_em;
	}


	public String get_gestorId() {
		return _gestorId;
	}


	public void set_gestorId(String _gestorId) {
		this._gestorId = _gestorId;
	}


	public String get_gestor() {
		return _gestor;
	}


	public void set_gestor(String _gestor) {
		this._gestor = _gestor;
	}


	public String get_lugarQuantidade() {
		return _lugarQuantidade;
	}


	public void set_lugarQuantidade(String _lugarQuantidade) {
		this._lugarQuantidade = _lugarQuantidade;
	}


	public String get_fotoCapa() {
		return _fotoCapa;
	}


	public void set_fotoCapa(String _fotoCapa) {
		this._fotoCapa = _fotoCapa;
	}
	
	

	
	
	
	
}
