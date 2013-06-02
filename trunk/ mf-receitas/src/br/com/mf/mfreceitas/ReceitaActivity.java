package br.com.mf.mfreceitas;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import ClassesBasicas.Categoria;
import ClassesBasicas.Marca;
import ClassesBasicas.Produto;
import ClassesBasicas.Unidade;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class ReceitaActivity extends Activity{
	Fachada fachada = new Fachada(this);
	Spinner spinnerProduto;
	Spinner spinnerMarca;
	Spinner spinnerUnidade;
	Spinner spinnerCategoria;
	EditText edtQtdPessoas;
	EditText edtTempoPreparo;
	EditText edtTempoForno;
	EditText edtTempoCongelador;
	EditText edtCustoMedio;
	EditText edtPreco;
	EditText edtQuantidade;
	EditText edtPasso;
	ImageView imgReceita;
	ArrayAdapter<Produto> adapterProduto;
	ArrayAdapter<Marca> adapterMarca;
	ArrayAdapter<Unidade> adapterUnidade;
	ArrayAdapter<Categoria> adapterCategoria;
	
	private Uri fileUri;
	
	public void MapeiaCampos(){
		spinnerProduto = (Spinner)findViewById(R.id.spinnerProduto);
		spinnerMarca = (Spinner)findViewById(R.id.spinnerMarcaProduto);
		spinnerUnidade = (Spinner)findViewById(R.id.spinnerUnidadeProduto);
		spinnerCategoria  = (Spinner)findViewById(R.id.spinnerCategoria);
		edtQtdPessoas = (EditText)findViewById(R.id.edtQtdPessoas);
		edtTempoPreparo = (EditText)findViewById(R.id.edtTempoPreparo);
		edtTempoForno = (EditText)findViewById(R.id.edtTempoForno);
		edtTempoCongelador = (EditText)findViewById(R.id.edtTempoCongelador);
		edtCustoMedio = (EditText)findViewById(R.id.edtCustoMedio);
		edtPreco = (EditText)findViewById(R.id.edtPrecoProduto);
		edtQuantidade = (EditText)findViewById(R.id.edtQtdProduto);
		edtPasso = (EditText)findViewById(R.id.edtPasso);
		imgReceita = (ImageView)findViewById(R.id.imgReceita);
	}
	
	public void btnDiminuirClick(View v) {
		EditText edt = null;
		switch (v.getId()) {  
        case R.id.btnDimQtdPessoas:
        	edt = edtQtdPessoas;
        	break;
        case R.id.btnDimTempoCongelador:
        	edt = edtTempoCongelador;
        	break;
        case R.id.btnDimTempoForno:
        	edt = edtTempoForno;
        	break;
        case R.id.btnDimTempoPreparo:
        	edt = edtTempoPreparo;
        	break;
        case R.id.btnDimQtdProduto:
        	edt = edtQuantidade;
        	break;
		}
		
		if(edt.getText().toString().equals(""))
			edt.setText("1");
		else{
			if (Integer.parseInt(edt.getText().toString()) - 1 > 0)
				edt.setText(String.valueOf(Integer.parseInt(edt.getText().toString()) - 1));
		}
	}
	
	public void btnAdicionarClick(View v) {
		EditText edt = null;
		switch (v.getId()) {  
        case R.id.btnAddQtdPessoas:
        	edt = edtQtdPessoas;
        	break;
        case R.id.btnAddTempoCongelador:
        	edt = edtTempoCongelador;
        	break;
        case R.id.btnAddTempoForno:
        	edt = edtTempoForno;
        	break;
        case R.id.btnAddTempoPreparo:
        	edt = edtTempoPreparo;
        	break;
        case R.id.btnAddQtdProduto:
        	edt = edtQuantidade;
        	break;
		}
		
		if(edt.getText().toString().equals(""))
			edt.setText("1");
		else
			edt.setText(String.valueOf(Integer.parseInt(edt.getText().toString()) + 1));
	}
	
	public void btnIncluirAlterarImagemClick(View v) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		//fileUri = getOutputMediaFileUri(); 
		File image = new  File("/sdcard/image.jpg");
		intent.putExtra(MediaStore.EXTRA_OUTPUT, image);
		startActivityForResult(intent, 1);
	}
	
	private static Uri getOutputMediaFileUri(/*int type*/){
	      //return Uri.fromFile(getOutputMediaFile(type));
		return Uri.fromFile(getOutputMediaFile());
	}

	/** Create a File for saving an image or video */
	private static File getOutputMediaFile(/*int type*/){
	    // To be safe, you should check that the SDCard is mounted
	    // using Environment.getExternalStorageState() before doing this.

	    File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	              Environment.DIRECTORY_PICTURES), "MFReceitas");
	    // This location works best if you want the created images to be shared
	    // between applications and persist after your app has been uninstalled.

	    // Create the storage directory if it does not exist
	    if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	           // Log.d("MyCameraApp", "failed to create directory");
	            return null;
	        }
	    }

	    // Create a media file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    File mediaFile;
	    //if (type == MEDIA_TYPE_IMAGE){
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "IMG_"+ timeStamp + ".jpg");
//	    } else if(type == MEDIA_TYPE_VIDEO) {
//	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
//	        "VID_"+ timeStamp + ".mp4");
//	    } else {
//	        return null;
//	    }

	    return mediaFile;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.incluirreceita);
		MapeiaCampos();
		
		adapterProduto = new ArrayAdapter<Produto>(getBaseContext(), android.R.layout.simple_list_item_1, fachada.ListarProdutos());
		adapterMarca = new ArrayAdapter<Marca>(getBaseContext(), android.R.layout.simple_list_item_1, fachada.ListarMarcas());
		adapterUnidade = new ArrayAdapter<Unidade>(getBaseContext(), android.R.layout.simple_list_item_1, fachada.ListarUnidades());
		adapterCategoria = new ArrayAdapter<Categoria>(getBaseContext(), android.R.layout.simple_list_item_1, fachada.ListarCategorias());
		
		spinnerProduto.setAdapter(adapterProduto);
		spinnerMarca.setAdapter(adapterMarca);
		spinnerUnidade.setAdapter(adapterUnidade);
		//spinnerCategoria.setAdapter(adapterCategoria);
		
		//Verificar se o celular tem camera
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {
	        if (resultCode == RESULT_OK) {
	            // Image captured and saved to fileUri specified in the Intent
	            Toast.makeText(this, "Image saved to:\n" +
	                     data.getData(), Toast.LENGTH_LONG).show();
	            
	            File image = new  File(data.getData() + ".jpg"); 
	            if(image.exists()){
	                     imgReceita.setImageBitmap(BitmapFactory.decodeFile(image.getAbsolutePath()));
	        }		
	        } else if (resultCode == RESULT_CANCELED) {
	            // User cancelled the image capture
	        } else {
	            // Image capture failed, advise user
	        }
	    }
	}
}
