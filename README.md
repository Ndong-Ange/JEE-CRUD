1. Lister les avions :
Méthode : GET
URL : http://localhost:8080/planes
Corps de la requête : Aucun, c'est une requête de type GET.

PS : entre les infos dans le raw 

2. Ajouter un avion :
Méthode : POST
URL : http://localhost:8080/planes/add

3. Modifier un avion :
Méthode : POST
URL : http://localhost:8080/planes/edit/1 (remplacez 1 par l'ID réel de l'avion à modifier)

4. Supprimer un avion :
Méthode : GET
URL : http://localhost:8080/planes/delete/1 (remplacez 1 par l'ID de l'avion à supprimer)