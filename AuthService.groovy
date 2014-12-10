package br.com.mobilemind

class AuthService {

	def springSecurityService // injected by Spring	

	def isAdmin(){

		def auth = springSecurityService.authentication 
	 	def authorities = auth?.authorities;

	 	if(authorities){
	     	for(c in authorities){
	     		if(c.authority == 'ROLE_ADMIN' || c.authority == 'ROLE_ROOT'){
	     			return true
	     		}
	     	}
	 	} 

	 	return false    
	}
	

	def isUser(){
		def auth = springSecurityService.authentication 
	 	def authorities = auth?.authorities;

	 	if(authorities){
	     	for(c in authorities){
	     		if(c.authority == 'ROLE_ADMIN' || c.authority == 'ROLE_ROOT' || c.authority == 'ROLE_USER'){
	     			return true
	     		}
	     	}
	 	}     

	 	return false
	}

	def isRoot(){
		def auth = springSecurityService.authentication 
	 	def authorities = auth?.authorities;

	 	if(authorities){
	     	for(c in authorities){
	     		if(c.authority == 'ROLE_ROOT'){
	     			return true
	     		}
	     	}
	 	} 

	 	return false    

	}

	def isAnonymous(){
	    def principal = springSecurityService.getPrincipal()

	    return ("anonymousUser".equals(principal.toString()))
	}

	def isAuthenticated(){
		return isUser() || isAdmin() || isRoot()
	}

def user(){
    def principal = springSecurityService.getPrincipal()

    if(!principal)
    	return null

    if("anonymousUser".equals(principal.toString()))
        return null             

    return User.findWhere(username: principal.username) 
}

	def tenant(){
		def user = user()

		if(user != null)
			return Tenant.get(user.userTenantId)		

		return null
	}
}
