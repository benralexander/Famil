package re.own

import org.springframework.dao.DataIntegrityViolationException

class LucasController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [lucasInstanceList: Lucas.list(params), lucasInstanceTotal: Lucas.count()]
    }

    def create() {
        [lucasInstance: new Lucas(params)]
    }

    def save() {
        def lucasInstance = new Lucas(params)
        if (!lucasInstance.save(flush: true)) {
            render(view: "create", model: [lucasInstance: lucasInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'lucas.label', default: 'Lucas'), lucasInstance.id])
        redirect(action: "show", id: lucasInstance.id)
    }

    def show() {
        def lucasInstance = Lucas.get(params.id)
        if (!lucasInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'lucas.label', default: 'Lucas'), params.id])
            redirect(action: "list")
            return
        }

        [lucasInstance: lucasInstance]
    }

    def edit() {
        def lucasInstance = Lucas.get(params.id)
        if (!lucasInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lucas.label', default: 'Lucas'), params.id])
            redirect(action: "list")
            return
        }

        [lucasInstance: lucasInstance]
    }

    def update() {
        def lucasInstance = Lucas.get(params.id)
        if (!lucasInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lucas.label', default: 'Lucas'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (lucasInstance.version > version) {
                lucasInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'lucas.label', default: 'Lucas')] as Object[],
                          "Another user has updated this Lucas while you were editing")
                render(view: "edit", model: [lucasInstance: lucasInstance])
                return
            }
        }

        lucasInstance.properties = params

        if (!lucasInstance.save(flush: true)) {
            render(view: "edit", model: [lucasInstance: lucasInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'lucas.label', default: 'Lucas'), lucasInstance.id])
        redirect(action: "show", id: lucasInstance.id)
    }

    def delete() {
        def lucasInstance = Lucas.get(params.id)
        if (!lucasInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'lucas.label', default: 'Lucas'), params.id])
            redirect(action: "list")
            return
        }

        try {
            lucasInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'lucas.label', default: 'Lucas'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'lucas.label', default: 'Lucas'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
