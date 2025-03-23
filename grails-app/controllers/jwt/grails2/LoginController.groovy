package jwt.grails2

class LoginController {

    LoginService loginService

    def auth(String username, String password) {
        render loginService.authenticate(username, password)
    }

}
