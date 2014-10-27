/**
 */
package statemachine.impl;

import org.eclipse.emf.ecore.EClass;

import statemachine.ConditionalTransition;
import statemachine.StatemachinePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Conditional Transition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class ConditionalTransitionImpl extends AbstractTransitionImpl implements ConditionalTransition {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConditionalTransitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatemachinePackage.Literals.CONDITIONAL_TRANSITION;
	}

} //ConditionalTransitionImpl
