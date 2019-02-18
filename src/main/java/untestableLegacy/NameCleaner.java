package untestableLegacy;

public class NameCleaner {
    static String removeIllegalChars(Log log, String fromName, char[] illegalEntityNameChars) {
        final char[] chars = fromName.toCharArray();
        final StringBuilder newName = new StringBuilder();
        boolean nameChanged = false;
        for (char c : chars) {
            boolean hasIllegalChar = false;
            for (char illegalChar : illegalEntityNameChars) {
                if (c == illegalChar) {
                    hasIllegalChar = true;
                    break;
                }
            }
            if (hasIllegalChar) {
                nameChanged = true;
                continue;
            }
            newName.append(c);
        }
        if (nameChanged) {
            log.warn("The entity name " + fromName
                    + " contains illegal characters, it has been repaired to " + newName);
        } else {
            // check for other less normal characters
            for (char c : fromName.trim().toCharArray()) {
                final boolean normalChar = ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9')
                        || ('a' <= c && c <= 'z') || c == '_';
                if (!normalChar) {
                    log.warn("The entity name " + fromName + " contains a character (" + c
                            + ") which could result in issues in HQL or "
                            + "webservices. Use characters from a to z, A to Z or 0 to 9 or the _");
                }
            }
        }
        return newName.toString();
    }
}