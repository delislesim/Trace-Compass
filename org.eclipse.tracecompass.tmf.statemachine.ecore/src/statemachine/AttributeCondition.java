/**
 */
package statemachine;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link statemachine.AttributeCondition#getStateAttribute <em>State Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see statemachine.StatemachinePackage#getAttributeCondition()
 * @model
 * @generated
 */
public interface AttributeCondition extends AbstractCondition {
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
	 * @see statemachine.StatemachinePackage#getAttributeCondition_StateAttribute()
	 * @model containment="true"
	 * @generated
	 */
	EList<StateAttribute> getStateAttribute();

} // AttributeCondition
