package jwt.grails2

import org.apache.http.auth.AuthenticationException

class LoginService {

    String authenticate(String username, String password) {
        if (username == 'user.jwt.grails2.test' && password == 'jwt@grails2') {
            return JWTManager.generateToken(username)
        }

        throw new AuthenticationException('Credenciais inválidas')
    }
}
