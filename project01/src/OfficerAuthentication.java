import java.io.IOException;
import java.io.RandomAccessFile;


class OfficerAuthentication {
    private final String fileName;

    public OfficerAuthentication(String fileName) {
        this.fileName = fileName;
    }

    public boolean authenticate(String username, String password) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "r")) {
            String line;
            while ((line = raf.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
}