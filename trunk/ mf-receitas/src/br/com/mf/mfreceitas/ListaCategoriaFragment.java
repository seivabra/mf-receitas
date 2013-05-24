package br.com.mf.mfreceitas;

import java.util.ArrayList;

import com.actionbarsherlock.internal.widget.IcsAdapterView.AdapterContextMenuInfo;
import ClassesBasicas.Categoria;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListaCategoriaFragment extends ListFragment {
	ArrayList<Categoria> categorias;
	private ListenerCatergoria listener;
	private Fachada fachada;
	DetalheCategoriaFragment detalheFragment;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		fachada = new Fachada(getActivity());
		ListarCategorias();
		registerForContextMenu(getListView()); 
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
	
//	@Override
//	public void onCreateContextMenu(ContextMenu menu, View v,
//			ContextMenuInfo menuInfo) {
//		getActivity().getMenuInflater().inflate(R.menu.context_menu_alterar_excluir, menu);
//		super.onCreateContextMenu(menu, v, menuInfo);
//	}
//	
//	@Override
//	public boolean onContextItemSelected(MenuItem item) {
//		
//		AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();  
//		Categoria categoria = (Categoria)getListView().getItemAtPosition(info.position);
//		
//		switch (item.getItemId()) {  
//        case R.id.opAlterar:
//        	detalheFragment  = DetalheCategoriaFragment.novoDetalhe(categoria);
//        case R.id.opExcluir:
//			//fachada.ExcluirCategoria(categoria);
//        	Toast.makeText(getActivity(), "Excuikjsdafjah", Toast.LENGTH_SHORT).show();
//		}
//		return super.onContextItemSelected(item);
//	}
	
	public void ListarCategorias(){
		//Quando ta voltando sem nada ta voltando null e dando erro
		categorias = fachada.ListarCategorias();
		//categorias = new ArrayList<Categoria>();
		setListAdapter(new ArrayAdapter<Categoria>(getActivity(), android.R.layout.simple_list_item_1, categorias));
	}
}
