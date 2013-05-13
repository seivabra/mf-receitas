package Negocios;

import java.util.ArrayList;

import ClassesBasicas.Unidade;
import Dao.DaoUnidade;

public class NegociosUnidade {
	DaoUnidade daoUnidade = new DaoUnidade();
	
	public int InserirUnidade(Unidade unidade){
		return daoUnidade.InserirUnidade(unidade);
	}
	
	public int AlterarUnidade(Unidade unidade){
		return daoUnidade.AlterarUnidade(unidade);
	}
	
	public int ExcluirUnidade(Unidade unidade){
		return daoUnidade.ExcluirUnidade(unidade);
	}
	
	public ArrayList<Unidade> ListarUnidades(){
		return daoUnidade.ListarUnidades();
	}
	
	public ArrayList<Unidade> PesquisarUnidadeDescricao(String descricao){
		return daoUnidade.PesquisarUnidadeDescricao(descricao);
	}
}
