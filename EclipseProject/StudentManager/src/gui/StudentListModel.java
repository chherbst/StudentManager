package gui;

import java.util.List;

import javax.swing.AbstractListModel;

import DataModel.Student;

public class StudentListModel extends AbstractListModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2247374062666417439L;
	private List<Student> students;

	public StudentListModel(List<Student> students) {
		this.students = students;
	}
	
	public void setStudents(List<Student> students) {
		this.students = students;
		fireContentsChanged(this, 0, students.size());
	}
	
	@Override
	public int getSize() {
		return students.size();
	}

	@Override
	public Student getElementAt(int index) {
		return students.get(index);
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
