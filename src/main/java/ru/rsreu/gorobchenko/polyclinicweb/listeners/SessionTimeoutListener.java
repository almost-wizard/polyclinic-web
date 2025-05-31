package ru.rsreu.gorobchenko.polyclinicweb.listeners;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import ru.rsreu.gorobchenko.polyclinicweb.logic.UserLogic;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.NullUser;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.User;

public class SessionTimeoutListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent event) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        User user = UserLogic.fromSession(event.getSession());

        if (user == NullUser.getInstance()) {
            return;
        }

        UserLogic.logout(user.getId());
        event.getSession().invalidate();
    }
}
