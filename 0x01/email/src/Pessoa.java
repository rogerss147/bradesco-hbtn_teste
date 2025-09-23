public class Pessoa {

    public static boolean emailValid(String email) {
        if (email == null) {
            return false;
        }
        if (!email.contains("@")) {
            return false;
        }
        return email.length() <= 50;
    }
}

