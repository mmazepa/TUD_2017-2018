package service;

interface FamilyMemberManagerInterface {

  // Method for Testing
  public String sayName(String qualifier);
  public String sayGender(String qualifier);
  public String sayYob(String qualifier);
  public String sayParent(String qualifier);
  public String sayChild(String qualifier);

  // Helpfull methods
  public String hasParent(String parentName);
  public String hasChild(String childName);
  public String colorizeText(String textToColorize, String colorName, Boolean isBold);
  public void displayAnimalInfo(String qualifier);

}
