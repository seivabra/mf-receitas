package br.com.mf.mfreceitas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		//this.deleteDatabase("MfReceitas");
	}
	
	public void btnMntCategoriaClick(View v){
		startActivity(new Intent(this, CategoriaActivity.class));
	}
	
	public void btnMntMarcaClick(View v){
		startActivity(new Intent(this, MarcaActivity.class));
	}
	
	public void btnMntProdutoClick(View v){
		startActivity(new Intent(this, ProdutoActivity.class));
	}
	
	public void btnMntUnidadeClick(View v){
		startActivity(new Intent(this, UnidadeActivity.class));
	}
	
	public void btnMntReceitaClick(View v){
		startActivity(new Intent(this, IncluirAlterarReceitaActivity.class));
	}
}
