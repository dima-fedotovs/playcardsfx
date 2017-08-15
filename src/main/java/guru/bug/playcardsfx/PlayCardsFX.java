package guru.bug.playcardsfx;

import guru.bug.playcardsfx.impl.PlayCardsFXImpl;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class PlayCardsFX {

    public static void launch(String[] args, int width, int height, Consumer<Table> onStart) {
        PlayCardsFXImpl.launch(args, width, height, onStart);
    }

    public static List<Card> createFullDeck() {
        return createFullDeck(false);
    }

    public static List<Card> createFullDeck(boolean faceDown) {
        return PlayCardsFXImpl.createDeck(faceDown);
    }

    public static Card createCard(Rank rank, Suit suit) {
        return PlayCardsFXImpl.createCard(rank, suit);
    }

}
