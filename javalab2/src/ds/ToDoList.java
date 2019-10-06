package ds;

import java.io.Serializable;
import java.util.ArrayList;

public class ToDoList implements Serializable {
	
private ArrayList<User> users = new ArrayList();
private ArrayList<Project> projects = new ArrayList();
private User loggedIn = null;

	public Person registerPerson(String login, String pass, String name, String surname) {
		if(getUserByLogin(login)==null) {
			Person newPerson = new Person(login, pass, name, surname);
			users.add(newPerson);
			return newPerson;
		}
		return null;
	}
	
	public Company registerCompany(String login, String pass, String title) {
		if(getUserByLogin(login)==null) {
			Company newCompany = new Company (login, pass, title);
			users.add(newCompany);
			return newCompany;
		}
		
		return null;
	}

	public User login(String login, String pass) throws Exception {
		
		for(User u:users) {
			if(u.getLogin().equals(login) && u.getPass().equals(pass) && u.isActive()) {
				loggedIn=u;
				return u;
			}
		}
		throw new ObjectNotExists("incorrect login data");
	}
	
	public void logout(int id) {
		loggedIn=null;
	}
	
	public Project addProject(String title) {
		if(loggedIn!=null && title.length()>3) {
			Project newProject = new Project(title, loggedIn);
			projects.add(newProject);
			loggedIn.addProject(newProject);
			return newProject;
		}
		return null;
	}
	
	public void addProjectMember(int projectId, int userId) {
		if(loggedIn!=null) {
			Project current = getProjectById(projectId);
			User toAdd = getUserById(userId);
			if(current!=null && toAdd !=null) {
				current.addMember(toAdd);
				toAdd.addProject(current);
			}
		}
	}
	
	public void deleteProject(int id) {
		Project current = getProjectById(id);
		if(current!=null) {
			projects.remove(current);
			for(User u:current.getMembers()) {
				u.getProjects().remove(current);
			}
		}
	}
	
	public Task addTaskToProject(int projectId, String title) {
		if(loggedIn!=null) {
			Project my= getUserProjectById(projectId);
			Task newTask = new Task(title, my, loggedIn);
			my.addTask(newTask);
			return newTask;
		}
		return null;
	}
	
	public Task addTaskToTask(int taskId, String title) {
		if(loggedIn!=null) {
			
			Task my= getUserTaskById(taskId);
			Task newTask = new Task(title, my.getProject(), loggedIn);
			my.addTask(newTask);
			return newTask;
		}
		return null;
	}
	
	public Task getUserTaskById(int id) {
	   
		for(Project p:loggedIn.getProjects()) {
			ArrayList<Task> allTasks = p.getAllTasks();
			for(Task t:allTasks) {
				if(t.getId()==id) {
					return t;
				}
			}
		}
		
		return null;
	}
	
	public Project getUserProjectById(int id){
		if(loggedIn!=null) {
			for(Project p:loggedIn.getProjects()) {
				if(p.getId()==id) {
					return p;
				}
			}
		}
		return null;
	}
	
	
	public ArrayList<Project> getAllUserProjects(){
		if(loggedIn!=null) {
			return loggedIn.getProjects();
		}
		return new ArrayList();
	}
	
	public void editPersonInfo(int id, String name, String surname) {
		User current = getUserById(id);
		if(current!=null && current.getClass().equals(Person.class)) {
			Person p = (Person)current;
			p.setName(name);
			p.setSurname(surname);
		}
	}
	
	public void editCompanyInfo(int id, String title) {
		User current = getUserById(id);
		if(current!=null && current.getClass().equals(Company.class)) {
			Company p = (Company)current;
			p.setTitle(title);
		}
		
	}
	
	public ArrayList<User> getAllUsers (){
		if(loggedIn!=null && loggedIn.isActive()) {
			return users;
		}
		return new ArrayList();
	}
	
	public ArrayList<User> getAllActiveUsers (){
		if(loggedIn!=null && loggedIn.isActive()) {
		ArrayList<User> filtered = new ArrayList();
		for(User u:users) {
			if(u.isActive()) {
				filtered.add(u);
			}
		}
		return filtered;
		} 
		return new ArrayList();
	}
	public boolean disableUser (int id) {
		if(loggedIn!=null && loggedIn.isActive()) {
		User forDeletion = getUserById(id);
		if(forDeletion!=null && forDeletion.isActive()) {
			forDeletion.setActive(false);
			return true;
		}
		}
		return false;
	}
	
	public User getUserById (int id) {
		if(loggedIn!=null && loggedIn.isActive()) {
		for (User u:users) {
			if (u.getId()==id) {
				return u;
			}
		}}
		return null;
	}
	
	public Project getProjectById (int id) {
		if(loggedIn!=null && loggedIn.isActive()) {
			for (Project p:projects) {
				if (p.getId()==id) {
					return p;
				}
			}}
			return null;
	}
	
	
	public User getUserByLogin (String login) {
		if(loggedIn!=null && loggedIn.isActive()) {
		for (User u:users) {
			if (u.getLogin().equals(login)) {
				return u;
			}
		}}
		return null;
	}
}
