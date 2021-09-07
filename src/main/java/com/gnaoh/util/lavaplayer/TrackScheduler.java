package com.gnaoh.util.lavaplayer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

public class TrackScheduler extends AudioEventAdapter {
    public final AudioPlayer player;
    public final BlockingQueue<AudioTrack> trackQueue;

    public TrackScheduler(AudioPlayer player) {
        this.player = player;
        trackQueue = new LinkedBlockingQueue<>();
    }

    public void nextTrack() {
        boolean noInterrupt = false;
        this.player.startTrack(trackQueue.poll(), noInterrupt);
    }

    public void queue(AudioTrack track) {
        boolean noInterrupt = true;
        if (!player.startTrack(track, noInterrupt)) {
            trackQueue.offer(track);
        }
    }

    @Override
    public void onPlayerPause(AudioPlayer player) {

    }

    @Override
    public void onPlayerResume(AudioPlayer player) {

    }

    @Override
    public void onTrackStart(AudioPlayer player, AudioTrack track) {

    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if (endReason.mayStartNext) {
            nextTrack();
        }
    }
}
