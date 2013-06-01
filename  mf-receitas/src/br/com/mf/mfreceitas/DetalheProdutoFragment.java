package br.com.mf.mfreceitas;

import ClassesBasicas.Produto;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetalheProdutoFragment extends Fragment{
	
	static EditText edtDescricao;
	static Button btnSalvar;
	static Button btnCancelar;
	DetalheProdutoListener listener;
	Produto produto;
	Fachada fachada;
	static Boolean habilitaCampos;
	
	public void Incluir() {
		habilitarCampos();
		produto = new Produto(0, "");
		edtDescricao.setText(produto.getDescricao());
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
	
	
//	public long salvar(Produto produto){
//		if(produto.getId() == 0)
//			return fachada.InserirProduto(produto);
//		else
//			return fachada.AlterarProduto(produto);
//		
////		if(listener != null)	
////			listener.aoSalvarProduto(produto);
//	}
	
	public void setDetalheListenerProduto(DetalheProdutoListener listener) {
		this.listener = listener;
	}
	
	public static DetalheProdutoFragment novoDetalhe(Produto produto){
		Bundle parametros = new Bundle();
		parametros.putSerializable("produto", produto);
		
		DetalheProdutoFragment detalhe = new DetalheProdutoFragment();
		detalhe.setArguments(parametros);
		
//		if(produto.getId() > 0)
//			DesabilitarCampos();
		
		return detalhe;
	}
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		produto = (Produto)getArguments().get("produto");
		
		View layout = inflater.inflate(R.layout.detalheproduto, null);
		
		edtDescricao = (EditText)layout.findViewById(R.id.edtDescricao);
		btnSalvar = (Button)layout.findViewById(R.id.btnSalvar);
		btnCancelar = (Button)layout.findViewById(R.id.btnCancelar);
		
		fachada = new Fachada(getActivity());
		
		btnSalvar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(listener != null){
					produto.setDescricao(edtDescricao.getText().toString());
					
					if (fachada.AchouProdutoIgual(produto))
						Toast.makeText(getActivity(), "R.string.msgProdutoExiste", Toast.LENGTH_SHORT).show();
					else{
						if(produto.getId() == 0){
							try {
								produto.setId((int)(fachada.InserirProduto(produto)));
							} catch (Exception e) {
								Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
							}
						}
						else
							fachada.AlterarProduto(produto);
						
						listener.aoSalvarProduto(produto);
					}
				}
			}
		});
		btnCancelar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				listener.aoCancelarProduto(produto);
			}
		});
		
//		if(produto.getId() > 0)
//			DesabilitarCampos();
		
		edtDescricao.setText(produto.getDescricao());
		
		if ((habilitaCampos == null) ||(habilitaCampos == false))
			DesabilitarCampos();
		else{
			habilitarCampos();
			edtDescricao.selectAll();
		}
		return layout;
	}
	
	public void PreencheCampos(Produto produto){
		edtDescricao.setText(produto.getDescricao());
	}
	
	public interface DetalheProdutoListener{
		void aoSalvarProduto(Produto produto);
		void aoCancelarProduto(Produto produtoAntiga);
		
	}
	
}
