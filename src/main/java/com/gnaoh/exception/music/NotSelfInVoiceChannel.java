package com.gnaoh.exception.music;

public class NotSelfInVoiceChannel extends Exception {
    public NotSelfInVoiceChannel() {
        super("`Tao chưa vào voice channel. Ok!`");
    }
}
