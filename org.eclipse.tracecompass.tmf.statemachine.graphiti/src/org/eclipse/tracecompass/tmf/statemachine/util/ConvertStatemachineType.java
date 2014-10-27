package org.eclipse.tracecompass.tmf.statemachine.util;

import java.util.List;

import statemachine.StateAttributeType;
import statemachine.StateValueType;


public class ConvertStatemachineType {
	
	private List<StateValueType> stateValueTypeList;
	private String[] stateValueTypeString;
	
	private List<StateAttributeType> stateAttributeTypeList;
	private String[] stateAttributeTypeString; 
	
	public ConvertStatemachineType() {
		stateValueTypeList = StateValueType.VALUES;
		stateAttributeTypeList = StateAttributeType.VALUES;
		
		stateValueTypeString = new String[stateValueTypeList.size()];
        for(int i = 0; i < stateValueTypeList.size(); i++) {
        	stateValueTypeString[i] = stateValueTypeList.get(i).toString();
        }
        
        stateAttributeTypeString = new String[stateAttributeTypeList.size()];
        for(int i = 0; i < stateAttributeTypeList.size(); i++) {
        	stateAttributeTypeString[i] = stateAttributeTypeList.get(i).toString();
        }
	}
	
	public String[] getStateValueTypeString() {
		return stateValueTypeString;
	}
	
	public StateValueType getStateValueTypeFromindex(int index) {
		return StateValueType.getByName(stateValueTypeString[index]);
	}
	
	public String[] getStateAttributeTypeString() {
		return stateAttributeTypeString;
	}
	
	public StateAttributeType getAttributeTypeFromindex(int index) {
		return StateAttributeType.getByName(stateAttributeTypeString[index]);
	}
	
	public int getIndexFromAttributeType (StateAttributeType type) {
		
		for(int i = 0; i < stateAttributeTypeString.length; i++) {
			if(stateAttributeTypeString[i].equals(type.getName())) {
				return i;
			}
		}
		return 0;
	}
	
	
}
