package controller;

import gui.StudentListModel;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import javax.swing.ListModel;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import DataModel.DataModelFactory;
import DataModel.DataModelPackage;
import DataModel.Student;
import DataModel.StudentCollection;

public class MainController {
	private StudentCollection studentCollection;
	private StudentListModel studentListModel;
	private String modelfile = "studentManager.students";
	
	public MainController() {
		studentCollection = load();
		studentListModel = new StudentListModel(studentCollection);
	}

	public void createStudent(String name, String lastName) {
		Student student = DataModelFactory.eINSTANCE.createStudent();
		student.setName(name);
		student.setLastName(lastName);
		studentListModel.addStudent(student);
	}
	
	public void editStudent(int index, String name, String lastName) {
		if(index != -1) {
			Student student = (Student)studentCollection.getStudents().get(index);
			student.setName(name);
			student.setLastName(lastName);
			studentListModel.studentChanged(index);
		}
	}

	public void deleteStudent(int index) {
		studentListModel.deleteStudent(index);
	}

	private ResourceSet getRecourceSet() {
		// Initialize the model
		DataModelPackage.eINSTANCE.eClass();

		// Register the XMI resource factory for the .students extension
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("students", new XMIResourceFactoryImpl());

		// Obtain a new resource set
		return new ResourceSetImpl();
	} 
	
	public void save() {
		ResourceSet resSet = getRecourceSet();

		// Create a resource
		Resource resource = resSet.createResource(URI
				.createURI(modelfile));
	
		// add the root node to the resource
		resource.getContents().add(studentCollection);

		// Now save the content.
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadFromFile(String modelFile) {
		this.setModelfile(modelFile);
		studentCollection = load();
		studentListModel.updateModelSource(studentCollection);
	}
	
	/**
	 * @return the modelfile
	 */
	public String getModelfile() {
		return modelfile;
	}

	/**
	 * @param modelfile the modelfile to set
	 */
	public void setModelfile(String modelfile) {
		this.modelfile = modelfile;
	}

	private StudentCollection load() {
		ResourceSet resSet = getRecourceSet();

		// Get the resource
		StudentCollection studentCollection = null;
		try {
			Resource resource = resSet.getResource(URI
					.createURI(modelfile), true);
			// Get the first model element and cast it to the right type, in my
			// example everything is hierarchical included in this first node
			studentCollection = (StudentCollection) resource.getContents().get(0);
		} catch (Exception e) {
			studentCollection = DataModelFactory.eINSTANCE.createStudentCollection();
		}
		return studentCollection;
	}

	public ListModel getStudentModel() {
		return studentListModel;
	}

	public Student getStudent(int index) {
		return studentListModel.getElementAt(index);
	}

}
