package com.spgo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.spgo.model.web.PersonInfo;
import com.spgo.service.PersonService;
   
@Controller    
public class PersonController {  
   
	@Autowired
	private PersonService personService;

    @RequestMapping(value = "/person/save", method = RequestMethod.POST)  
	public View createPerson(@ModelAttribute PersonInfo person, ModelMap model) {
    	try {
	    	person.setBirthDay(new Date());
	    	if(StringUtils.hasText(person.getId())) {
	    		personService.updatePerson(person);
	    	} else {
	    		personService.addPerson(person);
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return new RedirectView("/spgo/person");  
    }
        
    @RequestMapping(value = "/person/delete", method = RequestMethod.GET)  
	public View deletePerson(@ModelAttribute PersonInfo person, ModelMap model) {  
	    try {
	    	personService.deletePerson(person);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return new RedirectView("/spgo/person");  
    }
    
    @RequestMapping(value = "/person", method = RequestMethod.GET)  
	public String getPersonList(ModelMap model) {
    	try {
    		model.addAttribute("personList", personService.listPerson());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
        return "listPerson";  
    } 

    // Guest (allow to add new)
    @RequestMapping(value = "/guest", method = RequestMethod.GET)  
	public String getGuest(ModelMap model) {
        return "addPerson";  
    }
    @RequestMapping(value = "/guest/save", method = RequestMethod.POST)  
	public View createGuest(@ModelAttribute PersonInfo person, ModelMap model) {
    	try {
	    	person.setBirthDay(new Date());
	    	if(StringUtils.hasText(person.getId())) {
	    		personService.updatePerson(person);
	    	} else {
	    		personService.addPerson(person);
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return new RedirectView("/spgo/person");  
    }

    // Login-Logout
    @RequestMapping(value = "/login", method = RequestMethod.GET)  
	public String getLogin(ModelMap model) {
        return "login";  
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)  
	public String getLogout(ModelMap model) {
        return "logout";  
    }
}
