<?php

	require_once('db.php');
	require_once('database_config.php');
	require_once('parser.php');
	require_once('ParserException.php');

	class LocalParser{

		const NAME_COLUMN = "namePlace";
		const PHONE_COLUMN = "phonePlace";
		const HOURS_COLUMN = "operation";
		const DESCRIPTION_COLUMN = "description";
		const ADDRESS_COLUMN = "address";
		const LONGITUDE_COLUMN = "longitude";
		const LATITUDE_COLUMN = "latitude";

		const FILE_NAME = "local.csv";

		public function parse(){

			try{

				$parser = new Parser(self::FILE_NAME);

				$spreadsheet = $parser->data();

				$db = new Database(
					DatabaseConfig::USERNAME,
					DatabaseConfig::PASSWORD,
					DatabaseConfig::HOST,
					DatabaseConfig::DATABASE_NAME
				);

				$db_is_connected = $db->connect();

				if($db_is_connected){

					$spreadsheet = $spreadsheet->data;

					if(sizeof($spreadsheet) != 0){

						$latitudes = array();
						$longitudes = array();
						$names = array();

						foreach($spreadsheet as $tuple){

							$current_latitude = $tuple[self::LATITUDE_COLUMN];
							$current_longitude =  $tuple[self::LONGITUDE_COLUMN];
							$current_name = $tuple[self::NAME_COLUMN];

							$latitude_is_not_repeated = !array_key_exists($current_latitude, $latitudes);
							$longitude_is_not_repeated = !array_key_exists($current_longitude, $longitudes);
							$name_is_not_repeated = !array_key_exists($current_name, $names);

							$data_is_not_repeated = $latitude_is_not_repeated && $longitude_is_not_repeated && $name_is_not_repeated;

							if($data_is_not_repeated){

								// Insert the coordinates first
								$insert_coordinates = "INSERT INTO tb_locate(longitude, latitude, address)
								VALUES ('".$tuple[self::LONGITUDE_COLUMN]."', '".$tuple[self::LATITUDE_COLUMN]."', '"
								.$tuple[self::ADDRESS_COLUMN]."')";

								$db->query($insert_coordinates);

								// Insert the place data with the coordinates referenced
								$insert_place = "INSERT INTO tb_place(namePlace, phonePlace, description, latitude, longitude, operation)
								VALUES('".$tuple[self::NAME_COLUMN]."', '".$tuple[self::PHONE_COLUMN]."', '"
								.$tuple[self::DESCRIPTION_COLUMN]."', '".$tuple[self::LATITUDE_COLUMN]."','"
								.$tuple[self::LONGITUDE_COLUMN]."', '".$tuple[self::HOURS_COLUMN]."')";

								$db->query($insert_place);

								$latitudes[$current_latitude] = $current_latitude;
								$longitudes[$current_longitude] = $current_longitude;
								$names[$current_name] = $current_name;
							}
						}

						$db->disconnect();

						$wasSaved = TRUE;
					}else{

						$wasSaved = FALSE;
					}

				}else{
					$wasSaved = FALSE;
				}

			}catch(ParserException $caught_exception){
				$wasSaved = FALSE;
			}

			return $wasSaved;
		}
	}

?>