package cvturismo.cidade.model;

public class Conteudo {

	
	int _id;
	int _id_tipo_conteudo;
	int _link_file;
	
	
	public Conteudo(int _id, int _id_tipo_conteudo, int _link_file) {
		super();
		this._id = _id;
		this._id_tipo_conteudo = _id_tipo_conteudo;
		this._link_file = _link_file;
	}
	
	
	
	public Conteudo( int _id_tipo_conteudo, int _link_file) {
		super();
		
		this._id_tipo_conteudo = _id_tipo_conteudo;
		this._link_file = _link_file;
	}



	public int get_id() {
		return _id;
	}



	public void set_id(int _id) {
		this._id = _id;
	}



	public int get_id_tipo_conteudo() {
		return _id_tipo_conteudo;
	}



	public void set_id_tipo_conteudo(int _id_tipo_conteudo) {
		this._id_tipo_conteudo = _id_tipo_conteudo;
	}



	public int get_link_file() {
		return _link_file;
	}



	public void set_link_file(int _link_file) {
		this._link_file = _link_file;
	}
	
	
	
}
