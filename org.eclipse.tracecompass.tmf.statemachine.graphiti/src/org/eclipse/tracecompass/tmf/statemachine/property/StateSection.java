package org.eclipse.tracecompass.tmf.statemachine.property;

import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.impl.AbstractFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.GFPropertySection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import statemachine.AbstractState;
import statemachine.State;

public class StateSection extends GFPropertySection implements ITabbedPropertyConstants {
	private Text stateNameText;
	private Label colorLabel;
	private static RGB DEFAULT_COLOR = new RGB(0, 0, 255);
	private RGB stateColor = DEFAULT_COLOR;
	
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        
        TabbedPropertySheetWidgetFactory factory = getWidgetFactory();
        final Composite composite = factory.createFlatFormComposite(parent);
        composite.setLayout(new GridLayout(2, false));
        
        GridData gridData;
        
        Label label = factory.createLabel(composite, "Name");
        
        stateNameText = factory.createText(composite, "");
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = true;
        stateNameText.setLayoutData(gridData);
        
        stateNameText.addModifyListener(new ModifyListener() {

        	@Override
        	public void modifyText(ModifyEvent e) {
        		String newStateName = stateNameText.getText();
        		if (newStateName == null) {
        			newStateName = "";
        		}
        		PictogramElement pe = getSelectedPictogramElement();
        		if (pe != null) {
        			Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
        			if (bo == null)
        				return;
        			String ActualStateName = null;
        			if(bo instanceof AbstractState) {
        				ActualStateName = ((AbstractState) bo).getName();
        			}
        			if (newStateName.equals(ActualStateName))
        				return;
        		}
        		final String stateName = newStateName;
        		IFeature feature = new AbstractFeature(getDiagramTypeProvider().getFeatureProvider()) {
        				
        			@Override
        			public void execute(IContext context) {
        				PictogramElement pe = getSelectedPictogramElement();
        				if (pe != null) {
        					Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
        					if (bo == null)
        						return;
                			if(bo instanceof AbstractState) {
                				((AbstractState) bo).setName(stateName);
                			}        					
        				}
        			}
        			
        			@Override
        			public boolean canExecute(IContext context) {
        				return true;
        			}
        		};
        		CustomContext context = new CustomContext();
        		execute(feature, context);
        	}
        });
        
        // Add color picker for defining state color in generated views
        colorLabel = factory.createLabel(composite, DEFAULT_COLOR.toString());
        colorLabel.setBackground(new Color(composite.getDisplay(), DEFAULT_COLOR));
        colorLabel.setAlignment(SWT.CENTER);
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.widthHint = 130;
        colorLabel.setLayoutData(gridData);
        Button colorButton = factory.createButton(composite, "...", SWT.PUSH);    
        colorButton.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			ColorDialog colorDialog = new ColorDialog(composite.getShell());
    			colorDialog.setText("Choose a Color");
    			colorDialog.setRGB(stateColor);
    			RGB chosenColor = colorDialog.open();
    			if(chosenColor != null) {
    				stateColor = chosenColor;
    				colorLabel.setBackground(new Color(composite.getDisplay(), stateColor));
    				colorLabel.setText(stateColor.toString());
    				saveStateColor();
    			}
    		}
		});
    }
    
    @Override
    public void refresh() {
        PictogramElement pe = getSelectedPictogramElement();
        if (pe != null) {
        	Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
        	if (bo == null)
                return;
        	
        	if(bo instanceof AbstractState) {
        		String stateName = ((AbstractState)bo).getName();
        		stateNameText.setText((stateName != null) ? stateName : "");
        	}
        	
        	if(bo instanceof State) {
        		String stateColorHex = ((State)bo).getStateColor();
				if (stateColorHex != null) {
					stateColor = hexToRgb(stateColorHex);
				} else {
					stateColor = DEFAULT_COLOR;
				}
        		colorLabel.setBackground(new Color(colorLabel.getDisplay(), stateColor));
        		colorLabel.setText(stateColor.toString());
        	}
        }
    }
    
    private void saveStateColor() {
    	IFeature feature = new AbstractFeature(getDiagramTypeProvider().getFeatureProvider()) {
			
			@Override
			public void execute(IContext context) {
				PictogramElement pe = getSelectedPictogramElement();
				if (pe != null) {
					Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
					if (bo == null)
						return;
        			if(bo instanceof State) {
        				((State) bo).setStateColor(rgbToHex(stateColor));
        			}        					
				}
			}
			
			@Override
			public boolean canExecute(IContext context) {
				return true;
			}
		};
		CustomContext context = new CustomContext();
		execute(feature, context);
    }
    
    private String rgbToHex(RGB rgb) {
    	int r = rgb.red;
        int g = rgb.green;
        int b = rgb.blue;
        return "#" + toHexValue(r) + toHexValue(g) + toHexValue(b);
    	//return "#" + Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b);
    }
    
    private String toHexValue(int number) {
        StringBuilder builder = new StringBuilder(Integer.toHexString(number));
        while (builder.length() < 2) {
          builder.append("0");
        }
        return builder.toString().toUpperCase();
    }
    
    // e.g. hex = #FFFFFF
    private RGB hexToRgb(String hex) {
    	int r = Integer.valueOf(hex.substring(1, 3), 16);
        int g = Integer.valueOf(hex.substring(3, 5), 16);
        int b = Integer.valueOf(hex.substring(5, 7), 16);
    	return new RGB(r, g, b);
    }
}
