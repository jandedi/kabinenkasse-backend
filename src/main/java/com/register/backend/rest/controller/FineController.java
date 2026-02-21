package com.register.backend.rest.controller;

import com.register.backend.rest.dto.FineDto;
import com.register.backend.service.FineService;
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
@RequestMapping("/api/fines")
@Tag(name = "Fines", description = "CRUD endpoints for Fine")
public class FineController {

    private final FineService fineService;

    public FineController(FineService fineService) {
        this.fineService = fineService;
    }

    @GetMapping
    @Operation(summary = "Get all fines", description = "Returns a list of all fines.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FineDto.class)))
    })
    public List<FineDto> getAll() {
        return fineService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get fine by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FineDto.class))),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public FineDto getById(@Parameter(description = "Fine id", example = "1") @PathVariable Integer id) {
        return fineService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create fine")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FineDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<FineDto> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Fine payload (id can be null)",
                    required = true,
                    content = @Content(schema = @Schema(implementation = FineDto.class))
            )
            @RequestBody FineDto body
    ) {
        FineDto created = fineService.create(body);

        return ResponseEntity
                .created(URI.create("/api/fines/" + created.getId()))
                .body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update fine (full update)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FineDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public FineDto update(
            @Parameter(description = "Fine id", example = "1") @PathVariable Integer id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Full fine payload",
                    required = true,
                    content = @Content(schema = @Schema(implementation = FineDto.class))
            )
            @RequestBody FineDto body
    ) {
        return fineService.update(id, body);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete fine")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deleted"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<Void> delete(@Parameter(description = "Fine id", example = "1") @PathVariable Integer id) {
        fineService.delete(id);
        return ResponseEntity.noContent().build();
    }

}