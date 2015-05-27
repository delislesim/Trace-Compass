/**
 */
package statemachine.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import statemachine.AbstractCondition;
import statemachine.AbstractState;
import statemachine.AbstractTransition;
import statemachine.AttributeCondition;
import statemachine.ConditionalState;
import statemachine.ConditionalTransition;
import statemachine.FieldCondition;
import statemachine.FinalState;
import statemachine.InitialState;
import statemachine.Named;
import statemachine.State;
import statemachine.StateAttribute;
import statemachine.StateAttributeType;
import statemachine.StateChange;
import statemachine.StateValue;
import statemachine.StateValueType;
import statemachine.Statemachine;
import statemachine.StatemachineFactory;
import statemachine.StatemachinePackage;
import statemachine.Transition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StatemachinePackageImpl extends EPackageImpl implements StatemachinePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass statemachineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractStateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractTransitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass initialStateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass finalStateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conditionalTransitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateAttributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conditionalStateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateChangeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractConditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fieldConditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeConditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum stateValueTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum stateAttributeTypeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see statemachine.StatemachinePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private StatemachinePackageImpl() {
		super(eNS_URI, StatemachineFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link StatemachinePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static StatemachinePackage init() {
		if (isInited) return (StatemachinePackage)EPackage.Registry.INSTANCE.getEPackage(StatemachinePackage.eNS_URI);

		// Obtain or create and register package
		StatemachinePackageImpl theStatemachinePackage = (StatemachinePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof StatemachinePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new StatemachinePackageImpl());

		isInited = true;

		// Create package meta-data objects
		theStatemachinePackage.createPackageContents();

		// Initialize created meta-data
		theStatemachinePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theStatemachinePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(StatemachinePackage.eNS_URI, theStatemachinePackage);
		return theStatemachinePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStatemachine() {
		return statemachineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStatemachine_States() {
		return (EReference)statemachineEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStatemachine_AssociatedTree() {
		return (EAttribute)statemachineEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStatemachine_AssociatedAttribute() {
		return (EAttribute)statemachineEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamed() {
		return namedEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamed_Name() {
		return (EAttribute)namedEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractState() {
		return abstractStateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractState_Transitions() {
		return (EReference)abstractStateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractTransition() {
		return abstractTransitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractTransition_State() {
		return (EReference)abstractTransitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractTransition_StateChange() {
		return (EReference)abstractTransitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInitialState() {
		return initialStateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFinalState() {
		return finalStateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getState() {
		return stateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getState_StateColor() {
		return (EAttribute)stateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransition() {
		return transitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConditionalTransition() {
		return conditionalTransitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStateValue() {
		return stateValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStateValue_Type() {
		return (EAttribute)stateValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStateValue_Value() {
		return (EAttribute)stateValueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStateAttribute() {
		return stateAttributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStateAttribute_Type() {
		return (EAttribute)stateAttributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStateAttribute_Value() {
		return (EAttribute)stateAttributeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStateAttribute_StateAttributeQuery() {
		return (EReference)stateAttributeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConditionalState() {
		return conditionalStateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConditionalState_Condition() {
		return (EReference)conditionalStateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConditionalState_AndExpression() {
		return (EAttribute)conditionalStateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConditionalState_ConditionsOrganization() {
		return (EAttribute)conditionalStateEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStateChange() {
		return stateChangeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStateChange_StateAttribute() {
		return (EReference)stateChangeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStateChange_StateValue() {
		return (EReference)stateChangeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractCondition() {
		return abstractConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractCondition_StateValue() {
		return (EReference)abstractConditionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractCondition_IsNotCondition() {
		return (EAttribute)abstractConditionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFieldCondition() {
		return fieldConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFieldCondition_FieldName() {
		return (EAttribute)fieldConditionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAttributeCondition() {
		return attributeConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAttributeCondition_StateAttribute() {
		return (EReference)attributeConditionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getStateValueType() {
		return stateValueTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getStateAttributeType() {
		return stateAttributeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatemachineFactory getStatemachineFactory() {
		return (StatemachineFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		statemachineEClass = createEClass(STATEMACHINE);
		createEReference(statemachineEClass, STATEMACHINE__STATES);
		createEAttribute(statemachineEClass, STATEMACHINE__ASSOCIATED_TREE);
		createEAttribute(statemachineEClass, STATEMACHINE__ASSOCIATED_ATTRIBUTE);

		namedEClass = createEClass(NAMED);
		createEAttribute(namedEClass, NAMED__NAME);

		abstractStateEClass = createEClass(ABSTRACT_STATE);
		createEReference(abstractStateEClass, ABSTRACT_STATE__TRANSITIONS);

		abstractTransitionEClass = createEClass(ABSTRACT_TRANSITION);
		createEReference(abstractTransitionEClass, ABSTRACT_TRANSITION__STATE);
		createEReference(abstractTransitionEClass, ABSTRACT_TRANSITION__STATE_CHANGE);

		initialStateEClass = createEClass(INITIAL_STATE);

		finalStateEClass = createEClass(FINAL_STATE);

		stateEClass = createEClass(STATE);
		createEAttribute(stateEClass, STATE__STATE_COLOR);

		transitionEClass = createEClass(TRANSITION);

		conditionalTransitionEClass = createEClass(CONDITIONAL_TRANSITION);

		stateValueEClass = createEClass(STATE_VALUE);
		createEAttribute(stateValueEClass, STATE_VALUE__TYPE);
		createEAttribute(stateValueEClass, STATE_VALUE__VALUE);

		stateAttributeEClass = createEClass(STATE_ATTRIBUTE);
		createEAttribute(stateAttributeEClass, STATE_ATTRIBUTE__TYPE);
		createEAttribute(stateAttributeEClass, STATE_ATTRIBUTE__VALUE);
		createEReference(stateAttributeEClass, STATE_ATTRIBUTE__STATE_ATTRIBUTE_QUERY);

		conditionalStateEClass = createEClass(CONDITIONAL_STATE);
		createEReference(conditionalStateEClass, CONDITIONAL_STATE__CONDITION);
		createEAttribute(conditionalStateEClass, CONDITIONAL_STATE__AND_EXPRESSION);
		createEAttribute(conditionalStateEClass, CONDITIONAL_STATE__CONDITIONS_ORGANIZATION);

		stateChangeEClass = createEClass(STATE_CHANGE);
		createEReference(stateChangeEClass, STATE_CHANGE__STATE_ATTRIBUTE);
		createEReference(stateChangeEClass, STATE_CHANGE__STATE_VALUE);

		abstractConditionEClass = createEClass(ABSTRACT_CONDITION);
		createEReference(abstractConditionEClass, ABSTRACT_CONDITION__STATE_VALUE);
		createEAttribute(abstractConditionEClass, ABSTRACT_CONDITION__IS_NOT_CONDITION);

		fieldConditionEClass = createEClass(FIELD_CONDITION);
		createEAttribute(fieldConditionEClass, FIELD_CONDITION__FIELD_NAME);

		attributeConditionEClass = createEClass(ATTRIBUTE_CONDITION);
		createEReference(attributeConditionEClass, ATTRIBUTE_CONDITION__STATE_ATTRIBUTE);

		// Create enums
		stateValueTypeEEnum = createEEnum(STATE_VALUE_TYPE);
		stateAttributeTypeEEnum = createEEnum(STATE_ATTRIBUTE_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		statemachineEClass.getESuperTypes().add(this.getNamed());
		abstractStateEClass.getESuperTypes().add(this.getNamed());
		abstractTransitionEClass.getESuperTypes().add(this.getNamed());
		initialStateEClass.getESuperTypes().add(this.getAbstractState());
		finalStateEClass.getESuperTypes().add(this.getAbstractState());
		stateEClass.getESuperTypes().add(this.getAbstractState());
		transitionEClass.getESuperTypes().add(this.getAbstractTransition());
		conditionalTransitionEClass.getESuperTypes().add(this.getAbstractTransition());
		conditionalStateEClass.getESuperTypes().add(this.getAbstractState());
		fieldConditionEClass.getESuperTypes().add(this.getAbstractCondition());
		attributeConditionEClass.getESuperTypes().add(this.getAbstractCondition());

		// Initialize classes, features, and operations; add parameters
		initEClass(statemachineEClass, Statemachine.class, "Statemachine", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStatemachine_States(), this.getAbstractState(), null, "states", null, 0, -1, Statemachine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStatemachine_AssociatedTree(), ecorePackage.getEString(), "associatedTree", null, 0, 1, Statemachine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStatemachine_AssociatedAttribute(), ecorePackage.getEString(), "associatedAttribute", null, 0, 1, Statemachine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(namedEClass, Named.class, "Named", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamed_Name(), ecorePackage.getEString(), "name", null, 0, 1, Named.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractStateEClass, AbstractState.class, "AbstractState", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractState_Transitions(), this.getAbstractTransition(), null, "transitions", null, 0, -1, AbstractState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractTransitionEClass, AbstractTransition.class, "AbstractTransition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractTransition_State(), this.getAbstractState(), null, "state", null, 0, 1, AbstractTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractTransition_StateChange(), this.getStateChange(), null, "stateChange", null, 0, -1, AbstractTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(initialStateEClass, InitialState.class, "InitialState", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(finalStateEClass, FinalState.class, "FinalState", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(stateEClass, State.class, "State", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getState_StateColor(), ecorePackage.getEString(), "stateColor", null, 0, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transitionEClass, Transition.class, "Transition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(conditionalTransitionEClass, ConditionalTransition.class, "ConditionalTransition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(stateValueEClass, StateValue.class, "StateValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStateValue_Type(), this.getStateValueType(), "type", null, 0, 1, StateValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStateValue_Value(), ecorePackage.getEString(), "value", null, 0, 1, StateValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stateAttributeEClass, StateAttribute.class, "StateAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStateAttribute_Type(), this.getStateAttributeType(), "type", null, 0, 1, StateAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStateAttribute_Value(), ecorePackage.getEString(), "value", null, 0, 1, StateAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStateAttribute_StateAttributeQuery(), this.getStateAttribute(), null, "stateAttributeQuery", null, 0, -1, StateAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(conditionalStateEClass, ConditionalState.class, "ConditionalState", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConditionalState_Condition(), this.getAbstractCondition(), null, "condition", null, 0, -1, ConditionalState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConditionalState_AndExpression(), ecorePackage.getEBoolean(), "andExpression", "true", 0, 1, ConditionalState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConditionalState_ConditionsOrganization(), ecorePackage.getEString(), "conditionsOrganization", null, 0, 1, ConditionalState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stateChangeEClass, StateChange.class, "StateChange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStateChange_StateAttribute(), this.getStateAttribute(), null, "stateAttribute", null, 0, -1, StateChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStateChange_StateValue(), this.getStateValue(), null, "stateValue", null, 0, 1, StateChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractConditionEClass, AbstractCondition.class, "AbstractCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractCondition_StateValue(), this.getStateValue(), null, "stateValue", null, 0, 1, AbstractCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractCondition_IsNotCondition(), ecorePackage.getEBoolean(), "isNotCondition", null, 0, 1, AbstractCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fieldConditionEClass, FieldCondition.class, "FieldCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFieldCondition_FieldName(), ecorePackage.getEString(), "fieldName", null, 0, 1, FieldCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attributeConditionEClass, AttributeCondition.class, "AttributeCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAttributeCondition_StateAttribute(), this.getStateAttribute(), null, "stateAttribute", null, 0, -1, AttributeCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(stateValueTypeEEnum, StateValueType.class, "StateValueType");
		addEEnumLiteral(stateValueTypeEEnum, StateValueType.INT);
		addEEnumLiteral(stateValueTypeEEnum, StateValueType.NULL);
		addEEnumLiteral(stateValueTypeEEnum, StateValueType.STRING);
		addEEnumLiteral(stateValueTypeEEnum, StateValueType.LONG);
		addEEnumLiteral(stateValueTypeEEnum, StateValueType.EVENT_FIELD);
		addEEnumLiteral(stateValueTypeEEnum, StateValueType.EVENT_NAME);
		addEEnumLiteral(stateValueTypeEEnum, StateValueType.DELETE);
		addEEnumLiteral(stateValueTypeEEnum, StateValueType.QUERY);
		addEEnumLiteral(stateValueTypeEEnum, StateValueType.DEFINED_STATE);

		initEEnum(stateAttributeTypeEEnum, StateAttributeType.class, "StateAttributeType");
		addEEnumLiteral(stateAttributeTypeEEnum, StateAttributeType.NULL);
		addEEnumLiteral(stateAttributeTypeEEnum, StateAttributeType.CONSTANT);
		addEEnumLiteral(stateAttributeTypeEEnum, StateAttributeType.EVENT_FIELD);
		addEEnumLiteral(stateAttributeTypeEEnum, StateAttributeType.LOCATION);
		addEEnumLiteral(stateAttributeTypeEEnum, StateAttributeType.QUERY);

		// Create resource
		createResource(eNS_URI);
	}

} //StatemachinePackageImpl
