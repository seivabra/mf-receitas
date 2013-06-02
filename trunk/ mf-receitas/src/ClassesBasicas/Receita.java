package ClassesBasicas;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Receita implements Serializable{
	int id;
	String descricao;
	ArrayList<Item> itens;
	int qtdPessoasServe;
	int tempoForno;
	int tempoCongelador;
	int tempoPreparo;
	Double custoMedio;
	String modoPreparo;
	String medidaTempoForno;
	String medidaTempoCongelador;
	String medidaTempoPreparo;
	Categoria categoria;
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	//Image Imagem;
	public Receita() {
		super();
		itens = new ArrayList<Item>();
	}
	
	public Receita(int id, String descricao, ArrayList<Item> itens,
			int qtdPessoasServe, int tempoForno, int tempoCongelador,
			int tempoPreparo, Double custoMedio, String modoPreparo,
			String medidaTempoForno, String medidaTempoCongelador,
			String medidaTempoPreparo, Categoria categoria) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.itens = itens;
		this.qtdPessoasServe = qtdPessoasServe;
		this.tempoForno = tempoForno;
		this.tempoCongelador = tempoCongelador;
		this.tempoPreparo = tempoPreparo;
		this.custoMedio = custoMedio;
		this.modoPreparo = modoPreparo;
		this.medidaTempoForno = medidaTempoForno;
		this.medidaTempoCongelador = medidaTempoCongelador;
		this.medidaTempoPreparo = medidaTempoPreparo;
		this.categoria = categoria;
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
	public int getTempoPreparo() {
		return tempoPreparo;
	}
	public void setTempoPreparo(int tempoPreparo) {
		this.tempoPreparo = tempoPreparo;
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
	public String getMedidaTempoForno() {
		return medidaTempoForno;
	}
	public void setMedidaTempoForno(String medidaTempoForno) {
		this.medidaTempoForno = medidaTempoForno;
	}
	public String getMedidaTempoCongelador() {
		return medidaTempoCongelador;
	}
	public void setMedidaTempoCongelador(String medidaTempoCongelador) {
		this.medidaTempoCongelador = medidaTempoCongelador;
	}
	public String getMedidaTempoPreparo() {
		return medidaTempoPreparo;
	}
	public void setMedidaTempoPreparo(String medidaTempoPreparo) {
		this.medidaTempoPreparo = medidaTempoPreparo;
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
