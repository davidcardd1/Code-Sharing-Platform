package platform.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@JsonSerialize
@Data
public class IdObject {
    private String id;

   public IdObject(String id) {
       this.id = id;
   }
}
