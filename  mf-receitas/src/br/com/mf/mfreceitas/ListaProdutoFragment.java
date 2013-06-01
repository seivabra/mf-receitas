package br.com.mf.mfreceitas;

import java.util.ArrayList;

import ClassesBasicas.Produto;
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

public class ListaProdutoFragment extends ListFragment {
	ArrayList<Produto> produtos;
	private CatergoriaListener listener;
	private Fachada fachada;
	DetalheProdutoFragment detalheFragment;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		fachada = new Fachada(getActivity());
		ListarProdutos();
		registerForContextMenu(getListView()); 
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (listener != null){
			listener.aoClicarNaProduto(produtos.get(position), position);
		}
	}

	public Produto PrimeiraProdutoLista(){
		Produto primeiraProduto = null;
		if ((produtos != null) && (produtos.size() > 0))
			 primeiraProduto = produtos.get(0);
		
		return primeiraProduto;
	}
	
	public Produto achaProdutoPeloCodigo(Produto produto){
		//É mais rápido varrer um for ou procurar no banco pelo código?
		if (produto.getId() == 0){
			if(produtos.size() > 0)
				produto = produtos.get(0);
		}else{
			for (int i = 0; i < produtos.size(); i++) {
				if (produtos.get(i).getId() == produto.getId()){
					produto = produtos.get(i);
					break;
				}
			}
		}
		return produto;
	}
	
	public void setCatergoriaListener(CatergoriaListener listener) {
		this.listener = listener;
	}
	
	public interface CatergoriaListener{
		void aoClicarNaProduto(Produto produto, int position);
		void aoSelecionarAlterarProduto(Produto produto);
		void aoExcluirProduto();
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
		final Produto produto = (Produto)getListView().getItemAtPosition(info.position);
		
		switch (item.getItemId()) {  
        case R.id.opAlterar:
        	if (listener != null){
    			listener.aoSelecionarAlterarProduto(produto);
    		}
        	break;
        case R.id.opExcluir:
        	if (listener != null){
        		ExcluirProduto(produto);
    			listener.aoExcluirProduto();
    		}
        	break;
		}
		return super.onContextItemSelected(item);
	}
	
	public void ExcluirProduto(final Produto produto){
		AlertDialog.Builder dialogDeletarItem = new AlertDialog.Builder(getActivity());
		    dialogDeletarItem.setTitle("Alerta");
		    dialogDeletarItem.setMessage("Deseja deletar esse item?");
		   //builder.setIcon(R.drawable.ic_tab_name_selected);
		    dialogDeletarItem.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// fazer pesquisar se a produto é usada em alguma receita
					fachada.ExcluirProduto(produto);
					ListarProdutos();
				}
					
			});
		    dialogDeletarItem.setNegativeButton("Não", null);
		    dialogDeletarItem.show();
	}
	
	public void ListarProdutos(){
		//Quando ta voltando sem nada ta voltando null e dando erro
		produtos = fachada.ListarProdutos();
		//produtos = new ArrayList<Produto>();
		setListAdapter(new ArrayAdapter<Produto>(getActivity(), android.R.layout.simple_list_item_1, produtos));
	}
	
	
}
