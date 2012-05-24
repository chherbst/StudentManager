package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import DataModel.Student;

public class StudentTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2247374062666417439L;
	private List<Student> students;

	public StudentTableModel(List<Student> students) {
		this.students = students;
	}
	
	public void setStudents(List<Student> students) {
		this.students = students;
		fireTableStructureChanged();
	}
	

	public void studentAdded(int index) {
		fireTableRowsInserted(index, index);
	}

	public void studentDeleted(int index) {
		fireTableRowsDeleted(index, index);
	}

	public void studentChanged(int index) {
		fireTableRowsUpdated(index, index);
	}

	@Override
	public int getRowCount() {
		return students.size();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Student student = students.get(rowIndex);
		Object value = null;
		switch (columnIndex) {
		case 0:
			value = student.getName();
			break;

		default:
			value = student.getLastName();
			break;
		}
		return value;
	}

}
