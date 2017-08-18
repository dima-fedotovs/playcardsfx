package guru.bug.playcardsfx.impl;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
class MutableInteger {
    private int value;

    int getAndIncrement() {
        int result = value;
        value++;
        return result;
    }
}
