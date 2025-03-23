package jwt.grails2

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTCreator
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm

class JWTManager {

    static final String DEFAULT_SECRET = '01a5bb67-7e96-4ef7-974a-03735004f3cb'
    private static final String USERNAME_FIELD = 'user'
    private static final Long MINUTE = 60_000L //Milliseconds
    private static final Long THIRTY_SECONDS = 30_000L
    private static final Long EXPIRATION_TIME = 30 * MINUTE
    private static final Algorithm ALGORITHM = Algorithm.HMAC512(DEFAULT_SECRET)
    private final JWTVerifier VERIFIER = JWT.require(ALGORITHM)
            .acceptLeeway(THIRTY_SECONDS)
            .build()

    static String generateToken(String username) {
        JWTCreator.Builder builder = JWT.create()
                .withIssuer('jwt-grails2')
                .withJWTId(UUID.randomUUID().toString())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))

        builder.withSubject(UUID.randomUUID().toString())
                .withClaim(USERNAME_FIELD, username)
                .sign(ALGORITHM)
    }

}
