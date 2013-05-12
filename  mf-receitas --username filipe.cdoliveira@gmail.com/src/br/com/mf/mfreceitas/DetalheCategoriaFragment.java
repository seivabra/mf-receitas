package br.com.mf.mfreceitas;

import ClassesBasicas.Categoria;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class DetalheCategoriaFragment extends Fragment{
	public static DetalheCategoriaFragment novoDetalhe(Categoria categoria){
		Bundle parametros = new Bundle();
		parametros.putSerializable("categoria", categoria);
		
		DetalheCategoriaFragment detalhe = new DetalheCategoriaFragment();
		detalhe.setArguments(parametros);
		return detalhe;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		Categoria categoria = (Categoria)getArguments().get("categoria");
		
		View layout = inflater.inflate(R.layout.detalhecategoria, null);
		
		TextView lblCodigo = (TextView)layout.findViewById(R.id.lblCodigo);
		EditText edtDescricao = (EditText)layout.findViewById(R.id.edtDescricao);
		
		lblCodigo.setText(String.valueOf(categoria.getId()));
		edtDescricao.setText(categoria.getDescricao());
		
		return layout;
	}
}
