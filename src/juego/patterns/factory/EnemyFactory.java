package juego.patterns.factory;

import java.util.Random;

public class EnemyFactory {

    private static final Random rnd = new Random();

    public static Enemy createEnemy(String type, int maxWidth, int maxHeight) {

        int x = rnd.nextInt(Math.max(1, maxWidth - 100));
        int y = rnd.nextInt(Math.max(1, maxHeight - 120));

        switch (type.toLowerCase()) {
            case "normal":
                return new GhostEnemy(x, y);

            case "fast":
                return new FastGhostEnemy(x, y);

            default:
                throw new IllegalArgumentException(
                    "Tipo de enemigo no válido: " + type + ". Usa: normal | fast"
                );
        }
    }
}
