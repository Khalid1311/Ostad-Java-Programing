package Module_6;

public class Cat extends Pet implements PetBehavior{

    public Cat(String name, int age, String breed){
        super(name, age, breed);
    }

    @Override
    public void makeSound() {
        System.out.println("Meow");
    }

    @Override
    public void feed(){
        System.out.println("Feeding the cat");
    }

    @Override
    public void play() {
        System.out.println("Playing with the cat");
    }

    public String toString(){
        return "Name : "+getName()+" Age : "+getAge()+" Breed : "+getBreed();
    }



}
