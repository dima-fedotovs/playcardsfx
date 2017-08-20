package guru.bug.playcardsfx;

import java.util.Collection;
import java.util.List;

/**
 * Stack of the cards. It has only two methods - for settings and getting cards to/from this stack.
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public interface Stack {
    /**
     * Sets collection of cards to this stack. Passed collection will be "detached" from the stack,
     * so changing the collection after this method will no have effect.
     *
     * @param cards collection of cards to be displayed in this stack. Can be <code>null</code>, to clear the stack.
     */
    void setCards(Collection<Card> cards);

    /**
     * Gets list of the cards from this stack. Method doesn't change how stack is displayed.
     * Returned list is "detached", so changing the list won't affect how stack is displayed.
     *
     * @return "detached" mutable list of cards.
     */
    List<Card> getCards();
}
