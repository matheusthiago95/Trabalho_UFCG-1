package main.controller;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import main.elementos.Item;
import main.elementos.Usuario;
import main.elementos.ordenacao.ItemOrdenacaoDescricao;
import main.elementos.ordenacao.ItemOrdenacaoValor;
import main.exception.DadoInvalido;
import main.exception.OperacaoNaoPermitida;
import main.repository.UsuarioRepository;

public class SistemaController {
	
	private UsuarioRepository repository;
	private Usuario usuario;
	private static final String ATRIBUTO_EMAIL = "email";
	private static final String USUARIO_INVALIDO = "Usuario invalido";
	private static final String USUARIO_CASASTRADO = "Usuario ja cadastrado";
	private static final String ITEM_JA_EMPRESTADO = "Item emprestado no momento";
	private static final String ITEM_NAO_ENCONTRADO = "Item nao encontrado";
	private static final String EMPRESTIMO_NAO_ENCONTRADO = "Emprestimo nao encontrado";

	public SistemaController() {
		repository = new UsuarioRepository();
	}

	/**
	 * Adiciona um {@link Usuario} em um sistema de usuarios
	 * 
	 * @param nome Nome do usuario
	 * @param telefone Telefone do usuario
	 * @param email Email do usuario
	 * @throws Exception  Verifica se ja existe um usuario no sistema caso verdade ,
	 *  lanca um Exception
	 */
	public void adicionar(String nome, String telefone, String email) throws Exception {
		if (repository.recuperar(nome, telefone) != null) {
			throw new OperacaoNaoPermitida(USUARIO_CASASTRADO);
		}
		usuario = new Usuario(nome, email, telefone);
		repository.adiciona(usuario);
	}

	/**
	 * Metodo que da uma informacao de um {@link Usuario}
	 * 
	 * @param nome
	 *            Nome do usuario
	 * @param telefone
	 *            Telefone do usuario
	 * @param atributo
	 *            Atributo a ser seguido
	 * @return
	 * @throws Exception
	 *             caso o usuario nao exista
	 */
	public String getInfoUsuario(String nome, String telefone, String atributo) throws Exception {
		usuario = repository.recuperar(nome, telefone);
		if (usuario == null) {
			throw new DadoInvalido(USUARIO_INVALIDO);
		}
		if (ATRIBUTO_EMAIL.equalsIgnoreCase(atributo)) {
			return usuario.getEmail();
		}
		return null;
	}

	/**
	 * Remove um usuario do sistema
	 * 
	 * @param nome
	 *            Nome do usuario
	 * @param telefone
	 *            Telefone do usuario
	 * @throws Exception
	 *             caso o usuario nao exista
	 */
	public void removerUsuario(String nome, String telefone) throws Exception {
		if (repository.recuperar(nome, telefone) == null) {
			throw new DadoInvalido(USUARIO_INVALIDO);
		}
		repository.remover(nome, telefone);
	}

	public void atualizarUsuario(String nome, String telefone, String atributo, String valor) throws Exception {
		if (repository.recuperar(nome, telefone) == null) {
			throw new DadoInvalido(USUARIO_INVALIDO);
		}

		repository.editar(nome, telefone, atributo, valor);
	}

	/**
	 * Recupera um {@link Usuario} no sistema
	 * 
	 * @param nome
	 *            Nome do usuario
	 * @param telefone
	 *            Telefone do usuario
	 * @return
	 */
	public Usuario recuperar(String nome, String telefone) {
		return recuperar(nome, telefone);
	}

