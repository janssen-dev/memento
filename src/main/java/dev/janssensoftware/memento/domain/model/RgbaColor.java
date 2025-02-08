package dev.janssensoftware.memento.domain.model;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RgbaColor {

    private int red;
    private int green;
    private int blue;
    private int alpha;

    public static RgbaColor fromHex(String hex) {
        if (!hex.matches("^#([A-Fa-f0-9]{8})$")) {
            throw new IllegalArgumentException("Invalid RGBA HEX color format: " + hex);
        }
        return new RgbaColor(
                Integer.valueOf(hex.substring(1, 3), 16),
                Integer.valueOf(hex.substring(3, 5), 16),
                Integer.valueOf(hex.substring(5, 7), 16),
                Integer.valueOf(hex.substring(7, 9), 16)
        );
    }

    public String toHex() {
        return String.format("#%02X%02X%02X%02X", red, green, blue, alpha);
    }
}
