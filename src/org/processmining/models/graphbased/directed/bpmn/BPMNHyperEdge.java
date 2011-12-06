package org.processmining.models.graphbased.directed.bpmn;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Set;

import org.processmining.models.graphbased.AttributeMap;
import org.processmining.models.graphbased.AttributeMap.ArrowType;
import org.processmining.models.graphbased.directed.bpmn.elements.IGraphElementDecoration;
import org.processmining.models.graphbased.directed.bpmn.elements.SubProcess;
import org.processmining.models.graphbased.directed.bpmn.elements.Swimlane;
import org.processmining.models.shapes.Decorated;


public abstract class BPMNHyperEdge <S extends BPMNNode, T extends BPMNNode> extends BPMNNode {
	
	
	
	public class InEdge extends BPMNEdge<S, BPMNHyperEdge<S, T>> 
	implements Decorated {
		private IGraphElementDecoration decorator = null;
		public InEdge(S from, BPMNHyperEdge<S, T> bpmnHyperEdge) {
			super(from, bpmnHyperEdge);
			fillAttributes();
		}
		private void fillAttributes() {
			getAttributeMap().put(AttributeMap.EDGEEND, ArrowType.ARROWTYPE_NONE);
			getAttributeMap().put(AttributeMap.EDGEENDFILLED, true);
			getAttributeMap().remove(AttributeMap.LABEL);
			getAttributeMap().put(AttributeMap.SHOWLABEL, false);

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

	public class OutEdge extends BPMNEdge<BPMNHyperEdge<S, T>, T>	implements Decorated {
		private IGraphElementDecoration decorator = null;
		public OutEdge(BPMNHyperEdge<S, T> bpmnHyperEdge, T target) {
			super(bpmnHyperEdge, target);
			fillAttributes();
		}

		private void fillAttributes() {
			getAttributeMap().put(AttributeMap.EDGEEND, ArrowType.ARROWTYPE_CLASSIC);
			getAttributeMap().put(AttributeMap.EDGEENDFILLED, true);
			getAttributeMap().remove(AttributeMap.LABEL);
			getAttributeMap().put(AttributeMap.SHOWLABEL, false);

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

	private S source;
	private T target;
	private InEdge inEdge;
	private OutEdge outEdge;
	
	
	public BPMNHyperEdge(S source, T target) {
		super(source.getGraph());
		this.source = source;
		this.target = target;
		addRealEdges();
		if(source.getParentSubProcess()!=null){
			SubProcess parentSubProcess = source.getParentSubProcess();
			this.setParentSubprocess(parentSubProcess);
			if (parentSubProcess != null) {
				parentSubProcess.addChild(this);
			}
		}
		if(source.getParentSwimlane()!=null){
			Swimlane parentSwimlane = source.getParentSwimlane();
			this.setParentSwimlane(parentSwimlane);
			if (parentSwimlane != null) {
				parentSwimlane.addChild(this);
			}
		}

	}
	public static final Color ABSTRACTBACKGROUNDCOLOR = null;
	public static final Color ABSTRACTBORDERCOLOR = null;
	public static final Color ABSTRACTTEXTCOLOR = null;

	public static final Color ADJACENTBACKGROUNDCOLOR =null;

	public static final Color CLUSTERBACKGROUNDCOLOR = null;
	public static final Color CLUSTERBORDERCOLOR = null;
	public static final Color CLUSTERTEXTCOLOR = null;

	public static final Color EDGECOLOR = null;
	public static final Color EDGECORRELATEDCOLOR = null;
	public static final Color EDGEUNCORRELATEDCOLOR = null;

	public static final Color LABELCOLOR = null;

	public static final Color PRIMITIVEBACKGROUNDCOLOR = null;
	public static final Color PRIMITIVEBORDERCOLOR = null;
	public static final Color PRIMITIVETEXTCOLOR = null;
	public BPMNHyperEdge(S source, T target, SubProcess parentSubProcess) {
		super(source.getGraph(), parentSubProcess);
		this.source = source;
		this.target = target;
		addRealEdges();
		
	}
	public BPMNHyperEdge(S source, T target, Swimlane parentSwimLine) {
		super(source.getGraph(), parentSwimLine);
		this.source = source;
		this.target = target;
		addRealEdges();
	}
	private void addRealEdges() {
		inEdge = new InEdge(this.source, this);
		this.getGraph().graphElementAdded(this);
		this.getGraph().graphElementAdded(inEdge);
		outEdge = new OutEdge(this, this.target);
		this.getGraph().graphElementAdded(outEdge);
	}

	public S getSource() {
		return this.source;
	}
	public T getTarget() {
		return this.target;
	}
	public Set<BPMNEdge<? extends BPMNNode, ? extends BPMNNode>> getRealEdges() {
		Set<BPMNEdge<? extends BPMNNode, ? extends BPMNNode>> edges = new HashSet<BPMNEdge<? extends BPMNNode, ? extends BPMNNode>>();
		edges.add(inEdge);
		edges.add(outEdge);

		return edges;
	}
	
	public boolean equals(Object o) {
		
		if (!(this.getClass().equals(o.getClass()))) {
			return false;
		}
		BPMNHyperEdge<?, ?> edge = (BPMNHyperEdge<?, ?>) o;

		return edge.getId().equals(this.getId());
	}
	
	public int compareTo(BPMNHyperEdge<S, T> edge) {
		return edge.getId().compareTo(this.getId());
	}

	
}
