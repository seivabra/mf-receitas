package Excecoes;

@SuppressWarnings("serial")
public class SemPassosException extends Exception{
	public SemPassosException(){
		super("");
	}
	public SemPassosException(String message){
		super(message);
	}
}