package br.com.mf.mfreceitas;

import java.util.ArrayList;
import ClassesBasicas.Receita;
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

public class ListaReceitaFragment extends ListFragment {
	ArrayList<Receita> receitas;
	private ReceitaListener listener;
	private Fachada fachada;
	DetalheReceitaFragment detalheFragment;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		fachada = new Fachada(getActivity());
		ListarReceitas();
		registerForContextMenu(getListView()); 
		
		//fazer selecionar o primeiro item quando mostrardetalhes
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (listener != null){
			listener.aoClicarNaReceita(receitas.get(position), position);
		}
	}
//
//	public Receita PrimeiraReceitaLista(){
//		Receita primeiraReceita = null;
//		if ((receitas != null) && (receitas.size() > 0))
//			 primeiraReceita = receitas.get(0);
//		
//		return primeiraReceita;
//	}
//	
//	public Receita achaReceitaPeloCodigo(Receita receita){
//		//É mais rápido varrer um for ou procurar no banco pelo código?
//		if (receita.getId() == 0){
//			if(receitas.size() > 0)
//				receita = receitas.get(0);
//		}else{
//			for (int i = 0; i < receitas.size(); i++) {
//				if (receitas.get(i).getId() == receita.getId()){
//					receita = receitas.get(i);
//					break;
//				}
//			}
//		}
//		return receita;
//	}
//	
	public void setReceitaListener(ReceitaListener listener) {
		this.listener = listener;
	}
	
	public interface ReceitaListener{
		void aoClicarNaReceita(Receita receita, int position);
//		void aoSelecionarAlterarReceita(Receita receita);
		void aoExcluirReceita();
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		getActivity().getMenuInflater().inflate(R.menu.context_menu_alterar_excluir_enviar, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();  
		final Receita receita = (Receita)getListView().getItemAtPosition(info.position);
		
		switch (item.getItemId()) {  
        case R.id.opAlterar:
        	if (listener != null){
    			//listener.aoSelecionarAlterarReceita(receita);
    		}
        	break;
        case R.id.opExcluir:
        	if (listener != null){
        		ExcluirReceita(receita);
    			listener.aoExcluirReceita();
    		}
        	break;
        case R.id.opEnviar:
	    	if (listener != null){
	    		ReceitasAsyncTask async = new ReceitasAsyncTask(getActivity());
	    		async.execute(receita);
    		}
	    	break;

		}
		return super.onContextItemSelected(item);
	}
	
	public void ExcluirReceita(final Receita receita){
		AlertDialog.Builder dialogDeletarItem = new AlertDialog.Builder(getActivity());
		    dialogDeletarItem.setTitle("Alerta");
		    dialogDeletarItem.setMessage("Deseja deletar esse item?");
		   //builder.setIcon(R.drawable.ic_tab_name_selected);
		    dialogDeletarItem.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// fazer pesquisar se a receita é usada em alguma receita
					fachada.ExcluirReceita(receita);
					ListarReceitas();
				}
					
			});
		    dialogDeletarItem.setNegativeButton("Não", null);
		    dialogDeletarItem.show();
	}
//	
	public void ListarReceitas(){
		//Quando ta voltando sem nada ta voltando null e dando erro
		receitas = fachada.ListarReceitas();
		//setListAdapter(new ArrayAdapter<Receita>(getActivity(), android.R.layout.simple_list_item_1, receitas));
		setListAdapter(new AdapterReceita(getActivity(),receitas));
	}
	
	
}
