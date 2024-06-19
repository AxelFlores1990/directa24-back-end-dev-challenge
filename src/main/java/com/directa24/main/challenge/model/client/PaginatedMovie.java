package com.directa24.main.challenge.model.client;

import com.directa24.main.challenge.model.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PaginatedMovie {

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("per_page")
    private Integer perPage;

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("total_pages")
    private Integer totalPages;

    @JsonProperty("data")
    private List<Movie> data;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Movie> getData() {
        return data;
    }

    public void setData(List<Movie> data) {
        this.data = data;
    }
}
