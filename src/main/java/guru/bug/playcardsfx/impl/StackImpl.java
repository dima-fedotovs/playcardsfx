package guru.bug.playcardsfx.impl;

import guru.bug.playcardsfx.Card;
import guru.bug.playcardsfx.Stack;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class StackImpl implements Stack {
    private final TableImpl table;
    private final ReadOnlyListWrapper<CardImpl> cards = new ReadOnlyListWrapper<>(FXCollections.observableArrayList());
    private final SimpleDoubleProperty hOffset = new SimpleDoubleProperty();
    private final SimpleDoubleProperty vOffset = new SimpleDoubleProperty();

    public StackImpl(TableImpl table) {
        this.table = table;
    }

    @Override
    public List<Card> getCards() {
        List<Card> result = new ArrayList<>(this.cards.size());
        this.cards.stream()
                .map(c -> (Card) c)
                .forEach(result::add);
        return result;
    }

    @Override
    public void setCards(Collection<Card> cards) {
        if (cards == null || cards.isEmpty()) {
            this.cards.clear();
        } else {
            List<CardImpl> list = cards.stream()
                    .map(c -> (CardImpl) c)
                    .collect(Collectors.toList());
            this.cards.setAll(list);
        }
    }

    public double getHOffset() {
        return hOffset.get();
    }

    public void setHOffset(double hOffset) {
        this.hOffset.set(hOffset);
    }

    public DoubleProperty hOffsetProperty() {
        return hOffset;
    }

    public double getVOffset() {
        return vOffset.get();
    }

    public void setVOffset(double vOffset) {
        this.vOffset.set(vOffset);
    }

    public DoubleProperty vOffsetProperty() {
        return vOffset;
    }
}
