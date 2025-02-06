package pe.com.bcp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Semilla {
    private String categories;
	
	private String created_at;
	
	private String icon_url;

	private String id;
	
	private String updated_at;
	
	private String url;

	private String value;
}
