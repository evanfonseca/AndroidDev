package cvturismo.cidade.model;

public class ItemGaleria {

	
	int _id;
	int _idlugar;
	int _idconteudo;
	
	
	
	public ItemGaleria(int _id, int _idlugar, int _idconteudo) {
		super();
		this._id = _id;
		this._idlugar = _idlugar;
		this._idconteudo = _idconteudo;
	}
	
	public ItemGaleria( int _idlugar, int _idconteudo) {
		super();
		
		this._idlugar = _idlugar;
		this._idconteudo = _idconteudo;
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

	public int get_idconteudo() {
		return _idconteudo;
	}

	public void set_idconteudo(int _idconteudo) {
		this._idconteudo = _idconteudo;
	}

	
}
