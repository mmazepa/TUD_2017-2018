package service;

import java.util.List;

import domain.Animal;
import domain.Zoo;

public interface SellingManagerInterface {

	void addZoo(Zoo zoo);
	List<Zoo> getAllZoos();
	void deleteZoo(Zoo zoo);
	Zoo findZooByOwner(String owner);

	Long addNewAnimal(Animal animal);
	List<Animal> getAvailableAnimals();
	void disposeAnimal(Zoo zoo, Animal animal);
	Animal findAnimalById(Long id);

	List<Animal> getOwnedAnimals(Zoo zoo);
	void sellAnimal(Long zooId, Long animalId);

}
