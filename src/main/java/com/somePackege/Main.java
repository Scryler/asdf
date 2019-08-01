package com.somePackege;

import com.somePackege.builder.Line;
import com.somePackege.builder.LineBuilder;
import com.somePackege.originalPackege.SomeClass;

public class Main{

    void init(){

        SomeClass someClass = new SomeClass.Builder(1,2).build();

        Line line = new LineBuilder().withId(1L).withName("test").build();
    }
}
