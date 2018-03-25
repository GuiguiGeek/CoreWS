package fr.guig33k.repertoire.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.guig33k.repertoire.models.Contact;
import fr.guig33k.repertoire.models.ContactRepository;

@Controller
@RequestMapping(path="/repApp") // This means URL's start with /demo (after Application path)
public class ContactController {

	  // Private fields

	  @Autowired
	  private ContactRepository contactRepository;

	  
	  /**
	   * GET /create  --> Create a new Contact and save it in the database.
	   */
	  @RequestMapping("/create")
	  public @ResponseBody String create(@RequestParam String prenom, @RequestParam String nom, @RequestParam String email, @RequestParam String adresse) {
		  	// @ResponseBody means the returned String is the response, not a view name
		  	// @RequestParam means it is a parameter from the GET or POST request
		  
		  String ContactId = "";
		  try {
			  Contact contact = new Contact(prenom, nom, email, adresse);
			  contactRepository.save(contact);
			  ContactId = String.valueOf(contact.getId());
		  }
		  catch (Exception ex) {
			  return "Error creating the Contact: " + ex.toString();
		  }
		  return "Contact succesfully created with id = " + ContactId;
	  }

	  /**
	   * This returns a JSON or XML with the users
	   * @return This returns a JSON or XML with the users
	   */
	  @GetMapping(path="/all")
	  public @ResponseBody Iterable<Contact> getAllUsers() {
		  return contactRepository.findAll();
	  }
	  
	  /**
	   * GET /delete  --> Delete the Contact having the passed id.
	   */
	  @RequestMapping("/delete")
	  @ResponseBody
	  public String delete(long id) {
	    try {
	      Contact contact = new Contact(id);
	      contactRepository.delete(contact);
	    }
	    catch (Exception ex) {
	      return "Error deleting the Contact:" + ex.toString();
	    }
	    return "Contact succesfully deleted!";
	  }
	  
	  /**
	   * GET /get-by-email  --> Return the id for the Contact having the passed
	   * email.
	   */
	  @CrossOrigin
	  @RequestMapping("/get-by-email")
	  @ResponseBody
	  public String getByEmail(String email) {
	    String contactId = "";
	    try {
	      Contact contact = contactRepository.findByEmail(email);
	      contactId = String.valueOf(contact.getId());
	    }
	    catch (Exception ex) {
	      return "Contact not found";
	    }
	    return "The Contact id is: " + contactId;
	  }
	  
	  /**
	   * GET /get-by-email  --> Return the id for the Contact having the passed
	   * email.
	   */
	  @CrossOrigin(origins = "http://localhost:4200")
	  @RequestMapping("/get-by-id")
	  @ResponseBody
	  public Contact getById(long id) {
		  System.out.println("coucou : " + String.valueOf(id));
		  return contactRepository.findById(id);
	  }
	  
	  /**
	   * GET /get-by-email  --> Return the id for the Contact having the passed
	   * email.
	   */
	  @RequestMapping("/get-by-nom")
	  @ResponseBody
	  public String getByNom(String nom) {
	    String contactId = "";
	    try {
	      Contact contact = contactRepository.findByNom(nom);
	      contactId = String.valueOf(contact.getId());
	    }
	    catch (Exception ex) {
	      return "Contact not found";
	    }
	    return "The Contact id is: " + contactId;
	  }
	  
	  /**
	   * GET /update  --> Update the email and the name for the Contact in the 
	   * database having the passed id.
	   */
	  @RequestMapping("/update")
	  @ResponseBody
	  public String updateContact(long id, String email, String name) {
	    try {
	      Contact contact = contactRepository.findOne(id);
	      contact.setEmail(email);
	      contact.setNom(name);
	      contactRepository.save(contact);
	    }
	    catch (Exception ex) {
	      return "Error updating the Contact: " + ex.toString();
	    }
	    return "Contact succesfully updated!";
	  }

	
}
