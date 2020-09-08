package com.vtb.idrteam.taskmanager.utils;

public final class Views {
    public interface Id {}

    public interface Small extends Id{}

    public interface BigUser extends Small{}

    public interface FullUser extends BigUser{}

    public interface BigProject extends Small{}

    public interface FullProject extends BigProject{}

    public interface FullTask extends BigTask{}

    public interface BigTask extends Small{}
}
