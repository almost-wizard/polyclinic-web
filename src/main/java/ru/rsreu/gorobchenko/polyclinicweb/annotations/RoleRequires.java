package ru.rsreu.gorobchenko.polyclinicweb.annotations;

import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserRole;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RoleRequires {
    UserRole[] value();
}
