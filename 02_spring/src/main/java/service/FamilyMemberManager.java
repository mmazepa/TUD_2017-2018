package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import domain.Animal;
import domain.FamilyMember;

@Component
public class FamilyMemberManager implements FamilyMemberManagerInterface {

  // Font style - color, bold and reset (console)
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BLUE  = "\u001B[34m";
  public static final String ANSI_WHITE = "\u001B[37m";
  public static final String ANSI_RED   = "\u001B[31m";
  public static final String ANSI_BOLD  = "\u001B[1m";

	@Autowired
  @Qualifier("animal01")
	private FamilyMember animal01;

  @Autowired
  @Qualifier("animal02")
  private FamilyMember animal02;

  @Autowired
  @Qualifier("animal03")
  private FamilyMember animal03;

  @Autowired
  @Qualifier("animal04")
  private FamilyMember animal04;

  @Autowired
  @Qualifier("animal05")
  private FamilyMember animal05;

  @Override
	public String sayName(String qualifier) {
    if(qualifier == "animal01") return "I'm " + animal01.getName() + "!";
    if(qualifier == "animal02") return "I'm " + animal02.getName() + "!";
    if(qualifier == "animal03") return "I'm " + animal03.getName() + "!";
    if(qualifier == "animal04") return "I'm " + animal04.getName() + "!";
    if(qualifier == "animal05") return "I'm " + animal05.getName() + "!";
    return "Animal not found!";
	}

  @Override
  public String sayGender(String qualifier){
    if(qualifier == "animal01") return "I'm " + animal01.getGender() + "!";
    if(qualifier == "animal02") return "I'm " + animal02.getGender() + "!";
    if(qualifier == "animal03") return "I'm " + animal03.getGender() + "!";
    if(qualifier == "animal04") return "I'm " + animal04.getGender() + "!";
    if(qualifier == "animal05") return "I'm " + animal05.getGender() + "!";
    return "Animal not found!";
  }

  @Override
  public String sayYob(String qualifier) {
    if(qualifier == "animal01") return "I was born in " + animal01.getYob() + "!";
    if(qualifier == "animal02") return "I was born in " + animal02.getYob() + "!";
    if(qualifier == "animal03") return "I was born in " + animal03.getYob() + "!";
    if(qualifier == "animal04") return "I was born in " + animal04.getYob() + "!";
    if(qualifier == "animal05") return "I was born in " + animal05.getYob() + "!";
    return "Animal not found!";
  }

  @Override
  public String sayParent(String qualifier) {
    if(qualifier == "animal01") return hasParent(animal01.getParent().getName());
    if(qualifier == "animal02") return hasParent(animal02.getParent().getName());
    if(qualifier == "animal03") return hasParent(animal03.getParent().getName());
    if(qualifier == "animal04") return hasParent(animal04.getParent().getName());
    if(qualifier == "animal05") return hasParent(animal05.getParent().getName());
    return "Animal not found!";
  }

  @Override
  public String sayChild(String qualifier) {
    if(qualifier == "animal01") return hasChild(animal01.getChild().getName());
    if(qualifier == "animal02") return hasChild(animal02.getChild().getName());
    if(qualifier == "animal03") return hasChild(animal03.getChild().getName());
    if(qualifier == "animal04") return hasChild(animal04.getChild().getName());
    if(qualifier == "animal05") return hasChild(animal05.getChild().getName());
    return "Animal not found!";
  }

  @Override
  public String hasParent(String parentName) {
    if (parentName != null && parentName != "") {
      return "My parent name is " + parentName + "!";
    }
    return "I don't have a parent!";
  }

  @Override
  public String hasChild(String childName) {
    if (childName != null && childName != "") {
      return "My child name is " + childName + "!";
    }
    return "I don't have a child!";
  }

  @Override
  public String colorizeText(String textToColorize, String colorName, Boolean isBold) {
    if(isBold) textToColorize = ANSI_BOLD + textToColorize + ANSI_RESET;
    if(colorName == "blue") textToColorize = ANSI_BLUE + textToColorize + ANSI_RESET;
    if(colorName == "white") textToColorize = ANSI_WHITE + textToColorize + ANSI_RESET;
    if(colorName == "red") textToColorize = ANSI_RED + textToColorize + ANSI_RESET;
    return textToColorize;
  }

  @Override
  public void displayAnimalInfo(String qualifier) {
    System.out.println("   " + colorizeText("+--------------------------------------------", "red", false));
    // SAY NAME
    System.out.println("      " + colorizeText("|", "red", false) + " "
    + colorizeText("[NAME]:", "white", false) + "   " + sayName(qualifier));
    // SAY GENDER
    System.out.println("      " + colorizeText("|", "red", false) + " "
    + colorizeText("[GENDER]:", "white", false) + " " + sayGender(qualifier));
    // SAY YOB
    System.out.println("      " + colorizeText("|", "red", false) + " "
    + colorizeText("[YOB]:", "white", false) + "    " + sayYob(qualifier));
    // SAY PARENT
    System.out.println("      " + colorizeText("|", "red", false) + " "
    + colorizeText("[PARENT]:", "white", false) + " " + sayParent(qualifier));
    // SAY CHILD
    System.out.println("      " + colorizeText("|", "red", false) + " "
    + colorizeText("[CHILD]:", "white", false) + "  " + sayChild(qualifier));
    System.out.println("   " + colorizeText("+--------------------------------------------", "red", false));
  }

}
