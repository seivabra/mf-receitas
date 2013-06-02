package Negocios;

import java.util.ArrayList;
import android.content.Context;
import ClassesBasicas.Receita;
import Dao.DaoReceita;
import Excecoes.SemDescricaoException;
import Excecoes.SemIngredientesException;
import Excecoes.SemPassosException;

public class NegociosReceita {
	DaoReceita daoReceita;
	
	public NegociosReceita(Context context) {
		daoReceita = new DaoReceita(context);
	}
	
	public long InserirReceita(Receita receita) throws SemDescricaoException, SemIngredientesException, SemPassosException{
		if (receita.getDescricao().equals(""))
			throw new SemDescricaoException();
		if (receita.getItens().size() == 0)
			throw new SemIngredientesException();
		if (receita.getModoPreparo().equals(""))
			throw new SemPassosException();
		return daoReceita.InserirReceita(receita);
	}
	
	public int AlterarReceita(Receita receita){
		return daoReceita.AlterarReceita(receita);
	}
	
	public int ExcluirReceita(Receita receita){
		return daoReceita.ExcluirReceita(receita);
	}
	
	public ArrayList<Receita> ListarReceitas(){
		return daoReceita.ListarReceitas();
	}
	
	public ArrayList<Receita> PesquisarReceitaDescricao(String descricao){
		return daoReceita.PesquisarReceitaDescricao(descricao);
	}
}	
