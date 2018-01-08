package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import domain.Person;
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
  @Qualifier("person01")
	private FamilyMember person01;

  @Autowired
  @Qualifier("person02")
  private FamilyMember person02;

  @Autowired
  @Qualifier("person03")
  private FamilyMember person03;

  @Autowired
  @Qualifier("person04")
  private FamilyMember person04;

  @Autowired
  @Qualifier("person05")
  private FamilyMember person05;

  @Override
	public String sayName(String qualifier) {
    if(qualifier == "person01") return "I'm " + person01.getFirstName() + "!";
    if(qualifier == "person02") return "I'm " + person02.getFirstName() + "!";
    if(qualifier == "person03") return "I'm " + person03.getFirstName() + "!";
    if(qualifier == "person04") return "I'm " + person04.getFirstName() + "!";
    if(qualifier == "person05") return "I'm " + person05.getFirstName() + "!";
    return "Person not found!";
	}

  @Override
  public String sayGender(String qualifier){
    if(qualifier == "person01") return "I'm " + person01.getGender() + "!";
    if(qualifier == "person02") return "I'm " + person02.getGender() + "!";
    if(qualifier == "person03") return "I'm " + person03.getGender() + "!";
    if(qualifier == "person04") return "I'm " + person04.getGender() + "!";
    if(qualifier == "person05") return "I'm " + person05.getGender() + "!";
    return "Person not found!";
  }

  @Override
  public String sayYob(String qualifier) {
    if(qualifier == "person01") return "I was born in " + person01.getYob() + "!";
    if(qualifier == "person02") return "I was born in " + person02.getYob() + "!";
    if(qualifier == "person03") return "I was born in " + person03.getYob() + "!";
    if(qualifier == "person04") return "I was born in " + person04.getYob() + "!";
    if(qualifier == "person05") return "I was born in " + person05.getYob() + "!";
    return "Person not found!";
  }

  @Override
  public String sayParent(String qualifier) {
    if(qualifier == "person01") return hasParent(person01.getParent().getFirstName());
    if(qualifier == "person02") return hasParent(person02.getParent().getFirstName());
    if(qualifier == "person03") return hasParent(person03.getParent().getFirstName());
    if(qualifier == "person04") return hasParent(person04.getParent().getFirstName());
    if(qualifier == "person05") return hasParent(person05.getParent().getFirstName());
    return "Person not found!";
  }

  @Override
  public String sayChild(String qualifier) {
    if(qualifier == "person01") return hasChild(person01.getChild().getFirstName());
    if(qualifier == "person02") return hasChild(person02.getChild().getFirstName());
    if(qualifier == "person03") return hasChild(person03.getChild().getFirstName());
    if(qualifier == "person04") return hasChild(person04.getChild().getFirstName());
    if(qualifier == "person05") return hasChild(person05.getChild().getFirstName());
    return "Person not found!";
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
  public void displayPersonInfo(String qualifier) {
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
