/**
 */
package statemachine.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import statemachine.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see statemachine.StatemachinePackage
 * @generated
 */
public class StatemachineSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static StatemachinePackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatemachineSwitch() {
		if (modelPackage == null) {
			modelPackage = StatemachinePackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case StatemachinePackage.STATEMACHINE: {
				Statemachine statemachine = (Statemachine)theEObject;
				T result = caseStatemachine(statemachine);
				if (result == null) result = caseNamed(statemachine);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StatemachinePackage.NAMED: {
				Named named = (Named)theEObject;
				T result = caseNamed(named);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StatemachinePackage.ABSTRACT_STATE: {
				AbstractState abstractState = (AbstractState)theEObject;
				T result = caseAbstractState(abstractState);
				if (result == null) result = caseNamed(abstractState);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StatemachinePackage.ABSTRACT_TRANSITION: {
				AbstractTransition abstractTransition = (AbstractTransition)theEObject;
				T result = caseAbstractTransition(abstractTransition);
				if (result == null) result = caseNamed(abstractTransition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StatemachinePackage.INITIAL_STATE: {
				InitialState initialState = (InitialState)theEObject;
				T result = caseInitialState(initialState);
				if (result == null) result = caseAbstractState(initialState);
				if (result == null) result = caseNamed(initialState);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StatemachinePackage.FINAL_STATE: {
				FinalState finalState = (FinalState)theEObject;
				T result = caseFinalState(finalState);
				if (result == null) result = caseAbstractState(finalState);
				if (result == null) result = caseNamed(finalState);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StatemachinePackage.STATE: {
				State state = (State)theEObject;
				T result = caseState(state);
				if (result == null) result = caseAbstractState(state);
				if (result == null) result = caseNamed(state);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StatemachinePackage.TRANSITION: {
				Transition transition = (Transition)theEObject;
				T result = caseTransition(transition);
				if (result == null) result = caseAbstractTransition(transition);
				if (result == null) result = caseNamed(transition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StatemachinePackage.CONDITIONAL_TRANSITION: {
				ConditionalTransition conditionalTransition = (ConditionalTransition)theEObject;
				T result = caseConditionalTransition(conditionalTransition);
				if (result == null) result = caseAbstractTransition(conditionalTransition);
				if (result == null) result = caseNamed(conditionalTransition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StatemachinePackage.STATE_VALUE: {
				StateValue stateValue = (StateValue)theEObject;
				T result = caseStateValue(stateValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StatemachinePackage.STATE_ATTRIBUTE: {
				StateAttribute stateAttribute = (StateAttribute)theEObject;
				T result = caseStateAttribute(stateAttribute);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StatemachinePackage.CONDITIONAL_STATE: {
				ConditionalState conditionalState = (ConditionalState)theEObject;
				T result = caseConditionalState(conditionalState);
				if (result == null) result = caseAbstractState(conditionalState);
				if (result == null) result = caseNamed(conditionalState);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StatemachinePackage.STATE_CHANGE: {
				StateChange stateChange = (StateChange)theEObject;
				T result = caseStateChange(stateChange);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StatemachinePackage.ABSTRACT_CONDITION: {
				AbstractCondition abstractCondition = (AbstractCondition)theEObject;
				T result = caseAbstractCondition(abstractCondition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StatemachinePackage.FIELD_CONDITION: {
				FieldCondition fieldCondition = (FieldCondition)theEObject;
				T result = caseFieldCondition(fieldCondition);
				if (result == null) result = caseAbstractCondition(fieldCondition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StatemachinePackage.ATTRIBUTE_CONDITION: {
				AttributeCondition attributeCondition = (AttributeCondition)theEObject;
				T result = caseAttributeCondition(attributeCondition);
				if (result == null) result = caseAbstractCondition(attributeCondition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Statemachine</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Statemachine</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStatemachine(Statemachine object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamed(Named object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract State</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract State</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractState(AbstractState object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Transition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Transition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractTransition(AbstractTransition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Initial State</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Initial State</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInitialState(InitialState object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Final State</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Final State</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFinalState(FinalState object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>State</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>State</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseState(State object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Transition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Transition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTransition(Transition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Conditional Transition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Conditional Transition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConditionalTransition(ConditionalTransition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>State Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>State Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStateValue(StateValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>State Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>State Attribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStateAttribute(StateAttribute object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Conditional State</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Conditional State</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConditionalState(ConditionalState object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>State Change</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>State Change</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStateChange(StateChange object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Condition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Condition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractCondition(AbstractCondition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Field Condition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Field Condition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFieldCondition(FieldCondition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Attribute Condition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Attribute Condition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttributeCondition(AttributeCondition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //StatemachineSwitch
