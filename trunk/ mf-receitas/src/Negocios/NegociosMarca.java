package Negocios;

import java.util.ArrayList;

import ClassesBasicas.Marca;
import Dao.DaoMarca;

public class NegociosMarca {
	DaoMarca daoMarca = new DaoMarca();
	
	public int InserirMarca(Marca marca){
		return daoMarca.InserirMarca(marca);
	}
	
	public int AlterarMarca(Marca marca){
		return daoMarca.AlterarMarca(marca);
	}
	
	public int ExcluirMarca(Marca marca){
		return daoMarca.ExcluirMarca(marca);
	}
	
	public ArrayList<Marca> ListarMarcas(){
		return daoMarca.ListarMarcas();
	}
	
	public ArrayList<Marca> PesquisarMarcaDescricao(String descricao){
		return daoMarca.PesquisarMarcaDescricao(descricao);
	}
}
