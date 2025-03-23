package jwt.grails2

import org.apache.http.auth.AuthenticationException

class CheckerFilters {

    def filters = {
        loginCheck(controller: 'login', action: 'auth') {
            before = {
                String secret = request.getHeader('secret_key')
                if (secret != JWTManager.DEFAULT_SECRET) {
                    throw new AuthenticationException('Api não autenticada adicione a configuração correta')
                }
            }
        }
        authenticationCheck(controller: 'hello', action: '*') {
            before = {
                if (controllerName != 'login') {
                    String bearerToken = request.getHeader('Authorization')
                    String[] parts = bearerToken?.split('Bearer ')
                    String jwt = parts && parts.size() == 2 ? parts[1] : null
                    JWTManager.verifyAndDecode(jwt)
                }
            }
        }
    }
}
