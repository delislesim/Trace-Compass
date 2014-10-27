/**
 */
package statemachine.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import statemachine.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StatemachineFactoryImpl extends EFactoryImpl implements StatemachineFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static StatemachineFactory init() {
		try {
			StatemachineFactory theStatemachineFactory = (StatemachineFactory)EPackage.Registry.INSTANCE.getEFactory(StatemachinePackage.eNS_URI);
			if (theStatemachineFactory != null) {
				return theStatemachineFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new StatemachineFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatemachineFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case StatemachinePackage.STATEMACHINE: return createStatemachine();
			case StatemachinePackage.NAMED: return createNamed();
			case StatemachinePackage.INITIAL_STATE: return createInitialState();
			case StatemachinePackage.FINAL_STATE: return createFinalState();
			case StatemachinePackage.STATE: return createState();
			case StatemachinePackage.TRANSITION: return createTransition();
			case StatemachinePackage.CONDITIONAL_TRANSITION: return createConditionalTransition();
			case StatemachinePackage.STATE_VALUE: return createStateValue();
			case StatemachinePackage.STATE_ATTRIBUTE: return createStateAttribute();
			case StatemachinePackage.CONDITIONAL_STATE: return createConditionalState();
			case StatemachinePackage.STATE_CHANGE: return createStateChange();
			case StatemachinePackage.ABSTRACT_CONDITION: return createAbstractCondition();
			case StatemachinePackage.FIELD_CONDITION: return createFieldCondition();
			case StatemachinePackage.ATTRIBUTE_CONDITION: return createAttributeCondition();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case StatemachinePackage.STATE_VALUE_TYPE:
				return createStateValueTypeFromString(eDataType, initialValue);
			case StatemachinePackage.STATE_ATTRIBUTE_TYPE:
				return createStateAttributeTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case StatemachinePackage.STATE_VALUE_TYPE:
				return convertStateValueTypeToString(eDataType, instanceValue);
			case StatemachinePackage.STATE_ATTRIBUTE_TYPE:
				return convertStateAttributeTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Statemachine createStatemachine() {
		StatemachineImpl statemachine = new StatemachineImpl();
		return statemachine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Named createNamed() {
		NamedImpl named = new NamedImpl();
		return named;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InitialState createInitialState() {
		InitialStateImpl initialState = new InitialStateImpl();
		return initialState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FinalState createFinalState() {
		FinalStateImpl finalState = new FinalStateImpl();
		return finalState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State createState() {
		StateImpl state = new StateImpl();
		return state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Transition createTransition() {
		TransitionImpl transition = new TransitionImpl();
		return transition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConditionalTransition createConditionalTransition() {
		ConditionalTransitionImpl conditionalTransition = new ConditionalTransitionImpl();
		return conditionalTransition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateValue createStateValue() {
		StateValueImpl stateValue = new StateValueImpl();
		return stateValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateAttribute createStateAttribute() {
		StateAttributeImpl stateAttribute = new StateAttributeImpl();
		return stateAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConditionalState createConditionalState() {
		ConditionalStateImpl conditionalState = new ConditionalStateImpl();
		return conditionalState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateChange createStateChange() {
		StateChangeImpl stateChange = new StateChangeImpl();
		return stateChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractCondition createAbstractCondition() {
		AbstractConditionImpl abstractCondition = new AbstractConditionImpl();
		return abstractCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldCondition createFieldCondition() {
		FieldConditionImpl fieldCondition = new FieldConditionImpl();
		return fieldCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeCondition createAttributeCondition() {
		AttributeConditionImpl attributeCondition = new AttributeConditionImpl();
		return attributeCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateValueType createStateValueTypeFromString(EDataType eDataType, String initialValue) {
		StateValueType result = StateValueType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertStateValueTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateAttributeType createStateAttributeTypeFromString(EDataType eDataType, String initialValue) {
		StateAttributeType result = StateAttributeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertStateAttributeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatemachinePackage getStatemachinePackage() {
		return (StatemachinePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static StatemachinePackage getPackage() {
		return StatemachinePackage.eINSTANCE;
	}

} //StatemachineFactoryImpl
