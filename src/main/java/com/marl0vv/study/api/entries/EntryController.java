package com.marl0vv.study.api.entries;

import com.marl0vv.study.Entry;
import com.marl0vv.study.api.entries.dto.CreateEntryDTO;
import com.marl0vv.study.api.entries.dto.EntryDTO;
import com.marl0vv.study.api.entries.dto.UpdateEntryDTO;
import com.marl0vv.study.api.entries.mapper.EntryMapper;
import com.marl0vv.study.service.Service;
import com.marl0vv.study.service.argument.CreateEntryArgument;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entries")
@RequiredArgsConstructor
public class EntryController {

    private final Service service;
    private final EntryMapper mapper;

    @GetMapping("{id}")
    @Operation(summary = "Get entry by id")
    @ApiResponse(description = "Entry not found", responseCode = "404")
    public EntryDTO getById(@PathVariable("id") int id) {
        return mapper.toDto(service.getEntryById(id));
    }

    @GetMapping("search")
    @Operation(summary = "Find all messages that correspond to a name")
    public List<EntryDTO> getByName(@RequestParam("name") String searchName) {
        List<Entry> entries = service.findEntriesByName(searchName);
        return mapper.toDtoList(entries);
    }

    @PostMapping("create")
    @Operation(summary = "Create new entry")
    public EntryDTO create(@RequestBody CreateEntryDTO dto) {
        CreateEntryArgument entry = mapper.toCreateArgument(dto);
        return mapper.toDto(service.createEntry(entry));
    }

    @PutMapping("{id}")
    @Operation(summary = "Change entry by id")
    public void updateById(@RequestBody @Valid UpdateEntryDTO dto, @PathVariable("id") int id){
        service.updateEntryById(mapper.toUpdateArgument(dto), id);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete entry by id")
    @ApiResponse(description = "Entry not found", responseCode = "404")
    public void deleteById(@PathVariable("id") int id){service.deleteEntryById(id);}
}
