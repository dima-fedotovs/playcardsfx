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
        PlayCardsFX.launch(args, 5, 5, Example::start);
    }

    private static void start(Table table) {
        List<Card> cards = table.createPack(false);
        Collections.shuffle(cards);
        List<Card> reds = cards.stream().filter(c -> c.getColor() == Color.RED).collect(Collectors.toList());
        List<Card> blacks = cards.stream().filter(c -> c.getColor() == Color.BLACK).collect(Collectors.toList());
        Stack redSt = table.createStack(0, 0, 0.2, 0.0);
        Stack blackSt = table.createStack(0, 1.2, 0.2, 0);
        blackSt.setCards(blacks);
        Collections.shuffle(reds);
        redSt.setCards(reds);
    }

}
