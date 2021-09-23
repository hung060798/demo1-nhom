package cg.wbd.grandemonstration.fomater;

import cg.wbd.grandemonstration.models.Classes;
import cg.wbd.grandemonstration.service.IClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class ClassFomater implements Formatter<Classes> {
    private IClassesService classesService;
    @Override
    public Classes parse(String text, Locale locale) throws ParseException {
        return classesService.findOne(Long.parseLong(text)).get();
    }

    @Override
    public String print(Classes object, Locale locale) {
        return null;
    }

    public ClassFomater(IClassesService classesService) {
        this.classesService = classesService;
    }
}
