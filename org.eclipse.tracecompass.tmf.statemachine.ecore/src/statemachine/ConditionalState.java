/**
 */
package statemachine;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Conditional State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link statemachine.ConditionalState#getCondition <em>Condition</em>}</li>
 *   <li>{@link statemachine.ConditionalState#isAndExpression <em>And Expression</em>}</li>
 *   <li>{@link statemachine.ConditionalState#getConditionsOrganization <em>Conditions Organization</em>}</li>
 * </ul>
 * </p>
 *
 * @see statemachine.StatemachinePackage#getConditionalState()
 * @model
 * @generated
 */
public interface ConditionalState extends AbstractState {
	/**
	 * Returns the value of the '<em><b>Condition</b></em>' containment reference list.
	 * The list contents are of type {@link statemachine.AbstractCondition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' containment reference list.
	 * @see statemachine.StatemachinePackage#getConditionalState_Condition()
	 * @model containment="true"
	 * @generated
	 */
	EList<AbstractCondition> getCondition();

	/**
	 * Returns the value of the '<em><b>And Expression</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>And Expression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>And Expression</em>' attribute.
	 * @see #setAndExpression(boolean)
	 * @see statemachine.StatemachinePackage#getConditionalState_AndExpression()
	 * @model default="true"
	 * @generated
	 */
	boolean isAndExpression();

	/**
	 * Sets the value of the '{@link statemachine.ConditionalState#isAndExpression <em>And Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>And Expression</em>' attribute.
	 * @see #isAndExpression()
	 * @generated
	 */
	void setAndExpression(boolean value);

	/**
	 * Returns the value of the '<em><b>Conditions Organization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Conditions Organization</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Conditions Organization</em>' attribute.
	 * @see #setConditionsOrganization(String)
	 * @see statemachine.StatemachinePackage#getConditionalState_ConditionsOrganization()
	 * @model
	 * @generated
	 */
	String getConditionsOrganization();

	/**
	 * Sets the value of the '{@link statemachine.ConditionalState#getConditionsOrganization <em>Conditions Organization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Conditions Organization</em>' attribute.
	 * @see #getConditionsOrganization()
	 * @generated
	 */
	void setConditionsOrganization(String value);

} // ConditionalState
