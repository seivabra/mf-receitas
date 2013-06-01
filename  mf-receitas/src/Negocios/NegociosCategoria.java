package Negocios;

import java.util.ArrayList;
import android.content.Context;
import ClassesBasicas.Categoria;
import Dao.DaoCategoria;

public class NegociosCategoria {
	
	DaoCategoria daoCategoria;
	
	public NegociosCategoria(Context context) {
		daoCategoria = new DaoCategoria(context);
	}
	
	public long InserirCategoria(Categoria categoria) throws Exception{
		if (categoria.getDescricao().equals(""))
			throw new Exception("Preencha a descrição da categoria");
		return daoCategoria.InserirCategoria(categoria);
	}
	
	public long AlterarCategoria(Categoria categoria){
		return daoCategoria.AlterarCategoria(categoria);
	}
	
	public int ExcluirCategoria(Categoria categoria){
		//Verificar se a categoria está sendo usada em alguma receita
		return daoCategoria.ExcluirCategoria(categoria);
	}
	
	public ArrayList<Categoria> ListarCategorias(){
		return daoCategoria.ListarCategorias();
	}
	
	public ArrayList<Categoria> PesquisarCategoriaDescricao(String descricao){
		return daoCategoria.PesquisarCategoriaDescricao(descricao);
	}
	public Boolean AchouCategoriaIgual(Categoria categoria){
		return daoCategoria.AchouCategoriaIgual(categoria);
	}
}
