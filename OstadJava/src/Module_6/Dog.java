package Module_6;

public class Dog extends Pet implements PetBehavior{

    public Dog(String name, int age, String breed){
        super(name, age, breed);
    }

    @Override
    public void makeSound() {
        System.out.println("Woof");
    }

    @Override
    public void feed(){
        System.out.println("Feeding the dog");
    }

    @Override
    public void play() {
        System.out.println("Playing with the dog");
    }

    public String toString(){
        return "Name : "+getName()+" Age : "+getAge()+" Breed : "+getBreed();
    }
}
