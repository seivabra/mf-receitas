package ClassesBasicas;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Categoria implements Serializable{
	int id;
	String descricao;
	public Categoria() {
		super();
	}
	public Categoria(int id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		return descricao;
	}
}
