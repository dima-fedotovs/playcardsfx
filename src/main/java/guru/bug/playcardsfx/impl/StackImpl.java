package guru.bug.playcardsfx.impl;

import guru.bug.playcardsfx.Card;
import guru.bug.playcardsfx.Stack;
import javafx.beans.property.*;
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
    private final SimpleIntegerProperty column = new SimpleIntegerProperty();
    private final SimpleIntegerProperty row = new SimpleIntegerProperty();
    private final ReadOnlyDoubleWrapper x = new ReadOnlyDoubleWrapper();
    private final ReadOnlyDoubleWrapper y = new ReadOnlyDoubleWrapper();

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
        this.cards.forEach(c -> {
            StackImpl parentStack = c.getParentStack();
            if (parentStack != null && parentStack != this) {
                parentStack.cards.remove(c);
                c.setParentStack(null);
            }
        });
        table.getChildren().removeAll(this.cards);
        if (cards == null || cards.isEmpty()) {
            this.cards.clear();
        } else {
            List<CardImpl> list = cards.stream()
                    .map(c -> (CardImpl) c)
                    .collect(Collectors.toList());
            this.cards.setAll(list);
        }
        table.getChildren().addAll(this.cards);
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

    public int getColumn() {
        return column.get();
    }

    public IntegerProperty columnProperty() {
        return column;
    }

    public void setColumn(int column) {
        this.column.set(column);
    }

    public int getRow() {
        return row.get();
    }

    public IntegerProperty rowProperty() {
        return row;
    }

    public void setRow(int row) {
        this.row.set(row);
    }
}
