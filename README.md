This a To-Do app to add and view tasks.
The data is transferred through REST apis from client to server.
Uses MySQL database to store user details and tasks
A user can login and fetch their tasks.
The app uses Jwt authentication to maintain the session and fetch tasks for that particular user.
Internally the spring boot app uses JPA which implements hibernate queries to to perform CRUD operatoins.
The passwords are hashsed using Bcrypt encoder before storing in database.
since it is a one way hashing method, no one can decrypt the password.
The User login and register and public endpoints to allow external user access.
