package com.marl0vv.study.api.entries.mapper;

import com.marl0vv.study.Entry;
import com.marl0vv.study.api.entries.dto.CreateEntryDTO;
import com.marl0vv.study.api.entries.dto.EntryDTO;
import com.marl0vv.study.api.entries.dto.UpdateEntryDTO;
import com.marl0vv.study.service.argument.CreateEntryArgument;
import com.marl0vv.study.service.argument.UpdateEntryArgument;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface EntryMapper {
    EntryDTO toDto(Entry entry);

    List<EntryDTO> toDtoList(List<Entry> entries);

    CreateEntryArgument toCreateArgument(CreateEntryDTO dto);

    UpdateEntryArgument toUpdateArgument(UpdateEntryDTO dto);
}
