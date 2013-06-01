package br.com.mf.mfreceitas;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import br.com.mf.mfreceitas.DetalheProdutoFragment.DetalheProdutoListener;
import br.com.mf.mfreceitas.ListaProdutoFragment.CatergoriaListener;
import ClassesBasicas.Produto;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.inputmethod.InputMethodManager;

public class ProdutoActivity extends SherlockFragmentActivity implements DetalheProdutoListener{
	private ListaProdutoFragment listaProdutoFragment;
	private DetalheProdutoFragment  fragmentDetalhe;
	private FragmentManager fm;
	Context context = this;
	Menu menu;
	Fachada fachada = new Fachada(context);
	Produto produtoSelecionada;
	
	
	public boolean mostraDetalhe(){
    	return findViewById(R.id.fragmentDetalheProdutos) != null;
    }
	
	public void CarregaFragmentDetalhe(Produto produto){
		fragmentDetalhe = DetalheProdutoFragment.novoDetalhe(produto);
		fragmentDetalhe.setDetalheListenerProduto(this);
    	FragmentTransaction trans = fm.beginTransaction();
		trans.replace(R.id.fragmentDetalheProdutos, fragmentDetalhe, "detalhe");
		trans.commit();
    }
	
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.produto);
        fm = getSupportFragmentManager();
        listaProdutoFragment = (ListaProdutoFragment)fm.findFragmentById(R.id.fragmentListaProdutos);
        
      //selecionar a primeira produto
        if(mostraDetalhe()){
        	Produto primeiraProdutoLista = listaProdutoFragment.PrimeiraProdutoLista();
        	//não deu certo porque passa primeiro aqui e depois no create do fragment
        	if(primeiraProdutoLista != null)
        		CarregaFragmentDetalhe(primeiraProdutoLista);
        	else
        		CarregaFragmentDetalhe(new Produto(0, ""));
        }
        
         
        
        listaProdutoFragment.setCatergoriaListener(new CatergoriaListener() {
			
			@Override
			public void aoClicarNaProduto(Produto produto, int position) {
				DetalheProdutoFragment.habilitaCampos = false;
				produtoSelecionada = produto;
				if (mostraDetalhe()){
					CarregaFragmentDetalhe(produto);
				}else{
					Intent intent = new Intent(context, DetalheProdutoActivity.class);
					intent.putExtra("produto", produto);
					startActivity(intent);
				}
			}

			@Override
			public void aoSelecionarAlterarProduto(Produto produto) {
				DetalheProdutoFragment.habilitaCampos = true;
				if (mostraDetalhe()){
					DetalheProdutoFragment.habilitaCampos = true;
					CarregaFragmentDetalhe(produto);
					InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			        mgr.toggleSoftInput (InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
				}else{
					Intent intent = new Intent(context, DetalheProdutoActivity.class);
					intent.putExtra("produto", produto);
					startActivity(intent);
				}
			}

			@Override
			public void aoExcluirProduto() {
				if (mostraDetalhe()){
					//selecionar a primeira produto
				}
			}
		});
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	if(mostraDetalhe()){
	    	getSupportMenuInflater().inflate(R.menu.incluiralterarexcluir, menu);
			this.menu = menu;
		}else{
			getSupportMenuInflater().inflate(R.menu.incluir, menu);
			this.menu = menu;
		}
    	return super.onCreateOptionsMenu(menu);
    }
    

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
    	if (item.getItemId() == R.id.act_Novo){
    		DetalheProdutoFragment.habilitaCampos = true;
    		if (mostraDetalhe()){
	    		CarregaFragmentDetalhe(new Produto(0, ""));
	    		InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	            mgr.toggleSoftInput (InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
	    	}else{
				Intent intent = new Intent(context, DetalheProdutoActivity.class);
				intent.putExtra("produto", new Produto(0, ""));
				startActivity(intent);
				
			}
    	}else if (item.getItemId() == R.id.act_Alterar)
			fragmentDetalhe.habilitarCampos();
    	else if (item.getItemId() == R.id.act_Excluir)
    		listaProdutoFragment.ExcluirProduto(produtoSelecionada);
    	return super.onMenuItemSelected(featureId, item);
    }

	@Override
	public void aoSalvarProduto(Produto produto) {
		//Tem que saber se estorou exceção
		listaProdutoFragment.ListarProdutos();
		fragmentDetalhe.DesabilitarCampos();
	}

	@Override
	public void aoCancelarProduto(Produto produtoAntiga) {
		//Se cancelar um novo ele pega a ultima produto selecionada... porque na hora de incluir eu não mando uma produto zerada, só limpo o campo
		produtoAntiga = listaProdutoFragment.achaProdutoPeloCodigo(produtoAntiga);
		fragmentDetalhe.PreencheCampos(produtoAntiga);
		fragmentDetalhe.DesabilitarCampos();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		listaProdutoFragment.ListarProdutos();
	}
}
