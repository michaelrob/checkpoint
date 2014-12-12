package checkpoint.recon.debug

@Grab(group='com.github.groovy-wslite', module='groovy-wslite', version='1.1.0')
import wslite.soap.*
import groovy.xml.XmlUtil
import groovy.util.slurpersupport.GPathResult
import org.apache.commons.lang.time.DateFormatUtils

class debugClient {

  String soapAction = "http://www.siteminder.com.au/siteconnect/HotelAvailRQ"
  String uri = "http://localhost:6980/MathServiceInterface?wsdl"
  String username = ""
  String password = ""

  String hotelCode

  if(!soapAction || !url || !username || !password) {
    println "Missing configuration options"
  }

  if(!hotelCode) {
    println "Missing hotel code"
  }

  Map soapRequest = [
    headers: headerXml(username, password)
    payload: bodyXml(hotelCode)
  ]

  def client = new SOAPClient(wsdl)

  def response = clint.send(SOAPAction: soapAction) {

  }

  private String headerXml(String username, String password) {
    xml = """
    mkp.declareNamespace(soap:"http://schemas.xmlsoap.org/soap/envelope/")
            mkp.declareNamespace(wsse:"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd")
            'wsse:Security'('soap:mustUnderstand': "1") {
                'wsse:UsernameToken' {
                    'wsse:Username'($username)
                    'wsse:Password'(Type: "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText", $password)
                }
            }
    """
    return XmlUtil.serialize(xml as GPathResult)
  }

  private String bodyXml(String hotelCode) {
    def echoToken = UUID.randomUUID().toString()
    def timestamp = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(new Date())

    xml = """
    mkp.declareNamespace("": "http://www.opentravel.org/OTA/2003/05")
    OTA_HotelAvailRQ(Version: "1.0", TimeStamp: timestamp, EchoToken: $echoToken, AvailRatesOnly: "true") {
        AvailRequestSegments {
            AvailRequestSegment(AvailReqType: "Room") {
                HotelSearchCriteria {
                    Criterion {
                        HotelRef(HotelCode: $hotelCode)
                    }
                }
            }
        }
    }
    """

    return XmlUtil.serialize(xml as GPathResult)
  }

  /*
  <OTA_HotelAvailRQ xmlns="http://www.opentravel.org/OTA/2003/05" Version="1.0" TimeStamp="2005-08-01T09:30:47+02:00" EchoToken="fb57388d" AvailRatesOnly="true">
  <AvailRequestSegments>
    <AvailRequestSegment AvailReqType="Room">
      <HotelSearchCriteria>
        <Criterion>
          <HotelRef HotelCode="HOTEL1"/>
        </Criterion>
      </HotelSearchCriteria>
    </AvailRequestSegment>
  </AvailRequestSegments>
  </OTA_HotelAvailRQ>
  */
}
