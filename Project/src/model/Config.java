package model;

/**
 *
 * @author Yannick Lagger lagger.yannick@gmail.com
 */
public class Config {

    private String path;
    private String prefix;
    private String sufix;
    private String comment;
    private String shiftYear;
    private String shiftMonth;
    private String shiftDay;
    private String shiftHoures;
    private String shiftMinutes;

    public Config() {
        this.prefix = "";
        this.sufix = "";
    }

    public String getName(String date) {
        String name = "";
        if (!this.prefix.equals("")) {
            name += this.prefix + "-";
        }
        name += date;
        if (!this.sufix.equals("")) {
            name += "-" + this.sufix;
        }
        return name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSufix() {
        return sufix;
    }

    public void setSufix(String sufix) {
        this.sufix = sufix;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getShiftYear() {
        return shiftYear;
    }

    public void setShiftYear(String shiftYear) {
        this.shiftYear = shiftYear;
    }

    public String getShiftMonth() {
        return shiftMonth;
    }

    public void setShiftMonth(String shiftMonth) {
        this.shiftMonth = shiftMonth;
    }

    public String getShiftDay() {
        return shiftDay;
    }

    public void setShiftDay(String shiftDay) {
        this.shiftDay = shiftDay;
    }
    
    public String getShiftHoures() {
        return shiftHoures;
    }

    public void setShiftHoures(String shiftHoures) {
        this.shiftHoures = shiftHoures;
    }

    public String getShiftMinutes() {
        return shiftMinutes;
    }

    public void setShiftMinutes(String shiftMinutes) {
        this.shiftMinutes = shiftMinutes;
    }
}
