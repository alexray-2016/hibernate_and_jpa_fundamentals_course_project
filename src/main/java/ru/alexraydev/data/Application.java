package ru.alexraydev.data;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import org.hibernate.Session;

import ru.alexraydev.data.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Application {

	public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("infinite-finances");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Bank bank = em.find(Bank.class, 2L);
        System.out.println(bank.getName());
        tx.commit();
        em.close();
        emf.close();
	}
	
}
