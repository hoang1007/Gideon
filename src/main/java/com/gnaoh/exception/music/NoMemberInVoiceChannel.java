package com.gnaoh.exception.music;

public class NoMemberInVoiceChannel extends Exception {
    public NoMemberInVoiceChannel() {
        super("`Bạn vào voice channel trước đi rồi tôi vào!`");
    }
}
