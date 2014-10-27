/**
 */
package statemachine;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see statemachine.StatemachinePackage
 * @generated
 */
public interface StatemachineFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StatemachineFactory eINSTANCE = statemachine.impl.StatemachineFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Statemachine</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Statemachine</em>'.
	 * @generated
	 */
	Statemachine createStatemachine();

	/**
	 * Returns a new object of class '<em>Named</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Named</em>'.
	 * @generated
	 */
	Named createNamed();

	/**
	 * Returns a new object of class '<em>Initial State</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Initial State</em>'.
	 * @generated
	 */
	InitialState createInitialState();

	/**
	 * Returns a new object of class '<em>Final State</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Final State</em>'.
	 * @generated
	 */
	FinalState createFinalState();

	/**
	 * Returns a new object of class '<em>State</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>State</em>'.
	 * @generated
	 */
	State createState();

	/**
	 * Returns a new object of class '<em>Transition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Transition</em>'.
	 * @generated
	 */
	Transition createTransition();

	/**
	 * Returns a new object of class '<em>Conditional Transition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Conditional Transition</em>'.
	 * @generated
	 */
	ConditionalTransition createConditionalTransition();

	/**
	 * Returns a new object of class '<em>State Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>State Value</em>'.
	 * @generated
	 */
	StateValue createStateValue();

	/**
	 * Returns a new object of class '<em>State Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>State Attribute</em>'.
	 * @generated
	 */
	StateAttribute createStateAttribute();

	/**
	 * Returns a new object of class '<em>Conditional State</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Conditional State</em>'.
	 * @generated
	 */
	ConditionalState createConditionalState();

	/**
	 * Returns a new object of class '<em>State Change</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>State Change</em>'.
	 * @generated
	 */
	StateChange createStateChange();

	/**
	 * Returns a new object of class '<em>Abstract Condition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Abstract Condition</em>'.
	 * @generated
	 */
	AbstractCondition createAbstractCondition();

	/**
	 * Returns a new object of class '<em>Field Condition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Field Condition</em>'.
	 * @generated
	 */
	FieldCondition createFieldCondition();

	/**
	 * Returns a new object of class '<em>Attribute Condition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Attribute Condition</em>'.
	 * @generated
	 */
	AttributeCondition createAttributeCondition();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	StatemachinePackage getStatemachinePackage();

} //StatemachineFactory
