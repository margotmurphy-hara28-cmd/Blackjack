import com.sun.media.sound.RIFFInvalidDataException;

public class Player {
    Card[] hand;
    int numberOfCards;
    Boolean hasStood = false;

    public Player(Card card1, Card card2){
        hand = new Card[2];
        hand[0] = card1;
        hand[1] = card2;
        numberOfCards = 2;

    }
    public void stand() {
    }

    public void hit(Card newCard){
        numberOfCards = numberOfCards + 1;
        Card[] newHand = new Card[numberOfCards];
        for (int i = 0; i < numberOfCards-1; i++) {
            newHand[i] = hand[i];
        }
        newHand[numberOfCards-1] = newCard;
        hand = newHand;
        System.out.println("==============");
        printInfo();
        if(getHandValue()>21){
            System.out.println("YOU LOSE");
        }
    }
    public void printInfo(){
        for (int i = 0; i < numberOfCards; i++) {
            hand[i].printInfo();
        }
        System.out.println("Value= " + getHandValue());
    }
    public void dealerInfo(){
        for (int i = 0; i < numberOfCards; i++) {
            if(i==0){
                hand[i].printInfo();
            }
            else{
                System.out.println("?");
            }
        }

    }
    public int getHandValue(){
        int sum = 0;
        for (int i = 0; i < numberOfCards; i++) {
            sum = sum +hand[i].value;
        }
        return sum;
    }
}



