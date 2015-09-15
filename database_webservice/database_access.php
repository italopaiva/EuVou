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

        $result = array("status" => 1);
        $result["result"] = $queryResult;

        $response = json_encode($result);

        $database->disconnect();

    }else{
        $response = json_encode(array("status" => 0));
    }

}else{
    $response = json_encode(array("status" => 0));
}

echo $response;

?>
