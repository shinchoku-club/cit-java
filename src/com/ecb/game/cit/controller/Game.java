package com.ecb.game.cit.controller;

import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ecb.game.cit.form.GameForm;

@Controller
public class Game {
	@Autowired
	private Properties systemProperties;

	private GameForm form = new GameForm();

	@RequestMapping("/game")
	public ModelAndView index() {
		form.setWebsocketUrl(systemProperties.getProperty("url.websocket"));
		Date now = new Date();
		form.setNow(String.valueOf(now.getTime()));
		return new ModelAndView("game", "form", form);
	}

	@RequestMapping("/gameTest")
	public ModelAndView test() {
		form.setWebsocketUrl(systemProperties.getProperty("url.websocket"));
		Date now = new Date();
		form.setNow(String.valueOf(now.getTime()));
		return new ModelAndView("gameSp", "form", form);
	}
}
