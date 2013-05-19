package br.com.mf.mfreceitas;

import br.com.mf.mfreceitas.DetalheCategoriaFragment.CategoriaListener;
import br.com.mf.mfreceitas.ListaCategoriaFragment.ListenerCatergoria;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import ClassesBasicas.Categoria;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

public class DetalheCategoriaActivity extends SherlockFragmentActivity implements CategoriaListener{
	Menu menu;
	DetalheCategoriaFragment fragmentDetalhe;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Categoria categoria = (Categoria)getIntent().getSerializableExtra("categoria");
		fragmentDetalhe  = DetalheCategoriaFragment.novoDetalhe(categoria);
		FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
		trans.add(android.R.id.content, fragmentDetalhe, "detalhe");
		trans.commit();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.incluiralterar, menu);
		this.menu = menu;
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		if(item.getItemId() == R.id.act_Novo){
			fragmentDetalhe.Incluir();
		}else if(item.getItemId() == R.id.act_Alterar){
			fragmentDetalhe.habilitarCampos();
		}
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public void aoSalvarCategoria(Categoria categoria) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aoCancelarCategoria(Categoria categoriaAntiga) {
		//Ta dando erro
		finish();
	}


	

}
