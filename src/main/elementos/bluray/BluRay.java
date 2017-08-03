package main.elementos.bluray;

import main.elementos.Item;

/**
 * Classe que representa um bluray
 * 
 * @author Giovana Brito Oliveira - 116110904
 *
 */
public abstract class BluRay extends Item {

	private String nome;
	private int duracao;
	private String classificacao;

	/**
	 * Construtor do BluRay
	 * @throws Exception 
	 */
	public BluRay(String nome, double valor, int duracao, String classificacao) throws Exception {
		super(nome, valor);
		this.duracao = duracao;
		this.classificacao = classificacao;
	}

	/**
	 * Metodo que retorna a duracao do BluRay
	 * 
	 * @return retorna a duracao do BluRay
	 */
	public int getDuracao() {
		return duracao;
	}

	/**
	 * Metodo que atualiza a duracao do BluRay
	 * 
	 * @param duracao
	 *            duracao do bluray
	 */
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	/**
	 * Metodo que retorna a classificacao do BluRay
	 * 
	 * @return retorna a clasificacao do BluRay
	 */
	public String getClassificacao() {
		return classificacao;
	}

	/**
	 * Metodo que atualiza a classificacao do bluray
	 * 
	 * @param classificacao
	 *            classificacao do bluray
	 */
	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	/**
	 * Metodo que retorna uma representacao inteira do bluray
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/**
	 * Metodo que indica se um bluray � ou n�o igual ao outros
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BluRay other = (BluRay) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	/**
	 * Metodo retorna a representacao em string do BluRay
	 */
	@Override
	public String toString() {
		return "Nome: " + nome + ", duracao: " + duracao + ", classificacao: " + classificacao;
	}
	@Override
	public void addBluray(int duracao) {
	}
}
