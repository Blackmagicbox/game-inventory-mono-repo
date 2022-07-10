package com.api.gameinventory.backend.game;

import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {
    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    // aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/games")
    public Iterable<Game> getAllGames() {
        return gameRepository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/games")
    public Game addNewGame(@RequestBody Game game) {
        return gameRepository.save(game);
    }

    // Single item

    @GetMapping("/games/{id}")
    public Game getGame(@PathVariable Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));
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
