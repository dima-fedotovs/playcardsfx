package guru.bug.playcardsfx.impl;

import guru.bug.playcardsfx.*;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.LinearGradient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class TableImpl extends Pane implements Table {
    private static final Image BACKGROUND = new Image("/guru/bug/playcardsfx/pattern.png");
    private final ReadOnlyIntegerWrapper columns = new ReadOnlyIntegerWrapper();
    private final ReadOnlyIntegerWrapper rows = new ReadOnlyIntegerWrapper();
    private final ReadOnlyDoubleWrapper cellWidth = new ReadOnlyDoubleWrapper();
    private final ReadOnlyDoubleWrapper cellHeight = new ReadOnlyDoubleWrapper();


    public TableImpl(int columns, int rows) {
        this.columns.set(columns);
        this.rows.set(rows);
        setBackground(new Background(new BackgroundImage(BACKGROUND, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, null, null)));
        DoubleBinding boundWidthBinding = Bindings.createDoubleBinding(() -> {
            int pc = this.columns.intValue();
            return getWidth() / (pc <= 0 ? 20 : pc);
        }, widthProperty(), this.columns);
        this.cellWidth.bind(boundWidthBinding);
        DoubleBinding boundHeightBinding = Bindings.createDoubleBinding(() -> {
            int pr = this.rows.intValue();
            return getHeight() / (pr <= 0 ? 20 : pr);
        }, heightProperty(), this.rows);
        this.cellHeight.bind(boundHeightBinding);
    }

    @Override
    public Stack createStack(int col, int row, int hOfs, int vOfs) {
        StackImpl result = new StackImpl(this);
        result.setColumn(col);
        result.setRow(col);
        result.setHorizOffset(hOfs);
        result.setVertOffset(vOfs);
        return result;
    }

    @Override
    public List<Card> createPack(boolean faceDown) {
        List<Card> result = new ArrayList<>(52);
        for (Rank r : Rank.values()) {
            for (Suit s : Suit.values()) {
                CardImpl c = new CardImpl(r, s);
                c.setFaceDown(faceDown);
                result.add(c);
                getChildren().add(c);
            }
        }
        return result;
    }

    @Override
    public Card createCard(Rank rank, Suit suit) {
        CardImpl result = new CardImpl(rank, suit);
        getChildren().add(result);
        return result;
    }

    public int getColumns() {
        return columns.get();
    }

    public ReadOnlyIntegerProperty columnsProperty() {
        return columns.getReadOnlyProperty();
    }

    public int getRows() {
        return rows.get();
    }

    public ReadOnlyIntegerProperty rowsProperty() {
        return rows.getReadOnlyProperty();
    }

    public Stream<CardImpl> cardsStream() {
        return getChildren().stream()
                .filter(n -> n instanceof CardImpl)
                .map(n -> (CardImpl) n);
    }

    public void sortCards() {
        Collections.sort(getChildren(), this::compareChildren);
    }

    private int compareChildren(Node t1, Node t2) {
        int v1 = -1;
        int v2 = -1;
        if (t1 instanceof CardImpl) {
            v1 = ((CardImpl) t1).getIndex();
        }
        if (t2 instanceof CardImpl) {
            v2 = ((CardImpl) t2).getIndex();
        }
        return (v1 - v2);
    }

    public double getCellWidth() {
        return cellWidth.get();
    }

    public ReadOnlyDoubleProperty cellWidthProperty() {
        return cellWidth.getReadOnlyProperty();
    }

    public double getCellHeight() {
        return cellHeight.get();
    }

    public ReadOnlyDoubleProperty cellHeightProperty() {
        return cellHeight.getReadOnlyProperty();
    }
}
