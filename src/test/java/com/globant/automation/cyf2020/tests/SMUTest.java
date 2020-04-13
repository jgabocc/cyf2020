package com.globant.automation.cyf2020.tests;

import com.globant.automation.cyf2020.smu.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class SMUTest {
    private WebDriver driver;


    @BeforeSuite
    public void openSMU() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://uat.starmeup.com/login.html");
    }

    @Test(priority = 1)
    public void testingLogin() {
        SMUser userB = new SMUser();
        userB.setUsersEmail(("user68@bootcampsqe.com"));
        userB.setPassword("user68@bootcampsqe.com");
        userB.setUsersJob("job_wogia");
        userB.setName("username68");
        userB.setLastName("feed68");
        StarMeUpLogin loginPage = new StarMeUpLogin(driver);
        String username = loginPage.usernameLogin(userB.getUsersEmail());
        SMUHome loggedIn = loginPage.passwordLoginComplete(userB.getPassword());
        String usersNamesAre = loggedIn.nameInfo();
        String usersJobIs = loggedIn.jobInfo();
        //String[] usernames= usersNamesAre.split(" ",2);
        String userStarNumber = loggedIn.howManyStars();
        int numberOfStars = Integer.parseInt(userStarNumber);

        Assert.assertTrue(usersNamesAre.contains(userB.getName()), "Name's different");
        Assert.assertTrue(usersNamesAre.contains(userB.getLastName()), "Different last name");
        Assert.assertTrue(usersJobIs.contains(userB.getUsersJob()), "That's not what they work as");
        Assert.assertTrue(username.contains(userB.getUsersEmail()), "It's not their email");
        String starsBHas = loggedIn.howManyStars();
        loggedIn.logOut();
        Assert.assertEquals(8, numberOfStars, "It's giving another value of stars");

       /* SMUser user2= new SMUser();
        user2.setName("username68");
        SMUser2 sendStar= loggedIn.searchBar(user2.getName());
        SendStar sendingTheStar= sendStar.sendStar();
        String messageForUser2= sendingTheStar.sendingStar("gr8 work");
        SMUser2 backToUser2= sendingTheStar.closeConfirmation();
        SMUHome backToHome= backToUser2.goHome();
*/
    }


    @Test(priority = 2)
    public void testingGiveStar() {
        SMUser userA = new SMUser();
        userA.setUsersEmail(("user64@bootcampsqe.com"));
        userA.setPassword("user64@bootcampsqe.com");
        StarMeUpLogin loginPage = new StarMeUpLogin(driver);
        String usernameB = loginPage.usernameLogin(userA.getUsersEmail());
        SMUHome smuHomeB = loginPage.passwordLoginComplete(userA.getPassword());
        SMUser userB = new SMUser();
        userB.setName("username68");
        SMUUserThatReceives searchUser = smuHomeB.searchBar(userB.getName());
        SendStar sendingTheStar = searchUser.sendStar();
        sendingTheStar.sendingStar("  if you're reading this it means the test works");
        SMUUserThatReceives backToUser = sendingTheStar.closeConfirmation();
        SMUHome backToHome = backToUser.goHome();
        backToHome.logOut();


    }

    @Test(priority = 3)
    public void checkIfStarWasReceived() {
        SMUser userB = new SMUser();
        userB.setUsersEmail(("user68@bootcampsqe.com"));
        userB.setPassword("user68@bootcampsqe.com");
        userB.setName("username68");
        userB.setLastName("feed68");


        SMUser userA = new SMUser();
        userA.setUsersEmail(("user64@bootcampsqe.com"));
        userA.setPassword("user64@bootcampsqe.com");
        userA.setLastName("feed64");

        StarMeUpLogin loginPage = new StarMeUpLogin(driver);
        String username = loginPage.usernameLogin(userB.getUsersEmail());
        SMUHome loggedIn = loginPage.passwordLoginComplete(userB.getPassword());
        // loggedIn.popUps();
        Notifications starNotification = loggedIn.aNotification();
        NotificationInfo theStarNotification = starNotification.goToNotificationInfo();
        SMUHome goHome = theStarNotification.goBackHome();
        String notification = goHome.notification();
        goHome.clickOnNotificationAgain();

        int stars = Integer.parseInt(goHome.howManyStars());
        int starsAdd = stars + 1;


        String actStar = loggedIn.activityFeedStar(); //userB should be the first star to appear
        Assert.assertTrue(actStar.contains(userB.getName()), "Not the same names");

        MyProfile myProfile = loggedIn.goToProfile();
        SMUHome actFeed1 = myProfile.goBackHome();
        MyProfile myProfile1 = actFeed1.goToProfile();
        myProfile1.clickReceived();

        int starsBHas = Integer.parseInt(myProfile1.howManyStars());
//        Assert.assertEquals(starsBHas, starsAdd, "not the same amount of stars");

        String theStarWasSentBy = myProfile1.starSentBy(); //verifies if the star is in received bc the star shows with who sent it
        Assert.assertTrue(theStarWasSentBy.contains(userA.getLastName()), "Different last names");

        String noNotification = myProfile1.notification();
        Assert.assertTrue(noNotification.toLowerCase().contains("don't"), "no");

        myProfile1.logOut();


    }


    @Test (priority = 4)
    public void commentOnSentStar() {
        SMUser userA = new SMUser();
        userA.setUsersEmail(("user64@bootcampsqe.com"));
        userA.setPassword("user64@bootcampsqe.com");
        //User b checks their star comments
        SMUser userB = new SMUser();
        userB.setUsersEmail(("user68@bootcampsqe.com"));
        userB.setPassword("user68@bootcampsqe.com");
        userB.setName("username68");
        userB.setLastName("feed68");
        StarMeUpLogin loginPageB = new StarMeUpLogin(driver);
        String usernameB = loginPageB.usernameLogin(userB.getUsersEmail());
        SMUHome loggedInB = loginPageB.passwordLoginComplete(userB.getPassword());
        MyProfile myProfileB = loggedInB.goToProfile();
        String comments = myProfileB.howManyCommentsOnTheLastStarReceived();
        int amountOfComments = Integer.parseInt(comments);
        Assert.assertEquals(0, amountOfComments, "there's more than x comments");
        myProfileB.logOut(); //UserB logout

        StarMeUpLogin loginPageA = new StarMeUpLogin(driver);
        String usernameA = loginPageA.usernameLogin(userA.getUsersEmail());
        SMUHome homePageA = loginPageA.passwordLoginComplete(userA.getPassword());
        SMUUserThatReceives searchUserB = homePageA.searchBar(userB.getName());
        String sentComment = searchUserB.commentOnSentStar(" you're very creative and think out of the box, this star is well deserved");
        String commentsNmbr = searchUserB.howManyCommentsOnTheLastStarReceived();
        int actualNumber = (amountOfComments + 1);
        int numberOfCom = Integer.parseInt(commentsNmbr);
        Assert.assertEquals(actualNumber,numberOfCom,"Different amount of comments");
        SMUHome goBackToHome = searchUserB.goHome();
        goBackToHome.logOut(); //UserA logout

        //UserB logs back in
        StarMeUpLogin bLoginPage = new StarMeUpLogin(driver);
        String bUsername = bLoginPage.usernameLogin(userB.getUsersEmail());
        SMUHome bHome = bLoginPage.passwordLoginComplete(userB.getPassword());
        Notifications theresAnotification = bHome.aNotification();
        //Here i check if the notification is for the comment
        String commentNote = theresAnotification.getsTheText();
        Assert.assertTrue(commentNote.toLowerCase().contains("comment"));

        NotificationInfo theNotification = theresAnotification.goToNotificationInfo();
        SMUHome theHomePage = theNotification.goBackHome();
        theHomePage.notification();
        theHomePage.clickOnNotificationAgain();
        MyProfile profileB=theHomePage.goToProfile();

        String noNotification = profileB.notification();
        Assert.assertTrue(noNotification.toLowerCase().contains("don't"), "no");
        profileB.clickOnNotificationAgain();

        String checkingHowManyComments = profileB.howManyCommentsOnTheLastStarReceived();
        int howManyComments = Integer.parseInt(checkingHowManyComments);
        profileB.logOut(); //UserB logout
        Assert.assertEquals(numberOfCom,howManyComments,"it's showing a different number");

    }

    @Test(priority = 5)
    public void likeSentStar(){
        SMUser userA = new SMUser();
        userA.setUsersEmail(("user64@bootcampsqe.com"));
        userA.setPassword("user64@bootcampsqe.com");
        //User b checks their star comments
        SMUser userB = new SMUser();
        userB.setUsersEmail(("user68@bootcampsqe.com"));
        userB.setPassword("user68@bootcampsqe.com");
        userB.setName("username68");
        userB.setLastName("feed68");
        StarMeUpLogin loggingInAsB = new StarMeUpLogin(driver);
        String usernameB = loggingInAsB.usernameLogin(userB.getUsersEmail());
        SMUHome loggedInAsB = loggingInAsB.passwordLoginComplete(userB.getPassword());
        MyProfile userBsProfile = loggedInAsB.goToProfile();
        String amountOfLikesOnStar = userBsProfile.howManyLikesOnTheLastStarReceived();
        int amountOfLikes = Integer.parseInt(amountOfLikesOnStar);
        Assert.assertEquals(0, amountOfLikes, "there's more than x likes");
        userBsProfile.logOut(); //UserB logout

        StarMeUpLogin loggingInAsA = new StarMeUpLogin(driver);
        String usernameA = loggingInAsA.usernameLogin(userA.getUsersEmail());
        SMUHome smUserAHome = loggingInAsA.passwordLoginComplete(userA.getPassword());
        SMUUserThatReceives userAGoesToUserB = smUserAHome.searchBar(userB.getName());
        String likesOnStar = userAGoesToUserB.likeTheLastStarSent();
        int nmbrOfStars = (amountOfLikes + 1);
        int likesOnStarAfterLiking = Integer.parseInt(likesOnStar);
        Assert.assertEquals(nmbrOfStars,likesOnStarAfterLiking,"Different amount of likes");
        SMUHome goBackToHome = userAGoesToUserB.goHome();
        goBackToHome.logOut(); //UserA logout

        //UserB logs back in
        StarMeUpLogin bLoginPage = new StarMeUpLogin(driver);
        String bUsername = bLoginPage.usernameLogin(userB.getUsersEmail());
        SMUHome bHome = bLoginPage.passwordLoginComplete(userB.getPassword());
        Notifications theresAnotification = bHome.aNotification();
        //Here i check if the notification is for the comment
        String likeNotification = theresAnotification.getsTheText();
        Assert.assertTrue(likeNotification.toLowerCase().contains("like"));

        NotificationInfo theNotification = theresAnotification.goToNotificationInfo();
        SMUHome theHomePage = theNotification.goBackHome();
        theHomePage.notification();
        theHomePage.clickOnNotificationAgain();
        MyProfile profileB=theHomePage.goToProfile();

        String noNotification = profileB.notification();
        Assert.assertTrue(noNotification.toLowerCase().contains("don't"), "no");
        profileB.clickOnNotificationAgain();

        String checkingHowManyLikes = userBsProfile.howManyLikesOnTheLastStarReceived();
        int howManyLikes = Integer.parseInt(checkingHowManyLikes);
        Assert.assertEquals(likesOnStarAfterLiking,howManyLikes,"it's showing a different number");


        userBsProfile.logOut(); //UserB logout


    }





    //@AfterSuite
    // public  void close(){
    //   driver.quit();
    //  }

}














