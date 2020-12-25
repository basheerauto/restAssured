package TestSuite;

import Utility.ApiBaseMethods;
import Utility.ConstantData;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;


public class RegressionTestCases {
    @Test
    public void TC01_searchForUserName()  {
        String searchResult = ApiBaseMethods.searchUser(ConstantData.USERNAME).toString();
        System.out.println("Search for the user " + searchResult + ": is found!!!");
        Assert.assertEquals(searchResult,ConstantData.USERNAME);

    }

    @Test
    public void TC02_searchForUserId()  {
        int userId = ApiBaseMethods.getUserId(ConstantData.USERNAME);
        Assert.assertNotNull(userId, "UserId Exists");
    }

    @Test
    public void TC03_fetchCommentsByUserName() {
        int userId = ApiBaseMethods.getUserId(ConstantData.USERNAME);
        Integer[] postId = ApiBaseMethods.getPostId(userId);
        Assert.assertNotNull(ApiBaseMethods.getComments(postId), "Comments are Listed Out for the User");
    }

    @Test
    public void TC04_fetchEmailsByPostIds()  {
        int userId = ApiBaseMethods.getUserId(ConstantData.USERNAME);
        Integer[] postId = ApiBaseMethods.getPostId(userId);
        Assert.assertNotNull(ApiBaseMethods.getEmailAdresses(postId), "Email Addresses are Listed Out for the User");
    }

    @Test
    public void TC05_fetchEmailsByPostIdVerifyemailpattern()  {
        int userId = ApiBaseMethods.getUserId(ConstantData.USERNAME);
        Integer[] postId = ApiBaseMethods.getPostId(userId);
        for (Object eusid : postId) {
            System.out.println("Get List of email ids  from Comments:" + eusid);
            ArrayList<String> emailList = ApiBaseMethods.getEmailAdressesByPostId((Integer) eusid);
            Assert.assertNotNull(emailList, "Email Addresses are Listed Out for the User");
            boolean isValidEmailList = ApiBaseMethods.isValidEmailAddress(emailList);
            Assert.assertTrue(isValidEmailList, "Emails in the list are valid");
        }
    }


}
