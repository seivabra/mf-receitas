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
import android.widget.Toast;

public class DetalheCategoriaFragment extends Fragment{
	
	static EditText edtDescricao;
	static Button btnSalvar;
	static Button btnCancelar;
	DetalheCategoriaListener listener;
	Categoria categoria;
	Fachada fachada;
	static Boolean habilitaCampos;
	
	public void Incluir() {
		habilitarCampos();
		categoria = new Categoria(0, "");
		edtDescricao.setText(categoria.getDescricao());
	}
	
	public void habilitarCampos() {
		edtDescricao.setEnabled(true);
		btnSalvar.setEnabled(true);
		btnCancelar.setEnabled(true);
		//edtDescricao.selectAll();
	}
	
	public void DesabilitarCampos() {
		edtDescricao.setEnabled(false);
		btnSalvar.setEnabled(false);
		btnCancelar.setEnabled(false);
	}
	
//	public void onActivityCreated(Bundle savedInstanceState) {
//		fachada = new Fachada(getActivity());
//	};
	
	
//	public long salvar(Categoria categoria){
//		if(categoria.getId() == 0)
//			return fachada.InserirCategoria(categoria);
//		else
//			return fachada.AlterarCategoria(categoria);
//		
////		if(listener != null)	
////			listener.aoSalvarCategoria(categoria);
//	}
	
	public void setDetalheListenerCategoria(DetalheCategoriaListener listener) {
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
		
		fachada = new Fachada(getActivity());
		
		btnSalvar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(listener != null){
					categoria.setDescricao(edtDescricao.getText().toString());
					if(categoria.getId() == 0){
						try {
							categoria.setId((int)(fachada.InserirCategoria(categoria)));
						} catch (Exception e) {
							Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
						}
					}
					else
						fachada.AlterarCategoria(categoria);
					
					listener.aoSalvarCategoria(categoria);
				}
			}
		});
		btnCancelar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				listener.aoCancelarCategoria(categoria);
			}
		});
		
//		if(categoria.getId() > 0)
//			DesabilitarCampos();
		
		edtDescricao.setText(categoria.getDescricao());
		
		if ((habilitaCampos == null) ||(habilitaCampos == false))
			DesabilitarCampos();
		else{
			habilitarCampos();
			edtDescricao.selectAll();
		}
		return layout;
	}
	
	public void PreencheCampos(Categoria categoria){
		edtDescricao.setText(categoria.getDescricao());
	}
	
	public interface DetalheCategoriaListener{
		void aoSalvarCategoria(Categoria categoria);
		void aoCancelarCategoria(Categoria categoriaAntiga);
		
	}
	
}
