package guru.bug.playcardsfx.impl;

import guru.bug.playcardsfx.*;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;


/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
class CardImpl extends CardsSliceView implements Card {
    private final ReadOnlyObjectWrapper<Rank> rank = new ReadOnlyObjectWrapper<>();
    private final ReadOnlyObjectWrapper<Suit> suit = new ReadOnlyObjectWrapper<>();
    private final SimpleBooleanProperty faceDown = new SimpleBooleanProperty();
    private final SimpleObjectProperty<StackImpl> parentStack = new SimpleObjectProperty<>();

    CardImpl(Rank rank, Suit suit) {
        this.rank.set(rank);
        this.suit.set(suit);
        sliceColProperty().bind(Bindings.createIntegerBinding(() -> {
            if (this.faceDown.get() || this.rank.get() == null || this.suit.get() == null) {
                return Images.BACK_COL;
            } else {
                return this.rank.get().ordinal();
            }
        }, this.faceDown, this.rank, this.suit));
        sliceRowProperty().bind(Bindings.createIntegerBinding(() -> {
            if (this.faceDown.get() || this.rank.get() == null || this.suit.get() == null) {
                return Images.BACK_ROW;
            } else {
                return this.suit.get().ordinal();
            }
        }, this.faceDown, this.rank, this.suit));
        visibleProperty().bind(parentStack.isNotNull());
    }

    @Override
    public Rank getRank() {
        return rank.get();
    }

    public ReadOnlyObjectProperty<Rank> rankProperty() {
        return rank.getReadOnlyProperty();
    }

    @Override
    public Suit getSuit() {
        return suit.get();
    }

    public ReadOnlyObjectProperty<Suit> suitProperty() {
        return suit.getReadOnlyProperty();
    }

    @Override
    public boolean isFaceDown() {
        return faceDown.get();
    }

    @Override
    public void setFaceDown(boolean faceDown) {
        this.faceDown.set(faceDown);
    }

    @Override
    public Stack getStack() {
        return parentStack.get();
    }

    @Override
    public Color getColor() {
        return suit.get().getColor();
    }

    public BooleanProperty faceDownProperty() {
        return faceDown;
    }

    public StackImpl getParentStack() {
        return parentStack.get();
    }

    public void setParentStack(StackImpl parentStack) {
        this.parentStack.set(parentStack);
    }

    public ObjectProperty<StackImpl> parentStackProperty() {
        return parentStack;
    }


    @Override
    public String toString() {
        return "Card{" + rank.get() +
                ", " + suit.get() +
                '}';
    }
}
