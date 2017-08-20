package guru.bug.playcardsfx;

/**
 * Representing the playing card. It has {@link Suit}, {@link Rank} and can be displayed face up or down.
 * Card cannot be displayed on the {@link Table} directly: cards should be added to the {@link Stack} to be displayed.
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public interface Card {
    /**
     * Gets {@link Rank} of the card
     *
     * @return {@link Rank} of the card
     */
    Rank getRank();

    /**
     * Gets {@link Suit} of the card
     *
     * @return {@link Suit} of the card
     */
    Suit getSuit();

    /**
     * Checks whether the card placed face down.
     * @return <code>true</code> if card placed face down or <code>false</code> - if face up
     */
    boolean isFaceDown();

    /**
     * Sets how card is displayed.
     * @param faceDown <code>true</code> if card placed face down or <code>false</code> - if face up
     */
    void setFaceDown(boolean faceDown);

    /**
     * Gets stack where the card is placed in.
     * @return {@link Stack} where card is placed in, or <code>null</code> if the card is not placed anywhere
     */
    Stack getStack();

    /**
     * Gets the color of the card. Convenient method as a shortcut for <code>getSuit().getColor()</code>
     * @return the {@link Color} of the card.
     */
    Color getColor();

}
