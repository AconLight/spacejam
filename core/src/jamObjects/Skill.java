package jamObjects;

import boost.GameObject;

public abstract class Skill extends GameObject {
    public boolean isUsed = false;
    public Person person;
    public Skill(Person person) {
        super();
        this.person = person;
    }

    public void left() {

    }

    public void right() {

    }

    public abstract void use();
}
