package org.processmining.plugins.xpdl.collections;

import org.processmining.plugins.xpdl.idname.XpdlMessageFlow;

/**
 * @author hverbeek
 * 
 *         <xsd:element name="MessageFlows"> <xsd:annotation>
 *         <xsd:documentation>BPMN</xsd:documentation> </xsd:annotation>
 *         <xsd:complexType> <xsd:sequence minOccurs="0" maxOccurs="unbounded">
 *         <xsd:element ref="xpdl:MessageFlow"/> <xsd:any namespace="##other"
 *         processContents="lax" minOccurs="0" maxOccurs="unbounded"/>
 *         </xsd:sequence> <xsd:anyAttribute namespace="##other"
 *         processContents="lax"/> </xsd:complexType> </xsd:element>
 */
public class XpdlMessageFlows extends XpdlCollections<XpdlMessageFlow> {

	public XpdlMessageFlows(String tag) {
		super(tag);
	}

	public XpdlMessageFlow create() {
		return new XpdlMessageFlow("MessageFlow");
	}
}
