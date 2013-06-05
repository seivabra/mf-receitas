package br.com.mf.mfreceitas;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import ClassesBasicas.Receita;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

public class DetalheReceitaActivity extends SherlockFragmentActivity{
	Menu menu;
	DetalheReceitaFragment fragmentDetalhe;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Receita receita = (Receita)getIntent().getSerializableExtra("receita");
		fragmentDetalhe  = DetalheReceitaFragment.novoDetalhe(receita);
		FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
		trans.add(android.R.id.content, fragmentDetalhe, "detalhe");
		trans.commit();
		
		//fragmentDetalhe.setDetalheListenerReceita(this);
	}
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		getSupportMenuInflater().inflate(R.menu.incluiralterarvoltar, menu);
//		this.menu = menu;
//		return super.onCreateOptionsMenu(menu);
//	}
	
//	public void CarregaFragmentDetalhe(Receita receita){
//		fragmentDetalhe = DetalheReceitaFragment.novoDetalhe(receita);
//		fragmentDetalhe.setDetalheListenerReceita(this);
//    	FragmentTransaction trans = fm.beginTransaction();
//		trans.replace(R.id.fragmentDetalheReceitas, fragmentDetalhe, "detalhe");
//		trans.commit();
//    }
	
//	@Override
//	public boolean onMenuItemSelected(int featureId, MenuItem item) {
//		if(item.getItemId() == R.id.act_Novo){
//			fragmentDetalhe.Incluir();
//		}else if(item.getItemId() == R.id.act_Alterar){
//			fragmentDetalhe.habilitarCampos();
//		}else if(item.getItemId() == R.id.act_Voltar){
//			finish();
//		}
//		return super.onMenuItemSelected(featureId, item);
//	}
//
//	@Override
//	public void aoSalvarReceita(Receita receita) {
//		// TODO Auto-generated method stub
//		finish();
//	}
//
//	@Override
//	public void aoCancelarReceita(Receita receitaAntiga) {
//		//Ta dando erro
//		finish();
//	}


	

}
