package org.processmining.models.graphbased.directed.bpmn.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.SwingConstants;

import org.processmining.models.graphbased.AttributeMap;
import org.processmining.models.graphbased.directed.bpmn.BPMNHyperEdge;
import org.processmining.models.graphbased.directed.bpmn.BPMNNode;
import org.processmining.models.shapes.Decorated;
import org.processmining.models.shapes.Ellipse;

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


		getAttributeMap().put(AttributeMap.SHOWLABEL, false);

		if(label!=null){
			this.setLabel(label);
		}else{
			getAttributeMap().put(AttributeMap.SIZE, new Dimension(1, 1));
		}

	}

	public void setLabel(String label){
		getAttributeMap().put(AttributeMap.SHOWLABEL, true);
		getAttributeMap().remove(AttributeMap.LABEL);
		getAttributeMap().put(AttributeMap.LABEL, label);

		getAttributeMap().put(AttributeMap.SIZE, new Dimension(10, 10));
		getAttributeMap().put(AttributeMap.SHAPE, new Ellipse());
		getAttributeMap().put(AttributeMap.FILLCOLOR, Color.WHITE);
		getAttributeMap().put(AttributeMap.STROKECOLOR, Color.WHITE);

		getAttributeMap().put(AttributeMap.LABELVERTICALALIGNMENT, SwingConstants.CENTER);
		getAttributeMap().put(AttributeMap.LABELHORIZONTALALIGNMENT, SwingConstants.CENTER);

	}
	
	public void removeLabel(){
		getAttributeMap().put(AttributeMap.SHOWLABEL, false);
		

		getAttributeMap().put(AttributeMap.SIZE, new Dimension(1, 1));
		
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

	public BPMNNode getSouce(){
		return this.getSource();

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
