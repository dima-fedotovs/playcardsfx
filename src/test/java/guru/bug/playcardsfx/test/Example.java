package guru.bug.playcardsfx.test;

import guru.bug.playcardsfx.Card;
import guru.bug.playcardsfx.PlayCardsFX;
import guru.bug.playcardsfx.Stack;
import guru.bug.playcardsfx.Table;

import java.util.List;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class Example {

    public static void main(String[] args) {
        PlayCardsFX.launch(args, 5, 5, Example::start);
    }

    private static void start(Table table) {
        List<Card> cards = table.createPack();
        Stack st = table.createStack(0, 1, 25, 0);
        st.setCards(cards);
    }

}
