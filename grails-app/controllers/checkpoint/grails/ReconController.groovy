package checkpoint.grails

import checkpoint.ValidateXml
import checkpoint.Recon
import groovy.util.slurpersupport.GPathResult
import groovy.xml.XmlUtil
import org.apache.commons.lang.time.DateFormatUtils
import wslite.soap.*

class ReconController {

    def index() { }

    def show() {
        //do recon things
        //toDo: put this into its own class

        String soapAction = "http://www.siteminder.com.au/siteconnect/HotelAvailRQ"
        String uri = params.uri.toString()
        String username = params.username.toString()
        String password = params.password.toString()
        String hotelCode = params.hotelCode.toString()

        if(!soapAction || !uri || !username || !password) {
            println "Missing configuration options"
        }

        if(!hotelCode) {
            println "Missing hotel code"
        }

        def request = Recon.generateXml(username, password, hotelCode)

        def client = new SOAPClient(uri)

        //toDo: add the below commented code to pretty format the XML
        /*
        def stringWriter = new StringWriter()
        def node = new XmlParser().parseText(xml);
        new XmlNodePrinter(new PrintWriter(stringWriter)).print(node)

        println stringWriter.toString()
         */

        //toDo: complete common returns
        def common = [soapAction: soapAction, uri: uri, username: username, password: password, hotelCode: hotelCode, xmlRequest: request]
        try {
            def response = client.send(SOAPAction: soapAction, request)
            def roomTypes = Recon.extractRoomTypes(response.body)

            return [soapAction: soapAction, uri: uri, username: username, password: password, hotelCode: hotelCode, xmlRequest: request, xmlResponse: response.text, roomTypes: roomTypes]
        } catch (SOAPFaultException sfe) {
            return [soapAction: soapAction, uri: uri, username: username, password: password, hotelCode: hotelCode, xmlRequest: request, xmlResponse: sfe.text, xmlError: sfe.message]
        } catch (SOAPClientException sce) {
            return [soapAction: soapAction, uri: uri, username: username, password: password, hotelCode: hotelCode, xmlRequest: request, xmlError: sce]
        }
    }

    private def validate() {
        //validate all of the xml against xsd schema

        //check uuid
    }
}
