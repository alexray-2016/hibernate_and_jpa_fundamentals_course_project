package ru.alexraydev.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ru.alexraydev.data.entities.Transaction;

public class JpaApplication {

	public static void main(String[] args) {

		EntityManagerFactory factory = null;
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			factory = Persistence
					.createEntityManagerFactory("infinite-finances");
			em = factory.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			//select t from transaction t
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Transaction> criteriaQuery = cb.createQuery(Transaction.class);
			
			Root<Transaction> root = criteriaQuery.from(Transaction.class);
			criteriaQuery.select(root);
			
			TypedQuery<Transaction> query = em.createQuery(criteriaQuery);
			List<Transaction> transactions = query.getResultList();

			for (Transaction t : transactions) {
				System.out.println(t.getTitle());
			}

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			factory.close();
		}
	}
}
