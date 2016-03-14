package com.lodenrogue.oyesocio.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lodenrogue.oyesocio.JsonResponse;
import com.lodenrogue.oyesocio.model.User;
import com.lodenrogue.oyesocio.service.UserFacade;

@RestController
public class ServiceController {

	// @RequestMapping(path = "/api/services", method = RequestMethod.GET)
	// public String processRequest(@RequestParam String email, @RequestParam
	// String subject, @RequestParam String body) {
	// System.out.println("Subject is " + subject);
	// return parseCommands(subject).get(0);
	// }
	//
	// private List<String> parseCommands(String subject) {
	// String[] split = subject.split(" ");
	// List<String> commands = new ArrayList<String>();
	//
	// for (int i = 1; i < split.length; i++) {
	// commands.add(split[i]);
	// }
	// return commands;
	// }

	@RequestMapping(path = "/api/main", method = RequestMethod.GET)
	public JsonResponse processMain(@RequestParam String email) throws IOException {
		User user = new UserFacade().findByEmail(email);
		String type = "";
		if (user == null) {
			type = "SIGNUP";
		}
		else {
			type = "PROFILE";
		}
		JsonResponse response = new JsonResponse();
		response.setType(type);
		response.setData("");
		return response;
	}
}
