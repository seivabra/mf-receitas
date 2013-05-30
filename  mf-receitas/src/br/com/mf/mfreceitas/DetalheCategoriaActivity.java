package br.com.mf.mfreceitas;

import br.com.mf.mfreceitas.DetalheCategoriaFragment.DetalheCategoriaListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import ClassesBasicas.Categoria;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

public class DetalheCategoriaActivity extends SherlockFragmentActivity implements DetalheCategoriaListener{
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
		
		fragmentDetalhe.setDetalheListenerCategoria(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.incluiralterarvoltar, menu);
		this.menu = menu;
		return super.onCreateOptionsMenu(menu);
	}
	
//	public void CarregaFragmentDetalhe(Categoria categoria){
//		fragmentDetalhe = DetalheCategoriaFragment.novoDetalhe(categoria);
//		fragmentDetalhe.setDetalheListenerCategoria(this);
//    	FragmentTransaction trans = fm.beginTransaction();
//		trans.replace(R.id.fragmentDetalheCategorias, fragmentDetalhe, "detalhe");
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
	public void aoSalvarCategoria(Categoria categoria) {
		// TODO Auto-generated method stub
		finish();
	}

	@Override
	public void aoCancelarCategoria(Categoria categoriaAntiga) {
		//Ta dando erro
		finish();
	}


	

}
