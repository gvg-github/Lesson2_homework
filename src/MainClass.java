
/* Гурьевских В.Г.

(Task2) 2. Найти максимальное из четырех чисел. Массивы не использовать.

(Task3) 3. Написать программу обмена значениями двух целочисленных переменных:
        a. с использованием третьей переменной;
        b.*без использования третьей переменной.

(Task13) 13. * Написать функцию, генерирующую случайное число от 1 до 100.
        а) с использованием стандартной функции rand()
        б) без использования стандартной функции rand()

(Task14) 14. *Автоморфные числа. Натуральное число называется автоморфным, если оно равно последним цифрам своего квадрата.
         Например, 252 = 625. Напишите программу, которая вводит натуральное число N и выводит на экран все автоморфные числа,
          не превосходящие N.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainClass {
    public static void main(String[] args) {

        //Найти максимальное из четырех чисел. Массивы не использовать.
        Task2();

        //Написать программу обмена значениями двух целочисленных переменных.
        Task3();

        //Написать функцию, генерирующую случайное число от 1 до 100.
        Task13();

        //Напишите программу, которая вводит натуральное число N и выводит на экран все автоморфные числа, не превосходящие N.
        Task14();
    }

    private static void Task13() {

        //С использованием стандартной функции
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (Integer.parseInt(br.readLine()) != 0)
            {
                System.out.println(1 + (int) (Math.random() * 100));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Без использования стандартной функции
        BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (Integer.parseInt(br1.readLine()) != 0)
            {
                byte x = (byte)System.currentTimeMillis();
                System.out.println(1 + Math.abs(x * 99 / 128));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void Task3() {
        //С использованием третьей переменной
        int a = 4;
        int b = 6;
        System.out.println("Before change. a = " + a + ", b = " + b);
        int c = a;
        a = b;
        b = c;
        System.out.println("After change. a = " + a + ", b = " + b);

        //Без использования третьей переменной
        int x = 2;
        int y = 8;
        System.out.println("Before change. x = " + x + ", y = " + y);
        x = x + y;
        y = x - y;
        x = x - y;
        System.out.println("After change. x = " + x + ", y = " + y);
    }

    private static void Task2() {

        //Ввод чисел пользователем
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Find max number from 4 numbers.");
        try {
            System.out.println("Input 1 number:");
            int x1 = Integer.parseInt(br.readLine());
            System.out.println("Input 2 number:");
            int x2 = Integer.parseInt(br.readLine());
            System.out.println("Input 3 number:");
            int x3 = Integer.parseInt(br.readLine());
            System.out.println("Input 4 number:");
            int x4 = Integer.parseInt(br.readLine());

            //Поиск максимального значения
            int x = x1;
            if (x2 > x) {
                x = x2;
            }
            if (x3 > x) {
                x = x3;
            }
            if (x4 > x) {
                x = x4;
            }

            System.out.println("Max number: " + x);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void Task14() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Input number:");
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        try {
            int x = Integer.parseInt(br.readLine());
            for (int i = 1; i < x; i++) {
                int y = i * i;
                str1.append(i);
                str2.append(y);
                if (str2.length() > str1.length()){
                    String str3 = str2.substring(str1.length(), str2.length());
                    if (str3.equals(str1.toString())){
                        System.out.println(i);
                    }
                }
                str1.delete(0, str1.length());
                str2.delete(0, str2.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
