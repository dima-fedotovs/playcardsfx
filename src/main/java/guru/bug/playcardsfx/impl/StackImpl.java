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
    private final SimpleDoubleProperty column = new SimpleDoubleProperty();
    private final SimpleDoubleProperty row = new SimpleDoubleProperty();
    private final ReadOnlyDoubleWrapper startX = new ReadOnlyDoubleWrapper();
    private final ReadOnlyDoubleWrapper startY = new ReadOnlyDoubleWrapper();


    StackImpl(TableImpl table) {
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
        List<CardImpl> oldMyCards = owningCards();
        List<CardImpl> newMyCards = cards.stream()
                .map(c -> (CardImpl)c)
                .collect(Collectors.toList());
        oldMyCards.forEach(c -> c.setParentStack(null));
        table.getChildren().removeAll(oldMyCards);
        table.getChildren().removeAll(newMyCards);
        table.getChildren().addAll(newMyCards);
        newMyCards.forEach(c -> c.setParentStack(this));
        table.layout();
    }

    private List<CardImpl> owningCards() {
        return table.cardsStream()
                .filter(c -> c.getParentStack() == this)
                .collect(Collectors.toList());
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

    public double getColumn() {
        return column.get();
    }

    public void setColumn(double column) {
        this.column.set(column);
    }

    public DoubleProperty columnProperty() {
        return column;
    }

    public double getRow() {
        return row.get();
    }

    public void setRow(double row) {
        this.row.set(row);
    }

    public DoubleProperty rowProperty() {
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
