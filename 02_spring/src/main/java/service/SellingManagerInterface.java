package service;

import java.util.List;

import domain.Animal;
import domain.Address;
import domain.Breeder;
import domain.Zoo;

public interface SellingManagerInterface {

	void addZoo(Zoo zoo);
	List<Zoo> getAllZoos();
  void updateZoo(Zoo oldZoo, Zoo newZoo);
	void deleteZoo(Zoo zoo);
	Zoo findZooByOwner(String owner);

  void addAddress(Address address);
  List<Address> getAllAddresses();
  void updateAddress(Address oldAddress, Address newAddress);
  void deleteAddress(Address address);

  void addBreeder(Breeder breeder);
  List<Breeder> getAllBreeders();
  void updateBreeder(Breeder oldBreeder, Breeder newBreeder);
  void deleteBreeder(Breeder breeder);
  Breeder findBreederById(Long id);
  Breeder findBreederByBreedingSpecies(String breedingSpecies);

	Long addNewAnimal(Animal animal);
	List<Animal> getAvailableAnimals();
  List<Animal> getAllAnimals();
  void updateAnimal(Animal oldAnimal, Animal newAnimal);
  void deleteAnimal(Animal animal);
	void disposeAnimal(Zoo zoo, Animal animal);
	Animal findAnimalById(Long id);
  Animal findAnimalByBreeder(Breeder breeder);

	List<Animal> getOwnedAnimals(Zoo zoo);
	void sellAnimal(Long zooId, Long animalId);

}
