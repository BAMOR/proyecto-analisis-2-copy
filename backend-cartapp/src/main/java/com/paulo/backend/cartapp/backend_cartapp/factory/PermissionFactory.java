package com.paulo.backend.cartapp.backend_cartapp.factory;

public class PermissionFactory {

    public static DatabasePermission getPermission(String role) {
        switch (role.toLowerCase()) {
            case "admin":
            case "read_write":
                return new ReadWritePermission();
            case "user":
            case "read_only":
                return new ReadOnlyPermission();
            case "writer":
            case "write_only":
                return new WriteOnlyPermission();
            default:
                throw new IllegalArgumentException("Rol no soportado: " + role);
        }
    }
}