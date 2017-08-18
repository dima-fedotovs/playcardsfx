package guru.bug.playcardsfx;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public interface Card {
    Rank getRank();

    Suit getSuit();

    boolean isFaceDown();

    void setFaceDown(boolean faceDown);

    Stack getStack();

    Color getColor();

}
