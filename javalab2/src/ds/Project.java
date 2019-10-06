package ds;

import java.io.Serializable;
import java.util.ArrayList;

public class Project implements Serializable{
private int id;
private static int idCounter =1;
private String title;
private ArrayList<User> members = new ArrayList();
private ArrayList<Task> tasks = new ArrayList();

public Project(String title, User creator) {
	this.title=title;
	members.add(creator);
	this.id=idCounter++;
	}
public void addMember(User u) {
	members.add(u);
}
public void addTask(Task u) {
	tasks.add(u);
}
public int getId() {
	return id;
}






@Override
public String toString() {
	return "Project [id=" + id + ", title=" + title + ", members=" + members + ", tasks=" + tasks + "]";
}
public String getTitle() {
	return title;
}
public ArrayList<User> getMembers() {
	return members;
}
public String getTasks() {
	return tasks.toString();
}



public ArrayList<Task> getAllTasks(){
	ArrayList<Task> all = new ArrayList();
	all.addAll(this.tasks);
	for(Task t:tasks) {
		all.addAll(t.getAllTasks());
	}
	return all;
	
}

}
