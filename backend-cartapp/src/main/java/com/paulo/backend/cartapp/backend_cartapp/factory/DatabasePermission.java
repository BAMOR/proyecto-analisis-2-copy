package com.paulo.backend.cartapp.backend_cartapp.factory;

public interface DatabasePermission {
    boolean canRead();
    boolean canWrite();
    String getPermissionLevel();
}