import command.Command;
import command.ReserveTicketCommand;
import command.ShowMovieListCommand;
import command.print.MainViewImpl;

import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) {
        MainViewImpl menu = new MainViewImpl();
        menu.start();
    }
}