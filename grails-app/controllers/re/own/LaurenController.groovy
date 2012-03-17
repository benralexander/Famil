package re.own

import org.springframework.dao.DataIntegrityViolationException

class LaurenController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [laurenInstanceList: Lauren.list(params), laurenInstanceTotal: Lauren.count()]
    }

    def create() {
        [laurenInstance: new Lauren(params)]
    }

    def save() {
        def laurenInstance = new Lauren(params)
        if (!laurenInstance.save(flush: true)) {
            render(view: "create", model: [laurenInstance: laurenInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'lauren.label', default: 'Lauren'), laurenInstance.id])
        redirect(action: "show", id: laurenInstance.id)
    }

    def show() {
        def laurenInstance = Lauren.get(params.id)
        if (!laurenInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lauren.label', default: 'Lauren'), params.id])
            redirect(action: "list")
            return
        }

        [laurenInstance: laurenInstance]
    }

    def edit() {
        def laurenInstance = Lauren.get(params.id)
        if (!laurenInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lauren.label', default: 'Lauren'), params.id])
            redirect(action: "list")
            return
        }

        [laurenInstance: laurenInstance]
    }

    def update() {
        def laurenInstance = Lauren.get(params.id)
        if (!laurenInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lauren.label', default: 'Lauren'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (laurenInstance.version > version) {
                laurenInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'lauren.label', default: 'Lauren')] as Object[],
                        "Another user has updated this Lauren while you were editing")
                render(view: "edit", model: [laurenInstance: laurenInstance])
                return
            }
        }

        laurenInstance.properties = params

        if (!laurenInstance.save(flush: true)) {
            render(view: "edit", model: [laurenInstance: laurenInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'lauren.label', default: 'Lauren'), laurenInstance.id])
        redirect(action: "show", id: laurenInstance.id)
    }

    def delete() {
        def laurenInstance = Lauren.get(params.id)
        if (!laurenInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lauren.label', default: 'Lauren'), params.id])
            redirect(action: "list")
            return
        }

        try {
            laurenInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'lauren.label', default: 'Lauren'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'lauren.label', default: 'Lauren'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
