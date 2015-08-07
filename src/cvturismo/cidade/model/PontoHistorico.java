package cvturismo.cidade.model;

import java.sql.Date;

public class PontoHistorico {

	
	int _id;
	String _historia;
	Date _data_criacao;
	
	
	public PontoHistorico(int _id, String _historia, Date _data_criacao) {
		super();
		this._id = _id;
		this._historia = _historia;
		this._data_criacao = _data_criacao;
	}
	
	public PontoHistorico( String _historia, Date _data_criacao) {
		super();
		
		this._historia = _historia;
		this._data_criacao = _data_criacao;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_historia() {
		return _historia;
	}

	public void set_historia(String _historia) {
		this._historia = _historia;
	}

	public Date get_data_criacao() {
		return _data_criacao;
	}

	public void set_data_criacao(Date _data_criacao) {
		this._data_criacao = _data_criacao;
	}
	
	
	
	
}
