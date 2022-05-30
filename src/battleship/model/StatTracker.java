package battleship.model;

import java.io.*;

public class StatTracker {
    private Stats stats;
    private File statsFile = new File("stats.txt");

    public StatTracker() {
        if (!statsFile.exists()) {
            try {
                statsFile.createNewFile();
            } catch (IOException e) {


            }
            stats = new Stats();
        } else {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(statsFile))) {
                stats = (Stats) objectInputStream.readObject();
            } catch (IOException ioException) {

            } catch (ClassNotFoundException classNotFoundException) {

            }

        }
    }

    public Stats getStats() {
        return stats;
    }

    public void updateStatsFile() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(statsFile))) {
            objectOutputStream.writeObject(stats);
            objectOutputStream.flush();
        } catch (IOException ioException) {

        }
    }
}
