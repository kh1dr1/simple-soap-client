package lt.vikoeif.pi24.simple_soap_client;

public final class Logger {
    public static void logVerboseMessage(String title, String... message) {
        logTitle(title);

        for (String msg : message) {
            System.out.println(msg);
        }

        System.out.print("\n");
    }

    public static void logTitle(String title) {
        System.out.print("\n");
        System.out.println("======== " + title + " ========");
    }
}