package br.com.mf.mfreceitas;

import br.com.mf.mfreceitas.DetalheUnidadeFragment.DetalheUnidadeListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import ClassesBasicas.Unidade;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

public class DetalheUnidadeActivity extends SherlockFragmentActivity implements DetalheUnidadeListener{
	Menu menu;
	DetalheUnidadeFragment fragmentDetalhe;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Unidade unidade = (Unidade)getIntent().getSerializableExtra("unidade");
		fragmentDetalhe  = DetalheUnidadeFragment.novoDetalhe(unidade);
		FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
		trans.add(android.R.id.content, fragmentDetalhe, "detalhe");
		trans.commit();
		
		fragmentDetalhe.setDetalheListenerUnidade(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.incluiralterarvoltar, menu);
		this.menu = menu;
		return super.onCreateOptionsMenu(menu);
	}
	
//	public void CarregaFragmentDetalhe(Unidade unidade){
//		fragmentDetalhe = DetalheUnidadeFragment.novoDetalhe(unidade);
//		fragmentDetalhe.setDetalheListenerUnidade(this);
//    	FragmentTransaction trans = fm.beginTransaction();
//		trans.replace(R.id.fragmentDetalheUnidades, fragmentDetalhe, "detalhe");
//		trans.commit();
//    }
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		if(item.getItemId() == R.id.act_Novo){
			fragmentDetalhe.Incluir();
		}else if(item.getItemId() == R.id.act_Alterar){
			fragmentDetalhe.habilitarCampos();
		}else if(item.getItemId() == R.id.act_Voltar){
			finish();
		}
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public void aoSalvarUnidade(Unidade unidade) {
		// TODO Auto-generated method stub
		finish();
	}

	@Override
	public void aoCancelarUnidade(Unidade unidadeAntiga) {
		//Ta dando erro
		finish();
	}


	

}
