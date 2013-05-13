package ClassesBasicas;

import java.util.ArrayList;

public class ListaCompras{
	int id;
	String descricao;
	ArrayList<Item> itens;
	public ListaCompras() {
		super();
		itens = new ArrayList<Item>();
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
	public ArrayList<Item> getItens() {
		return itens;
	}
	public void setItens(ArrayList<Item> itens) {
		this.itens = itens;
	}
	@Override
	public String toString() {
		return "ListaCompras [id=" + id + ", descricao=" + descricao
				+ ", itens=" + itens + "]";
	}
}
