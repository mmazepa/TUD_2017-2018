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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import domain.Animal;
import domain.Address;;
import domain.Breeder;
import domain.Zoo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class SellingManagerTest {

	@Autowired
	SellingManagerInterface sellingManager;

  // ZOOS FOR TESTING
	private final String ZOO_NAME_1 = "Gdański Ogród Zoologiczny";
	private final String ZOO_OWNER_1 = "Ferdynand Gdański";

  private final String ZOO_NAME_2 = "Białowieski Ogród Zoologiczny";
  private final String ZOO_OWNER_2 = "Kazimierz Białowieski";

  private final String ZOO_NAME_3 = "Sosnowski Ogród Zoologiczny";
  private final String ZOO_OWNER_3 = "Marcin Sosnowski";

  // ANIMALS FOR TESTING
	private final String ANIMAL_SPECIES_1 = "Słoń";
	private final String ANIMAL_NAME_1 = "Franciszek";

  private final String ANIMAL_SPECIES_2 = "Lew";
  private final String ANIMAL_NAME_2 = "Stanisław";

  private final String ANIMAL_SPECIES_3 = "Krokodyl";
  private final String ANIMAL_NAME_3 = "Ryszard";

  // BREEDERS FOR TESTING
  private final String BREEDER_FIRSTNAME_1 = "Bronisław";
  private final String BREEDER_BREEDINGSPECIES_1 = ANIMAL_SPECIES_1;

  private final String BREEDER_FIRSTNAME_2 = "Mieczysław";
  private final String BREEDER_BREEDINGSPECIES_2 = ANIMAL_SPECIES_2;

  private final String BREEDER_FIRSTNAME_3 = "Tadeusz";
  private final String BREEDER_BREEDINGSPECIES_3 = ANIMAL_SPECIES_3;

  // ADDRESSES FOR TESTING
  private final String ADDRESS_STREET_1 = "Oliwska";
  private final int ADDRESS_NUMBER_1 = 4;
  private final String ADDRESS_POSTALCODE_1 = "17-141";
  private final String ADDRESS_CITY_1 = "Gdańsk";
  private final String ADDRESS_COUNTRY_1 = "Polska";

  private final String ADDRESS_STREET_2 = "Białowieska";
  private final int ADDRESS_NUMBER_2 = 7;
  private final String ADDRESS_POSTALCODE_2 = "24-547";
  private final String ADDRESS_CITY_2 = "Białowieża";
  private final String ADDRESS_COUNTRY_2 = "Polska";

  private final String ADDRESS_STREET_3 = "Sosnowska";
  private final int ADDRESS_NUMBER_3 = 13;
  private final String ADDRESS_POSTALCODE_3 = "93-215";
  private final String ADDRESS_CITY_3 = "Sosnowiec";
  private final String ADDRESS_COUNTRY_3 = "Polska";

  // Font style - color, bold and reset (console)
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BLUE  = "\u001B[34m";
  public static final String ANSI_WHITE = "\u001B[37m";
  public static final String ANSI_RED   = "\u001B[31m";
  public static final String ANSI_BOLD  = "\u001B[1m";

  public static void testInfo(String testName) {
    System.out.println(colorizeText(boldText("[TEST]:"), "white") + " " + colorizeText(testName, "white"));
  }

  public static void testInfo(String testInfo, String data1, String data2) {
    String successInfo = colorizeText(boldText("[SUCCESS]:"), "blue") + " " + colorizeText( testInfo + " is correct.", "blue");
    String failureInfo = colorizeText(boldText("[FAILURE]:"), "red") + " " + colorizeText( testInfo + " is incorrect.", "red");

    if (data1.equals(data2)) System.out.println(successInfo);
    else System.out.println(failureInfo);
  }

  public static void testInfo(String testInfo, Boolean data1, Boolean data2) {
    String successInfo = colorizeText(boldText("[SUCCESS]:"), "blue") + " " + colorizeText( testInfo + " is correct.", "blue");
    String failureInfo = colorizeText(boldText("[FAILURE]:"), "red") + " " + colorizeText( testInfo + " is incorrect.", "red");

    if (data1 == data2) System.out.println(successInfo);
    else System.out.println(failureInfo);
  }

  public static void testInfo(String testInfo, int data1, int data2) {
    String successInfo = colorizeText(boldText("[SUCCESS]:"), "blue") + " " + colorizeText( testInfo + " is correct.", "blue");
    String failureInfo = colorizeText(boldText("[FAILURE]:"), "red") + " " + colorizeText( testInfo + " is incorrect.", "red");

    if (data1 == data2) System.out.println(successInfo);
    else System.out.println(failureInfo);
  }

  public static void testInfo(String testInfo, Breeder data1, Breeder data2) {
    String successInfo = colorizeText(boldText("[SUCCESS]:"), "blue") + " " + colorizeText( testInfo + " is correct.", "blue");
    String failureInfo = colorizeText(boldText("[FAILURE]:"), "red") + " " + colorizeText( testInfo + " is incorrect.", "red");

    if (data1 == data2) System.out.println(successInfo);
    else System.out.println(failureInfo);
  }

  public static String colorizeText(String textToColorize, String colorName) {
    if (colorName == "blue") {
      return ANSI_BLUE + textToColorize + ANSI_RESET;
    }
    else if (colorName == "red") {
      return ANSI_RED + textToColorize + ANSI_RESET;
    }
    else if (colorName == "white") {
      return ANSI_WHITE + textToColorize + ANSI_RESET;
    }
    else return textToColorize;
  }

  public static String boldText(String textToBold) {
    return ANSI_BOLD + textToBold + ANSI_RESET;
  }

  @BeforeClass
  public static void setUpClass() {
    // Before testing
    System.out.println(colorizeText(boldText("[TESTS START]"), "white") + "\n");
  }

  @AfterClass
  public static void tearDownClass() {
    // After testing
    System.out.println("\n" + colorizeText(boldText("[TESTS END]"), "white"));
  }

  @Before
  public void setUp() {
    // Before every test
    System.out.println("");
  }

  @After
  public void tearDown() {
    // After every test
  }

	@Test
	public void addZooCheck() {

    testInfo("Add Zoo Check");

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
    testInfo("Zoo name", ZOO_NAME_1, retrievedZoo.getName());

		assertEquals(ZOO_OWNER_1, retrievedZoo.getOwner());
    testInfo("Zoo owner", ZOO_OWNER_1, retrievedZoo.getOwner());
		// ... check other properties here
	}

	@Test
	public void addAnimalCheck() {

    testInfo("Add Animal Check");

		Animal animal = new Animal();
		animal.setSpecies(ANIMAL_SPECIES_1);
		animal.setName(ANIMAL_NAME_1);
    animal.setSold(false);
    Breeder breeder = new Breeder();
    breeder.setFirstName(BREEDER_FIRSTNAME_1);
    breeder.setBreedingSpecies(BREEDER_BREEDINGSPECIES_1);
    sellingManager.addBreeder(breeder);
    animal.setBreeder(breeder);
		// ... other properties here

		Long animalId = sellingManager.addNewAnimal(animal);

		Animal retrievedAnimal = sellingManager.findAnimalById(animalId);

    assertEquals(ANIMAL_SPECIES_1, retrievedAnimal.getSpecies());
    testInfo("Animal species", ANIMAL_SPECIES_1, retrievedAnimal.getSpecies());

		assertEquals(ANIMAL_NAME_1, retrievedAnimal.getName());
    testInfo("Animal name", ANIMAL_NAME_1, retrievedAnimal.getName());

    assertEquals(false, retrievedAnimal.getSold());
    testInfo("Animal sold status", false, retrievedAnimal.getSold());

    assertEquals(breeder, retrievedAnimal.getBreeder());
    testInfo("Animal breeder", breeder, retrievedAnimal.getBreeder());
		// ... check other properties here

	}

  @Test
	public void addBreederCheck() {

    testInfo("Add Breeder Check");

		Breeder breeder = new Breeder();
		breeder.setFirstName(BREEDER_FIRSTNAME_1);
		breeder.setBreedingSpecies(BREEDER_BREEDINGSPECIES_1);
		// ... other properties here

		sellingManager.addBreeder(breeder);

		Breeder retrievedBreeder = sellingManager.findBreederByBreedingSpecies(BREEDER_BREEDINGSPECIES_1);

		assertEquals(BREEDER_FIRSTNAME_1, retrievedBreeder.getFirstName());
    testInfo("Breeder name", BREEDER_FIRSTNAME_1, retrievedBreeder.getFirstName());

		assertEquals(BREEDER_BREEDINGSPECIES_1, retrievedBreeder.getBreedingSpecies());
    testInfo("Breeder breeding species", BREEDER_BREEDINGSPECIES_1, retrievedBreeder.getBreedingSpecies());
		// ... check other properties here

	}

  @Test
  public void addAddressCheck() {

    testInfo("Add Address Check");

    Address address = new Address();
    address.setStreet(ADDRESS_STREET_1);
    address.setNumber(ADDRESS_NUMBER_1);
    address.setPostalCode(ADDRESS_POSTALCODE_1);
    address.setCity(ADDRESS_CITY_1);
    address.setCountry(ADDRESS_COUNTRY_1);
		// ... other properties here

    sellingManager.addAddress(address);

    List<Address> addresses = sellingManager.getAllAddresses();

    // assertEquals(1, addresses.size());
    // testInfo("Addresses count", 1, addresses.size());

    Address retrievedAddress = addresses.get(0);

    assertEquals(ADDRESS_STREET_1, retrievedAddress.getStreet());
    testInfo("Address street", ADDRESS_STREET_1, retrievedAddress.getStreet());

    assertEquals(ADDRESS_NUMBER_1, retrievedAddress.getNumber());
    testInfo("Address number", ADDRESS_NUMBER_1, retrievedAddress.getNumber());

    assertEquals(ADDRESS_POSTALCODE_1, retrievedAddress.getPostalCode());
    testInfo("Address postal code", ADDRESS_POSTALCODE_1, retrievedAddress.getPostalCode());

    assertEquals(ADDRESS_CITY_1, retrievedAddress.getCity());
    testInfo("Address city", ADDRESS_CITY_1, retrievedAddress.getCity());

    assertEquals(ADDRESS_COUNTRY_1, retrievedAddress.getCountry());
    testInfo("Address country", ADDRESS_COUNTRY_1, retrievedAddress.getCountry());

  }

  @Test
  public void updateZooCheck() {

    testInfo("Update Zoo Check");

    Zoo retrievedZoo = sellingManager.findZooByOwner(ZOO_OWNER_1);
    if (retrievedZoo != null) {
      sellingManager.deleteZoo(retrievedZoo);
    }

    Zoo zoo1 = new Zoo();
    zoo1.setName(ZOO_NAME_1);
    zoo1.setOwner(ZOO_OWNER_1);
    sellingManager.addZoo(zoo1);

    Zoo zoo2 = new Zoo();
    zoo2.setName(zoo1.getName());
    zoo2.setOwner(ZOO_OWNER_2);

    sellingManager.updateZoo(zoo1, zoo2);

    retrievedZoo = sellingManager.findZooByOwner(ZOO_OWNER_2);

    assertEquals(ZOO_NAME_1, retrievedZoo.getName());
    testInfo("Zoo old name", ZOO_NAME_1, retrievedZoo.getName());

		assertEquals(ZOO_OWNER_2, retrievedZoo.getOwner());
    testInfo("Zoo new owner", ZOO_OWNER_2, retrievedZoo.getOwner());

  }

  @Test
  public void updateAnimalCheck() {

    testInfo("Update Animal Check");

    Animal animal1 = new Animal();
    animal1.setSpecies(ANIMAL_SPECIES_1);
    animal1.setName(ANIMAL_NAME_1);
    animal1.setSold(false);
    Breeder breeder = new Breeder();
    breeder.setFirstName(BREEDER_FIRSTNAME_1);
    breeder.setBreedingSpecies(BREEDER_BREEDINGSPECIES_1);
    sellingManager.addBreeder(breeder);
    animal1.setBreeder(breeder);
    Long animalId = sellingManager.addNewAnimal(animal1);

    Animal animal2 = new Animal();
    animal2 = animal1;
    animal2.setName(ANIMAL_NAME_2);

    sellingManager.updateAnimal(animal1, animal2);

		Animal retrievedAnimal = sellingManager.findAnimalById(animalId);

    assertEquals(ANIMAL_SPECIES_1, retrievedAnimal.getSpecies());
    testInfo("Animal old species", ANIMAL_SPECIES_1, retrievedAnimal.getSpecies());

		assertEquals(ANIMAL_NAME_2, retrievedAnimal.getName());
    testInfo("Animal new name", ANIMAL_NAME_2, retrievedAnimal.getName());

    assertEquals(false, retrievedAnimal.getSold());
    testInfo("Animal old sold status", false, retrievedAnimal.getSold());

    assertEquals(breeder, retrievedAnimal.getBreeder());
    testInfo("Animal old breeder", breeder, retrievedAnimal.getBreeder());

  }

  @Test
  public void updateBreederCheck() {

    testInfo("Update Breeder Check");

    Breeder breeder1 = new Breeder();
    breeder1.setFirstName(BREEDER_FIRSTNAME_1);
    breeder1.setBreedingSpecies(BREEDER_BREEDINGSPECIES_1);
    sellingManager.addBreeder(breeder1);

    Breeder breeder2 = new Breeder();
    breeder2.setFirstName(breeder1.getFirstName());
    breeder2.setBreedingSpecies(BREEDER_BREEDINGSPECIES_2);

    sellingManager.updateBreeder(breeder1, breeder2);

    Breeder retrievedBreeder = sellingManager.findBreederByBreedingSpecies(BREEDER_BREEDINGSPECIES_2);

    assertEquals(BREEDER_FIRSTNAME_1, retrievedBreeder.getFirstName());
    testInfo("Breeder old name", BREEDER_FIRSTNAME_1, retrievedBreeder.getFirstName());

    assertEquals(BREEDER_BREEDINGSPECIES_2, retrievedBreeder.getBreedingSpecies());
    testInfo("Breeder new breeding species", BREEDER_BREEDINGSPECIES_2, retrievedBreeder.getBreedingSpecies());

  }

  @Test
  public void updateAddressCheck() {

    testInfo("Update Address Check");

    Address address1 = new Address();
    address1.setStreet(ADDRESS_STREET_1);
    address1.setNumber(ADDRESS_NUMBER_1);
    address1.setPostalCode(ADDRESS_POSTALCODE_1);
    address1.setCity(ADDRESS_CITY_1);
    address1.setCountry(ADDRESS_COUNTRY_1);
		// ... other properties here

    Address address2 = new Address();
    address2.setStreet(ADDRESS_STREET_2);
    address2.setNumber(ADDRESS_NUMBER_2);
    address2.setPostalCode(ADDRESS_POSTALCODE_2);
    address2.setCity(ADDRESS_CITY_1);
    address2.setCountry(ADDRESS_COUNTRY_1);
    // ... other properties here

    sellingManager.addAddress(address1);
    sellingManager.updateAddress(address1, address2);

    List<Address> addresses = sellingManager.getAllAddresses();
    Address retrievedAddress = addresses.get(0);

    assertEquals(ADDRESS_STREET_2, retrievedAddress.getStreet());
    testInfo("Address new street", ADDRESS_STREET_2, retrievedAddress.getStreet());

    assertEquals(ADDRESS_NUMBER_2, retrievedAddress.getNumber());
    testInfo("Address new number", ADDRESS_NUMBER_2, retrievedAddress.getNumber());

    assertEquals(ADDRESS_POSTALCODE_2, retrievedAddress.getPostalCode());
    testInfo("Address new postal code", ADDRESS_POSTALCODE_2, retrievedAddress.getPostalCode());

    assertEquals(ADDRESS_CITY_1, retrievedAddress.getCity());
    testInfo("Address old city", ADDRESS_CITY_1, retrievedAddress.getCity());

    assertEquals(ADDRESS_COUNTRY_1, retrievedAddress.getCountry());
    testInfo("Address old country", ADDRESS_COUNTRY_1, retrievedAddress.getCountry());

  }

	@Test
	public void sellAnimalCheck() {

    testInfo("Sell Animal Check");

		Zoo zoo = new Zoo();
		zoo.setName(ZOO_NAME_2);
		zoo.setOwner(ZOO_OWNER_2);

		sellingManager.addZoo(zoo);

		Zoo retrievedZoo = sellingManager.findZooByOwner(ZOO_OWNER_2);

		Animal animal = new Animal();
		animal.setSpecies(ANIMAL_SPECIES_2);
		animal.setName(ANIMAL_NAME_2);
    Breeder breeder = new Breeder();
    breeder.setFirstName(BREEDER_FIRSTNAME_2);
    breeder.setBreedingSpecies(BREEDER_BREEDINGSPECIES_2);
    sellingManager.addBreeder(breeder);
    animal.setBreeder(breeder);

		Long animalId = sellingManager.addNewAnimal(animal);

		sellingManager.sellAnimal(retrievedZoo.getId(), animalId);

		List<Animal> ownedAnimals = sellingManager.getOwnedAnimals(retrievedZoo);

		assertEquals(1, ownedAnimals.size());
    testInfo("Owned animals count", 1, ownedAnimals.size());

    assertEquals(ANIMAL_SPECIES_2, ownedAnimals.get(0).getSpecies());
    testInfo("Owned animal species", ANIMAL_SPECIES_2, ownedAnimals.get(0).getSpecies());

		assertEquals(ANIMAL_NAME_2, ownedAnimals.get(0).getName());
    testInfo("Owned animal name", ANIMAL_NAME_2, ownedAnimals.get(0).getName());

    assertEquals(true, ownedAnimals.get(0).getSold());
    testInfo("Owned animal sold status", true, ownedAnimals.get(0).getSold());

    assertEquals(breeder, ownedAnimals.get(0).getBreeder());
    testInfo("Owned animal breeder", breeder, ownedAnimals.get(0).getBreeder());

	}

	@Test
	public void disposeAnimalCheck() {

    testInfo("Dispose Animal Check");

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
    testInfo("Owned animals count", 0, ownedAnimals.size());

	}

  @Test
  public void deleteZooCheck() {

    testInfo("Delete Zoo Check");

    Zoo zoo = new Zoo();
    zoo.setName(ZOO_NAME_3);
    zoo.setOwner(ZOO_OWNER_3);
    sellingManager.addZoo(zoo);

    List<Zoo> retrievedZoos = sellingManager.getAllZoos();

    assertEquals(1, retrievedZoos.size());
    testInfo("Zoos before delete count", 1, retrievedZoos.size());

    sellingManager.deleteZoo(zoo);
    retrievedZoos = sellingManager.getAllZoos();

    assertEquals(0, retrievedZoos.size());
    testInfo("Zoos after delete count", 0, retrievedZoos.size());

  }

  @Test
  public void deleteBreederCheck() {

    testInfo("Delete Breeder Check");

    Breeder breeder = new Breeder();
    breeder.setFirstName(BREEDER_FIRSTNAME_3);
    breeder.setBreedingSpecies(BREEDER_BREEDINGSPECIES_3);
    sellingManager.addBreeder(breeder);

    List<Breeder> retrievedBreeders = sellingManager.getAllBreeders();

    assertEquals(1, retrievedBreeders.size());
    testInfo("Breeders before delete count", 1, retrievedBreeders.size());

    sellingManager.deleteBreeder(breeder);
    retrievedBreeders = sellingManager.getAllBreeders();

    assertEquals(0, retrievedBreeders.size());
    testInfo("Breeders after delete count", 0, retrievedBreeders.size());

  }

  @Test
  public void deleteAddressCheck() {

    testInfo("Delete Address Check");

    Address address = new Address();
    address.setStreet(ADDRESS_STREET_3);
    address.setNumber(ADDRESS_NUMBER_3);
    address.setPostalCode(ADDRESS_POSTALCODE_3);
    address.setCity(ADDRESS_CITY_3);
    address.setCountry(ADDRESS_COUNTRY_3);
    sellingManager.addAddress(address);

    List<Address> retrievedAddresses = sellingManager.getAllAddresses();

    assertEquals(1, retrievedAddresses.size());
    testInfo("Adresses before delete count", 1, retrievedAddresses.size());

    sellingManager.deleteAddress(address);
    retrievedAddresses = sellingManager.getAllAddresses();

    assertEquals(0, retrievedAddresses.size());
    testInfo("Adresses after delete count", 0, retrievedAddresses.size());

  }

}
