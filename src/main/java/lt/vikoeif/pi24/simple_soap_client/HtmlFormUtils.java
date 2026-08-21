package lt.vikoeif.pi24.simple_soap_client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public final class HtmlFormUtils {
    private static final Logger _logger = LoggerFactory.getLogger(HtmlFormUtils.class);

//    public static String parseTextFormInput(String rawFormData) {
//        if (rawFormData == null || rawFormData.isBlank()) {
//            return null;
//        } else {
//            return rawFormData;
//        }
//    }

    /**
     * Try to parse an integer value from an HTML form.
     * @param rawFormData raw text from a form input
     * @return an Optional wrapping an Integer
     */
    public static Optional<Integer> parseIntFormInput(String rawFormData) {
        if (rawFormData == null || rawFormData.isBlank()) {
            return Optional.empty();
        }

        int parsedInteger;
        try {
            parsedInteger = Integer.parseInt(rawFormData);
        } catch (NumberFormatException e) {
            _logger.warn("Cannot parse integer form input: {}", e.getMessage());
            return Optional.empty();
        }
        return Optional.of(parsedInteger);
    }
}
