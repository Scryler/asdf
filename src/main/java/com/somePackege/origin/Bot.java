package com.somePackege.origin;

import com.somePackege.origin.events.HelloEvent;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class Bot {

    static void init() throws Exception{
        JDA jda = new JDABuilder("NjA2NDQxMDcxNjk3NzIzNDAy.XULGcw.4GTwSuD6B9CeKcfuu7ro6mgkh5o").build();
        jda.addEventListener(new HelloEvent("test"));
    }

    public static void main(String[] args) {
        try {
            init();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
