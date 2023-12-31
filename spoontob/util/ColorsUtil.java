package net.runelite.client.plugins.spoontob.util;

import java.awt.Color;

public class ColorsUtil {
    public static int RGBtoRS2HSB(int r, int g, int b) {
        float[] HSB = Color.RGBtoHSB(r, g, b, (float[])null);
        float hue = HSB[0];
        float saturation = HSB[1];
        HSB[2] = HSB[2] - Math.min(hue, HSB[2] / 2.0F);
        float brightness = HSB[2];
        int encode_hue = (int)(hue * 63.0F);
        int encode_saturation = (int)(saturation * 7.0F);
        int encode_brightness = (int)(brightness * 127.0F);
        return (encode_hue << 10) + (encode_saturation << 7) + encode_brightness;
    }

    public static int RS2HSBtoRGB(int rs2HSB) {
        int decode_hue = rs2HSB >> 10 & 0x3F;
        int decode_saturation = rs2HSB >> 7 & 0x7;
        int decode_brightness = rs2HSB & 0x7F;
        float hue = decode_hue / 63.0F;
        float saturation = decode_saturation / 7.0F;
        float brightness = decode_brightness / 127.0F;
        return Color.HSBtoRGB(hue, saturation, brightness);
    }
}
