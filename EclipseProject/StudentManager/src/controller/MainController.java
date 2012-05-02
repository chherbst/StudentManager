package controller;

import gui.StudentListModel;

import javax.swing.ListModel;

import DataModel.DataModelFactory;
import DataModel.Student;
import DataModel.StudentCollection;

public class MainController {
	private StudentCollection studentCollection;
	private StudentListModel studentListModel;
	
	
	public MainController() {
		studentCollection = DataModelFactory.eINSTANCE.createStudentCollection();
		studentListModel = new StudentListModel(studentCollection);
	}

	public void createStudent(String name, String lastName) {
		Student student = DataModelFactory.eINSTANCE.createStudent();
		student.setName(name);
		student.setLastName(lastName);
		studentListModel.addStudent(student);
	}
	
	public void editStudent(int student, String name, String lastName) {
		studentListModel.editStudent(student, name, lastName);
	}

	public void deleteStudent(int index) {
		studentListModel.deleteStudent(index);
	}

	public void save() {
		for (Student student : studentCollection.getStudents()) {
			System.err.println(student);
		}
		
	}

	public ListModel getStudentModel() {
		return studentListModel;
	}

	public Student getStudent(int index) {
		return studentListModel.getElementAt(index);
	}

}
