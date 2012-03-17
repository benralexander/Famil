package re.own



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(LaurenController)
@Mock(Lauren)
class LaurenControllerTests {


    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/lauren/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.laurenInstanceList.size() == 0
        assert model.laurenInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.laurenInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.laurenInstance != null
        assert view == '/lauren/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/lauren/show/1'
        assert controller.flash.message != null
        assert Lauren.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/lauren/list'


        populateValidParams(params)
        def lauren = new Lauren(params)

        assert lauren.save() != null

        params.id = lauren.id

        def model = controller.show()

        assert model.laurenInstance == lauren
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/lauren/list'


        populateValidParams(params)
        def lauren = new Lauren(params)

        assert lauren.save() != null

        params.id = lauren.id

        def model = controller.edit()

        assert model.laurenInstance == lauren
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/lauren/list'

        response.reset()


        populateValidParams(params)
        def lauren = new Lauren(params)

        assert lauren.save() != null

        // test invalid parameters in update
        params.id = lauren.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/lauren/edit"
        assert model.laurenInstance != null

        lauren.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/lauren/show/$lauren.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        lauren.clearErrors()

        populateValidParams(params)
        params.id = lauren.id
        params.version = -1
        controller.update()

        assert view == "/lauren/edit"
        assert model.laurenInstance != null
        assert model.laurenInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/lauren/list'

        response.reset()

        populateValidParams(params)
        def lauren = new Lauren(params)

        assert lauren.save() != null
        assert Lauren.count() == 1

        params.id = lauren.id

        controller.delete()

        assert Lauren.count() == 0
        assert Lauren.get(lauren.id) == null
        assert response.redirectedUrl == '/lauren/list'
    }
}
