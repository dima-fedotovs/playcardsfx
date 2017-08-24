# playcardsfx

This is a simple library/engine for card games creation.

Sample code:

    public class Main {
        public static void main(String[] args) {
            PlayCardsFX.launch(args, 6, 5, Main::start);
        }

        private static void start(Table table) {
            List<Card> cards = table.createPack(true);
            Collections.shuffle(cards);
            Stack deckStack = table.createStack(0.5, 0.5, 0.0025, 0);
            deckStack.setCards(cards);

            Queue<Stack> stacks = new LinkedList<>();
            for (int i = 0; i < 4; i++) {
                Stack s = table.createStack(2 + i * 1, 0.5, 0, 0.3);
                stacks.add(s);
            }

            table.onClick((stack, card) -> {
                Stack s = stacks.remove();
                stacks.add(s);
                List<Card> list = s.getCards();
                list.add(card);
                card.setFaceDown(false);
                s.setCards(list);
            });
        }
    }

Card images are taken from [openclipart.org](https://openclipart.org/user-cliparts/nicubunu?page=8)
