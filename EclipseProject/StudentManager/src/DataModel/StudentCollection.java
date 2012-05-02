/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package DataModel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Student Collection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link DataModel.StudentCollection#getStudents <em>Students</em>}</li>
 * </ul>
 * </p>
 *
 * @see DataModel.DataModelPackage#getStudentCollection()
 * @model
 * @generated
 */
public interface StudentCollection extends EObject {
	/**
	 * Returns the value of the '<em><b>Students</b></em>' containment reference list.
	 * The list contents are of type {@link DataModel.Student}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Students</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Students</em>' containment reference list.
	 * @see DataModel.DataModelPackage#getStudentCollection_Students()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<Student> getStudents();

} // StudentCollection
