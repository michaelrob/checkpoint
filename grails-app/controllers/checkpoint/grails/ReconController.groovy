package checkpoint.grails

class ReconController {

    def index() { }

    def show() {
        //do recon things

        String soapAction = "http://www.siteminder.com.au/siteconnect/HotelAvailRQ"
        String uri
        String username
        String password
        String hotelCode


        Map soapRequest = [
            headers: headerXml(username, password),
            payload: bodyXml(hotelCode)
        ]
    }

    private String headerXml(String username, String password) {

    }

    private String bodyXml(String hotelCode) {

    }
}
