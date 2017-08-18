package guru.bug.playcardsfx.impl;

import guru.bug.playcardsfx.Card;
import guru.bug.playcardsfx.Stack;
import javafx.beans.property.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class StackImpl implements Stack {
    private final TableImpl table;
    private final SimpleDoubleProperty horizOffset = new SimpleDoubleProperty();
    private final SimpleDoubleProperty vertOffset = new SimpleDoubleProperty();
    private final SimpleIntegerProperty column = new SimpleIntegerProperty();
    private final SimpleIntegerProperty row = new SimpleIntegerProperty();
    private final ReadOnlyDoubleWrapper startX = new ReadOnlyDoubleWrapper();
    private final ReadOnlyDoubleWrapper startY = new ReadOnlyDoubleWrapper();


    public StackImpl(TableImpl table) {
        this.table = table;
        startX.bind(table.cellWidthProperty().multiply(column));
        startY.bind(table.cellHeightProperty().multiply(row));
    }

    @Override
    public List<Card> getCards() {
        List<Card> result = new ArrayList<>(20);
        table.cardsStream()
                .filter(c -> c.getParentStack() == this)
                .map(c -> (Card) c)
                .forEach(result::add);
        return result;
    }

    @Override
    public void setCards(Collection<Card> cards) {
        Set<CardImpl> oldMyCards = identitySetOfCards();
        MutableInteger idx = new MutableInteger();
        table.cardsStream().forEach(c -> {
            oldMyCards.remove(c);
            c.setParentStack(this);
            c.setIndex(idx.getAndIncrement());
        });
        table.sortCards();
        oldMyCards.forEach(c -> c.setParentStack(null));
    }

    private Set<CardImpl> identitySetOfCards() {
        return table.cardsStream()
                .filter(c -> c.getParentStack() == this)
                .collect(Collectors.toCollection(() -> Collections.newSetFromMap(new IdentityHashMap<>())));
    }

    public double getHorizOffset() {
        return horizOffset.get();
    }

    public void setHorizOffset(double hOffset) {
        this.horizOffset.set(hOffset);
    }

    public DoubleProperty horizOffsetProperty() {
        return horizOffset;
    }

    public double getVertOffset() {
        return vertOffset.get();
    }

    public void setVertOffset(double vOffset) {
        this.vertOffset.set(vOffset);
    }

    public DoubleProperty vertOffsetProperty() {
        return vertOffset;
    }

    public int getColumn() {
        return column.get();
    }

    public void setColumn(int column) {
        this.column.set(column);
    }

    public IntegerProperty columnProperty() {
        return column;
    }

    public int getRow() {
        return row.get();
    }

    public void setRow(int row) {
        this.row.set(row);
    }

    public IntegerProperty rowProperty() {
        return row;
    }

    public double getStartX() {
        return startX.get();
    }

    public ReadOnlyDoubleProperty startXProperty() {
        return startX.getReadOnlyProperty();
    }

    public double getStartY() {
        return startY.get();
    }

    public ReadOnlyDoubleProperty startYProperty() {
        return startY.getReadOnlyProperty();
    }
}
