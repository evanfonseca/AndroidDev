package cvturismo.cidade.model;

public class Outros_Servicos {

	
	int _id;
	String _descricao;
	String _link_servico;
	int _idcidade;
	
	
	public Outros_Servicos(int _id, String _descricao, String _link_servico,
			int _idcidade) {
		super();
		this._id = _id;
		this._descricao = _descricao;
		this._link_servico = _link_servico;
		this._idcidade = _idcidade;
	}
	
	public Outros_Servicos( String _descricao, String _link_servico,
			int _idcidade) {
		super();
		
		this._descricao = _descricao;
		this._link_servico = _link_servico;
		this._idcidade = _idcidade;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_descricao() {
		return _descricao;
	}

	public void set_descricao(String _descricao) {
		this._descricao = _descricao;
	}

	public String get_link_servico() {
		return _link_servico;
	}

	public void set_link_servico(String _link_servico) {
		this._link_servico = _link_servico;
	}

	public int get_idcidade() {
		return _idcidade;
	}

	public void set_idcidade(int _idcidade) {
		this._idcidade = _idcidade;
	}
	
	
	
}
