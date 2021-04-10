package wsb.rest.homework;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("boardgames")
public class BoardgameController {
    final BoardgameRepository boardgameRepository;

    public BoardgameController(BoardgameRepository boardgameRepository) {
        this.boardgameRepository = boardgameRepository;
    }

    @GetMapping
    List<Boardgame> findAll(){
        return boardgameRepository.findAll();
    }

    @GetMapping("{id}")
    ResponseEntity<?> find(@PathVariable Long id){
        Boardgame boardgame = boardgameRepository.find(id);

        if (boardgame != null){
            return ResponseEntity.ok().body(boardgame);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    ResponseEntity<?> create(@RequestBody Boardgame boardgame) throws URISyntaxException {
        Boardgame savedBoardgame = boardgameRepository.create(boardgame);

        return ResponseEntity.created(new URI("http://localhost:8080/boardgames/" + boardgame.getId())).body(savedBoardgame);
    }

    @PutMapping("{id}")
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody Boardgame boardgame)  {
        Boardgame updatedBoardgame = boardgameRepository.update(id, boardgame);

        if (updatedBoardgame != null){
            return ResponseEntity.ok().body(updatedBoardgame);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        boardgameRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
