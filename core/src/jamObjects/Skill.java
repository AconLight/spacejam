package jamObjects;

import boost.GameObject;

public abstract class Skill extends GameObject {

    public Person person;
    public Skill(Person person) {
        super();
        this.person = person;
    }

    public abstract void use();
}
