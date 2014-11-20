/**
 */
package statemachine;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Statemachine</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link statemachine.Statemachine#getStates <em>States</em>}</li>
 *   <li>{@link statemachine.Statemachine#getAssociatedTree <em>Associated Tree</em>}</li>
 *   <li>{@link statemachine.Statemachine#getAssociatedAttribute <em>Associated Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see statemachine.StatemachinePackage#getStatemachine()
 * @model
 * @generated
 */
public interface Statemachine extends Named {
	/**
	 * Returns the value of the '<em><b>States</b></em>' containment reference list.
	 * The list contents are of type {@link statemachine.AbstractState}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>States</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>States</em>' containment reference list.
	 * @see statemachine.StatemachinePackage#getStatemachine_States()
	 * @model containment="true"
	 * @generated
	 */
	EList<AbstractState> getStates();

	/**
	 * Returns the value of the '<em><b>Associated Tree</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Associated Tree</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Associated Tree</em>' attribute.
	 * @see #setAssociatedTree(String)
	 * @see statemachine.StatemachinePackage#getStatemachine_AssociatedTree()
	 * @model
	 * @generated
	 */
	String getAssociatedTree();

	/**
	 * Sets the value of the '{@link statemachine.Statemachine#getAssociatedTree <em>Associated Tree</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Associated Tree</em>' attribute.
	 * @see #getAssociatedTree()
	 * @generated
	 */
	void setAssociatedTree(String value);

	/**
	 * Returns the value of the '<em><b>Associated Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Associated Attribute</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Associated Attribute</em>' attribute.
	 * @see #setAssociatedAttribute(String)
	 * @see statemachine.StatemachinePackage#getStatemachine_AssociatedAttribute()
	 * @model
	 * @generated
	 */
	String getAssociatedAttribute();

	/**
	 * Sets the value of the '{@link statemachine.Statemachine#getAssociatedAttribute <em>Associated Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Associated Attribute</em>' attribute.
	 * @see #getAssociatedAttribute()
	 * @generated
	 */
	void setAssociatedAttribute(String value);

} // Statemachine
