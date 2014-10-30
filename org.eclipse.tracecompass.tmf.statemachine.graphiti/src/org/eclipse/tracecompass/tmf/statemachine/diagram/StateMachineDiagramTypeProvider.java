package org.eclipse.tracecompass.tmf.statemachine.diagram;

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;

public class StateMachineDiagramTypeProvider extends AbstractDiagramTypeProvider {
	
	private IToolBehaviorProvider[] toolBehaviorProviders;

	public StateMachineDiagramTypeProvider() {
		super();
		setFeatureProvider(new StateMachineFeatureProvider(this));
	}
	
	@Override
	public boolean isAutoUpdateAtRuntime() {
		return true;
	}
	
	@Override
	public boolean isAutoUpdateAtStartup() {
		return true;
	}
	
	@Override
    public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
		if (toolBehaviorProviders == null) {
            toolBehaviorProviders = new IToolBehaviorProvider[] { new StateMachineToolBehaviorProvider(this) };
        }
        return toolBehaviorProviders;
	}
}
