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
	public void animalSayHelloTest() {
    System.out.println("");
    System.out.println("   " + fmm.colorizeText("[ANIMAL_SAY_HELLO_TEST]", "white", true));
    System.out.println("   " + fmm.colorizeText("+--------------------------------------------", "red", false));

    // ANIMAL_01
    System.out.println("   " + fmm.colorizeText("|", "red", false)
      + " " + fmm.colorizeText("[ANIMAL_01]:", "blue", true));
    assertEquals("I'm Henryk!", fmm.sayName("animal01"));
    assertEquals("I'm Male!", fmm.sayGender("animal01"));
    assertEquals("I was born in 1894!", fmm.sayYob("animal01"));
    assertEquals("I don't have a parent!", fmm.sayParent("animal01"));
    assertEquals("My child name is Franciszek!", fmm.sayChild("animal01"));
    fmm.displayAnimalInfo("animal01");

    // ANIMAL_02
    System.out.println("   " + fmm.colorizeText("|", "red", false)
      + " " + fmm.colorizeText("[ANIMAL_02]:", "blue", true));
    assertEquals("I'm Franciszek!", fmm.sayName("animal02"));
    assertEquals("I'm Male!", fmm.sayGender("animal02"));
    assertEquals("I was born in 1924!", fmm.sayYob("animal02"));
    assertEquals("My parent name is Henryk!", fmm.sayParent("animal02"));
    assertEquals("My child name is Michał!", fmm.sayChild("animal02"));
    fmm.displayAnimalInfo("animal02");

    // ANIMAL_03
    System.out.println("   " + fmm.colorizeText("|", "red", false)
      + " " + fmm.colorizeText("[ANIMAL_03]:", "blue", true));
    assertEquals("I'm Michał!", fmm.sayName("animal03"));
    assertEquals("I'm Male!", fmm.sayGender("animal03"));
    assertEquals("I was born in 1954!", fmm.sayYob("animal03"));
    assertEquals("My parent name is Franciszek!", fmm.sayParent("animal03"));
    assertEquals("My child name is Karolina!", fmm.sayChild("animal03"));
    fmm.displayAnimalInfo("animal03");

    // ANIMAL_04
    System.out.println("   " + fmm.colorizeText("|", "red", false)
      + " " + fmm.colorizeText("[ANIMAL_04]:", "blue", true));
    assertEquals("I'm Karolina!", fmm.sayName("animal04"));
    assertEquals("I'm Female!", fmm.sayGender("animal04"));
    assertEquals("I was born in 1984!", fmm.sayYob("animal04"));
    assertEquals("My parent name is Michał!", fmm.sayParent("animal04"));
    assertEquals("My child name is Zofia!", fmm.sayChild("animal04"));
    fmm.displayAnimalInfo("animal04");

    // ANIMAL_05
    System.out.println("   " + fmm.colorizeText("|", "red", false)
      + " " + fmm.colorizeText("[ANIMAL_05]:", "blue", true));
    assertEquals("I'm Zofia!", fmm.sayName("animal05"));
    assertEquals("I'm Female!", fmm.sayGender("animal05"));
    assertEquals("I was born in 2014!", fmm.sayYob("animal05"));
    assertEquals("My parent name is Karolina!", fmm.sayParent("animal05"));
    assertEquals("I don't have a child!", fmm.sayChild("animal05"));
    fmm.displayAnimalInfo("animal05");

    System.out.println("");
	}

}
