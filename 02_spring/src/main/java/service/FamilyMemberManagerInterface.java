package service;

import java.util.List;

interface FamilyMemberManagerInterface {

  // Method for Testing
  public String saySpecies(String qualifier);
  public String sayName(String qualifier);
  public String sayGender(String qualifier);
  public int sayYob(String qualifier);
  public String sayParent(String qualifier);
  public String sayChild(String qualifier);

  // Helpfull methods
  public String hasParent(String parentName);
  public String hasChild(String childName);

  // Display stuff
  public String colorizeText(String textToColorize, String colorName, Boolean isBold);
  public void displayFrame(String where);
  public void displayAnimalInfo(String qualifier);
  public String extendWord(String word, int desiredLength);
  public String prepareFrame(int columnsNumber, List<Integer> lengths, String where);
  public String makeFrame(List<Integer> lengths, String where);
  public String repeatString(String string, int times);
  public List<Integer> prepareLengthsList();

}
