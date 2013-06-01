package br.com.mf.mfreceitas;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import br.com.mf.mfreceitas.DetalheUnidadeFragment.DetalheUnidadeListener;
import br.com.mf.mfreceitas.ListaUnidadeFragment.CatergoriaListener;
import ClassesBasicas.Unidade;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.inputmethod.InputMethodManager;

public class UnidadeActivity extends SherlockFragmentActivity implements DetalheUnidadeListener{
	private ListaUnidadeFragment listaUnidadeFragment;
	private DetalheUnidadeFragment  fragmentDetalhe;
	private FragmentManager fm;
	Context context = this;
	Menu menu;
	Fachada fachada = new Fachada(context);
	Unidade unidadeSelecionada;
	
	
	public boolean mostraDetalhe(){
    	return findViewById(R.id.fragmentDetalheUnidades) != null;
    }
	
	public void CarregaFragmentDetalhe(Unidade unidade){
		fragmentDetalhe = DetalheUnidadeFragment.novoDetalhe(unidade);
		fragmentDetalhe.setDetalheListenerUnidade(this);
    	FragmentTransaction trans = fm.beginTransaction();
		trans.replace(R.id.fragmentDetalheUnidades, fragmentDetalhe, "detalhe");
		trans.commit();
    }
	
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unidade);
        fm = getSupportFragmentManager();
        listaUnidadeFragment = (ListaUnidadeFragment)fm.findFragmentById(R.id.fragmentListaUnidades);
        
      //selecionar a primeira unidade
        if(mostraDetalhe()){
        	Unidade primeiraUnidadeLista = listaUnidadeFragment.PrimeiraUnidadeLista();
        	//não deu certo porque passa primeiro aqui e depois no create do fragment
        	if(primeiraUnidadeLista != null)
        		CarregaFragmentDetalhe(primeiraUnidadeLista);
        	else
        		CarregaFragmentDetalhe(new Unidade(0, ""));
        }
        
         
        
        listaUnidadeFragment.setCatergoriaListener(new CatergoriaListener() {
			
			@Override
			public void aoClicarNaUnidade(Unidade unidade, int position) {
				DetalheUnidadeFragment.habilitaCampos = false;
				unidadeSelecionada = unidade;
				if (mostraDetalhe()){
					CarregaFragmentDetalhe(unidade);
				}else{
					Intent intent = new Intent(context, DetalheUnidadeActivity.class);
					intent.putExtra("unidade", unidade);
					startActivity(intent);
				}
			}

			@Override
			public void aoSelecionarAlterarUnidade(Unidade unidade) {
				DetalheUnidadeFragment.habilitaCampos = true;
				if (mostraDetalhe()){
					DetalheUnidadeFragment.habilitaCampos = true;
					CarregaFragmentDetalhe(unidade);
					InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			        mgr.toggleSoftInput (InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
				}else{
					Intent intent = new Intent(context, DetalheUnidadeActivity.class);
					intent.putExtra("unidade", unidade);
					startActivity(intent);
				}
			}

			@Override
			public void aoExcluirUnidade() {
				if (mostraDetalhe()){
					//selecionar a primeira unidade
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
    		DetalheUnidadeFragment.habilitaCampos = true;
    		if (mostraDetalhe()){
	    		CarregaFragmentDetalhe(new Unidade(0, ""));
	    		InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	            mgr.toggleSoftInput (InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
	    	}else{
				Intent intent = new Intent(context, DetalheUnidadeActivity.class);
				intent.putExtra("unidade", new Unidade(0, ""));
				startActivity(intent);
				
			}
    	}else if (item.getItemId() == R.id.act_Alterar)
			fragmentDetalhe.habilitarCampos();
    	else if (item.getItemId() == R.id.act_Excluir)
    		listaUnidadeFragment.ExcluirUnidade(unidadeSelecionada);
    	return super.onMenuItemSelected(featureId, item);
    }

	@Override
	public void aoSalvarUnidade(Unidade unidade) {
		//Tem que saber se estorou exceção
		listaUnidadeFragment.ListarUnidades();
		fragmentDetalhe.DesabilitarCampos();
	}

	@Override
	public void aoCancelarUnidade(Unidade unidadeAntiga) {
		//Se cancelar um novo ele pega a ultima unidade selecionada... porque na hora de incluir eu não mando uma unidade zerada, só limpo o campo
		unidadeAntiga = listaUnidadeFragment.achaUnidadePeloCodigo(unidadeAntiga);
		fragmentDetalhe.PreencheCampos(unidadeAntiga);
		fragmentDetalhe.DesabilitarCampos();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		listaUnidadeFragment.ListarUnidades();
	}
}
