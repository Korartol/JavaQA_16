import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class GameTest {
    ArrayList<Player> expected = new ArrayList<>();
    Game game = new Game();

    Player player1 = new Player(1, "player1", 200);
    Player player2 = new Player(2, "player2", 2000);
    Player player3 = new Player(3, "player3", 20);
    Player player4 = new Player(4, "player4", 200);


    // регистрация игрока
    @Test
    public void shouldRegisterPlayers() {
        expected.add(player1);
        game.register(player1);

        ArrayList<Player> actual = new ArrayList<>(game.players);

        Assertions.assertEquals(expected, actual);
    }

    // игрок не 1 не зарегистрирован
    @Test
    public void shouldThrowNotRegisteredException1() {
        game.register(player2);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("player1", "player2");
        });
    }

    // игрок не 1 не зарегистрирован
    @Test
    public void shouldThrowNotRegisteredException2() {
        game.register(player1);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("player1", "player2");
        });
    }


    // победил 2-й с силой 2000
    @Test
    public void shouldWinSecondPlayer() {
        game.register(player1);
        game.register(player2);

        int expected = 2;
        int actual = game.round("player1", "player2");

        Assertions.assertEquals(expected, actual);
    }

    // первый победил с силой 2000
    @Test
    public void shouldWinFirstPlayer() {
        game.register(player2);
        game.register(player3);

        int expected = 1;
        int actual = game.round("player2", "player3");

        Assertions.assertEquals(expected, actual);
    }

    // ничья
    @Test
    public void shouldBeAllWin() {
        game.register(player1);
        game.register(player4);

        int expected = 0;
        int actual = game.round("player1", "player4");

        Assertions.assertEquals(expected, actual);
    }
}
