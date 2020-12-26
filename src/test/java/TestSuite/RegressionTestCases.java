/**
 * Class Name:RegressionTestCases
 * Description: This class include all positive and negative usecases based on business functionality.
 Positive Usecases
 TC01_searchForUserName Search the Username
 TC02_searchForUserIdSearch the users userid
 TC03_fetchCommentsByUserNamesearch posts from user
 TC04_fetchEmailsByPostIdsSearch user posted comments emailids
 TC05_fetchEmailsByPostIdVerifyemailpatternverify user email id pattern.

 Negative Usecases
 TC06_searchForInvalidUserNameSearch Invalid Username
 TC07_searchFornullUsernameUserIdSearch null Username Userid
 TC08_fetchCommentsByUnknownUserNameSearch Invalid Userid comments
 TC09_fetchEmailsByUnknownUserPostIds Search Invalid Userid Posts
 TC10_fetchEmailsByUserPostIdSearch particular userid email list in comments

 *
 * @author  Basheer Ahamed
 * @version 1.0
 * @since   12/26/2020   */

package TestSuite;

import Utility.ApiBaseMethods;
import Utility.ConstantData;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.ArrayList;

//================================Positive  Testcases =============================
public class RegressionTestCases {

    //TC01_searchForUserName Search the Username
    @Test
    public void TC01_searchForUserName()  {
        String searchResult = ApiBaseMethods.searchUser(ConstantData.USERNAME).toString();
        System.out.println("Search for the user " + searchResult + ": is found!!!");
        Assert.assertEquals(searchResult,ConstantData.USERNAME);

    }
    //TC02_searchForUserIdSearch the users userid
    @Test
    public void TC02_searchForUserId()  {
        int userId = ApiBaseMethods.getUserId(ConstantData.USERNAME);
        Assert.assertNotNull(userId, "UserId Exists");
    }
    //TC03_fetchCommentsByUserNamesearch posts from user
    @Test
    public void TC03_fetchCommentsByUserName() {
        int userId = ApiBaseMethods.getUserId(ConstantData.USERNAME);
        Integer[] postId = ApiBaseMethods.getPostId(userId);
        Assert.assertNotNull(ApiBaseMethods.getComments(postId), "Comments are Listed Out for the User");
    }
    //TC04_fetchEmailsByPostIdsSearch user posted comments emailids
    @Test
    public void TC04_fetchEmailsByPostIds()  {
        int userId = ApiBaseMethods.getUserId(ConstantData.USERNAME);
        Integer[] postId = ApiBaseMethods.getPostId(userId);
        Assert.assertNotNull(ApiBaseMethods.getEmailAdresses(postId), "Email Addresses are Listed Out for the User");
    }
    //TC05_fetchEmailsByPostIdVerifyemailpatternverify user email id pattern.
    @Test
    public void TC05_fetchEmailsByPostIdVerifyemailpattern()  {
        int userId = ApiBaseMethods.getUserId(ConstantData.USERNAME);
        Integer[] postId = ApiBaseMethods.getPostId(userId);
        for (Object eusid : postId) {
            System.out.println("Get List of email ids from Comments:"+"===>"+ "UserId:"+eusid);
            ArrayList<String> emailList = ApiBaseMethods.getEmailAdressesByPostId((Integer) eusid);
            Assert.assertNotNull(emailList, "Email Addresses are Listed Out for the User");
            boolean isValidEmailList = ApiBaseMethods.isValidEmailAddress(emailList);
            Assert.assertTrue(isValidEmailList, "Emails in the list are valid");
        }
    }

    //================================Negative Testcases =============================
    //TC06_searchForInvalidUserNameSearch Invalid Username
    @Test
    public void TC06_searchForInvalidUserName()  {
        String userName = "Jackson";
        System.out.println("UserName====>" + userName);
        String searchResult = ApiBaseMethods.searchUser(userName);
        Assert.assertNotEquals(userName, searchResult);
        System.out.println("Search for the user " + searchResult + ": is Not found!!!");
    }

    //TC07_searchFornullUsernameUserIdSearch null Username Userid

    @Test
    public void TC07_searchFornullUsernameUserId()  {
        String UserName = "";
        int userId = ApiBaseMethods.getUserId(UserName);
        Assert.assertNotNull(userId, "UserId Does Not Exists");
        System.out.println(userId);
    }

    //TC08_fetchCommentsByUnknownUserNameSearch Invalid Userid comments
    @Test
    public void TC08_fetchCommentsByUnknownUserName()  {
        String userName = "UnknowUser";
        int userId = ApiBaseMethods.getUserId(userName);
        Integer[] postId = ApiBaseMethods.getPostId(userId);
        Assert.assertNotNull(ApiBaseMethods.getComments(postId), "Comments are Not Listed Out for the User");
    }

    //TC09_fetchEmailsByUnknownUserPostIds Search Invalid Userid Posts
    @Test
    public void TC09_fetchEmailsByUnknownUserPostIds()  {
        String userName = "UnknowUser";
        int userId = ApiBaseMethods.getUserId(userName);
        Integer[] postId = ApiBaseMethods.getPostId(userId);
        Assert.assertNotNull(ApiBaseMethods.getEmailAdresses(postId), "Email Addresses are Not Listed Out for the User");
    }

    //TC10_fetchEmailsByUserPostIdSearch particular userid email list in comments
    @Test
    public void TC10_fetchEmailsByUserPostId()  {
        String userName = ConstantData.USERNAME;
        int userId = ApiBaseMethods.getUserId(userName);
        int postId = 201;
        ArrayList<String> emailList = ApiBaseMethods.getEmailAdressesByPostId(postId);
        Assert.assertNotNull(emailList, "Email Addresses are Not Listed Out for the User");
        boolean isValidEmailList = ApiBaseMethods.isValidEmailAddress(emailList);
        Assert.assertTrue(isValidEmailList, "Emails in the list are Not valid");

    }


}
