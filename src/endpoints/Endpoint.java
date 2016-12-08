package endpoints;

import java.util.List;

import classes.Metadata;
import classes.Request;
import classes.Result;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Endpoint {
	
	@POST("find-by-metadata-field")
	Call<List<Result>> findByMetadataField(@Body Request request);
	
	@GET("{id}/metadata")
	Call<List<Metadata>> getOA(@Path("id") int id);
}
