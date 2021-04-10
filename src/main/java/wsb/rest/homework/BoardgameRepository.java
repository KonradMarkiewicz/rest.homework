package wsb.rest.homework;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Repository
public class BoardgameRepository {

    Long count = 2L;

    List<Boardgame> boardgames = new LinkedList<>(Arrays.asList(
            new Boardgame( 1L, "Exploding Kittens", "Matthew Inman"),
            new Boardgame(2L, "Flamme Rouge", "Asger Harding Granerud")
    ));

    List<Boardgame> findAll(){
        return boardgames;
    }

    Boardgame find(Long id) {
        return boardgames
                .stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Boardgame create(Boardgame boardgame) {
        boardgame.setId(++count);

        boardgames.add(boardgame);

        return boardgame;
    }

    public Boardgame update(Long id, Boardgame boardgame) {
        Boardgame boardgameToUpdate = find(id);

        if (boardgameToUpdate == null){
            return null;
        }

        boardgameToUpdate.setTitle(boardgame.getTitle());
        boardgameToUpdate.setAuthor(boardgame.getAuthor());

        return boardgameToUpdate;
    }

    public void delete(Long id) {
        Boardgame boardgame = find(id);
        boardgames.remove(boardgame);
    }
}
