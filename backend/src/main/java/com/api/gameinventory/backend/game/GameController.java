package com.api.gameinventory.backend.game;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class GameController {
    private final GameRepository gameRepository;

    private final GameModelAssembler assembler;


    public GameController(GameRepository gameRepository, GameModelAssembler assembler) {
        this.gameRepository = gameRepository;
        this.assembler = assembler;
    }


    // aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/games")
    public CollectionModel<EntityModel<Game>> getAllGames() {
        List<EntityModel<Game>> games = gameRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        
        return CollectionModel.of(games,
                linkTo(methodOn(GameController.class).getAllGames()).withSelfRel());
    }
    // end::get-aggregate-root[]

    @PostMapping("/games")
    public Game addNewGame(@RequestBody Game game) {
        return gameRepository.save(game);
    }

    // Single item

    @GetMapping("/games/{id}")
    public EntityModel<Game> getGame(@PathVariable Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));
        return assembler.toModel(game);
    }

    @PutMapping("/games/{id}")
    public Game updateGame(@PathVariable Long id, @RequestBody Game game) {
        return gameRepository.findById(id).map(gameToUpdate -> {
                    gameToUpdate.setTitle(game.getTitle());
                    gameToUpdate.setGenre(game.getGenre());
                    gameToUpdate.setPlatform(game.getPlatform());
                    return gameRepository.save(gameToUpdate);
            }).orElseGet(() -> {
                game.setId(id);
                return gameRepository.save(game);
            });
    }

    @DeleteMapping("/games/{id}")
    public void deleteGame(@PathVariable Long id) {
        gameRepository.deleteById(id);
    }
}
