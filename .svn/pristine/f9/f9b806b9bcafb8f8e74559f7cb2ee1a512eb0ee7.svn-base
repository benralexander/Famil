package re.own



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(ClaraController)
@Mock(Clara)
class ClaraControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/clara/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.claraInstanceList.size() == 0
        assert model.claraInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.claraInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.claraInstance != null
        assert view == '/clara/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/clara/show/1'
        assert controller.flash.message != null
        assert Clara.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/clara/list'


        populateValidParams(params)
        def clara = new Clara(params)

        assert clara.save() != null

        params.id = clara.id

        def model = controller.show()

        assert model.claraInstance == clara
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/clara/list'


        populateValidParams(params)
        def clara = new Clara(params)

        assert clara.save() != null

        params.id = clara.id

        def model = controller.edit()

        assert model.claraInstance == clara
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/clara/list'

        response.reset()


        populateValidParams(params)
        def clara = new Clara(params)

        assert clara.save() != null

        // test invalid parameters in update
        params.id = clara.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/clara/edit"
        assert model.claraInstance != null

        clara.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/clara/show/$clara.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        clara.clearErrors()

        populateValidParams(params)
        params.id = clara.id
        params.version = -1
        controller.update()

        assert view == "/clara/edit"
        assert model.claraInstance != null
        assert model.claraInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/clara/list'

        response.reset()

        populateValidParams(params)
        def clara = new Clara(params)

        assert clara.save() != null
        assert Clara.count() == 1

        params.id = clara.id

        controller.delete()

        assert Clara.count() == 0
        assert Clara.get(clara.id) == null
        assert response.redirectedUrl == '/clara/list'
    }
}
