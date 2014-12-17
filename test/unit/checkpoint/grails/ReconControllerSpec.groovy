package checkpoint.grails

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ReconController)
class ReconControllerSpec extends Specification {

    ReconController reconController

    def setup() {
        reconController = new ReconController()
    }

    def cleanup() {
    }

    void "test show should return xml response"() {
        when:
        params.uri = "https://193.201.124.113:4441/InvControl.asmx"
        params.username = "site"
        params.password = "minder"
        params.hotelcode = "STA/STA/3"
        def show = reconController.show()

        then:
        response.text == ""
    }

    void "generateXml should generate xml with fields"() {
        when:
        def xml = reconController.generateXml("siteminder", "password", "12345")

        then:
        xml == """<?xml version="1.0"?>
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
        <wsse:Security xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" soap:mustUnderstand="1">
            <wsse:UsernameToken>
                <wsse:Username>siteminder</wsse:Username>
                <wsse:Password Type="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText">password</wsse:Password>
            </wsse:UsernameToken>
        </wsse:Security>
    </SOAP-ENV:Header>
    <SOAP-ENV:Body xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
        <OTA_HotelAvailRQ xmlns="http://www.opentravel.org/OTA/2003/05" AvailRatesOnly="true" EchoToken="e01b39e8-962f-4078-ad93-8070c2e2f1a7" TimeStamp="2014-12-16T13:16:46+11:00" Version="1.0">
            <AvailRequestSegments>
                <AvailRequestSegment AvailReqType="Room">
                    <HotelSearchCriteria>
                        <Criterion>
                            <HotelRef HotelCode="12345"/>
                        </Criterion>
                    </HotelSearchCriteria>
                </AvailRequestSegment>
            </AvailRequestSegments>
        </OTA_HotelAvailRQ>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
"""
    }
}
