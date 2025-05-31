package ru.rsreu.gorobchenko.polyclinicweb.command.implementation.authorization;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionCommand;
import ru.rsreu.gorobchenko.polyclinicweb.command.Page;

import java.io.IOException;

public class GetLoginCommand extends ActionCommand {
    public GetLoginCommand(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        forward(Page.LOGIN);
    }
}
