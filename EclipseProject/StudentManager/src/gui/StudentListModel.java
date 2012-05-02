package gui;

import javax.swing.AbstractListModel;

import DataModel.Student;
import DataModel.StudentCollection;

public class StudentListModel extends AbstractListModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2247374062666417439L;
	private StudentCollection studentCollection;

	public StudentListModel(StudentCollection studentCollection) {
		this.studentCollection = studentCollection;
	}
	
	public void updateModelSource(StudentCollection studentCollection) {
		this.studentCollection.getStudents().clear(); 
		fireIntervalRemoved(this, 0, studentCollection.getStudents().size()-1);
		this.studentCollection = studentCollection;
		fireIntervalAdded(this, 0, studentCollection.getStudents().size()-1);
	}
	
	@Override
	public int getSize() {
		return studentCollection.getStudents().size();
	}

	@Override
	public Student getElementAt(int index) {
		return studentCollection.getStudents().get(index);
	}

	public void addStudent(Student student) {
		studentCollection.getStudents().add(student);
		int index = studentCollection.getStudents().indexOf(student);
		fireIntervalAdded(this, index, index);
	}
	
	public void editStudent(int studentIndex, String name, String lname) {
		Student s = (Student)studentCollection.getStudents().get(studentIndex);
		s.setName(name);
		s.setLastName(lname);
		fireContentsChanged(this, studentIndex, studentIndex);
	}

	public void deleteStudent(int index) {
		studentCollection.getStudents().remove(index);
		fireIntervalRemoved(this, index, index);
	}

}
