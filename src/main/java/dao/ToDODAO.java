package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import connection.HibernateConnection;
import model.ToDo;

public class ToDODAO {

	public static ToDODAO instance = null;
	
	private EntityManager entityManager = null;
	
	private static final String SELECT_TODO = "SELECT t FROM ToDo t";
	
	private static final String PARAM_IP = "ip";
	
	public static ToDODAO getInstance() {
		if (instance == null) {
			instance = new ToDODAO();
		}
		return instance;
	}
	
	private ToDODAO() {
		this.entityManager = HibernateConnection.getInstance().getConnection();
	}
	
	/**
	 * Saves an entity
	 * 
	 * @param entity ToDo
	 * @return ToDo
	 */
	public ToDo save(ToDo entity) {
		try {
			this.entityManager.getTransaction().begin();
			
			this.entityManager.persist(entity);
			
			this.entityManager.getTransaction().commit();
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
		return entity;
	}
	
	/**
	 * Returns list of ToDo
	 * 
	 * @param ip String
	 * @return List
	 */
	public List<ToDo> getAll(String ip){
		try {
			String query = SELECT_TODO + " WHERE t.ip like :ip";
			
			return (List<ToDo>) this.entityManager.createQuery(query).setParameter(PARAM_IP, ip).getResultList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	/**
	 * Gets List of only ToDo with completed flag true
	 * 
	 * @param ip String
	 * @return List
	 */
	public List<ToDo> getOnlyCompleted(String ip){
		try {
			List<ToDo> todoCompleted = new ArrayList<>();
			for (ToDo todo : this.getAll(ip)) {
				if (todo.isCompleted())
					todoCompleted.add(todo);
			}
			return todoCompleted;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	/**
	 * Deletes all ToDo with completed flag true based on ip string
	 * 
	 * @param ip String
	 */
	public void deleteAllCompleted(String ip) {
		try {
			this.entityManager.getTransaction().begin();
			
			for (ToDo entity : this.getAll(ip)) {
				if (entity.isCompleted())
					this.entityManager.remove(entity);
			}
			
			this.entityManager.getTransaction().commit();
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
	}
	
	/**
	 * Deletes an entity
	 * 
	 * @param entity ToDo
	 */
	public void delete(ToDo entity) {
		try {
			this.entityManager.getTransaction().begin();
			
			this.entityManager.remove(entity);
			
			this.entityManager.getTransaction().commit();
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
	}
	
	/**
	 * Updates an entity
	 * 
	 * @param entity ToDo
	 */
	public void update(ToDo entity) {
		try {
			this.entityManager.getTransaction().begin();
			
			ToDo oldEntity = this.entityManager.find(ToDo.class, entity.getId());
			oldEntity.setCompleted(entity.isCompleted());
			oldEntity.setIp(entity.getIp());
			oldEntity.setTask(entity.getTask());
			
			this.entityManager.getTransaction().commit();
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
	}
	
}
