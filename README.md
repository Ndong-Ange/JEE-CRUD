1. Lister les avions :
Méthode : GET
URL : http://localhost:8080/planes/api
Corps de la requête : Aucun, c'est une requête de type GET.

PS : entre les infos dans le raw 

2. Ajouter un avion :
Méthode : POST
URL : http://localhost:8080/planes/add/api

3. Modifier un avion :
Méthode : PUT
URL : http://localhost:8080/planes/edit/{id}/api (remplacez 1 par l'ID réel de l'avion à modifier)

4. Supprimer un avion :
Méthode : GET
URL : http://localhost:8080/planes/delete/{id}/api (remplacez 1 par l'ID de l'avion à supprimer)
