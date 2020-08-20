<!DOCTYPE html>
<html charset="UTF-8">
<head>
<title>Message</title>
</head>
<body>
	<table border="1" cellspacing="0" cellpadding="1">
	<tr>
	<th>Name</th>
	<th>Place</th>
	</tr>
	<#list persons as person>
	<tr>
	<td>${person.name}</td>
	<td>${person.place}</td>
	</tr>
	</#list>
	</table>
</body>
</html>