package guru.bug.playcardsfx.impl;

import guru.bug.playcardsfx.Table;
import javafx.application.Application;

import java.util.function.Consumer;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class PlayCardsFXImpl {
    public static void launch(String[] args, double width, double height, Consumer<Table> onStart) {
        MainWindow.setupInitialValues(onStart, width, height);
        Application.launch(MainWindow.class, args);
    }
}
