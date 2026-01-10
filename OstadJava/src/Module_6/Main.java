package Module_6;

public class Main {
    public static void main(String[] args) {
        Owner owner = new Owner("Khalid","Rajshahi");
        Dog dog = new Dog("Hachi",20,"Bulldog");
        Cat cat = new Cat("Tom",18,"Maine Coon");

        owner.adoptPet(dog);
        owner.adoptPet(cat);

        dog.makeSound();
        dog.feed();
        dog.play();

        cat.makeSound();
        cat.feed();
        cat.play();

        System.out.println(dog.toString());
        System.out.println(cat.toString());
    }
}
