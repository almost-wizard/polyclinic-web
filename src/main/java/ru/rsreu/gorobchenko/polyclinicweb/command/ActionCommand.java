package ru.rsreu.gorobchenko.polyclinicweb.command;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public abstract class ActionCommand {
    protected final HttpServletRequest request;
    protected final HttpServletResponse response;

    public ActionCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public abstract void execute() throws ServletException, IOException;

    protected void forward(Page page) {
        _forward(page, request, response);
    }

    protected void redirect(Command command) {
        _redirect(command, response);
    }

    public static void forward(Page page, HttpServletRequest request, HttpServletResponse response) {
        _forward(page, request, response);
    }

    public static void redirect(Command command, HttpServletResponse response) {
        _redirect(command, response);
    }

    private static void _forward(Page page, HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(page.get());
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void _redirect(Command command, HttpServletResponse response) {
        try {
            response.sendRedirect(command.constructUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
