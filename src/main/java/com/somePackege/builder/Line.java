package com.somePackege.builder;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Line implements Serializable {
    private final Long id;

    private final String name;

    private final Map<String, List<String>> tags;

    private final Long aggregationInterval;

    private final String aggregationFunction;


    public Line(final Long id, final String name, final Map<String, List<String>> tags, final Long aggregationInterval,
                final String aggregationFunction) {
        this.id = id;
        this.name = name;
        this.tags = tags;
        this.aggregationInterval = aggregationInterval;
        this.aggregationFunction = aggregationFunction;
    }


    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public Map<String, List<String>> getTags() {
        return tags;
    }


    public Long getAggregationInterval() {
        return aggregationInterval;
    }


    public String getAggregationFunction() {
        return aggregationFunction;
    }


    @Override
    public String toString() {
        return "Line{" +
                "id=" + id +
                ", name=" + name +
                ", tags=" + tags +
                ", aggregationInterval=" + aggregationInterval +
                ", aggregationFunction=" + aggregationFunction +
                '}';
    }
}