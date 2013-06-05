package br.com.mf.mfreceitas;

import br.com.mf.mfreceitas.ListaReceitaFragment.ReceitaListener;
import ClassesBasicas.Receita;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetalheReceitaFragment extends Fragment{
	
	static TextView lblDescricao;
	static TextView lblCategoria;
	static TextView lblQtdPessoas;
	static TextView lblTempoPreparo;
	static TextView lblTempoForno;
	static TextView lblTempoCongelador;
	static TextView lblCustoMedio;
	static Receita receita;
	

	
	public void PreencheInformacoes(Receita receita){
		lblDescricao.setText(receita.getDescricao());
		lblCategoria.setText(receita.getCategoria().getDescricao());
		if (receita.getQtdPessoasServe() > 0)
			lblQtdPessoas.setText(String.valueOf(receita.getQtdPessoasServe()));
		if (receita.getTempoPreparo() > 0)
			lblTempoPreparo.setText(String.valueOf(receita.getTempoPreparo()) + receita.getMedidaTempoPreparo());
		if (receita.getTempoForno() > 0)
			lblTempoForno.setText(String.valueOf(receita.getTempoForno()) + receita.getMedidaTempoForno());
		if (receita.getTempoCongelador() > 0)
			lblTempoCongelador.setText(String.valueOf(receita.getTempoCongelador()) + receita.getMedidaTempoCongelador());
		if (receita.getCustoMedio() > 0.0)
			lblCustoMedio.setText(String.valueOf(receita.getCustoMedio()));
		
//		LinearLayout controls = (android.widget.LinearLayout)getActivity().findViewById(R.id.linearIngedientes);
//		for (int i = 0; i < receita.getItens().size(); i++) {
//			TextView txtProduto = new TextView(getActivity());
//			txtProduto.setText(String.valueOf(receita.getItens().get(i).getQuantidade()) + " " + receita.getItens().get(i).getUnidade().getDescricao() +
//						      " - " + receita.getItens().get(i).getProduto().getDescricao() + " " + receita.getItens().get(i).getMarca().getDescricao());
//			controls.addView(txtProduto);
//		}
	}
	

	public static DetalheReceitaFragment novoDetalhe(Receita receita){
		Bundle parametros = new Bundle();
		parametros.putSerializable("receita", receita);
		
		DetalheReceitaFragment detalhe = new DetalheReceitaFragment();
		detalhe.setArguments(parametros);
		

		return detalhe;
	}
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		receita = (Receita)getArguments().get("receita");
		
		View layout = inflater.inflate(R.layout.detalhereceita, null);
		
		lblDescricao = (TextView)layout.findViewById(R.id.lblDescricao);
		lblCategoria = (TextView)layout.findViewById(R.id.lblCategoria);
		lblQtdPessoas = (TextView)layout.findViewById(R.id.lblQtdPessoas);
		lblTempoPreparo = (TextView)layout.findViewById(R.id.lblTempoPreparo);
		lblTempoForno = (TextView)layout.findViewById(R.id.lblTempoForno);
		lblTempoCongelador = (TextView)layout.findViewById(R.id.lblTempoCongelador);
		lblCustoMedio = (TextView)layout.findViewById(R.id.lblCustoMedio);
		
		if (receita.getId() > 0){
				
			PreencheInformacoes(receita);
		
		}
		return layout;
	}
}
