<?php

	class Database
	{
		private $con;
		private $user;
		private $pass;
		private $host;
		private $db;

		public function __construct($users,$password,$link,$database)
		{
			$this->user = $users;
			$this->pass = $password;
			$this->host = $link;
			$this->db = $database;
		}

		public function connect()
		{
			$this->con = new mysqli($this->host, $this->user, $this->pass, $this->db);
			if (mysqli_connect_errno())
			{
				return false;
			}
			else
			{
				return true;
			}
		}

		public function query($query)
		{
			return $this->con->query($query);
			/*if ($result = $this->con->query($query)) {

				while($row = $result->fetch_assoc()){
					$rows[] = $row;
				}
			}
			else{
				$rows = $queryResult;
			}
			$result->close();
			return $rows;*/
		}

		public function disconnect()
		{
			$this->con->close();
		}
	}

?>
