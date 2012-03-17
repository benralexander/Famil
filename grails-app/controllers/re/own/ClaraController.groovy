package re.own

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

class ClaraController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [claraInstanceList: Clara.list(params), claraInstanceTotal: Clara.count()]
    }
      @Secured(['ROLE_USER'])
    def create() {
        [claraInstance: new Clara(params)]
    }


    def nosecurity() {

    }

    @Secured(['ROLE_USER'])
    def low() {

    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def medium() {

    }

    @Secured(['ROLE_USER', 'IS_AUTHENTICATED_FULLY'])
    def high() {

    }

    @Secured(['ROLE_ADMIN', 'IS_AUTHENTICATED_FULLY'])
    def superhigh() {

    }

    def save() {
        def claraInstance = new Clara(params)
        if (!claraInstance.save(flush: true)) {
            render(view: "create", model: [claraInstance: claraInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'clara.label', default: 'Clara'), claraInstance.id])
        redirect(action: "show", id: claraInstance.id)
    }

    def show() {
        def claraInstance = Clara.get(params.id)
        if (!claraInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'clara.label', default: 'Clara'), params.id])
            redirect(action: "list")
            return
        }

        [claraInstance: claraInstance]
    }

    def edit() {
        def claraInstance = Clara.get(params.id)
        if (!claraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'clara.label', default: 'Clara'), params.id])
            redirect(action: "list")
            return
        }

        [claraInstance: claraInstance]
    }

    def update() {
        def claraInstance = Clara.get(params.id)
        if (!claraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'clara.label', default: 'Clara'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (claraInstance.version > version) {
                claraInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'clara.label', default: 'Clara')] as Object[],
                          "Another user has updated this Clara while you were editing")
                render(view: "edit", model: [claraInstance: claraInstance])
                return
            }
        }

        claraInstance.properties = params

        if (!claraInstance.save(flush: true)) {
            render(view: "edit", model: [claraInstance: claraInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'clara.label', default: 'Clara'), claraInstance.id])
        redirect(action: "show", id: claraInstance.id)
    }

    def delete() {
        def claraInstance = Clara.get(params.id)
        if (!claraInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'clara.label', default: 'Clara'), params.id])
            redirect(action: "list")
            return
        }

        try {
            claraInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'clara.label', default: 'Clara'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'clara.label', default: 'Clara'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
