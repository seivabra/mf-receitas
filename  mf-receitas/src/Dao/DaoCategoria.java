package Dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import ClassesBasicas.Categoria;

public class DaoCategoria {
	private SQLiteDatabase database;  
    private DatabaseHelper databaseHelper; 
	//private String scriptCreate = "create table categorias (_id integer primary key autoincrement, descricao text not null);";
	private ContentValues contentValues;
	public DaoCategoria(Context context){
		databaseHelper = new DatabaseHelper(context, "MfReceitas", 1/*, scriptCreate*/);
	}
	
	public long InserirCategoria(Categoria categoria){
		contentValues = new ContentValues();  
		contentValues.put("descricao", categoria.getDescricao()); 
		//erro aqui
	    database = databaseHelper.getWritableDatabase();  
	    long id = database.insert("categorias", null, contentValues);  
	    database.close();  
	    return id;  
	}
	
	public long AlterarCategoria(Categoria categoria){
		contentValues = new ContentValues();  
		contentValues.put("descricao", categoria.getDescricao());  
		
		database = databaseHelper.getWritableDatabase();  
	    int rows = database.update("categorias", contentValues, "_id = ?",   
	       new String[]{ String.valueOf(categoria.getId())});  
	    database.close();  
	    return rows;
	}
	
	public int ExcluirCategoria(Categoria categoria){
		
		database = databaseHelper.getWritableDatabase();  
	    int rows = database.delete("categorias", "_id = ?",   
	      new String[]{ String.valueOf(categoria.getId()) });  
	    return rows; 
	}
	
	public ArrayList<Categoria> ListarCategorias(){
		ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();  
		  
	    database = databaseHelper.getWritableDatabase();  
	    
	    Cursor cursor = database.rawQuery("select _id, descricao from categorias", null);
	    
	    cursor.moveToFirst();  
	    while(!cursor.isAfterLast()){  
	    	Categoria categoria = preencherCategoria(cursor);  
	    	listaCategorias.add(categoria);  
	    	cursor.moveToNext();  
	    }  
	    cursor.close();  
	    database.close();  
	    return listaCategorias;  
	}
	
	public ArrayList<Categoria> PesquisarCategoriaDescricao(String descricao){
		ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();  
		  
		//Upper
		
	    String[] columns = new String[]{  
	       "_id", "descricao"};  
	    String[] args = new String[]{descricao+"%"};  
	  
	    database = databaseHelper.getWritableDatabase();  
	    Cursor cursor = database.query("categorias", columns,   
	       "descricao like ?", args, null, null, "descricao");
	    
	   cursor.moveToFirst();  
	    while(!cursor.isAfterLast()){  
	    	Categoria categoria = preencherCategoria(cursor);  
	    	listaCategorias.add(categoria);  
	    	cursor.moveToNext();  
	    }  
	    cursor.close();  
	    database.close();  
	    return listaCategorias;
	}
	
	public Boolean AchouCategoriaIgual(Categoria categoria){
		Boolean achouCategoria = false;  
		
		String[] columns = new String[]{"_id", "descricao"};  
	    String[] args = new String[]{categoria.getDescricao(), String.valueOf(categoria.getId())};//+"%"};  
	  
	    database = databaseHelper.getWritableDatabase();  
	    Cursor cursor = database.query("categorias", columns,   
	       "upper(descricao) = upper(?) and _id <> ?", args, null, null, "descricao");
	   Categoria categoriaIgual = null;
	   cursor.moveToFirst();  
	    while(!cursor.isAfterLast()){  
	    	categoriaIgual = preencherCategoria(cursor);  
	    	cursor.moveToNext();  
	    }

	    if (categoriaIgual != null)
		   achouCategoria = true;
	    
	    cursor.close();  
	    database.close(); 
	    
	    return achouCategoria;
	}
	
	public Boolean UsaEmAlgumaReceita(Categoria categoria){
		Boolean UsaEmReceita = false;  
		
		String[] columns = new String[]{"_id", "descricao"};  
	    String[] args = new String[]{String.valueOf(categoria.getId())};  
	  
	    database = databaseHelper.getWritableDatabase();  
	    Cursor cursor = database.query("receitas", columns,   
	       "codCategoria = ?", args, null, null, "descricao");
	   Categoria categoriaUsada = null;
	   cursor.moveToFirst();  
	    while(!cursor.isAfterLast()){  
	    	categoriaUsada = preencherCategoria(cursor);  
	    	cursor.moveToNext();  
	    }

	    if (categoriaUsada != null)
	    	UsaEmReceita = true;
	    
	    cursor.close();  
	    database.close(); 
	    
	    return UsaEmReceita;
	}
	
	private Categoria preencherCategoria(Cursor cursor) {  
		Categoria categoria = new Categoria();  
		categoria.setId((int)cursor.getLong(0));   
		categoria.setDescricao(cursor.getString(1));  
	    return categoria;  
	}  
}
