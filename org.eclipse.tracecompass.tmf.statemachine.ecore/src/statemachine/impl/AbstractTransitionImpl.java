/**
 */
package statemachine.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import statemachine.AbstractState;
import statemachine.AbstractTransition;
import statemachine.StateChange;
import statemachine.StatemachinePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Transition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link statemachine.impl.AbstractTransitionImpl#getState <em>State</em>}</li>
 *   <li>{@link statemachine.impl.AbstractTransitionImpl#getStateChange <em>State Change</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractTransitionImpl extends NamedImpl implements AbstractTransition {
	/**
	 * The cached value of the '{@link #getState() <em>State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected AbstractState state;

	/**
	 * The cached value of the '{@link #getStateChange() <em>State Change</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStateChange()
	 * @generated
	 * @ordered
	 */
	protected EList<StateChange> stateChange;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractTransitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatemachinePackage.Literals.ABSTRACT_TRANSITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractState getState() {
		if (state != null && state.eIsProxy()) {
			InternalEObject oldState = (InternalEObject)state;
			state = (AbstractState)eResolveProxy(oldState);
			if (state != oldState) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StatemachinePackage.ABSTRACT_TRANSITION__STATE, oldState, state));
			}
		}
		return state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractState basicGetState() {
		return state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setState(AbstractState newState) {
		AbstractState oldState = state;
		state = newState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatemachinePackage.ABSTRACT_TRANSITION__STATE, oldState, state));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StateChange> getStateChange() {
		if (stateChange == null) {
			stateChange = new EObjectContainmentEList<StateChange>(StateChange.class, this, StatemachinePackage.ABSTRACT_TRANSITION__STATE_CHANGE);
		}
		return stateChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StatemachinePackage.ABSTRACT_TRANSITION__STATE_CHANGE:
				return ((InternalEList<?>)getStateChange()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StatemachinePackage.ABSTRACT_TRANSITION__STATE:
				if (resolve) return getState();
				return basicGetState();
			case StatemachinePackage.ABSTRACT_TRANSITION__STATE_CHANGE:
				return getStateChange();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case StatemachinePackage.ABSTRACT_TRANSITION__STATE:
				setState((AbstractState)newValue);
				return;
			case StatemachinePackage.ABSTRACT_TRANSITION__STATE_CHANGE:
				getStateChange().clear();
				getStateChange().addAll((Collection<? extends StateChange>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case StatemachinePackage.ABSTRACT_TRANSITION__STATE:
				setState((AbstractState)null);
				return;
			case StatemachinePackage.ABSTRACT_TRANSITION__STATE_CHANGE:
				getStateChange().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case StatemachinePackage.ABSTRACT_TRANSITION__STATE:
				return state != null;
			case StatemachinePackage.ABSTRACT_TRANSITION__STATE_CHANGE:
				return stateChange != null && !stateChange.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //AbstractTransitionImpl
