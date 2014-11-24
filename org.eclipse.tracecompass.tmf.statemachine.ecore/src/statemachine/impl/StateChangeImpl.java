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
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import statemachine.StateAttribute;
import statemachine.StateChange;
import statemachine.StateValue;
import statemachine.StatemachinePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>State Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link statemachine.impl.StateChangeImpl#getStateAttribute <em>State Attribute</em>}</li>
 *   <li>{@link statemachine.impl.StateChangeImpl#getStateValue <em>State Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StateChangeImpl extends MinimalEObjectImpl.Container implements StateChange {
	/**
	 * The cached value of the '{@link #getStateAttribute() <em>State Attribute</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStateAttribute()
	 * @generated
	 * @ordered
	 */
	protected EList<StateAttribute> stateAttribute;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StateChangeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatemachinePackage.Literals.STATE_CHANGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StateAttribute> getStateAttribute() {
		if (stateAttribute == null) {
			stateAttribute = new EObjectContainmentEList<StateAttribute>(StateAttribute.class, this, StatemachinePackage.STATE_CHANGE__STATE_ATTRIBUTE);
		}
		return stateAttribute;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StatemachinePackage.STATE_CHANGE__STATE_VALUE, oldStateValue, newStateValue);
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
				msgs = ((InternalEObject)stateValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StatemachinePackage.STATE_CHANGE__STATE_VALUE, null, msgs);
			if (newStateValue != null)
				msgs = ((InternalEObject)newStateValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StatemachinePackage.STATE_CHANGE__STATE_VALUE, null, msgs);
			msgs = basicSetStateValue(newStateValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatemachinePackage.STATE_CHANGE__STATE_VALUE, newStateValue, newStateValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StatemachinePackage.STATE_CHANGE__STATE_ATTRIBUTE:
				return ((InternalEList<?>)getStateAttribute()).basicRemove(otherEnd, msgs);
			case StatemachinePackage.STATE_CHANGE__STATE_VALUE:
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
			case StatemachinePackage.STATE_CHANGE__STATE_ATTRIBUTE:
				return getStateAttribute();
			case StatemachinePackage.STATE_CHANGE__STATE_VALUE:
				return getStateValue();
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
			case StatemachinePackage.STATE_CHANGE__STATE_ATTRIBUTE:
				getStateAttribute().clear();
				getStateAttribute().addAll((Collection<? extends StateAttribute>)newValue);
				return;
			case StatemachinePackage.STATE_CHANGE__STATE_VALUE:
				setStateValue((StateValue)newValue);
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
			case StatemachinePackage.STATE_CHANGE__STATE_ATTRIBUTE:
				getStateAttribute().clear();
				return;
			case StatemachinePackage.STATE_CHANGE__STATE_VALUE:
				setStateValue((StateValue)null);
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
			case StatemachinePackage.STATE_CHANGE__STATE_ATTRIBUTE:
				return stateAttribute != null && !stateAttribute.isEmpty();
			case StatemachinePackage.STATE_CHANGE__STATE_VALUE:
				return stateValue != null;
		}
		return super.eIsSet(featureID);
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		String toString = "";
		for(StateAttribute attribute : stateAttribute) {
			toString += "/" + attribute.getValue();
		}
		toString += " = " + ((!stateValue.getValue().equals("")) ? stateValue.getValue() : "null");
		
		return toString;
	}

} //StateChangeImpl
