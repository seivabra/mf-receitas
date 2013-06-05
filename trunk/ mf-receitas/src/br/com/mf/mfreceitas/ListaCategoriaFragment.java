package br.com.mf.mfreceitas;

import java.util.ArrayList;

import ClassesBasicas.Categoria;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListaCategoriaFragment extends ListFragment {
	ArrayList<Categoria> categorias;
	private CatergoriaListener listener;
	private Fachada fachada;
	DetalheCategoriaFragment detalheFragment;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		fachada = new Fachada(getActivity());
		ListarCategorias();
		registerForContextMenu(getListView()); 
		
		//fazer selecionar o primeiro item quando mostrardetalhes
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (listener != null){
			listener.aoClicarNaCategoria(categorias.get(position), position);
		}
	}

	public Categoria PrimeiraCategoriaLista(){
		Categoria primeiraCategoria = null;
		if ((categorias != null) && (categorias.size() > 0))
			 primeiraCategoria = categorias.get(0);
		
		return primeiraCategoria;
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
	
	public void setCatergoriaListener(CatergoriaListener listener) {
		this.listener = listener;
	}
	
	public interface CatergoriaListener{
		void aoClicarNaCategoria(Categoria categoria, int position);
		void aoSelecionarAlterarCategoria(Categoria categoria);
		void aoExcluirCategoria();
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		getActivity().getMenuInflater().inflate(R.menu.context_menu_alterar_excluir, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();  
		final Categoria categoria = (Categoria)getListView().getItemAtPosition(info.position);
		
		switch (item.getItemId()) {  
        case R.id.opAlterar:
        	if (listener != null){
    			listener.aoSelecionarAlterarCategoria(categoria);
    		}
        	break;
        case R.id.opExcluir:
        	if (listener != null){
        		ExcluirCategoria(categoria);
    			listener.aoExcluirCategoria();
    		}
        	break;
		}
		return super.onContextItemSelected(item);
	}
	
	public void ExcluirCategoria(final Categoria categoria){
		AlertDialog.Builder dialogDeletarItem = new AlertDialog.Builder(getActivity());
		    dialogDeletarItem.setTitle("Alerta");
		    dialogDeletarItem.setMessage("Deseja deletar esse item?");
		   //builder.setIcon(R.drawable.ic_tab_name_selected);
		    dialogDeletarItem.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					try {
						fachada.ExcluirCategoria(categoria);
						ListarCategorias();
					} catch (Exception e) {
						Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
					}
					
				}
					
			});
		    dialogDeletarItem.setNegativeButton("Não", null);
		    dialogDeletarItem.show();
	}
	
	public void ListarCategorias(){
		//Quando ta voltando sem nada ta voltando null e dando erro
		categorias = fachada.ListarCategorias();
		//categorias = new ArrayList<Categoria>();
		setListAdapter(new ArrayAdapter<Categoria>(getActivity(), android.R.layout.simple_list_item_1, categorias));
	}
	
	
}
