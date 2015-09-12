<?php
require_once("database.php");

/**
 * Set these constants to your specific database values
 */
define("HOST", "localhost");
define("USERNAME", "root");
define("PASSWORD", "");
define("DATABASE_NAME", "webservice");
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
        // Could connect to DB or the requested database does not exists.
        $response = json_encode(array("status" => 0));
    }

}else{
    $response = json_encode(array("status" => 0));
}

echo $response;

?>