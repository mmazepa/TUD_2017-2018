package service;

import java.util.List;

import domain.Animal;
import domain.Breeder;
import domain.Zoo;

public interface SellingManagerInterface {

	void addZoo(Zoo zoo);
	List<Zoo> getAllZoos();
	void deleteZoo(Zoo zoo);
	Zoo findZooByOwner(String owner);

  void addBreeder(Breeder breeder);
  List<Breeder> getAllBreeders();
  void deleteBreeder(Breeder breeder);
  Breeder findBreederById(Long id);
  Breeder findBreederByBreedingSpecies(String breedingSpecies);

	Long addNewAnimal(Animal animal);
	List<Animal> getAvailableAnimals();
	void disposeAnimal(Zoo zoo, Animal animal);
	Animal findAnimalById(Long id);
  Animal findAnimalByBreeder(Breeder breeder);

	List<Animal> getOwnedAnimals(Zoo zoo);
	void sellAnimal(Long zooId, Long animalId);

}
