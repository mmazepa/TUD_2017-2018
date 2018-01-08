package domain;

import java.sql.Date;

public class Animal {

	private long id;
	private String species;
  private double height;
  private double weight;
  private boolean isExtinct;
  private Date dateOfBirth;

	public Animal() {
	}

	public Animal(String species, double height, double weight, boolean isExtinct, Date dateOfBirth) {
		super();
		this.species = species;
		this.height = height;
    this.weight = weight;
    this.isExtinct = isExtinct;
    this.dateOfBirth = dateOfBirth;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

  public String getSpecies() {
    return species;
  }
  public void setSpecies(String species){
    this.species = species;
  }

  public double getHeight(){
    return height;
  }
  public void setHeight(double height){
    this.height = height;
  }

  public double getWeight(){
    return weight;
  }
  public void setWeight(double weight){
    this.weight = weight;
  }

  public boolean getIsExtinct(){
    return isExtinct;
  }
  public void setIsExtinct(boolean isExtinct){
    this.isExtinct = isExtinct;
  }

  public Date getDateOfBirth(){
    return dateOfBirth;
  }
  public void setDateOfBirth(Date dateOfBirth){
    this.dateOfBirth = dateOfBirth;
  }
}
