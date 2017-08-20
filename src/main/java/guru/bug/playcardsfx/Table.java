package guru.bug.playcardsfx;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * Representing the area where cards are displayed.
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public interface Table {
    /**
     * Creates full pack of cards (52) of all suits and ranks. Cards created are face up.
     * It is the same as to call <code>createPack(false)</code>
     *
     * @return list of all cards.
     */
    default List<Card> createPack() {
        return createPack(false);
    }

    /**
     * Creates full pack of cards (52) of all suits and ranks.
     * Parameter indicates how this cards should be displayed face down (<code>true</code>) or face up (<code>false</code>)
     *
     * @param faceDown if <code>true</code> cards will be displayed face down by default.
     * @return list of all cards.
     */
    List<Card> createPack(boolean faceDown);

    /**
     * Creates single card of specified rank and suit.
     * @param rank rank of the card
     * @param suit suit of the card
     * @return newly created card
     */
    Card createCard(Rank rank, Suit suit);

    /**
     * Creates new stack for placing cards in specified position on the table.
     * Parameters <code>hOfs</code> and <code>vOfs</code> are the factors how much to offset every next card in the stack.
     * For example if <code>hOfs</code> is <code>1.0</code>, then cards will be displayed horizontally side by side.
     * If <code>hOfs</code> is <code>0.5</code> - every next card will overlap previous for 50%.
     * @param col column where to create the stack
     * @param row row where to create the stack
     * @param hOfs horizontal offset of cards in the stack
     * @param vOfs vertical offset of cards in the stack
     * @return {@link Stack} instance, where cards can be added
     */
    Stack createStack(double col, double row, double hOfs, double vOfs);

    /**
     * Action for clicking on card or empty stack. BiConsumer will receive the instance of {@link Stack}
     * and the instance of {@link Card}. If user click on empty stack the card will be <code>null</code>
     * @param onClick action with two parameters - stack and card
     */
    void onClick(BiConsumer<Stack, Card> onClick);

}
