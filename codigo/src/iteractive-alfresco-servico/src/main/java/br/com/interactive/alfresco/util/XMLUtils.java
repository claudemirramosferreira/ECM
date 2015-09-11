package br.com.interactive.alfresco.util;

import org.dom4j.Element;
import org.dom4j.Node;

/**
 * 
 * Classe responsavel por fornecer as principais estruturas de XML para criacao de dados no Alfresco. 
 * 
 * @author ELIMARCOSAROUCA
 *
 */
public class XMLUtils {
	
	public static String xmlCreateFolder(String nomePasta) {
		
		return "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
				"<entry xmlns=\"http://www.w3.org/2005/Atom\" xmlns:cmisra=\"http://docs.oasis-open.org/ns/cmis/restatom/200908/\" xmlns:cmis=\"http://docs.oasis-open.org/ns/cmis/core/200908/\">" +
				"<title>"+nomePasta+"</title>" +
				"<cmisra:object>" +
					"<cmis:properties>" +
						"<cmis:propertyId propertyDefinitionId=\"cmis:objectTypeId\">" +
							"<cmis:value>cmis:folder</cmis:value>" +
						"</cmis:propertyId>" +
					"</cmis:properties>" +
				"</cmisra:object>" +
				"</entry>";
	}
	
	public static String getUUIDByElement(Element element) {
		for ( int i = 0, size = element.nodeCount(); i < size; i++ ) {
            Node node = element.node(i);
        	if ("id".equals(node.getName())) {
        		return node.getStringValue().replace("urn:uuid:", "workspace://SpacesStore/");
            }
        }	
		
		return null;
	}

}
