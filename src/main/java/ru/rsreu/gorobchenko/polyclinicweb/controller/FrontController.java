package ru.rsreu.gorobchenko.polyclinicweb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionCommand;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionFactory;

import java.io.IOException;

/**
 * The {@code FrontController} servlet acts as a central dispatcher for all requests to the application.
 * It receives all incoming HTTP requests (GET and POST) and delegates the processing to the appropriate
 * {@link ActionCommand}.
 */
public class FrontController extends HttpServlet {
    /**
     * Handles HTTP GET requests by delegating to the {@link #processRequest(HttpServletRequest, HttpServletResponse)} method.
     *
     * @param request  The HttpServletRequest object containing client request data.
     * @param response The HttpServletResponse object for sending responses to the client.
     * @throws ServletException If a servlet-specific error occurs during request processing.
     * @throws IOException      If an I/O error occurs during request processing.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles HTTP POST requests by delegating to the {@link #processRequest(HttpServletRequest, HttpServletResponse)} method.
     *
     * @param request  The HttpServletRequest object containing client request data.
     * @param response The HttpServletResponse object for sending responses to the client.
     * @throws ServletException If a servlet-specific error occurs during request processing.
     * @throws IOException      If an I/O error occurs during request processing.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Processes an incoming HTTP request. It uses the {@link ActionFactory} to determine
     * the appropriate {@link ActionCommand} to execute based on the request parameters, and then executes the command.
     *
     * @param request  The HttpServletRequest object containing client request data.
     * @param response The HttpServletResponse object for sending responses to the client.
     * @throws ServletException If a servlet-specific error occurs during request processing.
     * @throws IOException      If an I/O error occurs during request processing.
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionCommand command = ActionFactory.defineCommand(request, response);
        command.execute();
    }
}
