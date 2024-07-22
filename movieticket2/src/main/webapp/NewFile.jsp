<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TicketTrick Landing Page</title>
    <link rel="stylesheet" href="styles.css">
</head>
<style>
body, html {
    margin: 0;
    padding: 0;
    width: 100%;
    height: 100%;
    font-family: Arial, sans-serif;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #f4f4f4;
}

.full-screen-preview {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}

.frame-container {
    width: 90%;
    max-width: 1200px;
    height: 90%;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
    overflow: hidden;
    background-color: #fff;
}

.frame-content {
    width: 100%;
    height: 100%;
}

iframe {
    width: 100%;
    height: 100%;
    border: none;
}

</style>
<body>
    <div class="full-screen-preview">
        <div class="frame-container">
            <div class="frame-content">
                <!-- Your content goes here -->
                <iframe src="https://example.com/your-tickettrick-landing-page" name="preview-frame" frameborder="0" allow="geolocation 'self'; autoplay 'self'"></iframe>
            </div>
        </div>
    </div>
</body>
</html>
