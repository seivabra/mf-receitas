package br.com.mf.mfreceitas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ClassesBasicas.Categoria;
import ClassesBasicas.Item;
import ClassesBasicas.Marca;
import ClassesBasicas.Produto;
import ClassesBasicas.Receita;
import ClassesBasicas.Unidade;
import Excecoes.SemDescricaoException;
import Excecoes.SemIngredientesException;
import Excecoes.SemPassosException;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class IncluirAlterarReceitaActivity extends SherlockActivity{
	Fachada fachada = new Fachada(this);
	Spinner spinnerProduto;
	Spinner spinnerMarca;
	Spinner spinnerUnidade;
	Spinner spinnerCategoria;
	Spinner spinnerForno;
	Spinner spinnerCongelador;
	Spinner spinnerPreparo;
	EditText edtDescricao;
	EditText edtQtdPessoas;
	EditText edtTempoPreparo;
	EditText edtTempoForno;
	EditText edtTempoCongelador;
	EditText edtCustoMedio;
	EditText edtPreco;
	EditText edtQuantidade;
	EditText edtPasso;
	ImageView imgReceita;
	ListView listItens;
	ListView listPassos;
	ArrayAdapter<Produto> adapterProduto;
	ArrayAdapter<Marca> adapterMarca;
	ArrayAdapter<Unidade> adapterUnidade;
	ArrayAdapter<Categoria> adapterCategoria;
	ArrayAdapter<Item> adapterItem;
	ArrayAdapter<String> adapterPassos;
	ArrayAdapter<String> adapterTempo;
	ArrayList<Item> listaItens = new ArrayList<Item>();
	ArrayList<String> listaPassos = new ArrayList<String>();
	ArrayList<String> listaTempo = new ArrayList<String>();
	String arquivo;
	Receita receita;
	
	private Uri fileUri;
	
	public void MapeiaCampos(){
		spinnerProduto = (Spinner)findViewById(R.id.spinnerProduto);
		spinnerMarca = (Spinner)findViewById(R.id.spinnerMarcaProduto);
		spinnerUnidade = (Spinner)findViewById(R.id.spinnerUnidadeProduto);
		spinnerForno  = (Spinner)findViewById(R.id.spinnerTempoForno);
		spinnerCongelador = (Spinner)findViewById(R.id.spinnerTempoCongelador);
		spinnerPreparo = (Spinner)findViewById(R.id.spinnerTempoPreparo);
		spinnerCategoria  = (Spinner)findViewById(R.id.spinnerCategoria);
		edtDescricao = (EditText)findViewById(R.id.edtDescricao);
		edtQtdPessoas = (EditText)findViewById(R.id.edtQtdPessoas);
		edtTempoPreparo = (EditText)findViewById(R.id.edtTempoPreparo);
		edtTempoForno = (EditText)findViewById(R.id.edtTempoForno);
		edtTempoCongelador = (EditText)findViewById(R.id.edtTempoCongelador);
		edtCustoMedio = (EditText)findViewById(R.id.edtCustoMedio);
		edtPreco = (EditText)findViewById(R.id.edtPrecoProduto);
		edtQuantidade = (EditText)findViewById(R.id.edtQtdProduto);
		edtPasso = (EditText)findViewById(R.id.edtPasso);
		imgReceita = (ImageView)findViewById(R.id.imgReceita);
		listItens = (ListView)findViewById(R.id.listItens);
		listPassos = (ListView)findViewById(R.id.listPassos);
	}
	
	public void LimparIngrediente() {
		spinnerProduto.setSelection(0);
		spinnerUnidade.setSelection(0);
		spinnerMarca.setSelection(0);
		edtPreco.setText("0");
		edtQuantidade.setText("0");
	}
	
	public void LimparReceita() {
		LimparIngrediente();
		edtDescricao.setText("");
		edtQtdPessoas.setText("");
		edtTempoForno.setText("");
		edtTempoCongelador.setText("");
		edtTempoPreparo.setText("");
		edtCustoMedio.setText("");
		spinnerCategoria.setSelection(0);
		spinnerForno.setSelection(0);
		spinnerCongelador.setSelection(0);
		spinnerPreparo.setSelection(0);
		edtPasso.setText("");
		listaItens.clear();
		listItens.setAdapter(adapterItem);
		listaPassos.clear();
		listPassos.setAdapter(adapterPassos);
	}
	
	public void SalvarReceita() {
		String passos = "";
		Receita receita = new Receita();
		receita.setDescricao(edtDescricao.getText().toString().trim());
		receita.setCategoria(adapterCategoria.getItem(spinnerCategoria.getSelectedItemPosition()));
		receita.setQtdPessoasServe(StrToIntDef(edtQtdPessoas.getText().toString(), 0));
		receita.setCustoMedio(StrToDoubleDef(edtCustoMedio.getText().toString(), 0.0));
		receita.setTempoForno(StrToIntDef(edtTempoForno.getText().toString(), 0));
		receita.setTempoCongelador(StrToIntDef(edtTempoCongelador.getText().toString(), 0));
		receita.setTempoPreparo(StrToIntDef(edtTempoPreparo.getText().toString(), 0));
		receita.setMedidaTempoForno((String)spinnerForno.getSelectedItem());
		receita.setMedidaTempoCongelador((String)spinnerCongelador.getSelectedItem());
		receita.setMedidaTempoPreparo((String)spinnerPreparo.getSelectedItem());
		receita.setItens(listaItens);
		receita.setCaminhoImagem(arquivo);
		
		for (int i = 0; i < listaPassos.size(); i++) {
			passos = passos + listaPassos.get(i);
			if(i < listaPassos.size() - 1)
				passos = passos + "|";
		}
		
		receita.setModoPreparo(passos);
		
		try {
			fachada.InserirReceita(receita);
			LimparReceita();
			finish();
		} catch (SemDescricaoException e) {
			Toast.makeText(getBaseContext(), R.string.msgDescricaoEmBranco, Toast.LENGTH_SHORT).show();
		} catch (SemIngredientesException e) {
			Toast.makeText(getBaseContext(), R.string.msgListaIngredientesVazia, Toast.LENGTH_SHORT).show();
		} catch (SemPassosException e) {
			Toast.makeText(getBaseContext(), R.string.msgListaPassosVazia, Toast.LENGTH_SHORT).show();
		}
	}
	
	public int StrToIntDef(String numero, int Ndefault){
		int retorno = 0;
		try{
			retorno = Integer.parseInt(numero);
		} catch (Exception e){
			retorno = Ndefault;
		}
		return retorno;
	}
	
	public Double StrToDoubleDef(String numero, Double Ndefault){
		Double retorno = 0.0;
		try{
			retorno = Double.parseDouble(numero);
		} catch (Exception e){
			retorno = Ndefault;
		}
		return retorno;
	}
	
	public static void copy(String fOrigem, String fDestino) throws IOException { 

		FileChannel fcOrigem = new FileInputStream(fOrigem).getChannel(); 
		FileChannel fcDestino = new FileOutputStream(fDestino).getChannel(); 

		fcOrigem.transferTo(0, fcOrigem.size(), fcDestino); 

		fcDestino.close(); 
		fcOrigem.close(); 

	}
	
	private void showOptions(){
		final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.opcoescarregarimagem);
        dialog.setTitle("Escolher uma ação");
        Button btnTirarFoto = (Button) dialog.findViewById(R.id.btnTirarFoto);
        Button btnAbrirGaleria = (Button) dialog.findViewById(R.id.btnAbrirGaleria);
        
        
        btnTirarFoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
    				
					Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
					arquivo = Environment.getExternalStorageDirectory() + "/MFReceitas/" + System.currentTimeMillis() + ".jpg";
					File file = new File(arquivo);
					Uri outputFileUri = Uri.fromFile(file);
					intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
					startActivityForResult(intent, 1);
				}else
					Toast.makeText(getBaseContext(), "O cartão de memória não está acessível", Toast.LENGTH_SHORT).show();
            	
            	dialog.dismiss();
            }
        });
        
        btnAbrirGaleria.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),2);
                dialog.dismiss();
			}
		});
        
        
    dialog.show();
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
			showOptions();
	}
	
	public void btnAdicionarProdutoClick(View v){
		Item item = new Item();
		item.setProduto(adapterProduto.getItem(spinnerProduto.getSelectedItemPosition()));
		item.setMarca(adapterMarca.getItem(spinnerMarca.getSelectedItemPosition()));
		item.setUnidade(adapterUnidade.getItem(spinnerUnidade.getSelectedItemPosition()));
		item.setQuantidade(StrToIntDef(edtQuantidade.getText().toString(), 0));
		item.setPreco(StrToDoubleDef(edtPreco.getText().toString(), 0.0));
		if(item.getQuantidade() > 0){
			listaItens.add(item);
			listItens.setAdapter(adapterItem);
			edtCustoMedio.setText(String.valueOf(StrToDoubleDef(edtCustoMedio.getText().toString(), 0.0) + item.getPreco()));
			LimparIngrediente();
		}else
			Toast.makeText(getBaseContext(), R.string.qtdMaiorZero, Toast.LENGTH_LONG).show();
	}
	
	public void btnAdicionarPassoClick(View v) {
		if (!edtPasso.getText().toString().trim().equals("")){
			listaPassos.add(edtPasso.getText().toString());
			listPassos.setAdapter(adapterPassos);
			edtPasso.setText("");
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.incluirreceita);
		MapeiaCampos();
		
		if (savedInstanceState != null){
			listaItens = (ArrayList<Item>)savedInstanceState.getSerializable("arrayItens");
			adapterItem = new ArrayAdapter<Item>(getBaseContext(), android.R.layout.simple_list_item_1, listaItens);
			listItens.setAdapter(adapterItem);
			
			listaPassos = (ArrayList<String>)savedInstanceState.getStringArrayList("arrayPassos");
			adapterPassos = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, listaPassos);
			listPassos.setAdapter(adapterPassos);
		}
		listaTempo.clear();
		listaTempo.add("h");
		listaTempo.add("min");
		
		adapterProduto = new ArrayAdapter<Produto>(getBaseContext(), android.R.layout.simple_list_item_1, fachada.ListarProdutos());
		adapterMarca = new ArrayAdapter<Marca>(getBaseContext(), android.R.layout.simple_list_item_1, fachada.ListarMarcas());
		adapterUnidade = new ArrayAdapter<Unidade>(getBaseContext(), android.R.layout.simple_list_item_1, fachada.ListarUnidades());
		adapterCategoria = new ArrayAdapter<Categoria>(getBaseContext(), android.R.layout.simple_list_item_1, fachada.ListarCategorias());
		adapterItem = new ArrayAdapter<Item>(getBaseContext(), android.R.layout.simple_list_item_1, listaItens);
		adapterPassos = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, listaPassos);
		adapterTempo = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, listaTempo);
		
		spinnerProduto.setAdapter(adapterProduto);
		spinnerMarca.setAdapter(adapterMarca);
		spinnerUnidade.setAdapter(adapterUnidade);
		spinnerCategoria.setAdapter(adapterCategoria);
		spinnerForno.setAdapter(adapterTempo);
		spinnerCongelador.setAdapter(adapterTempo);
		spinnerPreparo.setAdapter(adapterTempo);
		listItens.setAdapter(adapterItem);
		listPassos.setAdapter(adapterPassos);
		
		registerForContextMenu(listItens);
		registerForContextMenu(listPassos); 
		
		receita = (Receita)getIntent().getSerializableExtra("receita");
		if (receita != null){
			Bitmap yourSelectedImage = BitmapFactory.decodeFile(receita.getCaminhoImagem());  
            
            imgReceita.setImageBitmap(yourSelectedImage);
		}
		listItens.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				Item item = adapterItem.getItem(index);
				spinnerProduto.setSelection(adapterProduto.getPosition(item.getProduto()));
				spinnerMarca.setSelection(adapterMarca.getPosition(item.getMarca()));
				spinnerUnidade.setSelection(adapterUnidade.getPosition(item.getUnidade()));
				edtQuantidade.setText(String.valueOf(item.getQuantidade()));
				edtPreco.setText(String.valueOf(item.getPreco()));
				
			}
		});
		
		//Verificar se o celular tem camera
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
//		if ((requestCode == 1) && (resultCode == RESULT_OK)){
//			if(data != null){
//				Bundle bundle = data.getExtras();
//				if(bundle != null){
//					Bitmap bitmap = (Bitmap)bundle.get("data");
//					imgReceita.setImageBitmap(bitmap);
//					//imgImagem.setImageBitmap(arquivo);
//				}
//			}
//	    }
		
		if((requestCode == 1) && (resultCode == RESULT_OK)){
			if(data != null){
				Bundle bundle = data.getExtras();
				if(bundle != null){
					Bitmap bitmap = (Bitmap)bundle.get("data");
					imgReceita.setImageBitmap(bitmap);
					//imgImagem.setImageBitmap(arquivo);
				}
			}
		}else if((requestCode == 2) && (resultCode == RESULT_OK)){

            
            Uri selectedImage = data.getData();  
            String[] filePathColumn = {MediaStore.Images.Media.DATA };  
            
            Cursor cursor = getContentResolver().query(  
              selectedImage, filePathColumn, null, null, null);  
            cursor.moveToFirst();  
            
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);  
            String filePath = cursor.getString(columnIndex);   
            cursor.close();  
            arquivo = filePath;
            Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);  
            
            imgReceita.setImageBitmap(yourSelectedImage);
            
            String origem = filePath;
            String destino = Environment.getExternalStorageDirectory() + "/MFReceitas/" + System.currentTimeMillis() + ".jpg";
            try {
				copy(origem, destino);
			} catch (IOException e) {
				Toast.makeText(getBaseContext(), "Erro ao tentar salvar a imagem no local de destiono.", Toast.LENGTH_SHORT).show();
			}
       }
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		
		outState.putSerializable("arrayItens", listaItens);
		outState.putStringArrayList("arrayPassos", listaPassos);
		super.onSaveInstanceState(outState);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		if (v.getId() == R.id.listItens)
			getMenuInflater().inflate(R.menu.context_menu_excluir_item, menu);
		else if (v.getId() == R.id.listPassos)
			getMenuInflater().inflate(R.menu.context_menu_excluir_passo, menu); 
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	
	
	@Override
	public boolean onContextItemSelected(android.view.MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();  
		
		
		switch (item.getItemId()) {  
        case R.id.opExcluirItem:
        	listaItens.remove(info.position);
        	listItens.setAdapter(adapterItem);
        	break;
        case R.id.opExcluirPasso:
        	listaPassos.remove(info.position);
        	listPassos.setAdapter(adapterPassos);
        	break;
		}
		return super.onContextItemSelected(item);
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.salvarreceita, menu);
		return super.onCreateOptionsMenu(menu);
    }
	
	@Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
    	if (item.getItemId() == R.id.act_SalvarReceita){
    		SalvarReceita();
    	}
    	return super.onMenuItemSelected(featureId, item);
    }
}
