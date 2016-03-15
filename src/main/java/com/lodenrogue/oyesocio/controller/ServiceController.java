package com.lodenrogue.oyesocio.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lodenrogue.oyesocio.TypeResponse;
import com.lodenrogue.oyesocio.model.User;
import com.lodenrogue.oyesocio.service.UserFacade;
import com.lodenrogue.oyesocio.view.HtmlViewBuilder;

@RestController
public class ServiceController {

	@RequestMapping(path = "/api/signin", method = RequestMethod.GET)
	public TypeResponse signin(@RequestParam String email) throws IOException {
		User user = new UserController().getUser(email);
		String type = "";
		String data = "";
		if (user == null) {
			type = "SIGNUP";
		}
		else {
			data = new HtmlViewBuilder("schemas").buildProfile(user);
			type = "PROFILE";
		}

		TypeResponse response = new TypeResponse();
		response.setType(type);
		response.setData(data);
		return response;
	}

	@RequestMapping(path = "/api/register", method = RequestMethod.POST)
	public String register(@RequestParam String email, @RequestParam String firstName, @RequestParam String lastName) {
		User user = new UserFacade().findByEmail(email);
		if (user == null) {
			user = new User();
			user.setEmail(email);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user = new UserFacade().create(user);
		}
		return new HtmlViewBuilder("schemas").buildProfile(user);
	}

}
