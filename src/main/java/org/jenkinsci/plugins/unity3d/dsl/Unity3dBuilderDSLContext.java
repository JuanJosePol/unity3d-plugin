package org.jenkinsci.plugins.unity3d.dsl;

import org.jenkinsci.plugins.unity3d.Unity3dBuilder;

import javaposse.jobdsl.dsl.Context;
import javaposse.jobdsl.dsl.Preconditions;
import jenkins.model.Jenkins;

public class Unity3dBuilderDSLContext implements Context {
	String unstableReturnCodes;
	
	public void unstableReturnCodes(String value) {
		Preconditions.checkArgument(isValueParsable(value), value + "Is not parsable as coma separated integers.");
		this.unstableReturnCodes = value;
	}
	
	private boolean isValueParsable(String value) {
		Unity3dBuilder.DescriptorImpl desriptor = Jenkins.getInstance().getDescriptorByType(Unity3dBuilder.DescriptorImpl.class);
		return desriptor.isUnstableReturnCodesParsable(value);
	}
}
