package br.com.mf.mfreceitas;

import ClassesBasicas.Receita;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetalheReceitaFragment extends Fragment{
	
	static TextView lblDescricao;
	static TextView lblCategoria;
	static TextView lblQtdPessoas;
	static TextView lblTempoPreparo;
	static TextView lblTempoForno;
	static TextView lblTempoCongelador;
	static TextView lblCustoMedio;
	Receita receita;
	

	
	public void PreencheInformacoes(Receita receita){
		lblDescricao.setText(receita.getDescricao());
		lblCategoria.setText(receita.getCategoria().getDescricao());
		lblQtdPessoas.setText(String.valueOf(receita.getQtdPessoasServe()));
		lblTempoPreparo.setText(String.valueOf(receita.getTempoPreparo()) + receita.getMedidaTempoPreparo());
		lblTempoForno.setText(String.valueOf(receita.getTempoForno()) + receita.getMedidaTempoForno());
		lblTempoCongelador.setText(String.valueOf(receita.getTempoCongelador()) + receita.getMedidaTempoCongelador());
		lblCustoMedio.setText(String.valueOf(receita.getCustoMedio()));
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
		
		View layout = inflater.inflate(R.layout.detalhecategoria, null);
		
		lblDescricao = (TextView)layout.findViewById(R.id.lblDescricao);
		lblCategoria = (TextView)layout.findViewById(R.id.lblCategoria);
		lblQtdPessoas = (TextView)layout.findViewById(R.id.lblQtdPessoas);
		lblTempoPreparo = (TextView)layout.findViewById(R.id.lblTempoPreparo);
		lblTempoForno = (TextView)layout.findViewById(R.id.lblTempoForno);
		lblTempoCongelador = (TextView)layout.findViewById(R.id.lblTempoCongelador);
		lblCustoMedio = (TextView)layout.findViewById(R.id.lblCustoMedio);
		
		
		return layout;
	}
}
