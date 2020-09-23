package com.example.popularmovies.models;

import java.util.List;

public class MovieTrailer {

  private int id;
  private List<ResultsBean> results;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public List<ResultsBean> getResults() {
    return results;
  }

  public void setResults(List<ResultsBean> results) {
    this.results = results;
  }

  public static class ResultsBean {

    private String id;
    private String key;
    private String name;
    private String site;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getKey() {
      return key;
    }

    public void setKey(String key) {
      this.key = key;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getSite() {
      return site;
    }

    public void setSite(String site) {
      this.site = site;
    }
  }
}
