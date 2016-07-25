package checkpoint.grails

import checkpoint.ValidateXml

class XmlValidatorController {

    def index() { }

    def show() {

        def xml = params.xml.toString()
        def xsd = params.xsd.toString()


        ValidateXml.valid(xml, xsd)
    }
}
