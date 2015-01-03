package checkpoint.grails

import groovy.util.slurpersupport.GPathResult
import groovy.xml.XmlUtil
import org.apache.commons.lang.time.DateFormatUtils
import wslite.soap.*

class ReconController {

    def index() { }

    def show() {
        //do recon things

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

        def request = generateXml(username, password, hotelCode)

        def client = new SOAPClient(uri)
        try {
            def response = client.send(SOAPAction: soapAction, request)
            def roomTypes = extractRoomTypes(response.body)

            return [soapAction: soapAction, uri: uri, username: username, password: password, hotelCode: hotelCode, xmlRequest: request, xmlResponse: response.text, roomTypes: roomTypes]
        } catch (SOAPFaultException sfe) {
            return [soapAction: soapAction, uri: uri, username: username, password: password, hotelCode: hotelCode, xmlRequest: request, xmlResponse: sfe.text, xmlError: sfe.message]
        } catch (SOAPClientException sce) {
            return [soapAction: soapAction, uri: uri, username: username, password: password, hotelCode: hotelCode, xmlRequest: request, xmlError: sce]
        }
    }

    private static String generateXml(username, password, hotelCode) {
        def xml = """<?xml version="1.0"?>
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
        <wsse:Security xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" soap:mustUnderstand="1">
            <wsse:UsernameToken>
                <wsse:Username>${username}</wsse:Username>
                <wsse:Password Type="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText">${password}</wsse:Password>
            </wsse:UsernameToken>
        </wsse:Security>
    </SOAP-ENV:Header>
    <SOAP-ENV:Body xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
        <OTA_HotelAvailRQ xmlns="http://www.opentravel.org/OTA/2003/05" AvailRatesOnly="true" EchoToken="e01b39e8-962f-4078-ad93-8070c2e2f1a7" TimeStamp="2014-12-16T13:16:46+11:00" Version="1.0">
            <AvailRequestSegments>
                <AvailRequestSegment AvailReqType="Room">
                    <HotelSearchCriteria>
                        <Criterion>
                            <HotelRef HotelCode="${hotelCode}"/>
                        </Criterion>
                    </HotelSearchCriteria>
                </AvailRequestSegment>
            </AvailRequestSegments>
        </OTA_HotelAvailRQ>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
"""
        return xml
    }

    private def validateXml() {
      //validate all of the xml
    }

    private def extractRoomTypes(GPathResult body) {
      //try and determine any roomTypes if validation passes

      def roomTypes = []

      body.OTA_HotelAvailRS.RoomStays.RoomStay.each { roomStay ->
          String roomTypeCode = roomStay.RoomTypes.RoomType[0].@RoomTypeCode.text()
          String roomTypeName = roomStay.RoomTypes.RoomType[0].RoomDescription.@Name.text()
          String roomPlanCode = roomStay.RoomTypes.RoomType[0].@RatePlanCode.text()
          String ratePlanName = roomStay.RoomTypes.RoomType[0].RatePlanDescription.@Name.text()

          String name = ratePlanName ? roomTypeName + ' - ' + ratePlanName : roomTypeName

          roomTypes.add(["name": name,"room-type-code": roomTypeCode, "room-type-name": roomTypeName, "rate-plan-code": ratePlanCode, "rate-plan-name": ratePlanName])
      }
      return roomTypes
    }
}
