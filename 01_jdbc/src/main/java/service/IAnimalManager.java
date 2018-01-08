package service;

import java.sql.Connection;
import java.util.List;

import domain.Animal;

interface IAnimalManager {

  public int addAnimal(Animal animal);
  public int updateAnimal(String oldSpecies, String newSpecies);
  public int deleteAnimal(long animalId);

  public List<Animal> getAllAnimals();
  public Animal findAnimalBySpecies(String species);

  // batch insert - transactional
  public void addAllAnimals(List<Animal> animals);

  // batch update - transactional
  public int updateAllAnimals(List<Animal> animals);

  // business method - transactional
  public Boolean businessMethod(List<Animal> animals, String[] newSpecies);

}
