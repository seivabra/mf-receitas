package br.com.mf.mfreceitas;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.Marshal;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import ClassesBasicas.Item;
import ClassesBasicas.Marca;
import ClassesBasicas.Produto;
import ClassesBasicas.Receita;
import ClassesBasicas.Unidade;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class ReceitasAsyncTask extends AsyncTask<Receita, Void, Void> {

	private ProgressDialog progressDialog;
	private Context ctx;
	
	private final String SOAP_ADDRESS = "http://carsoft.no-ip.info//wsmfreceitas/MFReceitasWS.asmx";
	private final String NAMESPACE = "http://tempuri.org/";
	private final String METODOWS = "SalvarReceita";

	public ReceitasAsyncTask(Context ctx) {
		this.ctx = ctx;
	}
	@Override
	protected Void doInBackground(Receita... receita) {
		SalvarReceita(receita[0]);
		return null;
	}

	public String imageToByte(String imagefile) throws IOException {      
	      
//		InputStream fileIn = null;
//	    fileIn = new BufferedInputStrream(newFileInputStream(imagefile));
//	    byte [] InBuf = new byte[1024];
//	    fileIn.read(InBuf);
		imagefile = Environment.getExternalStorageDirectory().getAbsolutePath()+"/MFReceitas/1370453303436.jpg";
		byte[] data = new byte[(int) imagefile.length()];
		File file = new File(imagefile);
		FileInputStream fis = new FileInputStream(file);
		fis.read(data);
		fis.close();
		String imagem = new String(data);
		
		return imagem;
	}
	
	public void SalvarReceita(Receita receita) {
			
		StringArraySerializer lReceita = new StringArraySerializer();
		
		try {
			String[] caminhoImg = receita.getCaminhoImagem().split("/");
			String caminho = caminhoImg[caminhoImg.length - 2] + "/" + caminhoImg[caminhoImg.length-1];
			byte[] teste = imageToByte(caminho);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		lReceita.addAll(Arrays.asList(String.valueOf(receita.getId()),String.valueOf(receita.getDescricao()),
						String.valueOf(receita.getCategoria().getId()),String.valueOf(receita.getTempoPreparo()),
						String.valueOf(receita.getTempoForno()),String.valueOf(receita.getTempoCongelador()),
						receita.getMedidaTempoPreparo(),receita.getMedidaTempoForno(),receita.getMedidaTempoCongelador(), "50", 
						receita.getModoPreparo()));
		
		SoapObject request = new SoapObject(NAMESPACE, METODOWS);
		request.addProperty("receita", (List<String>)lReceita);
				
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);

		
		HttpTransportSE transport = new HttpTransportSE(SOAP_ADDRESS);
		
		try{
			
			transport.debug = true;
			transport.call(NAMESPACE + METODOWS, envelope);
			Log.i("RCT","body out: " + envelope.bodyOut);
			Log.i("RCT","body in: " + envelope.bodyIn );
			Log.i("RCT","response: " + envelope.getResponse() );
			
		}catch(IOException e){
			e.printStackTrace();
		}catch (XmlPullParserException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void onPreExecute() {
		progressDialog = new ProgressDialog(ctx);

		progressDialog.setMessage("Aguarde...");
		progressDialog.show();
	}

	protected void onPostExecute(String result) {
		progressDialog.dismiss();
	}
	
	public class StringArraySerializer extends Vector<String> implements KvmSerializable {

	        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
        public Object getProperty(int arg0) {
                return this.get(arg0);
        }

        @Override
        public int getPropertyCount() {
                return this.size();
        }

        @Override
        public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
        	info.type = PropertyInfo.STRING_CLASS;
            info.name = "string";	                
        }

        @Override
        public void setProperty(int arg0, Object arg1) {
                this.add(arg1.toString());
        }

	}


}
