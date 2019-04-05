import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void showBanner() {
        System.out.println("*********************************************************************************************************************************************\n" +
                "*                      *****                *****                *****                *****               ******               ****         *\n" +
                "*                      *****                *****                *****                *****               ******               ****         *\n" +
                "*                      *****                *****                *****                *****               ******               ****         *\n" +
                "*                      *****                *****                *****                *****               ******               ****         *\n" +
                "*                      *****                *****                *****                *****               ******               ****         *\n" +
                "*         *************************************************************************************************************************         *\n" +
                "*         *************************************************************************************************************************         *\n" +
                "*         *************************************************************************************************************************         *\n" +
                "*         *************************************************************************************************************************         *\n" +
                "*         ******  ***  **  **  ****      ***        *********        **  *****  ***       *****      ****       ***       *********         *\n" +
                "*****************  **  *   **  ***       ***         *******         **  *****  **         ***       ***        **         ********         *\n" +
                "*****************  **     ***  ***       ***         *******        ***  *****  **         ***       ***        **         ********         *\n" +
                "*****************  ***    **  **** *********  *****  *******  *********  *****  **  *****  *** *********  ********  *****  ********         *\n" +
                "******************  **    **  ***         **        ********        ***  *****  **         **         **        **         ******************\n" +
                "******************  ***  **   ****        **        ********         **  *****  **         ***        **        **         ******************\n" +
                "*******************  *   **  *****  ********  *****  **************  **  *****  **  *****  ***  ********  ********  *****  ******************\n" +
                "*         *********  *    *  ***** *********  *****  **************  **  *****  **  *****  *** *********  ********  *****  ******************\n" +
                "*         *********         ******  ********  *****  **************  **  *****  **  *****  *** *********  ********  *****  ******************\n" +
                "*         **********        ******        **         *******         **         **  *****  *** *********        **  *****  ******************\n" +
                "*         **********   **   ******        **        ********        ****        **  *****  **   ********        **  *****  ********         *\n" +
                "*         *************************************************************************************************************************         *\n" +
                "*         *************************************************************************************************************************         *\n" +
                "*         *************************************************************************************************************************         *\n" +
                "*         ***********************************************  ** ***** **      **      ***********************************************         *\n" +
                "*         ***********************************************  **  ***  **  *** **  **  ***********************************************         *\n" +
                "*         ***********************************************  **  ***  ** **** **     ************************************************         *\n" +
                "*********************************************************  **   *   ** **** **      ***********************************************         *\n" +
                "*********************************************************  ** *   * ** **** ** **** ***********************************************         *\n" +
                "*********************************************************  ** *   * **      **      ***********************************************         *\n" +
                "*********************************************************  ** ** ** **     ***     **********************************************************\n" +
                "*********************************************************************************************************************************************\n" +
                "*         ***********************************************************************************************************************************\n" +
                "*         ***********************************************************************************************************************************\n" +
                "*         ***********************************************************************************************************************************\n" +
                "*         *************************************************************************************************************************         *\n" +
                "*         ************************************************* ****  ****  *****      ************************************************         *\n" +
                "*         *************************************************  ***  ****   ****  ****************************************************         *\n" +
                "*         *************************************************  **   **** * **** **  *************************************************         *\n" +
                "*         *************************************************  **   ***  *  *** **  *************************************************         *\n" +
                "*         ************************************************* *   * ***     *** ***  ************************************************         *\n" +
                "*         ************************************************* *   * **  ***  ** ***  ************************************************         *\n" +
                "*         ************************************************* ** ** **  ***  **      ************************************************         *\n" +
                "*         *************************************************************************************************************************         *\n" +
                "*         *************************************************************************************************************************         *\n" +
                "*                        *****                *****               *****                *****                *****              ****         *\n" +
                "*                        *****                *****               *****                *****                *****              ****         *\n" +
                "*                        *****                *****               *****                *****                *****              ****         *\n" +
                "*                        *****                *****               *****                *****                *****              ****         *\n" +
                "*                        *****                *****               *****                *****                *****              ****         *");
    }

    public static void showMainMenu() {

        System.out.println("*********************************************************************************************************************************************");
        System.out.println();
        System.out.println("Options:");
        System.out.println("            update-whole ( Downloading new data from www.imdb.com and saving them. )");
        System.out.println("            update-news ( Downloading new data for news and saving them. )");
        System.out.println("            update-actors ( Downloading new data about actors/actress. )");
        System.out.println("            process ( Downloading new data from www.imdb.com and saving them. )");
        System.out.println("            exit");
        System.out.println();

    }

    public static void showProcessMenu() {

        System.out.println("            top-films ( Processing data related to IMDB top rated films )");
        System.out.println("            pop-films ( Processing data related to popular films )");
        System.out.println("            top-tv ( Processing data related to IMDB top rated TV shows )");
        System.out.println("            pop-tv ( Processing data related to popular TV shows )");
        System.out.println("            news ( Processing data related to news )");
        System.out.println("            coming-soon ( Films and series that are going to be released this month. )");
        System.out.println("            goback");

    }
    public static BufferedReader runCommand(String command) throws IOException {

        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        return r;

    }

    public static void main(String[] args) throws IOException, URISyntaxException {


        // Imdb Object
        Imdb site = new Imdb();
        String order;
        Scanner input = new Scanner(System.in);
        runCommand("cls");
        runCommand("color 0a");
        showBanner();


        while (true) {

            showMainMenu();System.out.print("> ");
            order = input.next();

            if (order.equals("update-whole")) {
                site.downloadEssentialFiles();
            } else if (order.equals("update-news")) {
                site.downloadNews();
            } else if (order.equals("update-actors")) {
                site.downloadActors();
            } else if (order.equals("process")) {

                while (true) {

                    showProcessMenu();
                    System.out.print("> ");
                    order = input.next();

                    if (order.equals("top-films")){

                        int fromNumber;
                        int toNumber;
                        System.out.println("Processing data From film with rank A to one with B : (between 1-250)");
                        System.out.print("Enter A : ");
                        fromNumber = input.nextInt();
                        System.out.print("Enter B : ");
                        toNumber = input.nextInt();

                        site.topRatedMovies(fromNumber, toNumber, "Top-Films");

                        break;
                    } else if (order.equals("pop-films")) {

                        site.topRatedMovies(1, 10, "Popular-Films");

                        break;
                    } else if (order.equals("top-tv")) {

                        int fromNumber;
                        int toNumber;
                        System.out.println("Processing data From TV-Show with rank A to one with B : (between 1-250)");
                        System.out.print("Enter A : ");
                        fromNumber = input.nextInt();
                        System.out.print("Enter B : ");
                        toNumber = input.nextInt();

                        site.topRatedMovies(fromNumber, toNumber, "Top-TV");

                        break;
                    } else if (order.equals("pop-tv")) {

                        int fromNumber;
                        int toNumber;
                        System.out.println("Processing data From TV-Show with rank A to one with B : (between 1-100)");
                        System.out.print("Enter A : ");
                        fromNumber = input.nextInt();
                        System.out.print("Enter B : ");
                        toNumber = input.nextInt();

                        site.topRatedMovies(fromNumber, toNumber, "Popular-TV");

                        break;
                    } else if (order.equals("news")) {
                        site.getNews();
                        break;
                    } else if (order.equals("coming-soon")) {

                        site.getComingSoon();

                        break;
                    } else if (order.equals("goback")) {
                        break;
                    } else {
                        System.out.println();
                        System.out.println("[!] BAD Input !");
                    }
                    System.out.println();
                }

            }
            else if (order.equals("exit")) { break; }
            else {
                System.out.println();
                System.out.println("[!] BAD Input !");
            }
            System.out.println();

        }


    }

}
