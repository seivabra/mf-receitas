package Dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import ClassesBasicas.Categoria;
import ClassesBasicas.Item;
import ClassesBasicas.Marca;
import ClassesBasicas.Produto;
import ClassesBasicas.Receita;
import ClassesBasicas.Unidade;

public class DaoReceita {
	private SQLiteDatabase database;  
    private DatabaseHelper databaseHelper; 
	//private String scriptCreate = "create table categorias (_id integer primary key autoincrement, descricao text not null);";
	private ContentValues contentValues;
	public DaoReceita(Context context){
		databaseHelper = new DatabaseHelper(context, "MfReceitas", 7/*, scriptCreate*/);
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
		    contentValues.put("quantidade", receita.getItens().get(i).getQuantidade());
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
		ArrayList<Receita> listaReceitas = new ArrayList<Receita>();  
		  
	    database = databaseHelper.getWritableDatabase();  
	    

	    Cursor cursor = database.rawQuery("select r._id id, r.descricao, r.qtdPessoasServe, r.tempoForno, r.tempoCongelador, r.tempoPreparo, " +
	    								  "r.custoMedio, r.modoPreparo, r.medidaTempoForno, r.medidaTempoCongelador, r.medidaTempoPreparo, c._id, c.descricao, "+
	    								  "p._id, p.descricao, u._id, u.descricao, m._id, m.descricao, i.quantidade, i.preco from receitas r " +
	    								  "left join categorias c on c._id = r.codCategoria "+
	    								  "left join itensReceita i on i.codReceita = id " +
	    								  "left join produtos p on p._id = i.codProduto " +
	    								  "left join marcas m on m._id = i.codMarca " +
	    								  "left join unidades u on u._id = i.codUnidade;", null);
	    
	    cursor.moveToFirst();
	    Receita receita = new Receita();
	    while(!cursor.isAfterLast()){  
	    	receita = preencherReceita(cursor, receita, listaReceitas);  
	    	//listaReceitas.add(receita);  
	    	cursor.moveToNext();  
	    }
	    listaReceitas.add(receita);
	    cursor.close();  
	    database.close();  
	    return listaReceitas;  
	}
	
	public ArrayList<Receita> PesquisarReceitaDescricao(String descricao){
		return null;
	}
	
	private Receita preencherReceita(Cursor cursor, Receita receita, ArrayList<Receita> listaReceitas) {  
		Item item = new Item();
		item.setProduto(new Produto((int)cursor.getLong(13), cursor.getString(14)));
		item.setUnidade(new Unidade((int)cursor.getLong(15), cursor.getString(16)));
		item.setMarca(new Marca((int)cursor.getLong(17), cursor.getString(18)));
		item.setQuantidade(cursor.getInt(19));
		item.setPreco(cursor.getDouble(20));
		if (receita.getId() != (int)cursor.getLong(0)) {
			if (receita.getId() >0)
				listaReceitas.add(receita);
			receita = new Receita();
			receita.setId((int)cursor.getLong(0));   
			receita.setDescricao(cursor.getString(1)); 
			receita.setQtdPessoasServe(cursor.getInt(2));
			receita.setTempoForno(cursor.getInt(3));
			receita.setTempoCongelador(cursor.getInt(4));
			receita.setTempoPreparo(cursor.getInt(5));
			receita.setCustoMedio(cursor.getDouble(6));
			receita.setModoPreparo(cursor.getString(7));
			receita.setMedidaTempoForno(cursor.getString(8));
			receita.setMedidaTempoCongelador(cursor.getString(9));
			receita.setMedidaTempoPreparo(cursor.getString(10));
			receita.setCategoria(new Categoria((int)cursor.getLong(11), cursor.getString(12)));
			receita.setItens(new ArrayList<Item>());
		}
		receita.getItens().add(item); 
	    return receita;  
	} 
}
