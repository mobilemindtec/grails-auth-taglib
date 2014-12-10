package br.com.mobilemind

class AuthTagLib {

	static namespace =  "auth"
	def authService

	def isAdmin = { attrs, body ->

 		if(authService.isAdmin() || authService.isRoot()){
 			out << body()
 		}
	}


	def isUser = { attrs, body ->
 		if(authService.isAdmin() || authService.isUser() || authService.isRoot()){
 			out << body()
 		}
	}

	def isRoot = { attrs, body ->
 		if(authService.isRoot()){
 			out << body()
 		}
	}

	def writeUsername = { attr, body ->
 		if(authService.isAuthenticated()){
 			out << authService.user().name
 		}
	}	

	def isAdmin(){	
		return authService.isAdmin()
	}

	def isUser(){
		return authService.isUser()
	}

	def isRoot(){
		return authService.isRoot()
	}


}
