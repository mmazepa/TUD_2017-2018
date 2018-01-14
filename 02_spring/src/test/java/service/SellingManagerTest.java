package service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Animal;
import domain.Breeder;
import domain.Zoo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class SellingManagerTest {

	@Autowired
	SellingManagerInterface sellingManager;

	private final String ZOO_NAME_1 = "Gdański Ogród Zoologiczny";
	private final String ZOO_OWNER_1 = "Ferdynand Gdański";

  private final String ZOO_NAME_2 = "Białowieski Ogród Zoologiczny";
  private final String ZOO_OWNER_2 = "Kazimierz Białowieski";

  private final String ZOO_NAME_3 = "Sosnowski Ogród Zoologiczny";
  private final String ZOO_OWNER_3 = "Marcin Sosnowski";

	private final String ANIMAL_SPECIES_1 = "Słoń";
	private final String ANIMAL_NAME_1 = "Franciszek";

  private final String ANIMAL_SPECIES_2 = "Lew";
  private final String ANIMAL_NAME_2 = "Stanisław";

  private final String ANIMAL_SPECIES_3 = "Krokodyl";
  private final String ANIMAL_NAME_3 = "Ryszard";

  private final String BREEDER_FIRSTNAME_1 = "Bronisław";
  private final String BREEDER_BREEDINGSPECIES_1 = "Słoń";

  private final String BREEDER_FIRSTNAME_2 = "Mieczysław";
  private final String BREEDER_BREEDINGSPECIES_2 = "Lew";

  private final String BREEDER_FIRSTNAME_3 = "Tadeusz";
  private final String BREEDER_BREEDINGSPECIES_3 = "Krokodyl";

	@Test
	public void addZooCheck() {

		List<Zoo> retrievedZoos = sellingManager.getAllZoos();

		// If there is a zoo with ZOO_OWNER_1 delete it
		for (Zoo zoo : retrievedZoos) {
			if (zoo.getOwner().equals(ZOO_OWNER_1)) {
				sellingManager.deleteZoo(zoo);
			}
		}

		Zoo zoo = new Zoo();
		zoo.setName(ZOO_NAME_1);
		zoo.setOwner(ZOO_OWNER_1);
		// ... other properties here

		// Owner is Unique
		sellingManager.addZoo(zoo);

		Zoo retrievedZoo = sellingManager.findZooByOwner(ZOO_OWNER_1);

		assertEquals(ZOO_NAME_1, retrievedZoo.getName());
		assertEquals(ZOO_OWNER_1, retrievedZoo.getOwner());
		// ... check other properties here
	}

	@Test
	public void addAnimalCheck() {

		Animal animal = new Animal();
		animal.setSpecies(ANIMAL_SPECIES_1);
		animal.setName(ANIMAL_NAME_1);
		// ... other properties here

		Long animalId = sellingManager.addNewAnimal(animal);

		Animal retrievedAnimal = sellingManager.findAnimalById(animalId);
		assertEquals(ANIMAL_SPECIES_1, retrievedAnimal.getSpecies());
		assertEquals(ANIMAL_NAME_1, retrievedAnimal.getName());
		// ... check other properties here

	}

  @Test
	public void addBreederCheck() {

		Breeder breeder = new Breeder();
		breeder.setFirstName(BREEDER_FIRSTNAME_1);
		breeder.setBreedingSpecies(BREEDER_BREEDINGSPECIES_1);
		// ... other properties here

		sellingManager.addBreeder(breeder);

		Breeder retrievedBreeder = sellingManager.findBreederByBreedingSpecies(BREEDER_BREEDINGSPECIES_1);

		assertEquals(BREEDER_FIRSTNAME_1, retrievedBreeder.getFirstName());
		assertEquals(BREEDER_BREEDINGSPECIES_1, retrievedBreeder.getBreedingSpecies());
		// ... check other properties here

	}

	@Test
	public void sellAnimalCheck() {

		Zoo zoo = new Zoo();
		zoo.setName(ZOO_NAME_2);
		zoo.setOwner(ZOO_OWNER_2);

		sellingManager.addZoo(zoo);

		Zoo retrievedZoo = sellingManager.findZooByOwner(ZOO_OWNER_2);

		Animal animal = new Animal();
		animal.setSpecies(ANIMAL_SPECIES_2);
		animal.setName(ANIMAL_NAME_2);

		Long animalId = sellingManager.addNewAnimal(animal);

		sellingManager.sellAnimal(retrievedZoo.getId(), animalId);

		List<Animal> ownedAnimals = sellingManager.getOwnedAnimals(retrievedZoo);

		assertEquals(1, ownedAnimals.size());
		assertEquals(ANIMAL_SPECIES_2, ownedAnimals.get(0).getSpecies());
		assertEquals(ANIMAL_NAME_2, ownedAnimals.get(0).getName());
	}

	@Test
	public void disposeAnimalCheck() {

		Zoo zoo = new Zoo();
    zoo.setName(ZOO_NAME_3);
    zoo.setOwner(ZOO_OWNER_3);

    Animal animal = new Animal();
    animal.setSpecies(ANIMAL_SPECIES_3);
    animal.setName(ANIMAL_NAME_3);

    sellingManager.addZoo(zoo);
    sellingManager.addNewAnimal(animal);

    sellingManager.disposeAnimal(zoo, animal);

    Zoo retrievedZoo = sellingManager.findZooByOwner(ZOO_OWNER_3);
    List<Animal> ownedAnimals = sellingManager.getOwnedAnimals(retrievedZoo);

    assertEquals(0, ownedAnimals.size());

	}

}
