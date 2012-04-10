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
            println ("Hello from sounds.  see this? ${getApplication().getMainContext().getResource("./songs/Track01.wav").getFile().getAbsolutePath()}")
        }  catch(Exception e) {
            e.printStackTrace()
        }
        try{
            println ("Hello from sounds.  and what about this ? ${getApplication().getMainContext().getResource(soundReference).getFile().getAbsolutePath()}")
        }  catch(Exception e) {
            e.printStackTrace()
        }

        File tSoundFile =  getApplication().getMainContext().getResource(soundReference).getFile()
        this.name = tSoundFile.getName()
        soundFile = tSoundFile
    }

    static constraints = {
    }
}
