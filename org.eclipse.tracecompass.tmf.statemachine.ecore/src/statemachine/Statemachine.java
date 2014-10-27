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

} // Statemachine
