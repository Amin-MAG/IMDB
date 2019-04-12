import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Imdb {

    private Scanner input = new Scanner(System.in);
    private static final String curDirectory = System.getProperty("user.dir");

    private String getFileName(int numberOfLine, String firstName) {

        if (numberOfLine < 10) { firstName = firstName + "00" + numberOfLine + ".html"; }
        else if (numberOfLine < 100) { firstName = firstName + "0" + numberOfLine + ".html"; }
        else { firstName = firstName + numberOfLine + ".html"; }

        return firstName;
    }

    private void getStatus(int cur, int end, String name) {

        int loadingPer = (int)(((double)cur/end) * 100);
        System.out.println("[" + loadingPer + " % ] Downloading " + name);

    }

    private int getNumberOfPages () throws IOException {

        Elements filmNameList = new Elements();
        Document htmlCode;
        ArrayList<String> searchingPages = new ArrayList<>();

        // Top Rated Films
        searchingPages.add("https://www.imdb.com/chart/top");
        htmlCode = Jsoup.connect("https://www.imdb.com/chart/top").get();
        filmNameList = htmlCode.select("td.titleColumn a");
        for (Element item : filmNameList) { searchingPages.add(item.attr("abs:href").split("[?]")[0]); }

        // Popular Films
        searchingPages.add("https://www.imdb.com/chart/");
        htmlCode = Jsoup.connect("https://www.imdb.com/chart/").get();
        filmNameList = htmlCode.select("td.titleColumn a");
        for (Element item : filmNameList) { searchingPages.add(item.attr("abs:href").split("[?]")[0]); }

        // Top Tv Shows
        searchingPages.add("https://www.imdb.com/chart/toptv");
        htmlCode = Jsoup.connect("https://www.imdb.com/chart/toptv").get();
        filmNameList = htmlCode.select("td.titleColumn a");
        for (Element item : filmNameList) { searchingPages.add(item.attr("abs:href").split("[?]")[0]); }

        // Popular Tv Shows
        searchingPages.add("https://www.imdb.com/chart/tvmeter");
        htmlCode = Jsoup.connect("https://www.imdb.com/chart/tvmeter").get();
        filmNameList = htmlCode.select("td.titleColumn a");
        for (Element item : filmNameList) { searchingPages.add(item.attr("abs:href").split("[?]")[0]); }




        return searchingPages.size();

    }

    public void downloadEssentialFiles() throws IOException {



        new File(curDirectory +"\\Pages\\").mkdir();
        new File(curDirectory +"\\Pages\\Popular-Films\\").mkdir();
        new File(curDirectory +"\\Pages\\Popular-TV\\").mkdir();
        new File(curDirectory +"\\Pages\\Top-Films\\").mkdir();
        new File(curDirectory +"\\Pages\\Top-TV\\").mkdir();
        new File(curDirectory +"\\Pages\\Actors\\").mkdir();
        new File(curDirectory +"\\Pages\\Coming-Soon\\").mkdir();
        int numberOfUrls = getNumberOfPages();
        String url;
        FileWriter htmlFile;
        int numberOfline;

        System.out.println("[+] " + numberOfUrls + " Pages Is Going To Be Downloaded");
        System.out.print("[?] Are You Sure ? (y/n) : ");
        String yorN = input.next();


        // Downloading And Saving

        Elements filmNameList;
        Document htmlCode;


        if ( yorN.equals("y") || yorN.equals("Y") ) {

            int pageNumber = 0;

            // Actors Page

            downloadActors();

            // Top News


            downloadNews();

            System.out.println("[+} Films Data");

            // Top Rated Films

            htmlFile = new FileWriter(curDirectory +"\\Pages\\Top-Films.html");
            url = "https://www.imdb.com/chart/top";
            htmlCode = Jsoup.connect(url).get();
            htmlFile.write(htmlCode.html());
            htmlFile.close();
            pageNumber++;
            getStatus(pageNumber, numberOfUrls, url);

            filmNameList = htmlCode.select("td.titleColumn a");
            numberOfline = 1;
            for (Element item : filmNameList) {

                String fileName = curDirectory +"\\Pages\\Top-Films\\";

                fileName = getFileName(numberOfline, fileName);

                htmlFile = new FileWriter(fileName);
                url = item.attr("abs:href").split("[?]")[0];
                htmlCode = Jsoup.connect(url).get();
                htmlFile.write(htmlCode.html());
                htmlFile.close();
                pageNumber++;
                getStatus(pageNumber, numberOfUrls, url);

                numberOfline++;
            }


            // Popular Films

            htmlFile = new FileWriter(curDirectory +"\\Pages\\Popular-Films.html");
            url = "https://www.imdb.com/chart/";
            htmlCode = Jsoup.connect(url).get();
            htmlFile.write(htmlCode.html());
            htmlFile.close();
            pageNumber++;
            getStatus(pageNumber, numberOfUrls,url );

            filmNameList = htmlCode.select("td.titleColumn a");
            numberOfline = 1;
            for (Element item : filmNameList) {

                String fileName = curDirectory +"\\Pages\\Popular-Films\\";

                fileName = getFileName(numberOfline, fileName);

                htmlFile = new FileWriter(fileName);
                url = item.attr("abs:href").split("[?]")[0];
                htmlCode = Jsoup.connect(url).get();
                htmlFile.write(htmlCode.html());
                htmlFile.close();
                pageNumber++;
                getStatus(pageNumber, numberOfUrls,url );

                numberOfline++;
            }



            // Top Tv Shows

            htmlFile = new FileWriter(curDirectory +"\\Pages\\Top-TV.html");
            url = "https://www.imdb.com/chart/toptv";
            htmlCode = Jsoup.connect(url).get();
            htmlFile.write(htmlCode.html());
            htmlFile.close();
            pageNumber++;
            getStatus(pageNumber, numberOfUrls,url );

            filmNameList = htmlCode.select("td.titleColumn a");
            numberOfline = 1;
            for (Element item : filmNameList) {

                String fileName = curDirectory +"\\Pages\\Top-TV\\";

                fileName = getFileName(numberOfline, fileName);

                htmlFile = new FileWriter(fileName);
                url = item.attr("abs:href").split("[?]")[0];
                htmlCode = Jsoup.connect(url).get();
                htmlFile.write(htmlCode.html());
                htmlFile.close();
                pageNumber++;
                getStatus(pageNumber, numberOfUrls,url );

                numberOfline++;
            }

            // Popular Tv Shows

            htmlFile = new FileWriter(curDirectory +"\\Pages\\Popular-TV.html");
            url = "https://www.imdb.com/chart/tvmeter";
            htmlCode = Jsoup.connect(url).get();
            htmlFile.write(htmlCode.html());
            htmlFile.close();
            pageNumber++;
            getStatus(pageNumber, numberOfUrls,url );

            filmNameList = htmlCode.select("td.titleColumn a");
            numberOfline = 1;
            for (Element item : filmNameList) {

                String fileName = curDirectory +"\\Pages\\Popular-TV\\";

                fileName = getFileName(numberOfline, fileName);

                htmlFile = new FileWriter(fileName);
                url = item.attr("abs:href").split("[?]")[0];
                htmlCode = Jsoup.connect(url).get();
                htmlFile.write(htmlCode.html());
                htmlFile.close();
                pageNumber++;
                getStatus(pageNumber, numberOfUrls,url );

                numberOfline++;
            }


            System.out.println();
            System.out.println("[+] All The Files Have Been Downloaded And Updated.");
            System.out.println();




        } else {
            System.out.println("[!] Operation Canceled.");
        }





    }

    public void downloadNews() throws IOException {

        System.out.println("[+] News Data");

        new File(curDirectory +"\\Pages\\").mkdir();

        int numberOfUrls = 5;
        String url;
        FileWriter htmlFile;
        Document htmlCode;

        int pageNumber = 0;

        // Top News

        htmlFile = new FileWriter(curDirectory +"\\Pages\\Top-News.html");
        url = "https://www.imdb.com/news/top";
        htmlCode = Jsoup.connect(url).get();
        htmlFile.write(htmlCode.html());
        htmlFile.close();
        pageNumber++;
        getStatus(pageNumber, numberOfUrls, url);


        // Movie News

        htmlFile = new FileWriter(curDirectory +"\\Pages\\Movie-News.html");
        url = "https://www.imdb.com/news/movie";
        htmlCode = Jsoup.connect(url).get();
        htmlFile.write(htmlCode.html());
        htmlFile.close();
        pageNumber++;
        getStatus(pageNumber, numberOfUrls, url);

        // TV News

        htmlFile = new FileWriter(curDirectory +"\\Pages\\TV-News.html");
        url = "https://www.imdb.com/news/tv";
        htmlCode = Jsoup.connect(url).get();
        htmlFile.write(htmlCode.html());
        htmlFile.close();
        pageNumber++;
        getStatus(pageNumber, numberOfUrls, url);

        // Celebrity News

        htmlFile = new FileWriter(curDirectory +"\\Pages\\Celebrity-News.html");
        url = "https://www.imdb.com/news/celebrity";
        htmlCode = Jsoup.connect(url).get();
        htmlFile.write(htmlCode.html());
        htmlFile.close();
        pageNumber++;
        getStatus(pageNumber, numberOfUrls, url);

        // Coming Soon

        htmlFile = new FileWriter(curDirectory +"\\Pages\\Coming-Soon.html");
        url = "https://www.imdb.com/movies-coming-soon/";
        htmlCode = Jsoup.connect(url).get();
        htmlFile.write(htmlCode.html());
        htmlFile.close();
        pageNumber++;
        getStatus(pageNumber, numberOfUrls, url);


        System.out.println();
        System.out.println("[+] News Files Have Been Downloaded And Updated.");
        System.out.println();

        downloadComingSoon();

    }

    public void downloadComingSoon() throws IOException {

        System.out.println("[+] Coming Soon Data");

        new File(curDirectory +"\\Pages\\Coming-Soon").mkdirs();

        String url;
        FileWriter htmlFile;
        Document htmlCode;

        int numberOfline = 1, pageNumber =  0;
        String htmlStringCodeOfMainPage = new String(Files.readAllBytes(Paths.get(curDirectory + "\\Pages\\Coming-Soon.html")));
        htmlCode = Jsoup.parseBodyFragment(htmlStringCodeOfMainPage);
        Elements filmNameList = htmlCode.select("td.overview-top h4 a");
        int numberOfUrls = filmNameList.size();

        for (Element item : filmNameList) {

            String fileName = curDirectory +"\\Pages\\Coming-Soon\\";

            fileName = getFileName(numberOfline, fileName);
            htmlFile = new FileWriter(fileName);
            url = "https://www.imdb.com" + item.attr("href").split("[?]")[0];
            htmlCode = Jsoup.connect(url).get();
            htmlFile.write(htmlCode.html());
            htmlFile.close();
            pageNumber++;
            getStatus(pageNumber, numberOfUrls, url);

            numberOfline++;
        }

        System.out.println("\n[+] Coming Soon data downloaded !");

    }

    public void downloadActors() throws IOException {

        new File(curDirectory +"\\Pages\\Actors\\").mkdirs();

        System.out.println("[+] Actors Data");
        int numberOfUrls = 10;
        String url;
        FileWriter htmlFile;
        Document htmlCode;
        FileWriter actorsFile = new FileWriter(curDirectory + "\\Pages\\Actors\\Actors.txt");

        int pageNumber = 0;
        String thisPageString = "";
        for (int page = 1; page < 11; page++){
            htmlFile = new FileWriter(curDirectory + "\\Pages\\Actors\\" + page + ".html" );
            url = "https://www.imdb.com/list/ls058011111/?page=" + page;
            htmlCode = Jsoup.connect(url).get();
            htmlFile.write(htmlCode.html());
            htmlFile.close();
            Elements actorsList = htmlCode.select("h3.lister-item-header a");
            for (Element item : actorsList) {
                thisPageString += item.text() + "=";
            }
            pageNumber++;
            getStatus(pageNumber, numberOfUrls, url);
        }


        actorsFile.write(thisPageString);
        actorsFile.close();

        System.out.println();
        System.out.println("[+] Actors Data Downloaded And Updated !");
        System.out.println();
    }

    private String[] getTopN(HashMap tops, int numberOfTop){

        HashMap<String,Integer> topsCopy = new HashMap<String,Integer>();
        Set<String> keys = tops.keySet();
        String ans[] = new String[numberOfTop];


        for (String item : keys) {
            int theItemNumber = (int)tops.get(item);
            topsCopy.put(item, theItemNumber);
        }

        keys = topsCopy.keySet();

        for (int i = 0; i < numberOfTop; i++) {
            int maxim = -1 ;
            for (String item : keys) {
                int theItemNumber = (int)topsCopy.get(item);
                if (theItemNumber > maxim) {
                    ans[i] = item;
                    maxim = theItemNumber;
                }
            }

            topsCopy.remove(ans[i]);
        }

        return ans;
    }

    public void showTop(HashMap topDirectors, HashMap topWriters, HashMap topStars, HashMap topCasts, HashMap topGenres, HashMap topCreators, int topValue, String category) {

        if (!category.equals("Top-TV") && !category.equals("Popular-TV")) {
            System.out.println();
            System.out.println("*****************");
            System.out.println("* Top Directors *");
            System.out.println("*****************");
            for (String item : getTopN(topDirectors, topValue)) {
                if (item != null) {
                    System.out.println(item + " : " + topDirectors.get(item));
                }
            }
            System.out.println();
            System.out.println("***************");
            System.out.println("* Top Writers *");
            System.out.println("***************");
            for (String item : getTopN(topWriters, topValue)) {
                if (item != null) {
                    System.out.println(item + " : " + topWriters.get(item));
                }
            }
        }
        if (category.equals("Popular-TV")){
            System.out.println();
            System.out.println("****************");
            System.out.println("* Top Creators *");
            System.out.println("****************");
            for (String item : getTopN(topCreators,topValue)){
                if (item  != null){ System.out.println(item + " : " + topCreators.get(item)); }
            }
        }
        System.out.println();
        System.out.println("*************");
        System.out.println("* Top Stars *");
        System.out.println("*************");
        for (String item : getTopN(topStars,topValue)){
            if (item  != null){ System.out.println(item + " : " + topStars.get(item)); }
        }
        System.out.println();
        System.out.println("*************");
        System.out.println("* Top Casts *");
        System.out.println("*************");
        for (String item : getTopN(topCasts,topValue)){
            if (item  != null){ System.out.println(item + " : " + topCasts.get(item)); }
        }
        System.out.println();
        System.out.println("**************");
        System.out.println("* Top Genres *");
        System.out.println("**************");
        for (String item : getTopN(topGenres,topValue)){
            if (item  != null){ System.out.println(item + " : " + topGenres.get(item)); }
        }

        System.out.println();
    }

    public void topRatedMovies(int fromNumber, int toNumber,String category) throws IOException, URISyntaxException {

        JSONObject data = new JSONObject();

        int numberOfLine;String showItems = "a";
        HashMap<String,Integer> topDirectors = new HashMap<String,Integer>();
        HashMap<String,Integer> topWriters = new HashMap<String,Integer>();
        HashMap<String,Integer> topStars = new HashMap<String,Integer>();
        HashMap<String,Integer> topCasts = new HashMap<String,Integer>();
        HashMap<String,Integer> topGenres = new HashMap<String,Integer>();
        HashMap<String,Integer> topCreators = new HashMap<String,Integer>();

        final int numberOfTopRatedFilms = 250;

        System.out.println();
        while ( !(showItems.equals("y") || showItems.equals("n") || showItems.equals("Y") || showItems.equals("N")) ){
            System.out.print("Do you want to see items with detail ? (y/n) : ");
            showItems = input.next();
        }


        String htmlStringCodeOfMainPage = new String(Files.readAllBytes(Paths.get(curDirectory + "\\Pages\\" + category + ".html")));
        Document htmlCodeOfMainPage = Jsoup.parseBodyFragment(htmlStringCodeOfMainPage);

        Elements filmNameList = htmlCodeOfMainPage.select("td.titleColumn a");
        Elements filmYearList = htmlCodeOfMainPage.select("td.titleColumn span");
        Elements filmRateList = htmlCodeOfMainPage.select("td.ratingColumn strong");

        System.out.println(category + "\n");
        System.out.println("From " + fromNumber + " to " + toNumber);

        for (int filmRank = fromNumber; filmRank <= toNumber ; filmRank++){

            String fileName;
            int sign = 0;
            ArrayList<String> directors = new ArrayList<>();
            ArrayList<String> writers = new ArrayList<>();
            ArrayList<String> stars = new ArrayList<>();
            ArrayList<String> casts = new ArrayList<>();
            ArrayList<String> genres = new ArrayList<>();
            ArrayList<String> creators = new ArrayList<>();
            JSONArray directorsJSON = new JSONArray();
            JSONArray writersJSON = new JSONArray();
            JSONArray starsJSON = new JSONArray();
            JSONArray castsJSON = new JSONArray();
            JSONArray genresJSON = new JSONArray();
            JSONArray creatorsJSON = new JSONArray();


            if (filmRank < 10) { fileName = "00" + filmRank ; }
            else if (filmRank < 100) { fileName = "0" + filmRank ; }
            else { fileName = "" + filmRank ; }
            fileName += ".html";

            String htmlStringCode = new String(Files.readAllBytes(Paths.get(curDirectory + "\\Pages\\"+ category + "\\" + fileName)));
            Document htmlCodeOfFilmPage = Jsoup.parseBodyFragment(htmlStringCode);
            Elements filmDetails = htmlCodeOfFilmPage.select("div.credit_summary_item a ,div.credit_summary_item h4");
            Elements filmGenres = htmlCodeOfFilmPage.select("div.subtext a[href^=/search/title]");
            Elements filmCasts = htmlCodeOfFilmPage.select("table.cast_list tbody tr td a[href^=/name/]");


            for (Element cast : filmCasts) {
                String text = cast.text();
                if (!text.isEmpty()){
                    casts.add(text);
                    castsJSON.add(text);
                    if (topCasts.get(text) != null) { topCasts.put(text, 1 + (int)topCasts.get(text)); }
                    else { topCasts.put(text, 1); }
                }
            }

            for (Element genre : filmGenres) {
                String text = genre.text();
                if (!text.isEmpty()) {
                    genres.add(text);
                    genresJSON.add(text);
                    if (topGenres.get(text) != null) { topGenres.put(text, 1 + (int) topGenres.get(text)); }
                    else { topGenres.put(text, 1); }
                }
            }

            for (Element item : filmDetails) {
                String text = item.text();

                if (text.equals("Directors:") || text.equals("Director:")){ sign = 1;continue; }
                else if (text.equals("Writers:") || text.equals("Writer:")) { sign = 2;continue; }
                else if (text.equals("Stars:") || text.equals("Star:")) { sign = 3;continue; }
                else if (text.equals("Creator:") || text.equals("Creators:")) { sign = 4;continue; }

                if (sign == 1){
                    directors.add(text);
                    directorsJSON.add(text);
                    if (topDirectors.get(text) != null) { topDirectors.put(text, 1 + (int)topDirectors.get(text)); }
                    else { topDirectors.put(text, 1); }
                } else if (sign == 2 && !text.contains("more credit")) {
                    writers.add(text);
                    writersJSON.add(text);
                    if (topWriters.get(text) != null) { topWriters.put(text, 1 + (int)topWriters.get(text)); }
                    else { topWriters.put(text, 1); }
                } else if (sign == 3 && !text.contains("See full cast & crew")) {
                    stars.add(text);
                    starsJSON.add(text);
                    if (topStars.get(text) != null) { topStars.put(text, 1 + (int)topStars.get(text)); }
                    else { topStars.put(text, 1); }
                } else if (sign == 4) {
                    creators.add(text);
                    creatorsJSON.add(text);
                    if (topCreators.get(text) != null) { topCreators.put(text, 1 + (int)topCreators.get(text)); }
                    else { topCreators.put(text, 1); }
                }
            }


            System.out.println("-----------------------------------------------------------------------------------");
            try {
                System.out.println(filmRank + ". " + filmNameList.get(filmRank-1).text() + " " + filmYearList.get(filmRank - 1).text());
            } catch (Exception e) {
                System.out.println(filmRank + ". " + filmNameList.get(filmRank-1).text() + " " /*+ filmYearList.get(filmRank - 1).text()*/);
            }

            if (showItems.equals("y") || showItems.equals("Y")) {
                try {
                    System.out.println("Rate : " + filmRateList.get(filmRank - 1).text());
                } catch (Exception e) {

                }

                System.out.println("Genres : " + genres);

                if (!category.equals("Top-TV") && !category.equals("Popular-TV")) {
                    System.out.println("Director(s) : " + directors);
                    System.out.println("Writer(s) : " + writers);
                }
                if (category.equals("Popular-TV")) {
                    System.out.println("Creators(s) : " + creators);
                }
                System.out.println("Star(s) : " + stars);
                System.out.println("Casts : " + casts);
            }

            JSONObject filmData = new JSONObject();

            try {
                filmData.put("Rate", filmRateList.get(filmRank - 1).text());
            } catch (Exception e) { }
            try {
                filmData.put("Year", filmYearList.get(filmRank - 1).text());
            } catch (Exception e) { }
            filmData.put("Directors", directorsJSON);
            filmData.put("Creators", creatorsJSON);
            filmData.put("Writers", writersJSON);
            filmData.put("Stars", starsJSON);
            filmData.put("Casts", castsJSON);
            filmData.put("Genres", genresJSON);

            data.put(filmNameList.get(filmRank-1).text(),filmData);

        }


        System.out.println();
        System.out.print("If you want to see N top people and genres (Enter N) else enter 0 : ");
        int topValue = input.nextInt();
        System.out.println();

        if (topValue != 0) showTop(topDirectors, topWriters, topStars, topCasts, topGenres, topCreators, topValue, category);


        String saveDataOrNot = "a";

        while ( !(saveDataOrNot.equals("y") || saveDataOrNot.equals("n") || saveDataOrNot.equals("Y") || saveDataOrNot.equals("N")) ){
            System.out.print("Do you want to save items as a Json File ? (y/n) : ");
            saveDataOrNot = input.next();
        }

        if (saveDataOrNot.equals("y") || saveDataOrNot.equals("Y")) {

            new File(curDirectory + "\\Data").mkdir();
            PrintWriter jsonFile = new PrintWriter(curDirectory + "\\Data\\" + category + ".json");
            jsonFile.write(data.toJSONString());
            jsonFile.flush();
            jsonFile.close();

        }


    }

    public void searchPageForSpecialNewsPage(String[] actorsName, String nameOfPage) throws IOException {

        JSONObject data = new JSONObject();

        ArrayList<String> pageTitles = new ArrayList<>();
        ArrayList<String> pageContents = new ArrayList<>();
        ArrayList<String> people = new ArrayList<>();

        String stringCode = new String(Files.readAllBytes(Paths.get(curDirectory + "\\Pages\\"+ nameOfPage + ".html")));
        Document htmlCode = Jsoup.parseBodyFragment(stringCode);

        Elements titles = htmlCode.select("h2.news-article__title a");
        Elements contents = htmlCode.select("div.news-article__content");
        for (Element title : titles){ pageTitles.add(title.text()); }
        for (Element content : contents){ pageContents.add(content.text()); }



        for (int numberOfNews = 0; numberOfNews < titles.size(); numberOfNews++){
            for (String name : actorsName){
                if (pageContents.get(numberOfNews).indexOf(name) != -1 && !people.contains(name)){
                    people.add(name);
                }
            }
        }


        System.out.println("\nPeople:");
        System.out.println();
        System.out.println("**********************");
        int rowNumber = 1;
        for (String name : people) {
            System.out.println("[+] " + name);
            rowNumber++;
        }
        System.out.println("**********************");
        System.out.println();


        String showAll;
        System.out.print("[?] Do you want to see all of news about people ? (y/n) : ");
        showAll = input.next();
        while (!(showAll.equals("n") || showAll.equals("Y") || showAll.equals("y") || showAll.equals("N"))) {
            System.out.println();
            System.out.print("[?] Do you want to see all of news about people ? (y/n) : ");
            showAll = input.next();
        }

        String yOrN = "Bull";

        if (showAll.equals("y") || showAll.equals("Y")){

            for (int numberOfNews = 0; numberOfNews < titles.size(); numberOfNews++){
                JSONArray actorsInThisNews = new JSONArray();
                for (String name : actorsName){

                    if (pageContents.get(numberOfNews).indexOf(name) != -1){
                        actorsInThisNews.add(name);
                        System.out.println("\n\n[Name] " + name);
                        System.out.println("[Title] " + pageTitles.get(numberOfNews));
                        System.out.println("\n[Content] : \n");
                        System.out.println(pageContents.get(numberOfNews));
                        System.out.println("\n-----------------------------------------------------------------");
                    }

                }
                JSONArray newsData = new JSONArray();
                newsData.add(actorsInThisNews);
                newsData.add(pageContents.get(numberOfNews));
                data.put(pageTitles.get(numberOfNews),newsData);
            }

        } else if (showAll.equals("n") || showAll.equals("N")){


            for (int numberOfNews = 0; numberOfNews < titles.size(); numberOfNews++){
                JSONArray actorsInThisNews = new JSONArray();
                for (String name : actorsName){
                    yOrN = "Bull";
                    if (pageContents.get(numberOfNews).indexOf(name) != -1){

                        actorsInThisNews.add(name);
                        System.out.println("\n\n[Name] " + name);
                        System.out.println("[Title] " + pageTitles.get(numberOfNews));
                        while (!(yOrN.equals("n") || yOrN.equals("Y") || yOrN.equals("y") || yOrN.equals("N"))) {
                            System.out.println();
                            System.out.print("[?] Do you want to see the details ? (y/n) : ");
                            yOrN = input.next();
                        }
                        if (yOrN.equals("Y") || yOrN.equals("y")) {
                            System.out.println("\n[Content] : \n");
                            System.out.println(pageContents.get(numberOfNews));
                        }


                    }
                }
                JSONArray newsData = new JSONArray();
                newsData.add(actorsInThisNews);
                newsData.add(pageContents.get(numberOfNews));
                data.put(pageTitles.get(numberOfNews),newsData);
            }

        }

        System.out.println();
        String saveData;
        System.out.print("[?] Do you want to save data as Json File ? (y/n) : ");
        saveData = input.next();
        while (!(saveData.equals("n") || saveData.equals("Y") || saveData.equals("y") || saveData.equals("N"))) {
            System.out.println();
            System.out.print("[?] Do you want to save data as Json File ? (y/n) : ");
            saveData = input.next();
        }

        if (saveData.equals("y") || saveData.equals("Y")){

            new File(curDirectory + "\\Data").mkdir();
            PrintWriter jsonFile = new PrintWriter(curDirectory + "\\Data\\" + nameOfPage + ".json");
            jsonFile.write(data.toJSONString());
            jsonFile.flush();
            jsonFile.close();

        }


    }

    public void getNews() throws IOException {

        String actorsString = new String(Files.readAllBytes(Paths.get(curDirectory + "\\Pages\\Actors\\Actors.txt")));
        String[] actorsName = actorsString.split("=");




        System.out.println("\n\n[+} Top News: \n");
        searchPageForSpecialNewsPage(actorsName, "Top-News");

        System.out.println("\n\n[+} Movie News: \n");
        searchPageForSpecialNewsPage(actorsName, "Movie-News");

        System.out.println("\n\n[+} TV News: \n");
        searchPageForSpecialNewsPage(actorsName, "Tv-News");

        System.out.println("\n\n[+} Celebrity News: \n");
        searchPageForSpecialNewsPage(actorsName, "Celebrity-News");


    }

    public void getComingSoon() throws IOException {

        int numberOfLine;String showItems = "a";

        System.out.println();
        while ( !(showItems.equals("y") || showItems.equals("n") || showItems.equals("Y") || showItems.equals("N")) ){
            System.out.print("Do you want to see items with detail ? (y/n) : ");
            showItems = input.next();
        }


        String htmlStringCodeOfMainPage = new String(Files.readAllBytes(Paths.get(curDirectory + "\\Pages\\Coming-Soon.html")));
        Document htmlCodeOfMainPage = Jsoup.parseBodyFragment(htmlStringCodeOfMainPage);

        Elements filmNameList = htmlCodeOfMainPage.select("td.overview-top h4 a");

        System.out.println("\n[+] Comming Soon !\n");

        for (int filmRank = 1 ; filmRank < filmNameList.size(); filmRank++){

            String fileName;
            int sign = 0;
            ArrayList<String> directors = new ArrayList<>();
            ArrayList<String> writers = new ArrayList<>();
            ArrayList<String> stars = new ArrayList<>();
            ArrayList<String> casts = new ArrayList<>();
            ArrayList<String> genres = new ArrayList<>();


            if (filmRank < 10) { fileName = "00" + filmRank ; }
            else if (filmRank < 100) { fileName = "0" + filmRank ; }
            else { fileName = "" + filmRank ; }
            fileName += ".html";

            String htmlStringCode = new String(Files.readAllBytes(Paths.get(curDirectory + "\\Pages\\Coming-Soon\\" + fileName)));
            Document htmlCodeOfFilmPage = Jsoup.parseBodyFragment(htmlStringCode);
            Elements filmDetails = htmlCodeOfFilmPage.select("div.credit_summary_item a ,div.credit_summary_item h4");
            Elements filmGenres = htmlCodeOfFilmPage.select("div.subtext a[href^=/search/title]");
            Elements filmCasts = htmlCodeOfFilmPage.select("table.cast_list tbody tr td a[href^=/name/]");


            for (Element cast : filmCasts) {
                String text = cast.text();
                if (!text.isEmpty()){
                    casts.add(text);
                }
            }

            for (Element genre : filmGenres) {
                String text = genre.text();
                if (!text.isEmpty()) {
                    genres.add(text);
                }
            }

            for (Element item : filmDetails) {
                String text = item.text();

                if (text.equals("Directors:") || text.equals("Director:")){ sign = 1;continue; }
                else if (text.equals("Writers:") || text.equals("Writer:")) { sign = 2;continue; }
                else if (text.equals("Stars:") || text.equals("Star:")) { sign = 3;continue; }

                if (sign == 1){
                    directors.add(text);
                } else if (sign == 2 && !text.contains("more credit")) {
                    writers.add(text);
                } else if (sign == 3 && !text.contains("See full cast & crew")) {
                    stars.add(text);
                }

            }


            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println(filmRank + ". " + filmNameList.get(filmRank-1).text());
            if (showItems.equals("y") || showItems.equals("Y")) {

                System.out.println("Genres : " + genres);
                System.out.println("Director(s) : " + directors);
                System.out.println("Writer(s) : " + writers);
                System.out.println("Star(s) : " + stars);
                System.out.println("Casts : " + casts);
            }

        }

    }


}