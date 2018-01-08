package domain;

public class FamilyMember extends Person {

  private Person parent = new Person();
  private Person child = new Person();

  public FamilyMember() {

  }

  public void setParent(Person parent) {
    this.parent = parent;
  }

  public Person getParent() {
    return parent;
  }

  public void setChild(Person child) {
    this.child = child;
  }

  public Person getChild() {
    return child;
  }

}
