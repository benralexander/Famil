package re.own

import org.springframework.dao.DataIntegrityViolationException
import re.disp.Sounds
import org.codehaus.groovy.grails.commons.ApplicationHolder
import static org.codehaus.groovy.grails.commons.ApplicationHolder.*

class JeffryBarnesController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [jeffryBarnesInstanceList: JeffryBarnes.list(params), jeffryBarnesInstanceTotal: JeffryBarnes.count()]
    }

    def create() {
        [jeffryBarnesInstance: new JeffryBarnes(params)]
    }

    def save() {
        def jeffryBarnesInstance = new JeffryBarnes(params)
        if (!jeffryBarnesInstance.save(flush: true)) {
            render(view: "create", model: [jeffryBarnesInstance: jeffryBarnesInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'jeffryBarnes.label', default: 'JeffryBarnes'), jeffryBarnesInstance.id])
        redirect(action: "show", id: jeffryBarnesInstance.id)
    }
    def show1() {
        params.soundName="Track01.wav"
        show()
    }
    def show2() {
        params.soundName="Track02.wav"
        show()
    }
    def show3() {
        params.soundName="Track03.wav"
        show()
    }
    def show4() {
        params.soundName="Track04.wav"
        show()
    }
    def show5() {
        params.soundName="Track05.wav"
        show()
    }
    def show6() {
        params.soundName="Track06.wav"
        show()
    }

    def show() {
        println ("Hello there.  your root dir is ${getApplication().getMainContext().getResource("/").getFile().getAbsolutePath()}")
     //   println ("Hello there.  your abs song dir dir is ${getApplication().getMainContext().getResource("C:\\apache-tomcat-7.0.26\\webapps\\Family\\songs").getFile().getAbsolutePath()}")
        try{
            println ("Hello there.  your track01 song dir is ${getApplication().getMainContext().getResource("./songs/Track01.wav").getFile().getAbsolutePath()}")
        }  catch(Exception e) {
              e.printStackTrace()
        }
        try{
            println ("Hello there.  your track01 no per ssong dir is ${getApplication().getMainContext().getResource("songs/Track01.wav").getFile().getAbsolutePath()}")
        }  catch(Exception e) {
            e.printStackTrace()
        }

        println ("Hello there.  your songs dir is ${getApplication().getMainContext().getResource("./songs").getFile().getAbsolutePath()}")
        OutputStream out = response.getOutputStream()
        System.out.println("params.id=${params.id}")
        String soundName =  params.soundName
        System.out.println()
        Sounds sound = Sounds.findByName(soundName)
        System.out.println()
        response.setContentLength(sound.soundBytes().length)
        response.addHeader("Content-disposition", "attachment; filename=${sound.soundFileName()}")
        response.addHeader("Content-type", "audio/vnd.wave")
        out.write(sound.soundBytes())
        out.close()
    }

    def edit() {
        def jeffryBarnesInstance = JeffryBarnes.get(params.id)
        if (!jeffryBarnesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'jeffryBarnes.label', default: 'JeffryBarnes'), params.id])
            redirect(action: "list")
            return
        }

        [jeffryBarnesInstance: jeffryBarnesInstance]
    }

    def update() {
        def jeffryBarnesInstance = JeffryBarnes.get(params.id)
        if (!jeffryBarnesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'jeffryBarnes.label', default: 'JeffryBarnes'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (jeffryBarnesInstance.version > version) {
                jeffryBarnesInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'jeffryBarnes.label', default: 'JeffryBarnes')] as Object[],
                        "Another user has updated this JeffryBarnes while you were editing")
                render(view: "edit", model: [jeffryBarnesInstance: jeffryBarnesInstance])
                return
            }
        }

        jeffryBarnesInstance.properties = params

        if (!jeffryBarnesInstance.save(flush: true)) {
            render(view: "edit", model: [jeffryBarnesInstance: jeffryBarnesInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'jeffryBarnes.label', default: 'JeffryBarnes'), jeffryBarnesInstance.id])
        redirect(action: "show", id: jeffryBarnesInstance.id)
    }

    def delete() {
        def jeffryBarnesInstance = JeffryBarnes.get(params.id)
        if (!jeffryBarnesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'jeffryBarnes.label', default: 'JeffryBarnes'), params.id])
            redirect(action: "list")
            return
        }

        try {
            jeffryBarnesInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'jeffryBarnes.label', default: 'JeffryBarnes'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'jeffryBarnes.label', default: 'JeffryBarnes'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
