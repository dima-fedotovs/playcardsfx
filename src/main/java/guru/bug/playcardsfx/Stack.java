package guru.bug.playcardsfx;

import java.util.Collection;
import java.util.List;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public interface Stack {
    void setCards(Collection<Card> cards);
    List<Card> getCards();
}
