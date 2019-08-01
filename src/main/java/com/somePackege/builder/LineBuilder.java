package com.somePackege.builder;


import java.util.List;
import java.util.Map;

public class LineBuilder {
    private Long id;

    private String name;

    private Map<String, List<String>> tags;

    private Long aggregationInterval;

    private String aggregationFunction;


    public static LineBuilder newBuilder() {
        return new LineBuilder();
    }


    public LineBuilder withLine(final Line line) {
        this.id = line.getId();
        this.name = line.getName();
        this.tags = line.getTags();
        this.aggregationInterval = line.getAggregationInterval();
        this.aggregationFunction = line.getAggregationFunction();

        return this;
    }


    public LineBuilder withId(final Long id) {
        this.id = id;
        return this;
    }


    public LineBuilder withName(final String name) {
        this.name = name;
        return this;
    }


    public LineBuilder withTags(final Map<String, List<String>> tags) {
        this.tags = tags;
        return this;
    }


    public LineBuilder withAggregationInterval(final Long aggregationInterval) {
        this.aggregationInterval = aggregationInterval;
        return this;
    }


    public LineBuilder withAggregationFunction(final String aggregationFunction) {
        this.aggregationFunction = aggregationFunction;
        return this;
    }


    public Line build() {
        return new Line(id, name, tags, aggregationInterval, aggregationFunction);
    }
}
