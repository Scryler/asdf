package com.somePackege.origin.events;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class HelloEvent extends ListenerAdapter {

    private String message;

    public HelloEvent(String message) {
        this.message = message;
    }

    public HelloEvent() {
        this.message = "test";
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        final String rawMessage = event.getMessage().getContentRaw();
        if (rawMessage.equalsIgnoreCase("hello")) {
            event.getChannel().sendMessage(message).queue();
        }
    }

}
