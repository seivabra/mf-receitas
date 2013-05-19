package br.com.mf.mfreceitas;

import java.util.ArrayList;

import ClassesBasicas.Categoria;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaCategoriaFragment extends ListFragment {
	ArrayList<Categoria> categorias;
	private ListenerCatergoria listener;
	private Fachada fachada;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		fachada = new Fachada(getActivity());
		ListarCategorias();
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (listener != null){
			listener.aoClicarNaCategoria(categorias.get(position), position);
		}
	}
	
	public Categoria achaCategoriaPeloCodigo(Categoria categoria){
		//É mais rápido varrer um for ou procurar no banco pelo código?
		if (categoria.getId() == 0){
			if(categorias.size() > 0)
				categoria = categorias.get(0);
		}else{
			for (int i = 0; i < categorias.size(); i++) {
				if (categorias.get(i).getId() == categoria.getId()){
					categoria = categorias.get(i);
					break;
				}
			}
		}
		return categoria;
	}
	
	public void setListener(ListenerCatergoria listener) {
		this.listener = listener;
	}
	
	public interface ListenerCatergoria{
		void aoClicarNaCategoria(Categoria categoria, int position);
	}
	
	public void ListarCategorias(){
		//Quando ta voltando sem nada ta voltando null e dando erro
		categorias = fachada.ListarCategorias();
		//categorias = new ArrayList<Categoria>();
		setListAdapter(new ArrayAdapter<Categoria>(getActivity(), android.R.layout.simple_list_item_1, categorias));
	}
}
