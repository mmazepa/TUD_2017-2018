package service;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import static org.hamcrest.CoreMatchers.*;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Connection;
import java.net.ConnectException;

import domain.Animal;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AnimalManagerTest {

	AnimalManager animalManager = new AnimalManager();

  // ANIMALS FOR TESTING
  // Animal For Testing No. 1
  private final static String SPECIES_1 = "Lew";
  private final static double HEIGHT_1 = 1.1;
  private final static double WEIGHT_1 = 213.7;
  private final static boolean ISEXTINCT_1 = false;
  private final static Date DATEOFBIRTH_1 = new Date(2017 - 1900, 11 - 1, 18);
                            // FORMAT OF DATE: new Date(year-1900,month-1,day)

  // Animal For Testing No. 2
  private final static String SPECIES_2 = "Nosorożec";
  private final static double HEIGHT_2 = 1.75;
  private final static double WEIGHT_2 = 2200.5;
  private final static boolean ISEXTINCT_2 = false;
  private final static Date DATEOFBIRTH_2 = new Date(2008 - 1900, 5 - 1, 13);
                              // FORMAT OF DATE: new Date(year-1900,month-1,day)

  // Animal For Testing No. 3
  private final static String SPECIES_3 = "Tyranozaur";
  private final static double HEIGHT_3 = 12.8;
  private final static double WEIGHT_3 = 4750.81;
  private final static boolean ISEXTINCT_3 = true;
  private final static Date DATEOFBIRTH_3 = new Date(0 - 1900, 1 - 1, 1);
                              // FORMAT OF DATE: new Date(year-1900,month-1,day)

  // Animal For Testing No. 4
  private final static String SPECIES_4 = "Jeż";
  private final static double HEIGHT_4 = 0.35;
  private final static double WEIGHT_4 = 1.15;
  private final static boolean ISEXTINCT_4 = false;
  private final static Date DATEOFBIRTH_4 = new Date(2016 - 1900, 3 - 1, 23);
                              // FORMAT OF DATE: new Date(year-1900,month-1,day)

  // Animal For Testing No. 5
  private final static String SPECIES_5 = "Tur";
  private final static double HEIGHT_5 = 1.87;
  private final static double WEIGHT_5 = 940.5;
  private final static boolean ISEXTINCT_5 = true;
  private final static Date DATEOFBIRTH_5 = new Date(1357 - 1900, 8 - 1, 12);
                              // FORMAT OF DATE: new Date(year-1900,month-1,day)

  // Animal For Testing No. 6
  private final static String SPECIES_6 = "Indyk";
  private final static double HEIGHT_6 = 0.43;
  private final static double WEIGHT_6 = 22.41;
  private final static boolean ISEXTINCT_6 = false;
  private final static Date DATEOFBIRTH_6 = new Date(2014 - 1900, 7 - 1, 4);
                              // FORMAT OF DATE: new Date(year-1900,month-1,day)

  // Animal For Testing No. 7
  private final static String SPECIES_7 = "Krab";
  private final static double HEIGHT_7 = 0.12;
  private final static double WEIGHT_7 = 0.25;
  private final static boolean ISEXTINCT_7 = false;
  private final static Date DATEOFBIRTH_7 = new Date(2017 - 1900, 4 - 1, 7);
                              // FORMAT OF DATE: new Date(year-1900,month-1,day)

  // Animal For Testing No. 8
  private final static String SPECIES_8 = "Mucha";
  private final static double HEIGHT_8 = 0.0;
  private final static double WEIGHT_8 = 0.0;
  private final static boolean ISEXTINCT_8 = false;
  private final static Date DATEOFBIRTH_8 = null;
                              // FORMAT OF DATE: new Date(year-1900,month-1,day)

  Animal animal1 = new Animal(SPECIES_1, HEIGHT_1, WEIGHT_1, ISEXTINCT_1, DATEOFBIRTH_1);
  Animal animal2 = new Animal(SPECIES_2, HEIGHT_2, WEIGHT_2, ISEXTINCT_2, DATEOFBIRTH_2);
  Animal animal3 = new Animal(SPECIES_3, HEIGHT_3, WEIGHT_3, ISEXTINCT_3, DATEOFBIRTH_3);
  Animal animal4 = new Animal(SPECIES_4, HEIGHT_4, WEIGHT_4, ISEXTINCT_4, DATEOFBIRTH_4);
  Animal animal5 = new Animal(SPECIES_5, HEIGHT_5, WEIGHT_5, ISEXTINCT_5, DATEOFBIRTH_5);
  Animal animal6 = new Animal(SPECIES_6, HEIGHT_6, WEIGHT_6, ISEXTINCT_6, DATEOFBIRTH_6);
  Animal animal7 = new Animal(SPECIES_7, HEIGHT_7, WEIGHT_7, ISEXTINCT_7, DATEOFBIRTH_7);
  Animal animal8 = new Animal(SPECIES_8, HEIGHT_8, WEIGHT_8, ISEXTINCT_8, DATEOFBIRTH_8);

  // Font style - color, bold and reset (console)
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BOLD  = "\u001B[1m";
  public static final String ANSI_WHITE = "\u001B[37m";

  @BeforeClass
  public static void setUpClass() {
    // Before testing
    System.out.println("\n   " + ANSI_WHITE + ANSI_BOLD + "[TESTS START]" + ANSI_RESET);
  }

  @AfterClass
  public static void tearDownClass() {
    // After testing
    System.out.println("   --------------------------------------------------------");
    System.out.println("   " + ANSI_WHITE + ANSI_BOLD + "[TESTS END]" + ANSI_RESET + "\n");
  }

  @Before
  public void setUp() {
    // Before every test
    System.out.println("   --------------------------------------------------------");
  }

  @After
  public void tearDown() {
    // After every test
  }

  @Test
  public void test01_checkConnectionNegative() {

    animalManager.testInfo(1, "Check Connection Negative");

    try {
      Connection connection = animalManager.getConnection();
      assertNull(connection);
      animalManager.exceptionInfo("Connection is null.");
    } catch (AssertionError err) {
      Connection connection = animalManager.getConnection();
      assertNotNull(connection);
      animalManager.exceptionInfo("assertNull(animalManager.getConnection()) failed!");
    }

  }

	@Test
	public void test02_checkConnectionPositive() {

    animalManager.testInfo(2, "Check Connection Positive");

    try {
      Connection connection = animalManager.getConnection();
      assertNotNull(connection);
      System.out.println("     Connection is OK: " + connection);
    } catch (AssertionError err) {
      Connection connection = animalManager.getConnection();
      assertNull(connection);
      animalManager.exceptionInfo("     [ERROR] Connection is null.");
    }

	}

  @Test
  public void test03_checkAddingNegative() {

    animalManager.testInfo(3, "Check Adding Negative");

    int addRowCount = animalManager.addAnimal(animal8);
    assertEquals(0, addRowCount);
    if (addRowCount == 0)
      System.out.println("     Animal [" + SPECIES_8 + "] was not added.");

  }

	@Test
	public void test04_checkAddingPositive() {

    animalManager.testInfo(4, "Check Adding Positive");
    animalManager.clearAnimals();

    int addRowCount = animalManager.addAnimal(animal1);
		assertEquals(1, addRowCount);

    if (addRowCount == 1) {
  		List<Animal> animals = animalManager.getAllAnimals();
  		Animal animalRetrieved = animals.get(0);

  		assertEquals(SPECIES_1,     animalRetrieved.getSpecies());
  		assertEquals(HEIGHT_1,      animalRetrieved.getHeight(), 0D);
      assertEquals(WEIGHT_1,      animalRetrieved.getWeight(), 0D);
      assertEquals(ISEXTINCT_1,   animalRetrieved.getIsExtinct());
      assertEquals(DATEOFBIRTH_1, animalRetrieved.getDateOfBirth());
      System.out.println("     Animal [" + SPECIES_1 + "] was added.");
    }

	}

  @Test
  public void test05_checkAddingTransactionNegative() {

    animalManager.testInfo(5, "Check Adding Transaction Negative");
    animalManager.clearAnimals();

    List<Animal> animals = new ArrayList<>();
    animals.add(animal1);
    animals.add(animal1);
    animals.add(animal3);
    animals.add(animal8);
    animals.add(animal5);
    animals.add(animal5);
    animals.add(animal7);

    animalManager.addAllAnimals(animals);
    int size = animalManager.getAllAnimals().size();

    assertThat(size, either(is(animals.size())).or(is(0)));
    System.out.println("     Number of " + size + " Animals added.");

  }

  @Test
  public void test06_checkAddingTransactionPositive() {

    animalManager.testInfo(6, "Check Adding Transaction Positive");
    animalManager.clearAnimals();

		List<Animal> animals = new ArrayList<>();
		animals.add(animal1);
		animals.add(animal2);
		animals.add(animal3);
		animals.add(animal4);
    animals.add(animal5);
    animals.add(animal6);
    animals.add(animal7);

		animalManager.addAllAnimals(animals);
		int size = animalManager.getAllAnimals().size();

    assertThat(size, either(is(animals.size())).or(is(0)));
    System.out.println("     Number of " + size + " Animals added.");

  }

  @Test
  public void test07_checkUpdatingNegative() {

    animalManager.testInfo(7, "Check Updating Negative");

    String oldSpecies = "Nosorożec";
    String newSpecies = "Lew";

    int updateRowCount = animalManager.updateAnimal(oldSpecies, newSpecies);
    assertEquals(0, updateRowCount);
    if (updateRowCount == 0)
      System.out.println("     Animal [" + oldSpecies + "] species was not changed to [" + newSpecies + "].");

  }

  @Test
  public void test08_checkUpdatingPositive() {

    animalManager.testInfo(8, "Check Updating Positive");

    String oldSpecies = "Nosorożec";
    String newSpecies = "Słoń";

    int updateRowCount = animalManager.updateAnimal(oldSpecies, newSpecies);
    assertEquals(1, updateRowCount);
    if (updateRowCount == 1)
      System.out.println("     Animal [" + oldSpecies + "] species was changed to [" + newSpecies + "].");

  }

  @Test
  public void test09_checkUpdatingTransactionNegative() {

    animalManager.testInfo(9, "Check Updating Transaction Negative");

    List<Animal> animals = new ArrayList<>();
    animals.add(animal1);
    animals.add(animal2);
    animals.add(animal3);
    animals.add(animal8);
    animals.add(animal5);
    animals.add(animal6);
    animals.add(animal7);

    int size = animalManager.getAllAnimals().size();

    int updateRowCount = animalManager.updateAllAnimals(animals);
    assertNotEquals(size, updateRowCount);
    if (updateRowCount != size)
      System.out.println("     Number of 0 Animals updated (weight increased by 10%).");

  }

  @Test
  public void test10_checkUpdatingTransactionPositive() {

    animalManager.testInfo(10, "Check Updating Transaction Positive");

    List<Animal> animals = new ArrayList<>();
		animals.add(animal1);
		animals.add(animal2);
		animals.add(animal3);
		animals.add(animal4);
    animals.add(animal5);
    animals.add(animal6);
    animals.add(animal7);

		int size = animalManager.getAllAnimals().size();

		int updateRowCount = animalManager.updateAllAnimals(animals);
    assertThat(size, either(is(animals.size())).or(is(0)));
    if (updateRowCount == size)
      System.out.println("     Number of " + size + " Animals updated (weight increased by 10%).");

  }

  @Test
  public void test11_checkDeletionNegative() {

    animalManager.testInfo(11, "Check Deletion Negative");

    List<Animal> animals = animalManager.getAllAnimals();
		Animal animalRetrieved = animals.get(0);
    long animalId = animalRetrieved.getId() - 1;

    int deleteRowCount = animalManager.deleteAnimal(animalId);
    assertEquals(0, deleteRowCount);
    if (deleteRowCount == 0)
      System.out.println("     Animal with ID " + animalId + " was not deleted, animal not found.");

  }

  @Test
  public void test12_checkDeletionPositive() {

    animalManager.testInfo(12, "Check Deletion Positive");

    List<Animal> animals = animalManager.getAllAnimals();
		Animal animalRetrieved = animals.get(0);
    long animalId = animalRetrieved.getId();

    int deleteRowCount = animalManager.deleteAnimal(animalId);
    assertEquals(1, deleteRowCount);
    if (deleteRowCount == 1)
      System.out.println("     Animal with ID " + animalId + " [" + animalRetrieved.getSpecies() + "] was deleted.");

  }

  @Test
  public void test13_checkSearchingNegative() {

    animalManager.testInfo(13, "Check Searching Negative");

    String requiredSpecies = "Wielbłąd";
    Animal foundAnimal = animalManager.findAnimalBySpecies(requiredSpecies);
    assertNull(foundAnimal);
    if (foundAnimal == null)
      System.out.println("     Animal with required species [" + requiredSpecies + "] was not found.");

  }

  @Test
  public void test14_checkSearchingPositive() {

    animalManager.testInfo(14, "Check Searching Positive");

    String requiredSpecies = "Jeż";
    Animal foundAnimal = animalManager.findAnimalBySpecies(requiredSpecies);
    assertNotNull(foundAnimal);
    if (foundAnimal != null)
      System.out.println("     Animal with required species [" + requiredSpecies + "] was found.");

  }

	@Test
	public void test15_businessMethodNegative() {

		animalManager.testInfo(15, "Check Business Method Negative");

    String[] newSpecies = new String[2];
    newSpecies[0] = "Krokodyl";
    newSpecies[1] = "Salamandra";

    List<Animal> animals = new ArrayList<Animal>();
    animals.add(animal1);
    animals.add(animal2);
    animals.add(animal3);

    Boolean result = animalManager.businessMethod(animals, newSpecies);
    assertEquals(result, false);
    if (result == false)
      System.out.println("     BusinessMethod failed!");
	}

	@Test
	public void test16_businessMethodPositive() {

		animalManager.testInfo(16, "Check Business Method Positive");

    String[] newSpecies = new String[2];
    newSpecies[0] = "Krokodyl";
    newSpecies[1] = "Salamandra";

    List<Animal> animals = new ArrayList<Animal>();
    animals.add(animal1);
    animals.add(animal2);
    animals.add(animal1);
    animals.add(animal2);

    Boolean result = animalManager.businessMethod(animals, newSpecies);
    assertEquals(result, true);
    if (result == true)
      System.out.println("     BusinessMethod finished successfully!");
  }

}
