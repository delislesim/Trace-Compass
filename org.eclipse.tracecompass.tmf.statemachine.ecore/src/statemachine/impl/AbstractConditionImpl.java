/**
 */
package statemachine.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import statemachine.AbstractCondition;
import statemachine.StateValue;
import statemachine.StatemachinePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link statemachine.impl.AbstractConditionImpl#getStateValue <em>State Value</em>}</li>
 *   <li>{@link statemachine.impl.AbstractConditionImpl#isIsNotCondition <em>Is Not Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AbstractConditionImpl extends MinimalEObjectImpl.Container implements AbstractCondition {
	/**
	 * The cached value of the '{@link #getStateValue() <em>State Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStateValue()
	 * @generated
	 * @ordered
	 */
	protected StateValue stateValue;

	/**
	 * The default value of the '{@link #isIsNotCondition() <em>Is Not Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsNotCondition()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_NOT_CONDITION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsNotCondition() <em>Is Not Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsNotCondition()
	 * @generated
	 * @ordered
	 */
	protected boolean isNotCondition = IS_NOT_CONDITION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractConditionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatemachinePackage.Literals.ABSTRACT_CONDITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateValue getStateValue() {
		return stateValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStateValue(StateValue newStateValue, NotificationChain msgs) {
		StateValue oldStateValue = stateValue;
		stateValue = newStateValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StatemachinePackage.ABSTRACT_CONDITION__STATE_VALUE, oldStateValue, newStateValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStateValue(StateValue newStateValue) {
		if (newStateValue != stateValue) {
			NotificationChain msgs = null;
			if (stateValue != null)
				msgs = ((InternalEObject)stateValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StatemachinePackage.ABSTRACT_CONDITION__STATE_VALUE, null, msgs);
			if (newStateValue != null)
				msgs = ((InternalEObject)newStateValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StatemachinePackage.ABSTRACT_CONDITION__STATE_VALUE, null, msgs);
			msgs = basicSetStateValue(newStateValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatemachinePackage.ABSTRACT_CONDITION__STATE_VALUE, newStateValue, newStateValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsNotCondition() {
		return isNotCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsNotCondition(boolean newIsNotCondition) {
		boolean oldIsNotCondition = isNotCondition;
		isNotCondition = newIsNotCondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatemachinePackage.ABSTRACT_CONDITION__IS_NOT_CONDITION, oldIsNotCondition, isNotCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StatemachinePackage.ABSTRACT_CONDITION__STATE_VALUE:
				return basicSetStateValue(null, msgs);
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
			case StatemachinePackage.ABSTRACT_CONDITION__STATE_VALUE:
				return getStateValue();
			case StatemachinePackage.ABSTRACT_CONDITION__IS_NOT_CONDITION:
				return isIsNotCondition();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case StatemachinePackage.ABSTRACT_CONDITION__STATE_VALUE:
				setStateValue((StateValue)newValue);
				return;
			case StatemachinePackage.ABSTRACT_CONDITION__IS_NOT_CONDITION:
				setIsNotCondition((Boolean)newValue);
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
			case StatemachinePackage.ABSTRACT_CONDITION__STATE_VALUE:
				setStateValue((StateValue)null);
				return;
			case StatemachinePackage.ABSTRACT_CONDITION__IS_NOT_CONDITION:
				setIsNotCondition(IS_NOT_CONDITION_EDEFAULT);
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
			case StatemachinePackage.ABSTRACT_CONDITION__STATE_VALUE:
				return stateValue != null;
			case StatemachinePackage.ABSTRACT_CONDITION__IS_NOT_CONDITION:
				return isNotCondition != IS_NOT_CONDITION_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (isNotCondition: ");
		result.append(isNotCondition);
		result.append(')');
		return result.toString();
	}

} //AbstractConditionImpl
