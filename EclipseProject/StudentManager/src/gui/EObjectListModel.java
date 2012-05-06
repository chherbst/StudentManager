package gui;

import javax.swing.AbstractListModel;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class EObjectListModel extends AbstractListModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2247374062666417439L;
	private EObject eObject;
	private EStructuralFeature feature;

	private AdapterImpl changeAdapter = new AdapterImpl(){
		@Override
	     public void notifyChanged(Notification msg){
			if ( msg.getEventType() == Notification.ADD ){
				fireIntervalAdded(this, msg.getPosition(), msg.getPosition() );
				((EObject)getList().get(msg.getPosition())).eAdapters().add(elementChangeAdapter);
			}else if ( msg.getEventType() == Notification.REMOVE )
				fireIntervalRemoved(this, msg.getPosition(), msg.getPosition() );
	     } 
	};
	
	private AdapterImpl elementChangeAdapter = new AdapterImpl(){
		@Override
	     public void notifyChanged(Notification msg){
			int index = getList().indexOf(msg.getNotifier());
			fireContentsChanged(this, index , index);
	     } 
	};

	public EObjectListModel(EObject eObject, EStructuralFeature feature) {
		this.feature = feature;
		setEObject(eObject);
	}
	
	public void setEObject(EObject eObject) {
		if ( this.eObject != null )
			eObject.eAdapters().remove(changeAdapter);
		eObject.eAdapters().add(changeAdapter);
		this.eObject = eObject;
		for (Object object : getList()) {
			((EObject)object).eAdapters().add(elementChangeAdapter);
		}
		fireContentsChanged(this, 0, getList().size());
	}
	
	private EList<?> getList(){
		return (EList<?>)eObject.eGet(feature);
	}
	
	@Override
	public int getSize() {
		return getList().size();
	}

	@Override
	public Object getElementAt(int index) {
		return getList().get(index);
	}
}
