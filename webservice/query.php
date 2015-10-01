<?php
require_once("db.php");
require_once("database_config.php");

if($_POST){

    $query = $_POST['query'];

    $database = new Database(
        DatabaseConfig::USERNAME,
        DatabaseConfig::PASSWORD,
        DatabaseConfig::HOST,
        DatabaseConfig::DATABASE_NAME
    );

    $connection = $database->connect();

    if($connection){

        $queryResult = $database->query($query);

		if($queryResult != NULL)
			echo "Salvo com Sucesso";
		else
			echo "Erro ao salvar, tente novamente";
        $database->disconnect();

    }else{
		echo "Erro ao estabelecer conexão com o banco de dados";
    }

}else{
		echo "Erro receber os dados";
}

echo $response;

?>
