package jwt.grails2

import org.apache.http.HttpStatus
import org.apache.http.auth.AuthenticationException

class CheckerFilters {

    def filters = {
        loginCheck(controller: 'login', action: 'auth') {
            before = {
                String secret = request.getHeader('secret_key')
                if (secret != JWTManager.DEFAULT_SECRET) {
                    render status: HttpStatus.SC_UNAUTHORIZED, text: 'Api não autenticada adicione a configuração correta'
                    return false
                }
            }
        }
        authenticationCheck(controller: 'hello', action: '*') {
            before = {
                String bearerToken = request.getHeader('Authorization')
                String[] parts = bearerToken?.split('Bearer ')
                String jwt = parts && parts.size() == 2 ? parts[1] : null

                try {
                    JWTManager.verifyAndDecode(jwt)
                    return true
                } catch (AuthenticationException ex) {
                    render status: HttpStatus.SC_UNAUTHORIZED, text: ex.message
                    return false
                }
            }
        }
    }
}
