package checkpoint

import groovy.util.slurpersupport.GPathResult
import org.apache.commons.lang.time.DateFormatUtils

class Recon {

    private static String generateXml(username, password, hotelCode) {
        def timeStamp = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(new Date())
        def echoToken = UUID.randomUUID().toString()

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
        <OTA_HotelAvailRQ xmlns="http://www.opentravel.org/OTA/2003/05" AvailRatesOnly="true" EchoToken="${echoToken}" TimeStamp="${timeStamp}" Version="1.0">
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

    private static extractRoomTypes(GPathResult body) {
        //try and determine any roomTypes if validation passes

        def roomTypes = []

        body.OTA_HotelAvailRS.RoomStays.RoomStay.each { roomStay ->
            String roomTypeCode = roomStay.RoomTypes.RoomType[0].@RoomTypeCode.text() ?: "no value"
            String roomTypeName = roomStay.RoomTypes.RoomType[0].RoomDescription.@Name.text() ?: "no value"
            String ratePlanCode = roomStay.RatePlans.RatePlan[0].@RatePlanCode.text() ?: "no value"
            String ratePlanName = roomStay.RatePlans.RatePlan[0].RatePlanDescription.@Name.text() ?: "no value"

            String name = ratePlanName ? roomTypeName + ' - ' + ratePlanName : roomTypeName

            roomTypes.add(["name": name,"room-type-code": roomTypeCode, "room-type-name": roomTypeName, "rate-plan-code": ratePlanCode, "rate-plan-name": ratePlanName])
        }
        return roomTypes
    }
}
