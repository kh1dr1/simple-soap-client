package lt.vikoeif.pi24.simple_soap_client;

public final class Logger {
    public static void logVerboseMessage(String title, String... message) {
        System.out.println("\n");
        System.out.println("======== " + title + " ========");

        for (String msg : message) {
            System.out.println(msg);
        }

        System.out.println("\n");
    }
}