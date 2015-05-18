package dbconnect;

public interface DBIntializer_Prodn {
String DRIVER="oracle.jdbc.driver.OracleDriver";
String CON_STRING="jdbc:oracle:thin:@localhost:1521:xe";
String USERNAME="system";
String PASSWORD="d@tabas3";
}

/* for date picker
 * 
 * <script>
  $(function() {
	  $("#datepicker").datepicker({dateFormat:'dd-MM-yy',minDate:"+7D" ,maxDate:new Date(2014, 11,10)});
    
  });
  </script>
 * 
 * */
 