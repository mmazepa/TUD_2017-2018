package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;

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

  // Display stuff
  int columnWidth = 10;
  int columns = 6;

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
    if(qualifier == "animal01") return animal01.getName();
    if(qualifier == "animal02") return animal02.getName();
    if(qualifier == "animal03") return animal03.getName();
    if(qualifier == "animal04") return animal04.getName();
    if(qualifier == "animal05") return animal05.getName();
    return "Animal not found!";
	}

  @Override
  public String sayGender(String qualifier){
    if(qualifier == "animal01") return animal01.getGender();
    if(qualifier == "animal02") return animal02.getGender();
    if(qualifier == "animal03") return animal03.getGender();
    if(qualifier == "animal04") return animal04.getGender();
    if(qualifier == "animal05") return animal05.getGender();
    return "Animal not found!";
  }

  @Override
  public int sayYob(String qualifier) {
    if(qualifier == "animal01") return animal01.getYob();
    if(qualifier == "animal02") return animal02.getYob();
    if(qualifier == "animal03") return animal03.getYob();
    if(qualifier == "animal04") return animal04.getYob();
    if(qualifier == "animal05") return animal05.getYob();
    return 0;
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
      return parentName;
    }
    return "-";
  }

  @Override
  public String hasChild(String childName) {
    if (childName != null && childName != "") {
      return childName;
    }
    return "-";
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
  public void displayFrame(String where) {
    if (where.equals("open")) {
      displayFrame("close");
      String headerInfo = colorizeText("   | ", "red", false)
        + "No."
        + colorizeText(" | ", "red", false)
        + extendWord("NAME", columnWidth)
        + colorizeText(" | ", "red", false)
        + extendWord("GENDER", columnWidth)
        + colorizeText(" | ", "red", false)
        + extendWord("YOB", 4)
        + colorizeText(" | ", "red", false)
        + extendWord("PARENT", columnWidth)
        + colorizeText(" | ", "red", false)
        + extendWord("CHILD", columnWidth)
        + colorizeText(" |", "red", false);
      System.out.println(headerInfo);
    }
    else if (where.equals("middle")) {
      List<Integer> lengths = new ArrayList<Integer>();
      lengths.add(3);
      lengths.add(columnWidth);
      lengths.add(columnWidth);
      lengths.add(4);
      lengths.add(columnWidth);
      lengths.add(columnWidth);
      System.out.println("   " + makeFrame(columns, lengths));
    }
    else if (where.equals("close")) {
      List<Integer> lengths = new ArrayList<Integer>();
      int sum = 3 + columnWidth + columnWidth + 4 + columnWidth + columnWidth + columns*3 - 3;
      lengths.add(sum);
      System.out.println("   " + makeFrame(1, lengths));
    }
    else System.out.println("   Command not found!");
  }

  @Override
  public void displayAnimalInfo(String qualifier) {

    FamilyMember animal = new FamilyMember();
    Animal parent = new Animal();
    Animal child = new Animal();
    String no = extendWord(qualifier.substring(qualifier.length() - 2), 3);

    animal.setName(extendWord(sayName(qualifier), columnWidth));
    animal.setGender(extendWord(sayGender(qualifier), columnWidth));
    animal.setYob(sayYob(qualifier));
    parent.setName(extendWord(sayParent(qualifier), columnWidth));
    animal.setParent(parent);
    child.setName(extendWord(sayChild(qualifier), columnWidth));
    animal.setChild(child);

    String animalInfo = colorizeText("   | ", "red", false)
      + no
      + colorizeText(" | ", "red", false)
      + animal.getName()
      + colorizeText(" | ", "red", false)
      + animal.getGender()
      + colorizeText(" | ", "red", false)
      + animal.getYob()
      + colorizeText(" | ", "red", false)
      + animal.getParent().getName()
      + colorizeText(" | ", "red", false)
      + animal.getChild().getName()
      + colorizeText(" |", "red", false);

    displayFrame("middle");
    System.out.println(animalInfo);
  }

  @Override
  public String extendWord(String word, int desiredLength) {
    if (word.length() < desiredLength) {
      while (word.length() < desiredLength) {
        word = word + " ";
      }
    }
    return word;
  }

  @Override
  public String prepareFrame(int columnsNumber, List<Integer> lengths) {
    String finishedFrame = new String();
    if(columnsNumber > 0) {
        finishedFrame = finishedFrame + colorizeText("+", "red", false);
        finishedFrame = finishedFrame + colorizeText(repeatString("-", lengths.get(0) + 2), "red", false);
        lengths.remove(0);
        finishedFrame = finishedFrame + prepareFrame(columnsNumber-1, lengths);
    }
    return finishedFrame;
  }

  @Override
  public String makeFrame(int columnsNumber, List<Integer> lengths) {
    String finishedFrame = prepareFrame(columnsNumber, lengths);
    finishedFrame = finishedFrame + colorizeText("+", "red", false);
    return finishedFrame;
  }

  @Override
  public String repeatString(String string, int times) {
    String repeated = new String();
    for (int i = 0; i < times; i++) repeated = repeated + string;
    return repeated;
  }

}
