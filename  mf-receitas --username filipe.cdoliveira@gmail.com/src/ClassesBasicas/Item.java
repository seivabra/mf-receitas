package ClassesBasicas;

public class Item {
	int id;
	Produto produto;
	Unidade unidade;
	Marca marca;
	int tamanho;
	int quantidade;
	public Item() {
		super();
	}
	public Item(int id, Produto produto, Unidade unidade, Marca marca,
			int tamanho, int quantidade) {
		super();
		this.id = id;
		this.produto = produto;
		this.unidade = unidade;
		this.marca = marca;
		this.tamanho = tamanho;
		this.quantidade = quantidade;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public int getTamanho() {
		return tamanho;
	}
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
