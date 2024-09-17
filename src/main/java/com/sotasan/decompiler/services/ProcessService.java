package com.sotasan.decompiler.services;

import com.sotasan.decompiler.MainKt;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import java.lang.management.ManagementFactory;
import java.util.Optional;
import java.util.prefs.Preferences;

@UtilityClass
public class ProcessService {

    public static final Preferences PREFERENCES = Preferences.userNodeForPackage(MainKt.class);

    @SneakyThrows
    public static void start() {
        Optional<String> java = ProcessHandle.current().info().command();
        if (java.isPresent()) {
            String classPath = ManagementFactory.getRuntimeMXBean().getClassPath();
            String main = MainKt.class.getCanonicalName();
            new ProcessBuilder(java.get(), "-cp", classPath, main).start();
        }
    }

}