package ru.rsreu.gorobchenko.polyclinicweb.command.implementation.error;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionCommand;
import ru.rsreu.gorobchenko.polyclinicweb.command.Page;

public class Get404Command extends ActionCommand {
    public Get404Command(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() {
        forward(Page.NOT_FOUND);
    }
}
