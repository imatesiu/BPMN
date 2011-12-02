package org.processmining.plugins.xpdl.collections;

import java.util.Map;

import org.processmining.models.graphbased.directed.DirectedGraphNode;
import org.processmining.models.graphbased.directed.bpmn.BPMNDiagram;
import org.processmining.models.graphbased.directed.bpmn.BPMNNode;
import org.processmining.plugins.xpdl.idname.XpdlAssociation;

/**
 * @author hverbeek
 * 
 *         <xsd:element name="Associations"> <xsd:annotation>
 *         <xsd:documentation>BPMN</xsd:documentation> </xsd:annotation>
 *         <xsd:complexType> <xsd:sequence maxOccurs="unbounded"> <xsd:element
 *         ref="xpdl:Association"/> <xsd:any namespace="##other"
 *         processContents="lax" minOccurs="0" maxOccurs="unbounded"/>
 *         </xsd:sequence> <xsd:anyAttribute namespace="##other"
 *         processContents="lax"/> </xsd:complexType> </xsd:element>
 */
public class XpdlAssociations extends XpdlCollections<XpdlAssociation> {

	public XpdlAssociations(String tag) {
		super(tag);
	}

	public XpdlAssociation create() {
		return new XpdlAssociation("Association");
	}
	public void convertToBpmn(BPMNDiagram bpmn, DirectedGraphNode parent,
			Map<String, BPMNNode> id2node) {
		
		for(XpdlAssociation xs : this.list){

			xs.convertToBpmn(bpmn,parent,id2node);
		}

	}

}
