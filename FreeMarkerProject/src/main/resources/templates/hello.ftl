<!DOCTYPE html>
<html charset="UTF-8">
<head>
<title>Message</title>
</head>
<body>
	<table border="1" cellspacing="1" cellpadding="1" width="50%">
		<thead>
			<tr>
				<th>Name</th>
				<th>Place</th>
			</tr>
		</thead>
      	<tbody>
		<#assign x=100>
		<#list 1..x as i>
			<#list persons as person>
				<tr>
					<td>${person.name}</td>
					<td>${person.place}</td>
				</tr>
			</#list>
		</#list>
		</tbody>
	</table>
</body>
</html>