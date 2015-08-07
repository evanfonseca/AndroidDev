package cvturismo.cidade.model;

public class SubCategoria {

	
	int _id;
	int _idcategoria;
	String _descricao;
	String _link_icon;
	
	
	public SubCategoria(int _id, int _idcategoria, String _descricao,
			String _link_icon) {
		super();
		this._id = _id;
		this._idcategoria = _idcategoria;
		this._descricao = _descricao;
		this._link_icon = _link_icon;
	}
	
	
	public SubCategoria( int _idcategoria, String _descricao,
			String _link_icon) {
		super();
		
		this._idcategoria = _idcategoria;
		this._descricao = _descricao;
		this._link_icon = _link_icon;
	}


	public int get_id() {
		return _id;
	}


	public void set_id(int _id) {
		this._id = _id;
	}


	public int get_idcategoria() {
		return _idcategoria;
	}


	public void set_idcategoria(int _idcategoria) {
		this._idcategoria = _idcategoria;
	}


	public String get_descricao() {
		return _descricao;
	}


	public void set_descricao(String _descricao) {
		this._descricao = _descricao;
	}


	public String get_link_icon() {
		return _link_icon;
	}


	public void set_link_icon(String _link_icon) {
		this._link_icon = _link_icon;
	}
	
	
}
