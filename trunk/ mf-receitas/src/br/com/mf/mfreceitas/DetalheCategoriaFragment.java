package br.com.mf.mfreceitas;

import ClassesBasicas.Categoria;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class DetalheCategoriaFragment extends Fragment{
	
	EditText edtDescricao;
	Button btnSalvar;
	Button btnCancelar;
	CategoriaListener listener;
	Categoria categoria;
	
	public void Incluir() {
		habilitarCampos();
		edtDescricao.setText("");
	}
	
	public void habilitarCampos() {
		edtDescricao.setEnabled(true);
		btnSalvar.setEnabled(true);
		btnCancelar.setEnabled(true);
		edtDescricao.selectAll();
	}
	
	public void DesabilitarCampos() {
		edtDescricao.setEnabled(false);
		btnSalvar.setEnabled(false);
		btnCancelar.setEnabled(false);
	}
	Fachada fachada = new Fachada(getActivity());
	public long salvar(Categoria categoria){
		if(categoria.getId() == 0)
			return fachada.InserirCategoria(categoria);
		else
			return fachada.AlterarCategoria(categoria);
		
//		if(listener != null)	
//			listener.aoSalvarCategoria(categoria);
	}
	
	public void setListenerCategoria(CategoriaListener listener) {
		this.listener = listener;
	}
	
	public static DetalheCategoriaFragment novoDetalhe(Categoria categoria){
		Bundle parametros = new Bundle();
		parametros.putSerializable("categoria", categoria);
		
		DetalheCategoriaFragment detalhe = new DetalheCategoriaFragment();
		detalhe.setArguments(parametros);
		
//		if(categoria.getId() > 0)
//			DesabilitarCampos();
		
		return detalhe;
	}
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		categoria = (Categoria)getArguments().get("categoria");
		
		View layout = inflater.inflate(R.layout.detalhecategoria, null);
		
		edtDescricao = (EditText)layout.findViewById(R.id.edtDescricao);
		btnSalvar = (Button)layout.findViewById(R.id.btnSalvar);
		btnCancelar = (Button)layout.findViewById(R.id.btnCancelar);
		
		btnSalvar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(listener != null){
					categoria.setDescricao(edtDescricao.getText().toString());
					long a = 0;
					if(categoria.getId() == 0)
						a = fachada.InserirCategoria(categoria);
					else
						fachada.AlterarCategoria(categoria);	
					listener.aoSalvarCategoria(categoria);
				}
			}
		});
		btnCancelar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				listener.aoCancelarCategoria();
				
			}
		});
		
		if(categoria.getId() > 0)
			DesabilitarCampos();
		
		edtDescricao.setText(categoria.getDescricao());
		
		return layout;
	}
	
	public interface CategoriaListener{
		void aoSalvarCategoria(Categoria categoria);
		void aoCancelarCategoria();
	}
	
}
