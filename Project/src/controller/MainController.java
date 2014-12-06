package controller;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import model.Config;

/**
 *
 * @author Yannick Lagger lagger.yannick@gmail.com
 */
public class MainController {

    private Config config;
    private Date currentDate;

    public MainController() {
        this.config = new Config();
        this.currentDate = new Date();
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public boolean rename() throws UnsupportedOperationException {
        boolean resultat = true;
        String name;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss");
        File newFile;
        File folder = new File(this.config.getPath());
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            int index = 1;
            if (file.isFile()) {
                int endIndex = file.getName().lastIndexOf(".");
                String extention = "";
                if (endIndex != -1) {
                    extention = file.getName().substring(endIndex + 1, file.getName().length()).toLowerCase();
                }
                if (extention.equals("jpg")) {
                    try {
                        Metadata metadata = ImageMetadataReader.readMetadata(file);
                        ExifSubIFDDirectory directory = metadata.getDirectory(ExifSubIFDDirectory.class);
                        Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
                        date = this.addShift(date);
                        do {
                            name = file.getParent() + "\\" + this.config.getName(dateFormat.format(date));
                            if (index > 1) {
                                name += "-" + index;
                            }
                            newFile = new File(name + "." + extention);
                            index++;
                        } while (!file.renameTo(newFile));
                    } catch (Exception e) {
                        resultat = false;
                    }
                }
            }
        }
        return resultat;
    }
    
    public Date addShift(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (!config.getShiftYear().equals("")){
            c.add(Calendar.YEAR, Integer.parseInt(config.getShiftYear()));
        }
        if (!config.getShiftMonth().equals("")){
            c.add(Calendar.MONTH, Integer.parseInt(config.getShiftMonth()));
        }
        if (!config.getShiftDay().equals("")){
            c.add(Calendar.DATE, Integer.parseInt(config.getShiftDay()));
        }
        if (!config.getShiftHoures().equals("")){
            c.add(Calendar.HOUR, Integer.parseInt(config.getShiftHoures()));
        }
        if (!config.getShiftMinutes().equals("")){
            c.add(Calendar.MINUTE, Integer.parseInt(config.getShiftMinutes()));
        }
        return c.getTime();
    }
    
    public boolean shiftValidity(String value) {
        if (value != null && !value.equals("")){
            try {
                Integer.parseInt(value);
            } catch (Exception e){
                return false;
            }
        }
        return true;
    }

    public void setDefaultValue() {
        this.config.setPath("");
        this.config.setPrefix("");
        this.config.setSufix("");
    }

    public String formatCurrentDate() {
        return formatDate(this.currentDate);
    }
    
    public String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss");
        return dateFormat.format(date);
    }
}
