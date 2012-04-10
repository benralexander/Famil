package re.own



import org.junit.*
import grails.test.mixin.*

@TestFor(JeffryBarnesController)
@Mock(JeffryBarnes)
class JeffryBarnesControllerTests {


    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/jeffryBarnes/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.jeffryBarnesInstanceList.size() == 0
        assert model.jeffryBarnesInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.jeffryBarnesInstance != null
    }

    void testSave() {
        controller.save()

        assert model.jeffryBarnesInstance != null
        assert view == '/jeffryBarnes/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/jeffryBarnes/show/1'
        assert controller.flash.message != null
        assert JeffryBarnes.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/jeffryBarnes/list'


        populateValidParams(params)
        def jeffryBarnes = new JeffryBarnes(params)

        assert jeffryBarnes.save() != null

        params.id = jeffryBarnes.id

        def model = controller.show()

        assert model.jeffryBarnesInstance == jeffryBarnes
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/jeffryBarnes/list'


        populateValidParams(params)
        def jeffryBarnes = new JeffryBarnes(params)

        assert jeffryBarnes.save() != null

        params.id = jeffryBarnes.id

        def model = controller.edit()

        assert model.jeffryBarnesInstance == jeffryBarnes
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/jeffryBarnes/list'

        response.reset()


        populateValidParams(params)
        def jeffryBarnes = new JeffryBarnes(params)

        assert jeffryBarnes.save() != null

        // test invalid parameters in update
        params.id = jeffryBarnes.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/jeffryBarnes/edit"
        assert model.jeffryBarnesInstance != null

        jeffryBarnes.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/jeffryBarnes/show/$jeffryBarnes.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        jeffryBarnes.clearErrors()

        populateValidParams(params)
        params.id = jeffryBarnes.id
        params.version = -1
        controller.update()

        assert view == "/jeffryBarnes/edit"
        assert model.jeffryBarnesInstance != null
        assert model.jeffryBarnesInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/jeffryBarnes/list'

        response.reset()

        populateValidParams(params)
        def jeffryBarnes = new JeffryBarnes(params)

        assert jeffryBarnes.save() != null
        assert JeffryBarnes.count() == 1

        params.id = jeffryBarnes.id

        controller.delete()

        assert JeffryBarnes.count() == 0
        assert JeffryBarnes.get(jeffryBarnes.id) == null
        assert response.redirectedUrl == '/jeffryBarnes/list'
    }
}
