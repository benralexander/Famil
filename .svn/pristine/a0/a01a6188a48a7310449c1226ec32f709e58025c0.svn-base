package re.own

import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(BenController)
@Mock(Ben)
class BenControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/papa/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.papaInstanceList.size() == 0
        assert model.papaInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.papaInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.papaInstance != null
        assert view == '/papa/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/papa/show/1'
        assert controller.flash.message != null
        assert Ben.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/papa/list'


        populateValidParams(params)
        def papa = new Ben(params)

        assert papa.save() != null

        params.id = papa.id

        def model = controller.show()

        assert model.papaInstance == papa
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/papa/list'


        populateValidParams(params)
        def papa = new Ben(params)

        assert papa.save() != null

        params.id = papa.id

        def model = controller.edit()

        assert model.papaInstance == papa
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/papa/list'

        response.reset()


        populateValidParams(params)
        def papa = new Ben(params)

        assert papa.save() != null

        // test invalid parameters in update
        params.id = papa.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/papa/edit"
        assert model.papaInstance != null

        papa.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/papa/show/$papa.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        papa.clearErrors()

        populateValidParams(params)
        params.id = papa.id
        params.version = -1
        controller.update()

        assert view == "/papa/edit"
        assert model.papaInstance != null
        assert model.papaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/papa/list'

        response.reset()

        populateValidParams(params)
        def papa = new Ben(params)

        assert papa.save() != null
        assert Ben.count() == 1

        params.id = papa.id

        controller.delete()

        assert Ben.count() == 0
        assert Ben.get(papa.id) == null
        assert response.redirectedUrl == '/papa/list'
    }
}
