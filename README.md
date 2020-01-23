# SCHEDULE PLUS PLUS
### Back end for admin and employee use

This is my capstone project for <a href="https://adadevelopersacademy.org">Ada Developers Academy</a>.  I made this in 4 weeks, and that includes the [front end](https://github.com/stupendousC/schedulePlusPlus), and the back end(what you see here).  

The front end is written in Javascript with React, and it's deployed via AWS S3, you can find it [here](http://schedplusplus.s3-website-us-west-2.amazonaws.com/).  The front end is not yet fully functional when deployed due to limitations in Google OAuth when your app has not been fully verified by them (which takes 3-5 weeks). When run locally, you will be able to log in with this dummy google authorization of email = FakeEmployee001@yahoo.com and password = RealPassword001, which is attached to an employee named Lisa Simpson, and you'll be able to play with the employee dashboard functions.  For now you can still play with it on http://localhost:3000/.

The back end is written in Java using the Spring Boot framework, plus PostgreSQL database.  Deployed via AWS Elastic Beanstalk [here](http://schedplusplusbackend.us-west-2.elasticbeanstalk.com/), and the database is hosted via AWS RDS.

***

## CAN I SEE A DEMO?  

Well, this is the back end, so there really isn't much to see, unless you just read the code.  I'd really recommend you check out the [front end demos](https://github.com/stupendousC/schedulePlusPlus) instead.  
However, if you really want to, you can still see what kind of [API endpoints](http://schedplusplusbackend.us-west-2.elasticbeanstalk.com/) I have available on the index.html, but if you just enter in the URL endpoint, you'll get either an empty list or null back as a response because you have to log in via front end first, unless you manipulate the http request header...  
I have not fully implemented google access token authentication as of this writing on 1/22/2020, so that's going on my TODO wishlist for security...

***

## HOW TO SET UP ON MY OWN COMPUTER
### Requirements: 
A. You need to sign up with Google OAuth via their Google API Console.  [Overview here](https://developers.google.com/identity/protocols/OAuth2)  
B. You need a [Twilio](https://www.twilio.com/) account if you want to enable texting, which trust me, YOU DO.  
C. You need some way of deploying it, I used [AWS Elastic Beanstalk](https://aws.amazon.com/elasticbeanstalk/?nc2=type_a), see Step 5 below.  Or you can just use localhost:5000 for now.  
D. You need a database, I chose postgreSQL, this will be part of Step 5 during the Elastic Beanstalk environment creation.  
E. You need an IDE if you want to work with the Java code.  I used IntelliJ.  
F. Java and Maven 
  
### Download & Setup:
1. In your terminal, git clone a copy from here.  
    `git clone https://github.com/stupendousC/schedule.git`

2. Open up the project workspace in IntelliJ, or whatever IDE you use.  It should prompt you to install the dependencies, which Maven took care of for me in IntelliJ.

3. Declare your environment variables.  
  In IntelliJ, you click on the green dragdown toward the top that says {projectName}Application, in my case it was ScheduleApplication.  
  Choose 'edit configuration'.  
  You should be in the Configuration tab, look for Environment variables towards the middle, and cick on the little notepad looking icon on the far right.  
  A separate window pops up, and this is where you add the environment variables:  
    * ACCOUNT_SID: {get from your Twilio console}
    * AUTH_TOKEN: {get from your Twilio console}
    * TRIAL_NUMBER: {get from your Twilio console}
    * CLIENT_ID: {get from your Google OAuth console}
  
4. Get .jar file ready for deployment.  
  On the far right of IntelliJ you should see a Maven option, click on that, and click on 'install' under the 'Lifecycle' folder.  
  You should see the console below saying 'Build Successful'.  Now you have a {projectName}-SNAPSHOT.jar file in the target folder!
 
5. Now you want to go to AWS
    * Click 'Create New Application' in Elastic Beanstalk.
    * Choose 'Web Server Environment'.
    * Choose an available domain name.  Below that, choose preconfigured platform of 'java'.
    * Below that, choose 'upload your code' and find that jar file we made in Step 4.
    * After a few seconds of uploading, click 'Configure more options'.
    * Click on 'Database', choose 'postgres' under engine.  Make up a username and password.  Click 'Save'.

6. Click 'Create Environment' and go for a walk, this step took ~10 minutes the first time.  Each subsequent jar updates will be much faster.

7. Guess what, now that you have your database info, you'll need to go back and add it into the environment variables, as in Step 3 above.    
    I know... it's weird but if I wanted to use AWS database I have to create the environment first so they can generate an instance for me, which I'll need to plug into java then load it up.  It's kind of a chicken and egg scenario here...  At first I created my own standalone database via AWS RDS, and declared it in the environment variables, but the deployed environment would not accept it unless it's the database they made as part of their own config process.  
    If you use a built-in database like H2 in Spring then you won't have this headache I suppose.  
    * RDS_HOSTNAME: {get this value from the URL endpoint of your database}
    * RDS_PORT: 5432
    * RDS_DB_NAME: {get this from your database}
    * RDS_USERNAME: {whatever you chose}
    * RDS_PASSWORD: {whatever you chose}  
8.  Repeat Step 4... Now you should be deployed. 
  
9. To run it on your local machine.  
  Click that little green triangle play button on the top right in IntelliJ.  
