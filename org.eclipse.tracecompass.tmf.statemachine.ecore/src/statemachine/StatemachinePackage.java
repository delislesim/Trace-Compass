/**
 */
package statemachine;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see statemachine.StatemachineFactory
 * @model kind="package"
 * @generated
 */
public interface StatemachinePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "statemachine";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "org.eclipse.tracecompass.tmf.statemachine";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "statemachine";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StatemachinePackage eINSTANCE = statemachine.impl.StatemachinePackageImpl.init();

	/**
	 * The meta object id for the '{@link statemachine.impl.NamedImpl <em>Named</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see statemachine.impl.NamedImpl
	 * @see statemachine.impl.StatemachinePackageImpl#getNamed()
	 * @generated
	 */
	int NAMED = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED__NAME = 0;

	/**
	 * The number of structural features of the '<em>Named</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Named</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link statemachine.impl.StatemachineImpl <em>Statemachine</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see statemachine.impl.StatemachineImpl
	 * @see statemachine.impl.StatemachinePackageImpl#getStatemachine()
	 * @generated
	 */
	int STATEMACHINE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMACHINE__NAME = NAMED__NAME;

	/**
	 * The feature id for the '<em><b>States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMACHINE__STATES = NAMED_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Associated Tree</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMACHINE__ASSOCIATED_TREE = NAMED_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Associated Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMACHINE__ASSOCIATED_ATTRIBUTE = NAMED_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Statemachine</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMACHINE_FEATURE_COUNT = NAMED_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Statemachine</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMACHINE_OPERATION_COUNT = NAMED_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link statemachine.impl.AbstractStateImpl <em>Abstract State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see statemachine.impl.AbstractStateImpl
	 * @see statemachine.impl.StatemachinePackageImpl#getAbstractState()
	 * @generated
	 */
	int ABSTRACT_STATE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_STATE__NAME = NAMED__NAME;

	/**
	 * The feature id for the '<em><b>Transitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_STATE__TRANSITIONS = NAMED_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Abstract State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_STATE_FEATURE_COUNT = NAMED_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Abstract State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_STATE_OPERATION_COUNT = NAMED_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link statemachine.impl.AbstractTransitionImpl <em>Abstract Transition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see statemachine.impl.AbstractTransitionImpl
	 * @see statemachine.impl.StatemachinePackageImpl#getAbstractTransition()
	 * @generated
	 */
	int ABSTRACT_TRANSITION = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TRANSITION__NAME = NAMED__NAME;

	/**
	 * The feature id for the '<em><b>State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TRANSITION__STATE = NAMED_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>State Change</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TRANSITION__STATE_CHANGE = NAMED_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Abstract Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TRANSITION_FEATURE_COUNT = NAMED_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Abstract Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TRANSITION_OPERATION_COUNT = NAMED_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link statemachine.impl.InitialStateImpl <em>Initial State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see statemachine.impl.InitialStateImpl
	 * @see statemachine.impl.StatemachinePackageImpl#getInitialState()
	 * @generated
	 */
	int INITIAL_STATE = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIAL_STATE__NAME = ABSTRACT_STATE__NAME;

	/**
	 * The feature id for the '<em><b>Transitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIAL_STATE__TRANSITIONS = ABSTRACT_STATE__TRANSITIONS;

	/**
	 * The number of structural features of the '<em>Initial State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIAL_STATE_FEATURE_COUNT = ABSTRACT_STATE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Initial State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIAL_STATE_OPERATION_COUNT = ABSTRACT_STATE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link statemachine.impl.FinalStateImpl <em>Final State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see statemachine.impl.FinalStateImpl
	 * @see statemachine.impl.StatemachinePackageImpl#getFinalState()
	 * @generated
	 */
	int FINAL_STATE = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_STATE__NAME = ABSTRACT_STATE__NAME;

	/**
	 * The feature id for the '<em><b>Transitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_STATE__TRANSITIONS = ABSTRACT_STATE__TRANSITIONS;

	/**
	 * The number of structural features of the '<em>Final State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_STATE_FEATURE_COUNT = ABSTRACT_STATE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Final State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_STATE_OPERATION_COUNT = ABSTRACT_STATE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link statemachine.impl.StateImpl <em>State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see statemachine.impl.StateImpl
	 * @see statemachine.impl.StatemachinePackageImpl#getState()
	 * @generated
	 */
	int STATE = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__NAME = ABSTRACT_STATE__NAME;

	/**
	 * The feature id for the '<em><b>Transitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__TRANSITIONS = ABSTRACT_STATE__TRANSITIONS;

	/**
	 * The feature id for the '<em><b>State Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__STATE_COLOR = ABSTRACT_STATE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FEATURE_COUNT = ABSTRACT_STATE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_OPERATION_COUNT = ABSTRACT_STATE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link statemachine.impl.TransitionImpl <em>Transition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see statemachine.impl.TransitionImpl
	 * @see statemachine.impl.StatemachinePackageImpl#getTransition()
	 * @generated
	 */
	int TRANSITION = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__NAME = ABSTRACT_TRANSITION__NAME;

	/**
	 * The feature id for the '<em><b>State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__STATE = ABSTRACT_TRANSITION__STATE;

	/**
	 * The feature id for the '<em><b>State Change</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__STATE_CHANGE = ABSTRACT_TRANSITION__STATE_CHANGE;

	/**
	 * The number of structural features of the '<em>Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_FEATURE_COUNT = ABSTRACT_TRANSITION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_OPERATION_COUNT = ABSTRACT_TRANSITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link statemachine.impl.ConditionalTransitionImpl <em>Conditional Transition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see statemachine.impl.ConditionalTransitionImpl
	 * @see statemachine.impl.StatemachinePackageImpl#getConditionalTransition()
	 * @generated
	 */
	int CONDITIONAL_TRANSITION = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_TRANSITION__NAME = ABSTRACT_TRANSITION__NAME;

	/**
	 * The feature id for the '<em><b>State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_TRANSITION__STATE = ABSTRACT_TRANSITION__STATE;

	/**
	 * The feature id for the '<em><b>State Change</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_TRANSITION__STATE_CHANGE = ABSTRACT_TRANSITION__STATE_CHANGE;

	/**
	 * The number of structural features of the '<em>Conditional Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_TRANSITION_FEATURE_COUNT = ABSTRACT_TRANSITION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Conditional Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_TRANSITION_OPERATION_COUNT = ABSTRACT_TRANSITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link statemachine.impl.StateValueImpl <em>State Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see statemachine.impl.StateValueImpl
	 * @see statemachine.impl.StatemachinePackageImpl#getStateValue()
	 * @generated
	 */
	int STATE_VALUE = 9;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_VALUE__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_VALUE__VALUE = 1;

	/**
	 * The number of structural features of the '<em>State Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_VALUE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>State Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_VALUE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link statemachine.impl.StateAttributeImpl <em>State Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see statemachine.impl.StateAttributeImpl
	 * @see statemachine.impl.StatemachinePackageImpl#getStateAttribute()
	 * @generated
	 */
	int STATE_ATTRIBUTE = 10;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_ATTRIBUTE__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_ATTRIBUTE__VALUE = 1;

	/**
	 * The feature id for the '<em><b>State Attribute Query</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_ATTRIBUTE__STATE_ATTRIBUTE_QUERY = 2;

	/**
	 * The number of structural features of the '<em>State Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_ATTRIBUTE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>State Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_ATTRIBUTE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link statemachine.impl.ConditionalStateImpl <em>Conditional State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see statemachine.impl.ConditionalStateImpl
	 * @see statemachine.impl.StatemachinePackageImpl#getConditionalState()
	 * @generated
	 */
	int CONDITIONAL_STATE = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_STATE__NAME = ABSTRACT_STATE__NAME;

	/**
	 * The feature id for the '<em><b>Transitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_STATE__TRANSITIONS = ABSTRACT_STATE__TRANSITIONS;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_STATE__CONDITION = ABSTRACT_STATE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>And Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_STATE__AND_EXPRESSION = ABSTRACT_STATE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Conditions Organization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_STATE__CONDITIONS_ORGANIZATION = ABSTRACT_STATE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Conditional State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_STATE_FEATURE_COUNT = ABSTRACT_STATE_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Conditional State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_STATE_OPERATION_COUNT = ABSTRACT_STATE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link statemachine.impl.StateChangeImpl <em>State Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see statemachine.impl.StateChangeImpl
	 * @see statemachine.impl.StatemachinePackageImpl#getStateChange()
	 * @generated
	 */
	int STATE_CHANGE = 12;

	/**
	 * The feature id for the '<em><b>State Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_CHANGE__STATE_ATTRIBUTE = 0;

	/**
	 * The feature id for the '<em><b>State Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_CHANGE__STATE_VALUE = 1;

	/**
	 * The number of structural features of the '<em>State Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_CHANGE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>State Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_CHANGE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link statemachine.impl.AbstractConditionImpl <em>Abstract Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see statemachine.impl.AbstractConditionImpl
	 * @see statemachine.impl.StatemachinePackageImpl#getAbstractCondition()
	 * @generated
	 */
	int ABSTRACT_CONDITION = 13;

	/**
	 * The feature id for the '<em><b>State Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONDITION__STATE_VALUE = 0;

	/**
	 * The feature id for the '<em><b>Is Not Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONDITION__IS_NOT_CONDITION = 1;

	/**
	 * The number of structural features of the '<em>Abstract Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONDITION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Abstract Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONDITION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link statemachine.impl.FieldConditionImpl <em>Field Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see statemachine.impl.FieldConditionImpl
	 * @see statemachine.impl.StatemachinePackageImpl#getFieldCondition()
	 * @generated
	 */
	int FIELD_CONDITION = 14;

	/**
	 * The feature id for the '<em><b>State Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_CONDITION__STATE_VALUE = ABSTRACT_CONDITION__STATE_VALUE;

	/**
	 * The feature id for the '<em><b>Is Not Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_CONDITION__IS_NOT_CONDITION = ABSTRACT_CONDITION__IS_NOT_CONDITION;

	/**
	 * The feature id for the '<em><b>Field Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_CONDITION__FIELD_NAME = ABSTRACT_CONDITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Field Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_CONDITION_FEATURE_COUNT = ABSTRACT_CONDITION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Field Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_CONDITION_OPERATION_COUNT = ABSTRACT_CONDITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link statemachine.impl.AttributeConditionImpl <em>Attribute Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see statemachine.impl.AttributeConditionImpl
	 * @see statemachine.impl.StatemachinePackageImpl#getAttributeCondition()
	 * @generated
	 */
	int ATTRIBUTE_CONDITION = 15;

	/**
	 * The feature id for the '<em><b>State Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_CONDITION__STATE_VALUE = ABSTRACT_CONDITION__STATE_VALUE;

	/**
	 * The feature id for the '<em><b>Is Not Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_CONDITION__IS_NOT_CONDITION = ABSTRACT_CONDITION__IS_NOT_CONDITION;

	/**
	 * The feature id for the '<em><b>State Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_CONDITION__STATE_ATTRIBUTE = ABSTRACT_CONDITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Attribute Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_CONDITION_FEATURE_COUNT = ABSTRACT_CONDITION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Attribute Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_CONDITION_OPERATION_COUNT = ABSTRACT_CONDITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link statemachine.StateValueType <em>State Value Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see statemachine.StateValueType
	 * @see statemachine.impl.StatemachinePackageImpl#getStateValueType()
	 * @generated
	 */
	int STATE_VALUE_TYPE = 16;

	/**
	 * The meta object id for the '{@link statemachine.StateAttributeType <em>State Attribute Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see statemachine.StateAttributeType
	 * @see statemachine.impl.StatemachinePackageImpl#getStateAttributeType()
	 * @generated
	 */
	int STATE_ATTRIBUTE_TYPE = 17;


	/**
	 * Returns the meta object for class '{@link statemachine.Statemachine <em>Statemachine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Statemachine</em>'.
	 * @see statemachine.Statemachine
	 * @generated
	 */
	EClass getStatemachine();

	/**
	 * Returns the meta object for the containment reference list '{@link statemachine.Statemachine#getStates <em>States</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>States</em>'.
	 * @see statemachine.Statemachine#getStates()
	 * @see #getStatemachine()
	 * @generated
	 */
	EReference getStatemachine_States();

	/**
	 * Returns the meta object for the attribute '{@link statemachine.Statemachine#getAssociatedTree <em>Associated Tree</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Associated Tree</em>'.
	 * @see statemachine.Statemachine#getAssociatedTree()
	 * @see #getStatemachine()
	 * @generated
	 */
	EAttribute getStatemachine_AssociatedTree();

	/**
	 * Returns the meta object for the attribute '{@link statemachine.Statemachine#getAssociatedAttribute <em>Associated Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Associated Attribute</em>'.
	 * @see statemachine.Statemachine#getAssociatedAttribute()
	 * @see #getStatemachine()
	 * @generated
	 */
	EAttribute getStatemachine_AssociatedAttribute();

	/**
	 * Returns the meta object for class '{@link statemachine.Named <em>Named</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named</em>'.
	 * @see statemachine.Named
	 * @generated
	 */
	EClass getNamed();

	/**
	 * Returns the meta object for the attribute '{@link statemachine.Named#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see statemachine.Named#getName()
	 * @see #getNamed()
	 * @generated
	 */
	EAttribute getNamed_Name();

	/**
	 * Returns the meta object for class '{@link statemachine.AbstractState <em>Abstract State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract State</em>'.
	 * @see statemachine.AbstractState
	 * @generated
	 */
	EClass getAbstractState();

	/**
	 * Returns the meta object for the containment reference list '{@link statemachine.AbstractState#getTransitions <em>Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transitions</em>'.
	 * @see statemachine.AbstractState#getTransitions()
	 * @see #getAbstractState()
	 * @generated
	 */
	EReference getAbstractState_Transitions();

	/**
	 * Returns the meta object for class '{@link statemachine.AbstractTransition <em>Abstract Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Transition</em>'.
	 * @see statemachine.AbstractTransition
	 * @generated
	 */
	EClass getAbstractTransition();

	/**
	 * Returns the meta object for the reference '{@link statemachine.AbstractTransition#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>State</em>'.
	 * @see statemachine.AbstractTransition#getState()
	 * @see #getAbstractTransition()
	 * @generated
	 */
	EReference getAbstractTransition_State();

	/**
	 * Returns the meta object for the containment reference list '{@link statemachine.AbstractTransition#getStateChange <em>State Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>State Change</em>'.
	 * @see statemachine.AbstractTransition#getStateChange()
	 * @see #getAbstractTransition()
	 * @generated
	 */
	EReference getAbstractTransition_StateChange();

	/**
	 * Returns the meta object for class '{@link statemachine.InitialState <em>Initial State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Initial State</em>'.
	 * @see statemachine.InitialState
	 * @generated
	 */
	EClass getInitialState();

	/**
	 * Returns the meta object for class '{@link statemachine.FinalState <em>Final State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Final State</em>'.
	 * @see statemachine.FinalState
	 * @generated
	 */
	EClass getFinalState();

	/**
	 * Returns the meta object for class '{@link statemachine.State <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State</em>'.
	 * @see statemachine.State
	 * @generated
	 */
	EClass getState();

	/**
	 * Returns the meta object for the attribute '{@link statemachine.State#getStateColor <em>State Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>State Color</em>'.
	 * @see statemachine.State#getStateColor()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_StateColor();

	/**
	 * Returns the meta object for class '{@link statemachine.Transition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition</em>'.
	 * @see statemachine.Transition
	 * @generated
	 */
	EClass getTransition();

	/**
	 * Returns the meta object for class '{@link statemachine.ConditionalTransition <em>Conditional Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Conditional Transition</em>'.
	 * @see statemachine.ConditionalTransition
	 * @generated
	 */
	EClass getConditionalTransition();

	/**
	 * Returns the meta object for class '{@link statemachine.StateValue <em>State Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State Value</em>'.
	 * @see statemachine.StateValue
	 * @generated
	 */
	EClass getStateValue();

	/**
	 * Returns the meta object for the attribute '{@link statemachine.StateValue#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see statemachine.StateValue#getType()
	 * @see #getStateValue()
	 * @generated
	 */
	EAttribute getStateValue_Type();

	/**
	 * Returns the meta object for the attribute '{@link statemachine.StateValue#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see statemachine.StateValue#getValue()
	 * @see #getStateValue()
	 * @generated
	 */
	EAttribute getStateValue_Value();

	/**
	 * Returns the meta object for class '{@link statemachine.StateAttribute <em>State Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State Attribute</em>'.
	 * @see statemachine.StateAttribute
	 * @generated
	 */
	EClass getStateAttribute();

	/**
	 * Returns the meta object for the attribute '{@link statemachine.StateAttribute#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see statemachine.StateAttribute#getType()
	 * @see #getStateAttribute()
	 * @generated
	 */
	EAttribute getStateAttribute_Type();

	/**
	 * Returns the meta object for the attribute '{@link statemachine.StateAttribute#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see statemachine.StateAttribute#getValue()
	 * @see #getStateAttribute()
	 * @generated
	 */
	EAttribute getStateAttribute_Value();

	/**
	 * Returns the meta object for the containment reference list '{@link statemachine.StateAttribute#getStateAttributeQuery <em>State Attribute Query</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>State Attribute Query</em>'.
	 * @see statemachine.StateAttribute#getStateAttributeQuery()
	 * @see #getStateAttribute()
	 * @generated
	 */
	EReference getStateAttribute_StateAttributeQuery();

	/**
	 * Returns the meta object for class '{@link statemachine.ConditionalState <em>Conditional State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Conditional State</em>'.
	 * @see statemachine.ConditionalState
	 * @generated
	 */
	EClass getConditionalState();

	/**
	 * Returns the meta object for the containment reference list '{@link statemachine.ConditionalState#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Condition</em>'.
	 * @see statemachine.ConditionalState#getCondition()
	 * @see #getConditionalState()
	 * @generated
	 */
	EReference getConditionalState_Condition();

	/**
	 * Returns the meta object for the attribute '{@link statemachine.ConditionalState#isAndExpression <em>And Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>And Expression</em>'.
	 * @see statemachine.ConditionalState#isAndExpression()
	 * @see #getConditionalState()
	 * @generated
	 */
	EAttribute getConditionalState_AndExpression();

	/**
	 * Returns the meta object for the attribute '{@link statemachine.ConditionalState#getConditionsOrganization <em>Conditions Organization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Conditions Organization</em>'.
	 * @see statemachine.ConditionalState#getConditionsOrganization()
	 * @see #getConditionalState()
	 * @generated
	 */
	EAttribute getConditionalState_ConditionsOrganization();

	/**
	 * Returns the meta object for class '{@link statemachine.StateChange <em>State Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State Change</em>'.
	 * @see statemachine.StateChange
	 * @generated
	 */
	EClass getStateChange();

	/**
	 * Returns the meta object for the containment reference list '{@link statemachine.StateChange#getStateAttribute <em>State Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>State Attribute</em>'.
	 * @see statemachine.StateChange#getStateAttribute()
	 * @see #getStateChange()
	 * @generated
	 */
	EReference getStateChange_StateAttribute();

	/**
	 * Returns the meta object for the containment reference '{@link statemachine.StateChange#getStateValue <em>State Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>State Value</em>'.
	 * @see statemachine.StateChange#getStateValue()
	 * @see #getStateChange()
	 * @generated
	 */
	EReference getStateChange_StateValue();

	/**
	 * Returns the meta object for class '{@link statemachine.AbstractCondition <em>Abstract Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Condition</em>'.
	 * @see statemachine.AbstractCondition
	 * @generated
	 */
	EClass getAbstractCondition();

	/**
	 * Returns the meta object for the containment reference '{@link statemachine.AbstractCondition#getStateValue <em>State Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>State Value</em>'.
	 * @see statemachine.AbstractCondition#getStateValue()
	 * @see #getAbstractCondition()
	 * @generated
	 */
	EReference getAbstractCondition_StateValue();

	/**
	 * Returns the meta object for the attribute '{@link statemachine.AbstractCondition#isIsNotCondition <em>Is Not Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Not Condition</em>'.
	 * @see statemachine.AbstractCondition#isIsNotCondition()
	 * @see #getAbstractCondition()
	 * @generated
	 */
	EAttribute getAbstractCondition_IsNotCondition();

	/**
	 * Returns the meta object for class '{@link statemachine.FieldCondition <em>Field Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Field Condition</em>'.
	 * @see statemachine.FieldCondition
	 * @generated
	 */
	EClass getFieldCondition();

	/**
	 * Returns the meta object for the attribute '{@link statemachine.FieldCondition#getFieldName <em>Field Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Field Name</em>'.
	 * @see statemachine.FieldCondition#getFieldName()
	 * @see #getFieldCondition()
	 * @generated
	 */
	EAttribute getFieldCondition_FieldName();

	/**
	 * Returns the meta object for class '{@link statemachine.AttributeCondition <em>Attribute Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute Condition</em>'.
	 * @see statemachine.AttributeCondition
	 * @generated
	 */
	EClass getAttributeCondition();

	/**
	 * Returns the meta object for the containment reference list '{@link statemachine.AttributeCondition#getStateAttribute <em>State Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>State Attribute</em>'.
	 * @see statemachine.AttributeCondition#getStateAttribute()
	 * @see #getAttributeCondition()
	 * @generated
	 */
	EReference getAttributeCondition_StateAttribute();

	/**
	 * Returns the meta object for enum '{@link statemachine.StateValueType <em>State Value Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>State Value Type</em>'.
	 * @see statemachine.StateValueType
	 * @generated
	 */
	EEnum getStateValueType();

	/**
	 * Returns the meta object for enum '{@link statemachine.StateAttributeType <em>State Attribute Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>State Attribute Type</em>'.
	 * @see statemachine.StateAttributeType
	 * @generated
	 */
	EEnum getStateAttributeType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	StatemachineFactory getStatemachineFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link statemachine.impl.StatemachineImpl <em>Statemachine</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see statemachine.impl.StatemachineImpl
		 * @see statemachine.impl.StatemachinePackageImpl#getStatemachine()
		 * @generated
		 */
		EClass STATEMACHINE = eINSTANCE.getStatemachine();

		/**
		 * The meta object literal for the '<em><b>States</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATEMACHINE__STATES = eINSTANCE.getStatemachine_States();

		/**
		 * The meta object literal for the '<em><b>Associated Tree</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATEMACHINE__ASSOCIATED_TREE = eINSTANCE.getStatemachine_AssociatedTree();

		/**
		 * The meta object literal for the '<em><b>Associated Attribute</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATEMACHINE__ASSOCIATED_ATTRIBUTE = eINSTANCE.getStatemachine_AssociatedAttribute();

		/**
		 * The meta object literal for the '{@link statemachine.impl.NamedImpl <em>Named</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see statemachine.impl.NamedImpl
		 * @see statemachine.impl.StatemachinePackageImpl#getNamed()
		 * @generated
		 */
		EClass NAMED = eINSTANCE.getNamed();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED__NAME = eINSTANCE.getNamed_Name();

		/**
		 * The meta object literal for the '{@link statemachine.impl.AbstractStateImpl <em>Abstract State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see statemachine.impl.AbstractStateImpl
		 * @see statemachine.impl.StatemachinePackageImpl#getAbstractState()
		 * @generated
		 */
		EClass ABSTRACT_STATE = eINSTANCE.getAbstractState();

		/**
		 * The meta object literal for the '<em><b>Transitions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_STATE__TRANSITIONS = eINSTANCE.getAbstractState_Transitions();

		/**
		 * The meta object literal for the '{@link statemachine.impl.AbstractTransitionImpl <em>Abstract Transition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see statemachine.impl.AbstractTransitionImpl
		 * @see statemachine.impl.StatemachinePackageImpl#getAbstractTransition()
		 * @generated
		 */
		EClass ABSTRACT_TRANSITION = eINSTANCE.getAbstractTransition();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_TRANSITION__STATE = eINSTANCE.getAbstractTransition_State();

		/**
		 * The meta object literal for the '<em><b>State Change</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_TRANSITION__STATE_CHANGE = eINSTANCE.getAbstractTransition_StateChange();

		/**
		 * The meta object literal for the '{@link statemachine.impl.InitialStateImpl <em>Initial State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see statemachine.impl.InitialStateImpl
		 * @see statemachine.impl.StatemachinePackageImpl#getInitialState()
		 * @generated
		 */
		EClass INITIAL_STATE = eINSTANCE.getInitialState();

		/**
		 * The meta object literal for the '{@link statemachine.impl.FinalStateImpl <em>Final State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see statemachine.impl.FinalStateImpl
		 * @see statemachine.impl.StatemachinePackageImpl#getFinalState()
		 * @generated
		 */
		EClass FINAL_STATE = eINSTANCE.getFinalState();

		/**
		 * The meta object literal for the '{@link statemachine.impl.StateImpl <em>State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see statemachine.impl.StateImpl
		 * @see statemachine.impl.StatemachinePackageImpl#getState()
		 * @generated
		 */
		EClass STATE = eINSTANCE.getState();

		/**
		 * The meta object literal for the '<em><b>State Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__STATE_COLOR = eINSTANCE.getState_StateColor();

		/**
		 * The meta object literal for the '{@link statemachine.impl.TransitionImpl <em>Transition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see statemachine.impl.TransitionImpl
		 * @see statemachine.impl.StatemachinePackageImpl#getTransition()
		 * @generated
		 */
		EClass TRANSITION = eINSTANCE.getTransition();

		/**
		 * The meta object literal for the '{@link statemachine.impl.ConditionalTransitionImpl <em>Conditional Transition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see statemachine.impl.ConditionalTransitionImpl
		 * @see statemachine.impl.StatemachinePackageImpl#getConditionalTransition()
		 * @generated
		 */
		EClass CONDITIONAL_TRANSITION = eINSTANCE.getConditionalTransition();

		/**
		 * The meta object literal for the '{@link statemachine.impl.StateValueImpl <em>State Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see statemachine.impl.StateValueImpl
		 * @see statemachine.impl.StatemachinePackageImpl#getStateValue()
		 * @generated
		 */
		EClass STATE_VALUE = eINSTANCE.getStateValue();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE_VALUE__TYPE = eINSTANCE.getStateValue_Type();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE_VALUE__VALUE = eINSTANCE.getStateValue_Value();

		/**
		 * The meta object literal for the '{@link statemachine.impl.StateAttributeImpl <em>State Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see statemachine.impl.StateAttributeImpl
		 * @see statemachine.impl.StatemachinePackageImpl#getStateAttribute()
		 * @generated
		 */
		EClass STATE_ATTRIBUTE = eINSTANCE.getStateAttribute();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE_ATTRIBUTE__TYPE = eINSTANCE.getStateAttribute_Type();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE_ATTRIBUTE__VALUE = eINSTANCE.getStateAttribute_Value();

		/**
		 * The meta object literal for the '<em><b>State Attribute Query</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_ATTRIBUTE__STATE_ATTRIBUTE_QUERY = eINSTANCE.getStateAttribute_StateAttributeQuery();

		/**
		 * The meta object literal for the '{@link statemachine.impl.ConditionalStateImpl <em>Conditional State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see statemachine.impl.ConditionalStateImpl
		 * @see statemachine.impl.StatemachinePackageImpl#getConditionalState()
		 * @generated
		 */
		EClass CONDITIONAL_STATE = eINSTANCE.getConditionalState();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONDITIONAL_STATE__CONDITION = eINSTANCE.getConditionalState_Condition();

		/**
		 * The meta object literal for the '<em><b>And Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONDITIONAL_STATE__AND_EXPRESSION = eINSTANCE.getConditionalState_AndExpression();

		/**
		 * The meta object literal for the '<em><b>Conditions Organization</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONDITIONAL_STATE__CONDITIONS_ORGANIZATION = eINSTANCE.getConditionalState_ConditionsOrganization();

		/**
		 * The meta object literal for the '{@link statemachine.impl.StateChangeImpl <em>State Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see statemachine.impl.StateChangeImpl
		 * @see statemachine.impl.StatemachinePackageImpl#getStateChange()
		 * @generated
		 */
		EClass STATE_CHANGE = eINSTANCE.getStateChange();

		/**
		 * The meta object literal for the '<em><b>State Attribute</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_CHANGE__STATE_ATTRIBUTE = eINSTANCE.getStateChange_StateAttribute();

		/**
		 * The meta object literal for the '<em><b>State Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_CHANGE__STATE_VALUE = eINSTANCE.getStateChange_StateValue();

		/**
		 * The meta object literal for the '{@link statemachine.impl.AbstractConditionImpl <em>Abstract Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see statemachine.impl.AbstractConditionImpl
		 * @see statemachine.impl.StatemachinePackageImpl#getAbstractCondition()
		 * @generated
		 */
		EClass ABSTRACT_CONDITION = eINSTANCE.getAbstractCondition();

		/**
		 * The meta object literal for the '<em><b>State Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_CONDITION__STATE_VALUE = eINSTANCE.getAbstractCondition_StateValue();

		/**
		 * The meta object literal for the '<em><b>Is Not Condition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONDITION__IS_NOT_CONDITION = eINSTANCE.getAbstractCondition_IsNotCondition();

		/**
		 * The meta object literal for the '{@link statemachine.impl.FieldConditionImpl <em>Field Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see statemachine.impl.FieldConditionImpl
		 * @see statemachine.impl.StatemachinePackageImpl#getFieldCondition()
		 * @generated
		 */
		EClass FIELD_CONDITION = eINSTANCE.getFieldCondition();

		/**
		 * The meta object literal for the '<em><b>Field Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_CONDITION__FIELD_NAME = eINSTANCE.getFieldCondition_FieldName();

		/**
		 * The meta object literal for the '{@link statemachine.impl.AttributeConditionImpl <em>Attribute Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see statemachine.impl.AttributeConditionImpl
		 * @see statemachine.impl.StatemachinePackageImpl#getAttributeCondition()
		 * @generated
		 */
		EClass ATTRIBUTE_CONDITION = eINSTANCE.getAttributeCondition();

		/**
		 * The meta object literal for the '<em><b>State Attribute</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTRIBUTE_CONDITION__STATE_ATTRIBUTE = eINSTANCE.getAttributeCondition_StateAttribute();

		/**
		 * The meta object literal for the '{@link statemachine.StateValueType <em>State Value Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see statemachine.StateValueType
		 * @see statemachine.impl.StatemachinePackageImpl#getStateValueType()
		 * @generated
		 */
		EEnum STATE_VALUE_TYPE = eINSTANCE.getStateValueType();

		/**
		 * The meta object literal for the '{@link statemachine.StateAttributeType <em>State Attribute Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see statemachine.StateAttributeType
		 * @see statemachine.impl.StatemachinePackageImpl#getStateAttributeType()
		 * @generated
		 */
		EEnum STATE_ATTRIBUTE_TYPE = eINSTANCE.getStateAttributeType();

	}

} //StatemachinePackage
