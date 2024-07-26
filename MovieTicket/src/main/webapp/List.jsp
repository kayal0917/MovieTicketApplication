<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>Admin Dashboard</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
            background: linear-gradient(120deg, #3F567C, #B5CCC6);
            color: #333;
        }

        .container {
            max-width: 500px;
            margin: 50px auto;
            padding: 20px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }

        header {
            text-align: center;
            margin-bottom: 30px;
        }

        header h1 {
            margin: 0;
            color: #279CEB;
            font-size: 2.5em;
        }

        nav {
            margin-bottom: 30px;
        }

        nav ul {
            list-style: none;
            padding: 0;
            display: flex;
            justify-content: center;
        }

        nav ul li {
            margin: 0 10px;
        }

        nav ul li a {
            text-decoration: none;
            color: #279CEB;
            font-weight: bold;
            padding: 10px 20px;
            border: 2px solid #279CEB;
            border-radius: 5px;
            transition: background-color 0.3s, color 0.3s;
        }

        nav ul li a:hover {
            background-color: #279CEB;
            color: #fff;
        }

        main {
            text-align: center;
        }

        main h2 {
            color: #3F567C;
        }

        main p {
            font-size: 1.2em;
            color: #666;
        }

    </style>
</head>
<body>
    <div class="container">
        <header>
            <h1>Admin</h1>
        </header>
        <nav>
            <ul>
                <li><a href="userList">User List</a></li>
                <li><a href="theaterList">Theater List</a></li>
                <li><a href="/AllMovie">Movie List</a></li>
            </ul>
        </nav>
        <main>
            <h2>Welcome to the Admin Panel</h2>
        </main>
    </div>
</body>
</html>
