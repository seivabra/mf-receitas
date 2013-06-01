package br.com.mf.mfreceitas;

import ClassesBasicas.Marca;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetalheMarcaFragment extends Fragment{
	
	static EditText edtDescricao;
	static Button btnSalvar;
	static Button btnCancelar;
	DetalheMarcaListener listener;
	Marca marca;
	Fachada fachada;
	static Boolean habilitaCampos;
	
	public void Incluir() {
		habilitarCampos();
		marca = new Marca(0, "");
		edtDescricao.setText(marca.getDescricao());
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
	
	
//	public long salvar(Marca marca){
//		if(marca.getId() == 0)
//			return fachada.InserirMarca(marca);
//		else
//			return fachada.AlterarMarca(marca);
//		
////		if(listener != null)	
////			listener.aoSalvarMarca(marca);
//	}
	
	public void setDetalheListenerMarca(DetalheMarcaListener listener) {
		this.listener = listener;
	}
	
	public static DetalheMarcaFragment novoDetalhe(Marca marca){
		Bundle parametros = new Bundle();
		parametros.putSerializable("marca", marca);
		
		DetalheMarcaFragment detalhe = new DetalheMarcaFragment();
		detalhe.setArguments(parametros);
		
//		if(marca.getId() > 0)
//			DesabilitarCampos();
		
		return detalhe;
	}
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		marca = (Marca)getArguments().get("marca");
		
		View layout = inflater.inflate(R.layout.detalhemarca, null);
		
		edtDescricao = (EditText)layout.findViewById(R.id.edtDescricao);
		btnSalvar = (Button)layout.findViewById(R.id.btnSalvar);
		btnCancelar = (Button)layout.findViewById(R.id.btnCancelar);
		
		fachada = new Fachada(getActivity());
		
		btnSalvar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(listener != null){
					marca.setDescricao(edtDescricao.getText().toString());
					
					if (fachada.AchouMarcaIgual(marca))
						Toast.makeText(getActivity(), "R.string.msgMarcaExiste", Toast.LENGTH_SHORT).show();
					else{
						if(marca.getId() == 0){
							try {
								marca.setId((int)(fachada.InserirMarca(marca)));
							} catch (Exception e) {
								Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
							}
						}
						else
							fachada.AlterarMarca(marca);
						
						listener.aoSalvarMarca(marca);
					}
				}
			}
		});
		btnCancelar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				listener.aoCancelarMarca(marca);
			}
		});
		
//		if(marca.getId() > 0)
//			DesabilitarCampos();
		
		edtDescricao.setText(marca.getDescricao());
		
		if ((habilitaCampos == null) ||(habilitaCampos == false))
			DesabilitarCampos();
		else{
			habilitarCampos();
			edtDescricao.selectAll();
		}
		return layout;
	}
	
	public void PreencheCampos(Marca marca){
		edtDescricao.setText(marca.getDescricao());
	}
	
	public interface DetalheMarcaListener{
		void aoSalvarMarca(Marca marca);
		void aoCancelarMarca(Marca marcaAntiga);
		
	}
	
}
