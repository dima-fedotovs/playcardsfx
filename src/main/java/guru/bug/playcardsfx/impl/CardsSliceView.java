package guru.bug.playcardsfx.impl;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class CardsSliceView extends ImageView {
    private final SimpleIntegerProperty sliceCol = new SimpleIntegerProperty();
    private final SimpleIntegerProperty sliceRow = new SimpleIntegerProperty();

    public CardsSliceView() {
        this(0, 0);
    }

    public CardsSliceView(int fixedCol, int fixedRow) {
        super(Images.CARDS);
        sliceCol.set(fixedCol);
        sliceRow.set(fixedRow);
        ObjectBinding<Rectangle2D> viewport = Bindings.createObjectBinding(() -> {
            int col = sliceCol.intValue();
            int row = sliceRow.intValue();
            double x = col * Images.CARD_IMG_WIDTH;
            double y = row * Images.CARD_IMG_HEIGHT;
            return new Rectangle2D(x, y, Images.CARD_IMG_WIDTH, Images.CARD_IMG_HEIGHT);
        }, sliceCol, sliceRow);
        viewportProperty().bind(viewport);
        setManaged(true);
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public void resize(double width, double height) {
        setFitWidth(width);
        setFitHeight(height);
    }

    public int getSliceCol() {
        return sliceCol.get();
    }

    public void setSliceCol(int sliceCol) {
        this.sliceCol.set(sliceCol);
    }

    public IntegerProperty sliceColProperty() {
        return sliceCol;
    }

    public int getSliceRow() {
        return sliceRow.get();
    }

    public void setSliceRow(int sliceRow) {
        this.sliceRow.set(sliceRow);
    }

    public IntegerProperty sliceRowProperty() {
        return sliceRow;
    }
}
