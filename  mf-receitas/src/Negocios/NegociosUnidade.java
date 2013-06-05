package Negocios;

import java.util.ArrayList;
import android.content.Context;
import ClassesBasicas.Unidade;
import Dao.DaoUnidade;

public class NegociosUnidade {
	
	DaoUnidade daoUnidade;
	
	public NegociosUnidade(Context context) {
		daoUnidade = new DaoUnidade(context);
	}
	
	public long InserirUnidade(Unidade unidade){
		return daoUnidade.InserirUnidade(unidade);
	}
	
	public int AlterarUnidade(Unidade unidade){
		return daoUnidade.AlterarUnidade(unidade);
	}
	
	public int ExcluirUnidade(Unidade unidade) throws Exception{
		if (daoUnidade.UsaEmAlgumaReceita(unidade))
			throw new Exception("Essa unidade está sendo usada em uma receita.");
		else
			return daoUnidade.ExcluirUnidade(unidade);
	}
	
	public ArrayList<Unidade> ListarUnidades(){
		return daoUnidade.ListarUnidades();
	}
	
	public ArrayList<Unidade> PesquisarUnidadeDescricao(String descricao){
		return daoUnidade.PesquisarUnidadeDescricao(descricao);
	}
	
	public Boolean AchouUnidadeIgual(Unidade unidade){
		return daoUnidade.AchouUnidadeIgual(unidade);
	}
}
