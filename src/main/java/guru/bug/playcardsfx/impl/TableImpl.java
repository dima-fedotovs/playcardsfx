package guru.bug.playcardsfx.impl;

import guru.bug.playcardsfx.Stack;
import guru.bug.playcardsfx.Table;
import javafx.scene.layout.Pane;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
class TableImpl extends Pane implements Table {
    private final int columns;
    private final int rows;

    public TableImpl(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
    }

    @Override
    public Stack createStack(int col, int row, int hOfs, int vOfs) {
        StackImpl result = new StackImpl(this);
        result.setColumn(col);
        result.setRow(col);
        result.setHOffset(hOfs);
        result.setVOffset(vOfs);
    }

    ;
}
