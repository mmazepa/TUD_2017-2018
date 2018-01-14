package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "breeder.bySpecies", query = "Select b from Breeder b where b.breedingSpecies = :breedingSpecies")
})
public class Breeder {

	private Long id;
	private String firstName;
	private String breedingSpecies;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

  public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getBreedingSpecies() {
		return breedingSpecies;
	}

	public void setBreedingSpecies(String breedingSpecies) {
		this.breedingSpecies = breedingSpecies;
	}
}
