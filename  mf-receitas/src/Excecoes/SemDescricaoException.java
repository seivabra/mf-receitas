package Excecoes;

@SuppressWarnings("serial")
public class SemDescricaoException extends Exception{
	public SemDescricaoException(){
		super("");
	}
	public SemDescricaoException(String message){
		super(message);
	}
}
