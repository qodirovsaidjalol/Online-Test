package uz.qodirov.logs;

import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

public class CustomHandler extends StreamHandler {

    @Override
    public void publish(LogRecord record) {
        super.publish(record);
       /* Level level = record.getLevel();
        if (level.equals(Level.SEVERE)){
            // send telegram alarm
        }*/
    }


    @Override
    public void flush() {
        super.flush();
    }


    @Override
    public void close() throws SecurityException {
        super.close();
    }

}
