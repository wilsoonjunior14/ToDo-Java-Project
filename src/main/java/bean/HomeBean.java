package bean;

import java.util.ArrayList;
import java.util.List;

import dao.ToDODAO;
import model.ToDo;
import util.Network;

public class HomeBean {

	public String myIp = Network.getMyIP();
	
	public ToDo todo = new ToDo();
	
	public List<ToDo> todoList = new ArrayList<>();
	
	public ToDODAO dao = ToDODAO.getInstance();	
	
	public HomeBean() {
		this.updateTodoList();
	}
	
	public void save() {
		todo.setIp(this.myIp);
		this.dao.save(this.todo);
		this.updateTodoList();
		this.todo = new ToDo();
	}
	
	public void delete(ToDo todo) {
		this.dao.delete(todo);
		this.updateTodoList();
	}
	
	public void getOnlyCompleted() {
		this.todoList = this.dao.getOnlyCompleted(this.myIp);
	}
	
	public void clearAllCompleted() {
		this.dao.deleteAllCompleted(this.myIp);
		this.updateTodoList();
	}
	
	public void update(ToDo todo) {
		todo.setCompleted(!todo.isCompleted());
		this.dao.update(todo);
		this.updateTodoList();
	}
	
	public void updateTodoList() {
		this.todoList = this.dao.getAll(this.myIp);
	}
	
	public List<ToDo> getTodoList() {
		return todoList;
	}

	public void setTodoList(List<ToDo> todoList) {
		this.todoList = todoList;
	}

	public ToDo getTodo() {
		return todo;
	}

	public void setTodo(ToDo todo) {
		this.todo = todo;
	}
	
}
