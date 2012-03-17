package re.disp

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
        File tMovieFile = new File(movieReference)
        this.name = tMovieFile.getName()
        movieFile = tMovieFile
    }

    static constraints = {
//        movieFile validator: {
//            it.exists()
//        }
    }
}

