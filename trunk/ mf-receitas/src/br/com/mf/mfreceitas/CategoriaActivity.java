package br.com.mf.mfreceitas;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import br.com.mf.mfreceitas.ListaCategoriaFragment.ListenerCatergoria;
import ClassesBasicas.Categoria;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class CategoriaActivity extends SherlockFragmentActivity{
	private ListaCategoriaFragment listaCategoriaFragment;
	private FragmentManager fm;
	Context context = this;
	Menu menu;
	
	public boolean mostraDetalhe(){
    	return findViewById(R.id.fragmentDetalheCategorias) != null;
    }
	
	private void habilitarSalvar(boolean habilitar) {
		MenuItem itemSave = menu.findItem(R.id.action_save);
		if (itemSave != null){
			itemSave.setVisible(habilitar);
		}
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categoria);
        fm = getSupportFragmentManager();
        listaCategoriaFragment = (ListaCategoriaFragment)fm.findFragmentById(R.id.fragmentListaCategorias);
        
        listaCategoriaFragment.setListener(new ListenerCatergoria() {
			
			@Override
			public void aoClicarNaCategoria(Categoria categoria, int position) {
				if (mostraDetalhe()){
					FragmentTransaction trans = fm.beginTransaction();
					trans.replace(R.id.fragmentDetalheCategorias, DetalheCategoriaFragment.novoDetalhe(categoria), "detalhe");
					trans.commit();
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
    	getSupportMenuInflater().inflate(R.menu.incluirsalvar, menu);
		this.menu = menu;
		habilitarSalvar(false);
    	return super.onCreateOptionsMenu(menu);
    }
    
    
}
