/**
 * Class Name:ApiBaseMethods
 * Description: Using this Generic methods manipulates RestAssuted Api testing for below functionalities :-
 1) Search User
 2) Fetch Userid
 3) Fetch user posted userid
 4)Fetch Comments for userid
 5)Fetch email list for userid
 6)Validate email pattern for user posted comments.
 *
 * @author  Basheer Ahamed
 * @version 1.0
 * @since   12/26/2020   */

package Utility;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ApiBaseMethods {

    /*
     * Method to find a particular user given the userName as input
     * argument from the '/users' API response content
     */
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

    /*
     * Get the UserId for a particular user given the userName as input argument
     * from the '/users' API response content
     *
     */
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

    /*
     * Get the UserId for a particular user given the userName as input argument
     * from the '/posts' API response content
     */
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

    /*
     * Get the List of Comments for a particular user's posts given their postIds as
     * input argument from the '/comments' API response content
     */
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

    /*
     * Get the List of Email Addresses for a particular user's posts given their
     * postIds as input argument from the '/comments' API response content
     */
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

    /*
     * Get the List of Email Addresses for a particular user's posts given their
     * postIds as input argument from the '/comments' API response content
     */
    public static ArrayList<String> getEmailAdressesByPostId(int PostId) {
        RestAssured.baseURI = ConstantData.BASE_URI;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/comments");
        int code = response.getStatusCode();
        System.out.println("Status Code:" + code);
        Assert.assertEquals(code, 200);
        String responseString = response.asString();
        ArrayList<String> emailList = new ArrayList<String>();
        List<Object> fetchEmaiAddress = com.jayway.jsonpath.JsonPath.parse(responseString)
                .read("$[?(@.postId == " + PostId + ")].email");
        for (Object object : fetchEmaiAddress) {
            emailList.add((String) object);
        }

        return emailList;
    }


    /*
     * Validate list of emails id pattern  from the email ids
     */

    public static boolean isValidEmailAddress(ArrayList<String> email) {
        String emailRegex =ConstantData.EMAIL_REGEX;
        boolean emailValidationResult = true;
        Pattern pattern = Pattern.compile(emailRegex);
        Object[] objects = email.toArray();
        // Printing array of objects
        int len = objects.length;
        System.out.println("No of Email ids:" + len);
        for (Object emailID : email) {
            System.out.println("Email address:" + emailID);
            emailValidationResult = pattern.matcher((CharSequence) emailID).matches();
            if (!emailValidationResult) {
                return false;
            }
        }
        return emailValidationResult;
    }

   /* public static void main(String[] args)
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
       ArrayList<String> fetchListOfEmails = getEmailAdresses(postIds);
         boolean validEmailList = isValidEmailAddress(fetchListOfEmails);

    }
*/

}





