package com.springpageable.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.info.GitInfoContributor;
import org.springframework.boot.info.GitProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HistoryLinkProvidingGitInfoContributor extends GitInfoContributor {

  @Value("${git.url}")
  public String gitHubUrl;

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
      content.put(KEY_FOR_LINK, gitHubUrl + commitId);
    }
  }
}
