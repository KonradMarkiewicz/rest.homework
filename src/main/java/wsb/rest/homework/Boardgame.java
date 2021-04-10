package wsb.rest.homework;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter
@NoArgsConstructor
public class Boardgame {

    Long id;
    String title;
    String author;

    public Boardgame(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public Boardgame(String title, String author) {
        this.title = title;
        this.author = author;
    }

    EntityModel<Boardgame> toModel(){
        return EntityModel.of(this,
                linkTo(methodOn(BoardgameHATEOASController.class).find(this.id)).withSelfRel());
    }
}
