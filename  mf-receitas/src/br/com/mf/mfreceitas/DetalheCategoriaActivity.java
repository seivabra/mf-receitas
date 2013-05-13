package br.com.mf.mfreceitas;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;

import ClassesBasicas.Categoria;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

public class DetalheCategoriaActivity extends SherlockFragmentActivity{
	Menu menu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Categoria categoria = (Categoria)getIntent().getSerializableExtra("categoria");
		FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
		trans.add(android.R.id.content, DetalheCategoriaFragment.novoDetalhe(categoria), "detalhe");
		trans.commit();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.incluirsalvar, menu);
		this.menu = menu;
		
		return super.onCreateOptionsMenu(menu);
	}

}
