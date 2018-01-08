package domain;

public class FamilyMember extends Animal {

  private Animal parent = new Animal();
  private Animal child = new Animal();

  public FamilyMember() {

  }

  public void setParent(Animal parent) {
    this.parent = parent;
  }

  public Animal getParent() {
    return parent;
  }

  public void setChild(Animal child) {
    this.child = child;
  }

  public Animal getChild() {
    return child;
  }

}
