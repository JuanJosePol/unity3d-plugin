package org.jenkinsci.plugins.unity3d.dsl;

import javaposse.jobdsl.dsl.Context;

public class Unity3dBuilderDSLContext implements Context {
	String unstableReturnCodes;
	
	public void unstableReturnCodes(String value) {
		this.unstableReturnCodes = value;
	}
}
