package com.ohgiraffers.section03.delete;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("삭제할 메뉴 번호를 입력하세요: ");
        int menuCode = sc.nextInt();

        MenuService service = new MenuService();
        service.removeMenu(menuCode);
    }
}