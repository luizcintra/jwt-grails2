package jwt.grails2

import org.apache.http.auth.AuthenticationException
import org.springframework.http.HttpStatus

class LoginController {

    LoginService loginService

    def auth(String username, String password) {
        try {
            render loginService.authenticate(username, password)
        } catch (AuthenticationException ex) {
            render status: HttpStatus.UNAUTHORIZED, text: ex.message
        }
    }

}
