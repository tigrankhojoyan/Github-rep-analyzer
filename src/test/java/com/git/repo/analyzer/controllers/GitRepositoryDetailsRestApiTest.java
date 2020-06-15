package com.git.repo.analyzer.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.git.repo.analyzer.model.GitRepository;
import com.git.repo.analyzer.model.GitRepositoryDetails;
import com.git.repo.analyzer.model.Owner;
import com.git.repo.analyzer.services.GitRepositoryDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GitRepositoryDetailsRestApi.class)
class GitRepositoryDetailsRestApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GitRepositoryDetailsService gitRepositoryDetailsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllRepositoryDetailsTest() throws Exception {
        GitRepositoryDetails gitRepositoryDetails1 = new GitRepositoryDetails("15555", new GitRepository("repoDescription 1", "reponame 1", "https://aa.com", "String cloneUrl", new Owner()), new ArrayList<>(), new ArrayList<>());
        GitRepositoryDetails gitRepositoryDetails2 = new GitRepositoryDetails("15556", new GitRepository("repoDescription 2", "reponame 2", "https://bb.com", "String cloneUrl", new Owner()), new ArrayList<>(), new ArrayList<>());
        List<GitRepositoryDetails> repositoryDetails = Arrays.asList(gitRepositoryDetails1, gitRepositoryDetails2);
        when(gitRepositoryDetailsService.getAllGitRepositoryDetails()).thenReturn(repositoryDetails);
        MvcResult mvcResult = this.mockMvc.perform(get("/api/repodetails/getall")).andDo(print()).andExpect(status().isOk()).andReturn();

        List<GitRepositoryDetails> actualResponseBody = this.objectMapper.readValue(
                mvcResult.getResponse().getContentAsByteArray(),
                new TypeReference<List<GitRepositoryDetails>>(){});
        assertEquals(2, actualResponseBody.size());
        assertEquals(repositoryDetails, actualResponseBody);
    }

    @Test
    public void removeRepositoryDetailsIdTest() throws Exception {
        GitRepositoryDetails gitRepositoryDetails = new GitRepositoryDetails("15555", new GitRepository("repoDescription 1", "reponame 1", "https://aa.com", "String cloneUrl", new Owner()), new ArrayList<>(), new ArrayList<>());
        when(gitRepositoryDetailsService.findById("15555")).thenReturn(gitRepositoryDetails);
        MvcResult mvcResult = this.mockMvc.perform(get("/api/repodetails/15555")).
                andDo(print()).andExpect(status().isOk()).andReturn();
        GitRepositoryDetails actualResponseBody = this.objectMapper.readValue(
                mvcResult.getResponse().getContentAsByteArray(),
                new TypeReference<GitRepositoryDetails>(){});
        assertEquals(gitRepositoryDetails, actualResponseBody);
    }

    @Test
    public void getRepositoryDetailsIdTest() throws Exception {
        doNothing().when(gitRepositoryDetailsService).deleteRepositoryDetailsById("11");
        this.mockMvc.perform(delete("/api/repodetails/delete/11")).
                andDo(print()).andExpect(status().isOk());
    }

//    @Test
//    public void testSave() throws URISyntaxException {
//        RestTemplate restTemplate = new RestTemplate();
//        final String baseUrl = "http://localhost:" + randomServerPort + "/api/repodetails/save";
//        URI uri = new URI(baseUrl);
//
//        GitRepositoryDetails gitRepositoryDetails = new GitRepositoryDetails("15555", new GitRepository(), null, null);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("X-COM-PERSIST", "true");
//
//        HttpEntity<GitRepositoryDetails> request = new HttpEntity<>(gitRepositoryDetails, headers);
//
//        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
//
//        //Verify request succeed
//        Assertions.assertEquals(201, result.getStatusCodeValue());
//    }
//
//
//    @Test
//    void getAllRepositoryDetails() {
//        GitRepositoryDetails gitRepositoryDetails1 = new GitRepositoryDetails("15555", new GitRepository("repoDescription 1", "reponame 1", "https://aa.com", "String cloneUrl", new Owner()), new ArrayList<>(), new ArrayList<>());
//        GitRepositoryDetails gitRepositoryDetails2 = new GitRepositoryDetails("15556", new GitRepository("repoDescription 2", "reponame 2", "https://bb.com", "String cloneUrl", new Owner()), new ArrayList<>(), new ArrayList<>());
//        when(gitRepositoryDetailsService.getAllGitRepositoryDetails()).thenReturn(new ArrayList(Arrays.asList(gitRepositoryDetails1, gitRepositoryDetails2)));
//        List<GitRepositoryDetails> repositoryDetails = gitRepositoryDetailsRestApi.getAllRepositoryDetails();
//        assertEquals(2, repositoryDetails.size());
//    }
//
//    @Test
//    void getRepositoryDetailsId() {
//        GitRepositoryDetails gitRepositoryDetails2 = new GitRepositoryDetails("15555", new GitRepository("repoDescription 1", "reponame 1", "https://aa.com", "String cloneUrl", new Owner()), new ArrayList<>(), new ArrayList<>());
//        when(gitRepositoryDetailsService.findById("15555")).thenReturn(gitRepositoryDetails2);
//        GitRepositoryDetails repositoryDetailsResponse = gitRepositoryDetailsRestApi.getRepositoryDetailsId("15555");
//        assertEquals(gitRepositoryDetails2, repositoryDetailsResponse);
//    }

}