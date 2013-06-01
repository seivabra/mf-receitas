package br.com.mf.mfreceitas;

import br.com.mf.mfreceitas.DetalheMarcaFragment.DetalheMarcaListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import ClassesBasicas.Marca;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

public class DetalheMarcaActivity extends SherlockFragmentActivity implements DetalheMarcaListener{
	Menu menu;
	DetalheMarcaFragment fragmentDetalhe;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Marca marca = (Marca)getIntent().getSerializableExtra("marca");
		fragmentDetalhe  = DetalheMarcaFragment.novoDetalhe(marca);
		FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
		trans.add(android.R.id.content, fragmentDetalhe, "detalhe");
		trans.commit();
		
		fragmentDetalhe.setDetalheListenerMarca(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.incluiralterarvoltar, menu);
		this.menu = menu;
		return super.onCreateOptionsMenu(menu);
	}
	
//	public void CarregaFragmentDetalhe(Marca marca){
//		fragmentDetalhe = DetalheMarcaFragment.novoDetalhe(marca);
//		fragmentDetalhe.setDetalheListenerMarca(this);
//    	FragmentTransaction trans = fm.beginTransaction();
//		trans.replace(R.id.fragmentDetalheMarcas, fragmentDetalhe, "detalhe");
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
	public void aoSalvarMarca(Marca marca) {
		// TODO Auto-generated method stub
		finish();
	}

	@Override
	public void aoCancelarMarca(Marca marcaAntiga) {
		//Ta dando erro
		finish();
	}


	

}
