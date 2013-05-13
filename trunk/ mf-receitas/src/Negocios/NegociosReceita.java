package Negocios;

import java.util.ArrayList;

import ClassesBasicas.Receita;
import Dao.DaoReceita;

public class NegociosReceita {
	DaoReceita daoReceita = new DaoReceita();
	
	public int InserirReceita(Receita listaCompras){
		return daoReceita.InserirReceita(listaCompras);
	}
	
	public int AlterarReceita(Receita listaCompras){
		return daoReceita.AlterarReceita(listaCompras);
	}
	
	public int ExcluirReceita(Receita listaCompras){
		return daoReceita.ExcluirReceita(listaCompras);
	}
	
	public ArrayList<Receita> ListarReceitas(){
		return daoReceita.ListarReceitas();
	}
	
	public ArrayList<Receita> PesquisarReceitaDescricao(String descricao){
		return daoReceita.PesquisarReceitaDescricao(descricao);
	}
}	
