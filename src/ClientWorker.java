import java.security.MessageDigest;
class ClientWorker implements Runnable {
    private byte[] hash;
    private int start;
    private int end;
    public ClientWorker(byte[] hash, int start, int end) {
        this.hash = hash;
        this.start = start;
        this.end = end;
    }

    public void run() {
        for (int i = start; i <= end; i++) {
            String attempt = String.valueOf(i);

            // check if attempt matches hash
            MessageDigest md;
            try {
                md = MessageDigest.getInstance("MD5");
                byte[] attemptHash = md.digest(attempt.getBytes());
                if (MessageDigest.isEqual(attemptHash, hash)) {
                    System.out.println("Password cracked: " + attempt);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Failed to crack password.");
    }
}
