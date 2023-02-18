<html lang="en">
<head>
    <title>Code</title>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<#list codes as code>
    <div>
        <pre id="code_snippet">
            <code>
            ${code.code}
            </code>
        </pre>
        <span id="load_date">${code.date}</span>
        <#if code.timeRestricted>
            <span id="time_restriction"> Time: ${code.time} </span>
        </#if>
        <#if code.viewsRestricted>
            <span id="views_restriction"> Views: ${code.views} </span>
        </#if>
    </div>
    <br>
</#list>
</body>
</html>