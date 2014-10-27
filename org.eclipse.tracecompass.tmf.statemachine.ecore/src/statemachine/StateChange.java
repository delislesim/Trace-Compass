/**
 */
package statemachine;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link statemachine.StateChange#getStateAttribute <em>State Attribute</em>}</li>
 *   <li>{@link statemachine.StateChange#getStateValue <em>State Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see statemachine.StatemachinePackage#getStateChange()
 * @model
 * @generated
 */
public interface StateChange extends EObject {
	/**
	 * Returns the value of the '<em><b>State Attribute</b></em>' containment reference list.
	 * The list contents are of type {@link statemachine.StateAttribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State Attribute</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State Attribute</em>' containment reference list.
	 * @see statemachine.StatemachinePackage#getStateChange_StateAttribute()
	 * @model containment="true"
	 * @generated
	 */
	EList<StateAttribute> getStateAttribute();

	/**
	 * Returns the value of the '<em><b>State Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State Value</em>' containment reference.
	 * @see #setStateValue(StateValue)
	 * @see statemachine.StatemachinePackage#getStateChange_StateValue()
	 * @model containment="true"
	 * @generated
	 */
	StateValue getStateValue();

	/**
	 * Sets the value of the '{@link statemachine.StateChange#getStateValue <em>State Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State Value</em>' containment reference.
	 * @see #getStateValue()
	 * @generated
	 */
	void setStateValue(StateValue value);

} // StateChange
