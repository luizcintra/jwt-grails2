class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/login/auth"(controller: 'login', action: 'auth')

        "/"(view:"/index")
	}
}
