package com.marl0vv.study.api.entries;

import com.marl0vv.study.Entry;
import com.marl0vv.study.Storage;
import com.marl0vv.study.api.entries.dto.CreateEntryDTO;
import com.marl0vv.study.api.entries.dto.EntryDTO;
import com.marl0vv.study.api.entries.dto.UpdateEntryDTO;
import com.marl0vv.study.exception.NotFoundException;
import com.marl0vv.study.service.Service;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWebTestClient
@ExtendWith(SoftAssertionsExtension.class)
public class EntryControllerIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private Storage storage;

    @Autowired
    private Service service;

    @BeforeEach
    public void setupData() {
        Entry entry1 = new Entry(0, "Entry Test", "Description 1", "Link 1");
        Entry entry2 = new Entry(1, "Entry Test", "Description 2", "Link 2");

        storage.create(entry1);
        storage.create(entry2);
    }

    @Test
    void testCreate(SoftAssertions assertions) {
        //Arrange
        CreateEntryDTO newEntry = new CreateEntryDTO("Entry Create", "Description Test", "link Test");

        //Act
        EntryDTO response = webTestClient.post()
                .uri("api/entries/create")
                .contentType(APPLICATION_JSON)
                .bodyValue(newEntry)
                .exchange()
                .expectStatus().isOk()
                .expectBody(EntryDTO.class)
                .returnResult()
                .getResponseBody();

        //Assert
        assertions.assertThat(response.getName()).isEqualTo(newEntry.getName());
        assertions.assertThat(response.getDescription()).isEqualTo(newEntry.getDescription());
        assertions.assertThat(response.getLink()).isEqualTo(newEntry.getLink());
    }

    @Test
    void testGetById(SoftAssertions assertions) {
        //Arrange
        int id = 0;

        //Act
        EntryDTO response = webTestClient.get()
                .uri("api/entries/{id}", id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(EntryDTO.class)
                .returnResult()
                .getResponseBody();

        //Assert
        assertions.assertThat(response.getId()).isEqualTo(id);
    }

    @Test
    void testGetByName(SoftAssertions assertions) {
        //Arrange
        String name = "Entry Test";

        //Act & Assert
        webTestClient.get()
                .uri("api/entries/search?name={name}", name)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$.length()").isEqualTo(2)
                .jsonPath("$[0].name").isEqualTo(name)
                .jsonPath("$[0].description").isEqualTo("Description 1")
                .jsonPath("$[0].link").isEqualTo("Link 1")
                .jsonPath("$[1].name").isEqualTo(name)
                .jsonPath("$[1].description").isEqualTo("Description 2")
                .jsonPath("$[1].link").isEqualTo("Link 2");
    }

    @Test
    void testDeleteById() {
        //Arrange
        int id = 1;

        //Act
        webTestClient.delete()
                .uri("api/entries/{id}", id)
                .exchange()
                .expectStatus().isOk();

        //Assert
        assertThatThrownBy(() -> service.getEntryById(id))
                .isInstanceOf(NotFoundException.class);

    }

    @Test
    void testUpdateById(SoftAssertions assertions){
        //Arrange
        int id = 0;
        UpdateEntryDTO updateEntryDTO = new UpdateEntryDTO("EntryUpdateTest", "Description Update", "Link Update");

        //Act
        webTestClient.put()
                .uri("/api/entries/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updateEntryDTO)
                .exchange()
                .expectStatus().isOk();

        Entry changedEntry = storage.findByIdOrNull(0);
        assertions.assertThat(updateEntryDTO.getName()).isEqualTo(changedEntry.getName());
        assertions.assertThat(updateEntryDTO.getDescription()).isEqualTo(changedEntry.getDescription());
        assertions.assertThat(updateEntryDTO.getLink()).isEqualTo(changedEntry.getLink());
    }
}
