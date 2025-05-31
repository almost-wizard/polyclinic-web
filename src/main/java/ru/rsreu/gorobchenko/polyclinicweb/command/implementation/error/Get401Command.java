package ru.rsreu.gorobchenko.polyclinicweb.command.implementation.error;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionCommand;
import ru.rsreu.gorobchenko.polyclinicweb.command.Page;

import java.io.IOException;

public class Get401Command extends ActionCommand {
    public Get401Command(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        forward(Page.UNAUTHORIZED);
    }
}
