package de.awtools.string;

public class UserService {

    public void addUser(Nickname nickname) {
        StringBuilder sb = new StringBuilder();
        sb.append(nickname);
        System.out.printf("%s / %s", nickname, sb.toString());
    }
}
