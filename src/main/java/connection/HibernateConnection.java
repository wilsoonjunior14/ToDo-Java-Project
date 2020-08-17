package connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateConnection {

	private static HibernateConnection instance;
	private EntityManager entityManager;
	
	public static HibernateConnection getInstance() {
		if (instance == null) {
			instance = new HibernateConnection();
		}
		return instance;
	}
	
	public HibernateConnection() {
		entityManager = getEntityManager();
	}
	
	public EntityManager getConnection() {
		return entityManager;
	}
	
	private EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("Modelo").createEntityManager();
	}
	
}
