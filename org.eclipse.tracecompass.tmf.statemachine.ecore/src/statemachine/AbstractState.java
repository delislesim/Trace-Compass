/**
 */
package statemachine;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link statemachine.AbstractState#getTransitions <em>Transitions</em>}</li>
 * </ul>
 * </p>
 *
 * @see statemachine.StatemachinePackage#getAbstractState()
 * @model abstract="true"
 * @generated
 */
public interface AbstractState extends Named {
	/**
	 * Returns the value of the '<em><b>Transitions</b></em>' containment reference list.
	 * The list contents are of type {@link statemachine.AbstractTransition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transitions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transitions</em>' containment reference list.
	 * @see statemachine.StatemachinePackage#getAbstractState_Transitions()
	 * @model containment="true"
	 * @generated
	 */
	EList<AbstractTransition> getTransitions();

} // AbstractState
