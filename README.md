# jwt-grails2
Projeto de estudo sobre JWT do treinamento do ZG Resolve

Uma escolha de projeto foi utilizar poucas libs e fazer a autenticação de maneira mais "manual".

Esse projeto tem as seguintes dependências:
```
    Java zulu-7.56.0.11
    Grails 2.3.11
```

Para realizar o login os usuários estão definidos em Hardcoded e são os seguintes:
```
    username: user.jwt.grails2.test
    password: jwt@grails2
```

Além disso, é necessário passar a secret no header:
```
secret_key: 01a5bb67-7e96-4ef7-974a-03735004f3cb
```

Portanto para realizar o login basta enviar a seguinte requisição:

```
curl --location 'http://localhost:8080/jwt-grails2/login/auth' \
--header 'secret_key: 01a5bb67-7e96-4ef7-974a-03735004f3cb' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'username=user.jwt.grails2.test' \
--data-urlencode 'password=jwt@grails2'
```

A partir do Token obtido o endpoint HelloWorld pode ser acessado passando o Token Bearer. Ou seja, basta executar a requisição abaixo:

```
curl 'http://localhost:8080/jwt-grails2/hello' \
-H 'Authorization: Bearer /* INSIRA O TOKEN OBTIDO AQUI */'
```
