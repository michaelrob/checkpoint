package checkpoint.grails

import javax.xml.XMLConstants
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory

class XmlValidatorController {

    def index() { }

    def validateXml() {
      //validate all of the things

      def xml
      def xsd

      def xmlFactory = SchemaFactory.newInstance(XMLConstants.W3_XML_SCHEMA_NS_URI)

      def schema = xmlFactory.newSchema(new StreamSource(new StringReader(xsd)))
      def validator = scheam.newValidator()

      validator.validate(new StreamSource(new StringReader(xml)))
    }
}
