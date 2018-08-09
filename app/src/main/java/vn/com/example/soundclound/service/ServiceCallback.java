package vn.com.example.soundclound.service;

public interface ServiceCallback {
    void postName(String songName,String author);

    void postTotalTime(long totalTime);

    void postCurentTime(long currentTime);

    void postPauseButon();

    void postStartButton();

    void postShuffle(boolean isShuffle);

    void postLoop(boolean isLoop);

    void showError(String error);
}
