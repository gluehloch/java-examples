package collections;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SortCollection {

    @DisplayName("Example: Sort a collection with a Comparator.")
    @Tag("collections")
    @Test
    void sortCollection() {
        List<String> strings = new ArrayList<>();
        strings.addAll(List.of("test0:historie", "test0.xml", "test1:struktur", "test2:historie", "test3.xml", "test3:struktur"));
        
        Collections.sort(strings, new StringComparator());
        
        assertThat(strings).containsExactly("test0.xml", "test3.xml", "test1:struktur", "test3:struktur", "test0:historie", "test2:historie");
    }
    
    @DisplayName("Example: Sort a collection with a Comparable.")
    @Tag("collections")
    @Test
    public void sortCollectionWithComparable() {
        List<Player> players = new ArrayList<>();
        players.addAll(List.of(Player.of(10), Player.of(5), Player.of(12), Player.of(1)));
        
        Collections.sort(players);
        assertThat(players).extracting("ranking").containsExactly(1, 5, 10, 12);
    }

    private static class StringComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            if (o1.endsWith(":struktur") && o2.endsWith(":struktur")) {
                return o1.compareTo(o2);
            }
            
            if (o1.endsWith(":historie") && o2.endsWith(":historie")) {
                return o1.compareTo(o2);
            }

            if (o1.endsWith(":struktur") && o2.endsWith(":historie")) {
                return -1;
            }
            
            if (o1.endsWith(":historie") && o2.endsWith(":struktur")) {
                return 1;
            }
            
            if (!o1.endsWith(":historie") && !o1.endsWith(":struktur") && (o2.endsWith(":historie") || o2.endsWith(":struktur"))) {
                return -1;
            }

            if ((o1.endsWith(":historie") || o1.endsWith(":struktur")) && !o2.endsWith(":historie") && !o2.endsWith(":struktur")) {
                return 1;
            }

            return o1.compareTo(o2);
        }
        
    }
    
    private static class Player implements Comparable<Player> {
        private final int ranking;
        
        public static Player of(int ranking) {
            Player player = new Player(ranking);
            return player;
        }
        
        public Player(int ranking) {
            this.ranking = ranking;
        }
        
        public int getRanking() {
            return ranking;
        }
        
        @Override
        public int compareTo(Player player) {
            // Hint:  (x < y) ? -1 : ((x == y) ? 0 : 1);
            return Integer.compare(this.getRanking(), player.getRanking());
        }
        
    }

}
