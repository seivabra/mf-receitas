package Negocios;

import ClassesBasicas.Usuario;
import Dao.DaoUsuario;

public class NegociosUsuario {
	DaoUsuario daoUsuario = new DaoUsuario();
	
	public int InserirUsuario(Usuario usuario){
		return daoUsuario.InserirUsuario(usuario);
	}
	
	public int AlterarUsuario(Usuario usuario){
		return daoUsuario.AlterarUsuario(usuario);
	}
	
	public int ExcluirUsuario(Usuario usuario){
		return daoUsuario.ExcluirUsuario(usuario);
	}
}
