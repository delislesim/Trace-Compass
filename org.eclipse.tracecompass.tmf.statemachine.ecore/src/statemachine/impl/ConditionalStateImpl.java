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

import statemachine.AbstractCondition;
import statemachine.ConditionalState;
import statemachine.StatemachinePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Conditional State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link statemachine.impl.ConditionalStateImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link statemachine.impl.ConditionalStateImpl#isAndExpression <em>And Expression</em>}</li>
 *   <li>{@link statemachine.impl.ConditionalStateImpl#getConditionsOrganization <em>Conditions Organization</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConditionalStateImpl extends AbstractStateImpl implements ConditionalState {
	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractCondition> condition;

	/**
	 * The default value of the '{@link #isAndExpression() <em>And Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAndExpression()
	 * @generated
	 * @ordered
	 */
	protected static final boolean AND_EXPRESSION_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isAndExpression() <em>And Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAndExpression()
	 * @generated
	 * @ordered
	 */
	protected boolean andExpression = AND_EXPRESSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getConditionsOrganization() <em>Conditions Organization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditionsOrganization()
	 * @generated
	 * @ordered
	 */
	protected static final String CONDITIONS_ORGANIZATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConditionsOrganization() <em>Conditions Organization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditionsOrganization()
	 * @generated
	 * @ordered
	 */
	protected String conditionsOrganization = CONDITIONS_ORGANIZATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConditionalStateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatemachinePackage.Literals.CONDITIONAL_STATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractCondition> getCondition() {
		if (condition == null) {
			condition = new EObjectContainmentEList<AbstractCondition>(AbstractCondition.class, this, StatemachinePackage.CONDITIONAL_STATE__CONDITION);
		}
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAndExpression() {
		return andExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAndExpression(boolean newAndExpression) {
		boolean oldAndExpression = andExpression;
		andExpression = newAndExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatemachinePackage.CONDITIONAL_STATE__AND_EXPRESSION, oldAndExpression, andExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getConditionsOrganization() {
		return conditionsOrganization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConditionsOrganization(String newConditionsOrganization) {
		String oldConditionsOrganization = conditionsOrganization;
		conditionsOrganization = newConditionsOrganization;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatemachinePackage.CONDITIONAL_STATE__CONDITIONS_ORGANIZATION, oldConditionsOrganization, conditionsOrganization));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StatemachinePackage.CONDITIONAL_STATE__CONDITION:
				return ((InternalEList<?>)getCondition()).basicRemove(otherEnd, msgs);
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
			case StatemachinePackage.CONDITIONAL_STATE__CONDITION:
				return getCondition();
			case StatemachinePackage.CONDITIONAL_STATE__AND_EXPRESSION:
				return isAndExpression();
			case StatemachinePackage.CONDITIONAL_STATE__CONDITIONS_ORGANIZATION:
				return getConditionsOrganization();
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
			case StatemachinePackage.CONDITIONAL_STATE__CONDITION:
				getCondition().clear();
				getCondition().addAll((Collection<? extends AbstractCondition>)newValue);
				return;
			case StatemachinePackage.CONDITIONAL_STATE__AND_EXPRESSION:
				setAndExpression((Boolean)newValue);
				return;
			case StatemachinePackage.CONDITIONAL_STATE__CONDITIONS_ORGANIZATION:
				setConditionsOrganization((String)newValue);
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
			case StatemachinePackage.CONDITIONAL_STATE__CONDITION:
				getCondition().clear();
				return;
			case StatemachinePackage.CONDITIONAL_STATE__AND_EXPRESSION:
				setAndExpression(AND_EXPRESSION_EDEFAULT);
				return;
			case StatemachinePackage.CONDITIONAL_STATE__CONDITIONS_ORGANIZATION:
				setConditionsOrganization(CONDITIONS_ORGANIZATION_EDEFAULT);
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
			case StatemachinePackage.CONDITIONAL_STATE__CONDITION:
				return condition != null && !condition.isEmpty();
			case StatemachinePackage.CONDITIONAL_STATE__AND_EXPRESSION:
				return andExpression != AND_EXPRESSION_EDEFAULT;
			case StatemachinePackage.CONDITIONAL_STATE__CONDITIONS_ORGANIZATION:
				return CONDITIONS_ORGANIZATION_EDEFAULT == null ? conditionsOrganization != null : !CONDITIONS_ORGANIZATION_EDEFAULT.equals(conditionsOrganization);
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
		result.append(" (andExpression: ");
		result.append(andExpression);
		result.append(", conditionsOrganization: ");
		result.append(conditionsOrganization);
		result.append(')');
		return result.toString();
	}

} //ConditionalStateImpl
