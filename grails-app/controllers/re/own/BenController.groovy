package re.own

import re.disp.Movie

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.multipart.MultipartHttpServletRequest
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.commons.ApplicationHolder

class BenController {
    def springSecurityService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [papaInstanceList: Ben.list(params), papaInstanceTotal: Ben.count(), foo:"------------"]
    }

    def create() {
        [papaInstance: new Ben(params)]
    }

    def saveOsra() {
        if ( request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
            def downloadedFile  =  multipartHttpServletRequest.getFile("payload")
            downloadedFile.transferTo(new File("c:/temp/downloaded.png"))
        }
        redirect(action: "list", params: params)
    }

    def save() {
        def papaInstance = new Ben(params)
        if (!papaInstance.save(flush: true)) {
            render(view: "create", model: [papaInstance: papaInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'papa.label', default: 'Ben'), papaInstance.id])
        redirect(action: "show", id: papaInstance.id)
    }

//   @Secured(['ROLE_USER'])
    def show() {
      //  println ("Hello there,${currentUser ()}")
        println ("Hello there.  your dir is ${ApplicationHolder.getApplication().getMainContext().getResource("/").getFile().getAbsolutePath()}")
        OutputStream out = response.getOutputStream()
        String movieName =  params.movieName
        Movie m = Movie.findByName(movieName)
        response.setContentLength(m.movieBytes().length)
        response.addHeader("Content-disposition", "attachment; filename=${m.movieFileName()}")
        response.addHeader("Content-type", "video/quicktime")
        out.write(m.movieBytes())
        out.close()

        def papaInstance = Ben.get(params.id)

        [papaInstance: papaInstance]
    }

    def update() {
        def papaInstance = Ben.get(params.id)
        if (!papaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'papa.label', default: 'Ben'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (papaInstance.version > version) {
                papaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'papa.label', default: 'Ben')] as Object[],
                          "Another user has updated this Ben while you were editing")
                render(view: "edit", model: [papaInstance: papaInstance])
                return
            }
        }

        papaInstance.properties = params

        if (!papaInstance.save(flush: true)) {
            render(view: "edit", model: [papaInstance: papaInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'papa.label', default: 'Ben'), papaInstance.id])
        redirect(action: "show", id: papaInstance.id)
    }

    def delete() {
        def papaInstance = Ben.get(params.id)
        if (!papaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'papa.label', default: 'Ben'), params.id])
            redirect(action: "list")
            return
        }

        try {
            papaInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'papa.label', default: 'Ben'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'papa.label', default: 'Ben'), params.id])
            redirect(action: "show", id: params.id)
        }
    }


}
