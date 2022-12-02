package utils;

import java.io.IOException;

public interface Commandable {
    void synchronise_ticks();
    void synchronise_renders() throws IOException;
}