	public void cadastrarEletronico(String nome, String telefone, String nomeItem, double preco, String plataforma)
			throws Exception {
		repository.recuperar(nome, telefone).adiconarItemJogoEletronico(nomeItem, preco, plataforma);
	}
	/**
	 * Informacao do item desejado
	 * @param nome nome do usuario
	 * @param telefone telefonee do usuario
	 * @param nomeItem nome do item
	 * @param atributo atributo que deseja que seja informado
	 * @return
	 * @throws Exception
	 */
	public String getInfoItem(String nome, String telefone, String nomeItem, String atributo) throws Exception{
		if (repository.recuperar(nome, telefone).recuperItem(nomeItem) == null) {
			throw new OperacaoNaoPermitida(ITEM_NAO_ENCONTRADO);
		}
		if (atributo.equalsIgnoreCase("preco")) {
			String getvalor = String.valueOf(repository.recuperar(nome, telefone).recuperItem(nomeItem).getValor());
			return getvalor;
		}else if (atributo.equalsIgnoreCase("nome")) {
			return repository.recuperar(nome, telefone).recuperItem(nomeItem).getNome();
			
		}
		return "";
	}
	/**
	 * Cadastrar Jogo de taboleiro
	 * @param nome nome do usuario
	 * @param telefone telefone do usuario
	 * @param nomeItem nome do item
	 * @param preco preco do item
	 * @throws Exception
	 */
	public void cadastrarJogoTabuleiro(String nome, String telefone, String nomeItem, double preco) throws Exception {
		if (repository.recuperar(nome, telefone) == null) {
			throw new DadoInvalido(USUARIO_INVALIDO);
		}
		repository.recuperar(nome, telefone).adicionarItemJogoTabuleiro(nomeItem, preco);

	}
	/**
	 * Adicionar peca percida do jogo de taboleiro
	 * @param nome nome do usuario
	 * @param telefone telefone do usuario
	 * @param nomeItem nome do item
	 * @param nomePeca nome da peca perdida 
	 */
	public void adicionarPecaPerdida(String nome, String telefone, String nomeItem, String nomePeca) {
		repository.recuperar(nome, telefone).adicionarPecaperdida(nomeItem, nomePeca);

	}
	/**
	 * Cadastrar BluRaySerie
	 * @param nome nome do usuario
	 * @param telefone telefone do usuario
	 * @param nomeItem nome do serie
	 * @param preco preco do serie
	 * @param descricao descricao o serie
	 * @param duracao duracao da serie
	 * @param classificacao classificacao da serie
	 * @param genero genero da serie
	 * @param temporada temporada que da serie
	 * @throws Exception
	 */
	public void cadastrarBluRaySerie(String nome, String telefone, String nomeItem, double preco, String descricao,
			int duracao, String classificacao, String genero, int temporada) throws Exception {
		if (repository.recuperar(nome, telefone) == null) {
			throw new DadoInvalido(USUARIO_INVALIDO);
		}
		repository.recuperar(nome, telefone).adicionarItemSerie(nomeItem, preco, descricao, duracao, classificacao,
				genero, temporada);
	}
	/**
	 * Cadastrar BlurayFilme
	 * @param nome nome do usuario
	 * @param telefone telefone do usuario
	 * @param nomeItem nome do filme
	 * @param valor valor do filme
	 * @param duracao duracao total do filme
	 * @param genero genero do filme
	 * @param classificacao classificacao do filme 
	 * @param anoDeLancamento ano dde lancemento do filme
	 * @throws Exception
	 */
	public void cadastrarBluRayFilme(String nome, String telefone, String nomeItem, double valor, int duracao,
			String genero, String classificacao, String anoDeLancamento) throws Exception {
		repository.recuperar(nome, telefone).adicionarItemFilme(nomeItem, valor, duracao, genero, classificacao,
				anoDeLancamento);

	}
	/**
	 * Cadastrar BlurayShow
	 * @param nome nome do usuario
	 * @param telefone telefone do usuario
	 * @param nomeItem nome do BlurayShow
	 * @param valor valor do BlurayShow
	 * @param duracao duracao do BlurayShow
	 * @param numeroDeFaixas numero de faixas do BlurayShow
	 * @param artista artista do BlurayShow
	 * @param classificacao classificacao do BlurayShow
	 * @throws Exception
	 */
	public void cadastrarBluRayShow(String nome, String telefone, String nomeItem, double valor, int duracao,
			int numeroDeFaixas, String artista, String classificacao) throws Exception {
		repository.recuperar(nome, telefone).adicionarItemShow(nomeItem, valor, duracao, numeroDeFaixas, artista,
				classificacao);
	}

	public void adicionarBluRay(String nome, String telefone, String nomeItem, int duracao) {
		repository.recuperar(nome, telefone).adicionarBluRay(nomeItem, duracao);
	}

