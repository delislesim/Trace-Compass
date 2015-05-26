/**
 */
package statemachine;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link statemachine.State#getStateColor <em>State Color</em>}</li>
 * </ul>
 * </p>
 *
 * @see statemachine.StatemachinePackage#getState()
 * @model
 * @generated
 */
public interface State extends AbstractState {

	/**
	 * Returns the value of the '<em><b>State Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State Color</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State Color</em>' attribute.
	 * @see #setStateColor(String)
	 * @see statemachine.StatemachinePackage#getState_StateColor()
	 * @model
	 * @generated
	 */
	String getStateColor();

	/**
	 * Sets the value of the '{@link statemachine.State#getStateColor <em>State Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State Color</em>' attribute.
	 * @see #getStateColor()
	 * @generated
	 */
	void setStateColor(String value);
} // State
