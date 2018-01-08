package service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
public class FamilyMemberManagerTest {

	@Autowired
	private FamilyMemberManager fmm;

	@Test
	public void displayAnimals() {
    System.out.println("");
    System.out.println("   " + fmm.colorizeText("[DISPLAY ANIMALS TEST]:", "white", true));
    fmm.displayFrame("top");

    // ANIMAL_01
    assertEquals("Lew", fmm.saySpecies("animal01"));
    assertEquals("Henryk", fmm.sayName("animal01"));
    assertEquals("Male", fmm.sayGender("animal01"));
    assertEquals(1834, fmm.sayYob("animal01"));
    assertEquals("-", fmm.sayParent("animal01"));
    assertEquals("Franciszek", fmm.sayChild("animal01"));
    fmm.displayAnimalInfo("animal01");

    // ANIMAL_02
    assertEquals("Lew", fmm.saySpecies("animal02"));
    assertEquals("Franciszek", fmm.sayName("animal02"));
    assertEquals("Male", fmm.sayGender("animal02"));
    assertEquals(1864, fmm.sayYob("animal02"));
    assertEquals("Henryk", fmm.sayParent("animal02"));
    assertEquals("Michał", fmm.sayChild("animal02"));
    fmm.displayAnimalInfo("animal02");

    // ANIMAL_03
    assertEquals("Lew", fmm.saySpecies("animal03"));
    assertEquals("Michał", fmm.sayName("animal03"));
    assertEquals("Male", fmm.sayGender("animal03"));
    assertEquals(1894, fmm.sayYob("animal03"));
    assertEquals("Franciszek", fmm.sayParent("animal03"));
    assertEquals("Karolina", fmm.sayChild("animal03"));
    fmm.displayAnimalInfo("animal03");

    // ANIMAL_04
    assertEquals("Lew", fmm.saySpecies("animal04"));
    assertEquals("Karolina", fmm.sayName("animal04"));
    assertEquals("Female", fmm.sayGender("animal04"));
    assertEquals(1924, fmm.sayYob("animal04"));
    assertEquals("Michał", fmm.sayParent("animal04"));
    assertEquals("Zofia", fmm.sayChild("animal04"));
    fmm.displayAnimalInfo("animal04");

    // ANIMAL_05
    assertEquals("Lew", fmm.saySpecies("animal05"));
    assertEquals("Zofia", fmm.sayName("animal05"));
    assertEquals("Female", fmm.sayGender("animal05"));
    assertEquals(1954, fmm.sayYob("animal05"));
    assertEquals("Karolina", fmm.sayParent("animal05"));
    assertEquals("Kazimierz", fmm.sayChild("animal05"));
    fmm.displayAnimalInfo("animal05");

    // ANIMAL_06
    assertEquals("Lew", fmm.saySpecies("animal06"));
    assertEquals("Kazimierz", fmm.sayName("animal06"));
    assertEquals("Male", fmm.sayGender("animal06"));
    assertEquals(1984, fmm.sayYob("animal06"));
    assertEquals("Zofia", fmm.sayParent("animal06"));
    assertEquals("Genowefa", fmm.sayChild("animal06"));
    fmm.displayAnimalInfo("animal06");

    // ANIMAL_07
    assertEquals("Lew", fmm.saySpecies("animal07"));
    assertEquals("Genowefa", fmm.sayName("animal07"));
    assertEquals("Female", fmm.sayGender("animal07"));
    assertEquals(2014, fmm.sayYob("animal07"));
    assertEquals("Kazimierz", fmm.sayParent("animal07"));
    assertEquals("-", fmm.sayChild("animal07"));
    fmm.displayAnimalInfo("animal07");

    fmm.displayFrame("bottom");
    System.out.println("");
	}

}
