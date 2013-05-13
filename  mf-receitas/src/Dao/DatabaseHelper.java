package Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	private String scriptCreate;
//	private String scriptDelete;
	
	public DatabaseHelper(Context ctx, String nomeBd, 
		      int versaoBanco, String scriptCreate/*, 
		      String scriptDelete*/) {

		      super(ctx, nomeBd, null, versaoBanco);
		      this.scriptCreate = scriptCreate;
		      //this.scriptDelete = scriptDelete;
		  }

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(scriptCreate);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
}
