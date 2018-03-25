package fr.guig33k.repertoire.models;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository  extends CrudRepository<Contact, Long> {
	
	  /*
	   * These method will find an User instance in the database by its email, id or name.
	   * Note that this method is not implemented and its working code will be
	   * automatically generated from its signature by Spring Data JPA.
	   */
	
	  public Contact findByEmail(String email);
	
	  public Contact findById(long id);
	
	  public Contact findByNom(String nom);
	
}
