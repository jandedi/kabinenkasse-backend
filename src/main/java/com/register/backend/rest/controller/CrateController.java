package com.register.backend.rest.controller;

import com.register.backend.rest.dto.CrateDto;
import com.register.backend.service.CrateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/crates")
@Tag(name = "Crates", description = "CRUD endpoints for Crate")
public class CrateController {

    private final CrateService crateService;

    public CrateController(CrateService crateService) {
        this.crateService = crateService;
    }

    @GetMapping
    @Operation(summary = "Get all crates", description = "Returns a list of all crates.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CrateDto.class)))
    })
    public List<CrateDto> getAll() {
        return crateService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get crate by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CrateDto.class))),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public CrateDto getById(@Parameter(description = "Crate id", example = "1") @PathVariable Integer id) {
        return crateService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create crate")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CrateDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<CrateDto> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Crate payload (id can be null). playerId is required.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CrateDto.class))
            )
            @RequestBody CrateDto body
    ) {
        CrateDto created = crateService.create(body);

        return ResponseEntity
                .created(URI.create("/api/crates/" + created.getId()))
                .body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update crate (full update)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CrateDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public CrateDto update(
            @Parameter(description = "Crate id", example = "1") @PathVariable Integer id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Full crate payload. playerId is required.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CrateDto.class))
            )
            @RequestBody CrateDto body
    ) {
        return crateService.update(id, body);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete crate")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deleted"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<Void> delete(@Parameter(description = "Crate id", example = "1") @PathVariable Integer id) {
        crateService.delete(id);
        return ResponseEntity.noContent().build();
    }
}