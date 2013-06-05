package br.com.mf.mfreceitas;

import java.util.ArrayList;

import ClassesBasicas.Unidade;
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

public class ListaUnidadeFragment extends ListFragment {
	ArrayList<Unidade> unidades;
	private CatergoriaListener listener;
	private Fachada fachada;
	DetalheUnidadeFragment detalheFragment;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		fachada = new Fachada(getActivity());
		ListarUnidades();
		registerForContextMenu(getListView()); 
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (listener != null){
			listener.aoClicarNaUnidade(unidades.get(position), position);
		}
	}

	public Unidade PrimeiraUnidadeLista(){
		Unidade primeiraUnidade = null;
		if ((unidades != null) && (unidades.size() > 0))
			 primeiraUnidade = unidades.get(0);
		
		return primeiraUnidade;
	}
	
	public Unidade achaUnidadePeloCodigo(Unidade unidade){
		//É mais rápido varrer um for ou procurar no banco pelo código?
		if (unidade.getId() == 0){
			if(unidades.size() > 0)
				unidade = unidades.get(0);
		}else{
			for (int i = 0; i < unidades.size(); i++) {
				if (unidades.get(i).getId() == unidade.getId()){
					unidade = unidades.get(i);
					break;
				}
			}
		}
		return unidade;
	}
	
	public void setCatergoriaListener(CatergoriaListener listener) {
		this.listener = listener;
	}
	
	public interface CatergoriaListener{
		void aoClicarNaUnidade(Unidade unidade, int position);
		void aoSelecionarAlterarUnidade(Unidade unidade);
		void aoExcluirUnidade();
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
		final Unidade unidade = (Unidade)getListView().getItemAtPosition(info.position);
		
		switch (item.getItemId()) {  
        case R.id.opAlterar:
        	if (listener != null){
    			listener.aoSelecionarAlterarUnidade(unidade);
    		}
        	break;
        case R.id.opExcluir:
        	if (listener != null){
        		ExcluirUnidade(unidade);
    			listener.aoExcluirUnidade();
    		}
        	break;
		}
		return super.onContextItemSelected(item);
	}
	
	public void ExcluirUnidade(final Unidade unidade){
		AlertDialog.Builder dialogDeletarItem = new AlertDialog.Builder(getActivity());
		    dialogDeletarItem.setTitle("Alerta");
		    dialogDeletarItem.setMessage("Deseja deletar esse item?");
		   //builder.setIcon(R.drawable.ic_tab_name_selected);
		    dialogDeletarItem.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					try {
						fachada.ExcluirUnidade(unidade);
						ListarUnidades();
					} catch (Exception e) {
						Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
					}
					
				}
					
			});
		    dialogDeletarItem.setNegativeButton("Não", null);
		    dialogDeletarItem.show();
	}
	
	public void ListarUnidades(){
		//Quando ta voltando sem nada ta voltando null e dando erro
		unidades = fachada.ListarUnidades();
		//unidades = new ArrayList<Unidade>();
		setListAdapter(new ArrayAdapter<Unidade>(getActivity(), android.R.layout.simple_list_item_1, unidades));
	}
	
	
}
