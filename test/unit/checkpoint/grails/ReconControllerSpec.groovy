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

    void "test something"() {
        when:
        reconController.show()

        then:
        response.text == "wont work"
    }
}
