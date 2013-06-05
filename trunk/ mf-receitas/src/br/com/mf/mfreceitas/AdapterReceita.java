package br.com.mf.mfreceitas;

import java.util.ArrayList;

import ClassesBasicas.Receita;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterReceita extends BaseAdapter{

	private ArrayList<Receita> receitas;  
    private LayoutInflater inflater;
	   
    public AdapterReceita(Context context, ArrayList<Receita> receitas) {  
      this.receitas = receitas;  
	  inflater = LayoutInflater.from(context);
   }  
	
	@Override
	public int getCount() {
		
		return receitas.size();
	}

	@Override
	public Object getItem(int index) {
		return receitas.get(index);  
	}

	@Override
	public long getItemId(int index) {
		return (long)receitas.get(index).getId();
	}

	@Override
	public View getView(int index, View convertView, ViewGroup parent) {
		Receita receita = receitas.get(index);  
		  
	      View view = inflater.inflate(R.layout.linhaadapterreceita, null);  
	      ImageView imgReceita = (ImageView)view.findViewById(R.id.imgReceita);
	      TextView lblDescReceita = (TextView) view.findViewById(R.id.lblDescReceita);  
	      
	      Bitmap yourSelectedImage = BitmapFactory.decodeFile(receita.getCaminhoImagem());  
          
          imgReceita.setImageBitmap(yourSelectedImage);
	      
	      lblDescReceita.setText(receita.getDescricao());  
	      
	      return view;  
	}

}
