package Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
//	private String scriptCreate;
//	private String scriptDelete;
	
	public DatabaseHelper(Context ctx, String nomeBd, 
		      int versaoBanco/*, String scriptCreate/*, 
		      String scriptDelete*/) {

		      super(ctx, nomeBd, null, versaoBanco);
		      //this.scriptCreate = scriptCreate;
		      //this.scriptDelete = scriptDelete;
		  }

	@Override
	public void onCreate(SQLiteDatabase database) {
		//database.execSQL(scriptCreate);
		database.execSQL("create table categorias (_id integer primary key autoincrement, descricao text not null);");
		database.execSQL("create table marcas (_id integer primary key autoincrement, descricao text not null);");
		database.execSQL("create table produtos (_id integer primary key autoincrement, descricao text not null);");
		database.execSQL("create table unidades (_id integer primary key autoincrement, descricao text not null);");
//		database.execSQL("create table receitas (_id integer primary key autoincrement, descricao text not null " +
//					 	 ", qtdPessoasServe integer, tempoForno integer, tempoCongelador integer, custoMedio double "+
//					 	 ", modoPreparo text, codCategoria integer;");
//		database.execSQL("create table receitas (codReceita integer not null, codProduto integer" +
//					 	 ", codUnidade integer, codMarca integer, quantidade double;");
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		switch (newVersion) {  
        case 5:
        	database.execSQL("create table receitas (_id integer primary key autoincrement, descricao text not null " +
				 	 		 ", qtdPessoasServe integer, tempoForno integer, tempoCongelador integer, custoMedio double "+
				 	 		 ", modoPreparo text, codCategoria integer, medidaTempoForno text, medidaTempoCongelador text, "+
				 	 		 "medidaTempoPreparo text);");
        case 6:
        	database.execSQL("drop table itensReceitas;");
        	database.execSQL("create table itensReceita (codReceita integer not null, codProduto integer" +
        					 ", codUnidade integer, codMarca integer, quantidade integer, preco double);");
        	
//          case R.id.btnAddQtdProduto:
//        	edt = edtQuantidade;
//        	break;
		}	
	}
}
