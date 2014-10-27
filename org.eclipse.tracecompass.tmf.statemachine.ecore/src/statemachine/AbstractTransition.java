/**
 */
package statemachine;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Transition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link statemachine.AbstractTransition#getState <em>State</em>}</li>
 *   <li>{@link statemachine.AbstractTransition#getStateChange <em>State Change</em>}</li>
 * </ul>
 * </p>
 *
 * @see statemachine.StatemachinePackage#getAbstractTransition()
 * @model abstract="true"
 * @generated
 */
public interface AbstractTransition extends Named {
	/**
	 * Returns the value of the '<em><b>State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State</em>' reference.
	 * @see #setState(AbstractState)
	 * @see statemachine.StatemachinePackage#getAbstractTransition_State()
	 * @model
	 * @generated
	 */
	AbstractState getState();

	/**
	 * Sets the value of the '{@link statemachine.AbstractTransition#getState <em>State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State</em>' reference.
	 * @see #getState()
	 * @generated
	 */
	void setState(AbstractState value);

	/**
	 * Returns the value of the '<em><b>State Change</b></em>' containment reference list.
	 * The list contents are of type {@link statemachine.StateChange}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State Change</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State Change</em>' containment reference list.
	 * @see statemachine.StatemachinePackage#getAbstractTransition_StateChange()
	 * @model containment="true"
	 * @generated
	 */
	EList<StateChange> getStateChange();

} // AbstractTransition
