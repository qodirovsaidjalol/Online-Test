package uz.qodirov.services;

import uz.qodirov.entity.user.User;

public class SessionService {
    public static User session;

    public static User getSession() {
        return session;
    }

    public static void setSession(User user) {
        session = user;
    }
}
