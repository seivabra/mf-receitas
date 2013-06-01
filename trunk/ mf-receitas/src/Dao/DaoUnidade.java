package Dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import ClassesBasicas.Unidade;

public class DaoUnidade {
	
	private SQLiteDatabase database;  
    private DatabaseHelper databaseHelper; 
	private String scriptCreate = "create table unidades (_id integer primary key autoincrement, descricao text not null);";
	private ContentValues contentValues;
	
	public DaoUnidade(Context context){
		databaseHelper = new DatabaseHelper(context, "MfReceitas", 1, scriptCreate);
	}
	
	public long InserirUnidade(Unidade unidade){
		contentValues = new ContentValues();  
		contentValues.put("descricao", unidade.getDescricao()); 
		database = databaseHelper.getWritableDatabase();  
	    long id = database.insert("unidades", null, contentValues);  
	    database.close();  
	    return id;  
	}
	
	public int AlterarUnidade(Unidade unidade){
		contentValues = new ContentValues();  
		contentValues.put("descricao", unidade.getDescricao());  
		
		database = databaseHelper.getWritableDatabase();  
	    int rows = database.update("unidades", contentValues, "_id = ?",   
	       new String[]{ String.valueOf(unidade.getId())});  
	    database.close();  
	    return rows;
	}
	
	public int ExcluirUnidade(Unidade unidade){
		database = databaseHelper.getWritableDatabase();  
	    int rows = database.delete("unidades", "_id = ?",   
	      new String[]{ String.valueOf(unidade.getId()) });  
	    return rows; 
	}
	
	public ArrayList<Unidade> ListarUnidades(){
		ArrayList<Unidade> listaUnidades = new ArrayList<Unidade>();  
		  
	    database = databaseHelper.getWritableDatabase();  
	    
	    Cursor cursor = database.rawQuery("select _id, descricao from unidades", null);
	    
	    cursor.moveToFirst();  
	    while(!cursor.isAfterLast()){  
	    	Unidade unidade = preencherUnidade(cursor);  
	    	listaUnidades.add(unidade);  
	    	cursor.moveToNext();  
	    }  
	    cursor.close();  
	    database.close();  
	    return listaUnidades;  
	}
	
	public ArrayList<Unidade> PesquisarUnidadeDescricao(String descricao){
		ArrayList<Unidade> listaUnidades = new ArrayList<Unidade>();  
		  
		
	    String[] columns = new String[]{  
	       "_id", "descricao"};  
	    String[] args = new String[]{descricao+"%"};  
	  
	    database = databaseHelper.getWritableDatabase();  
	    Cursor cursor = database.query("unidades", columns,   
	       "descricao like ?", args, null, null, "descricao");
	    
	   cursor.moveToFirst();  
	    while(!cursor.isAfterLast()){  
	    	Unidade unidade = preencherUnidade(cursor);  
	    	listaUnidades.add(unidade);  
	    	cursor.moveToNext();  
	    }  
	    cursor.close();  
	    database.close();  
	    return listaUnidades;
	}
	
	public Boolean AchouUnidadeIgual(Unidade unidade){
		Boolean achouUnidade = false;  
		
		String[] columns = new String[]{"_id", "descricao"};  
	    String[] args = new String[]{unidade.getDescricao(), String.valueOf(unidade.getId())};//+"%"};  
	  
	    database = databaseHelper.getWritableDatabase();  
	    Cursor cursor = database.query("unidades", columns,   
	       "upper(descricao) = upper(?) and _id <> ?", args, null, null, "descricao");
	   Unidade unidadeIgual = null;
	   cursor.moveToFirst();  
	    while(!cursor.isAfterLast()){  
	    	unidadeIgual = preencherUnidade(cursor);  
	    	cursor.moveToNext();  
	    }

	    if (unidadeIgual != null)
		   achouUnidade = true;
	    
	    cursor.close();  
	    database.close(); 
	    
	    return achouUnidade;
	}
	
	private Unidade preencherUnidade(Cursor cursor) {  
		Unidade unidade = new Unidade();  
		unidade.setId((int)cursor.getLong(0));   
		unidade.setDescricao(cursor.getString(1));  
	    return unidade;  
	}  
}
