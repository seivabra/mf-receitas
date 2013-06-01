package br.com.mf.mfreceitas;

import java.util.ArrayList;

import android.content.Context;

import ClassesBasicas.Categoria;
import ClassesBasicas.ListaCompras;
import ClassesBasicas.Marca;
import ClassesBasicas.Produto;
import ClassesBasicas.Receita;
import ClassesBasicas.Unidade;
import ClassesBasicas.Usuario;
import Negocios.NegociosCategoria;
import Negocios.NegociosListaCompras;
import Negocios.NegociosMarca;
import Negocios.NegociosProduto;
import Negocios.NegociosReceita;
import Negocios.NegociosUnidade;
import Negocios.NegociosUsuario;

public class Fachada {
	
	NegociosCategoria negociosCategoria;
	NegociosListaCompras negocioListaCompras;
	NegociosMarca negociosMarca;
	NegociosProduto negociosProduto;
	NegociosReceita negociosReceita;
	NegociosUnidade negociosUnidade;
	NegociosUsuario negociosUsuario;
	
	public Fachada(Context context){
		negociosCategoria = new NegociosCategoria(context);
		negocioListaCompras = new NegociosListaCompras();
		negociosMarca = new NegociosMarca(context);
		negociosProduto = new NegociosProduto(context);
		negociosReceita = new NegociosReceita();
		negociosUnidade = new NegociosUnidade(context);
		negociosUsuario = new NegociosUsuario();
	}
	
	public long InserirCategoria(Categoria categoria) throws Exception{
		return negociosCategoria.InserirCategoria(categoria);
	}
	
	public long AlterarCategoria(Categoria categoria){
		return negociosCategoria.AlterarCategoria(categoria);
	}
	
	public int ExcluirCategoria(Categoria categoria){
		return negociosCategoria.ExcluirCategoria(categoria);
	}
	
	public ArrayList<Categoria> ListarCategorias(){
		return negociosCategoria.ListarCategorias();
	}
	
	public ArrayList<Categoria> PesquisarCategoriaDescricao(String descricao){
		return negociosCategoria.PesquisarCategoriaDescricao(descricao);
	}
	
	public int InserirListaCompras(ListaCompras listaCompras){
		return negocioListaCompras.InserirListaCompras(listaCompras);
	}
	
	public int AlterarListaCompras(ListaCompras listaCompras){
		return negocioListaCompras.AlterarListaCompras(listaCompras);
	}
	
	public int ExcluirListaCompras(ListaCompras listaCompras){
		return negocioListaCompras.ExcluirListaCompras(listaCompras);
	}
	
	public ArrayList<ListaCompras> ListarListaCompras(){
		return negocioListaCompras.ListarListaCompras();
	}
	
	public ArrayList<ListaCompras> PesquisarListaComprasDescricao(String descricao){
		return negocioListaCompras.PesquisarListaComprasDescricao(descricao);
	}
	
	public long InserirMarca(Marca marca){
		return negociosMarca.InserirMarca(marca);
	}
	
	public int AlterarMarca(Marca marca){
		return negociosMarca.AlterarMarca(marca);
	}
	
	public int ExcluirMarca(Marca marca){
		return negociosMarca.ExcluirMarca(marca);
	}
	
	public ArrayList<Marca> ListarMarcas(){
		return negociosMarca.ListarMarcas();
	}
	
	public ArrayList<Marca> PesquisarMarcaDescricao(String descricao){
		return negociosMarca.PesquisarMarcaDescricao(descricao);
	}
	
	public long InserirProduto(Produto listaCompras){
		return negociosProduto.InserirProduto(listaCompras);
	}
	
	public int AlterarProduto(Produto listaCompras){
		return negociosProduto.AlterarProduto(listaCompras);
	}
	
	public int ExcluirProduto(Produto listaCompras){
		return negociosProduto.ExcluirProduto(listaCompras);
	}
	
	public ArrayList<Produto> ListarProdutos(){
		return negociosProduto.ListarProdutos();
	}
	
	public ArrayList<Produto> PesquisarProdutoDescricao(String descricao){
		return negociosProduto.PesquisarProdutoDescricao(descricao);
	}
	
	public int InserirReceita(Receita listaCompras){
		return negociosReceita.InserirReceita(listaCompras);
	}
	
	public int AlterarReceita(Receita listaCompras){
		return negociosReceita.AlterarReceita(listaCompras);
	}
	
	public int ExcluirReceita(Receita listaCompras){
		return negociosReceita.ExcluirReceita(listaCompras);
	}
	
	public ArrayList<Receita> ListarReceitas(){
		return negociosReceita.ListarReceitas();
	}
	
	public ArrayList<Receita> PesquisarReceitaDescricao(String descricao){
		return negociosReceita.PesquisarReceitaDescricao(descricao);
	}
	
	public long InserirUnidade(Unidade unidade){
		return negociosUnidade.InserirUnidade(unidade);
	}
	
	public int AlterarUnidade(Unidade unidade){
		return negociosUnidade.AlterarUnidade(unidade);
	}
	
	public int ExcluirUnidade(Unidade unidade){
		return negociosUnidade.ExcluirUnidade(unidade);
	}
	
	public ArrayList<Unidade> ListarUnidades(){
		return negociosUnidade.ListarUnidades();
	}
	
	public ArrayList<Unidade> PesquisarUnidadeDescricao(String descricao){
		return negociosUnidade.PesquisarUnidadeDescricao(descricao);
	}
	
	public int InserirUsuario(Usuario usuario){
		return negociosUsuario.InserirUsuario(usuario);
	}
	
	public int AlterarUsuario(Usuario usuario){
		return negociosUsuario.AlterarUsuario(usuario);
	}
	
	public int ExcluirUsuario(Usuario usuario){
		return negociosUsuario.ExcluirUsuario(usuario);
	}
	
	public Boolean AchouCategoriaIgual(Categoria categoria){
		return negociosCategoria.AchouCategoriaIgual(categoria);
	}
	
	public Boolean AchouMarcaIgual(Marca marca){
		return negociosMarca.AchouMarcaIgual(marca);
	}
	
	public Boolean AchouProdutoIgual(Produto produto){
		return negociosProduto.AchouProdutoIgual(produto);
	}
	
	public Boolean AchouUnidadeIgual(Unidade unidade){
		return negociosUnidade.AchouUnidadeIgual(unidade);
	}
}
