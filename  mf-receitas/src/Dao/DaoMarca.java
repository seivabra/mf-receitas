package Dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import ClassesBasicas.Marca;

public class DaoMarca {
	
	private SQLiteDatabase database;  
    private DatabaseHelper databaseHelper; 
	private String scriptCreate = "create table marcas (_id integer primary key autoincrement, descricao text not null);";
	private ContentValues contentValues;
	public DaoMarca(Context context){
		databaseHelper = new DatabaseHelper(context, "MfReceitas", 1, scriptCreate);
	}
	
	public long InserirMarca(Marca marca){
		contentValues = new ContentValues();  
		contentValues.put("descricao", marca.getDescricao()); 
		database = databaseHelper.getWritableDatabase();  
	    long id = database.insert("marcas", null, contentValues);  
	    database.close();  
	    return id;  
	}
	
	public int AlterarMarca(Marca marca){
		contentValues = new ContentValues();  
		contentValues.put("descricao", marca.getDescricao());  
		
		database = databaseHelper.getWritableDatabase();  
	    int rows = database.update("marcas", contentValues, "_id = ?",   
	       new String[]{ String.valueOf(marca.getId())});  
	    database.close();  
	    return rows;
	}
	
	public int ExcluirMarca(Marca marca){
		database = databaseHelper.getWritableDatabase();  
	    int rows = database.delete("marcas", "_id = ?",   
	      new String[]{ String.valueOf(marca.getId()) });  
	    return rows; 
	}
	
	public ArrayList<Marca> ListarMarcas(){
		ArrayList<Marca> listaMarcas = new ArrayList<Marca>();  
		  
	    database = databaseHelper.getWritableDatabase();  
	    
	    Cursor cursor = database.rawQuery("select _id, descricao from marcas", null);
	    
	    cursor.moveToFirst();  
	    while(!cursor.isAfterLast()){  
	    	Marca marca = preencherMarca(cursor);  
	    	listaMarcas.add(marca);  
	    	cursor.moveToNext();  
	    }  
	    cursor.close();  
	    database.close();  
	    return listaMarcas;  
	}
	
	public ArrayList<Marca> PesquisarMarcaDescricao(String descricao){
		ArrayList<Marca> listaMarcas = new ArrayList<Marca>();  
		  
		//Upper
		
	    String[] columns = new String[]{  
	       "_id", "descricao"};  
	    String[] args = new String[]{descricao+"%"};  
	  
	    database = databaseHelper.getWritableDatabase();  
	    Cursor cursor = database.query("marcas", columns,   
	       "descricao like ?", args, null, null, "descricao");
	    
	   cursor.moveToFirst();  
	    while(!cursor.isAfterLast()){  
	    	Marca marca = preencherMarca(cursor);  
	    	listaMarcas.add(marca);  
	    	cursor.moveToNext();  
	    }  
	    cursor.close();  
	    database.close();  
	    return listaMarcas;
	}
	
	public Boolean AchouMarcaIgual(Marca marca){
		Boolean achouMarca = false;  
		
		String[] columns = new String[]{"_id", "descricao"};  
	    String[] args = new String[]{marca.getDescricao(), String.valueOf(marca.getId())};//+"%"};  
	  
	    database = databaseHelper.getWritableDatabase();  
	    Cursor cursor = database.query("marcas", columns,   
	       "upper(descricao) = upper(?) and _id <> ?", args, null, null, "descricao");
	   Marca marcaIgual = null;
	   cursor.moveToFirst();  
	    while(!cursor.isAfterLast()){  
	    	marcaIgual = preencherMarca(cursor);  
	    	cursor.moveToNext();  
	    }

	    if (marcaIgual != null)
	    	achouMarca = true;
	    
	    cursor.close();  
	    database.close(); 
	    
	    return achouMarca;
	}
	
	private Marca preencherMarca(Cursor cursor) {  
		Marca marca = new Marca();  
		marca.setId((int)cursor.getLong(0));   
		marca.setDescricao(cursor.getString(1));  
	    return marca;  
	}  
}
