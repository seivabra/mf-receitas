package Dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import ClassesBasicas.Receita;

public class DaoReceita {
	private SQLiteDatabase database;  
    private DatabaseHelper databaseHelper; 
	//private String scriptCreate = "create table categorias (_id integer primary key autoincrement, descricao text not null);";
	private ContentValues contentValues;
	public DaoReceita(Context context){
		databaseHelper = new DatabaseHelper(context, "MfReceitas", 6/*, scriptCreate*/);
	}
	
	public long InserirReceita(Receita receita){
		contentValues = new ContentValues();  
		contentValues.put("descricao", receita.getDescricao());
		if (receita.getTempoForno() > 0){
			contentValues.put("tempoForno", receita.getTempoForno());
			contentValues.put("medidaTempoForno", receita.getMedidaTempoForno());
		}
		if (receita.getTempoCongelador() > 0){
			contentValues.put("tempoCongelador", receita.getTempoCongelador());
			contentValues.put("medidaTempoCongelador", receita.getMedidaTempoCongelador());
		}
		if (receita.getTempoPreparo() > 0){
			contentValues.put("tempoPreparo", receita.getTempoPreparo());
			contentValues.put("medidaTempoPreparo", receita.getMedidaTempoPreparo());
		}
		if (receita.getCategoria().getId() > 0)
			contentValues.put("codCategoria", receita.getCategoria().getId());
		if (receita.getQtdPessoasServe() > 0)
			contentValues.put("qtdPessoasServe", receita.getQtdPessoasServe());
		if (receita.getCustoMedio() > 0)
			contentValues.put("custoMedio", receita.getCustoMedio());
		database = databaseHelper.getWritableDatabase();  
	    long id = database.insert("receitas", null, contentValues);  
	    
	    for (int i = 0; i < receita.getItens().size(); i++) {
	    	contentValues = new ContentValues();
		    contentValues.put("codReceita", id);
		    contentValues.put("codProduto", receita.getItens().get(i).getProduto().getId());
		    contentValues.put("codUnidade", receita.getItens().get(i).getUnidade().getId());
			if (receita.getItens().get(i).getMarca().getId() > 0)
				contentValues.put("codMarca", receita.getItens().get(i).getMarca().getId());
			if (receita.getItens().get(i).getPreco() > 0)
				contentValues.put("preco", receita.getItens().get(i).getPreco());
			database = databaseHelper.getWritableDatabase();  
		    database.insert("itensReceita", null, contentValues);
		}
	    
		
	    
	    database.close();  
	    return id;
	}
	
	public int AlterarReceita(Receita receita){
		return 0;
	}
	
	public int ExcluirReceita(Receita receita){
		return 0;
	}
	
	public ArrayList<Receita> ListarReceitas(){
		return null;
	}
	
	public ArrayList<Receita> PesquisarReceitaDescricao(String descricao){
		return null;
	}
}
