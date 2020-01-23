# SCHEDULE PLUS PLUS
### Back end for admin and employee use

This is my capstone project for <a href="https://adadevelopersacademy.org">Ada Developers Academy</a>.  I made this in 4 weeks, and that includes the [front end](https://github.com/stupendousC/schedulePlusPlus), and the back end(what you see here).  

The front end is written in Javascript with React, and it's deployed via AWS S3, you can find it [here](http://schedplusplus.s3-website-us-west-2.amazonaws.com/).  As of this writing on 1/22/2020, the front end is not yet fully functional.  When it is, you will be able to log in with this dummy google authorization of email = FakeEmployee001@yahoo.com and password = RealPassword001, which is attached to an employee named Lisa Simpson, and you'll be able to play with the employee dashboard functions.  For now you can still play with it on http://localhost:3000/.

The back end is written in Java using the Spring Boot framework, plus PostgreSQL database.  Deployed via AWS Elastic Beanstalk [here](http://schedplusplusbackend.us-west-2.elasticbeanstalk.com/), and the database is hosted via AWS RDS.

***

## WHAT DOES SCHEDULE PLUS PLUS DO?

This website is inspired by my time working at a temp staffing agency.  Whenever a client contacts the agency requesting someone to work a shift, the office would consult the master calendar and start calling employees one by one until they get a 'yes'.  Yes they do text and email too, but the nature of a temp staffing agency sometimes does necessitate urgent responses and that's why phone calls are a big part of their daily workload.

I myself was an employee, and if I'm further down the line, then I won't get a chance at these shifts if someone else got called first.  So coming from an employee's perspective, FOMO (Fear of Missing Out) does plague me especially since my last name starts with W.

What about for the people at the office?  Surely they can automate some of their phone tasks and individual texts, thus free themselves up for other things.  

1. If such an app exists, that would automatically generate a list of which employees are available, and texts them all for you, wouldn't that be great?  

2. Also, the employees can just grab the shifts on a first come first serve basis, that way they get an equal chance of work, wouldn't that be awesome too?  

3. And what if, instead of employees individually emailing in their own availability schedules, they can just manage their own days on/off via their own employee dashboard, wouldn't that also be just wonderful?

### YES, YES, and YES!   Says Schedule Plus Plus, and that is what I set out to do.

***

## CAN I SEE A DEMO?  

Well, this is the back end, so there really isn't much to see.  I'd really recommend you check out the [front end demos](https://github.com/stupendousC/schedulePlusPlus) instead.  
However, if you really want, you can still see what kind of [API endpoints](http://schedplusplusbackend.us-west-2.elasticbeanstalk.com/) I have available on the index.html, but if you just enter in the URL endpoint, you'll get either an empty list or null back as a response because you have to log in via front end first.  
I have not fully implemented google access token authentication as of this writing on 1/22/2020, so that's going on my TODO wishlist...

***

## HOW TO SET UP ON MY OWN COMPUTER?
### Requirements: 
A. You need to sign up with Google OAuth via their Google API Console.  [Overview here](https://developers.google.com/identity/protocols/OAuth2)  
B. You need a [Twilio](https://www.twilio.com/) account if you want to enable texting, which trust me, YOU DO.  
C. You need some way of deploying it, I used [AWS Elastic Beanstalk](https://aws.amazon.com/elasticbeanstalk/?nc2=type_a).  Or you can just use localhost:3000 for now.  
D. You need a database, I chose postgreSQL, this will be part of Step C during the Elastic Beanstalk environment creation.  
  ⋅⋅* Click 'Create New Application' in Elastic Beanstalk.
  ⋅⋅* Choose 'Web Server Environment'.
  ⋅⋅* Choose an available domain name.  Below that, choose preconfigured platform of 'java'.
  ⋅⋅* Below that, choose 'upload your code', use the local file in your target folder, the <projectName>-SNAPSHOT.jar file that you created with Maven's install function, which I did in IntelliJ.
  ⋅⋅* After a few seconds of uploading, click 'Configure more options'.
  ⋅⋅* Click on 'Database', choose 'postgres' under engine.  Make up a username and password.  Click 'Save'.
  ⋅⋅* See that 'Instances' option? We'll have to revisit that later.



### Download & Setup:
1. In your terminal, git clone a copy from here.  
    `git clone https://github.com/stupendousC/schedulePlusPlus.git`

2. Open up the project workspace in IntelliJ, or whatever IDE you use.  It should prompt you to install the dependencies, which Maven took care of for me in IntelliJ.

3. Declare your environment variables.  
  In IntelliJ, you click on the green dragdown toward the top that says <projectName>Application, in my case it was ScheduleApplication.  
  Choose 'edit configuration'.  
  You should be in the Configuration tab, look for Environment variables towards the middle, and cick on the little notepad looking icon on the far right.  
  A separate window pops up, and this is where you add the environment variables:  
  ⋅⋅* RDS_HOSTNAME: <get this value from the URL endpoint of your database>
  ⋅⋅* RDS_PORT: 5432
  ⋅⋅* RDS_DB_NAME: <get this from your database>
  ⋅⋅* RDS_USERNAME: <whatever you chose>
  ⋅⋅* RDS_PASSWORD: <whatever you chose>
  ⋅⋅* ACCOUNT_SID: <get from your Twilio console>
  ⋅⋅* AUTH_TOKEN: <get from your Twilio console>
  ⋅⋅* TRIAL_NUMBER: <get from your Twilio console>
  ⋅⋅* CLIENT_ID: <get from your Google OAuth console>
  
4. To run it on your local machine.  
  Click that little green triangle play button on the top right.  
  
5. To get .jar file ready for deployment.  
  On the far right you should see a Maven option, click on that, and click on 'install' under the 'Lifecycle' folder.  
  You should see the console below saying 'Build Successful'.
  
