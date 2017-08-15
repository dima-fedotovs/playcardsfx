package guru.bug.playcardsfx.impl;

import guru.bug.playcardsfx.PlayCardsFX;
import guru.bug.playcardsfx.Rank;
import guru.bug.playcardsfx.Suit;
import guru.bug.playcardsfx.Table;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.function.Consumer;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class MainWindow extends Application {
    private static Consumer<Table> onStart;
    private static int initCols;
    private static int initRows;

    static void setupInitialValues(Consumer<Table> onStart, int cols, int rows) {
        MainWindow.onStart = onStart;
        MainWindow.initCols = cols;
        MainWindow.initRows = rows;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        TableImpl table = new TableImpl(initCols, initRows);
        root.setCenter(table);
        root.setMinSize(600, 480);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        onStart.accept(table);
        CardImpl card = (CardImpl) PlayCardsFX.createCard(Rank.ACE, Suit.CLUB);
        table.getChildren().add(card);
        card.setLayoutX(100);
        card.setLayoutY(100);
        primaryStage.show();
    }
}
