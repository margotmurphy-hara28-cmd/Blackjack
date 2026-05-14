public class Card {
    int value;
    String name;
    String suite;
    int code;

    public Card(String nameInput, String suiteInput) {
        name = nameInput;
        suite = suiteInput;
        if (nameInput == "Ace") {
            value = 1;
        } else if (nameInput == "Jack") {
            value = 10;
        } else if (nameInput == "Queen") {
            value = 10;
        } else if (nameInput == "King") {
            value = 10;
        } else {
            value = Integer.parseInt(name);
        }
    }

    public void printInfo(){
        System.out.println(name+" of "+suite);
    }

    public String getInfo(){
        return name+" of "+suite;
    }

    public static void main(String[] args) {

    }
}

