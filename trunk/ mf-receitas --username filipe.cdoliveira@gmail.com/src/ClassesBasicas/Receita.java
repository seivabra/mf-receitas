package ClassesBasicas;

import java.util.ArrayList;

public class Receita {
	int id;
	String descricao;
	ArrayList<Item> itens;
	int qtdPessoasServe;
	int tempoForno;
	int tempoCongelador;
	Double custoMedio;
	String modoPreparo;
	//Image Imagem;
	public Receita() {
		super();
		itens = new ArrayList<Item>();
	}
	public Receita(int id, String descricao, ArrayList<Item> itens,
			int qtdPessoasServe, int tempoForno, int tempoCongelador,
			Double custoMedio, String modoPreparo) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.itens = itens;
		this.qtdPessoasServe = qtdPessoasServe;
		this.tempoForno = tempoForno;
		this.tempoCongelador = tempoCongelador;
		this.custoMedio = custoMedio;
		this.modoPreparo = modoPreparo;
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
	public int getQtdPessoasServe() {
		return qtdPessoasServe;
	}
	public void setQtdPessoasServe(int qtdPessoasServe) {
		this.qtdPessoasServe = qtdPessoasServe;
	}
	public int getTempoForno() {
		return tempoForno;
	}
	public void setTempoForno(int tempoForno) {
		this.tempoForno = tempoForno;
	}
	public int getTempoCongelador() {
		return tempoCongelador;
	}
	public void setTempoCongelador(int tempoCongelador) {
		this.tempoCongelador = tempoCongelador;
	}
	public Double getCustoMedio() {
		return custoMedio;
	}
	public void setCustoMedio(Double custoMedio) {
		this.custoMedio = custoMedio;
	}
	public String getModoPreparo() {
		return modoPreparo;
	}
	public void setModoPreparo(String modoPreparo) {
		this.modoPreparo = modoPreparo;
	}
	@Override
	public String toString() {
		return "Receita [id=" + id + ", descricao=" + descricao + ", itens="
				+ itens + ", qtdPessoasServe=" + qtdPessoasServe
				+ ", tempoForno=" + tempoForno + ", tempoCongelador="
				+ tempoCongelador + ", custoMedio=" + custoMedio
				+ ", modoPreparo=" + modoPreparo + "]";
	}

	
}
