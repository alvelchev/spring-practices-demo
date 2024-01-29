package com.springpracticesdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.GitProperties;

import com.springpracticesdemo.configuration.HistoryLinkProvidingGitInfoContributor;

/**
 * Copyright (c) 2023 Robert Bosch GmbH. All rights reserved. Created by VEA3SF on 17.7.2023 г..
 */
class HistoryLinkProvidingGitInfoContributorTest {

    private static final String GIT_URL = "https://github.com/myrepo/";

    @Mock
    private GitProperties gitProperties;

    private HistoryLinkProvidingGitInfoContributor contributor;

    @Value("${git.url}")
    private String gitHubUrl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        contributor = new HistoryLinkProvidingGitInfoContributor(gitProperties);
    }

    @Test
    void postProcessContent_ShouldAddHistoryLink_WhenCommitIdExists() {
        // Arrange
        String commitId = "abcd1234";
        Map<String, Object> content = new HashMap<>();
        content.put("key1", "value1");

        // Set up mock behavior
        when(gitProperties.getShortCommitId()).thenReturn(commitId);

        // Act
        contributor.postProcessContent(content);

        // Assert
        assertEquals(content.get("history"), gitHubUrl + commitId);
        assertEquals("value1", content.get("key1"));
    }

    @Test
    void postProcessContent_ShouldNotAddHistoryLink_WhenCommitIdIsNull() {
        // Arrange
        Map<String, Object> content = new HashMap<>();
        content.put("key1", "value1");

        // Set up mock behavior
        when(gitProperties.getShortCommitId()).thenReturn(null);

        // Act
        contributor.postProcessContent(content);

        // Assert
        assertFalse(content.containsKey("history"));
        assertEquals("value1", content.get("key1"));
    }
}