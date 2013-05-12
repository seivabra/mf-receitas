package br.com.mf.mfreceitas;

import ClassesBasicas.Categoria;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class DetalheCategoriaActivity extends FragmentActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Categoria categoria = (Categoria)getIntent().getSerializableExtra("categoria");
		FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
		trans.add(android.R.id.content, DetalheCategoriaFragment.novoDetalhe(categoria), "detalhe");
		trans.commit();
	}
}
