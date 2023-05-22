package main;

import dao.*;
import dataSets.*;

import java.util.List;

public class TestMain {
    public static void main(String[] args) throws Exception {
        LearnsThemeDAO ltDAO = new LearnsThemeDAO();
        PortionDAO portionDAO = new PortionDAO();
        ThemeDAO td = new ThemeDAO();
        UsersDAO userDAO = new UsersDAO();

        Users dataSet = userDAO.read(5);
        System.out.println("FINAL OUTPUT: User id: " + dataSet.getId() + " User name: " + dataSet.getName() + " " + dataSet.getSurname() + '\n');
        Users dataSet1 = userDAO.getLogin("mmmmmm", "131313");
        System.out.println("FINAL OUTPUT: User id: " + dataSet1.getId() + " User name: " + dataSet1.getName() + " " + dataSet1.getSurname() + '\n');

        Theme theme = td.read(1);
        System.out.println(theme.getId()+" "+theme.getName()+" "+theme.getDescription());

        List<Theme> themes = userDAO.getThemeOfUser(5);
        for (Theme t : themes) {
            System.out.println("|||"+t.getId()+" "+t.getName()+" "+t.getDescription());

        }

        Portion po = portionDAO.read(1);
        System.out.println(po.getId() + " " + po.getThemeId() + " " + po.getName());

        List<Portion> port = userDAO.getPortionsOfUser(5);
        for(Portion p : port) {
            System.out.println(p.getId() + " " + p.getThemeId() + " " + p.getName());
        }

        port = userDAO.getPortionsOfUserOfTheme(5, 4);
        for(Portion p : port) {
            System.out.println(p.getId() + " " + p.getThemeId() + " " + p.getName());
        }
    }
}
