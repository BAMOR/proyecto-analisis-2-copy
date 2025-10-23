package com.paulo.backend.cartapp.backend_cartapp;

import com.paulo.backend.cartapp.backend_cartapp.factory.DatabasePermission;
import com.paulo.backend.cartapp.backend_cartapp.factory.PermissionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PermissionFactoryTest {

    @Test
    void getPermission_Admin_ReturnsReadWritePermission() {
        DatabasePermission perm = PermissionFactory.getPermission("admin");

        assertTrue(perm.canRead());
        assertTrue(perm.canWrite());
        assertEquals("READ_WRITE", perm.getPermissionLevel());
    }

    @Test
    void getPermission_User_ReturnsReadOnlyPermission() {
        DatabasePermission perm = PermissionFactory.getPermission("user");

        assertTrue(perm.canRead());
        assertFalse(perm.canWrite());
        assertEquals("READ_ONLY", perm.getPermissionLevel());
    }

    @Test
    void getPermission_ReadWriteAlias_ReturnsReadWritePermission() {
        DatabasePermission perm = PermissionFactory.getPermission("read_write");

        assertTrue(perm.canRead() && perm.canWrite());
        assertEquals("READ_WRITE", perm.getPermissionLevel());
    }

    @Test
    void getPermission_InvalidRole_ThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            PermissionFactory.getPermission("invalid_role");
        });

        assertEquals("Rol no soportado: invalid_role", exception.getMessage());
    }
}