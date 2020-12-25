package Utility;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class ApiBaseMethods {

public static String searchUser(String searchUserName)
{
    RestAssured.baseURI = ConstantData.BASE_URI;
    RequestSpecification httpRequest = RestAssured.given();
    Response response = httpRequest.get("/users");
    int code = response.getStatusCode();
    System.out.println("Search User : Status Code:" + code);
    String responseString = response.asString();
    List<Object> userList = com.jayway.jsonpath.JsonPath.parse(responseString)
            .read("$.[?(@.username=='" + searchUserName + "')].username");
    if (userList == null || userList.isEmpty()) {
        return null;
    }
    return (String) userList.get(0);
}

    public static int getUserId(String userName)  {
        RestAssured.baseURI = ConstantData.BASE_URI;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/users");
        int code = response.getStatusCode();
        System.out.println("Get UserID : Status Code:" + code);
         String responseString = response.body().asString();
         List<Object> filteredIds = com.jayway.jsonpath.JsonPath.parse(responseString)
                .read("$[?(@.username == '" + userName + "')].id");
        if (filteredIds == null || filteredIds.isEmpty()) {
            return -1;
        }
        Integer id = (Integer) filteredIds.get(0);
        System.out.println("Fetched User ID for the user= "+userName +"===>"+ id);
        return id;
    }

    public static Integer[] getPostId(int userId)
    {
        RestAssured.baseURI = ConstantData.BASE_URI;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/posts");
        int code = response.getStatusCode();
        System.out.println("Post Id : Status Code:" + code);
        String responseString = response.asString();
        List<Object> fetchPostIds = com.jayway.jsonpath.JsonPath.parse(responseString)
                .read("$[?(@.userId == " + userId + ")].id");
        Integer[] postIds = fetchPostIds.toArray(new Integer[0]);
                     System.out.println("List of id posted :" + fetchPostIds.toString());
        return postIds;

    }
    public static ArrayList<String> getComments(Integer[] postId) {
        RestAssured.baseURI = ConstantData.BASE_URI;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/comments");
        int code = response.getStatusCode();
        System.out.println("Status Code:" + code);
        String responseString = response.asString();
       ArrayList<String> postsList = new ArrayList<String>();
        for (int pIds : postId) {
            List<Object> fetchComments = com.jayway.jsonpath.JsonPath.parse(responseString)
                    .read("$[?(@.postId == " + pIds + ")].body");
            String postedComments = fetchComments.toString();
            System.out.println("-------------User Posted Comments for id"+pIds+"====>" + postedComments);
           // postsList.add(postedComments);
        }
        return postsList;
    }


    public static ArrayList<String> getEmailAdresses(Integer[] PostId)  {
        RestAssured.baseURI = ConstantData.BASE_URI;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/comments");
        int code = response.getStatusCode();
        System.out.println("Status Code:" + code);
        Assert.assertEquals(code, 200);
        String responseString = response.asString();
        ArrayList<String> emailList = new ArrayList<String>();
        for (int pIds : PostId) {
            List<Object> fetchEmaiAddress = com.jayway.jsonpath.JsonPath.parse(responseString)
                    .read("$[?(@.postId == " + pIds + ")].email");
            String postedEmail = fetchEmaiAddress.toString();
            System.out.println("-------------Email Addresses of the PostID# " + pIds + "----------" + postedEmail);
            emailList.add(postedEmail);
        }
        return emailList;
    }




    public static boolean isValidEmailAddress(ArrayList<String> email) {
        String emailRegex =ConstantData.EMAIL_REGEX;
        boolean emailValidationResult = true;
        Pattern pattern = Pattern.compile(emailRegex);
        Object[] objects = email.toArray();
        // Printing array of objects
        int len = objects.length;
        System.out.println("Object Length is:" + len);
        for (Object emailID : email) {
            System.out.println("Object Value is:" + emailID);
            emailValidationResult = pattern.matcher((CharSequence) emailID).matches();
            if (!emailValidationResult) {
                return false;
            }
        }
        return emailValidationResult;
    }





    public static void main(String[] args)
    {
        System.out.println("==================================================================");
        String UserName = ConstantData.USERNAME;
        System.out.println("UserName====>" + UserName);
        String searchResult = searchUser(UserName);
        System.out.println(searchResult);
        System.out.println("================UserId fetch=======================================");
        int getUserId = getUserId(UserName);
        Integer postIds[] = getPostId(getUserId);
        System.out.println("================User posted list of comments ========================");
        System.out.println(getComments(postIds));
        System.out.println("================Email ids for User posted list of comments===========");
        //getEmailAdresses(postIds);
        ArrayList<String> fetchListOfEmails = getEmailAdresses(postIds);
        boolean validEmailList = isValidEmailAddress(fetchListOfEmails);
        }


        }





