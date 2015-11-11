package org.jenkinsci.plugins.unity3d.dsl;

import org.jenkinsci.plugins.unity3d.Unity3dBuilder;
import org.jenkinsci.plugins.unity3d.Unity3dInstallation;

import groovy.lang.Closure;
import hudson.Extension;
import javaposse.jobdsl.dsl.Preconditions;
import javaposse.jobdsl.dsl.helpers.step.StepContext;
import javaposse.jobdsl.plugin.ContextExtensionPoint;
import javaposse.jobdsl.plugin.DslExtensionMethod;
import jenkins.model.Jenkins;

@Extension(optional = true)
public class Unity3dBuilderDSLExtension extends ContextExtensionPoint {
	
	@DslExtensionMethod(context = StepContext.class)
	public Object unity3d(String unity3dName, String argLine) {
		return new Unity3dBuilder(unity3dName, argLine, null);
	}
	
	@DslExtensionMethod(context = StepContext.class)
	public Object unity3d(String unity3dName, String argLine, Runnable closure) {
		Preconditions.checkArgument(isValidInstallation(unity3dName), unity3dName + "is not a unity installation");
		Preconditions.checkArgument(closure instanceof Closure, "clousure must be a Groovy runnable.");

		Unity3dBuilderDSLContext context = new Unity3dBuilderDSLContext();
		executeInContext(closure, context);
		
		return new Unity3dBuilder(unity3dName, argLine, context.unstableReturnCodes);
	}
	
	private boolean isValidInstallation(String unity3dName) {
		Unity3dBuilder.DescriptorImpl builderDescriptor = Jenkins.getInstance().getDescriptorByType(Unity3dBuilder.DescriptorImpl.class);
		for (Unity3dInstallation installation : builderDescriptor.getInstallations()) {
			if (installation.getName().equals(unity3dName))
				return true;
		}
		return false;
	}
}
