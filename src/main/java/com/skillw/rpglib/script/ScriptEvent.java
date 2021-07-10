package com.skillw.rpglib.script;

import java.util.Objects;

public class ScriptEvent {
    private final String path;
    private final String function;

    public ScriptEvent(String text) {
        String[] strings = text.split("::");
        this.path = strings[0];
        this.function = strings[1];
    }

    public String getFunction() {
        return this.function;
    }

    public String getPath() {
        return this.path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        ScriptEvent that = (ScriptEvent) o;
        return this.getPath().equals(that.getPath()) && this.getFunction().equals(that.getFunction());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getPath(), this.getFunction());
    }
}
