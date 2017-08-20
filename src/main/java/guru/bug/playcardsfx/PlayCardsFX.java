package guru.bug.playcardsfx;

import guru.bug.playcardsfx.impl.PlayCardsFXImpl;

import java.util.function.Consumer;

/**
 * Class for launching your game.
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class PlayCardsFX {

    /**
     * Launches the game.
     *
     * @param args    arguments passed to the program's main method.
     * @param width   width of the table (how many cards can be placed side by side)
     * @param height  height of the table (how many cards can be placed vertically)
     * @param onStart action for providing logic of the game. This action will receive instance of {@link Table}
     */
    public static void launch(String[] args, double width, double height, Consumer<Table> onStart) {
        PlayCardsFXImpl.launch(args, width, height, onStart);
    }

}
