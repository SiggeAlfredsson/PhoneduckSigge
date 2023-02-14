# PhoneduckSigge
School assignment

ws://localhost:8080/sub/channel  !! Automaticly connects you to Channel: 1 , do not need to enter any headers. Can not send messages in this socket.

ws://localhost:8080/sub/chat     !! Connect to any channel you want by entering the channel id as KEY, leave rest blank.

GET     ! http://localhost:8080/channel       ! To get all the channels in the db.

POST    ! http://localhost:8080/channel       ! To create a new channel (JSON).

DELETE  ! http://localhost:8080/channel/(id)  ! To delete a channel by id.


What to fix !
Socket message does not send to all the channels you are connected to ( you do still get receive all messages ),
spring.jpa.hibernate.ddl-auto=create-drop --> update . Created weird bug where the id of the channel got way to large,
Message says you are connected to channels that do not exists if you enter a channelid that does not exist,
Add error handling,
Implement the user classes to the rest of the code, get a sender for each message,

