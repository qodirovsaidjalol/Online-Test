package uz.qodirov.logs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        return record.getLongThreadID() + " :: " + record.getSourceClassName() + " :: "
                + record.getSequenceNumber() + " :: "
                + record.getThrown() + " :: "
                + record.getLevel() + " :: "
                + record.getSourceMethodName() + " :: "
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(record.getMillis())) + " :: "
                + record.getMessage() + "\n";
    }

}
