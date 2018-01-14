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

@Entity
@NamedQueries({
    @NamedQuery(name = "animal.all", query = "Select a from Animal a"),
		@NamedQuery(name = "animal.unsold", query = "Select a from Animal a where a.sold = false"),
  	@NamedQuery(name = "animal.byBreeder", query = "Select a from Animal a where a.breeder = :breeder")
})
public class Animal {

	private Long id;
	private String species;
	private String name;
	private Boolean sold = false;
  private Breeder breeder;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Boolean getSold() {
		return sold;
	}
	public void setSold(Boolean sold) {
		this.sold = sold;
	}

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  public Breeder getBreeder() {
    return breeder;
  }
  public void setBreeder(Breeder breeder) {
    this.breeder = breeder;
  }
}
