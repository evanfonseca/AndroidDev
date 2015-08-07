package cvturismo.cidade.model;

public class Categoria {

	int _id;
	String _destring;
	String _link_icon;
	
	public Categoria(int _id, String _destring, String _link_icon) {
		super();
		this._id = _id;
		this._destring = _destring;
		this._link_icon = _link_icon;
	}
	
	
	public Categoria( String _destring, String _link_icon) {
		super();
		
		this._destring = _destring;
		this._link_icon = _link_icon;
	}


	public int get_id() {
		return _id;
	}


	public void set_id(int _id) {
		this._id = _id;
	}


	public String get_destring() {
		return _destring;
	}


	public void set_destring(String _destring) {
		this._destring = _destring;
	}


	public String get_link_icon() {
		return _link_icon;
	}


	public void set_link_icon(String _link_icon) {
		this._link_icon = _link_icon;
	}
	
	
	
	
}
