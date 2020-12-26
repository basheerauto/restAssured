<p> </p>
<table width="625">
<tbody>
<tr>
<td style="text-align: center;" width="625"><span style="text-align: center; color: #0000ff;">FreeNow -Backend Test Challenge</span></td>
</tr>
</tbody>
</table>
<p> </p>
<table width="623">
<tbody>
<tr>
<td width="162">
<p><strong>Problem</strong></p>
</td>
<td width="462">
<p>Using Published Api : <a href="https://jsonplaceholder.typicode.com/">https://jsonplaceholder.typicode.com/</a></p>
<p>Help the developers on your team make the feature more robust, by helping them write tests for some workflows that they might break while making progress with developing business logic</p>
</td>
</tr>
<tr>
<td width="162">
<p><strong>Task</strong></p>
</td>
<td width="462">
<p>To perform the validations for the comments for the post made by a specific user named “Samantha”.</p>
</td>
</tr>
<tr>
<td width="162">
<p><strong>Expected</strong></p>
</td>
<td width="462">
<p>Search for the user.</p>
<p>Use the details fetched to make a search for the posts written by the user.</p>
<p>For each post, fetch the comments and validate if the emails in the comment section are in the proper format.</p>
<p>Think of various scenarios for the test workflow, all the things that can go wrong.</p>
<p>Upload your test to Github</p>
<p>Execute the tests successfully on Circle CI.</p>
</td>
</tr>
<tr>
<td width="162">
<p><strong>API Calls</strong></p>
</td>
<td width="462">
<p>Find User :</p>
<p><strong>GET </strong>: ttps://jsonplaceholder.typicode.com/users?username=Samantha</p>
<p> </p>
<p>Search for the user posts:</p>
<p><strong>GET</strong>:<a href="https://jsonplaceholder.typicode.com/posts?userId=3">https://jsonplaceholder.typicode.com/posts?userId=3</a></p>
<p> </p>
<p>Using each post id find the comments</p>
<p><strong>GET</strong>: <a href="https://jsonplaceholder.typicode.com/comments?postId=21">https://jsonplaceholder.typicode.com/comments?postId=21</a></p>
<p> </p>
</td>
</tr>
<tr>
<td width="162">
<p><strong>Tools /Environment</strong></p>
</td>
<td width="462">
<p>Intellij Idea<br /> Java<br /> RestAssured<br /> TestNG</p>
<p>Maven<br /> GitHub<br /> CircleCI</p>
</td>
</tr>
<tr>
<td width="162">
<p><strong>Regression Scenarios</strong></p>
</td>
<td width="462">
<p><strong>Positive Usecases</strong></p>
<p>TC01_searchForUserName</p>
<p>TC02_searchForUserId</p>
<p>TC03_fetchCommentsByUserName</p>
<p>TC04_fetchEmailsByPostIds</p>
<p>TC05_fetchEmailsByPostIdVerifyemailpattern</p>
<p><strong>Negative Usecases          </strong></p>
<p>TC06_searchForInvalidUserName</p>
<p>TC07_searchFornullUsernameUserId</p>
<p>TC08_fetchCommentsByUnknownUserName</p>
<p>TC09_fetchEmailsByUnknownUserPostIds</p>
<p>TC10_fetchEmailsByUserPostId</p>
</td>
</tr>
</tbody>
</table>