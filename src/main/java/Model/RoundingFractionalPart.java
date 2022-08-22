package Model;

import java.text.DecimalFormat;

public interface RoundingFractionalPart {
    /**
     *
     * @return returns which rounds the number to the specified number after the decimal point
     */
    default DecimalFormat changingTheFractionalPart(int numberOfDecimalPlaces) {
        StringBuilder pattern = new StringBuilder("#.");
        for (int a = 0; a < numberOfDecimalPlaces; a++) {
            pattern.append("#");
        }

        return new DecimalFormat(pattern.
                toString());
    }
}
