package ru.rsreu.gorobchenko.polyclinicweb.service;

import ru.rsreu.gorobchenko.polyclinicweb.dao.SpecializationDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DaoFactory;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DbType;
import ru.rsreu.gorobchenko.polyclinicweb.model.Specialization;

import java.util.ArrayList;
import java.util.List;

public class SpecializationsManager {
    private static final SpecializationsManager instance = new SpecializationsManager();

    private final List<Specialization> specializations;

    public SpecializationsManager() {
        DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE);
        SpecializationDao specializationDao = factory.getSpecializationDao();
        this.specializations = specializationDao.getAll();
    }

    public static SpecializationsManager getInstance() {
        return instance;
    }

    public List<Specialization> getAll() {
        return this.specializations;
    }

    public Specialization fromId(int id) {
        for (Specialization specialization : this.specializations) {
            if (specialization.getId() == id) {
                return specialization;
            }
        }
        return Specialization.NULL;
    }

    public List<Specialization> fromIdsString(String specializationsIds) {
        List<Specialization> specializations = new ArrayList<>();
        for (String specializationId : specializationsIds.split(",")) {
            Specialization specialization = fromId(Integer.parseInt(specializationId));
            if (specialization != Specialization.NULL) {
                specializations.add(specialization);
            }
        }
        return specializations;
    }
}
