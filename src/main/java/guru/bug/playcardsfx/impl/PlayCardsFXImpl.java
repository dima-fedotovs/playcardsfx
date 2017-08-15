package guru.bug.playcardsfx.impl;

import guru.bug.playcardsfx.Card;
import guru.bug.playcardsfx.Rank;
import guru.bug.playcardsfx.Suit;
import guru.bug.playcardsfx.Table;
import javafx.application.Application;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class PlayCardsFXImpl {
    public static void launch(String[] args, int width, int height, Consumer<Table> onStart) {
        MainWindow.setupInitialValues(onStart, width, height);
        Application.launch(MainWindow.class, args);
    }

    public static List<Card> createDeck(boolean faceDown) {
        List<Card> result = new ArrayList<>(52);
        for (Rank r : Rank.values()) {
            for (Suit s : Suit.values()) {
                CardImpl c = new CardImpl(r, s);
                c.setFaceDown(faceDown);
                result.add(c);
            }
        }
        return result;
    }

    public static Card createCard(Rank rank, Suit suit) {
        return new CardImpl(rank, suit);
    }
}
