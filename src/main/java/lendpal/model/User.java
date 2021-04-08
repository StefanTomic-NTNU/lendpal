package lendpal.model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.binary.Hex;

/**
 * Users may lend Items using LendPal
 */
public class User {

    private enum Privileges {
        NONMEMBER,
        MEMBER,
        MODERATOR,
        ADMINISTRATOR
    }

    private String firstName;
    private String lastName;
    private String email;
    private int id;

    /**
     * This valid email pattern is not 100% correct but is sufficient.
     */
    private final static Pattern validEmailPattern = Pattern.compile(
            "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    /**
     * Passwords are hashed using salts during construction of User objects.
     */
    private String password;
    private byte[] salt;
    private Privileges privileges;

    public User(int id, String firstName, String lastName, String email, String password, Privileges privileges) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.setEmail(email);
        this.salt = generateSalt();
        this.password = hashPassword(password, this.salt);
        this.privileges = privileges;
        this.id = id;
    }

    public User(String firstName, String lastName, String email, String password, Privileges privileges) {
        this(1, firstName, lastName, email, password, Privileges.MEMBER);
    }

    public User(String firstName, String lastName, String email, String password) {
        this(firstName, lastName, email, password, Privileges.MEMBER);
    }

    private boolean verifyEmail(String email) { return validEmailPattern.matcher(email).matches(); }

    /**
     * Generates a random salt of size 16 bytes.
     * @return Salt as byte array
     */
    private static byte[] generateSalt() {
        byte[] bytes = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(bytes);
        return bytes;
    }

    /**
     * Hashes password using SHA-256 and given salt
     * @param password Unhashed password
     * @param salt as byte array
     * @return Hashed password as String
     */
    public static String hashPassword(String password, byte[] salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(salt);
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public boolean checkIfPasswordIsCorrect(String password) {
        String hashedPassword = hashPassword(password, this.salt);
        return hashedPassword.equals(this.password);
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        if (!this.verifyEmail(email)) {
            throw new IllegalArgumentException("Email " + email + " is invalid.");
        } else {
            this.email = email;
        }
    }

    public byte[] getSalt() { return salt; }

    public void setSalt(byte[] salt) { this.salt = salt; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Privileges getPrivileges() { return privileges; }

    public void setPrivileges(Privileges privileges) { this.privileges = privileges; }
}