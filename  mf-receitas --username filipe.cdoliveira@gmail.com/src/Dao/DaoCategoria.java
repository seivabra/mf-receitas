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
	private String scriptCreate = "create table categorias (_id integer primary key autoincrement, descricao text not null);";
	private ContentValues contentValues;
	public DaoCategoria(Context context){
		databaseHelper = new DatabaseHelper(context, "MfReceitas", 1, scriptCreate);
		
		//Categoria categoria = new Categoria(3, "Jantar");
		//categoria.setDescricao("Jantar");
		//ExcluirCategoria(categoria);
	}
	
	public long InserirCategoria(Categoria categoria){
		contentValues = new ContentValues();  
		contentValues.put("descricao", categoria.getDescricao());  
	    database = databaseHelper.getWritableDatabase();  
	    long id = database.insert("categorias", null, contentValues);  
	    database.close();  
	    return id;  
	}
	
	public int AlterarCategoria(Categoria categoria){
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
		  
	    String[] columns = new String[]{  
	       "_id", "descricao"};  
	    String[] args = new String[]{descricao+"%"};  
	  
	    database = databaseHelper.getWritableDatabase();  
	    Cursor cursor = database.query("carros", columns,   
	       "nome like ?", args, null, null, "nome");
	    
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
	
	private Categoria preencherCategoria(Cursor cursor) {  
		Categoria categoria = new Categoria();  
		categoria.setId((int)cursor.getLong(0));   
		categoria.setDescricao(cursor.getString(1));  
	    return categoria;  
	}  
}
