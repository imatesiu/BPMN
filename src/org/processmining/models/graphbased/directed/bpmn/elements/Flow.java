package org.processmining.models.graphbased.directed.bpmn.elements;

import java.awt.Dimension;
import java.awt.Graphics2D;

import org.processmining.models.graphbased.AttributeMap;
import org.processmining.models.graphbased.directed.bpmn.BPMNHyperEdge;
import org.processmining.models.graphbased.directed.bpmn.BPMNNode;
import org.processmining.models.shapes.Decorated;

public class Flow extends BPMNHyperEdge<BPMNNode, BPMNNode> implements Decorated {
	private IGraphElementDecoration decorator = null;

	public Flow(BPMNNode source, BPMNNode target, String label) {
		super(source, target);
		fillAttributes(label);
	}

	public Flow(BPMNNode source, BPMNNode target, SubProcess parentSubProcess, String label) {
		super(source, target, parentSubProcess);
		fillAttributes(label);
	}

	public Flow(BPMNNode source, BPMNNode target, Swimlane parentSwimlane, String label) {
		super(source, target, parentSwimlane);
		fillAttributes(label);
	}

	/**
	 * 
	 */
	private void fillAttributes(String label) {
		//getAttributeMap().put(AttributeMap.EDGEEND, ArrowType.ARROWTYPE_CLASSIC);
		//getAttributeMap().put(AttributeMap.EDGEENDFILLED, true);
		getAttributeMap().put(AttributeMap.SHOWLABEL, false);
		if(label!=null){
			getAttributeMap().put(AttributeMap.LABEL, label);
			getAttributeMap().put(AttributeMap.SHOWLABEL, true);
		}
		getAttributeMap().put(AttributeMap.SIZE, new Dimension(1, 1));
		//getAttributeMap().put(AttributeMap.SQUAREBB, false);
		getAttributeMap().put(AttributeMap.RESIZABLE, false);
		//getAttributeMap().put(AttributeMap., true);
	}

	public Swimlane getParentSwimlane() {
		if (getParent() != null) {
			if (getParent() instanceof Swimlane)
				return (Swimlane) getParent();
			else
				return null;
		}
		return null;
	}

	public SubProcess getParentSubProcess() {
		if (getParent() != null) {
			if (getParent() instanceof SubProcess)
				return (SubProcess) getParent();
			else
				return null;
		}
		return null;
	}

	public boolean equals(Object o) {
		return (o == this);
	}

	public IGraphElementDecoration getDecorator() {
		return decorator;
	}

	public void setDecorator(IGraphElementDecoration decorator) {
		this.decorator = decorator;
	}

	public void decorate(Graphics2D g2d, double x, double y, double width, double height) {
		if (decorator != null) {
			decorator.decorate(g2d, x, y, width, height);
		}
	}

}
