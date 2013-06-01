package br.com.mf.mfreceitas;

import ClassesBasicas.Unidade;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetalheUnidadeFragment extends Fragment{
	
	static EditText edtDescricao;
	static Button btnSalvar;
	static Button btnCancelar;
	DetalheUnidadeListener listener;
	Unidade unidade;
	Fachada fachada;
	static Boolean habilitaCampos;
	
	public void Incluir() {
		habilitarCampos();
		unidade = new Unidade(0, "");
		edtDescricao.setText(unidade.getDescricao());
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
	
	
//	public long salvar(Unidade unidade){
//		if(unidade.getId() == 0)
//			return fachada.InserirUnidade(unidade);
//		else
//			return fachada.AlterarUnidade(unidade);
//		
////		if(listener != null)	
////			listener.aoSalvarUnidade(unidade);
//	}
	
	public void setDetalheListenerUnidade(DetalheUnidadeListener listener) {
		this.listener = listener;
	}
	
	public static DetalheUnidadeFragment novoDetalhe(Unidade unidade){
		Bundle parametros = new Bundle();
		parametros.putSerializable("unidade", unidade);
		
		DetalheUnidadeFragment detalhe = new DetalheUnidadeFragment();
		detalhe.setArguments(parametros);
		
//		if(unidade.getId() > 0)
//			DesabilitarCampos();
		
		return detalhe;
	}
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		unidade = (Unidade)getArguments().get("unidade");
		
		View layout = inflater.inflate(R.layout.detalheunidade, null);
		
		edtDescricao = (EditText)layout.findViewById(R.id.edtDescricao);
		btnSalvar = (Button)layout.findViewById(R.id.btnSalvar);
		btnCancelar = (Button)layout.findViewById(R.id.btnCancelar);
		
		fachada = new Fachada(getActivity());
		
		btnSalvar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(listener != null){
					unidade.setDescricao(edtDescricao.getText().toString());
					
					if (fachada.AchouUnidadeIgual(unidade))
						Toast.makeText(getActivity(), "R.string.msgUnidadeExiste", Toast.LENGTH_SHORT).show();
					else{
						if(unidade.getId() == 0){
							try {
								unidade.setId((int)(fachada.InserirUnidade(unidade)));
							} catch (Exception e) {
								Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
							}
						}
						else
							fachada.AlterarUnidade(unidade);
						
						listener.aoSalvarUnidade(unidade);
					}
				}
			}
		});
		btnCancelar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				listener.aoCancelarUnidade(unidade);
			}
		});
		
//		if(unidade.getId() > 0)
//			DesabilitarCampos();
		
		edtDescricao.setText(unidade.getDescricao());
		
		if ((habilitaCampos == null) ||(habilitaCampos == false))
			DesabilitarCampos();
		else{
			habilitarCampos();
			edtDescricao.selectAll();
		}
		return layout;
	}
	
	public void PreencheCampos(Unidade unidade){
		edtDescricao.setText(unidade.getDescricao());
	}
	
	public interface DetalheUnidadeListener{
		void aoSalvarUnidade(Unidade unidade);
		void aoCancelarUnidade(Unidade unidadeAntiga);
		
	}
	
}
