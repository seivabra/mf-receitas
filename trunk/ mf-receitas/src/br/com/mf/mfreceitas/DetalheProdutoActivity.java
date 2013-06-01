package br.com.mf.mfreceitas;

import br.com.mf.mfreceitas.DetalheProdutoFragment.DetalheProdutoListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import ClassesBasicas.Produto;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

public class DetalheProdutoActivity extends SherlockFragmentActivity implements DetalheProdutoListener{
	Menu menu;
	DetalheProdutoFragment fragmentDetalhe;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Produto produto = (Produto)getIntent().getSerializableExtra("produto");
		fragmentDetalhe  = DetalheProdutoFragment.novoDetalhe(produto);
		FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
		trans.add(android.R.id.content, fragmentDetalhe, "detalhe");
		trans.commit();
		
		fragmentDetalhe.setDetalheListenerProduto(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.incluiralterarvoltar, menu);
		this.menu = menu;
		return super.onCreateOptionsMenu(menu);
	}
	
//	public void CarregaFragmentDetalhe(Produto produto){
//		fragmentDetalhe = DetalheProdutoFragment.novoDetalhe(produto);
//		fragmentDetalhe.setDetalheListenerProduto(this);
//    	FragmentTransaction trans = fm.beginTransaction();
//		trans.replace(R.id.fragmentDetalheProdutos, fragmentDetalhe, "detalhe");
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
	public void aoSalvarProduto(Produto produto) {
		// TODO Auto-generated method stub
		finish();
	}

	@Override
	public void aoCancelarProduto(Produto produtoAntiga) {
		//Ta dando erro
		finish();
	}


	

}
