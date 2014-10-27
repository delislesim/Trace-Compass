/**
 */
package statemachine;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link statemachine.StateValue#getType <em>Type</em>}</li>
 *   <li>{@link statemachine.StateValue#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see statemachine.StatemachinePackage#getStateValue()
 * @model
 * @generated
 */
public interface StateValue extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link statemachine.StateValueType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see statemachine.StateValueType
	 * @see #setType(StateValueType)
	 * @see statemachine.StatemachinePackage#getStateValue_Type()
	 * @model
	 * @generated
	 */
	StateValueType getType();

	/**
	 * Sets the value of the '{@link statemachine.StateValue#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see statemachine.StateValueType
	 * @see #getType()
	 * @generated
	 */
	void setType(StateValueType value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see statemachine.StatemachinePackage#getStateValue_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link statemachine.StateValue#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

} // StateValue
