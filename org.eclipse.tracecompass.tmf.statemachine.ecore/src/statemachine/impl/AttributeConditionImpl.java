/**
 */
package statemachine.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import statemachine.AttributeCondition;
import statemachine.StateAttribute;
import statemachine.StatemachinePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link statemachine.impl.AttributeConditionImpl#getStateAttribute <em>State Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AttributeConditionImpl extends AbstractConditionImpl implements AttributeCondition {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AttributeConditionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatemachinePackage.Literals.ATTRIBUTE_CONDITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StateAttribute> getStateAttribute() {
		if (stateAttribute == null) {
			stateAttribute = new EObjectContainmentEList<StateAttribute>(StateAttribute.class, this, StatemachinePackage.ATTRIBUTE_CONDITION__STATE_ATTRIBUTE);
		}
		return stateAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StatemachinePackage.ATTRIBUTE_CONDITION__STATE_ATTRIBUTE:
				return ((InternalEList<?>)getStateAttribute()).basicRemove(otherEnd, msgs);
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
			case StatemachinePackage.ATTRIBUTE_CONDITION__STATE_ATTRIBUTE:
				return getStateAttribute();
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
			case StatemachinePackage.ATTRIBUTE_CONDITION__STATE_ATTRIBUTE:
				getStateAttribute().clear();
				getStateAttribute().addAll((Collection<? extends StateAttribute>)newValue);
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
			case StatemachinePackage.ATTRIBUTE_CONDITION__STATE_ATTRIBUTE:
				getStateAttribute().clear();
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
			case StatemachinePackage.ATTRIBUTE_CONDITION__STATE_ATTRIBUTE:
				return stateAttribute != null && !stateAttribute.isEmpty();
		}
		return super.eIsSet(featureID);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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

} //AttributeConditionImpl
