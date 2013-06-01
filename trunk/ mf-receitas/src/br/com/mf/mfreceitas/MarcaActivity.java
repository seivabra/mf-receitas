package br.com.mf.mfreceitas;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import br.com.mf.mfreceitas.DetalheMarcaFragment.DetalheMarcaListener;
import ClassesBasicas.Marca;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.inputmethod.InputMethodManager;

public class MarcaActivity extends SherlockFragmentActivity implements DetalheMarcaListener{
	private ListaMarcaFragment listaMarcaFragment;
	private DetalheMarcaFragment  fragmentDetalhe;
	private FragmentManager fm;
	Context context = this;
	Menu menu;
	Fachada fachada = new Fachada(context);
	Marca marcaSelecionada;
	
	
	public boolean mostraDetalhe(){
    	return findViewById(R.id.fragmentDetalheMarcas) != null;
    }
	
	public void CarregaFragmentDetalhe(Marca marca){
		fragmentDetalhe = DetalheMarcaFragment.novoDetalhe(marca);
		fragmentDetalhe.setDetalheListenerMarca(this);
    	FragmentTransaction trans = fm.beginTransaction();
		trans.replace(R.id.fragmentDetalheMarcas, fragmentDetalhe, "detalhe");
		trans.commit();
    }
	
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marca);
        fm = getSupportFragmentManager();
        listaMarcaFragment = (ListaMarcaFragment)fm.findFragmentById(R.id.fragmentListaMarcas);
        
      //selecionar a primeira marca
        if(mostraDetalhe()){
        	Marca primeiraMarcaLista = listaMarcaFragment.PrimeiraMarcaLista();
        	//não deu certo porque passa primeiro aqui e depois no create do fragment
        	if(primeiraMarcaLista != null)
        		CarregaFragmentDetalhe(primeiraMarcaLista);
        	else
        		CarregaFragmentDetalhe(new Marca(0, ""));
        }
        
        
//        
//        listaMarcaFragment.setMarcaListener(new MarcaListener() {
//			
//			@Override
//			public void aoClicarNaMarca(Marca marca, int position) {
//				DetalheMarcaFragment.habilitaCampos = false;
//				marcaSelecionada = marca;
//				if (mostraDetalhe()){
//					CarregaFragmentDetalhe(marca);
//				}else{
//					Intent intent = new Intent(context, DetalheMarcaActivity.class);
//					intent.putExtra("marca", marca);
//					startActivity(intent);
//				}
//			}
//
//			@Override
//			public void aoSelecionarAlterarMarca(Marca marca) {
//				DetalheMarcaFragment.habilitaCampos = true;
//				if (mostraDetalhe()){
//					DetalheMarcaFragment.habilitaCampos = true;
//					CarregaFragmentDetalhe(marca);
//					InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//			        mgr.toggleSoftInput (InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
//				}else{
//					Intent intent = new Intent(context, DetalheMarcaActivity.class);
//					intent.putExtra("marca", marca);
//					startActivity(intent);
//				}
//			}
//
//			@Override
//			public void aoExcluirMarca() {
//				if (mostraDetalhe()){
//					//selecionar a primeira marca
//				}
//			}
//		});
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
    		DetalheMarcaFragment.habilitaCampos = true;
    		if (mostraDetalhe()){
	    		CarregaFragmentDetalhe(new Marca(0, ""));
	    		InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	            mgr.toggleSoftInput (InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
	    	}else{
				Intent intent = new Intent(context, DetalheMarcaActivity.class);
				intent.putExtra("marca", new Marca(0, ""));
				startActivity(intent);
				
			}
    	}else if (item.getItemId() == R.id.act_Alterar)
			fragmentDetalhe.habilitarCampos();
    	else if (item.getItemId() == R.id.act_Excluir)
    		listaMarcaFragment.ExcluirMarca(marcaSelecionada);
    	return super.onMenuItemSelected(featureId, item);
    }

	@Override
	public void aoSalvarMarca(Marca marca) {
		//Tem que saber se estorou exceção
		listaMarcaFragment.ListarMarcas();
		fragmentDetalhe.DesabilitarCampos();
	}

	@Override
	public void aoCancelarMarca(Marca marcaAntiga) {
		//Se cancelar um novo ele pega a ultima marca selecionada... porque na hora de incluir eu não mando uma marca zerada, só limpo o campo
		marcaAntiga = listaMarcaFragment.achaMarcaPeloCodigo(marcaAntiga);
		fragmentDetalhe.PreencheCampos(marcaAntiga);
		fragmentDetalhe.DesabilitarCampos();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		listaMarcaFragment.ListarMarcas();
	}
}
