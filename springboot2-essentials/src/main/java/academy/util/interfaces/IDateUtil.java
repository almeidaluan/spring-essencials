package academy.util.interfaces;

import java.time.LocalDateTime;

public interface IDateUtil {
    String formatLocalDateTimeToDatabaseStyle(LocalDateTime localDateTime);
}
