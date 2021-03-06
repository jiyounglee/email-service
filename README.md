# Email Service
## Introduction
Email Service allows user to send email using various different Email Service Provider

## Build
### Api Key
Add API key in application.properties file for sendgrid and mailgun by updating email.sender.sendgrid.api.key and email.sender.mailgun.api.key
Update the url for mailgun using your domain

application.properties
email.sender.sendgrid.api.key = {generated sendgrid api key} 
email.sender.mailgun.api.key = {generated mailgun api key}
email.sender.mailgun.url = https://api.mailgun.net/v3/{yourDomain}/messages

### How to Run Locally using IDE
1. Clone this project
2. Open with an IDE. IDE should recognise this is Maven Project and using java 8
3. Update API key for Send Grid and Mail Gun
4. Download Spring Plugin if your IDE doesn't have the plugin installed
5. Create Run/Debug Configuration and set the main class as com.challenge.emailservice.EmailServiceApplication
6. Run create Run/Debug config
7. Send request to http://localhost:8080/ as described below

### How to Run the packaged application
1. Clone this project
2. Update API key for Send Grid and Mail Gun
3. Run command using maven wrapper ./mvnw clean package
4. Run command java -jar target/{email-service-packaged-jar}
5. Send request to http://localhost:8080/ as described below

### How to Run the using maven
1. Clone this project
2. Update API key for Send Grid and Mail Gun
3. Run command using maven wrapper ./mvnw spring-boot:run
4. Send request to http://localhost:8080/ as described below

## Usage
### URL
POST /email with Header Content-Type : application/json and body JSON payload

### Example Payload
```JSON
{
  "from": {
    "name": "From Name",
    "address": "from@email.com"
  },
  "to": [
    {
      "name": "To One",
      "address": "toone@email.com"
    },
    {
      "address": "totwo@email.com"
    }
  ],
  "cc": [
    {
      "address": "ccone@email.com"
    },
    {
      "name" : "CC TWO",
      "address": "cctwo@email.com"
    },
    {
      "address": "ccthree@email.com"
    }
  ],
  "bcc": [
    {
      "name": "Bcc One",
      "address": "bccone@email.com"
    }
  ],
  "subject": "Some Subject",
  "content": "Some Content"
}
```

### Validation
#### Common Object
* Email Address Json object have name and address variables.
* Email Address object is used for from, to, cc and bcc.
* name is optional field
* address is required field and email address 

#### Root Object
* from is required field and Email Address Object is validated
* to is required field with array of email address Json Object. At least one email address need to be provided.
* cc is optional field with array of email address Json Object.
* bcc is optional field with array of email address Json Object.
* subject is required field. can not be empty and can not have more than 64 characters
* content is required field. can not be empty and can not have more than 2048 characters

### Error
#### 400 - Invalid Payload
* Malformed JSON
* Not in right structure
* Missing required fields
* Invalid Email
* Empty fields

#### 500 - Server Error
* Unable to send email using all the providers
* Payload contains email that doesn't exist

### Limitations
Only text can be sent

## Deployment
The project is deployed using Heroku.
https://challenge-email-service.herokuapp.com/

## Enhancements
* Validation
    * Validation message could be customised to be more friendlier to use
    * Validation message when payload contains correctly formatted but invalid email
    * Validate how many recipients can one payload have
* Response Object
    * Response Object could be customised depending what information we need to give to the clients
* Constants clean up
    * There are few constants that are repeated through out the project could be cleaned up