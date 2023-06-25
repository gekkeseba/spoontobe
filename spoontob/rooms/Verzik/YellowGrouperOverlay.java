package net.runelite.client.plugins.spoontob.rooms.Verzik;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Stroke;
import java.util.ArrayList;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.plugins.spoontob.RoomOverlay;
import net.runelite.client.plugins.spoontob.SpoonTobConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YellowGrouperOverlay extends RoomOverlay {
    private static final Logger log = LoggerFactory.getLogger(YellowGrouperOverlay.class);

    @Inject
    private Client client;

    @Inject
    private Verzik verzik;

    @Inject
    private SpoonTobConfig config;

    @Inject
    protected YellowGrouperOverlay(SpoonTobConfig config) {
        super(config);
    }

    public Dimension render(Graphics2D graphics) {
        if (this.verzik.yellowGroups.size() > 0 && this.config.showVerzikYellows() == SpoonTobConfig.verzikYellowsMode.GROUPS && this.verzik.yellowsOut && this.verzik.getVerzikNPC().getId() == 10852) {
            int group = 0;
            String text = String.valueOf(this.verzik.yellowTimer);
            if (this.config.yellowTicksOnPlayer() && this.client.getLocalPlayer() != null) {
                Point point = Perspective.getCanvasTextLocation(this.client, graphics, this.client.getLocalPlayer().getLocalLocation(), "#", this.config.yellowsOffset());
                if (this.config.fontStyle()) {
                    renderTextLocation(graphics, text, Color.WHITE, point);
                } else {
                    renderSteroidsTextLocation(graphics, text, this.config.yellowsSize(), 1, Color.WHITE, point);
                }
            }
            for (ArrayList<WorldPoint> list : this.verzik.yellowGroups) {
                for (WorldPoint next : list) {
                    LocalPoint localPoint = LocalPoint.fromWorld(this.client, next);
                    if (localPoint != null) {
                        Color fill;
                        Polygon poly = Perspective.getCanvasTilePoly(this.client, localPoint);
                        if (poly == null)
                            continue;
                        Color color = Color.BLACK;
                        graphics.setColor(color);
                        Stroke originalStroke = graphics.getStroke();
                        graphics.setStroke(new BasicStroke(2.0F));
                        graphics.draw(poly);
                        switch (group) {
                            case 0:
                                fill = Color.RED;
                                break;
                            case 1:
                                fill = Color.BLUE;
                                break;
                            case 2:
                                fill = Color.GREEN;
                                break;
                            case 3:
                                fill = Color.MAGENTA;
                                break;
                            case 4:
                                fill = Color.ORANGE;
                                break;
                            default:
                                fill = new Color(250, 50, 100);
                                break;
                        }
                        Color realFill = new Color(fill.getRed(), fill.getGreen(), fill.getBlue(), 130);
                        graphics.setColor(realFill);
                        graphics.fill(poly);
                        graphics.setStroke(originalStroke);
                        if (!this.config.yellowTicksOnPlayer()) {
                            Point point = Perspective.getCanvasTextLocation(this.client, graphics, localPoint, text, 0);
                            if (this.config.fontStyle()) {
                                renderTextLocation(graphics, text, Color.WHITE, point);
                                continue;
                            }
                            renderResizeTextLocation(graphics, text, 12, 1, Color.WHITE, point);
                        }
                    }
                }
                group++;
            }
        }
        return null;
    }
}
