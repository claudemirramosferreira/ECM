package br.com.interactive.alfresco.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.extensions.surf.util.XMLUtil;

public class ParseXML {

	static String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
			"<feed xmlns=\"http://www.w3.org/2005/Atom\" xmlns:app=\"http://www.w3.org/2007/app\" xmlns:cmisra=\"http://docs.oasis-open.org/ns/cmis/restatom/200908/\" xmlns:cmis=\"http://docs.oasis-open.org/ns/cmis/core/200908/\" xmlns:alf=\"http://www.alfresco.org\" xmlns:opensearch=\"http://a9.com/-/spec/opensearch/1.1/\">" +
			"<author><name>admin</name></author>" +
			"<title>Eng. Produtos Tree</title>" +
			"<entry>" +
			"<summary>197_2013</summary>" +
			"<title>197_2013</title>" +
			"<cmisra:object>" +
			"<cmis:properties>" +
			"<cmis:propertyId propertyDefinitionId=\"cmis:allowedChildObjectTypeIds\" displayName=\"Allowed Child Object Types Ids\" queryName=\"cmis:allowedChildObjectTypeIds\"/>" +
			"<cmis:propertyId propertyDefinitionId=\"cmis:objectTypeId\" displayName=\"Object Type Id\" queryName=\"cmis:objectTypeId\"><cmis:value>cmis:folder</cmis:value></cmis:propertyId>" +
			"<cmis:propertyString propertyDefinitionId=\"cmis:path\" displayName=\"Path\" queryName=\"cmis:path\"><cmis:value>/Sites/Eng. Produtos/197_2013</cmis:value></cmis:propertyString>" +
			"<cmis:propertyString propertyDefinitionId=\"cmis:name\" displayName=\"Name\" queryName=\"cmis:name\"><cmis:value>197_2013</cmis:value></cmis:propertyString>" +
			"<cmis:propertyDateTime propertyDefinitionId=\"cmis:creationDate\" displayName=\"Creation Date\" queryName=\"cmis:creationDate\"><cmis:value>2013-10-03T13:34:17.883-04:00</cmis:value></cmis:propertyDateTime>" +
			"<cmis:propertyString propertyDefinitionId=\"cmis:changeToken\" displayName=\"Change token\" queryName=\"cmis:changeToken\"/>" +
			"<cmis:propertyString propertyDefinitionId=\"cmis:lastModifiedBy\" displayName=\"Last Modified By\" queryName=\"cmis:lastModifiedBy\"><cmis:value>admin</cmis:value></cmis:propertyString>" +
			"<cmis:propertyString propertyDefinitionId=\"cmis:createdBy\" displayName=\"Created by\" queryName=\"cmis:createdBy\"><cmis:value>admin</cmis:value></cmis:propertyString>" +
			"<cmis:propertyId propertyDefinitionId=\"cmis:objectId\" displayName=\"Object Id\" queryName=\"cmis:objectId\"><cmis:value>workspace://SpacesStore/832eeda1-40fe-47bc-93c7-be56a402b6e4</cmis:value></cmis:propertyId>" +
			"<cmis:propertyId propertyDefinitionId=\"alfcmis:nodeRef\" displayName=\"Alfresco Node Ref\" queryName=\"alfcmis:nodeRef\"/>" +
			"<cmis:propertyId propertyDefinitionId=\"cmis:baseTypeId\" displayName=\"Base Type Id\" queryName=\"cmis:baseTypeId\"><cmis:value>cmis:folder</cmis:value></cmis:propertyId>" +
			"<cmis:propertyId propertyDefinitionId=\"cmis:parentId\" displayName=\"Parent Id\" queryName=\"cmis:parentId\"><cmis:value>workspace://SpacesStore/d6943520-ef94-476e-875d-cf8bc5ca1d01</cmis:value></cmis:propertyId>" +
			"<cmis:propertyDateTime propertyDefinitionId=\"cmis:lastModificationDate\" displayName=\"Last Modified Date\" queryName=\"cmis:lastModificationDate\"><cmis:value>2013-10-03T16:10:37.972-04:00</cmis:value></cmis:propertyDateTime>" +
			"</cmis:properties>" +
			"</cmisra:object>" +
			"<cmisra:pathSegment>197_2013</cmisra:pathSegment>" +
			"</entry>" +
			"<entry>" +
			"<summary>198_2013</summary>" +
			"<title>198_2013</title>" +
			"<cmisra:object>" +
			"<cmis:properties>" +
			"<cmis:propertyId propertyDefinitionId=\"cmis:allowedChildObjectTypeIds\" displayName=\"Allowed Child Object Types Ids\" queryName=\"cmis:allowedChildObjectTypeIds\"/>" +
			"<cmis:propertyId propertyDefinitionId=\"cmis:objectTypeId\" displayName=\"Object Type Id\" queryName=\"cmis:objectTypeId\"><cmis:value>cmis:folder</cmis:value></cmis:propertyId>" +
			"<cmis:propertyString propertyDefinitionId=\"cmis:path\" displayName=\"Path\" queryName=\"cmis:path\"><cmis:value>/Sites/Eng. Produtos/197_2013</cmis:value></cmis:propertyString>" +
			"<cmis:propertyString propertyDefinitionId=\"cmis:name\" displayName=\"Name\" queryName=\"cmis:name\"><cmis:value>197_2013</cmis:value></cmis:propertyString>" +
			"<cmis:propertyDateTime propertyDefinitionId=\"cmis:creationDate\" displayName=\"Creation Date\" queryName=\"cmis:creationDate\"><cmis:value>2013-10-03T13:34:17.883-04:00</cmis:value></cmis:propertyDateTime>" +
			"<cmis:propertyString propertyDefinitionId=\"cmis:changeToken\" displayName=\"Change token\" queryName=\"cmis:changeToken\"/>" +
			"<cmis:propertyString propertyDefinitionId=\"cmis:lastModifiedBy\" displayName=\"Last Modified By\" queryName=\"cmis:lastModifiedBy\"><cmis:value>admin</cmis:value></cmis:propertyString>" +
			"<cmis:propertyString propertyDefinitionId=\"cmis:createdBy\" displayName=\"Created by\" queryName=\"cmis:createdBy\"><cmis:value>admin</cmis:value></cmis:propertyString>" +
			"<cmis:propertyId propertyDefinitionId=\"cmis:objectId\" displayName=\"Object Id\" queryName=\"cmis:objectId\"><cmis:value>workspace://SpacesStore/832eeda1-40fe-47bc-93c7-be56a402b6e4</cmis:value></cmis:propertyId>" +
			"<cmis:propertyId propertyDefinitionId=\"alfcmis:nodeRef\" displayName=\"Alfresco Node Ref\" queryName=\"alfcmis:nodeRef\"/>" +
			"<cmis:propertyId propertyDefinitionId=\"cmis:baseTypeId\" displayName=\"Base Type Id\" queryName=\"cmis:baseTypeId\"><cmis:value>cmis:folder</cmis:value></cmis:propertyId>" +
			"<cmis:propertyId propertyDefinitionId=\"cmis:parentId\" displayName=\"Parent Id\" queryName=\"cmis:parentId\"><cmis:value>workspace://SpacesStore/d6943520-ef94-476e-875d-cf8bc5ca1d01</cmis:value></cmis:propertyId>" +
			"<cmis:propertyDateTime propertyDefinitionId=\"cmis:lastModificationDate\" displayName=\"Last Modified Date\" queryName=\"cmis:lastModificationDate\"><cmis:value>2013-10-03T16:10:37.972-04:00</cmis:value></cmis:propertyDateTime>" +
			"</cmis:properties>" +
			"</cmisra:object>" +
			"<cmisra:pathSegment>198_2013</cmisra:pathSegment>" +
			"</entry>" +
			"</feed>";
	
	public static String parseXML(String body) throws DocumentException {
		return XMLUtil.parse(body).getRootElement().getText();
	}
	
	public static Document parseXMLToDocument(String body)  throws DocumentException {
		return  XMLUtil.parse(body);
	}
	
	public static void treeWalk(Element element) {
		for ( int i = 0, size = element.nodeCount(); i < size; i++ ) {
            Node node = element.node(i);
            if ( node instanceof Element ) {
            	treeWalk( (Element) node );
            }
            else {
            	System.out.println(node.getText());
            }
        }	
	}
}
