package re.disp

import static org.codehaus.groovy.grails.commons.ApplicationHolder.getApplication

class Movie extends Disp {
    File movieFile=null

    byte[] movieBytes() {
        movieFile.getBytes()
    }

    String movieFileName() {
        movieFile.name
    }

    Movie(String movieReference) {
        super()
        File tMovieFile =  getApplication().getMainContext().getResource(movieReference).getFile()
        this.name = tMovieFile.getName()
        movieFile = tMovieFile
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", movieFile=" + movieFile +
                ", version=" + version +
                '}';
    }

    static constraints = {
//        movieFile validator: {
//            it.exists()
//        }
    }
}

