package br.com.mf.mfreceitas;

//import br.com.mf.mfreceitas.ListaReceitaFragment.ReceitaListener;
import br.com.mf.mfreceitas.ListaReceitaFragment.ReceitaListener;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import ClassesBasicas.Receita;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

public class ReceitaActivity extends SherlockFragmentActivity{
	private ListaReceitaFragment listaReceitaFragment;
	private DetalheReceitaFragment  fragmentDetalhe;
	private FragmentManager fm;
	Context context = this;
	Menu menu;
	Fachada fachada = new Fachada(context);
	Receita receitaSelecionada;
	
	
	public boolean mostraDetalhe(){
    	return findViewById(R.id.fragmentDetalheReceitas) != null;
    }
	
	public void CarregaFragmentDetalhe(Receita receita){
		fragmentDetalhe = DetalheReceitaFragment.novoDetalhe(receita);
		FragmentTransaction trans = fm.beginTransaction();
		trans.replace(R.id.fragmentDetalheReceitas, fragmentDetalhe, "detalhe");
		trans.commit();
    }
	
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receita);
        fm = getSupportFragmentManager();
        listaReceitaFragment = (ListaReceitaFragment)fm.findFragmentById(R.id.fragmentListaReceitas);
       
        listaReceitaFragment.setReceitaListener(new ReceitaListener() {
			
//			@Override
//			public void aoSelecionarAlterarReceita(Receita receita) {
//				if (mostraDetalhe()){
//					CarregaFragmentDetalhe(receita);
//				}else{
//					Intent intent = new Intent(context, DetalheReceitaActivity.class);
//					intent.putExtra("receita", receita);
//					startActivity(intent);
//				}
//				
//			}
//			
//			@Override
//			public void aoExcluirReceita() {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void aoClicarNaReceita(Receita receita, int position) {
//				// TODO Auto-generated method stub
//				
//			}

			@Override
			public void aoClicarNaReceita(Receita receita, int position) {
				if (mostraDetalhe()){
					CarregaFragmentDetalhe(receita);
					receitaSelecionada = receita;
				}else{
					Intent intent = new Intent(context, DetalheReceitaActivity.class);
					intent.putExtra("receita", receita);
					startActivity(intent);
				}
			}

			@Override
			public void aoExcluirReceita() {
				if (mostraDetalhe()){
					Receita receita = new Receita();
					receita.setId(0);
					CarregaFragmentDetalhe(receita);
				
				}
			}
		});
        
        //selecionar a primeira receita
//        if(mostraDetalhe()){
//        	Receita primeiraReceitaLista = listaReceitaFragment.PrimeiraReceitaLista();
//        	//não deu certo porque passa primeiro aqui e depois no create do fragment
//        	if(primeiraReceitaLista != null)
//        		CarregaFragmentDetalhe(primeiraReceitaLista);
//        	else
//        		CarregaFragmentDetalhe(new Receita(0, ""));
//        }
        
         
        
//        listaReceitaFragment.setCatergoriaListener(new CatergoriaListener() {
//			
//			@Override
//			public void aoClicarNaReceita(Receita receita, int position) {
//				DetalheReceitaFragment.habilitaCampos = false;
//				receitaSelecionada = receita;
//				if (mostraDetalhe()){
//					CarregaFragmentDetalhe(receita);
//				}else{
//					Intent intent = new Intent(context, DetalheReceitaActivity.class);
//					intent.putExtra("receita", receita);
//					startActivity(intent);
//				}
//			}
//
//			@Override
//			public void aoSelecionarAlterarReceita(Receita receita) {
//				DetalheReceitaFragment.habilitaCampos = true;
//				if (mostraDetalhe()){
//					DetalheReceitaFragment.habilitaCampos = true;
//					CarregaFragmentDetalhe(receita);
//					InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//			        mgr.toggleSoftInput (InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
//				}else{
//					Intent intent = new Intent(context, DetalheReceitaActivity.class);
//					intent.putExtra("receita", receita);
//					startActivity(intent);
//				}
//			}
//
//			@Override
//			public void aoExcluirReceita() {
//				if (mostraDetalhe()){
//					//selecionar a primeira receita
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
    	Intent intent = new Intent(this, IncluirAlterarReceitaActivity.class);
    	if (item.getItemId() == R.id.act_Novo)
    		startActivity(intent);
		else if (item.getItemId() == R.id.act_Alterar){
			if (receitaSelecionada != null){
				intent.putExtra("receita", receitaSelecionada);
				startActivity(intent);
			}else{
				Toast.makeText(getBaseContext(), R.string.msgEscolhaReceita, Toast.LENGTH_SHORT).show();
			}
		}	
    	else if (item.getItemId() == R.id.act_Excluir){
    		if (receitaSelecionada != null)
    			listaReceitaFragment.ExcluirReceita(receitaSelecionada);
    		else
    			Toast.makeText(getBaseContext(), R.string.msgEscolhaReceita, Toast.LENGTH_SHORT).show();
    	}
    	return super.onMenuItemSelected(featureId, item);
    }

	@Override
	protected void onRestart() {
		super.onRestart();
		listaReceitaFragment.ListarReceitas();
	}

	
}
