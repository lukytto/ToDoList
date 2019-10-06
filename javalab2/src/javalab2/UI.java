package javalab2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import ds.Project;
import ds.ToDoList;
import ds.User;

public class UI {
	
	static Scanner keyboard = null;
	
	public static void main(String[] args) {
		
		ToDoList todo = new ToDoList();
		try {
			ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("data.txt")));
		todo = (ToDoList) in.readObject();
		in.close();
		}catch(Exception e) {
			System.out.println("Failed to load data");
			todo.registerPerson("admin", "admin", "admin", "admin");
			todo.addProject("Projektas1");
		}
	

		keyboard = new Scanner(System.in);
		login(todo);
		
		start:
			while(true) {
				printCommands();

				String input = keyboard.nextLine().trim();
				switch(input) {
				case "user":
				case "1":
					userSubmeniu(todo);
					break;
				case "project":
				case "2":
					projectSubmeniu(todo);
					break;
				case "exit":
				case "3":
					try {
						ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data.tdl")));
					out.writeObject(todo);
					out.close();
					}catch(Exception e) {
						System.out.println("Failed to write data");
					}
					System.out.println("goodbye");
					break start;
				default:
					System.out.println("The commands does not exist");
					break;

				}
			}
	}
	
	private static void projectSubmeniu(ToDoList t) {
		while(true) {
			System.out.println("Pasirinkite:\n"
					+ "\tget project list - all,1\n"
					+ "\tadd project - addp, 2\n"
					+ "\tdelete Project - 3\n"
					+ "\tedit Project - 4\n" //TODO
					+ "\tadd Task to Project - 5 \n"
					+ "\texit submeniu - 9");
			String input = keyboard.nextLine();
			
			switch(input) {
			case "1":
				for(Project p:t.getAllUserProjects()) {
					System.out.println(p);
				}
				break;
			case "2":
				System.out.println("enter title:");
				String title = keyboard.nextLine().trim();
				t.addProject(title);
				break;
			case "3":
				System.out.println("Enter Project Id");
				int idPr=Integer.parseInt(keyboard.nextLine());
				t.deleteProject(idPr);
				break;
			case "4":
//				System.out.println("Enter Persons Id");
//				int idU=Integer.parseInt(keyboard.nextLine());
//				t.disableUser(idU);
				break;
			case "5":
				
				System.out.println("Enter Project Id");
				int idPro=Integer.parseInt(keyboard.nextLine());
				t.getProjectById(idPro);
				System.out.println("Enter Task title");
				String taskTitle=keyboard.nextLine().trim();
				t.addTaskToProject(idPro, taskTitle);
				break;
			case "6":
//				System.out.println("Enter Company Id");
//				int idC=Integer.parseInt(keyboard.nextLine());
//				System.out.println("Enter Company title");
//				String title=keyboard.nextLine().trim();
//				t.editCompanyInfo(idC, title);
				break;
			case "7":
				//importPersons(t);
				break;
			case "8":
			//	importCompanies(t);
				break;
			case "9":
				return;

			
			}
		}
		
	}

	public static void login(ToDoList t) {
		int numberOfAttempts = 3;
		while(numberOfAttempts>0) {
			System.out.println("enter login:");
			String login = keyboard.nextLine().trim();
			System.out.println("enter password:");
			String pass = keyboard.nextLine().trim();
			try {
				t.login(login,pass);
				return;
			}catch(Exception e) {
				System.out.println(e.getMessage());
				numberOfAttempts--;				
			}

		}
		System.exit(0);

	}
	
	public static void printCommands() {
		System.out.println("Pasirinkite komdanda:\n"
				+"\tUser - user, 1\n"
				+"\tProjects - project, 2\n"
				+"\tExit - exit, 3\n");
	}
	
	public static void userSubmeniu(ToDoList t) {
		while(true) {
			System.out.println("Pasirinkite:\n"
					+ "\tget user list - all,1\n"
					+ "\tadd person - addp, 2\n"
					+ "\tadd company - 3\n"
					+ "\tdisable User - 4\n"
					+ "\tedit Person - 5\n"
					+ "\tedit Company - 6\n"
					+ "\timport Person - 7\n"
					+ "\timport companies - 8 \n"
					+ "\texit submeniu - 9");
			String input = keyboard.nextLine();
			
			switch(input) {
			case "1":
				for(User u:t.getAllUsers()) {
					System.out.println(u);
				}
				break;
			case "2":
				readNewPerson(t);
				break;
			case "3":
				readNewCompany(t);
				break;
			case "4":
				System.out.println("Enter Persons Id");
				int idU=Integer.parseInt(keyboard.nextLine());
				t.disableUser(idU);
				break;
			case "5":
				System.out.println("Enter Persons Id");
				int idP=Integer.parseInt(keyboard.nextLine());
				System.out.println("Enter Persons name");
				String name=keyboard.nextLine().trim();
				System.out.println("Enter Persons surname");
				String surname=keyboard.nextLine().trim();
				t.editPersonInfo(idP, name, surname);
				break;
			case "6":
				System.out.println("Enter Company Id");
				int idC=Integer.parseInt(keyboard.nextLine());
				System.out.println("Enter Company title");
				String title=keyboard.nextLine().trim();
				t.editCompanyInfo(idC, title);
				break;
			case "7":
				importPersons(t);
				break;
			case "8":
				importCompanies(t);
				break;
			case "9":
				return;

			
			}
		}
	}
	
	public static void importPersons(ToDoList t) {
		Scanner file=null;
		try {
			file = new Scanner(new File("users.txt"));
			while(file.hasNext()) {
				String line=file.nextLine();
				String[] elements = line.split(";");
				t.registerPerson(elements[1], elements[2], elements[3], elements[4]);
			}

		}catch(Exception e) {
			System.out.println("Error reading data");
		}finally {
			if(file!=null) {
				file.close();
			}
		}
	}
	
	public static void readNewPerson(ToDoList t) {
		String login="";
		while(true) {
			System.out.println("enter login:");
			 login = keyboard.nextLine().trim();
			if(login.equals("exit")) {
				return;
			}
			if(login.length()>3 && t.getUserByLogin(login)==null) {
				break;
			}
			
		}
		System.out.println("enter pass:");
		String pass = keyboard.nextLine().trim();
		System.out.println("enter name;");
		String name = keyboard.nextLine().trim();
		System.out.println("enter surname:");
		String surname = keyboard.nextLine().trim();
		t.registerPerson(login, pass, name, surname);
	}
	
	public static void importCompanies(ToDoList t) {
		Scanner file=null;
		try {
			file = new Scanner(new File("companies.txt"));
			while(file.hasNext()) {
				String line=file.nextLine();
				String[] elements = line.split(";");
				t.registerCompany(elements[1], elements[2], elements[3]);
			}

		}catch(Exception e) {
			System.out.println("Error reading data");
		}finally {
			if(file!=null) {
				file.close();
			}
		}
	}
	
	public static void readNewCompany(ToDoList t) {
		String login="";
		while(true) {
			System.out.println("enter login:");
			 login = keyboard.nextLine().trim();
			if(login.equals("exit")) {
				return;
			}
			if(login.length()>3 && t.getUserByLogin(login)==null) {
				break;
			}
			
		}
		System.out.println("enter pass:");
		String pass = keyboard.nextLine().trim();
		System.out.println("enter title;");
		String title = keyboard.nextLine().trim();
		t.registerCompany(login, pass, title);
	}
}
