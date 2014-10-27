/**
 */
package statemachine;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link statemachine.AbstractCondition#getStateValue <em>State Value</em>}</li>
 *   <li>{@link statemachine.AbstractCondition#isIsNotCondition <em>Is Not Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @see statemachine.StatemachinePackage#getAbstractCondition()
 * @model
 * @generated
 */
public interface AbstractCondition extends EObject {
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
	 * @see statemachine.StatemachinePackage#getAbstractCondition_StateValue()
	 * @model containment="true"
	 * @generated
	 */
	StateValue getStateValue();

	/**
	 * Sets the value of the '{@link statemachine.AbstractCondition#getStateValue <em>State Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State Value</em>' containment reference.
	 * @see #getStateValue()
	 * @generated
	 */
	void setStateValue(StateValue value);

	/**
	 * Returns the value of the '<em><b>Is Not Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Not Condition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Not Condition</em>' attribute.
	 * @see #setIsNotCondition(boolean)
	 * @see statemachine.StatemachinePackage#getAbstractCondition_IsNotCondition()
	 * @model
	 * @generated
	 */
	boolean isIsNotCondition();

	/**
	 * Sets the value of the '{@link statemachine.AbstractCondition#isIsNotCondition <em>Is Not Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Not Condition</em>' attribute.
	 * @see #isIsNotCondition()
	 * @generated
	 */
	void setIsNotCondition(boolean value);

} // AbstractCondition
