package guru.bug.playcardsfx;

/**
 * Suit of the card.
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public enum Suit {
    CLUB(Color.BLACK),
    DIAMOND(Color.RED),
    HEART(Color.RED),
    SPADE(Color.BLACK);

    private final Color color;
    Suit(Color color) {
        this.color = color;
    }

    /**
     * Gets the color of the suit.
     *
     * @return Color (red or black) of the suit.
     */
    public Color getColor() {
        return color;
    }
}
