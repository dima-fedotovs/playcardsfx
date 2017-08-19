package guru.bug.playcardsfx.impl;

import guru.bug.playcardsfx.*;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class TableImpl extends Pane implements Table {
    private final ReadOnlyDoubleWrapper columns = new ReadOnlyDoubleWrapper();
    private final ReadOnlyDoubleWrapper rows = new ReadOnlyDoubleWrapper();
    private final ReadOnlyDoubleWrapper cellWidth = new ReadOnlyDoubleWrapper();
    private final ReadOnlyDoubleWrapper cellHeight = new ReadOnlyDoubleWrapper();
    private BiConsumer<Stack, Card> onClick;

    TableImpl(int columns, int rows) {
        this.columns.set(columns);
        this.rows.set(rows);
        BackgroundImage backgroundImage = new BackgroundImage(Images.BACKGROUND_PATTERN, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, null, null);
        setBackground(new Background(backgroundImage));
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
    public Stack createStack(double col, double row, double hOfs, double vOfs) {
        StackImpl result = new StackImpl(this);
        result.setColumn(col);
        result.setRow(row);
        result.setHorizOffset(hOfs);
        result.setVertOffset(vOfs);
        getChildren().add(0, result);
        result.setOnMouseClicked(this::stackClicked);
        return result;
    }

    private void stackClicked(MouseEvent mouseEvent) {
        Stack st = (Stack) mouseEvent.getSource();
        doOnClick(st, null);
    }

    private void cardClicked(MouseEvent mouseEvent) {
        Card card = (Card) mouseEvent.getSource();
        Stack stack = card.getStack();
        doOnClick(stack, card);
    }

    private void doOnClick(Stack stack, Card card) {
        if (onClick != null) {
            onClick.accept(stack, card);
        }
    }

    @Override
    public void onClick(BiConsumer<Stack, Card> onClick) {
        this.onClick = onClick;
    }

    @Override
    public List<Card> createPack(boolean faceDown) {
        List<Card> result = new ArrayList<>(52);
        for (Rank r : Rank.values()) {
            for (Suit s : Suit.values()) {
                CardImpl c = _createCard(r, s, faceDown);
                c.setFaceDown(faceDown);
                result.add(c);
            }
        }
        return result;
    }

    private CardImpl _createCard(Rank rank, Suit suit, boolean faceDown) {
        CardImpl result = new CardImpl(rank, suit);
        result.setFaceDown(faceDown);
        getChildren().add(result);
        result.setOnMouseClicked(this::cardClicked);
        return result;
    }

    @Override
    public Card createCard(Rank rank, Suit suit) {
        return _createCard(rank, suit, false);
    }

    public double getColumns() {
        return columns.get();
    }

    public ReadOnlyDoubleProperty columnsProperty() {
        return columns.getReadOnlyProperty();
    }

    public double getRows() {
        return rows.get();
    }

    public ReadOnlyDoubleProperty rowsProperty() {
        return rows.getReadOnlyProperty();
    }

    Stream<CardImpl> cardsStream() {
        return getChildren().stream()
                .filter(n -> n instanceof CardImpl)
                .map(n -> (CardImpl) n);
    }

    Stream<StackImpl> stacksStream() {
        return getChildren().stream()
                .filter(n -> n instanceof StackImpl)
                .map(n -> (StackImpl) n);
    }

    @Override
    protected void layoutChildren() {
        Map<StackImpl, List<CardImpl>> group = cardsStream()
                .filter(c -> c.getParentStack() != null)
                .collect(Collectors.groupingBy(
                        CardImpl::getParentStack,
                        IdentityHashMap::new,
                        Collectors.mapping(Function.identity(), Collectors.toList())));
        double cellWidth = getCellWidth();
        double cellHeight = getCellHeight();
        double scale = Math.min(cellWidth / Images.CARD_IMG_WIDTH, cellHeight / Images.CARD_IMG_HEIGHT);
        double cardWidth = scale * Images.CARD_IMG_WIDTH;
        double cardHeight = scale * Images.CARD_IMG_HEIGHT;
        group.forEach((stack, cards) -> {
            double startx = stack.getStartX();
            double starty = stack.getStartY();
            double stepx = cardWidth * stack.getHorizOffset();
            double stepy = cardHeight * stack.getVertOffset();
            for (int i = 0; i < cards.size(); i++) {
                double x = startx + stepx * i;
                double y = starty + stepy * i;
                cards.get(i).resizeRelocate(x, y, cardWidth, cardHeight);
            }
        });
        stacksStream().forEach(s -> {
            double x = s.getStartX();
            double y = s.getStartY();
            s.resizeRelocate(x, y, cardWidth, cardHeight);
        });
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
