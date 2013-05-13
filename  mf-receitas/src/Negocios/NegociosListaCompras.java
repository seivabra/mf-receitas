package Negocios;

import java.util.ArrayList;

import ClassesBasicas.ListaCompras;
import Dao.DaoListaCompras;

public class NegociosListaCompras {
	DaoListaCompras daoListaCompras = new DaoListaCompras();
	
	public int InserirListaCompras(ListaCompras listaCompras){
		return daoListaCompras.InserirListaCompras(listaCompras);
	}
	
	public int AlterarListaCompras(ListaCompras listaCompras){
		return daoListaCompras.AlterarListaCompras(listaCompras);
	}
	
	public int ExcluirListaCompras(ListaCompras listaCompras){
		return daoListaCompras.ExcluirListaCompras(listaCompras);
	}
	
	public ArrayList<ListaCompras> ListarListaCompras(){
		return daoListaCompras.ListarListaCompras();
	}
	
	public ArrayList<ListaCompras> PesquisarListaComprasDescricao(String descricao){
		return daoListaCompras.PesquisarListaComprasDescricao(descricao);
	}
}
