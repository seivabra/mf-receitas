package br.com.mf.mfreceitas;

import java.util.ArrayList;

import ClassesBasicas.Marca;
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

public class ListaMarcaFragment extends ListFragment {
	ArrayList<Marca> marcas;
	private MarcaListener listener;
	private Fachada fachada;
	DetalheCategoriaFragment detalheFragment;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		fachada = new Fachada(getActivity());
		ListarMarcas();
		registerForContextMenu(getListView()); 
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (listener != null){
			listener.aoClicarNaMarca(marcas.get(position), position);
		}
	}

	public Marca PrimeiraMarcaLista(){
		Marca primeiraMarca = null;
		if ((marcas != null) && (marcas.size() > 0))
			primeiraMarca = marcas.get(0);
		
		return primeiraMarca;
	}
	
	public Marca achaMarcaPeloCodigo(Marca marca){
		//É mais rápido varrer um for ou procurar no banco pelo código?
		if (marca.getId() == 0){
			if(marcas.size() > 0)
				marca = marcas.get(0);
		}else{
			for (int i = 0; i < marcas.size(); i++) {
				if (marcas.get(i).getId() == marca.getId()){
					marca = marcas.get(i);
					break;
				}
			}
		}
		return marca;
	}
	
	public void setMarcaListener(MarcaListener marcaListener) {
		this.listener = marcaListener;
	}
	
	public interface MarcaListener{
		void aoClicarNaMarca(Marca marca, int position);
		void aoSelecionarAlterarMarca(Marca marca);
		void aoExcluirMarca();
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
		final Marca marca = (Marca)getListView().getItemAtPosition(info.position);
		
		switch (item.getItemId()) {  
        case R.id.opAlterar:
        	if (listener != null){
    			listener.aoSelecionarAlterarMarca(marca);
    		}
        	break;
        case R.id.opExcluir:
        	if (listener != null){
        		ExcluirMarca(marca);
    			listener.aoExcluirMarca();
    		}
        	break;
		}
		return super.onContextItemSelected(item);
	}
	
	public void ExcluirMarca(final Marca marca){
		AlertDialog.Builder dialogDeletarMarca = new AlertDialog.Builder(getActivity());
		dialogDeletarMarca.setTitle("Alerta");
		dialogDeletarMarca.setMessage("Deseja deletar esse item?");
		   //builder.setIcon(R.drawable.ic_tab_name_selected);
		dialogDeletarMarca.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// fazer pesquisar se a categoria é usada em alguma receita
				fachada.ExcluirMarca(marca);
				ListarMarcas();
			}
				
		});
		dialogDeletarMarca.setNegativeButton("Não", null);
		dialogDeletarMarca.show();
	}
	
	public void ListarMarcas(){
		//Quando ta voltando sem nada ta voltando null e dando erro
		marcas = fachada.ListarMarcas();
		//categorias = new ArrayList<Categoria>();
		setListAdapter(new ArrayAdapter<Marca>(getActivity(), android.R.layout.simple_list_item_1, marcas));
	}
	
	
}
