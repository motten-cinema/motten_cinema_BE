package command;

import command.print.ExitPrintView;

import java.util.Scanner;

public class ExitCommand implements Command {
    @Override
    public void execute() {
        ExitPrintView.printExitMessage();
        new Scanner(System.in).nextLine();  // 아무 키나 입력 시 종료 대기
        System.exit(0);  // 실제 프로그램 종료
    }
}