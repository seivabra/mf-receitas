package Negocios;

import java.util.ArrayList;

import ClassesBasicas.Produto;
import Dao.DaoProduto;

public class NegociosProduto {
	DaoProduto daoProduto = new DaoProduto();
	
	public int InserirProduto(Produto listaCompras){
		return daoProduto.InserirProduto(listaCompras);
	}
	
	public int AlterarProduto(Produto listaCompras){
		return daoProduto.AlterarProduto(listaCompras);
	}
	
	public int ExcluirProduto(Produto listaCompras){
		return daoProduto.ExcluirProduto(listaCompras);
	}
	
	public ArrayList<Produto> ListarProdutos(){
		return daoProduto.ListarProdutos();
	}
	
	public ArrayList<Produto> PesquisarProdutoDescricao(String descricao){
		return daoProduto.PesquisarProdutoDescricao(descricao);
	}
}
