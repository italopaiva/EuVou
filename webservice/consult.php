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
        
	while ($row = $queryResult->fetch_assoc())
		$result[] = $row;
	
        $response = json_encode($result,JSON_FORCE_OBJECT);
        $database->disconnect();
    }else{
        $response = json_encode(array("Erro" => 0));
    }
}else{
    $response = json_encode(array("Erro" => 0));
}
echo $response;

?>
