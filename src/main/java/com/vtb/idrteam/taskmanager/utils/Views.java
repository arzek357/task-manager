package com.vtb.idrteam.taskmanager.utils;

public final class Views {
    public interface Id {}

    public interface Small extends Id{}

    public interface Big extends Small{}

    public interface BigUser extends Big{}
    public interface FullUser extends BigUser{}

    public interface BigProject extends Big{}
    public interface FullProject extends BigProject{}

    public interface BigTask extends Big{}
    public interface FullTask extends BigTask{}

    public interface BigNotification extends Big{}
    public interface FullNotification extends BigNotification{}
}
