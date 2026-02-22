package com.register.backend.rest.controller;

import com.register.backend.rest.dto.PlayerFineDto;
import com.register.backend.service.PlayerFineService;
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
@RequestMapping("/api/player-fines")
@Tag(name = "PlayerFines", description = "CRUD endpoints for PlayerFine (Player ↔ Fine)")
public class PlayerFineController {

    private final PlayerFineService playerFineService;

    public PlayerFineController(PlayerFineService playerFineService) {
        this.playerFineService = playerFineService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PENALTY_TREASURER')")
    @Operation(summary = "Get all player fines", description = "Returns a list of all player-fine entries.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlayerFineDto.class)))
    })
    public List<PlayerFineDto> getAll() {
        return playerFineService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PENALTY_TREASURER')")
    @Operation(summary = "Get player fine by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlayerFineDto.class))),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public PlayerFineDto getById(@Parameter(description = "PlayerFine id", example = "1") @PathVariable Integer id) {
        return playerFineService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PENALTY_TREASURER')")
    @Operation(summary = "Create player fine")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlayerFineDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<PlayerFineDto> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "PlayerFine payload (id can be null). playerId and fineId are required.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = PlayerFineDto.class))
            )
            @RequestBody PlayerFineDto body
    ) {
        PlayerFineDto created = playerFineService.create(body);

        return ResponseEntity
                .created(URI.create("/api/player-fines/" + created.getId()))
                .body(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PENALTY_TREASURER')")
    @Operation(summary = "Update player fine (full update)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlayerFineDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public PlayerFineDto update(
            @Parameter(description = "PlayerFine id", example = "1") @PathVariable Integer id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Full PlayerFine payload. playerId and fineId are required.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = PlayerFineDto.class))
            )
            @RequestBody PlayerFineDto body
    ) {
        return playerFineService.update(id, body);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Delete player fine")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deleted"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<Void> delete(@Parameter(description = "PlayerFine id", example = "1") @PathVariable Integer id) {
        playerFineService.delete(id);
        return ResponseEntity.noContent().build();
    }
}