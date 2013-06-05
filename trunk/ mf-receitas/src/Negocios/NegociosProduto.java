package Negocios;

import java.util.ArrayList;
import android.content.Context;
import ClassesBasicas.Produto;
import Dao.DaoProduto;

public class NegociosProduto {
	
	DaoProduto daoProduto;
	
	public NegociosProduto(Context context) {
		daoProduto = new DaoProduto(context);
	}
	
	public long InserirProduto(Produto listaCompras){
		return daoProduto.InserirProduto(listaCompras);
	}
	
	public int AlterarProduto(Produto listaCompras){
		return daoProduto.AlterarProduto(listaCompras);
	}
	
	public int ExcluirProduto(Produto produto) throws Exception{
		if (daoProduto.UsaEmAlgumaReceita(produto))
			throw new Exception("Esse produto está sendo usado em uma receita.");
		else
			return daoProduto.ExcluirProduto(produto);
	}
	
	public ArrayList<Produto> ListarProdutos(){
		return daoProduto.ListarProdutos();
	}
	
	public ArrayList<Produto> PesquisarProdutoDescricao(String descricao){
		return daoProduto.PesquisarProdutoDescricao(descricao);
	}
	
	public Boolean AchouProdutoIgual(Produto produto){
		return daoProduto.AchouProdutoIgual(produto);
	}
}
