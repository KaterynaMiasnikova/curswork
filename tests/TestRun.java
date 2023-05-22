import dao.*;
import dataSets.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TestRun {
    UsersDAO userDAO;
    FactDAO factDAO;
    ThemeDAO themeDAO;
    PortionDAO portionDAO;
    FeedbackDAO feedbackDAO;
    LikedFactDAO likedFactDAO;
    LearnedPortionDAO learnedPortionDAO;
    LearnsThemeDAO learnsThemeDAO;
    String name; //users
    String surname; //users
    String email1; //users
    String email2; //users
    String pass1; //users
    String pass2; //users
    String fact1; //fact
    String link1; //fact
    String picture1; //fact, theme
    String picture2; //fact, theme
    String theme; //theme
    int amount; //theme
    String description; //theme
    String portion; //portion
    String portionText; //portion
    long themeId; //portion, learns_theme
    long newThemeId; //portion, learns_theme
    long userId; //feedback, learned_portion, learns_theme, liked_fact
    long portionId; //feedback, learned_portion
    long feedbackId; //feedback
    long factId; //liked_fact
    int answer0; //feedback
    int answer1; //feedback
    long noId;

    @Before
    public void beforeTests() {
        userDAO = new UsersDAO();
        factDAO = new FactDAO();
        themeDAO = new ThemeDAO();
        portionDAO = new PortionDAO();
        feedbackDAO = new FeedbackDAO();
        likedFactDAO = new LikedFactDAO();
        learnedPortionDAO = new LearnedPortionDAO();
        learnsThemeDAO = new LearnsThemeDAO();
        name = "Freddie";
        surname = "Mercury";
        email1 = "iwant2";
        pass1 = "break4ree";
        email2 = "loki";
        pass2 = "tesseract";
        fact1 = "McDonald's first menu items were hot dogs, not hamburgers.";
        picture1 = "https://st.depositphotos.com/1003272/3274/i/950/depositphotos_32740243-stock-photo-hot-dog.jpg";
        link1 = "http://instanerd.me/v/279/mcdonald-s-first-menu";
        picture2 = "data:image/png;base64,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";
        theme = "Machine Learning";
        description = "Machine learning is a method of data analysis that automates analytical model building. It is a branch of artificial intelligence based on the idea that systems can learn from data, identify patterns and make decisions with minimal human intervention.";
        amount = 1;
        portion = "What Is Machine Learning?";
        portionText = "Machine learning is a branch of artificial intelligence (AI) and computer science which focuses on the use of data and algorithms to imitate the way that humans learn, gradually improving its accuracy.\n\nIBM has a rich history with machine learning. One of its own, Arthur Samuel, is credited for coining the term, “machine learning” with his research (PDF, 481 KB) (link resides outside IBM) around the game of checkers. Robert Nealey, the self-proclaimed checkers master, played the game on an IBM 7094 computer in 1962, and he lost to the computer. Compared to what can be done today, this feat almost seems trivial, but it’s considered a major milestone within the field of artificial intelligence. Over the next couple of decades, the technological developments around storage and processing power will enable some innovative products that we know and love today, such as Netflix’s recommendation engine or self-driving cars.\n\nMachine learning is an important component of the growing field of data science. Through the use of statistical methods, algorithms are trained to make classifications or predictions, uncovering key insights within data mining projects. These insights subsequently drive decision making within applications and businesses, ideally impacting key growth metrics. As big data continues to expand and grow, the market demand for data scientists will increase, requiring them to assist in the identification of the most relevant business questions and subsequently the data to answer them.";
        themeId = 4;
        newThemeId = 6;
        userId = 5;
        portionId = 3;
        feedbackId = 1;
        factId = 2;
        answer0 = 0;
        answer1 = 1;
        noId = 123456789;
    }

    //Users
    @Test
    public void userLogin() {
        Users user1_0 = userDAO.getLogin("iwan", pass1);
        System.out.println("\nValues: "+email1+" "+pass2+"\n");
        Users user1_1 = userDAO.getLogin(email1, pass2);
        System.out.println("\nValues: "+email1+" "+pass2+"\n");
        Users user2 = userDAO.getLogin(email2, pass2);
        System.out.println("\nValues: "+email2+" "+pass2+"\n");
        assertNull(user1_0);
        assertNull(user1_1);
        assertNotNull(user2);
        assertEquals(3,user2.getId());
        assertEquals("Tom", user2.getName());
        assertEquals("Hiddleston", user2.getSurname());
        assertEquals(email2, user2.getEmail());
        assertEquals(pass2, user2.getPassword_u());
    }

    @Test
    public void userSave() {
        Users user1 = new Users(name, surname, email2, pass1);
        System.out.println("\nValues: "+name+" "+surname+" "+email2+" "+pass1+"\n");
        Users user2 = new Users(name, surname, email1, pass1);
        System.out.println("\nValues: "+name+" "+surname+" "+email1+" "+pass1+"\n");
        userDAO.save(user1);
        Users result1 = userDAO.getLogin(email2, pass1);
        System.out.println("\nValues: "+email2+" "+pass1+"\n");
        assertNull(result1);
        userDAO.save(user2);
        Users result2 = userDAO.getLogin(email1, pass1);
        System.out.println("\nValues: "+email1+" "+pass1+"\n");
        assertEquals(name, result2.getName());
        assertEquals(surname, result2.getSurname());
        assertEquals(email1, result2.getEmail());
        assertEquals(pass1, result2.getPassword_u());
    }

    @Test
    public void userRead() {
        Users result1 = userDAO.read(noId);
        Users result2 = userDAO.read(userId);
        assertNull(result1);
        assertNotNull(result2);
        System.out.println(result2.getId()+" "+result2.getName()+" "+result2.getSurname()+" "+result2.getEmail()+" "+result2.getPassword_u());
    }

    @Test
    public void userLikedFacts() {
        List<Fact> facts1 = userDAO.getFactOfUser(noId);
        List<Fact> facts2 = userDAO.getFactOfUser(userId);
        assertNull(facts1);
        assertNotNull(facts2);
        for (Fact fact : facts2) {
            System.out.println(fact.getId()+" "+fact.getFact()+" "+fact.getLink()+" "+fact.getPicture());
        }
    }

    @Test
    public void userPortions() {
        List<Portion> portions1 = userDAO.getPortionsOfUser(noId);
        System.out.println("\nValues: "+noId+"\n");
        List<Portion> portions2 = userDAO.getPortionsOfUser(userId);
        System.out.println("\nValues: "+userId+"\n");
        assertNull(portions1);
        assertNotNull(portions2);
        for (Portion portion : portions2) {
            System.out.println(portion.getId()+" "+portion.getName()+" "+portion.getThemeId()+" \n\n"+portion.getPortion());
        }
    }

    @Test
    public void userPortionsOfTheme() {
        List<Portion> portions1_0 = userDAO.getPortionsOfUserOfTheme(noId, themeId);
        List<Portion> portions1_1 = userDAO.getPortionsOfUserOfTheme(userId, noId);
        List<Portion> portions1_2 = userDAO.getPortionsOfUserOfTheme(noId, noId);
        List<Portion> portions2 = userDAO.getPortionsOfUserOfTheme(userId, themeId);
        System.out.println("\nValues: "+userId+" "+themeId+"\n");
        assertNull(portions1_0);
        assertNull(portions1_1);
        assertNull(portions1_2);
        assertNotNull(portions2);
        System.out.println("Id of theme - " + themeId);
        for (Portion portion : portions2) {
            System.out.println("\n"+portion.getId()+" "+portion.getName()+" "+portion.getThemeId()+" \n"+portion.getPortion());
        }
    }

    @Test
    public void userThemes() {
        List<Theme> themes1 = userDAO.getThemeOfUser(noId);
        System.out.println("\nValues: "+noId+"\n");
        List<Theme> themes2 = userDAO.getThemeOfUser(userId);
        System.out.println("\nValues: "+userId+"\n");
        assertNull(themes1);
        assertNotNull(themes2);
        for (Theme theme : themes2) {
            System.out.println("\n"+theme.getId()+" "+theme.getName()+" "+theme.getPortions()+" \n"+theme.getPicture()+" \n"+theme.getDescription());
        }
    }

    //Fact
    @Test
    public void factRead() {
        Fact result1 = factDAO.read(noId);
        System.out.println("\nValues: "+noId+"\n");
        Fact result2 = factDAO.read(factId);
        System.out.println("\nValues: "+factId+"\n");
        assertNull(result1);
        assertNotNull(result2);
        System.out.println(result2.getId()+" "+result2.getFact()+" "+result2.getLink()+" "+result2.getPicture());
    }

    @Test
    public void factSave() {
        Fact fact = new Fact(fact1, link1, picture1);
        System.out.println("\nValues: "+factId+" "+link1+" "+picture1+"\n");
        factDAO.save(fact);
        Fact result = factDAO.read(6);
        System.out.println(result.getId()+" "+result.getFact()+" "+result.getLink()+" "+result.getPicture());
    }

    //Theme
    @Test
    public void themeRead() {
        Theme result1 = themeDAO.read(noId);
        System.out.println("\nValues: "+noId+"\n");
        Theme result2 = themeDAO.read(themeId);
        System.out.println("\nValues: "+themeId+"\n");
        assertNull(result1);
        assertNotNull(result2);
        System.out.println("\n"+result2.getId()+" "+result2.getName()+" "+result2.getPortions()+" "+result2.getPicture()+" \n"+result2.getDescription());
    }

    @Test
    public void themeSave() {
        Theme theme1 = new Theme(theme, amount, description, picture2);
        System.out.println("\nValues: "+theme+" "+amount+" "+description+" "+picture2+"\n");
        themeDAO.save(theme1);
        Theme result = themeDAO.read(6);
        System.out.println("\n"+result.getId()+" "+result.getName()+" "+result.getPortions()+" "+result.getPicture()+" \n"+result.getDescription());
    }

    //Portion
    @Test
    public void portionRead() {
        Portion result1 = portionDAO.read(noId);
        System.out.println("\nValues: "+noId+"\n");
        Portion result2 = portionDAO.read(themeId);
        System.out.println("\nValues: "+themeId+"\n");
        assertNull(result1);
        assertNotNull(result2);
        System.out.println(result2.getId()+" "+result2.getName()+" "+result2.getThemeId()+" \n\n"+result2.getPortion());
    }

    @Test
    public void portionSave() {
        Portion port = new Portion(portion, portionText, newThemeId);
        System.out.println("\nValues: "+portion+" "+newThemeId+" "+portionText+"\n");
        portionDAO.save(port);
        Portion result = portionDAO.read(6);
        System.out.println(result.getId()+" "+result.getName()+" "+result.getThemeId()+" \n\n"+result.getPortion());
    }

    @Test
    public void portionsOfTheme() {
        List<Portion> result1 = portionDAO.getPortionsOfTheme(noId);
        List<Portion> result2 = portionDAO.getPortionsOfTheme(themeId);
        assertNull(result1);
        assertNotNull(result2);
        System.out.println("Id of theme - " + themeId);
        for (Portion port : result2) {
            System.out.println("\n"+port.getId()+" "+port.getName()+" "+port.getThemeId()+" \n"+port.getPortion());
        }
    }

    //Feedback
    @Test
    public void feedbackRead() {
        Feedback result1 = feedbackDAO.read(noId);
        System.out.println("\nValues: "+noId+"\n");
        Feedback result2 = feedbackDAO.read(feedbackId);
        System.out.println("\nValues: "+feedbackId+"\n");
        assertNull(result1);
        assertNotNull(result2);
        System.out.println(result2.getId()+" "+result2.getAnswer1()+" "+result2.getAnswer2()+" "+result2.getAnswer3()+" "+result2.getUserId()+" "+result2.getPortionId());
    }

    @Test
    public void feedbackSave() {
        Feedback feedback = new Feedback(answer0, answer1, answer1, userId, portionId);
        System.out.println("\nValues: "+answer0+" "+answer1+" "+answer1+" "+userId+" "+portionId+"\n");
        feedbackDAO.save(feedback);
        Feedback result = feedbackDAO.read(6);
        System.out.println(result.getId()+" "+result.getAnswer1()+" "+result.getAnswer2()+" "+result.getAnswer3()+" "+result.getUserId()+" "+result.getPortionId());
    }

    //LearnedPortion
    @Test
    public void learnedPortionSave() {
        LearnedPortion learnedPortion = new LearnedPortion(userId, portionId);
        System.out.println("\nValues: "+userId+" "+portionId+"\n");
        learnedPortionDAO.save(learnedPortion);
        LearnedPortion learnedPortion2 = new LearnedPortion(userId, 6);
        System.out.println("\nValues: "+userId+" "+6+"\n");
        learnedPortionDAO.save(learnedPortion2);
    }

    //LearnsTheme
    @Test
    public void learnsThemeSave() {
        LearnsTheme learnsTheme = new LearnsTheme(userId, themeId);
        System.out.println("\nValues: "+userId+" "+themeId+"\n");
        learnsThemeDAO.save(learnsTheme);
        LearnsTheme learnsTheme2 = new LearnsTheme(userId, 6);
        System.out.println("\nValues: "+userId+" "+6+"\n");
        learnsThemeDAO.save(learnsTheme2);
    }

    //LikedFact
    @Test
    public void likedFactSave() {
        LikedFact likedFact = new LikedFact(userId, factId);
        System.out.println("\nValues: "+userId+" "+factId+"\n");
        likedFactDAO.save(likedFact);
        LikedFact likedFact2 = new LikedFact(userId, 6);
        System.out.println("\nValues: "+userId+" "+6+"\n");
        likedFactDAO.save(likedFact2);
    }
}