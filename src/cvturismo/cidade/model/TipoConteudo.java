package cvturismo.cidade.model;

public class TipoConteudo {

	
	int _id;
	int _descricao;
	
	
	public TipoConteudo(int _id, int _descricao) {
		super();
		this._id = _id;
		this._descricao = _descricao;
	}
	
	public TipoConteudo( int _descricao) {
		super();
		
		this._descricao = _descricao;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int get_descricao() {
		return _descricao;
	}

	public void set_descricao(int _descricao) {
		this._descricao = _descricao;
	}
	
	
	
	
}
