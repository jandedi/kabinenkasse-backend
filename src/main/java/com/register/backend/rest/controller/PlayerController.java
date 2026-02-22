package com.register.backend.rest.controller;

import com.register.backend.rest.dto.PlayerDto;
import com.register.backend.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/players")
@Tag(name = "Players", description = "CRUD endpoints for Player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'BEER_TREASURER', 'PENALTY_TREASURER')")
    @Operation(summary = "Get all players", description = "Returns a list of all players.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlayerDto.class)))
    })
    public List<PlayerDto> getAll() {
        return playerService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'BEER_TREASURER', 'PENALTY_TREASURER')")
    @Operation(summary = "Get player by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlayerDto.class))),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public PlayerDto getById(@Parameter(description = "Player id", example = "1") @PathVariable Integer id) {
        return playerService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Create player")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlayerDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<PlayerDto> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Player payload (id can be null)",
                    required = true,
                    content = @Content(schema = @Schema(implementation = PlayerDto.class))
            )
            @RequestBody PlayerDto body
    ) {
        PlayerDto created = playerService.create(body);

        return ResponseEntity
                .created(URI.create("/api/players/" + created.getId()))
                .body(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Update player (full update)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlayerDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public PlayerDto update(
            @Parameter(description = "Player id", example = "1") @PathVariable Integer id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Full player payload",
                    required = true,
                    content = @Content(schema = @Schema(implementation = PlayerDto.class))
            )
            @RequestBody PlayerDto body
    ) {
        return playerService.update(id, body);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Delete player")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deleted"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<Void> delete(@Parameter(description = "Player id", example = "1") @PathVariable Integer id) {
        playerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/drink-counter/increment")
    @PreAuthorize("hasAnyRole('ADMIN', 'BEER_TREASURER')")
    @Operation(summary = "Increment drink counter", description = "Increases drinkCounter by 1.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlayerDto.class))),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public PlayerDto incrementDrinkCounter(@Parameter(description = "Player id", example = "1") @PathVariable Integer id) {
        return playerService.incrementDrinkCounter(id);
    }

    @PostMapping("/{id}/drink-counter/decrement")
    @PreAuthorize("hasAnyRole('ADMIN', 'BEER_TREASURER')")
    @Operation(summary = "Decrement drink counter", description = "Decreases drinkCounter by 1 (not below 0).")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlayerDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request (would go below 0)"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public PlayerDto decrementDrinkCounter(@Parameter(description = "Player id", example = "1") @PathVariable Integer id) {
        return playerService.decrementDrinkCounter(id);
    }

}