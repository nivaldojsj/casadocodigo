package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Produto;

/*@Repository: Para o Spring conhecer ele  e poder ser injetado
 * Representa os DAOs
 */
@Repository
@Transactional
public class ProdutoDao {

	/*@PersistenceContext: Conteiner que guarda as entidades que estão sendo gerenciadas 
	 * pelo EntityManager, funciona como cache L1 para o EM.
	 * */
	@PersistenceContext
	private EntityManager manager;
	/* EntityManager: serve para realizar as operacoes de sincronismo com o BD
	 * e gerenciar o ciclo de vida das entidades. 
	 * */
	
	public void gravar(Produto produto) {
		manager.persist(produto);
	}

	public List<Produto> listar() {
		return manager.createQuery("SELECT p FROM Produto p", Produto.class)
				.getResultList();
	}
	
	public Produto find(Integer id) {
		return manager.createQuery(" SELECT distinct(p) FROM Produto p"
				+ " JOIN FETCH p.precos precos WHERE p.id= :id", Produto.class)
				.setParameter("id", id)
				.getSingleResult();
	}

}
