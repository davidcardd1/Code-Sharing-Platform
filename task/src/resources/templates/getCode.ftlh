<html lang="en">
<head>
    <title>Create</title>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<form>
    <label for="code_snippet"></label><textarea id="code_snippet"></textarea>
    <br>
    Time restriction <label for="time_restriction"></label><input id="time_restriction" type="text"/>
    <br>
    Views restriction <label for="views_restriction"></label><input id="views_restriction" type="text"/>
    <br>
    <button id="send_snippet" type="submit" onclick="send()">Submit</button>
</form>
</body>
<script>
    function send() {
        let object = {
            "code": document.getElementById("code_snippet").value,
            "time": document.getElementById("time_restriction").value,
            "views": document.getElementById("views_restriction").value
        };
        let json = JSON.stringify(object);
        let xhr = new XMLHttpRequest();
        xhr.open("POST", '/api/code/new', false)
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xhr.send(json);
        if (xhr.status === 200) {
            console.log(xhr.response);
            alert("Success!");
        }
    }
</script>
</html>