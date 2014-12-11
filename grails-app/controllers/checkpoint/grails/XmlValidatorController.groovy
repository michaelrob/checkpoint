package checkpoint.grails

import javax.xml.XMLConstants
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory

class XmlValidatorController {

    def index() { }

    def show() {
      //validate all of the things

      def xml = params.xml.toString()
      def xsd = params.xsd.toString()

      def xmlFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)

      try {
          def schema = xmlFactory.newSchema(new StreamSource(new StringReader(xsd)))
          def validator = schema.newValidator()

          def errorMessage = validator.validate(new StreamSource(new StringReader(xml)))
      } catch(e) {
        render("Problems $e")
      }
    }
}
