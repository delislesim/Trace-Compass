/**
 */
package statemachine;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link statemachine.StateAttribute#getType <em>Type</em>}</li>
 *   <li>{@link statemachine.StateAttribute#getValue <em>Value</em>}</li>
 *   <li>{@link statemachine.StateAttribute#getStateAttributeQuery <em>State Attribute Query</em>}</li>
 * </ul>
 * </p>
 *
 * @see statemachine.StatemachinePackage#getStateAttribute()
 * @model
 * @generated
 */
public interface StateAttribute extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link statemachine.StateAttributeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see statemachine.StateAttributeType
	 * @see #setType(StateAttributeType)
	 * @see statemachine.StatemachinePackage#getStateAttribute_Type()
	 * @model
	 * @generated
	 */
	StateAttributeType getType();

	/**
	 * Sets the value of the '{@link statemachine.StateAttribute#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see statemachine.StateAttributeType
	 * @see #getType()
	 * @generated
	 */
	void setType(StateAttributeType value);

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
	 * @see statemachine.StatemachinePackage#getStateAttribute_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link statemachine.StateAttribute#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>State Attribute Query</b></em>' containment reference list.
	 * The list contents are of type {@link statemachine.StateAttribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State Attribute Query</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State Attribute Query</em>' containment reference list.
	 * @see statemachine.StatemachinePackage#getStateAttribute_StateAttributeQuery()
	 * @model containment="true"
	 * @generated
	 */
	EList<StateAttribute> getStateAttributeQuery();

} // StateAttribute
