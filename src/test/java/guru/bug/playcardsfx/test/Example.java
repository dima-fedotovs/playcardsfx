package guru.bug.playcardsfx.test;

import guru.bug.playcardsfx.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class Example {

    public static void main(String[] args) {
        PlayCardsFX.launch(args, 7, 5, Example::start);
    }

    private static void start(Table table) {
        List<Card> cards = table.createPack(true);
        Collections.shuffle(cards);
        Stack deckStack = table.createStack(0.5, 0.5, 0.01, 0);
        deckStack.setCards(cards);
        Stack emptySt = table.createStack(3, 3.5, 0.2, 0);

        table.onClick((stack, card) -> {
            if (stack == deckStack && card != null) {
                List<Card> rest = deckStack.getCards();
                Card c = rest.get(rest.size() - 1);
                List<Card> l = emptySt.getCards();
                l.add(c);
                c.setFaceDown(false);
                emptySt.setCards(l);
            }
        });
    }

}
