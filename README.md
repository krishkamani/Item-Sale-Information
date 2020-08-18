# Item-Sale-Information
This is a Item sale Information App. It also display year wise  Sales data.
<br> An Apk file is also attached with this project.

Don't Forget to <a href="https://www.youtube.com/channel/UCV8auqEr_jx606MqyeyIPpw?sub_confirmation=1">Subscribe</a> My Channel , like video and share to your friends. If you want to learn any new things then comment over that. We will make new video on that As soon As Possible.

## Task:
Consider the following database tables for the application.<br>
### Database Table:<br>
#### Name of the table: Sales
<table>
<tr>
  <th>Column Names</th>
</tr>
<tr>
    <td>Item Name</td>
    <td>Year</td>
    <td>Sale Price</td>
    <td>Date</td>
</tr>
<tr>
     <th>Data type with Constraints</th>
</tr>
<tr>
    <td>Text, Not Null</td>
    <td>Integer,Not Null</td>
    <td>Real, Not Null<br>Non-Zero,Nonnegative</td>
    <td>Text, Not Null<br>DD/MM/YYYY</td>
</tr>
<tr>
     <th>Sample Data</th>
</tr>
<tr>
    <td>TV</td>
    <td>2019</td>
    <td>57000</td>
    <td>05/05/2019</td>
</tr>
<tr>
    <td>AC</td>
    <td>2020</td>
    <td>28000</td>
    <td>10/05/2020</td>
</tr>
<tr>
    <td>TV</td>
    <td>2019</td>
    <td>45000</td>
    <td>14/05/2019</td>
</tr>
</table>

###  Database Table:<br>
####  Name of the table: SalesYear
<table>
<tr>
      <th>Column Names</th>
</tr>
<tr>
    <td>Item Name</td>
    <td>Year</td>
    <td>TotalSalePriceYearWise</td>
</tr>
<tr>
     <th>Data type with Constraints</th>
</tr>
<tr>
    <td>Text, Not Null</td>
    <td>Integer,Not Null</td>
    <td>Real, Not Null<br>Non-Zero,Nonnegative</td>
</tr>
<tr>
     <th>Sample Data</th>
</tr>
<tr>
    <td>TV</td>
    <td>2019</td>
    <td>102000</td>
</tr>
<tr>
    <td>AC</td>
    <td>2020</td>
    <td>28000</td>
</tr>
</table>

# Following functionalities in app:
1) First activity is asked item sale details according to table Sales. As soon as owner clicks
the submit button, update information in Sales table. Validate the fields for appropriate
data type i.e Item Name is Text type, Year is Integer type, etc. Also while updating data
in Sales table, update the TotalSalePriceYearWise column for specific item.<br>
2) Second activity will display the data of SalesYear table using RecyclerView.<br><br>
Use Next and Previous button for navigation between activities.

## Images of Output

![1](https://user-images.githubusercontent.com/52067673/83346086-27886380-a337-11ea-80d2-180ca74136f0.PNG)

![2](https://user-images.githubusercontent.com/52067673/83346107-4d156d00-a337-11ea-9ce2-d93fad820577.PNG)

![3](https://user-images.githubusercontent.com/52067673/83346119-63232d80-a337-11ea-942b-d865be38fc43.PNG)

![4](https://user-images.githubusercontent.com/52067673/83346130-72a27680-a337-11ea-8e64-5e6b5536e7ea.PNG)
