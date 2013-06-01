package Dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import ClassesBasicas.Produto;

public class DaoProduto {

	private SQLiteDatabase database;  
    private DatabaseHelper databaseHelper; 
	private String scriptCreate = "create table produtos (_id integer primary key autoincrement, descricao text not null);";
	private ContentValues contentValues;
	
	public DaoProduto(Context context){
		databaseHelper = new DatabaseHelper(context, "MfReceitas", 1, scriptCreate);
	}
	
	public long InserirProduto(Produto produto){
		contentValues = new ContentValues();  
		contentValues.put("descricao", produto.getDescricao()); 
		database = databaseHelper.getWritableDatabase();  
	    long id = database.insert("produtos", null, contentValues);  
	    database.close();  
	    return id;  
	}
	
	public int AlterarProduto(Produto produto){
		contentValues = new ContentValues();  
		contentValues.put("descricao", produto.getDescricao());  
		
		database = databaseHelper.getWritableDatabase();  
	    int rows = database.update("produtos", contentValues, "_id = ?",   
	       new String[]{ String.valueOf(produto.getId())});  
	    database.close();  
	    return rows;
	}
	
	public int ExcluirProduto(Produto produto){
		database = databaseHelper.getWritableDatabase();  
	    int rows = database.delete("produtos", "_id = ?",   
	      new String[]{ String.valueOf(produto.getId()) });  
	    return rows; 
	}
	
	public ArrayList<Produto> ListarProdutos(){
		ArrayList<Produto> listaProdutos = new ArrayList<Produto>();  
		  
	    database = databaseHelper.getWritableDatabase();  
	    
	    Cursor cursor = database.rawQuery("select _id, descricao from produtos", null);
	    
	    cursor.moveToFirst();  
	    while(!cursor.isAfterLast()){  
	    	Produto produto = preencherProduto(cursor);  
	    	listaProdutos.add(produto);  
	    	cursor.moveToNext();  
	    }  
	    cursor.close();  
	    database.close();  
	    return listaProdutos;  
	}
	
	public ArrayList<Produto> PesquisarProdutoDescricao(String descricao){
		ArrayList<Produto> listaProdutos = new ArrayList<Produto>();  
		  
		//Upper
		
	    String[] columns = new String[]{  
	       "_id", "descricao"};  
	    String[] args = new String[]{descricao+"%"};  
	  
	    database = databaseHelper.getWritableDatabase();  
	    Cursor cursor = database.query("produtos", columns,   
	       "descricao like ?", args, null, null, "descricao");
	    
	   cursor.moveToFirst();  
	    while(!cursor.isAfterLast()){  
	    	Produto produto = preencherProduto(cursor);  
	    	listaProdutos.add(produto);  
	    	cursor.moveToNext();  
	    }  
	    cursor.close();  
	    database.close();  
	    return listaProdutos;
	}
	
	public Boolean AchouProdutoIgual(Produto produto){
Boolean achouProduto = false;  
		
		String[] columns = new String[]{"_id", "descricao"};  
	    String[] args = new String[]{produto.getDescricao(), String.valueOf(produto.getId())};//+"%"};  
	  
	    database = databaseHelper.getWritableDatabase();  
	    Cursor cursor = database.query("produtos", columns,   
	       "upper(descricao) = upper(?) and _id <> ?", args, null, null, "descricao");
	   Produto produtoIgual = null;
	   cursor.moveToFirst();  
	    while(!cursor.isAfterLast()){  
	    	produtoIgual = preencherProduto(cursor);  
	    	cursor.moveToNext();  
	    }

	    if (produtoIgual != null)
		   achouProduto = true;
	    
	    cursor.close();  
	    database.close(); 
	    
	    return achouProduto;
	}
	
	private Produto preencherProduto(Cursor cursor) {  
		Produto produto = new Produto();  
		produto.setId((int)cursor.getLong(0));   
		produto.setDescricao(cursor.getString(1));  
	    return produto;  
	}  
}
