package ru.rsreu.gorobchenko.polyclinicweb.command.implementation.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.rsreu.gorobchenko.polyclinicweb.annotations.RoleRequires;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionCommand;
import ru.rsreu.gorobchenko.polyclinicweb.command.Page;
import ru.rsreu.gorobchenko.polyclinicweb.dao.SpecializationDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DaoFactory;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DbType;
import ru.rsreu.gorobchenko.polyclinicweb.model.Specialization;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserRole;

import java.io.IOException;
import java.util.List;

@RoleRequires({UserRole.SYSTEM_ADMINISTRATOR})
public class GetCreateUserFormCommand extends ActionCommand {
    public GetCreateUserFormCommand(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE);
        SpecializationDao specializationDao = factory.getSpecializationDao();
        List<Specialization> specializations = specializationDao.getAll();
        request.setAttribute("roles", UserRole.values());
        request.setAttribute("specializations", specializations);
        request.setAttribute("doctorRoleId", UserRole.DOCTOR.getDatabaseRoleId());
        forward(Page.CREATE_USER);
    }
}
