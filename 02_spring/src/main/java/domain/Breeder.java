package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Table;

@Entity
@Table(name="Breeder")
@NamedQueries({
    @NamedQuery(name = "breeder.all", query = "Select b from Breeder b"),
    @NamedQuery(name = "breeder.bySpecies", query = "Select b from Breeder b where b.breedingSpecies = :breedingSpecies")
})
public class Breeder {

	private Long id;
	private String firstName;
	private String breedingSpecies;
  private Address address;

  public Breeder(String firstName, String breedingSpecies, Address address) {
    this.firstName = firstName;
    this.breedingSpecies = breedingSpecies;
    this.address = address;
  }

  public Breeder() {

  }

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

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  public Address getAddress() {
    return address;
  }
  public void setAddress(Address address) {
    this.address = address;
  }
}
