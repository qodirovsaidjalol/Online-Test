package uz.qodirov.ui;

import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;
import uz.qodirov.Application;
import uz.qodirov.configs.ApplicationContextHolder;
import uz.qodirov.enums.roles.Role;
import uz.qodirov.services.SessionService;

import java.util.Objects;
import java.util.logging.Logger;

public class Menu {
    public static final Logger logger = Logger.getLogger(Application.class.getName());
    private static final UserUI USER_UI =   ApplicationContextHolder.getBean(UserUI.class);
    private static final TestUI TEST_UI =   ApplicationContextHolder.getBean(TestUI.class);
    private static final QuizUI QUIZ_UI =   ApplicationContextHolder.getBean(QuizUI.class);
    // private static final AuthUI AUTH_UI = ApplicationContextHolder.getBean(AuthUI.class);

    public static void run() {
        Menu.menu();
        Print.println(Color.BLUE, "EXIT -> exit");
        String choose = Input.getStr(" ? : ");
        switch (choose) {
            case "LOGIN" -> USER_UI.login();
            case "REGISTER" -> USER_UI.register();
            case "LOGOUT" -> USER_UI.logout();

            case "CREATE_USER" -> USER_UI.create();
            case "DELETE_USER" -> USER_UI.delete();
            case "UPDATE_USER" -> USER_UI.update();
            case "LIST_USER" -> USER_UI.list();

            case "CREATE_TESTS" -> TEST_UI.create();
            case "DELETE_TESTS" -> TEST_UI.delete();
            case "UPDATE_TESTS" -> TEST_UI.update();
            case "LIST_TESTS" -> TEST_UI.list();

            case "PLAY_QUIZ" -> QUIZ_UI.play_quiz();
            case "MY_RESULTS" -> QUIZ_UI.result_quizes();


            case "EXIT" -> {
                Print.println(Color.YELLOW, "Goodbye");
                return ;
            }
            default -> {
                Print.println(Color.RED, "Wrong menu !!!");
            }
        }
        run();
    }







   public static void menu(){
if (Objects.isNull(SessionService.getSession())){
    Print.println(Color.BLUE, "LOGIN -> login");
    Print.println(Color.BLUE, "REGISTER-> register");
} else if (SessionService.session.getRole().equals(Role.ADMIN)){
    Print.println(Color.BLUE, "CREATE_USER -> create user");
    Print.println(Color.BLUE, "DELETE_USER -> delete user");
    Print.println(Color.BLUE, "UPDATE_USER -> update user");
    Print.println(Color.BLUE, "LIST_USER -> list user");
    Print.println(Color.BLUE, "LOGOUT -> logout");
}
else if (SessionService.session.getRole().equals(Role.TEACHER)){
    Print.println(Color.BLUE, "CREATE_TESTS -> create test");
    Print.println(Color.BLUE, "DELETE_TESTS -> delete test");
    Print.println(Color.BLUE, "UPDATE_TESTS -> update test");
    Print.println(Color.BLUE, "LIST_TESTS -> list test");
    Print.println(Color.BLUE, "LOGOUT -> logout");
}else {
    Print.println("PLAY_QUIZ -> Play Quiz");
    Print.println("MY_RESULTS -> History");
    Print.println("LOGOUT -> Logout");
}


   }
}
