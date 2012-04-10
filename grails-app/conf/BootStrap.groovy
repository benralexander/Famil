import re.disp.Movie
import re.disp.Sounds
import grails.util.GrailsUtil
import re.sec.ReRole
import org.springframework.security.core.userdetails.User
import re.sec.ReUser
import re.sec.ReUserReRole
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.apache.commons.logging.LogFactory
import re.disp.Sounds

class BootStrap {
    def springSecurityService

    //private static def log = LogFactory.getLog(this)
    def init = { servletContext ->

        switch (GrailsUtil.environment) {
            case "laptop":
            case "test":
            case "development":
                break;
            case "production":
                break;
        }

        // These are usernames for testing
        def samples = [
                'ben': [fullName: 'ben', password: "a", email: "hhh@ggg.com"],
                'a': [fullName: 'a', password: "a", email: "hhh@ggg.com"],
                'guest': [fullName: 'b', password: "guest", email: "hhh@ggg.com"]
        ]

        File f = new File('wtf.txt')
        f << "starting\n"

        // Testing or otherwise, we always set this up. Why doesn't it come from the database when we aren't testing, I wonder?
        def userRole = ReRole.findByAuthority("ROLE_USER") ?: new ReRole(authority: "ROLE_USER").save(flush: true)
        def adminRole = ReRole.findByAuthority("ROLE_ADMIN") ?: new ReRole(authority: "ROLE_ADMIN").save(flush: true)

        // Go to the database – what do we have?
        def users = ReUser.list() ?: []
        f.write("users=${users.size()}")

        //  If the database didn't have anything for us, then we need to make users from scratch
        if (!users) {
            samples.each {username, profileAttributes ->

                re.sec.ReUser reUser = new re.sec.ReUser(
                        username: username,
                        password: profileAttributes.password,
                        enabled: true)

                if (reUser.validate()) {

                    reUser.save(flush: true)
                    if ((reUser.authorities.contains(adminRole)) ||
                            (reUser.username == 'ben')) {
                        println "user ${username} was admin\n"
                        ReUserReRole.create reUser, userRole, true
                        ReUserReRole.create reUser, adminRole, true
                    } else {
                        println "user ${username} was no admin\n"
                        ReUserReRole.create reUser, userRole, true
                    }

                } else {
                    println "failed to creating user ${username}…"

                }



                users << reUser
            }
        }
        f << "ReUser.count()=${ReUser.count()}"
        //assert re.chm.sec.sec.ReUser.count() == 3
        // def testUser = new ReUser(username: 'me', enabled: true, password: springSecurityService.encodePassword("me"))
        def testUser = new ReUser(username: 'me', enabled: true, password: "me")
        testUser.save(flush: true)
        assert ReUser.count() == 4
        assert ReRole.count() == 2



        if (!(Movie.count()>=3)) {
            new Movie("./movies/Take_0004.mov").save(failOnError: true)
            new Movie("./movies/Take_helicopter.mov").save(failOnError: true)
            new Movie("./movies/morfo_viking.mp4").save(failOnError: true)
        }
        if (!(Sounds.count()>=6)) {
            new Sounds("./songs/Track01.wav").save(failOnError: true)
            new Sounds("./songs/Track02.wav").save(failOnError: true)
            new Sounds("./songs/Track03.wav").save(failOnError: true)
            new Sounds("./songs/Track04.wav").save(failOnError: true)
            new Sounds("./songs/Track05.wav").save(failOnError: true)
            new Sounds("./songs/Track06.wav").save(failOnError: true)
        }
    }
    def destroy = {
    }
}
