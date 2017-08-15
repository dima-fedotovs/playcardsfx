package guru.bug.playcardsfx.impl;

import guru.bug.playcardsfx.Card;
import guru.bug.playcardsfx.Rank;
import guru.bug.playcardsfx.Stack;
import guru.bug.playcardsfx.Suit;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
class CardImpl extends StackPane implements Card {
    private static final Image IMG = new Image("/guru/bug/playcardsfx/cards.png");
    private static final int CARD_IMG_WIDTH = 221;
    private static final int CARD_IMG_HEIGHT = 300;
    private static final int BACK_COL = 0;
    private static final int BACK_ROW = 4;
    private final ReadOnlyObjectWrapper<Rank> rank = new ReadOnlyObjectWrapper<>();
    private final ReadOnlyObjectWrapper<Suit> suit = new ReadOnlyObjectWrapper<>();
    private final SimpleBooleanProperty faceDown = new SimpleBooleanProperty();
    private final SimpleObjectProperty<StackImpl> parentStack = new SimpleObjectProperty<>();
    private final ObjectBinding<Rectangle2D> viewport = Bindings.createObjectBinding(() -> {
        double col;
        double row;
        if (faceDown.get() || rank.get() == null || suit.get() == null) {
            col = BACK_COL;
            row = BACK_ROW;
        } else {
            col = rank.get().ordinal();
            row = suit.get().ordinal();
        }
        double x = col * CARD_IMG_WIDTH;
        double y = row * CARD_IMG_HEIGHT;
        return new Rectangle2D(x, y, CARD_IMG_WIDTH, CARD_IMG_HEIGHT);
    }, rank, suit, faceDown);


    CardImpl(Rank rank, Suit suit) {
        super();
        ImageView imageView = new ImageView(IMG);
        getChildren().add(imageView);
        this.rank.set(rank);
        this.suit.set(suit);
        imageView.viewportProperty().bind(viewport);
        imageView.fitHeightProperty().bind(heightProperty());
        imageView.fitWidthProperty().bind(widthProperty());
        imageView.setPreserveRatio(true);
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

    public BooleanProperty faceDownProperty() {
        return faceDown;
    }

    public StackImpl getParentStack() {
        return parentStack.get();
    }

    public ObjectProperty<StackImpl> parentStackProperty() {
        return parentStack;
    }

    public void setParentStack(StackImpl parentStack) {
        this.parentStack.set(parentStack);
    }
}
