package ClassesBasicas;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Usuario implements Serializable{
	int id;
	String login;
	String senha;
	public Usuario() {
		super();
	}
	public Usuario(int id, String login, String senha) {
		super();
		this.id = id;
		this.login = login;
		this.senha = senha;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
