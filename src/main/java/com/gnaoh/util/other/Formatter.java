package com.gnaoh.util.other;

public class Formatter {
    /**
     * 
     * @param duration
     * @return Time format h:mm:ss
     */
    public static String formatTime(long duration) {
        if (duration == Long.MAX_VALUE)
            return "LIVE";
        
        final long secondsPerHour = 3600;
        final long secondsPerMinute = 60;

        long seconds = Math.round(duration / 1000.0);

        long hours = seconds / secondsPerHour;
        seconds %= secondsPerHour;

        long minutes = seconds / secondsPerMinute;
        seconds %= secondsPerMinute;

        return (hours > 0 ? hours + ":" : "") + 
                (minutes < 10 ? "0" + minutes : minutes) +
                ":" +
                (seconds < 10 ? "0" + seconds : seconds);
    }
}
