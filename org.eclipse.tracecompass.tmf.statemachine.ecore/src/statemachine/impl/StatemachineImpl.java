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
import statemachine.Statemachine;
import statemachine.StatemachinePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Statemachine</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link statemachine.impl.StatemachineImpl#getStates <em>States</em>}</li>
 *   <li>{@link statemachine.impl.StatemachineImpl#getAssociatedTree <em>Associated Tree</em>}</li>
 *   <li>{@link statemachine.impl.StatemachineImpl#getAssociatedAttribute <em>Associated Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StatemachineImpl extends NamedImpl implements Statemachine {
	/**
	 * The cached value of the '{@link #getStates() <em>States</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStates()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractState> states;

	/**
	 * The default value of the '{@link #getAssociatedTree() <em>Associated Tree</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociatedTree()
	 * @generated
	 * @ordered
	 */
	protected static final String ASSOCIATED_TREE_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getAssociatedTree() <em>Associated Tree</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociatedTree()
	 * @generated
	 * @ordered
	 */
	protected String associatedTree = ASSOCIATED_TREE_EDEFAULT;
	/**
	 * The default value of the '{@link #getAssociatedAttribute() <em>Associated Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociatedAttribute()
	 * @generated
	 * @ordered
	 */
	protected static final String ASSOCIATED_ATTRIBUTE_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getAssociatedAttribute() <em>Associated Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociatedAttribute()
	 * @generated
	 * @ordered
	 */
	protected String associatedAttribute = ASSOCIATED_ATTRIBUTE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StatemachineImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatemachinePackage.Literals.STATEMACHINE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractState> getStates() {
		if (states == null) {
			states = new EObjectContainmentEList<AbstractState>(AbstractState.class, this, StatemachinePackage.STATEMACHINE__STATES);
		}
		return states;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAssociatedTree() {
		return associatedTree;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssociatedTree(String newAssociatedTree) {
		String oldAssociatedTree = associatedTree;
		associatedTree = newAssociatedTree;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatemachinePackage.STATEMACHINE__ASSOCIATED_TREE, oldAssociatedTree, associatedTree));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAssociatedAttribute() {
		return associatedAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssociatedAttribute(String newAssociatedAttribute) {
		String oldAssociatedAttribute = associatedAttribute;
		associatedAttribute = newAssociatedAttribute;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatemachinePackage.STATEMACHINE__ASSOCIATED_ATTRIBUTE, oldAssociatedAttribute, associatedAttribute));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StatemachinePackage.STATEMACHINE__STATES:
				return ((InternalEList<?>)getStates()).basicRemove(otherEnd, msgs);
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
			case StatemachinePackage.STATEMACHINE__STATES:
				return getStates();
			case StatemachinePackage.STATEMACHINE__ASSOCIATED_TREE:
				return getAssociatedTree();
			case StatemachinePackage.STATEMACHINE__ASSOCIATED_ATTRIBUTE:
				return getAssociatedAttribute();
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
			case StatemachinePackage.STATEMACHINE__STATES:
				getStates().clear();
				getStates().addAll((Collection<? extends AbstractState>)newValue);
				return;
			case StatemachinePackage.STATEMACHINE__ASSOCIATED_TREE:
				setAssociatedTree((String)newValue);
				return;
			case StatemachinePackage.STATEMACHINE__ASSOCIATED_ATTRIBUTE:
				setAssociatedAttribute((String)newValue);
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
			case StatemachinePackage.STATEMACHINE__STATES:
				getStates().clear();
				return;
			case StatemachinePackage.STATEMACHINE__ASSOCIATED_TREE:
				setAssociatedTree(ASSOCIATED_TREE_EDEFAULT);
				return;
			case StatemachinePackage.STATEMACHINE__ASSOCIATED_ATTRIBUTE:
				setAssociatedAttribute(ASSOCIATED_ATTRIBUTE_EDEFAULT);
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
			case StatemachinePackage.STATEMACHINE__STATES:
				return states != null && !states.isEmpty();
			case StatemachinePackage.STATEMACHINE__ASSOCIATED_TREE:
				return ASSOCIATED_TREE_EDEFAULT == null ? associatedTree != null : !ASSOCIATED_TREE_EDEFAULT.equals(associatedTree);
			case StatemachinePackage.STATEMACHINE__ASSOCIATED_ATTRIBUTE:
				return ASSOCIATED_ATTRIBUTE_EDEFAULT == null ? associatedAttribute != null : !ASSOCIATED_ATTRIBUTE_EDEFAULT.equals(associatedAttribute);
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
		result.append(" (associatedTree: ");
		result.append(associatedTree);
		result.append(", associatedAttribute: ");
		result.append(associatedAttribute);
		result.append(')');
		return result.toString();
	}

} //StatemachineImpl
