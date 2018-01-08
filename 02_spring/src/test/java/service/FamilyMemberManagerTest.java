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
	public void personSayHelloTest() {
    System.out.println("");
    System.out.println("   " + fmm.colorizeText("[PERSON_SAY_HELLO_TEST]", "white", true));
    System.out.println("   " + fmm.colorizeText("+--------------------------------------------", "red", false));

    // PERSON_01
    System.out.println("   " + fmm.colorizeText("|", "red", false)
      + " " + fmm.colorizeText("[PERSON_01]:", "blue", true));
    assertEquals("I'm Henryk!", fmm.sayName("person01"));
    assertEquals("I'm Male!", fmm.sayGender("person01"));
    assertEquals("I was born in 1894!", fmm.sayYob("person01"));
    assertEquals("I don't have a parent!", fmm.sayParent("person01"));
    assertEquals("My child name is Franciszek!", fmm.sayChild("person01"));
    fmm.displayPersonInfo("person01");

    // PERSON_02
    System.out.println("   " + fmm.colorizeText("|", "red", false)
      + " " + fmm.colorizeText("[PERSON_02]:", "blue", true));
    assertEquals("I'm Franciszek!", fmm.sayName("person02"));
    assertEquals("I'm Male!", fmm.sayGender("person02"));
    assertEquals("I was born in 1924!", fmm.sayYob("person02"));
    assertEquals("My parent name is Henryk!", fmm.sayParent("person02"));
    assertEquals("My child name is Michał!", fmm.sayChild("person02"));
    fmm.displayPersonInfo("person02");

    // PERSON_03
    System.out.println("   " + fmm.colorizeText("|", "red", false)
      + " " + fmm.colorizeText("[PERSON_03]:", "blue", true));
    assertEquals("I'm Michał!", fmm.sayName("person03"));
    assertEquals("I'm Male!", fmm.sayGender("person03"));
    assertEquals("I was born in 1954!", fmm.sayYob("person03"));
    assertEquals("My parent name is Franciszek!", fmm.sayParent("person03"));
    assertEquals("My child name is Karolina!", fmm.sayChild("person03"));
    fmm.displayPersonInfo("person03");

    // PERSON_04
    System.out.println("   " + fmm.colorizeText("|", "red", false)
      + " " + fmm.colorizeText("[PERSON_04]:", "blue", true));
    assertEquals("I'm Karolina!", fmm.sayName("person04"));
    assertEquals("I'm Female!", fmm.sayGender("person04"));
    assertEquals("I was born in 1984!", fmm.sayYob("person04"));
    assertEquals("My parent name is Michał!", fmm.sayParent("person04"));
    assertEquals("My child name is Zofia!", fmm.sayChild("person04"));
    fmm.displayPersonInfo("person04");

    // PERSON_05
    System.out.println("   " + fmm.colorizeText("|", "red", false)
      + " " + fmm.colorizeText("[PERSON_05]:", "blue", true));
    assertEquals("I'm Zofia!", fmm.sayName("person05"));
    assertEquals("I'm Female!", fmm.sayGender("person05"));
    assertEquals("I was born in 2014!", fmm.sayYob("person05"));
    assertEquals("My parent name is Karolina!", fmm.sayParent("person05"));
    assertEquals("I don't have a child!", fmm.sayChild("person05"));
    fmm.displayPersonInfo("person05");

    System.out.println("");
	}

}
