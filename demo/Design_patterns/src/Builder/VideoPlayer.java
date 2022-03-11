package Builder;

import java.util.List;

/**
 * 具体产品
 */
class VideoPlayer {
    private String menu;
    private String playLog;
    private String mainWindow;
    private String controller;

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getPlayLog() {
        return playLog;
    }

    public void setPlayLog(String playLog) {
        this.playLog = playLog;
    }

    public String getMainWindow() {
        return mainWindow;
    }

    public void setMainWindow(String mainWindow) {
        this.mainWindow = mainWindow;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }
}
/**
 * 抽象工厂
 */
abstract class VideoPlayerFactory {
    protected VideoPlayer videoPlayer = new VideoPlayer();

    public abstract void createMenu();
    public abstract void createPlayLog();
    public abstract void createMainWindow();
    public abstract void createController();

    public boolean isMenu() {return true;}
    public boolean isPlayLog() {return true;}
    public boolean isMainWindow() {return true;}
    public boolean isController() {return true;}

    public VideoPlayer createVideoPlayer() {return videoPlayer;}
}
/**
 * 具体工厂：完整模式、精简模式、记忆模式
 */
class CompeteModel extends VideoPlayerFactory {
    @Override
    public void createMenu() {
        videoPlayer.setMenu("完整模式菜单");
        System.out.println("完整模式菜单");
    }
    @Override
    public void createPlayLog() {
        videoPlayer.setPlayLog("完整模式播放列表");
        System.out.println("完整模式播放列表");
    }
    @Override
    public void createMainWindow() {
        videoPlayer.setMainWindow("完整模式主窗口");
        System.out.println("完整模式主窗口");
    }
    @Override
    public void createController() {
        videoPlayer.setController("完整模式控制条");
        System.out.println("完整模式控制条");
    }
}
class SimpleModel extends VideoPlayerFactory {
    @Override
    public void createMenu() {
        videoPlayer.setMenu("精简模式菜单");
        System.out.println("精简模式菜单");
    }
    @Override
    public boolean isMenu() {
        return false;
    }
    @Override
    public void createPlayLog() {
        videoPlayer.setPlayLog("精简模式播放列表");
        System.out.println("精简模式播放列表");
    }
    @Override
    public boolean isPlayLog() {
        return false;
    }
    @Override
    public void createMainWindow() {
        videoPlayer.setMainWindow("精简模式主窗口");
        System.out.println("精简模式主窗口");
    }
    @Override
    public void createController() {
        videoPlayer.setController("精简模式控制条");
        System.out.println("精简模式控制条");
    }
}

/**
 * 记忆模式
 */
class RememberModel extends VideoPlayerFactory {
    @Override
    public void createMenu() {
        videoPlayer.setMenu("记忆模式菜单");
    }
    @Override
    public void createPlayLog() {
        videoPlayer.setPlayLog("记忆模式播放记录");
    }
    @Override
    public void createMainWindow() {
        videoPlayer.setMainWindow("记忆模式主窗口");
    }
    @Override
    public void createController() {
        videoPlayer.setController("记忆模式控制条");
    }
}
/**
 * 指挥者
 */
class Director {
    private VideoPlayerFactory videoPlayer = null;
    public void setVideoPlayer(VideoPlayerFactory videoPlayer) {
        this.videoPlayer = videoPlayer;
    }
    public Director(VideoPlayerFactory videoPlayer) {
        this.videoPlayer = videoPlayer;
    }
    public VideoPlayer construct() {
        if (videoPlayer.isMenu()) {
            videoPlayer.createMenu();
        }
        if (videoPlayer.isPlayLog()) {
            videoPlayer.createPlayLog();
        }
        if (videoPlayer.isMainWindow()) {
            videoPlayer.createMainWindow();
        }
        if (videoPlayer.isController()) {
            videoPlayer.createController();
        }
        return videoPlayer.createVideoPlayer();
    }
}
/**
 * 客户端
 */
class Client {
    public static void main(String[] args) {
        //完整模式
        VideoPlayerFactory competeModel = new CompeteModel();
        Director director = new Director(competeModel);
        director.construct();
        //精简模式
        VideoPlayerFactory simpleModel = new SimpleModel();
        Director director1 = new Director(simpleModel);
        director1.construct();
    }
}
