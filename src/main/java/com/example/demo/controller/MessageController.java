package com.example.demo.controller;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
	
	@Autowired
	MessageSource messageSource;
	
	@GetMapping("/message/notgification")
	public String getMessage(@RequestHeader(name="Accept-Language") Locale locale) {
		
		return messageSource.getMessage("messahe.notification.displey",null,locale);
		
	}

	@GetMapping("/message/notgification/{word}")
	public String[] readWords(Locale locale, @PathVariable String word) {
		return wordToArray(messageSource.getMessage("counting.word",null,locale), word);
	}
	
	private static String[] wordToArray(String content, String word) {
		return getValues(content, word).toString().split(" ");
	}
	
	
	private static StringBuilder getValues(String content, String word) {
		int count =0;
		Pattern pattern = Pattern.compile(word);
		Matcher matcches = pattern.matcher(content);
		
		StringBuilder builder = new StringBuilder();
		
		while(matcches.find()) {
				
			count++;
		}
		builder.append(word);
		builder.append(":");
		builder.append(count);
		return builder;
		
	}
}
