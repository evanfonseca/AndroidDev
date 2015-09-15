package cvturismo.cidade.model;

public class CategoriaC {
	int Id;
	String Descricao;
	int Img;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getDescricao() {
		return Descricao;
	}
	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
	public int getImg() {
		return Img;
	}
	public void setImg(int img) {
		Img = img;
	}
	public CategoriaC(int id, String descricao, int img) {
		super();
		Id = id;
		Descricao = descricao;
		Img = img;
	}
	

}
