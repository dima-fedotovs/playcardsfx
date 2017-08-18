package guru.bug.playcardsfx;

import java.util.List;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public interface Table {
    default List<Card> createPack() {
        return createPack(false);
    }

    List<Card> createPack(boolean faceDown);

    Card createCard(Rank rank, Suit suit);

    Stack createStack(double col, double row, double hOfs, double vOfs);

}
