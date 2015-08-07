package cvturismo.cidade.model;

public class FotoCapaCidade {

	
	int _id;
	int _id_item_galeria;
	
	
	
	public FotoCapaCidade(int _id, int _id_item_galeria) {
		super();
		this._id = _id;
		this._id_item_galeria = _id_item_galeria;
	}
	
	
	public FotoCapaCidade(int _id_item_galeria) {
		super();
		
		this._id_item_galeria = _id_item_galeria;
	}
	
}
