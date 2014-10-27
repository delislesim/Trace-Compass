package org.eclipse.tracecompass.tmf.statemachine.diagram;

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;

public class StateMachineDiagramTypeProvider extends AbstractDiagramTypeProvider {

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
}
