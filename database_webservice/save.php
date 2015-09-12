<?php

require_once("database.php");

define("HOST", "localhost");
define("USERNAME", "root");
define("PASSWORD", "");
define("DATABASE_NAME", "webservice");

if($_POST){

    $name = $_POST['username'];
    $password = $_POST['password'];

    $database = new Database(USERNAME, PASSWORD, HOST, DATABASE_NAME);

    $connection = $database->connect();

    if($connection){

        $queryResult = $database->query("INSERT INTO users(username, password) VALUES('".$name."', '".$password."')");

        echo "Cadastrado com sucesso.";

        $database->disconnect();

    }else{
        echo "Não foi possível conectar ao banco de dados";
    }

}else{
    echo "Não foi possível acessar os dados informados.";
}