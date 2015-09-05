import com.sun.jna.Library;
import com.sun.jna.Native;


public interface SkiaRenderLibrary extends Library {
    int FONT_TYPE_NORMAL = 0;
    int FONT_TYPE_BOLD = 0x01;
    int FONT_TYPE_ITALIC = 0x02;
            SkiaRenderLibrary INSTANCE =
                    (SkiaRenderLibrary) Native.loadLibrary(
                            SkiaRenderLibrary.class.getProtectionDomain().getCodeSource().getLocation().getPath() +
                                    "libskrender.dylib", SkiaRenderLibrary.class);
    void skrender(String font, String str, int color, int fontSize, boolean antiAliasing, int fontStyle, int x, int y,
                  int[] data, int width, int height);
}
