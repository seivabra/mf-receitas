package Negocios;

import java.util.ArrayList;

import android.content.Context;

import ClassesBasicas.Marca;
import Dao.DaoMarca;

public class NegociosMarca {
	DaoMarca daoMarca;
	
	public NegociosMarca(Context context) {
		daoMarca = new DaoMarca(context);
	}
	
	public long InserirMarca(Marca marca){
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
	
	public Boolean AchouMarcaIgual(Marca marca){
		return daoMarca.AchouMarcaIgual(marca);
	}
}
