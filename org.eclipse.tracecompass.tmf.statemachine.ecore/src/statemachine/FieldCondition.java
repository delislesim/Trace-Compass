/**
 */
package statemachine;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link statemachine.FieldCondition#getFieldName <em>Field Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see statemachine.StatemachinePackage#getFieldCondition()
 * @model
 * @generated
 */
public interface FieldCondition extends AbstractCondition {
	/**
	 * Returns the value of the '<em><b>Field Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Field Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Field Name</em>' attribute.
	 * @see #setFieldName(String)
	 * @see statemachine.StatemachinePackage#getFieldCondition_FieldName()
	 * @model
	 * @generated
	 */
	String getFieldName();

	/**
	 * Sets the value of the '{@link statemachine.FieldCondition#getFieldName <em>Field Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field Name</em>' attribute.
	 * @see #getFieldName()
	 * @generated
	 */
	void setFieldName(String value);

} // FieldCondition
