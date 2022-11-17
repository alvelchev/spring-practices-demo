package com.springpageable.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.GitInfoContributor;
import org.springframework.boot.info.GitProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HistoryLinkProvidingGitInfoContributor extends GitInfoContributor {

  public static final String GITHUB_ADDRESS =
      "https://github.com/alvelchev/spring-practices-demo/commit/";
  public static final String KEY_FOR_LINK = "history";

  @Autowired
  public HistoryLinkProvidingGitInfoContributor(GitProperties properties) {
    super(properties);
  }

  @Override
  protected void postProcessContent(Map<String, Object> content) {
    super.postProcessContent(content);

    final String commitId = getProperties().getShortCommitId();
    if (commitId != null) {
      content.put(KEY_FOR_LINK, GITHUB_ADDRESS + commitId);
    }
  }
}
