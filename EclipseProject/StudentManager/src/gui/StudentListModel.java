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

	public void deleteStudent(int index) {
		studentCollection.getStudents().remove(index);
		fireIntervalRemoved(this, index, index);
	}

}
