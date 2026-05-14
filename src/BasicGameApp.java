//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv DON'T CHANGE! vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
// Graphics Libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
public class BasicGameApp implements Runnable, KeyListener{

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;
    Card[] deck;
    Player Player;
    Player Dealer;
    int indexInDeck = 0;
    Image Background;
    Image Sushi;
    Image Fruit;
    Image cardBackground;




    //Variable Definition Section
    //You can set their initial values too
    // Like Mario mario = new Mario(); //





    // Initialize your variables and construct your program objects here.
    public BasicGameApp() { // BasicGameApp constructor
        setUpGraphics();
        Background = Toolkit.getDefaultToolkit().getImage("tablecloth.jpg");
       Sushi  = Toolkit.getDefaultToolkit().getImage("sushi.png");
        Fruit  = Toolkit.getDefaultToolkit().getImage("fruit.png");
        cardBackground = Toolkit.getDefaultToolkit().getImage("card.jpg");
        deck = getShuffleDeck();
        for(int i = 0; i < deck.length; i++){
            //deck[i].printInfo();
        }
        Player = new Player(deck[0],deck[1]);
        System.out.println("Your cards:");
        Player.printInfo();
        System.out.println(("==================="));
        indexInDeck = indexInDeck+2;
        Dealer = new Player(deck[2],deck[3]);
        System.out.println("Dealer's cards:");
        Dealer.dealerInfo();
        System.out.println(("==================="));

        indexInDeck = indexInDeck+2;
        //Player.stand();
        //Player.hit(deck[indexInDeck]);
        //indexInDeck = indexInDeck+2;


        //variable and objects
        //create (construct) the objects needed for the game

    }
    public Card[] getShuffleDeck() {
        String[] suites = {"Hearts", "Clubs", "Diamonds", "Spades"};
        String[] names = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Queen", "King","Jack"};
        Card[] gameDeck = new Card[52];
        int placeInDeck = 0;

        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < suites.length; j++) {
                gameDeck[placeInDeck] = new Card(names[i], suites[j]);
                placeInDeck = placeInDeck + 1;
            }
        }
        for (int i = 0; i < gameDeck.length; i++) {
            Card temp =  gameDeck[i];
            int randomIndex = (int)(Math.random()*52);
            gameDeck[i]= gameDeck[randomIndex];
            gameDeck[randomIndex] = temp;
        }

        return gameDeck;
    }

    // end BasicGameApp constructor

    public void moveThings() {
        //call the move() code for each object  -

    }

    //Paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(Background, 0, 0, WIDTH, HEIGHT, null);
        g.drawImage(Sushi, -150, -150, 400, 400, null);
        g.drawImage(Fruit, 700, 500, 400, 400, null);
        g.setFont(new Font("SansSerif",Font.BOLD, 20));
        g.drawString("Press 'H' to hit, 'S' to stand, and 'R' to play again", 200,40);

        //draw the images
        // Signature: drawImage(Image img, int x, int y, int width, int height, ImageObserver observer)
        g.setFont(new Font("SansSerif",Font.BOLD, 24));
        g.drawString("Player: ",200,150);
        for(int i = 0; i < Player.numberOfCards; i++){
            g.drawImage(cardBackground, 180, 180+100*i,250,40, null);
            g.drawString(Player.hand[i].getInfo(),200,200 + 100*i);
            g.drawString(""+Player.getHandValue(),290,150);
        }
        g.drawString("Dealer: ",600,150);

        for(int i = 0; i < Dealer.numberOfCards; i++){
            if (i > 0) {
                g.drawImage(cardBackground, 600, 180+100*i,250,40, null);
                g.drawString(Dealer.hand[i].getInfo(), 600, 200 + 100 * i);
            }
            else{
                g.drawString("????????", 600, 200 + 100 * i);

            }
        }
        if(Player.getHandValue()>21){
            g.setFont(new Font("SansSerif",Font.BOLD, 50));
            g.drawString("YOU BUST -- DEALER WINS", 300, 100 );
            g.setFont(new Font("SansSerif",Font.BOLD, 24));
            for(int i = 0; i < Dealer.numberOfCards; i++){
                    g.drawImage(cardBackground, 600, 180+100*i,250,40, null);
                    g.drawString(Dealer.hand[i].getInfo(), 600, 200 + 100 * i);

            }
        }
        if(Dealer.getHandValue()>21){
            g.setFont(new Font("SansSerif",Font.BOLD, 50));
            g.drawString("DEALER BUSTS -- YOU WIN", 300, 100 );
            g.setFont(new Font("SansSerif",Font.BOLD, 24));
            for(int i = 0; i < Dealer.numberOfCards; i++){

                    g.drawImage(cardBackground, 600, 180+100*i,250,40, null);
                    g.drawString(Dealer.hand[i].getInfo(), 600, 200 + 100 * i);



            }
        }

        if(Dealer.hasStood == true && Player.getHandValue()>Dealer.getHandValue()){
            g.drawString(""+Dealer.getHandValue(),690,150);
            g.setFont(new Font("SansSerif",Font.BOLD, 50));
            g.drawString("YOU WIN", 300, 100 );
            g.setFont(new Font("SansSerif",Font.BOLD, 24));
            for(int i = 0; i < Dealer.numberOfCards; i++){

                    g.drawImage(cardBackground, 600, 180+100*i,250,40, null);
                    g.drawString(Dealer.hand[i].getInfo(), 600, 200 + 100 * i);

            }
        }
        else if (Dealer.hasStood == true && Player.getHandValue()<Dealer.getHandValue() && Dealer.getHandValue()<= 21) {
            g.drawString(""+Dealer.getHandValue(),690,150);
            g.setFont(new Font("SansSerif",Font.BOLD, 50));
            g.drawString("DEALER WINS", 300, 100 );
            g.setFont(new Font("SansSerif",Font.BOLD, 24));
            for(int i = 0; i < Dealer.numberOfCards; i++){

                    g.drawImage(cardBackground, 600, 180+100*i,250,40, null);
                    g.drawString(Dealer.hand[i].getInfo(), 600, 200 + 100 * i);

            }
        }
        else if(Dealer.hasStood == true && Player.getHandValue()==Dealer.getHandValue() && Dealer.getHandValue()<= 21){
            g.drawString(""+Dealer.getHandValue(),690,150);
            g.setFont(new Font("SansSerif",Font.BOLD, 50));
            g.drawString("PUSH", 300, 100 );
            g.setFont(new Font("SansSerif",Font.BOLD, 24));
            for(int i = 0; i < Dealer.numberOfCards; i++){
                    g.drawImage(cardBackground, 600, 180+100*i,250,40, null);
                    g.drawString(Dealer.hand[i].getInfo(), 600, 200 + 100 * i);


            }
        }



        // Keep the code below at the end of render()
        g.dispose();
        bufferStrategy.show();
    }


    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv DON'T CHANGE! vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;

    // PSVM: This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever.
        while (true) {
            moveThings();  //move all the game objects
            render();  // paint the graphics
            pause(10); // sleep for 10 ms
        }
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time ) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    private Image getImage(String filename){
        return Toolkit.getDefaultToolkit().getImage(filename);
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);

        canvas.setIgnoreRepaint(true);
        canvas.addKeyListener(this);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println(e.getKeyCode());
        int key = e.getKeyCode();
        if(key == 83){

            /*
            if(Dealer.getHandValue()<=17){
                System.out.println("Dealer Hits");
                Dealer.hit(deck[indexInDeck++]);
            }
            else if(Dealer.getHandValue()>17){
                System.out.println("Dealer Stands");
                Dealer.stand();
                Dealer.hasStood = true;

            }
             */
            while(Dealer.getHandValue()<=17 || Dealer.getHandValue()< Player.getHandValue()) {
                System.out.println("Dealer Hits");
                Dealer.hit(deck[indexInDeck++]);
            }
            Dealer.hasStood = true;


            System.out.println("Dealer's cards:");
            Dealer.printInfo();
            System.out.println(("==================="));
            if(Player.getHandValue()>Dealer.getHandValue()){
                System.out.println("Player Wins!");
            }
            else if(Player.getHandValue()<Dealer.getHandValue()){
                System.out.println("Dealer Wins!");
            } else if (Player.getHandValue()<Dealer.getHandValue()) {
                System.out.println("Push");
            }

        }
        else if(key == 72){
            Player.hit(deck[indexInDeck++]);
        }
        else if(key == 82) { // R
            deck = getShuffleDeck();

            Player = new Player(deck[0],deck[1]);
            System.out.println("Your cards:");
            Player.printInfo();
            System.out.println(("==================="));
            indexInDeck = indexInDeck+2;
            Dealer = new Player(deck[2],deck[3]);
            System.out.println("Dealer's cards:");
            Dealer.dealerInfo();
            System.out.println(("==================="));

            indexInDeck = indexInDeck+2;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e.getKeyCode());
        int key = e.getKeyCode();

    }
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
}