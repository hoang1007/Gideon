package com.gnaoh.exception;

public class NoMemberInVoiceChannel extends Exception {
    public NoMemberInVoiceChannel() {
        super("`Bạn vào voice channel trước đi rồi tôi vào!`");
    }
}
