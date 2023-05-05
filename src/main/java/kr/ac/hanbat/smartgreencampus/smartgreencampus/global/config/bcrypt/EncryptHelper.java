package kr.ac.hanbat.smartgreencampus.smartgreencampus.global.config.bcrypt;

public interface EncryptHelper {
    String encrypt(String password);
    boolean isMatch(String password, String hashed);
}
