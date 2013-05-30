package br.com.mf.mfreceitas;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import br.com.mf.mfreceitas.DetalheCategoriaFragment.DetalheCategoriaListener;
import br.com.mf.mfreceitas.ListaCategoriaFragment.CatergoriaListener;
import ClassesBasicas.Categoria;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.inputmethod.InputMethodManager;

public class CategoriaActivity extends SherlockFragmentActivity implements DetalheCategoriaListener{
	private ListaCategoriaFragment listaCategoriaFragment;
	private DetalheCategoriaFragment  fragmentDetalhe;
	private FragmentManager fm;
	Context context = this;
	Menu menu;
	Fachada fachada = new Fachada(context);
	
	
	public boolean mostraDetalhe(){
    	return findViewById(R.id.fragmentDetalheCategorias) != null;
    }
	
	public void CarregaFragmentDetalhe(Categoria categoria){
		fragmentDetalhe = DetalheCategoriaFragment.novoDetalhe(categoria);
		fragmentDetalhe.setDetalheListenerCategoria(this);
    	FragmentTransaction trans = fm.beginTransaction();
		trans.replace(R.id.fragmentDetalheCategorias, fragmentDetalhe, "detalhe");
		trans.commit();
    }
	
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categoria);
        fm = getSupportFragmentManager();
        listaCategoriaFragment = (ListaCategoriaFragment)fm.findFragmentById(R.id.fragmentListaCategorias);
        
        if(mostraDetalhe()){
        	CarregaFragmentDetalhe(new Categoria(0, ""));
        }
        
         
        
        listaCategoriaFragment.setCatergoriaListener(new CatergoriaListener() {
			
			@Override
			public void aoClicarNaCategoria(Categoria categoria, int position) {
				DetalheCategoriaFragment.habilitaCampos = false;
				if (mostraDetalhe()){
					CarregaFragmentDetalhe(categoria);
				}else{
					Intent intent = new Intent(context, DetalheCategoriaActivity.class);
					intent.putExtra("categoria", categoria);
					startActivity(intent);
				}
			}

			@Override
			public void aoSelecionarAlterarCategoria(Categoria categoria) {
				DetalheCategoriaFragment.habilitaCampos = true;
				if (mostraDetalhe()){
					DetalheCategoriaFragment.habilitaCampos = true;
					CarregaFragmentDetalhe(categoria);
					InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			        mgr.toggleSoftInput (InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
				}else{
					Intent intent = new Intent(context, DetalheCategoriaActivity.class);
					intent.putExtra("categoria", categoria);
					startActivity(intent);
				}
			}

			@Override
			public void aoSelecionarExcluirCategoria(Categoria categoria) {

			}
		});
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	//if(mostraDetalhe()){
	    	getSupportMenuInflater().inflate(R.menu.incluir, menu);
			this.menu = menu;
		//}else{
			//getSupportMenuInflater().inflate(R.menu.incluiralterar, menu);
			//this.menu = menu;
		//}
    	return super.onCreateOptionsMenu(menu);
    }
    

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
    	if (item.getItemId() == R.id.act_Novo){
    		DetalheCategoriaFragment.habilitaCampos = true;
    		if (mostraDetalhe()){
	    		CarregaFragmentDetalhe(new Categoria(0, ""));
	    		InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	            mgr.toggleSoftInput (InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
	    	}else{
				Intent intent = new Intent(context, DetalheCategoriaActivity.class);
				intent.putExtra("categoria", new Categoria(0, ""));
				startActivity(intent);
				
			}
    	}//else if (item.getItemId() == R.id.act_Alterar)
//			fragmentDetalhe.habilitarCampos();
    	return super.onMenuItemSelected(featureId, item);
    }

	@Override
	public void aoSalvarCategoria(Categoria categoria) {
		//Tem que saber se estorou exceção
		listaCategoriaFragment.ListarCategorias();
		fragmentDetalhe.DesabilitarCampos();
	}

	@Override
	public void aoCancelarCategoria(Categoria categoriaAntiga) {
		//Se cancelar um novo ele pega a ultima categoria selecionada... porque na hora de incluir eu não mando uma categoria zerada, só limpo o campo
		categoriaAntiga = listaCategoriaFragment.achaCategoriaPeloCodigo(categoriaAntiga);
		fragmentDetalhe.PreencheCampos(categoriaAntiga);
		fragmentDetalhe.DesabilitarCampos();
	}

	
}
