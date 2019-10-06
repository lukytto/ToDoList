package javalab2;

import ds.*;

public class Main {

	public static void main(String[] args) {
		ToDoList todo = new ToDoList();
		todo.registerPerson("root", "admin", "Adminas", "Password");
		todo.registerPerson("adminas", "admin", "Adminas", "Password");
		User u = todo.registerPerson("admin", "admin", "Adminas", "Password");
		System.out.println(u);
		try {
		todo.login("admin","admin");
		}catch(Exception e) {
			System.out.println("Error:"+e.getMessage());
		}
		boolean pavyko = todo.disableUser(2);
		System.out.println("Pavyko istrinti useri?"+pavyko);
		
		
		todo.editPersonInfo(1,"jonas", "Jonaitis");
		for(User t:todo.getAllActiveUsers()) {
			System.out.println(t);
		}
		
		todo.addProject("Java ld");
		todo.getAllUserProjects();
		todo.addProjectMember(1, 3);
		//todo.deleteProject(1); //veikia
		Task t1 = todo.addTaskToProject(1, "Uzduotis");
		todo.addTaskToProject(1, "Uzduotis");
		todo.addTaskToProject(t1.getId(), "Uzduotis");





//		
//		todo.getProjectMembers();
//		
//		
//		todo.editProjectInfo();

//		todo.addTaskToProject();
//		todo.getProjectTasks();

//		todo.getAllActiveUsers();
//		todo.completeTask();
		

	}

}
