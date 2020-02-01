//Query_Parameters class is to create query for both GET and POST method.
//Query should locate after /post or /get.
//e.g. "POST /post?info=info HTTP/1.0\r\n" "?info=info " is query.
//NOTICE: if query not empty, should add "?" before the query.

public class Query_Parameters {
    String query_Parameter;

    public Query_Parameters(){
        query_Parameter = "? ";
    }

    public Query_Parameters(String query_Parameters){
        this.query_Parameter = "?" + query_Parameters + " ";
    }

    public String getQuery_Parameter() {
        return query_Parameter;
    }
}
