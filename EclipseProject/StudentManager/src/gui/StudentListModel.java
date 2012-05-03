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
	
	public void setStudentCollection(StudentCollection studentCollection) {
		this.studentCollection = studentCollection;
		fireContentsChanged(this, 0, studentCollection.getStudents().size());
	}
	
	@Override
	public int getSize() {
		return studentCollection.getStudents().size();
	}

	@Override
	public Student getElementAt(int index) {
		return studentCollection.getStudents().get(index);
	}

	public void studentAdded(int index) {
		fireIntervalAdded(this, index, index);
	}

	public void studentDeleted(int index) {
		fireIntervalRemoved(this, index, index);
	}

	public void studentChanged(int index) {
		fireContentsChanged(this, index, index);
	}

}
