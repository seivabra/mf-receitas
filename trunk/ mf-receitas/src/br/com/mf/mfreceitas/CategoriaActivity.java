package br.com.mf.mfreceitas;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import br.com.mf.mfreceitas.DetalheCategoriaFragment.CategoriaListener;
import br.com.mf.mfreceitas.ListaCategoriaFragment.ListenerCatergoria;
import ClassesBasicas.Categoria;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;
import android.widget.EditText;

public class CategoriaActivity extends SherlockFragmentActivity implements CategoriaListener{
	private ListaCategoriaFragment listaCategoriaFragment;
	private DetalheCategoriaFragment  fragmentDetalhe;
	private FragmentManager fm;
	Context context = this;
	Menu menu;
	
	
	EditText edtDescricao;
	Button btnSalvar;
	Button btnCancelar;
	
	public boolean mostraDetalhe(){
    	return findViewById(R.id.fragmentDetalheCategorias) != null;
    }
	
	public void CarregaFragmentDetalhe(Categoria categoria){
		fragmentDetalhe = DetalheCategoriaFragment.novoDetalhe(categoria);
		fragmentDetalhe.setListenerCategoria(this);
    	FragmentTransaction trans = fm.beginTransaction();
		trans.replace(R.id.fragmentDetalheCategorias, fragmentDetalhe, "detalhe");
		trans.commit();
    }
	
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categoria);
        fm = getSupportFragmentManager();
        listaCategoriaFragment = (ListaCategoriaFragment)fm.findFragmentById(R.id.fragmentListaCategorias);
        
        if(mostraDetalhe()){
        	CarregaFragmentDetalhe(new Categoria(0, ""));
        }
        
        listaCategoriaFragment.setListener(new ListenerCatergoria() {
			
			@Override
			public void aoClicarNaCategoria(Categoria categoria, int position) {
				if (mostraDetalhe()){
					CarregaFragmentDetalhe(categoria);
				}else{
					Intent intent = new Intent(context, DetalheCategoriaActivity.class);
					intent.putExtra("categoria", categoria);
					startActivity(intent);
				}
			}
		});
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	if(mostraDetalhe()){
	    	getSupportMenuInflater().inflate(R.menu.incluiralterar, menu);
			this.menu = menu;
		}else{
			getSupportMenuInflater().inflate(R.menu.incluir, menu);
			this.menu = menu;
		}
    	return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
    	if (item.getItemId() == R.id.act_Novo){
	    	if (mostraDetalhe()){
	    		//Da erro se mandar nova categoria
	    		//CarregaFragmentDetalhe(new Categoria(0, ""));
	    		fragmentDetalhe.Incluir();
			}else{
				Intent intent = new Intent(context, DetalheCategoriaActivity.class);
				intent.putExtra("categoria", new Categoria(0, ""));
				startActivity(intent);
			}
    	}else if (item.getItemId() == R.id.act_Alterar)
			fragmentDetalhe.habilitarCampos();
    	return super.onMenuItemSelected(featureId, item);
    }

	@Override
	public void aoSalvarCategoria(Categoria categoria) {
		//Tem que saber se estorou exce��o
		listaCategoriaFragment.ListarCategorias();
		fragmentDetalhe.DesabilitarCampos();
	}

	@Override
	public void aoCancelarCategoria(Categoria categoriaAntiga) {
		//Se cancelar um novo ele pega a ultima categoria selecionada... porque na hora de incluir eu n�o mando uma categoria zerada, s� limpo o campo
		categoriaAntiga = listaCategoriaFragment.achaCategoriaPeloCodigo(categoriaAntiga);
		fragmentDetalhe.PreencheCampos(categoriaAntiga);
		fragmentDetalhe.DesabilitarCampos();
	}

	
}
