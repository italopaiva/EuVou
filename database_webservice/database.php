<?php

class Database{

    /**
     * Set this constant to the database name on the server
     */

    private $username;
    private $password;
    private $host;

    private $databaseName;
    private $connection;

    public function __construct($username, $password, $host, $databaseName){

        $this->setUsername($username);
        $this->setPassword($password);
        $this->setHost($host);
        $this->setDatabaseName($databaseName);
    }

    public function query($query){

        $queryResult = mysql_query($query);

        if($queryResult !== TRUE && $queryResult !== FALSE){
            
            $rows = array();
            while($row = mysql_fetch_array($queryResult, MYSQL_ASSOC)){
                $rows[] = $row;
            }
        }else{
            $rows = $queryResult;
        }

        return $rows;
    }

    public function connect(){

        $username = $this->username();
        $password = $this->password();
        $host = $this->host();
        $databaseName = $this->databaseName();

        $dbconnection = mysql_connect($host, $username, $password);

        if($dbconnection){

            $databaseExists = mysql_select_db($databaseName, $dbconnection);

            if($databaseExists){

                $this->establishConnection($dbconnection);

                $connectionResult = TRUE;

            }else{
                $connectionResult = FALSE;
            }

        }else{
            $connectionResult = FALSE;
        }

        return $connectionResult;
    }

    public function disconnect(){

        mysql_close($this->connection);
    }

    private function establishConnection($dbconnection){
        $this->connection = $dbconnection;
    }

    private function setUsername($username){
        $this->username = $username;
    }

    private function setPassword($password){
        $this->password = $password;
    }

    private function setHost($host){
        $this->host = $host;
    }

    private function setDatabaseName($databaseName){
        $this->databaseName = $databaseName;
    }

    private function username(){
        return $this->username;
    }

    private function password(){
        return $this->password;
    }

    private function host(){
        return $this->host;
    }

    private function databaseName(){
        return $this->databaseName;
    }

}