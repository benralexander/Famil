package re.own



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(LucasController)
@Mock(Lucas)
class LucasControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/lucas/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.lucasInstanceList.size() == 0
        assert model.lucasInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.lucasInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.lucasInstance != null
        assert view == '/lucas/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/lucas/show/1'
        assert controller.flash.message != null
        assert Lucas.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/lucas/list'


        populateValidParams(params)
        def lucas = new Lucas(params)

        assert lucas.save() != null

        params.id = lucas.id

        def model = controller.show()

        assert model.lucasInstance == lucas
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/lucas/list'


        populateValidParams(params)
        def lucas = new Lucas(params)

        assert lucas.save() != null

        params.id = lucas.id

        def model = controller.edit()

        assert model.lucasInstance == lucas
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/lucas/list'

        response.reset()


        populateValidParams(params)
        def lucas = new Lucas(params)

        assert lucas.save() != null

        // test invalid parameters in update
        params.id = lucas.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/lucas/edit"
        assert model.lucasInstance != null

        lucas.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/lucas/show/$lucas.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        lucas.clearErrors()

        populateValidParams(params)
        params.id = lucas.id
        params.version = -1
        controller.update()

        assert view == "/lucas/edit"
        assert model.lucasInstance != null
        assert model.lucasInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/lucas/list'

        response.reset()

        populateValidParams(params)
        def lucas = new Lucas(params)

        assert lucas.save() != null
        assert Lucas.count() == 1

        params.id = lucas.id

        controller.delete()

        assert Lucas.count() == 0
        assert Lucas.get(lucas.id) == null
        assert response.redirectedUrl == '/lucas/list'
    }
}
