package re.disp

import static org.codehaus.groovy.grails.commons.ApplicationHolder.getApplication

class Sounds extends Disp {
    File soundFile=null

    byte[] soundBytes() {
        soundFile.getBytes()
    }

    String soundFileName() {
        soundFile.name
    }

    Sounds(String soundReference) {
        super()
        try{
            println ("Problem locating sounds file: ${getApplication().getMainContext().getResource(soundReference).getFile().getAbsolutePath()}")
        }  catch(FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace()
        }

        File tSoundFile =  getApplication().getMainContext().getResource(soundReference).getFile()
        this.name = tSoundFile.getName()
        soundFile = tSoundFile
    }

    static constraints = {
    }
}
