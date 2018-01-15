package service;

import java.sql.Connection;
import java.util.List;

import domain.Animal;

interface IAnimalManager {

  // standard CRUD - add, update, delete
  public int addAnimal(Animal animal);
  public int updateAnimal(String oldSpecies, String newSpecies);
  public int deleteAnimal(long animalId);

  // SELECT's for CRUD - getAll, findBy...
  public List<Animal> getAllAnimals();
  public Animal findAnimalBySpecies(String species);

  // batch insert - transactional
  public void addAllAnimals(List<Animal> animals);

  // batch update - transactional
  public int updateAllAnimals(List<Animal> animals);

  // business method - transactional
  public Boolean businessMethod(List<Animal> animals, String[] newSpecies);

}
