package cvturismo.cidade.model;

import java.sql.Date;

public class ComentarioRating {

	
	int _id;
	String _texo;
	int _rating;
	Date _data;
	
	
	public ComentarioRating(int _id, String _texo, int _rating, Date _data) {
		super();
		this._id = _id;
		this._texo = _texo;
		this._rating = _rating;
		this._data = _data;
	}
	
	
	public ComentarioRating(String _texo, int _rating, Date _data) {
		super();

		this._texo = _texo;
		this._rating = _rating;
		this._data = _data;
		
	}


	public int get_id() {
		return _id;
	}


	public void set_id(int _id) {
		this._id = _id;
	}


	public String get_texo() {
		return _texo;
	}


	public void set_texo(String _texo) {
		this._texo = _texo;
	}


	public int get_rating() {
		return _rating;
	}


	public void set_rating(int _rating) {
		this._rating = _rating;
	}


	public Date get_data() {
		return _data;
	}


	public void set_data(Date _data) {
		this._data = _data;
	}
	
	
	
}
