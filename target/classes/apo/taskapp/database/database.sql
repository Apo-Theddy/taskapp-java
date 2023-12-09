CREATE DATABASE lab14;

CREATE TABLE Users (
UserID INT PRIMARY KEY IDENTITY (1,1) NOT NULL,
Names VARCHAR(100) NOT NULL,
Lastnames VARCHAR(100) NOT NULL,
Birthday DATE NOT NULL,
);

CREATE TABLE Accounts (
Email VARCHAR(50) PRIMARY KEY NOT NULL,
Password VARCHAR(50) NOT NULL,
CreationDate DATE NULL DEFAULT SYSDATETIME(),
DeletedDate DATE NULL,
UpdatedDate DATE NULL,
UserID INT NOT NULL,
FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

CREATE TABLE Tasks(
 TaskID INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
 Title NVARCHAR(200),
 Content TEXT,
 CreationDate DATE NULL DEFAULT SYSDATETIME(),
 DeletedDate DATE NULL,
 UpdatedDate DATE NULL,
);

CREATE TABLE Users_Tasks(
	Users_TasksID INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
	UserID INT NOT NULL,
	TaskID INT NOT NULL,
	CONSTRAINT FK_UserIDTask FOREIGN KEY (UserID) REFERENCES Users(UserID),
	CONSTRAINT FK_TaskIDTask FOREIGN KEY (TaskID) REFERENCES Tasks(TaskID),
);

-- USER PROCEDURES --
CREATE PROCEDURE SP_CreateNewUser(
 @Names VARCHAR(100),
 @Lastnames VARCHAR(100),
 @Birthday DATE
) 
AS 
INSERT INTO Users(Names,Lastnames,Birthday) VALUES(@Names,@Lastnames,@Birthday);

CREATE PROCEDURE SP_DeleteUserByID(
	@UserID INT
) 
AS
BEGIN
	DELETE FROM Accounts WHERE UserId = @UserID;
	DELETE FROM Users WHERE UserID = @UserID;
END

CREATE PROCEDURE SP_UpdateUserByID(
	@UserID INT,
	@Names VARCHAR(100),
	@Lastnames VARCHAR(100),
	@Birthday DATE
) 
AS UPDATE Users SET Names = @Names, Lastnames = @Lastnames, Birthday = @Birthday
WHERE UserID = @UserID
-- // ------------------------------------------------------------------------------------ //

-- REGISTROS
INSERT INTO Users(Names, Lastnames, Birthday)
VALUES ('Juan', 'Pérez', '1990-05-15');

INSERT INTO Accounts (Email, Password, UserID)
VALUES ('juan.perez@email.com', 'contrasena123', 1);

INSERT INTO Tasks (Title, Content)
VALUES ('Hacer compras', 'Comprar leche, huevos y pan.');

INSERT INTO Users_Tasks VALUES(1,1);

-- // ------------------------------------------------------------------------------------ //


-- ACCOUNT FUNCTIONS
CREATE FUNCTION FN_GetUserAccount(
	@Email VARCHAR(50),
	@Password VARCHAR(50)
) 
RETURNS TABLE
AS RETURN SELECT u.* FROM Accounts a INNER JOIN Users u ON (u.UserID = a.UserID) WHERE a.Email = @Email AND a.Password = @Password 

-- ACCOUNT PROCEDURES --
-- USADO
CREATE PROCEDURE SP_CreateNewAccount(
 @Email VARCHAR(50),
 @Password VARCHAR(50),
 @UserID INT
) 
AS INSERT INTO Accounts(Email, Password, UserID) VALUES(@Email,@Password,@UserID);

CREATE PROCEDURE SP_UpdateAccount(
	@Email VARCHAR(50),
	@Password VARCHAR(50)
) 
AS UPDATE Accounts SET Email = @Email, Password = @Password WHERE Email = @Email

-- // ------------------------------------------------------------------------------------ //

-- TASKS PROCEDURES --
CREATE FUNCTION FN_GetTaskByUserID(
	@UserID INT
) RETURNS TABLE
AS RETURN SELECT t.* FROM Users_Tasks ut 
INNER JOIN Users u ON (ut.UserID = u.UserID) 
INNER JOIN Tasks t ON (ut.TaskID = t.TaskID) WHERE DeletedDate IS NULL

SELECT t.* FROM Users_Tasks ut 
INNER JOIN Users u ON (ut.UserID = u.UserID) 
INNER JOIN Tasks t ON (ut.TaskID = t.TaskID) WHERE DeletedDate IS NULL

CREATE PROCEDURE SP_CreateNewTask(
 @Title NVARCHAR(200),
 @Content TEXT,
 @TaskID INT OUTPUT
) 
AS
BEGIN
	INSERT INTO Tasks(Title,Content) VALUES(@Title,@Content);
	SET @TaskID = SCOPE_IDENTITY();
END

CREATE PROCEDURE SP_DeleteTask(
	@TaskID INT
)
AS
BEGIN
	DELETE FROM Users_Tasks WHERE TaskID = @TaskID
	DELETE FROM Tasks WHERE TaskID = @TaskID
END

CREATE PROCEDURE SP_UpdateTask(
	@TaskID INT,
	@Title NVARCHAR(200),
	@Content TEXT
)
AS UPDATE Tasks SET  Title = @Title, Content = @Content WHERE TaskID = @TaskID

-- // ------------------------------------------------------------------------------------ //
