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
  int columns = 7;

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

  @Autowired
  @Qualifier("animal06")
  private FamilyMember animal06;

  @Autowired
  @Qualifier("animal07")
  private FamilyMember animal07;

  @Override
	public String saySpecies(String qualifier) {
    if(qualifier == "animal01") return animal01.getSpecies();
    if(qualifier == "animal02") return animal02.getSpecies();
    if(qualifier == "animal03") return animal03.getSpecies();
    if(qualifier == "animal04") return animal04.getSpecies();
    if(qualifier == "animal05") return animal05.getSpecies();
    if(qualifier == "animal06") return animal06.getSpecies();
    if(qualifier == "animal07") return animal07.getSpecies();
    return "Animal not found!";
	}

  @Override
	public String sayName(String qualifier) {
    if(qualifier == "animal01") return animal01.getName();
    if(qualifier == "animal02") return animal02.getName();
    if(qualifier == "animal03") return animal03.getName();
    if(qualifier == "animal04") return animal04.getName();
    if(qualifier == "animal05") return animal05.getName();
    if(qualifier == "animal06") return animal06.getName();
    if(qualifier == "animal07") return animal07.getName();
    return "Animal not found!";
	}

  @Override
  public String sayGender(String qualifier){
    if(qualifier == "animal01") return animal01.getGender();
    if(qualifier == "animal02") return animal02.getGender();
    if(qualifier == "animal03") return animal03.getGender();
    if(qualifier == "animal04") return animal04.getGender();
    if(qualifier == "animal05") return animal05.getGender();
    if(qualifier == "animal06") return animal06.getGender();
    if(qualifier == "animal07") return animal07.getGender();
    return "Animal not found!";
  }

  @Override
  public int sayYob(String qualifier) {
    if(qualifier == "animal01") return animal01.getYob();
    if(qualifier == "animal02") return animal02.getYob();
    if(qualifier == "animal03") return animal03.getYob();
    if(qualifier == "animal04") return animal04.getYob();
    if(qualifier == "animal05") return animal05.getYob();
    if(qualifier == "animal06") return animal06.getYob();
    if(qualifier == "animal07") return animal07.getYob();
    return 0;
  }

  @Override
  public String sayParent(String qualifier) {
    if(qualifier == "animal01") return hasParent(animal01.getParent().getName());
    if(qualifier == "animal02") return hasParent(animal02.getParent().getName());
    if(qualifier == "animal03") return hasParent(animal03.getParent().getName());
    if(qualifier == "animal04") return hasParent(animal04.getParent().getName());
    if(qualifier == "animal05") return hasParent(animal05.getParent().getName());
    if(qualifier == "animal06") return hasParent(animal06.getParent().getName());
    if(qualifier == "animal07") return hasParent(animal07.getParent().getName());
    return "Animal not found!";
  }

  @Override
  public String sayChild(String qualifier) {
    if(qualifier == "animal01") return hasChild(animal01.getChild().getName());
    if(qualifier == "animal02") return hasChild(animal02.getChild().getName());
    if(qualifier == "animal03") return hasChild(animal03.getChild().getName());
    if(qualifier == "animal04") return hasChild(animal04.getChild().getName());
    if(qualifier == "animal05") return hasChild(animal05.getChild().getName());
    if(qualifier == "animal06") return hasChild(animal06.getChild().getName());
    if(qualifier == "animal07") return hasChild(animal07.getChild().getName());
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
    List<Integer> lengths = new ArrayList<Integer>();
    lengths = prepareLengthsList();
    if (where.equals("top")) {
      System.out.println("   " + makeFrame(lengths, where));
      String headerInfo = colorizeText("   ║ ", "red", false)
        + colorizeText("No.", "blue", true)
        + colorizeText(" ║ ", "red", false)
        + colorizeText(extendWord("SPECIES", columnWidth), "blue", true)
        + colorizeText(" ║ ", "red", false)
        + colorizeText(extendWord("NAME", columnWidth), "blue", true)
        + colorizeText(" ║ ", "red", false)
        + colorizeText(extendWord("GENDER", 6), "blue", true)
        + colorizeText(" ║ ", "red", false)
        + colorizeText(extendWord("YOB", 4), "blue", true)
        + colorizeText(" ║ ", "red", false)
        + colorizeText(extendWord("PARENT", columnWidth), "blue", true)
        + colorizeText(" ║ ", "red", false)
        + colorizeText(extendWord("CHILD", columnWidth), "blue", true)
        + colorizeText(" ║", "red", false);
      System.out.println(headerInfo);
    }
    else if (where.equals("middle")) {
      System.out.println("   " + makeFrame(lengths, where));
    }
    else if (where.equals("bottom")) {
      System.out.println("   " + makeFrame(lengths, where));
    }
    else System.out.println("   Command not found!");
  }

  @Override
  public void displayAnimalInfo(String qualifier) {

    FamilyMember animal = new FamilyMember();
    Animal parent = new Animal();
    Animal child = new Animal();
    String no = extendWord(qualifier.substring(qualifier.length() - 2), 3);

    animal.setSpecies(extendWord(saySpecies(qualifier), columnWidth));
    animal.setName(extendWord(sayName(qualifier), columnWidth));
    animal.setGender(extendWord(sayGender(qualifier), 6));
    animal.setYob(sayYob(qualifier));
    parent.setName(extendWord(sayParent(qualifier), columnWidth));
    animal.setParent(parent);
    child.setName(extendWord(sayChild(qualifier), columnWidth));
    animal.setChild(child);

    String animalInfo = colorizeText("   ║ ", "red", false)
      + colorizeText(no, "white", false)
      + colorizeText(" ║ ", "red", false)
      + animal.getSpecies()
      + colorizeText(" ║ ", "red", false)
      + animal.getName()
      + colorizeText(" ║ ", "red", false)
      + animal.getGender()
      + colorizeText(" ║ ", "red", false)
      + animal.getYob()
      + colorizeText(" ║ ", "red", false)
      + animal.getParent().getName()
      + colorizeText(" ║ ", "red", false)
      + animal.getChild().getName()
      + colorizeText(" ║", "red", false);

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
  public String prepareFrame(int columnsNumber, List<Integer> lengths, String where) {
    String finishedFrame = new String();
    if(columnsNumber > 0) {
        finishedFrame = finishedFrame + colorizeText(repeatString("═", lengths.get(0) + 2), "red", false);

        if (columnsNumber > 1) {
          if (where.equals("top"))
            finishedFrame = finishedFrame + colorizeText("╦", "red", false);
          else if (where.equals("middle"))
            finishedFrame = finishedFrame + colorizeText("╬", "red", false);
          else if (where.equals("bottom"))
            finishedFrame = finishedFrame + colorizeText("╩", "red", false);
          else System.out.println("Command not fount!");
        }

        lengths.remove(0);
        finishedFrame = finishedFrame + prepareFrame(columnsNumber-1, lengths, where);
    }
    return finishedFrame;
  }

  @Override
  public String makeFrame(List<Integer> lengths, String where) {
    String finishedFrame = new String();
    if(columns > 0) {
      if (where.equals("top"))
        finishedFrame = colorizeText("╔", "red", false);
      else if (where.equals("middle"))
        finishedFrame = colorizeText("╠", "red", false);
      else if (where.equals("bottom"))
        finishedFrame = colorizeText("╚", "red", false);
      else System.out.println("Command not found!");

      finishedFrame = finishedFrame + prepareFrame(columns, lengths, where);

      if (where.equals("top"))
        finishedFrame = finishedFrame + colorizeText("╗", "red", false);
      else if (where.equals("middle"))
        finishedFrame = finishedFrame + colorizeText("╣", "red", false);
      else if (where.equals("bottom"))
        finishedFrame = finishedFrame + colorizeText("╝", "red", false);
      else System.out.println("Command not found!");
    }
    return finishedFrame;
  }

  @Override
  public String repeatString(String string, int times) {
    String repeated = new String();
    for (int i = 0; i < times; i++) repeated = repeated + string;
    return repeated;
  }

  @Override
  public List<Integer> prepareLengthsList() {
    List<Integer> lengths = new ArrayList<Integer>();
    lengths.add(3);
    lengths.add(columnWidth);
    lengths.add(columnWidth);
    lengths.add(6);
    lengths.add(4);
    lengths.add(columnWidth);
    lengths.add(columnWidth);
    return lengths;
  }

}
