package Excecoes;

@SuppressWarnings("serial")
public class SemIngredientesException extends Exception{
	public SemIngredientesException(){
		super("");
	}
	public SemIngredientesException(String message){
		super(message);
	}
}
