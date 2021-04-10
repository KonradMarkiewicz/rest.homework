package wsb.rest.homework;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("hateoas")
public class BoardgameHATEOASController {
    final BoardgameRepository boardgameRepository;

    public BoardgameHATEOASController(BoardgameRepository boardgameRepository) {
        this.boardgameRepository = boardgameRepository;
    }

    @GetMapping()
    CollectionModel<EntityModel<Boardgame>> findAll(){

        List<EntityModel<Boardgame>> boardgamesModel = boardgameRepository
                .findAll()
                .stream()
                .map(Boardgame::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(boardgamesModel,
                linkTo(methodOn(BoardgameHATEOASController.class).findAll()).withSelfRel());
    }

    @GetMapping("{id}")
    ResponseEntity<?> find(@PathVariable Long id){
        Boardgame boardgame = boardgameRepository.find(id);

        if (boardgame != null){
            return ResponseEntity.ok().body(boardgame.toModel());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    ResponseEntity<?> create(@RequestBody Boardgame boardgame) {
        Boardgame saveBoardgame = boardgameRepository.create(boardgame);
        EntityModel<Boardgame> boardgameModel = saveBoardgame.toModel();

        return ResponseEntity
                .created(boardgameModel
                        .getRequiredLink(IanaLinkRelations.SELF)
                        .toUri())
                        .body(boardgameModel);
    }

    @PutMapping("{id}")
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody Boardgame boardgame)  {
        Boardgame updatedBoardgame = boardgameRepository.update(id, boardgame);

        if (updatedBoardgame != null){
            return ResponseEntity.ok().body(updatedBoardgame.toModel());
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
