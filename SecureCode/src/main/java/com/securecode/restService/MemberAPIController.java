package com.securecode.restService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.securecode.Controller.MemberController;
import com.securecode.restService.Response.MemberResponse;
import com.securecode.restService.Response.Response;

@RestController
public class MemberAPIController {

	@CrossOrigin(origins = "*")
	@GetMapping("/api/member")
	public ResponseEntity<Response> getMembers() {
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

	/*
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