<?php
require_once("db.php");

/**
 * Set these constants to your specific database values
 */
define("HOST", "mysql.hostinger.com.br");
define("USERNAME", "u956965464_euvou");
define("PASSWORD", "loveteam");
define("DATABASE_NAME", "u956965464_euvou");
/** 
 */

if($_POST){

    $query = $_POST['query'];

    $database = new Database(USERNAME, PASSWORD, HOST, DATABASE_NAME);

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