	public void removerItem(String nome, String telefone, String nomeItem) throws Exception {
		if (repository.recuperar(nome, telefone) == null) {
			throw new DadoInvalido(USUARIO_INVALIDO);
		}else if (repository.recuperar(nome, telefone).recuperItem(nomeItem) == null) {
			throw new DadoInvalido(ITEM_NAO_ENCONTRADO);
		}
		repository.recuperar(nome, telefone).removerItem(nomeItem);
	}

	public void atualizarItem(String nome, String telefone, String nomeItem, String atributo, String valor) throws Exception {
		if (repository.recuperar(nome, telefone) == null) {
			throw new DadoInvalido(USUARIO_INVALIDO);
		}else if (repository.recuperar(nome, telefone).recuperItem(nomeItem) == null) {
			throw new DadoInvalido(ITEM_NAO_ENCONTRADO);
		}
		repository.recuperar(nome, telefone).atualizarItem(nomeItem, atributo, valor);
		
	}
	
	public String pesquisarDetalhesItem(String nome, String telefone, String nomeItem) throws Exception{
		if (repository.recuperar(nome, telefone) == null) {
			throw new DadoInvalido(USUARIO_INVALIDO);
		}else if (repository.recuperar(nome, telefone).recuperItem(nomeItem) == null) {
			throw new DadoInvalido(ITEM_NAO_ENCONTRADO);
		}
		return repository.recuperar(nome, telefone).recuperItem(nomeItem).toString();
	}
	
	public void registrarEmprestimo(String nomeDono, String telefoneDono, String nomeRequerente, String telefoneRequerente, String nomeItem, String dataEmprestimo, int periodo) throws DadoInvalido, ParseException {
		if (repository.recuperar(nomeDono, telefoneDono) == null || repository.recuperar(nomeRequerente, telefoneRequerente) == null)
			throw new DadoInvalido(USUARIO_INVALIDO);
		if (repository.recuperar(nomeDono, telefoneDono).recuperItem(nomeItem) == null)
			throw new DadoInvalido(ITEM_NAO_ENCONTRADO);
		if (repository.recuperar(nomeDono, telefoneDono).recuperItem(nomeItem).getStatus() == true)
			throw new DadoInvalido(ITEM_JA_EMPRESTADO);
		repository.registrarEmprestimo(nomeDono, telefoneDono, nomeRequerente, telefoneRequerente, nomeItem, dataEmprestimo, periodo);
	}
	
	public void devolverItem(String nomeDono, String telefoneDono, String nomeRequerente, String telefoneRequerente, String nomeItem, String dataEmprestimo, String dataDevolucao) throws ParseException, DadoInvalido {
		if (repository.recuperar(nomeDono, telefoneDono) == null || repository.recuperar(nomeRequerente, telefoneRequerente) == null)
			throw new DadoInvalido(USUARIO_INVALIDO);
		if (repository.recuperar(nomeDono, telefoneDono).recuperItem(nomeItem) == null)
			throw new DadoInvalido(ITEM_NAO_ENCONTRADO);
		if (repository.devolverItem(nomeDono, telefoneDono, nomeRequerente, telefoneRequerente, nomeItem, dataEmprestimo, dataDevolucao) == false)
			throw new DadoInvalido(EMPRESTIMO_NAO_ENCONTRADO);
		repository.devolverItem(nomeDono, telefoneDono, nomeRequerente, telefoneRequerente, nomeItem, dataEmprestimo, dataDevolucao);
	}
	/**
	 * Metodo para ordenacao de itens por nome
	 * @return
	 */
	public String listarItensOrdenadosPorNome() {
		List<Item> listaItens = repository.listaTotalItens();
		Collections.sort(listaItens, new ItemOrdenacaoDescricao());
		String listaDescricaoItens = "";
		for (Item item : listaItens) {
			listaDescricaoItens += item.toString() + "|";
		}
		return listaDescricaoItens;
	}
	/**
	 * Metodo para ordenacao de itens por valor
	 * @return
	 */
	public String ordenacaoItensValor() {
		List<Item> listaItens = repository.listaTotalItens();
		Collections.sort(listaItens, new ItemOrdenacaoValor());
		String listaDescricaoItens = "";
		for (Item item : listaItens) {
			listaDescricaoItens += item.toString() + "|";
		}
		return listaDescricaoItens;
	}
}